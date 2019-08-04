package cacs.ul.privacydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Alarm_Prof_Receiver extends BroadcastReceiver {

    String TAG = "Alarm_Prof_Receiver";
    private static final int REQUEST_ENABLE_GPS = 516;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                TimeScheduler_Temp.setReminder(context, Alarm_Prof_Receiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
//        TimeScheduler_Temp.showNotification(context, TempSettingsActivity.class,
//                "You have notification.", "working now?");


        ////////////////

        Intent x = new Intent(context,Main2Activity.class);
        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(x);

//        String beforeEnable = Settings.Secure.getString (context.getContentResolver(),
//                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

//        if (null == beforeEnable) {
//            String str = Settings.Secure.getString (context.getContentResolver(),
//                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//            if (null == str) {
//                str = "";
//            } else {
//                String[] list = str.split (",");
//                str = "";
//                int j = 0;
//                for (int i = 0; i < list.length; i++) {
//                    if (!list[i].equals (LocationManager.GPS_PROVIDER)) {
//                        if (j > 0) {
//                            str += ",";
//                        }
//                        str += list[i];
//                        j++;
//                    }
//                }
//                beforeEnable = str;
//            }
//        }
//        try {
//            Settings.Secure.putString (context.getContentResolver(),
//                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
//                    beforeEnable);
//        } catch(Exception e) {}



    }
}


