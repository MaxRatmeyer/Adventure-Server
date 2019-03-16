package me.imaxmc.adventureserver.maps;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class Map {

    public Map(String name, String author, String downloadLink, String fileName, String folderName, int id, String resourcePack) {
        this.name = name;
        this.author = author;
        this.downloadLink = downloadLink;
        this.fileName = fileName;
        this.folderName = folderName;
        this.id = id;
        this.resourcePack = resourcePack;
    }

    public Map(String name, String author, String downloadLink, String fileName, String folderName, int id) {
        this.name = name;
        this.author = author;
        this.downloadLink = downloadLink;
        this.fileName = fileName;
        this.folderName = folderName;
        this.id = id;
        this.resourcePack = null;
    }

    public String name;
    public String author;
    public String downloadLink;
    public String fileName;
    public String folderName;
    public int id;
    public String resourcePack;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public int getId() {
        return id;
    }

    public String getResourcePack() {
        return resourcePack;
    }

}
