package com.fiares.Utility;

public class Menu {

    public Menu() {
    }

    public Menu(String title, String description, int image, String url) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
    }

    private String title, description, url;
    private int image;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
