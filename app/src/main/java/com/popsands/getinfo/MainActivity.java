package com.popsands.getinfo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.popsands.getinfo.R;

import java.io.File;

public class MainActivity extends Activity {

    private Button enBtn,deBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enBtn = (Button)findViewById(R.id.btn);
        deBtn = (Button)findViewById(R.id.btn2);

        enBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptionUtils.encrypt(new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/tte.jpg"), new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/enIMG_20200105_204717.talkboj"), "hagsd76ds5f7as5ads765a76576");
            }
        });

        deBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptionUtils.decrypt(new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/file-cmstalkbo.enc"), new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/file.zip"),"hagsd76ds5f7as5ads765a76576");
//                encryptionUtils.decrypt(new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/cnphonics.enc"), new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/cnphonics.zip"),"hagsd76ds5f7as5ads765a76576");
//                encryptionUtils.decrypt(new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/phonics.enc"), new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/phonics.zip"),"hagsd76ds5f7as5ads765a76576");
            }
        });
    }
}
