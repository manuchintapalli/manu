package com.example.manu.project0;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public Button spotifyButton, scoresButton, libraryButton, buildButton, xyzButton, capstoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spotifyButton = (Button)findViewById(R.id.spotifyButton);
        scoresButton = (Button)findViewById(R.id.scoresButton);
        libraryButton = (Button)findViewById(R.id.libraryButton);
        buildButton = (Button)findViewById(R.id.buildButton);
        xyzButton = (Button)findViewById(R.id.xyzButton);
        capstoneButton = (Button)findViewById(R.id.capstoneButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

     public void sendMessage(View view) {
         Context context = getApplicationContext();
         int duration = Toast.LENGTH_SHORT;
         Toast toast;
        switch (view.getId())
        {
            case R.id.spotifyButton:
                CharSequence text = "This button will launch my spotify streamer app";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
            case R.id.scoresButton:
                CharSequence text1 = "This button will launch my scores app";
                toast = Toast.makeText(context, text1, duration);
                toast.show();
                break;
            case R.id.libraryButton:
                CharSequence text2 = "This button will launch my library app";
                toast = Toast.makeText(context, text2, duration);
                toast.show();
                break;
            case R.id.buildButton:
                CharSequence text3 = "This button will launch my build app";
                toast = Toast.makeText(context, text3, duration);
                toast.show();
                break;
            case R.id.xyzButton:
                CharSequence text4 = "This button will launch my xyz app";
                toast = Toast.makeText(context, text4, duration);
                toast.show();
            case R.id.capstoneButton:
                CharSequence text5 = "This button will launch my cap stone app";
                toast = Toast.makeText(context, text5, duration);
                toast.show();
                break;

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
