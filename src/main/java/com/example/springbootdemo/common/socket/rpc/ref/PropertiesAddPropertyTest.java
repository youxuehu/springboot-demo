package com.example.springbootdemo.common.socket.rpc.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesAddPropertyTest {


    public static void main(String[] args) {


        try {
            Properties properties = new Properties();

            URL url = PropertiesAddPropertyTest.class.getClassLoader().getResource
                    ("com/example/springbootdemo/common/socket/rpc/ref/config/config.properties");
            System.out.println(url);
            File file = new File(url.toURI());
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            String className = properties.getProperty("className");
            System.out.println(className);

            FileOutputStream fileOutputStream = new FileOutputStream(
                    new File("C:\\Users\\youxuehu\\IdeaProjects\\springboot-demo\\src\\main\\java\\com\\example\\springbootdemo\\common\\socket\\rpc\\ref\\config\\config.properties"));

            properties.setProperty("appName", "aistudio");
            properties.store(fileOutputStream, "app name");
            fileOutputStream.flush();
            fileOutputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getRuntimePath() {
        String classPath = PropertiesAddPropertyTest.class.getName().replaceAll("\\.", "/") + ".class";
        URL resource = PropertiesAddPropertyTest.class.getClassLoader().getResource(classPath);
        if (resource == null) {
            return null;
        }
        String urlString = resource.toString();
        int insidePathIndex = urlString.indexOf('!');
        boolean isInJar = insidePathIndex > -1;
        if (isInJar) {
            urlString = urlString.substring(urlString.indexOf("file:"), insidePathIndex);
            return urlString;
        }
        return urlString.substring(urlString.indexOf("file:"), urlString.length() - classPath.length());
    }
}
