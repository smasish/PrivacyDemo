package cacs.ul.privacydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

public class Main2Activity extends Activity {
    private static final int REQUEST_ENABLE_GPS = 516;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent in = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

       startActivityForResult(in,REQUEST_ENABLE_GPS);

       this.finish();

    }
}
