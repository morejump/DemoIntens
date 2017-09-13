package com.example.naxam.demointens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String numberPhone;
    EditText editTextPhone;
    private static  String[] tenquyen = {Manifest.permission.CALL_PHONE};
    int quyengoidien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quyengoidien = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(quyengoidien != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, tenquyen, 1000);
        }

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
    }

    public void btnCall_Click(View v) {
        numberPhone = editTextPhone.getText().toString();
        Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numberPhone));
        if(quyengoidien == PackageManager.PERMISSION_GRANTED)
        {
            this.startActivity(intentPhone);

        }

    }

    public void btnSms_Click(View v)
    {
        numberPhone = editTextPhone.getText().toString();
        Intent intentSms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + numberPhone));
        intentSms.putExtra("sms_body", "Hello Thầy Hậu, em là Sơn");
        this.startActivity(intentSms);
    }

    public void btnEmail_Click(View v)
    {
        Intent intentEmail = new Intent(Intent.ACTION_SEND);
        intentEmail.setType("text/html");
        intentEmail.putExtra(Intent.EXTRA_EMAIL, "nhu.son@outlook.com.vn");
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Demo open email");
        this.startActivity(Intent.createChooser(intentEmail, "Send mail client :"));
    }

    public void btnNewspaper_Click(View v)
    {
        Intent intentNewspaper = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dantri.com.vn"));
        this.startActivity(intentNewspaper);
    }

    public void btnLanguage_Click(View v)
    {
        startActivityForResult(new Intent(Settings.ACTION_LOCALE_SETTINGS), 0);
    }

    public void btnMap_Click(View v)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void btnPgoto_Click(View v)
    {
        startActivityForResult(new Intent(Settings.ACTION_LOCALE_SETTINGS), 0);
    }
}
