package com.androidctsit.Database;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ViewUserDetails extends Activity {
	ListView lv;
	ListviewAdapter adapter;
	Cursor c = null;
	Activity activity=null;
	CustomAlert alert = new CustomAlert();
	DatabaseUtil dbUtil = new DatabaseUtil(this);
	ArrayList< Integer> arraylistID = new ArrayList<Integer>();
	ArrayList< String> arraylistCar = new ArrayList<String>();
	ArrayList< String> arraylistPhone = new ArrayList<String>();
	ArrayList< String> arraylistBike = new ArrayList<String>();
	int i=0;
	Dialog dialog=null;
	String id;
	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_user_details);
		activity = this;
		Bundle extras = getIntent().getExtras();
		id = extras.getString("_id");
		setarrayList(id);
		lv = (ListView) findViewById(R.id.listView1);
		adapter = new ListviewAdapter(this);
		lv.setAdapter(adapter);
	}
	
	public void setarrayList(String id) {
		dbUtil.open();
		arraylistCar.clear();
		arraylistBike.clear();
		arraylistPhone.clear();
		arraylistID.clear();
		i=0;
		c = dbUtil.getDetailsByID(id);
		if(c.getCount()!=0) {
			c.moveToFirst();
			do {
				arraylistID.add(c.getInt(0));
				arraylistCar.add(c.getString(1));
				arraylistPhone.add(c.getString(2));
				arraylistBike.add(c.getString(3));
				Log.i("ViewUserDetails" , arraylistCar.get(i) + arraylistPhone.get(i) + arraylistBike.get(i));
				i++;
			} while (c.moveToNext());
			Log.i("ViewUserDetails", "Length : "+i);
		}
		else 
		{
			Log.i("TAG" , "No user Exists...");
			AlertDialog dialog=new AlertDialog.Builder(ViewUserDetails.this).create();
			dialog.setTitle("Alert");
			dialog.setMessage("No Details Avilable for this User");
			dialog.setButton("OK",
			new DialogInterface.OnClickListener()	{
				public void onClick(DialogInterface dialog, int whichButton)	{
			            dialog.dismiss();
			            finish();
				}
			});
			dialog.show();
		}
		c.close();
		Log.i("TAG", "getDetailsByID ");
		dbUtil.close();
	}
	
	public class ListviewAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		public Context m_Context = null;

		public ListviewAdapter(Context context) {
			m_Context = context;
			mInflater = LayoutInflater.from(m_Context);
		}
		
		public int getCount() {
			try
			{
			return c.getCount();
			}catch(Exception e)
			{
				e.printStackTrace();
				return 0;
			}
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.listview_elements, null);
				//convertView.setClickable(true);
				
				holder = new ViewHolder();
				
				holder.text1 = (TextView) convertView.findViewById(R.id.listview_text1);
				holder.text2 = (TextView) convertView.findViewById(R.id.listview_text2);
				holder.text3 = (TextView) convertView.findViewById(R.id.listview_text3);
				holder.modify = (Button) convertView.findViewById(R.id.modify_button);
				holder.delete = (Button) convertView.findViewById(R.id.delete_button);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
				}

		if(holder.text1!=null)
		{
			holder.text1.setText("CAR Name  :    " + arraylistCar.get(position));
			holder.text1.setTypeface(null,Typeface.BOLD);
		}
		if(holder.text2!=null)
		{
			holder.text2.setText("Phone NO   :    " + arraylistPhone.get(position) );
			holder.text2.setTypeface(null,Typeface.BOLD);
		}
		if(holder.text3!=null)
		{
			holder.text3.setText("Bike Name  :   " + arraylistBike.get(position));
			holder.text3.setTypeface(null,Typeface.BOLD);
		}
		
		holder.delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dbUtil.open();
				dbUtil.deleteViewByID(arraylistID.get(position));
				dbUtil.close();
				setarrayList(id);
				if(!arraylistCar.isEmpty())
					alert.ErrorAlert("", " Details Deleted ", ViewUserDetails.this);
				notifyDataSetChanged();
			}
		});
		
		holder.modify.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = new Dialog(ViewUserDetails.this,
						android.R.style.Theme_Translucent_NoTitleBar);
				Window window = dialog.getWindow();
				window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
						WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
				window.setLayout(ViewGroup.LayoutParams.FILL_PARENT,
						ViewGroup.LayoutParams.FILL_PARENT);
				dialog.setCancelable(true);
				dialog.setCanceledOnTouchOutside(true);
				dialog.setContentView(R.layout.custom_dialog);

				final EditText edit_car = (EditText) dialog.findViewById(R.id.window_edit_user_car);
				final EditText edit_phone = (EditText) dialog.findViewById(R.id.window_edit_user_phone);
				final EditText edit_bike = (EditText) dialog.findViewById(R.id.window_edit_user_bike);
				
				Button ok = (Button) dialog.findViewById(R.id.alert_ok_button);
				Button cancel = (Button) dialog.findViewById(R.id.alert_cancel_button);
				
				ok.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						dbUtil.open();
						dbUtil.update_byID(arraylistID.get(position), edit_car.getEditableText().toString().trim(), edit_phone.getEditableText().toString().trim(), edit_bike.getEditableText().toString().trim(), id);
						dbUtil.close();
						dialog.cancel();
						Log.i("TAG"," entered car text : " + edit_car.getEditableText().toString().trim() );
						alert.ErrorAlert("", " Details Updated ", ViewUserDetails.this);
						setarrayList(id);
						notifyDataSetChanged();
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.cancel();
					}
				});
				dialog.show();
			}
		});
		
			return convertView;
		}

		 class ViewHolder {
			public TextView text1, text2, text3;
			Button modify, delete;
		}
		
	}
	
	

}
