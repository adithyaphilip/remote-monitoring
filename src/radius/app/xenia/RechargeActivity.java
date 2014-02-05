package radius.app.xenia;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class RechargeActivity extends Activity implements OnClickListener {

	EditText cpn;
	TextView prompt;
	Button sbm;
	Handler mHandler;
	String usname;
	String pwd;
	//String json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Display d = getWindowManager().getDefaultDisplay();
		Point p = new Point();
		d.getRealSize(p);
		int w = p.x;
		int h = p.y;
		
		Intent i = getIntent();	
		usname=i.getExtras().getString("usname");
		pwd=i.getExtras().getString("pwd");
		
		RelativeLayout mrl = new RelativeLayout(this);
		RelativeLayout.LayoutParams mrlp = new RelativeLayout.LayoutParams(w,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mrl.setLayoutParams(mrlp);
		setContentView(mrl);
		
		prompt = new TextView(this);
		mrl.addView(prompt);
		prompt.setText("Please enter your coupon number to recharge:");
		((LayoutParams)prompt.getLayoutParams()).setMargins(w/20, 0, w/20, 0);
		prompt.setId(1);
		
		cpn = new EditText(this);
		mrl.addView(cpn);
		RelativeLayout.LayoutParams clp = (LayoutParams) cpn.getLayoutParams();
		clp.addRule(RelativeLayout.BELOW, prompt.getId());
		clp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		clp.setMargins(w/20, h/40, w/20, w/20);
		cpn.setId(2);
		cpn.setWidth(9*w/10);
		
		sbm = new Button(this);
		mrl.addView(sbm);
		RelativeLayout.LayoutParams blp = (LayoutParams) sbm.getLayoutParams();
		blp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		blp.addRule(RelativeLayout.BELOW,cpn.getId());
		blp.setMargins(0, h/40, 0, h/40);
		sbm.setId(3);
		sbm.setText("Submit");
		sbm.setOnClickListener(this);
		
		mHandler = new Handler(){
			public void handleMessage(Message msg)
			{
				String tst = msg.getData().getString("start");
				String tst2 = msg.getData().getString("fail");
				String json = msg.getData().getString("json");
				if(tst!=null)
				{
					Toast.makeText(getBaseContext(), tst, Toast.LENGTH_SHORT).show();
				}
				else if(tst2!=null)
				{
					Toast.makeText(getBaseContext(), tst2, Toast.LENGTH_LONG).show();
				}
				else if(json!=null)
				{
					switch(MyJson.getInt("rc", json))
					{
					case 0:
					{
						prompt.setText(MyJson.getString("message", json));
						Toast.makeText(getBaseContext(), prompt.getText(), Toast.LENGTH_SHORT).show();
						cpn.setVisibility(View.GONE);
						sbm.setVisibility(View.GONE);
						break;
					}
					case -1:
					{
						Toast.makeText(getBaseContext(), MyJson.getString("message", json), Toast.LENGTH_SHORT).show();
						break;
					}
					case -9998:
					{
						prompt.setText(json);
					}
					}					
				}
			}
		};
	}
	
	@Override
	public void onClick(View v)
	{
		switch(v.getId()){
		case 3:
		{
			showMessage(); //send json
		}
		}
	}
	
	public void showMessage()
	{
		Thread th = new Thread(new Runnable(){

			@Override
			public void run() {
				String json= null;
				Message msg1 = mHandler.obtainMessage();
				Bundle b = new Bundle();
				b.putString("start", "Requesting coupon validation");
				msg1.setData(b);
				mHandler.sendMessage(msg1);
				
				DefaultHttpClient hc = new DefaultHttpClient();
				HttpGet hg = new HttpGet("http://103.14.127.3:8080/MainController/CouponRechargeAPI?login_id="+usname+"&password="+pwd+"&coupon_id="+cpn.getText().toString());
				HttpResponse response;
				response = null;
				try
				{
					response = hc.execute(hg);
					json = EntityUtils.toString(response.getEntity());
					
					Message msg2 = mHandler.obtainMessage();
					Bundle b2 = new Bundle();
					b2.putString("json", json);
					msg2.setData(b2);
					mHandler.sendMessage(msg2);
				}
				catch(Exception e)
				{
					Message msg2 = mHandler.obtainMessage();
					Bundle b2 = new Bundle();
					b2.putString("fail", "Connection failed. Please check your internet connection and try again.");
					msg2.setData(b2);
					mHandler.sendMessage(msg2);
				}
			}		
		});
		th.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recharge, menu);
		return true;
	}

}
