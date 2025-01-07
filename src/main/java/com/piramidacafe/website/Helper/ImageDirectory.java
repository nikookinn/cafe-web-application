package com.piramidacafe.website.Helper;

public enum ImageDirectory {
    APP_IMAGES("app_images"),
    MENU_IMAGES("menu_images"),
    CATEGORY_IMAGES("category_images"),
    ITEM_IMAGES("item_images"),
    CAMPAIGN_IMAGES("campaign_images"),
    APP_ICON("app_icon");

    private final String directory;

    public String getDirectory() {
        return directory;
    }

    ImageDirectory(String directory) {
        this.directory = directory;
    }

}
