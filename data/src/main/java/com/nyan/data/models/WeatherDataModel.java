
package com.nyan.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherDataModel {

    @SerializedName("base")
    private String mBase;
    @SerializedName("clouds")
    private Clouds mClouds;
    @SerializedName("cod")
    private Long mCod;
    @SerializedName("coord")
    private Coord mCoord;
    @SerializedName("dt")
    private Long mDt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("name")
    private String mName;
    @SerializedName("sys")
    private Sys mSys;
    @SerializedName("timezone")
    private Long mTimezone;
    @SerializedName("visibility")
    private Long mVisibility;
    @SerializedName("weather")
    private List<Weather> mWeather;
    @SerializedName("wind")
    private Wind mWind;

    public String getBase() {
        return mBase;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public Long getCod() {
        return mCod;
    }

    public Coord getCoord() {
        return mCoord;
    }

    public Long getDt() {
        return mDt;
    }

    public Long getId() {
        return mId;
    }

    public Main getMain() {
        return mMain;
    }

    public String getName() {
        return mName;
    }

    public Sys getSys() {
        return mSys;
    }

    public Long getTimezone() {
        return mTimezone;
    }

    public Long getVisibility() {
        return mVisibility;
    }

    public List<Weather> getWeather() {
        return mWeather;
    }

    public Wind getWind() {
        return mWind;
    }

}
