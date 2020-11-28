
package com.nyan.data.models;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("id")
    private Long mId;
    @SerializedName("main")
    private String mMain;

    public String getDescription() {
        return mDescription;
    }

    public String getIcon() {
        return mIcon;
    }

    public Long getId() {
        return mId;
    }

    public String getMain() {
        return mMain;
    }

    public static class Builder {

        private String mDescription;
        private String mIcon;
        private Long mId;
        private String mMain;

        public Weather.Builder withDescription(String description) {
            mDescription = description;
            return this;
        }

        public Weather.Builder withIcon(String icon) {
            mIcon = icon;
            return this;
        }

        public Weather.Builder withId(Long id) {
            mId = id;
            return this;
        }

        public Weather.Builder withMain(String main) {
            mMain = main;
            return this;
        }

        public Weather build() {
            Weather weather = new Weather();
            weather.mDescription = mDescription;
            weather.mIcon = mIcon;
            weather.mId = mId;
            weather.mMain = mMain;
            return weather;
        }

    }

}
