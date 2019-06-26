package cacs.ul.privacydemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class UserProfileViewAdapter extends ArrayAdapter{
    String[] user_profile_text;
    int selectedPosition;
    SharedPreferences.Editor user_profile_editor;


    public UserProfileViewAdapter(Context context, String[] text, SharedPreferences.Editor preference, int radioButtonSelect) {
        super(context, R.layout.user_profile_list_item, text);
        this.user_profile_text = text;
        this.user_profile_editor = preference; // preference to get the Radio Button Value
        this.selectedPosition = radioButtonSelect;
        this.getContext();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.user_profile_list_item, null, true);
        TextView textView1 = (TextView) row.findViewById(R.id.user_profile_text_view);
        RadioButton radioButton = (RadioButton) row.findViewById(R.id.user_profile_choice);

        radioButton.setText(user_profile_text[position]);

        if(position==selectedPosition) radioButton.setChecked(true);
        else radioButton.setChecked(false);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedPosition != position){
                    selectedPosition = position;

                    UserProfileViewAdapter.this.notifyDataSetChanged();
                    String logg = Integer.toString(selectedPosition);
                    Log.d("TanziLog", logg);

                    Object item = getItem(position);
                    String str = (String) item; // use this value for settings  or "position" values(0,1,2)
                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();

                    /***************shared Preference value storing******************/
                    /**********Code Here Profile Settings**********/
                    user_profile_editor.putString("Profile Name:",str);
                    user_profile_editor.putInt("Radio Button Id:",position);
                    user_profile_editor.commit();

                }
            }
        });



        return row;
    }
}
