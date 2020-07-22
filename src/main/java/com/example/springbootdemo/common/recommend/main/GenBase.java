package com.example.springbootdemo.common.recommend.main;

import com.google.common.base.Joiner;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenBase {
    String userActionData = "src/main/java/com/example/springbootdemo/common/recommend/ready/user_watch_pref.sml";
    String musicMetaData = "src/main/java/com/example/springbootdemo/common/recommend/ready/music_meta";
    String userProfileData = "src/main/java/com/example/springbootdemo/common/recommend/ready/user_profile.data";
    String output_file = "src/main/java/com/example/springbootdemo/common/recommend/data/merge_base.data";
    PrintWriter writer = null;

    Map<String, Object> itemInfoDict = new HashMap<>();
    Map<String, Object> userProfileDict = new HashMap<>();
    public GenBase() {
        try {
            writer = new PrintWriter(new FileOutputStream(output_file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMusicData() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(musicMetaData));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.trim().split("\001");
                if (ss.length != 6) {
                    continue;
                }
                String itemId = ss[0];
                String name = ss[1];
                String desc = ss[2];
                String totalTimeLen = ss[3];
                String location = ss[4];
                String tags = ss[5];
                itemInfoDict.put(itemId, Joiner.on("\001").join(Arrays.asList(name, desc, totalTimeLen, location, tags)));
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

    public void openProfileData() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(userProfileData));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.trim().split(",");
                if (ss.length != 5) {
                    continue;
                }
                String userid = ss[0];
                String gender = ss[1];
                String age = ss[2];
                String salary = ss[3];
                String location = ss[4];
                userProfileDict.put(userid, Joiner.on("\001").join(Arrays.asList(gender, age, salary, location)));
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

    public void openUserActionData() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(userActionData));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.trim().split("\001");
                if (ss.length != 4) {
                    continue;
                }
                String userId = ss[0];
                String itemId = ss[1];
                String watchLen = ss[2];
                String hour = ss[3];
                if (!userProfileDict.containsKey(userId)) {
                    continue;
                }
                if (!itemInfoDict.containsKey(itemId)) {
                    continue;
                }
                writer.println(Joiner.on("\001").join(Arrays.asList(userId, itemId, watchLen, hour, userProfileDict.get(userId), itemInfoDict.get(itemId))));
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        GenBase genBase = new GenBase();
        genBase.openMusicData();
        genBase.openProfileData();
        genBase.openUserActionData();
    }
}
