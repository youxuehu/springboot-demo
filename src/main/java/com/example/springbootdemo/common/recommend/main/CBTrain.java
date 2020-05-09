package com.example.springbootdemo.common.recommend.main;

import com.google.common.base.Joiner;
import com.qianxinyao.analysis.jieba.keyword.Keyword;
import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;
import java.io.*;
import java.util.*;

public class CBTrain {
    String input_file = "src/main/java/com/example/springbootdemo/common/recommend/data/merge_base.data";
    // 输出cb训练数据
    String output_file = "src/main/java/com/example/springbootdemo/common/recommend/data/cb_train.data";
    PrintWriter writer = null;
    final Float RATIO_FOR_NAME = 0.9f;
    final Float RATIO_FOR_DESC = 0.1f;
    final Float RATIO_FOR_TAGS = 0.05f;
    String idf_file = "src/main/java/com/example/springbootdemo/common/recommend/ready/idf.txt";
    Map<String, Object> idfDict = new HashMap<>();
//    itemid_set = set()
    Set<String> itemidSet = new HashSet<>();
    public CBTrain() {
        try {
            writer = new PrintWriter(new FileOutputStream(output_file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openIdfFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(idf_file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.trim().split(" ");
//                token, idf_score
                String token = ss[0];
                String idfScore = ss[1];
                idfDict.put(token, idfScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void openInputFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(input_file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.trim().split("\001");
                String userid = ss[0].trim();
                String itemid = ss[1].trim();
                String watch_len = ss[2].trim();
                String hour = ss[3].trim();
                // 用户画像
                String gender = ss[4].trim();
                String age = ss[5].trim();
                String salary = ss[6].trim();
                String user_location = ss[7].trim();
                // 物品元数据
                String name = ss[8].trim();
                String desc = ss[9].trim();
                String total_timelen = ss[10].trim();
                String item_location = ss[11].trim();
                String tags = ss[12].trim();
                if (!itemidSet.contains(itemid)) {
                    itemidSet.add(itemid);
                } else {
                    continue;
                }
                Map<String, Float> token_dict = new HashMap<>();
                List<Keyword> names=jiebaTfidf(name);
                names.forEach(item->{
                    String token = item.getName();
                    float score = item.getTfidfvalue().floatValue();
                    if (token_dict.containsKey(token)) {
                        Float aFloat = token_dict.get(token);
                        token_dict.put(token, aFloat += score * RATIO_FOR_NAME);
                    } else {
                        token_dict.put(token, score * RATIO_FOR_NAME);
                    }
                });

                List<Keyword> descs = jiebaTfidf(desc);
                descs.forEach(item->{
                    String token = item.getName();
                    float score = item.getTfidfvalue().floatValue();
                    if (token_dict.containsKey(token)) {
                        Float aFloat = token_dict.get(token);
                        token_dict.put(token, aFloat += score * RATIO_FOR_DESC);
                    } else {
                        token_dict.put(token, score * RATIO_FOR_DESC);
                    }
                });
                String[] tagList = tags.trim().split(",");
                for (String tag : tagList) {
                    if (!idfDict.containsKey(tag)) {
                        continue;
                    } else {
                        Float f = Float.valueOf((String) idfDict.get(tag));
                        if (token_dict.containsKey(tag)) {
                            Float aFloat = token_dict.get(tag);
                            token_dict.put(tag, aFloat += f * RATIO_FOR_TAGS);
                        } else {
                            token_dict.put(tag, f * RATIO_FOR_TAGS);
                        }
                    }
                }

                for (Map.Entry entry : token_dict.entrySet()) {
                    String token = ((String) entry.getKey()).trim();
                    String score = String.valueOf(entry.getValue());
                    writer.println(Joiner.on(",").join(Arrays.asList(token, itemid, score)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        CBTrain cbTrain = new CBTrain();
        cbTrain.openIdfFile();
        cbTrain.openInputFile();
    }

    private static List<Keyword> jiebaTfidf(String content) {
        int topN=5;
        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
        List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
        return list;
    }
}
