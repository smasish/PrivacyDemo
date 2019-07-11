package cacs.ul.privacydemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Custom_P_ListActivity extends AppCompatActivity {


    private ListView lv;
    private QueryDataSource datasource;
    TripAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__p__list);

        lv = (ListView) findViewById(R.id.list);

        datasource = new QueryDataSource(this);
        datasource.open();
        datasource.getAllComments();
        adapter = new TripAdapter(this);
        lv.setAdapter(adapter);

    }


    private class TripAdapter extends ArrayAdapter<TripData> {
        // StateListActivty context;
        private Context con;

        public TripAdapter(final Context c) {
            super(c, R.layout.triprow, datasource.getAllComments());// locallist

            con = c;
        }

        @Override
        public View getView(final int position, final View convertView,
                            final ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) con
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.triprow, null);
                //Log.d("**************. ", "--v--");
            }
            ///Log.d("**************. ", "out----" + position);



            //Log.d("******check********. ", "===" +datasource.getAllComments());
            //String place = datasource.getAllComments().get(position).getStartplace();
            //Log.d("******ss***datr*****. ", "===" +place);


            //Log.d("******im*******. ", "===" +im);
            String loc = datasource.getAllComments().get(position).getEndKM().toString();
            final TextView placeText = (TextView) v.findViewById(R.id.place_id);
            placeText.setText("Location\n "+loc);

            String s_place = datasource.getAllComments().get(position).getStartplace().toString();
            final TextView textView = (TextView) v.findViewById(R.id.offertext);
            textView.setText("From Date\n"+s_place);

            String s_km = datasource.getAllComments().get(position).getStartkm().toString();
            final TextView startkm = (TextView) v.findViewById(R.id.s_km_id);
            startkm.setText("To Date\n"+s_km);

            String s_time = datasource.getAllComments().get(position).getStartdate().toString();
            final TextView timeid = (TextView) v.findViewById(R.id.time_id);
            timeid.setText("From Time\n"+s_time);

            String end_place = datasource.getAllComments().get(position).getEndplace().toString();
            final TextView endplace = (TextView) v.findViewById(R.id.endplace_id);
            endplace.setText("To Time\n"+end_place);

//            String end_km = datasource.getAllComments().get(position).getEndKM().toString();
//            final TextView endkm = (TextView) v.findViewById(R.id.endkm_id);
//            endkm.setText(end_km);
//
//            String end_date = datasource.getAllComments().get(position).getEnddate().toString();
//            final TextView enddate = (TextView) v.findViewById(R.id.endtime_id);
//            enddate.setText(end_date);


            return v;

        }
    }
}
