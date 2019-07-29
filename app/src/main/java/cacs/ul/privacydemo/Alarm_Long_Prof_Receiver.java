package cacs.ul.privacydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Alarm_Long_Prof_Receiver extends BroadcastReceiver {

    String TAG = "Alarm_Long_Prof_Receiver";
    private static final int REQUEST_ENABLE_GPS = 516;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);

//                String sr = datasource.getAllComments().get(0).getStartdate().toString();
//                //  sr = sr.replaceAll(":","");
//                //   int hr = Integer.parseInt(sr.trim());
//                String[] parts = sr.split(":");
//                String part1 = parts[0]; // 004
//                String part2 = parts[1];
//
//                String sr2 = datasource.getAllComments().get(0).getStartdate().toString();
//                //   sr2 = sr2.replaceAll(":","");
//                //   int min = Integer.parseInt(sr2.trim());
//
//
//
//                //   TimeScheduler.setReminder(CustomTimeSettings.this, AlarmReceiver.class, hr, min);
//
//                //     startService(new Intent(this,LongProfileService.class));
//                int h = Integer.parseInt(part1);
//                int m = Integer.parseInt(part2);

                TimeScheduler_Long.setReminder(context, Alarm_Long_Prof_Receiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
//        TimeScheduler_Long.showNotification(context, TempSettingsActivity.class,
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


