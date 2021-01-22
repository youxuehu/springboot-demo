package com.example.springbootdemo.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ShellUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ShellUtil.class);

    public static List<String> runShell(List<String> cmds) throws Exception{
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(cmds);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        process.waitFor();
        InputStream inputStream = process.getInputStream();
        List<String> readLines = IOUtils.readLines(inputStream, "utf-8");
        for (String line : readLines) {
            LOGGER.info(line);
        }
        process.destroy();
        // 含有application_id的日志不加换行符
        return readLines.stream().map(item -> {
            if (!item.contains("Submitted application ")) {
                return item + "<br/>";
            }
            return item;
        }).collect(Collectors.toList());
    }
}
