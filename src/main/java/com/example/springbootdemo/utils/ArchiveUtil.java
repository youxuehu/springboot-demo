package com.example.springbootdemo.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;

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
                gcos.write(buffer, 0 , read);
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

    public static void main(String[] args) throws Exception {
        generateTargz(new File("/Users/youxuehu/IdeaProjects/springboot-demo/target/classes"));
    }
}
