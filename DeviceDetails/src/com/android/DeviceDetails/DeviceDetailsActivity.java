package com.android.DeviceDetails;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.TextView;

public class DeviceDetailsActivity extends Activity {

	TextView manu=null,model=null,resolution=null,density=null,sdkversion=null,network=null,build=null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        manu=(TextView)findViewById(R.id.manufacture);
        model=(TextView)findViewById(R.id.model);
        resolution=(TextView)findViewById(R.id.resolution);
        density=(TextView)findViewById(R.id.density);
        sdkversion=(TextView)findViewById(R.id.sdkversion);
        network=(TextView)findViewById(R.id.network);
        build=(TextView)findViewById(R.id.build);
        
	      float scale; 
	      int ScrWidth,ScrHeight;
	      DisplayMetrics displaymetrics = new DisplayMetrics(); 
	      getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	      
	      scale = getApplicationContext().getResources().getDisplayMetrics().density;
	      
	      ScrWidth  = getWindowManager().getDefaultDisplay().getWidth();  
	      ScrHeight = getWindowManager().getDefaultDisplay().getHeight();
	      
	      String model1 = android.os.Build.MODEL;
	      String device = android.os.Build.DEVICE;
	      String brand = android.os.Build.BRAND;
	      String manu1 = android.os.Build.MANUFACTURER;
	      String version = android.os.Build.VERSION.SDK;
	      String release = android.os.Build.VERSION.RELEASE;
	      
	      TelephonyManager telephonyManager =((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
	      String operatorName = telephonyManager.getNetworkOperatorName();
	      
	      String operatorNam = telephonyManager.getSimOperatorName();
	      
	      manu.setText(" Manufacture : " + manu1);
	      model.setText(" Model : " + model1);
	      resolution.setText(" Resolution : " + ScrWidth + "x" + ScrHeight);
	      if(scale==1.5)
	    	  density.setText(" Density : " + scale + " (High Resolution)");
	      else if(scale==1.0)
	    	  density.setText(" Density : " + scale + " (Mediun Resolution)");
	      else if(scale==0.75)
	    	  density.setText(" Density : " + scale + " (Low Resolution)");
	      sdkversion.setText( " SDK Version : " + version);
	      network.setText("Network Operator : " + operatorNam);
	      build.setText(" Build : " + release);
        
    }
}