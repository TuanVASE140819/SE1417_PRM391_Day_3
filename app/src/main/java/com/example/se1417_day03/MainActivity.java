package com.example.se1417_day03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button btnProgress;
    ProgressDialog myPro_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProgress=findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPro_bar = new ProgressDialog(MainActivity.this);
                myPro_bar.setMessage("Loading...");
                myPro_bar.setTitle("Please wait...");
                myPro_bar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                myPro_bar.setSecondaryProgress(0);
                myPro_bar.setMax(20);
                myPro_bar.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                         try{
                             while (myPro_bar.getProgress() < myPro_bar.getMax()){
                                 Thread.sleep(1000);
                                 handler.sendMessage(handler.obtainMessage());
                             }
                             if (myPro_bar.getProgress() >= myPro_bar.getMax()){
                                 myPro_bar.dismiss();
                             }
                         }catch (Exception e){
                             e.printStackTrace();
                         }
                    }
                }).start();
            }
            Handler handler =new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    myPro_bar.incrementProgressBy(2);
                }
            };
        });
    }

    public void clickToCreate(View view) {
        Intent Intent = new Intent(this,CreateSMSActivity.class);
    }
}