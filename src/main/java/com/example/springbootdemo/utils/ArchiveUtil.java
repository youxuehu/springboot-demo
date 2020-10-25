package com.example.springbootdemo.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.springbootdemo.utils.ShellUtil.runShell;

public class ArchiveUtil {

    public static String generateTar(File file) throws Exception {
        String tarPath = file.getAbsolutePath() + ".tar";
        //1:压缩成.tar
        TarArchiveOutputStream tos = new TarArchiveOutputStream(new FileOutputStream(tarPath));
        tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        try {
            doTar(file, tos, file.getName());
            tos.flush();
            return tarPath;
        } finally {
            IOUtils.closeQuietly(tos);
        }
    }

    public static void generateTargz(File file) throws Exception {
        String tarPath = generateTar(file);
        String tarGzPath = tarPath + ".gz";
        //2:压缩成.tar.gz
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tarPath));
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(tarGzPath)));
        try {
            byte[] buffer = new byte[1024];
            int read = -1;
            while ((read = bis.read(buffer)) != -1) {
                gcos.write(buffer, 0, read);
            }
            gcos.flush();
        } finally {
            IOUtils.closeQuietly(gcos);
        }
    }

    private static void doTar(File file, TarArchiveOutputStream tos, String base) throws Exception {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                doTar(child, tos, base + File.separator + child.getName());
            }
        } else {
            TarArchiveEntry entry = new TarArchiveEntry(base);
            entry.setSize(file.length());
            tos.putArchiveEntry(entry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] buffer = new byte[1024];
                int read = -1;
                while ((read = bis.read(buffer)) != -1) {
                    tos.write(buffer, 0, read);
                }
                tos.flush();
            } finally {
                bis.close();
                tos.closeArchiveEntry();
            }
        }
    }

    public static void generateTargz() {
        List<String> cmds = new ArrayList<>();
        cmds.add("/bin/sh");
        cmds.add("-c");
        cmds.add("cd /Users/youxuehu/IdeaProjects/springboot-demo/src/main/java/com/example/springbootdemo/utils && cat ArchiveUtil.java");
        try {
            runShell(cmds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> fetchFileNameWithContext(String filePath) throws IOException {
        Map<String, String> data = new HashMap<>();
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(filePath)));

        TarArchiveEntry nextTarEntry = null;
        while ((nextTarEntry = (TarArchiveEntry) tarArchiveInputStream.getNextEntry()) != null) {
            if (nextTarEntry.getSize() > 0) {
                data.put(nextTarEntry.getName(), new String(getContent(tarArchiveInputStream)));
            }
        }

        return data;
    }

    public static byte[] getContent(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bytes = new byte[1024];
            while (true) {
                int len = inputStream.read(bytes);
                if (len == -1) {
                    break;
                }
                byteArrayOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void main(String[] args) throws Exception {
//        generateTargz(new File("/Users/youxuehu/IdeaProjects/springboot-demo/target/classes"));
//        generateTargz();
        fetchFileNameWithContext("/Users/youxuehu/cmake-3.6.0-Linux-x86_64.tar.gz");
    }
}
