package com.androidctsit.Database;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class User_Page extends Activity {
	TextView welcome_text, phone_no, address1, email1;
	Button addDetails , viewDetails;
	CustomAlert alert = new CustomAlert();
	protected Typeface text_type;
	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_page);
		text_type = Typeface.createFromAsset(getAssets(), "fonts/BAHAMAHN.TTF");
		welcome_text = (TextView) findViewById(R.id.welcome_text);
		phone_no = (TextView) findViewById(R.id.phone_number);
		address1 = (TextView) findViewById(R.id.address);
		email1 = (TextView) findViewById(R.id.email);
		
		Bundle extras = getIntent().getExtras();
		String user_name = extras.getString("name");
		final String id = extras.getString("_id");
		final String phone = extras.getString("phone");
		final String address = extras.getString("add");
		final String email = extras.getString("email");
		
		welcome_text.setTypeface(text_type);
		welcome_text.setText("Welcome "+user_name+",");
		phone_no.setText("Phone Number : " + phone);
		address1.setText("Address  : " + address);
		email1.setText("E Mail Id : " +email);
		
		addDetails = (Button) findViewById(R.id.add_details);
		viewDetails = (Button) findViewById(R.id.view_details);
		
		addDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(User_Page.this, AddUserDetails.class);
				intent.putExtra("_id", id);
				startActivity(intent);
			}
		});
		
		viewDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(User_Page.this, ViewUserDetails.class);
				intent.putExtra("_id", id);
				startActivity(intent);
			}
		});
		
	}
	
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		Intent intent = new Intent(User_Page.this, DatabaseActivity.class);
//		startActivity(intent);
//		finish();
//	}

}
