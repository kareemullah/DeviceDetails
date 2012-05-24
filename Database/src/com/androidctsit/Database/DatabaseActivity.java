package com.androidctsit.Database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class DatabaseActivity extends Activity {
    Button login=null;
    Button signup=null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        login = (Button) findViewById(R.id.login_butt);
        signup = (Button) findViewById(R.id.signup_butt);
        
        login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(DatabaseActivity.this , Login_Page.class);
				startActivity(intent1);
			}
		});

        signup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(DatabaseActivity.this , Signup_Page.class);
				startActivity(intent2);
			}
		});
    }
}