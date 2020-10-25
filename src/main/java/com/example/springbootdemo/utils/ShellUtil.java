package com.example.springbootdemo.utils;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;

public class ShellUtil {
    public static List<String> runShell(List<String> cmds) throws Exception{
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(cmds);
        processBuilder.redirectErrorStream(true);
        boolean success = false;
        Process process = processBuilder.start();
        int returnCode = process.waitFor();
        InputStream inputStream = process.getInputStream();
        List<String> readLines = IOUtils.readLines(inputStream, "utf-8");
        for (String line : readLines) {
            System.out.println(line);
        }
        process.destroy();
        if (0 == returnCode) {
            success = true;
        }
        return readLines;
    }
}
