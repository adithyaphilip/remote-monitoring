package radius.app.xenia;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	ImageView wbg;
	ImageView xenia;
	EditText usr;
	EditText pwd;
	Handler mHandler;
	TextView lg;
	CheckBox chkbox;
	AlertDialog ad;
	ProgressBar pglg;
	TextView adtv;
	TextView statval;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int w = size.x;
        final int h = size.y;   
        //why final needs to be used when not needed for wbg in OnGlobalLayoutListener
        //Ans:- Only local variables need final, not instance variables
        final int green = getResources().getColor(R.color.green);
        final int red = getResources().getColor(R.color.red);
        final int wh = getResources().getColor(R.color.white);
        
        //Experimental
        
        //Colour definition
        
        RelativeLayout mrl = new RelativeLayout(this);
        mrl.setBackgroundResource(R.drawable.bl_bg);		
		setContentView(mrl);
		
        ImageView wban = new ImageView(this);
		wban.setBackgroundColor(getResources().getColor(R.color.White));
		RelativeLayout.LayoutParams wlp = new RelativeLayout.LayoutParams(w,h/10);		
		wban.setLayoutParams(wlp);		
		wban.setId(2);
		mrl.addView(wban);
		
		ImageView xl = new ImageView(this);
		xl.setAdjustViewBounds(true);
		xl.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		xl.setImageResource(R.drawable.xenialogo);
		RelativeLayout.LayoutParams xlp = new RelativeLayout.LayoutParams(w,h/10);
		xlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		xl.setLayoutParams(xlp);
		xl.setId(10022);
		mrl.addView(xl);
		xl.bringToFront();
		
		//il definition
		final RelativeLayout il = new RelativeLayout(this);
		final RelativeLayout.LayoutParams ilp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		ilp.addRule(RelativeLayout.CENTER_IN_PARENT);
		ilp.setMargins(w/20, h/40, w/20, 0);
		il.setLayoutParams(ilp);
		il.setBackgroundResource(R.drawable.coolbg);
		il.setId(64);
		mrl.addView(il);
		
		//il children
		
		lg = new TextView(this);
		lg.setText(R.string.login);
		RelativeLayout.LayoutParams lglp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lglp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lglp.setMargins(0, h/40, 0, h/40);
		lg.setLayoutParams(lglp);
		lg.setTextSize(32);
		lg.setTypeface(null, Typeface.BOLD);
		lg.setId(3);
		il.addView(lg);
		
		usr = new EditText(this);
		RelativeLayout.LayoutParams ulp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		ulp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		ulp.addRule(RelativeLayout.BELOW,3);
		ulp.setMargins(0, h/40, 0, h/40);
		usr.setLayoutParams(ulp);
		usr.setId(4);
		usr.setWidth((int)(7*w/10.0));
		usr.setHint(R.string.usrhint);
		usr.setBackgroundColor(wh);
		il.addView(usr);
	        
		pwd = new EditText(this);
		RelativeLayout.LayoutParams plp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		plp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		plp.addRule(RelativeLayout.BELOW,4);
		plp.setMargins(0, h/40, 0, 0);
		pwd.setLayoutParams(plp);
		pwd.setId(5);
		pwd.setWidth((int)(7*w/10.0));
		pwd.setHint(R.string.pwdhint);
		pwd.setBackgroundColor(wh);
		pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		il.addView(pwd);
		
		chkbox = new CheckBox(this);
		RelativeLayout.LayoutParams chlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		chlp.addRule(RelativeLayout.ALIGN_LEFT,5);
		chlp.addRule(RelativeLayout.BELOW,5);
		chlp.setMargins(0, h/80, 0, 0);
		chkbox.setLayoutParams(chlp);
		chkbox.setText("Remember me");
		chkbox.setId(6);
		il.addView(chkbox);
		
		final ImageView lgb = new ImageView(this);
		RelativeLayout.LayoutParams lpb = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpb.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lpb.addRule(RelativeLayout.BELOW,6);
		lpb.setMargins(0, h/80, w/20, h/40);
		lgb.setLayoutParams(lpb);
		lgb.setImageResource(R.drawable.redb);
		lgb.setOnClickListener(this);
		lgb.setId(7);
		il.addView(lgb);
	    
		OnGlobalLayoutListener gll = new OnGlobalLayoutListener(){
			
			public void onGlobalLayout() {
				if(lgb.getHeight()!=0)
				{
					lgb.getViewTreeObserver().removeOnGlobalLayoutListener(this);
					ilp.height= lgb.getBottom() + w/20;
				}
			}
			
		};

		lgb.getViewTreeObserver().addOnGlobalLayoutListener(gll);
        //Experimental ends        
        
        //Dialog box for logging in
        
        RelativeLayout adl = new RelativeLayout(this);
        adl.setBackgroundResource(R.color.Gray);
        pglg = new ProgressBar(this);
        pglg.setId(61);
        adl.addView(pglg);
        
        adtv = new TextView(this);
        adtv.setText("Logging in");
        RelativeLayout.LayoutParams atvlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        atvlp.addRule(RelativeLayout.RIGHT_OF, 61);
        atvlp.addRule(RelativeLayout.CENTER_VERTICAL);
        adtv.setLayoutParams(atvlp);
        adl.addView(adtv);
        
        //remember to test with getLayoutParams() before adding to layout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(adl);
        
        ad = builder.create();
        
        TextView statlbl = new TextView(this);
        statlbl.setId(65);
        statlbl.setTypeface(null, Typeface.BOLD);
        statlbl.setTextColor(wh);
        statlbl.setText("Server Status: ");
        RelativeLayout.LayoutParams stlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        statlbl.setLayoutParams(stlp);
        stlp.addRule(RelativeLayout.ALIGN_LEFT,64);
        stlp.addRule(RelativeLayout.BELOW,64);
        stlp.setMargins(0, h/40, 0, 0);
        mrl.addView(statlbl);
        
        statval = new TextView(this);
        statval.setText("Checking connection...");
        statval.setTypeface(null,Typeface.BOLD);
        statval.setTextColor(wh);
        RelativeLayout.LayoutParams stvlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        statval.setLayoutParams(stvlp);
        stvlp.addRule(RelativeLayout.RIGHT_OF,65);
        stvlp.addRule(RelativeLayout.ALIGN_BOTTOM,65);
        mrl.addView(statval);
        
        mHandler = new Handler(){
        	public void handleMessage(Message msg)
        	{
        		String tst = msg.getData().getString("sent");
        		String islg = msg.getData().getString("success");
        		String stat = msg.getData().getString("stat");
        		if(islg!=null) //means login
        		{
        			ad.show();
        			//Toast.makeText(getBaseContext(), msg.getData().getString("success"), Toast.LENGTH_LONG).show();
        		}
        		else if(stat!=null)//Means server status checking
        		{
        			if(stat.equals("success"))
        			{
        				statval.setText("Connection established");
        				statval.setTextColor(green);
        			}
        			else
        			{
        				statval.setText(stat);
        				statval.setTextColor(red);
        			}
        		}
        		else if(MyJson.getInt("rc",tst)==0)
        		{
        			ad.dismiss();
        			saveLogin();
        			Intent i = new Intent(getBaseContext(), InfoActivity.class);
        			i.putExtra("json", tst);
        			i.putExtra("usname", usr.getText().toString());
        			i.putExtra("pwd", pwd.getText().toString());
        			startActivity(i);     
        			
        		}        		
        		else if(MyJson.getInt("rc", tst)==-1)
        		{
        			ad.dismiss();
        			Toast.makeText(getBaseContext(), "Wrong username/password. Please try again", Toast.LENGTH_LONG).show();
        		}
        		else
        		{
        			ad.dismiss();
        			Toast.makeText(getBaseContext(), tst, Toast.LENGTH_LONG).show();
        		}
        	}
        };       

        checkConnection();
        
        //Setting checkbox
        if(getSharedPreferences("uspwd",0).getString("usrname", null)==null)
        {
        	chkbox.setChecked(false);
        }
        else
        {
        	usr.setText(getSharedPreferences("uspwd",0).getString("usrname", null));
        	pwd.setText(getSharedPreferences("uspwd",0).getString("pwd",null));
        	chkbox.setChecked(true);
        }        
    }
    @Override
    public void onResume()
    {
        super.onResume();
    	checkConnection();
    }
    @Override
	public void onClick(View v)
    {
    	switch(v.getId())
    	{
    	case 7:
    	{  		
    		ad.show();
    		sendJson();  		
    		break;
    	}
    	
    	}
    }
    void sendJson() 
    {
    	final String susr = usr.getText().toString();
    	final String spwd = pwd.getText().toString();
    	
    	Uri uri = new Uri.Builder()
        .scheme("http")
        .authority("103.14.127.3:8080")
        .path("MainController/login_auth")
        .appendQueryParameter("name", "1000102")
        .appendQueryParameter("password", "1000102")
        .build();
    	
    	try
    	{    	
    	Thread th = new Thread(new Runnable(){
    		public void run()
    		{
    			Message msg = mHandler.obtainMessage();
    			Bundle b = new Bundle();
    			b.putString("success", "Logging in");
    			msg.setData(b);
    			mHandler.sendMessage(msg);
    			
    			final DefaultHttpClient hc = new DefaultHttpClient();
    	    	HttpGet hg=null;
				
					hg = new HttpGet("http://103.14.127.3:8080/MainController/loginAPI?login_id="+susr+"&password="+spwd);
				
    			String json="";
    			HttpResponse response = null;
    			try
    			{
    				response = hc.execute(hg);
    				json=EntityUtils.toString(response.getEntity());
    				Message msg2 = mHandler.obtainMessage();
        			Bundle b1 = new Bundle();
        			b1.putString("sent", json);
        			msg2.setData(b1);
        			mHandler.sendMessage(msg2);   
    			}
    			catch(Exception e)
    			{
    				Message msg2 = mHandler.obtainMessage();
        			Bundle b1 = new Bundle();
        			b1.putString("sent", "Connection failed. Check Internet settings.");
        			msg2.setData(b1);
        			mHandler.sendMessage(msg2);
				}
    		}    		
    	});
    	th.start();
    	}
    	catch(Exception e)
    	{
    		Toast.makeText(this, "Please ensure that you are connected to the internet", Toast.LENGTH_LONG).show();
    	}
    }
    public void saveLogin()
    {
    	if(chkbox.isChecked()==true)
    	{
    		SharedPreferences uspwd = getSharedPreferences("uspwd",0);
    		SharedPreferences.Editor editor = uspwd.edit();
    		editor.putString("usrname", usr.getText().toString());
    		editor.putString("pwd", pwd.getText().toString());
    		editor.commit();
    	}
    	else
    	{
    		SharedPreferences uspwd = getSharedPreferences("uspwd",0);
    		SharedPreferences.Editor editor = uspwd.edit();
    		editor.putString("usrname", null);
    		editor.putString("pwd", null);
    		editor.commit();
    	}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void checkConnection()
    {
    	
    	Thread th = new Thread( new Runnable()
    	{
    		public void run()
    		{
    			int ct = 10000;
    			int st = 10000;
    			HttpParams hp = new BasicHttpParams();
    			HttpConnectionParams.setConnectionTimeout(hp, ct);
    			//HttpConnectionParams.setSoTimeout(hp, st);
    			DefaultHttpClient hc = new DefaultHttpClient(hp);
    			HttpGet hg = new HttpGet("http://103.14.127.3:8080/MainController/loginAPI?login_id=asdasd&password=asd");    
    			try{
    				HttpResponse hr = hc.execute(hg);
    				Message msg = mHandler.obtainMessage();
    				Bundle b = new Bundle();
    				b.putString("stat", "success");
    				msg.setData(b);
    				mHandler.sendMessage(msg);
    			}
    			catch(java.net.SocketTimeoutException e)
    			{
    				Message msg = mHandler.obtainMessage();
    				Bundle b = new Bundle();
    				b.putString("stat", "Connection failed");
    				msg.setData(b);
    				mHandler.sendMessage(msg);
    			}
    			catch(ClientProtocolException e)
    			{
    				Message msg = mHandler.obtainMessage();
    				Bundle b = new Bundle();
    				b.putString("stat", "Connection failed");
    				msg.setData(b);
    				mHandler.sendMessage(msg);
    			}
    			catch(IOException e)
    			{
    				Message msg = mHandler.obtainMessage();
    				Bundle b = new Bundle();
    				b.putString("stat", "Connection failed");
    				msg.setData(b);
    				mHandler.sendMessage(msg);
    			}    			
    		}
    	});
    	th.start();
    }
}
