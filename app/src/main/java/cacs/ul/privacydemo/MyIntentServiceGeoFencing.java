package cacs.ul.privacydemo;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;


public class MyIntentServiceGeoFencing extends IntentService {
    public MyIntentServiceGeoFencing() {
        super("MyIntentServiceGeoFencing");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String message = "";
            GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
            if (geofencingEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_ENTER) {
                message = getString(R.string.txt_enter_geofence);
                Log.e("GeoFenceService", "Entering Geofence");
            } else if (geofencingEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_EXIT) {
                message = getString(R.string.txt_exiting_geofence);
                Log.e("GeoFenceService", "Exiting Geofence");
            }
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                            .setContentTitle(getString(R.string.txt_notification_title))
                            .setContentText(message);

            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());


            /*

            Changing Phone Profile on Entering and Exiting the Geofence
             */
//            ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(800);
//            if(message.equals("Entering Appstudioz")) {
//                ((AudioManager) getSystemService(AUDIO_SERVICE)).setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
//            }
//            else
//            {
//                ((AudioManager) getSystemService(AUDIO_SERVICE)).setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//            }
        }
    }


}
