package com.example.menuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.help_menu:
                Toast.makeText(this, "HELP", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings_menu:
                Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void launchSMS(View view) {
        Intent sms = new Intent(Intent.ACTION_SENDTO);
        sms.setData(Uri.parse("smsto:" + Uri.encode("8001234567")));
        sms.putExtra("sms_body", "LETS ALL LOVE LAIN ");
        startActivity(sms);

    }

    public void launchPhone(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL);
        phone.setData(Uri.parse("tel:8001234567"));
        startActivity(phone);

    }

    public void launchWeb(View view) {
        String url = "http://google.com";
        Uri webpage = Uri.parse(url);
        Intent web = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(web);

    }

    public void launchMap(View view) {
        Uri birth = Uri.parse("geo:40.7901, 73.9530");
        Intent map = new Intent(Intent.ACTION_VIEW, birth);
        startActivity(map);
    }

    public void launchShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "LETS ALL LOVE LAIN");
        startActivity(Intent.createChooser(intent, "LETS ALL LOVE LAIN"));
    }


    public void launchNewActivity(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    public void launchHelp(MenuItem item) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }


}