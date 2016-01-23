package de.derandroidpro.permissions_marshmallow_tutorial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button btn;

    final int REQ_CODE_EXTERNAL_STORAGE_PERMISSION = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(1000);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    createFolder();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,new  String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_CODE_EXTERNAL_STORAGE_PERMISSION);
                }
            }
        });
    }

    private void createFolder(){
        File ordner = new File(Environment.getExternalStorageDirectory(), "TestOrdner");
        ordner.mkdirs();
        Toast.makeText(getApplicationContext(), "Ordner erstellt", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQ_CODE_EXTERNAL_STORAGE_PERMISSION && grantResults.length >0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            createFolder();
        }
    }
}
