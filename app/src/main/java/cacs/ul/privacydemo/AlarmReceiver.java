package cacs.ul.privacydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";
    private QueryDataSource datasource;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");

                datasource = new QueryDataSource(context);
                datasource.open();
                datasource.getAllComments();
                String sr = datasource.getAllComments().get(0).getStartdate().toString();
                sr = sr.replaceAll(":","");
                int hr = Integer.parseInt(sr.trim());

                String sr2 = datasource.getAllComments().get(0).getStartdate().toString();
                sr2 = sr2.replaceAll(":","");
                int min = Integer.parseInt(sr2.trim());
             //   LocalData localData = new LocalData(context);
                TimeScheduler.setReminder(context, AlarmReceiver.class, hr/100, min%100);
                return;
            }
        }

        Log.d(TAG, "onReceive: ");
      //  TimeScheduler.TurnOffGPS();


        //Trigger the notification
//        TimeScheduler.showNotification(context, MainActivity.class,
//                "You have 5 unwatched videos", "Watch them now?");

    }
}


