
package com.nyan.domain.models;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Main {

    private Double feels_like;

    private Long humidity;

    private Long pressure;

    private Double temp;

    private Double temp_max;

    private Double temp_min;

    public Double getFeelsLike() {
        return feels_like;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feels_like = feelsLike;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public void setTempMax(Double tempMax) {
        this.temp_max = tempMax;
    }

    public Double getTempMin() {
        return temp_min;
    }

    public void setTempMin(Double tempMin) {
        this.temp_min = tempMin;
    }

}
