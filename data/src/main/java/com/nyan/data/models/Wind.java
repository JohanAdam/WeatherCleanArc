
package com.nyan.data.models;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("deg")
    private Long mDeg;
    @SerializedName("speed")
    private Double mSpeed;

    public Long getDeg() {
        return mDeg;
    }

    public Double getSpeed() {
        return mSpeed;
    }

    public static class Builder {

        private Long mDeg;
        private Double mSpeed;

        public Wind.Builder withDeg(Long deg) {
            mDeg = deg;
            return this;
        }

        public Wind.Builder withSpeed(Double speed) {
            mSpeed = speed;
            return this;
        }

        public Wind build() {
            Wind wind = new Wind();
            wind.mDeg = mDeg;
            wind.mSpeed = mSpeed;
            return wind;
        }

    }

}
