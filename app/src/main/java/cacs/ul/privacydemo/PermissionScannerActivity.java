package cacs.ul.privacydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static java.lang.Package.getPackages;

public class PermissionScannerActivity extends AppCompatActivity implements View.OnClickListener {

    private Context con;

    public String[] text_info = {"android.permission.SEND_SMS","android.permission.CALL_PHONE"};
    public String[] personal_info = {"android.permission.READ_CALENDAR", "android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS",
            "android.permission.READ_PROFILE", "android.permission.READ_SMS", "android.permission.READ_SOCIAL_STREAM"};
    public String[] location_info = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    public String[] impact_battery = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.BLUETOOTH",
            "android.permission.CALL_PHONE", "android.permission.FLASHLIGHT", "android.permission.NFC"};
    public String[] change_system = {"android.permission.WRITE_SETTINGS"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_scanner);

        con = this;

        Toast.makeText(con,"Working on it",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View arg0) {
        //do scan
//        if(!results){
//            arg0.setEnabled(false);
//            getPackages();
//        }
//        // go to results
//        else{
//            Intent myIntent = new Intent(PermissionScannerActivity.this, ProfileUIActivity.class);
//
//            try{
//
//                myIntent.putParcelableArrayListExtra("money_obj", money_obj);
//                myIntent.putParcelableArrayListExtra("personal_info_obj", personal_info_obj);
//                myIntent.putParcelableArrayListExtra("can_impact_battery_obj", battery_obj);
//                myIntent.putParcelableArrayListExtra("can_change_system_obj", system_obj);
//                myIntent.putParcelableArrayListExtra("location_info_obj", location_info_obj);
//                myIntent.putParcelableArrayListExtra("has_majority",has_majority);
//                startActivity(myIntent);
//                finish();
//            }catch(Exception e){
//            }

 //       }
    }
}
