package com.androidctsit.Database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class CustomAlert {

	public static void showAlert(String title,String message,final Context context)
	{
		AlertDialog dialog=new AlertDialog.Builder(context).create();
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setButton("OK",
		new DialogInterface.OnClickListener()
		{
		public void onClick(DialogInterface dialog, int whichButton)
		{
		            dialog.dismiss();
//		          DunkinDonutsSplash da=new DunkinDonutsSplash();
//		          da.finish();
		}
		});

		dialog.show();
	}
	public void ErrorAlert(String title,String message,final Context context)
	{
		AlertDialog dialog=new AlertDialog.Builder(context).create();
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setButton("OK",
		new DialogInterface.OnClickListener()
		{
		public void onClick(DialogInterface dialog, int whichButton)
		{
		            dialog.dismiss();
		}
		});
		dialog.show();
	}
	
	 public void claimAlert(String title,String message,final Context context,final String UserName, final String id)
     {
             AlertDialog dialog=new AlertDialog.Builder(context).create();
             dialog.setTitle(title);
             dialog.setMessage(message);
             dialog.setButton("OK",
             new DialogInterface.OnClickListener()
             {
             public void onClick(DialogInterface dialog, int whichButton)
             {
                         dialog.dismiss();
                         Intent intent=new Intent(context,User_Page.class);
                         intent.putExtra("name", UserName);
                         intent.putExtra("_id", id);
                         context.startActivity(intent);
             }
             });

             dialog.show();
     }
	 
	 public void claimLoginAlert(String title,String message,final Context context,final Activity activity,final String UserName, final String id, final String phone, final String address, final String email)
     {
             AlertDialog dialog=new AlertDialog.Builder(context).create();
             dialog.setTitle(title);
             dialog.setMessage(message);
             dialog.setButton("OK",
             new DialogInterface.OnClickListener()
             {
             public void onClick(DialogInterface dialog, int whichButton)
             {
                         dialog.dismiss();
                         Intent intent=new Intent(context,User_Page.class);
                         intent.putExtra("name", UserName);
                         intent.putExtra("_id", id);
                         intent.putExtra("phone", phone);
                         intent.putExtra("add", address);
                         intent.putExtra("email", email);
                         activity.finish();
                         context.startActivity(intent);
             }
             });

             dialog.show();
     }
	 
	 public void ErrorAlertME(String title,String message,final Context context,final Activity activity, final String id)
		{
			AlertDialog dialog=new AlertDialog.Builder(context).create();
			dialog.setTitle(title);
			dialog.setMessage(message);
			dialog.setButton("OK",
			new DialogInterface.OnClickListener()
			{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				activity.finish();
				Intent intent = new Intent(context, ViewUserDetails.class);
				intent.putExtra("_id", id);
				context.startActivity(intent);
			    dialog.dismiss();
			}
			});
			dialog.show();
		}

}
