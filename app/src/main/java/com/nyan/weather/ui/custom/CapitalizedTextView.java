package com.nyan.weather.ui.custom;

import android.content.Context;
import android.util.AttributeSet;

public class CapitalizedTextView extends androidx.appcompat.widget.AppCompatTextView {

  public CapitalizedTextView( Context context, AttributeSet attrs ) {
    super( context, attrs );
  }

  @Override
  public void setText( CharSequence c, BufferType type ) {

    /* Capitalize All Words */
    try {
      c = String.valueOf( c.charAt( 0 ) ).toUpperCase() + c.subSequence( 1, c.length() ).toString().toLowerCase();
      for ( int i = 0; i < c.length(); i++ ) {
        if ( String.valueOf( c.charAt( i ) ).contains( " " ) ) {
          c = c.subSequence( 0, i + 1 ) + String.valueOf( c.charAt( i + 1 ) ).toUpperCase() + c.subSequence( i + 2, c.length() ).toString().toLowerCase();
        }
      }
    } catch ( Exception e ) {
      // String did not have more than + 2 characters after space.
      e.printStackTrace();
    }
    super.setText( c, type );
  }

}