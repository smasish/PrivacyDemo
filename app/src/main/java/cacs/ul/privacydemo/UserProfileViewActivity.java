package cacs.ul.privacydemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileViewActivity extends AppCompatActivity {

    String[] user_profile = {
            "Deny",
            "Allow",
            "Custom"
    };
    SharedPreferences.Editor user_profile_editor;

    private TextView title_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_view);

        title_view = (TextView)findViewById(R.id.title_id);

        /**************Shared preference Declaration start******************/
        user_profile_editor = getSharedPreferences("USER_PROFILE_PREFS_NAME", MODE_PRIVATE).edit(); // set

        SharedPreferences get_user_profile = getSharedPreferences("USER_PROFILE_PREFS_NAME", MODE_PRIVATE); // get

        String name = get_user_profile.getString("Profile Name:", "level");//"No name defined" is the default value.
        int idName = get_user_profile.getInt("Radio Button Id:", 0); //0 is the default value.

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        String get_title = intent.getStringExtra("level");
        title_view.setText(""+get_title);

        /**************Shared preference Declaration end******************/



        /******new CustomAdapter with passing Shared Preference Editor*********/

        final UserProfileViewAdapter userProfileViewAdapter = new UserProfileViewAdapter(this, user_profile,user_profile_editor,idName);

        ListView userProfileListView = (ListView) findViewById(R.id.user_profile_list_view);
        final RadioButton radioButton = (RadioButton) findViewById(R.id.user_profile_choice);


        userProfileListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        userProfileListView.setAdapter(userProfileViewAdapter);

//        userProfileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Object item = userProfileViewAdapter.getItem(position);
//                String str = (String) item;
//
//                Intent intent = new Intent(getApplicationContext(), ApplicationDetailsActivity.class);
//                intent.putExtra("ApplicationName", str);
//                //based on item add info to intent
//                startActivity(intent);
//
//                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
//            }
//        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void save(View v){
        Intent check = new Intent(UserProfileViewActivity.this, CustomTimeSettings.class);
//        check.putExtra("level","Visual");
        startActivity(check);
    }

}
