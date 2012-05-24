package com.androidctsit.Database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Signup_Page extends Activity {
	
	EditText edit_name, edit_password, edit_address, edit_phone, edit_email;
	String name, password, address, phone, email;
	Button sign_up;
	CustomAlert alert = new CustomAlert();
	private Activity signupActivity;
	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signup_page);
		signupActivity = this;
		sign_up = (Button) findViewById(R.id.button_signup);
		
		edit_name = (EditText) findViewById(R.id.signup_name_edit);
		edit_password = (EditText) findViewById(R.id.signup_password_edit);
		edit_address = (EditText) findViewById(R.id.signup_address_edit);
		edit_phone = (EditText) findViewById(R.id.signup_phone_edit);
		edit_email = (EditText) findViewById(R.id.signup_email_edit);
		
		sign_up.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getSignupValues();
				
			}
		});
		
	}
	
	public void getSignupValues() {
		Long id;
		name = edit_name.getEditableText().toString().trim();
		password = edit_password.getEditableText().toString().trim();
		address = edit_address.getEditableText().toString().trim();
		phone = edit_phone.getEditableText().toString().trim();
		email = edit_email.getEditableText().toString().trim();
		
		if(name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || address.equalsIgnoreCase("") || phone.equalsIgnoreCase("") ||email.equalsIgnoreCase(""))
			alert.ErrorAlert("Alert", "Enter All Fields", Signup_Page.this);
		else 
		{
			DatabaseUtil dbUtil = new DatabaseUtil(this);
			dbUtil.open();
			id = dbUtil.createUser(name, password, address, phone, email);
			dbUtil.close();
			edit_name.setText("");
			edit_password.setText("");
			edit_address.setText("");
			edit_phone.setText("");
			edit_email.setText("");
			alert.claimLoginAlert("", "Registration successful", Signup_Page.this, signupActivity, name, id.toString(), phone, address, email);
		}
		
	}

}
