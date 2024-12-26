package com.piramidacafe.website.Helper;

public enum ImageDirectory {
    APP_IMAGES("app_images"),
    MENU_IMAGES("menu_images");

    private final String directory;

    public String getDirectory() {
        return directory;
    }

    ImageDirectory(String directory) {
        this.directory = directory;
    }

}
