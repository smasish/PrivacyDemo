package cacs.ul.privacydemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PermissionScannerActivity extends AppCompatActivity {

    private Context con;

    public String[] text_info = {"android.permission.SEND_SMS","android.permission.CALL_PHONE"};
    public String[] personal_info = {"android.permission.READ_CALENDAR", "android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS",
            "android.permission.READ_PROFILE", "android.permission.READ_SMS", "android.permission.READ_SOCIAL_STREAM"};
    public String[] location_info = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    public String[] can_impact_battery = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.BLUETOOTH",
            "android.permission.CALL_PHONE", "android.permission.FLASHLIGHT", "android.permission.NFC"};
    public String[] can_change_system = {"android.permission.WRITE_SETTINGS"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_scanner);

        con = this;

        Toast.makeText(con,"Working on it",Toast.LENGTH_LONG).show();
    }
}
