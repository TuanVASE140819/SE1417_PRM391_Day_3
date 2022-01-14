package com.example.se1417_day03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateSMSActivity extends AppCompatActivity {
private EditText edtPhone,edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_smsactivity);
        edtPhone =findViewById(R.id.editPhone);
        edtContent=findViewById(R.id.edtContent);

    }

    public void clickToSend(View view) {
        String phone = edtPhone.getText().toString();
        String content = edtContent.getText().toString();
        SmsManager smsManager =SmsManager.getDefault();
        Intent intent = new Intent("TUANVA_SEND_SMS");
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,0,intent,0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result=getResultCode();
                String mes ="Send OK";
                        if(result != Activity.RESULT_OK){
                            mes="Send Failed";
                        }
                Toast.makeText(CreateSMSActivity.this,mes,Toast.LENGTH_LONG).show();
            }
        },new IntentFilter("TUANVA_SEND_SMS"));
            smsManager.sendTextMessage(phone, null,content, pendingIntent,null);
          finish();
    }
}