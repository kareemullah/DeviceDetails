package com.androidctsit.Database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddUserDetails extends Activity {
	Button add;
	EditText edit_car, edit_phone, edit_bike;
	String car, phone, bike, id;
	CustomAlert alert = new CustomAlert();
	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_user_details);
		add = (Button) findViewById(R.id.button_add_userdetails);
		
		edit_car = (EditText) findViewById(R.id.edit_user_car);
		edit_phone = (EditText) findViewById(R.id.edit_user_phone);
		edit_bike = (EditText) findViewById(R.id.edit_user_bike);
		Bundle extras = getIntent().getExtras();
		id = extras.getString("_id");
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addButtonClicked(id);
			}
		});
	}
	
	public void addButtonClicked(String id) {
		car = edit_car.getEditableText().toString().trim();
		phone = edit_phone.getEditableText().toString().trim();
		bike = edit_bike.getEditableText().toString().trim();
		
		if(car.equalsIgnoreCase("")|| phone.equalsIgnoreCase("") || bike.equalsIgnoreCase("")) 
			alert.ErrorAlert("Alert", "Enter All Fields", AddUserDetails.this);
		else
		{
			DatabaseUtil dbUtil = new DatabaseUtil(AddUserDetails.this);
			dbUtil.open();
			dbUtil.createView(car, phone, bike, id);
			dbUtil.close();
			edit_phone.setText("");
			edit_bike.setText("");
			edit_car.setText("");
			AlertDialog dialog=new AlertDialog.Builder(AddUserDetails.this).create();
			dialog.setTitle("Done");
			dialog.setMessage("User Details Added Successfully");
			dialog.setButton("OK",
			new DialogInterface.OnClickListener()	{
			public void onClick(DialogInterface dialog, int whichButton)	{
			            dialog.dismiss();
			            finish();
			}
			});
			dialog.show();
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
}
