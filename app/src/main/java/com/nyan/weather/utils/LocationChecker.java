package com.nyan.weather.utils;

import android.content.Context;
import android.location.LocationManager;

public class LocationChecker {

  private Context context;

  public LocationChecker(Context context) {
    this.context = context;
  }

  public void checkLocationEnable(LocationCheckerCallback callback) {
    LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    boolean gps_enabled = false;
    boolean network_enabled = false;

    try {
      gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    } catch(Exception ex) {
      ex.printStackTrace();
    }

    try {
      network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    } catch(Exception ex) {
      ex.printStackTrace();
    }

    if(!gps_enabled && !network_enabled) {
      // notify user
      callback.onError("Please turn on your location.");
    } else {
      callback.onSuccess();
    }
  }

  public interface LocationCheckerCallback {
    void onSuccess();
    void onError(String msg);
  }

}
