package com.nyan.weather.utils;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class PermissionManager {

  public static final int LOCATION_PERMISSION_REQUEST_CODE = 199;
  public static final int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;
  public static final int PERMISSION_DENIED = PackageManager.PERMISSION_DENIED;

  public PermissionManager() {

  }

  public boolean isLocationPermissionGranted(Context context) {
    return isPermissionGranted(context, permission.ACCESS_FINE_LOCATION);
  }

  public void requestLocationPermission(Fragment fragment) {
    requestPermission(fragment, permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE);
  }

  public void requestLocationPermission(Activity activity) {
    requestPermission(activity, permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE);
  }

  private void requestPermission(Fragment fragment, String permission, int requestCode) {
    fragment.requestPermissions(new String[]{permission}, requestCode);
  }

  private void requestPermission(Activity activity, String permission, int requestCode) {
    if (!isPermissionGranted(activity, permission)) {
      ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }
  }

  private boolean isPermissionGranted(Context context, String permission) {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
  }


}
