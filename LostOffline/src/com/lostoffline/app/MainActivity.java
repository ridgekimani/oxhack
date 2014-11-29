package com.lostoffline.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;



public class MainActivity extends ActionBarActivity {
	
	SendSMS mSender = new SendSMS();
	GPSTracker gpstracker = new GPSTracker(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        Button mButton = (Button)findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
            		EditText mEdit   = (EditText)findViewById(R.id.editText1);
            		String destination = mEdit.getText().toString();
            		sendIt(view, destination);
              	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

    
    
    private void sendIt(View arg0, String destination) {
    	double latitude = gpstracker.getLatitude();
    	double longitude = gpstracker.getLongitude();
    	System.out.println("sending "+destination+" "+latitude+" "+longitude);
    	boolean success = mSender.sendSMSMessage("insertnumberhere",
    		"directions "+latitude+" "+longitude+" "+destination);
    	Toast.makeText(this, "Message sent " + (
    		success ? "successfully" : "unsuccessfully"), 
    		Toast.LENGTH_SHORT).show();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
