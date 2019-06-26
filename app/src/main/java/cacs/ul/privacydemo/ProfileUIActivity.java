package cacs.ul.privacydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.PortUnreachableException;

public class ProfileUIActivity extends AppCompatActivity implements View.OnClickListener {


    private Button audioBtn,videoBtn,picBtn,contactBtn,locationBtn,visualBtn;

    private Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_items);


        con =this;

        locationBtn = (Button)findViewById(R.id.btnLoc);
        locationBtn.setOnClickListener(this);

        visualBtn = (Button)findViewById(R.id.btnVisual);
        visualBtn.setOnClickListener(this);

        picBtn = (Button)findViewById(R.id.btPic);
        picBtn.setOnClickListener(this);

        videoBtn = (Button)findViewById(R.id.btVideo);
        videoBtn.setOnClickListener(this);

        contactBtn = (Button)findViewById(R.id.btcontact);
        contactBtn.setOnClickListener(this);

        audioBtn = (Button)findViewById(R.id.btAudio);
        audioBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoc:
                // TODO Auto-generated method stub

                // Intent req = new Intent(MainActivity.this, RequestActivity.class);
                Intent req = new Intent(ProfileUIActivity.this, UserProfileViewActivity.class);
                req.putExtra("level","Location");
                startActivity(req);


                break;
            case R.id.btAudio:
                // TODO Auto-generated method stub
                Intent check = new Intent(ProfileUIActivity.this, UserProfileViewActivity.class);
                check.putExtra("level","Audio");
                startActivity(check);
                break;
            case R.id.btnVisual:
                // TODO Auto-generated method stub
                Intent visual = new Intent(ProfileUIActivity.this, UserProfileViewActivity.class);
                visual.putExtra("level","Visual");
                startActivity(visual);
                break;
            case R.id.btcontact:
                // TODO Auto-generated method stub
                Intent contact = new Intent(ProfileUIActivity.this, UserProfileViewActivity.class);
                contact.putExtra("level","Contact");
                startActivity(contact);
                break;
            case R.id.btPic:
                // TODO Auto-generated method stub
                Intent pic = new Intent(ProfileUIActivity.this, UserProfileViewActivity.class);
                pic.putExtra("level","Picture");
                startActivity(pic);
                break;
            case R.id.btVideo:
                // TODO Auto-generated method stub
                Intent video = new Intent(ProfileUIActivity.this, UserProfileViewActivity.class);
                video.putExtra("level","Video");
                startActivity(video);
                break;
           // case R.id.btcontact:
            default:
                // TODO Auto-generated method stub
                break;
        }
    }
}
