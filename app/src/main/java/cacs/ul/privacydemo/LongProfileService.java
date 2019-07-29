package cacs.ul.privacydemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LongProfileService extends Service {
    public static final long INTERVAL=10000;//variable to execute services every 10 second
    private Handler mHandler=new Handler(); // run on a
    private static final int REQUEST_ENABLE_GPS = 516;
    private QueryDataSource datasource;
    private Context con;
    // nother Thread to avoid crash
    private Timer mTimer=null; // timer handling
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("unsupported Operation");
    }
    @Override
    public void onCreate() {
        // cancel if service is  already existed
        if(mTimer!=null)
            mTimer.cancel();
        else
            mTimer=new Timer(); // recreate new timer
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(),0,INTERVAL);// schedule task
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this, "In Destroy", Toast.LENGTH_SHORT).show();//display toast when method called
        mTimer.cancel();//cancel the timer
    }
    //inner class of TimeDisplayTimerTask
    private class TimeDisplayTimerTask extends TimerTask  {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // display toast at every 10 second
                 //   Toast.makeText(getApplicationContext(), "Notify", Toast.LENGTH_SHORT).show();

                    con = getApplicationContext();
                    datasource = new QueryDataSource(con);
                    datasource.open();
                    datasource.getAllComments();

                    String sr = datasource.getAllComments().get(0).getStartkm().toString();
                   // sr = sr.replaceAll(":","");
                  //  int hr = Integer.parseInt(sr.trim());

                    String sr3 = datasource.getAllComments().get(0).getStartplace().toString();

                //    String sr2 = datasource.getAllComments().get(0).getEndplace().toString();
                  //  sr2 = sr2.replaceAll(":","");
                  //  int min = Integer.parseInt(sr2.trim());

                    SimpleDateFormat mdformat = new SimpleDateFormat("MM/dd/yyyy");
try {
    Date d1 = mdformat.parse(sr3);
    Date d2 = mdformat.parse(sr);

    Date date = new Date();
    String s = mdformat.format(date);

    Date d3 = mdformat.parse(s);
    System.out.println("Date1  Date2"+d1+"==="+d2);

    if (d3.compareTo(d2) > 0) {

        // When Date d1 > Date d2
        System.out.println("Date1 is after Date2");
     //   Toast.makeText(getApplicationContext(), "Date1 is after Date2"+sr, Toast.LENGTH_SHORT).show();
    }
    else if ((d3.compareTo(d1) >= 0) && (d3.compareTo(d2) <= 0) ) {
        System.out.println(" perfect--");

        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        String t1 = datasource.getAllComments().get(0).getStartdate().toString();

        String t2 = datasource.getAllComments().get(0).getEndplace().toString();
        Date currentDate = new Date();

        Date tt1 = timeformat.parse(t1);
        Date tt2 = timeformat.parse(t2);

        String st1 = timeformat.format(currentDate).toString();

        Date tt3 = timeformat.parse(st1);

        if(tt1.compareTo(tt2)==0){
            System.out.println("----perfect time- all--");


            Intent x = new Intent(con,Main2Activity.class);
            con.startActivity(x);

//            Intent intent = new Intent(this,LongProfileService.class);
//            stopService(intent);
  //          Intent intent = new Intent(con, LongProfileService.class);
   //         stopService(intent);
        }
    }

    else if (d3.compareTo(d1) < 0) {

        // When Date d1 < Date d2
        System.out.println("Date1 is before Date2");
    //    Toast.makeText(getApplicationContext(), "Date1 is before Date2"+sr, Toast.LENGTH_SHORT).show();
    }
    else if (d3.compareTo(d1) > 0) {

        // When Date d1 < Date d2
        System.out.println("d3>d1");
        //    Toast.makeText(getApplicationContext(), "Date1 is before Date2"+sr, Toast.LENGTH_SHORT).show();
    }







}catch (Exception e){

}

                }
            });
        }
    }
}
