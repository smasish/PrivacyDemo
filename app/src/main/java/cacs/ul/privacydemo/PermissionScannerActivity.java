package cacs.ul.privacydemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PermissionScannerActivity extends AppCompatActivity {

    private Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_scanner);

        con = this;

        Toast.makeText(con,"Working on it",Toast.LENGTH_LONG).show();
    }
}
