
package com.nyan.data.models;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("feels_like")
    private Double mFeelsLike;
    @SerializedName("humidity")
    private Long mHumidity;
    @SerializedName("pressure")
    private Long mPressure;
    @SerializedName("temp")
    private Double mTemp;
    @SerializedName("temp_max")
    private Long mTempMax;
    @SerializedName("temp_min")
    private Double mTempMin;

    public Double getFeelsLike() {
        return mFeelsLike;
    }

    public Long getHumidity() {
        return mHumidity;
    }

    public Long getPressure() {
        return mPressure;
    }

    public Double getTemp() {
        return mTemp;
    }

    public Long getTempMax() {
        return mTempMax;
    }

    public Double getTempMin() {
        return mTempMin;
    }

    public static class Builder {

        private Double mFeelsLike;
        private Long mHumidity;
        private Long mPressure;
        private Double mTemp;
        private Long mTempMax;
        private Double mTempMin;

        public Main.Builder withFeelsLike(Double feelsLike) {
            mFeelsLike = feelsLike;
            return this;
        }

        public Main.Builder withHumidity(Long humidity) {
            mHumidity = humidity;
            return this;
        }

        public Main.Builder withPressure(Long pressure) {
            mPressure = pressure;
            return this;
        }

        public Main.Builder withTemp(Double temp) {
            mTemp = temp;
            return this;
        }

        public Main.Builder withTempMax(Long tempMax) {
            mTempMax = tempMax;
            return this;
        }

        public Main.Builder withTempMin(Double tempMin) {
            mTempMin = tempMin;
            return this;
        }

        public Main build() {
            Main main = new Main();
            main.mFeelsLike = mFeelsLike;
            main.mHumidity = mHumidity;
            main.mPressure = mPressure;
            main.mTemp = mTemp;
            main.mTempMax = mTempMax;
            main.mTempMin = mTempMin;
            return main;
        }

    }

}
