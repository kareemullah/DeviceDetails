package com.androidctsit.Database;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Login_Page extends Activity {
	EditText edit_name, edit_password;
	Button login;
	String name, password;
	CustomAlert alert = new CustomAlert();
	Cursor c;
	private Activity loginActivity;
	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_page);
		edit_name = (EditText) findViewById(R.id.editname);
		edit_password = (EditText) findViewById(R.id.editpassword);
		login = (Button) findViewById(R.id.button1);
		loginActivity = this;
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onLoginClicked();
			}
		});
		
	}
	
	public void onLoginClicked() {
		String names = null;
		String passwords = null;
		String phone = null;
		String add = null;
		String email = null;
		int i=0;
		String id;
		name = edit_name.getEditableText().toString().trim();
		password = edit_password.getEditableText().toString().trim();
		
		if(name.equalsIgnoreCase("") || password.equalsIgnoreCase(""))
			alert.ErrorAlert("Alert", "Enter Name & Password", Login_Page.this);
		else
		{
			DatabaseUtil dbUtil = new DatabaseUtil(this);
			dbUtil.open();
			c = dbUtil.fetchLoginDetails();
			if(c!=null) {
				c.moveToFirst();
				do {
					names = c.getString(0);
					passwords = c.getString(1);
					id =  c.getString(2);
					phone = c.getString(3);
					add = c.getString(4);
					email = c.getString(5);
					if (names.equals(name) && (passwords.equals(password))){
						alert.claimLoginAlert("", "Login Successful", Login_Page.this, loginActivity, names, id, phone, add, email);
						i=1;
					}
				} while (c.moveToNext());
				if(i==0) {
					alert.ErrorAlert("Alert", "Invalid User/Password", Login_Page.this);
				}
			}
			else 
			{
				Log.i("TAG" , "No user Exists...");
				alert.ErrorAlert("Alert", "Invalid User/Password", Login_Page.this);
			}
			c.close();
			dbUtil.close();
		}
	}

}
