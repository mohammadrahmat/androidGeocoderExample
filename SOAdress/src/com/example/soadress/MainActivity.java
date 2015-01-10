package com.example.soadress;

import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	TextView txt;
	double latitude;
	double longitude;
	EditText lat, lon;
	Button btnShow;
	List<Address> addresses;
	Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.address);
        lat = (EditText)findViewById(R.id.txtLat);
        lon = (EditText)findViewById(R.id.txtLong);
        btnShow = (Button)findViewById(R.id.btnShow);
        geocoder = new Geocoder(this, Locale.getDefault());
        btnShow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
			        addresses = geocoder.getFromLocation(Double.valueOf(lat.getText().toString()), Double.valueOf(lon.getText().toString()), 1);
			        String fullAdd="";
			        if(addresses.get(0)!=null){
			        	if(addresses.get(0).getAddressLine(0)!=null)
			        		fullAdd+=addresses.get(0).getAddressLine(0);//area name
			        	if(addresses.get(0).getAddressLine(1)!=null)
			        		fullAdd+=" "+addresses.get(0).getAddressLine(1);//city name
			        	if(addresses.get(0).getAddressLine(2)!=null)
			        		fullAdd+=" "+addresses.get(0).getAddressLine(2);//postal code and country
			        }
			        txt.setText(fullAdd);
		        }
		        catch(Exception err){
		        	txt.setText("Wrong Lat/Long Combination");
		        }
				
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
}
