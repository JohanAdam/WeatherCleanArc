
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
    private Double mTempMax;
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

    public Double getTempMax() {
        return mTempMax;
    }

    public Double getTempMin() {
        return mTempMin;
    }

    public void setFeelsLike(Double mFeelsLike) {
        this.mFeelsLike = mFeelsLike;
    }

    public void setHumidity(Long mHumidity) {
        this.mHumidity = mHumidity;
    }

    public void setPressure(Long mPressure) {
        this.mPressure = mPressure;
    }

    public void setTemp(Double mTemp) {
        this.mTemp = mTemp;
    }

    public void setTempMax(Double mTempMax) {
        this.mTempMax = mTempMax;
    }

    public void setTempMin(Double mTempMin) {
        this.mTempMin = mTempMin;
    }

}
