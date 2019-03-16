package me.imaxmc.adventureserver.utils;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by maximilianratmeyer on 4/7/16.
 */
public class WorldDownloader {

    private static String root = new File("").getAbsolutePath() + "/";

    public static String getRoot() {
        return root;
    }

    public static void downloadMap(String url, final String fileName, final String worldName) {

        try {
            downloadUsingStream(url, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info("Downloaded zip.");

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AdventureServer.getPlugin(), new Runnable() {
            @Override
            public void run() {

                try {
                    extractZIP(new File(getRoot() + fileName), new File(getRoot()));
                    Bukkit.getLogger().info("Unzipping.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 20);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AdventureServer.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.createWorld (new WorldCreator(worldName));

                File file = new File(root + fileName);
                file.delete();
            }
        }, 40);

    }

    public static void dirDelete(File file) throws IOException {
        if (file.isDirectory()) {
            if (file.list().length == 0)
                file.delete();
            else {
                String files[] = file.list();

                for (String temp : files)
                    dirDelete(new File(file, temp));

                if (file.list().length == 0)
                    file.delete();
            }
        } else {
            file.delete();
        }
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    public static void extractZIP(File archive, File destDir) throws IOException {

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        ZipFile zipFile = new ZipFile(archive);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        byte[] buffer = new byte[16384];
        int len;
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String entryFileName = entry.getName();
            File dir = buildDirectoryHierarchyFor(entryFileName, destDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!entry.isDirectory()) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destDir, entryFileName)));
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                while ((len = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }

                bos.flush();
                bos.close();
                bis.close();

            }

        }
        zipFile.close();
    }

    private static File buildDirectoryHierarchyFor(String entryName, File destDir) {
        int lastIndex = entryName.lastIndexOf('/');
        String internalPathToEntry = entryName.substring(0, lastIndex + 1);
        return new File(destDir, internalPathToEntry);
    }

}
