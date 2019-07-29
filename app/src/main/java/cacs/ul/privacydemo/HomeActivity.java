package cacs.ul.privacydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnSystemApps, btn_custom_perm,btnPermission,btnTempPro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_options);

        init_views();
        init_variable();
        init_functions();
        init_listeners();



    }

    private void init_listeners() {

    }

    private void init_functions() {

    }

    private void init_variable() {
        btnSystemApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,InstalledAppsList.class);
                startActivity(intent);
            }
        });

        btn_custom_perm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,UserProfileViewActivity.class); //  ProfileUIActivity
                startActivity(intent);
            }
        });

        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,PermissionScannerActivity.class);
                startActivity(intent);
            }
        });

        btnTempPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TempSettingsActivity.class);
                startActivity(intent);
            }
        });

//        btnUserApps.setOnClickListener(
//                new View.OnClickListener()
//                { @Override
//                 public void onClick(View v){
//                    Intent intent = new Intent(HomeActivity.this,InstalledAppsList.class);
//                    startActivity(intent);
//                }
//               }
//        );
    }

    private void init_views() {
        btn_custom_perm = (Button)findViewById(R.id.prof_btn);
        btnSystemApps = (Button)findViewById(R.id.install_btn);
        btnPermission = (Button)findViewById(R.id.perm_btn);

        btnTempPro= (Button)findViewById(R.id.prof_btn_temp);
    }
}
