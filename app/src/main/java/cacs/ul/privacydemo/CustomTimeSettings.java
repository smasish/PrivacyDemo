package cacs.ul.privacydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.api.ResultCallback;


import java.util.ArrayList;
import java.util.List;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomTimeSettings extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {


private Context con;
    private Location mylocation;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS=0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS=0x2;

    private static Button date, time;
    private static Button date_end, time_end;
    private static TextView set_date, set_time,lat,longs;
    private static TextView set_date_end, set_time_end;
    private EditText loc;
    private static final int Date_id = 0;
    private static final int Time_id = 1;

    private static final int Date_eid = 2;
    private static final int Time_eid = 3;
    private String lat_st="",long_st="";
    private boolean e1=false,e2=false;

    /////////////////////////
    String str="";
    private String startDate,startTime,endDate,endTime,staff_id;
    private QueryDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_time_settings);

        con = this;

        date = (Button) findViewById(R.id.selectdate);
        time = (Button) findViewById(R.id.selecttime);
        set_date = (TextView) findViewById(R.id.set_date);
        set_time = (TextView) findViewById(R.id.set_time);

        date_end = (Button) findViewById(R.id.selectdate_to);
        time_end = (Button) findViewById(R.id.selecttime_to);
        set_date_end = (TextView) findViewById(R.id.set_date_to);
        set_time_end = (TextView) findViewById(R.id.set_time_to);

        loc = (EditText)findViewById(R.id.loc_text);

        lat = (TextView) findViewById(R.id.lat_id);
        longs = (TextView) findViewById(R.id.long_id);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MM / dd / yyyy"); //MM-dd-yyyy
        String strDate = "" + mdformat.format(calendar.getTime());
        set_date.setText(strDate);

        set_date_end.setText(strDate);


        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        String time_start = timeformat.format(Calendar.getInstance().getTime());

        set_time_end.setText(time_start);

        set_time.setText(time_start);


        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show Date dialog
                showDialog(Date_id);
            }
        });
        time.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show time dialog
                showDialog(Time_id);
            }
        });
        ///////////////////////////
        date_end.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
               e1=true;
                // Show Date dialog
                showDialog(Date_eid);
            }
        });
        time_end.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                e2=true;
                // Show time dialog
                showDialog(Time_eid);
            }
        });


        setUpGClient();
        getMyLocation();


        datasource = new QueryDataSource(this);
        datasource.open();
        datasource.getAllComments();


    }

    protected Dialog onCreateDialog(int id) {

        // Get the calander
        Calendar c = Calendar.getInstance();

        // From calander get the year, month, day, hour, minute
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
e1= false;
e2 = false;
        switch (id) {
            case Date_id:

                // Open the datepicker dialog
                return new DatePickerDialog(CustomTimeSettings.this, date_listener, year,
                        month, day);
            case Time_id:

                // Open the timepicker dialog
                return new TimePickerDialog(CustomTimeSettings.this, time_listener, hour,
                        minute, false);

            case Date_eid:
                e1=true;
                // Open the datepicker dialog
                return new DatePickerDialog(CustomTimeSettings.this, date_listener, year,
                        month, day);

            case Time_eid:
                e2=true;
                // Open the timepicker dialog
                return new TimePickerDialog(CustomTimeSettings.this, time_listener, hour,
                        minute, false);

        }
        return null;
    }

    // Date picker dialog
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // store the data in one string and set it to text
            String date1 = String.valueOf(month+1) + "/" + String.valueOf(day)
                    + "/" + String.valueOf(year);

            if(e1)
                set_date_end.setText(date1);
            else
               set_date.setText(date1);
        }
    };
    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            // store the data in one string and set it to text
            String time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
            if(e2)
                set_time_end.setText(time1);
            else
                set_time.setText(time1);
        }
    };

    public void view_Time(View v){
        Intent req = new Intent(CustomTimeSettings.this, Custom_P_ListActivity.class);
        //req.putExtra("level","Location");
        startActivity(req);
    }

    public void saveDateTime(View v){
//startDate,startTime,endDate,endTime,staff_id;
        startDate = set_date.getText().toString();
        endDate = set_date_end.getText().toString();
        startTime = set_time.getText().toString();
        endTime = set_time_end.getText().toString();

        lat_st = lat.getText().toString();
        long_st = longs.getText().toString();

        str = loc.getText().toString();
        ProfileData profileData = null;

        if(datasource.getAllComments().size()<2)
        profileData = datasource.createComment(startDate,endDate,startTime,endTime,str,lat_st,long_st);


        Toast.makeText(con,"Saved date and time for \nCustom permissions"+set_date.getText(),Toast.LENGTH_LONG).show();


        Intent req = new Intent(CustomTimeSettings.this, Custom_P_ListActivity.class);
        req.putExtra("level","Location");
        startActivity(req);


    }

    ////////////////////////////////////////////////////

    private synchronized void setUpGClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, 0, this)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        if (mylocation != null) {
            Double latitude=mylocation.getLatitude();
            Double longitude=mylocation.getLongitude();

            lat_st = ""+latitude;
            long_st = ""+longitude;
            lat.setText("Latitude : "+latitude);
            longs.setText("Longitude : "+longitude);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        checkPermissions();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    private void getMyLocation(){
        if(googleApiClient!=null) {
            if (googleApiClient.isConnected())
            {
                int permissionLocation = ContextCompat.checkSelfPermission(CustomTimeSettings.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation =                     LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                    PendingResult<LocationSettingsResult> result =LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    int permissionLocation = ContextCompat.checkSelfPermission(CustomTimeSettings.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED)
                                    {
                                        mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    try
                                    {
                                        status.startResolutionForResult(CustomTimeSettings.this,REQUEST_CHECK_SETTINGS_GPS);
                                    }
                                    catch (IntentSender.SendIntentException e)
                                    {
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    break;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        finish();
                        break;
                }
                break;
        }
    }

    private void checkPermissions(){
        int permissionLocation = ContextCompat.checkSelfPermission(CustomTimeSettings.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        }else{
            getMyLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(CustomTimeSettings.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        }
    }
}
