package sinfreu.marie.threesixty;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

	ConnectivityManager mConnectivityManager;

	//Views
	TextView tvNetwork, tvStatus;
	Button btSendEvent;
	EditText etEventsName;

	//Data
	String mConnectionStatus;
	String mEventsName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mConnectivityManager=
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		tvNetwork = (TextView) findViewById(R.id.tvNetwork);
		tvStatus = (TextView) findViewById(R.id.tvStatus);
		etEventsName = (EditText) findViewById(R.id.etEventsName);
		btSendEvent = (Button) findViewById(R.id.sendEvent);



		btSendEvent.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {


				tvStatus.setText("");
				tvStatus.setTextColor(Color.BLACK);

				//GET INFO TO SEND IN EVENTS
				//Get Connection status and display it in TextView
				if(!getNetworkStatus().isEmpty()) {
					mConnectionStatus = getNetworkStatus();
				}

				String name = etEventsName.getText().toString().trim();

				if(!name.isEmpty()) {
					tvStatus.setText(name);
					mEventsName = name;

				} else {
					tvStatus.setText("You must fill up this field è_é");
					tvStatus.setTextColor(Color.RED);
				}





//				sendEvent(mConnectionStatus, mEventsName, mKeyValues);
			}
		});

	}


	public String getNetworkStatus() {
		String status = "Offline :(";

		// NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();

		if(mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting()) {
			status = "Mobile";
			tvNetwork.setText("Online "+status);

		} else if(mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting()) {
			status = "Wifi";
			tvNetwork.setText("Online: "+status);

		}

		return status;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
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
