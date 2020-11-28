
package com.nyan.data.models;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private Long mAll;

    public Long getAll() {
        return mAll;
    }

    public static class Builder {

        private Long mAll;

        public Clouds.Builder withAll(Long all) {
            mAll = all;
            return this;
        }

        public Clouds build() {
            Clouds clouds = new Clouds();
            clouds.mAll = mAll;
            return clouds;
        }

    }

}
