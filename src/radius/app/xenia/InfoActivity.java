package radius.app.xenia;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends Activity implements OnClickListener{
	String json;
	String usname;
	String pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		final ScrollView sc = new ScrollView(this);
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.iarl);
		RelativeLayout mrl = new RelativeLayout(this);
		setContentView(mrl);
		
		Display d = getWindowManager().getDefaultDisplay();
		Point p = new Point();
		d.getRealSize(p);
		final int w = p.x;
		final int h = p.y;
		
		int wh = getResources().getColor(R.color.White);
		
		mrl.setBackgroundResource(R.drawable.bl_bg);
		sc.addView(rl);
		sc.setId(261);
		RelativeLayout.LayoutParams sclp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,7*h/10);		
		sc.setLayoutParams(sclp);
		//mrl.addView(sc);
		
		Intent i = getIntent();
		json = i.getStringExtra("json");
		usname = i.getStringExtra("usname");
		pwd = i.getStringExtra("pwd");
		/*TextView tv = new TextView(this);
		rl.addView(tv);
		if(json!=null)
		tv.setText(json); 
		else
			tv.setText("json is null");*/
		
		
		sclp.setMargins(w/20, w/20, w/20, w/20);
		
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
		
		mrl.addView(sc);
		sclp.addRule(RelativeLayout.BELOW,2);
		
		final RelativeLayout box1 = new RelativeLayout(this);
		box1.setBackgroundResource(R.drawable.bl_bg);
		RelativeLayout.LayoutParams b1lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,2*h/5);
		//b1lp.setMargins(w/20, w/20, w/20, w/20);
		box1.setLayoutParams(b1lp);
		box1.setId(10);
		final RelativeLayout box2 = new RelativeLayout(this);
		box2.setBackgroundResource(R.drawable.bl_bg2);
		RelativeLayout.LayoutParams b2lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,2*h/5);
		b2lp.addRule(RelativeLayout.BELOW, 10);
		box2.setLayoutParams(b2lp);
		box2.setId(11);
		rl.addView(box1);
		rl.addView(box2);
		
		ImageView srciv = new ImageView(this);		
		if(getVal("power_source").trim().equalsIgnoreCase("GRID"))
		{
			srciv.setImageResource(R.drawable.grid1);
		}
		else
		srciv.setImageResource(R.drawable.dg1);
		//Remember to use if for actual
		
		RelativeLayout.LayoutParams srlp = new RelativeLayout.LayoutParams(h/10,h/10);
		srlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		srlp.setMargins(0, 0, h/40, 0);
		srciv.setLayoutParams(srlp);
		srciv.setId(12);
		box1.addView(srciv);
		
		final TextView date = new TextView(this);
		date.setId(8);
		RelativeLayout.LayoutParams dlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		date.setLayoutParams(dlp);
		DateFormat df = DateFormat.getDateInstance();
		date.setText(df.format(new Date()));
		date.setTextColor(getResources().getColor(R.color.White));
		TextView time = new TextView(this);
		time.setId(9);
		DateFormat df2= DateFormat.getTimeInstance(DateFormat.SHORT);
		time.setText(df2.format(new Date()));
		RelativeLayout.LayoutParams dlp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		dlp2.addRule(RelativeLayout.BELOW,8);
		time.setLayoutParams(dlp2);
		time.setTextColor(getResources().getColor(R.color.White));
		box1.addView(date);
		box1.addView(time);
		
		TextView cbal_lbl = new TextView(this);
		cbal_lbl.setText("Current Balance");
		cbal_lbl.setId(3);
		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp1.addRule(RelativeLayout.BELOW, 12);
		cbal_lbl.setLayoutParams(lp1);
		TextView gr_lbl = new TextView(this);
		gr_lbl.setText("Grid Reading");
		gr_lbl.setId(4);
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp2.addRule(RelativeLayout.BELOW, 3);
		gr_lbl.setLayoutParams(lp2);
		TextView dgr_lbl = new TextView(this);
		dgr_lbl.setText("DG Reading");
		dgr_lbl.setId(5);
		RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp3.addRule(RelativeLayout.BELOW, 4);
		dgr_lbl.setLayoutParams(lp3);
		TextView lamt_lbl = new TextView(this);
		lamt_lbl.setText("Last Recharge Amt.");
		lamt_lbl.setId(6);
		RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp4.addRule(RelativeLayout.BELOW, 5);
		lamt_lbl.setLayoutParams(lp4);
		TextView src_lbl = new TextView(this);
		src_lbl.setText("Source of Power");
		src_lbl.setId(7);		
		RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp5.addRule(RelativeLayout.BELOW, 6);
		src_lbl.setLayoutParams(lp5);
		
		//Colons
		TextView c1 = new TextView(this);
		c1.setText(":");
		box1.addView(c1);		
		((RelativeLayout.LayoutParams)c1.getLayoutParams()).addRule(RelativeLayout.RIGHT_OF,lamt_lbl.getId());
		((RelativeLayout.LayoutParams)c1.getLayoutParams()).setMargins(w/20, 0, 0, 0);
		((RelativeLayout.LayoutParams)c1.getLayoutParams()).addRule(RelativeLayout.ALIGN_BOTTOM,lamt_lbl.getId());
		c1.setId(30);
		c1.setTextColor(wh);
		c1.setTypeface(null, Typeface.BOLD);
		
		TextView c2 = new TextView(this);
		box1.addView(c2);
		c2.setText(":");
		((RelativeLayout.LayoutParams)c2.getLayoutParams()).addRule(RelativeLayout.ALIGN_LEFT,c1.getId());
		((RelativeLayout.LayoutParams)c2.getLayoutParams()).setMargins(0, 0, 0, 0);
		((RelativeLayout.LayoutParams)c2.getLayoutParams()).addRule(RelativeLayout.ALIGN_BOTTOM,gr_lbl.getId());
		c2.setId(31);
		c2.setTextColor(wh);
		c2.setTypeface(null, Typeface.BOLD);
		
		TextView c3 = new TextView(this);
		box1.addView(c3);
		c3.setText(":");
		((RelativeLayout.LayoutParams)c3.getLayoutParams()).addRule(RelativeLayout.ALIGN_LEFT,c1.getId());
		((RelativeLayout.LayoutParams)c3.getLayoutParams()).setMargins(0, 0, 0, 0);
		((RelativeLayout.LayoutParams)c3.getLayoutParams()).addRule(RelativeLayout.ALIGN_BOTTOM,cbal_lbl.getId());
		c3.setId(32);
		c3.setTextColor(wh);
		c3.setTypeface(null, Typeface.BOLD);
		
		TextView c4 = new TextView(this);
		box1.addView(c4);
		c4.setText(":");
		((RelativeLayout.LayoutParams)c4.getLayoutParams()).addRule(RelativeLayout.ALIGN_LEFT,c1.getId());
		((RelativeLayout.LayoutParams)c4.getLayoutParams()).setMargins(0, 0, 0, 0);
		((RelativeLayout.LayoutParams)c4.getLayoutParams()).addRule(RelativeLayout.ALIGN_BOTTOM,dgr_lbl.getId());
		c4.setId(33);
		c4.setTextColor(wh);
		c4.setTypeface(null, Typeface.BOLD);
		
		TextView c5 = new TextView(this);
		box1.addView(c5);
		c5.setText(":");
		((RelativeLayout.LayoutParams)c5.getLayoutParams()).addRule(RelativeLayout.ALIGN_LEFT,c1.getId());
		((RelativeLayout.LayoutParams)c5.getLayoutParams()).setMargins(0, 0, 0, 0);
		((RelativeLayout.LayoutParams)c5.getLayoutParams()).addRule(RelativeLayout.ALIGN_BOTTOM,src_lbl.getId());
		c5.setId(34);
		c5.setTextColor(wh);
		c5.setTypeface(null, Typeface.BOLD);
		
		//Colons end */
		
		TextView cbal_val = new TextView(this);		
		cbal_val.setId(13);
		RelativeLayout.LayoutParams lpv1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//lpv1.addRule(RelativeLayout.BELOW, 12);
		lpv1.addRule(RelativeLayout.ALIGN_BOTTOM,c3.getId());
		lpv1.addRule(RelativeLayout.RIGHT_OF, c3.getId());
		cbal_val.setLayoutParams(lpv1);
		cbal_val.setText(getVal("balance_amount"));
		
		TextView gr_val = new TextView(this);		
		gr_val.setId(14);
		RelativeLayout.LayoutParams lpv2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpv2.addRule(RelativeLayout.BELOW, 13);
		lpv2.addRule(RelativeLayout.ALIGN_LEFT, 13);
		gr_val.setLayoutParams(lpv2);
		gr_val.setText(getVal("grid_reading"));
		
		TextView dgr_val = new TextView(this);		
		dgr_val.setId(15);
		RelativeLayout.LayoutParams lpv3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpv3.addRule(RelativeLayout.BELOW, 14);
		lpv3.addRule(RelativeLayout.ALIGN_LEFT, 14);
		dgr_val.setLayoutParams(lpv3);
		dgr_val.setText(getVal("dg_reading"));
		
		TextView lamt_val = new TextView(this);		
		lamt_val.setId(16);
		RelativeLayout.LayoutParams lpv4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpv4.addRule(RelativeLayout.BELOW, 15);
		lpv4.addRule(RelativeLayout.ALIGN_LEFT, 15);
		lamt_val.setLayoutParams(lpv4);
		lamt_val.setText(getVal("last_coupon_amount"));
		
		final TextView src_val = new TextView(this);		
		src_val.setId(17);		
		RelativeLayout.LayoutParams lpv5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpv5.addRule(RelativeLayout.BELOW, 16);
		lpv5.addRule(RelativeLayout.ALIGN_LEFT, 16);
		src_val.setLayoutParams(lpv5);
		src_val.setText(getVal("power_source"));
		
		int gr = getResources().getColor(R.color.LawnGreen);
		cbal_val.setTextColor(gr);
		gr_val.setTextColor(gr);
		dgr_val.setTextColor(gr);
		lamt_val.setTextColor(gr);
		src_val.setTextColor(gr);
		
		
		cbal_lbl.setTextColor(wh);
		gr_lbl.setTextColor(wh);
		dgr_lbl.setTextColor(wh);
		lamt_lbl.setTextColor(wh);
		src_lbl.setTextColor(wh);
		box1.addView(cbal_lbl);
		box1.addView(gr_lbl);
		box1.addView(dgr_lbl);
		box1.addView(lamt_lbl);
		box1.addView(src_lbl);
		cbal_lbl.setTypeface(null, Typeface.BOLD);
		gr_lbl.setTypeface(null, Typeface.BOLD);
		dgr_lbl.setTypeface(null, Typeface.BOLD);
		lamt_lbl.setTypeface(null, Typeface.BOLD);
		src_lbl.setTypeface(null, Typeface.BOLD);
		date.setTypeface(null, Typeface.BOLD);
		time.setTypeface(null, Typeface.BOLD);
		//Padding
		cbal_lbl.setPadding(w/20,0,0,0);
		gr_lbl.setPadding(w/20,0,0,0);
		dgr_lbl.setPadding(w/20,0,0,0);
		lamt_lbl.setPadding(w/20,0,0,0);
		src_lbl.setPadding(w/20,0,0,0);
		date.setPadding(w/20,0,0,0);
		time.setPadding(w/20,0,0,0);
		
		box1.addView(cbal_val);
		box1.addView(gr_val);
		box1.addView(dgr_val);
		box1.addView(lamt_val);
		box1.addView(src_val);	
		
		//src_val.setText(Integer.toString((int)src_val.getX()));
		
		src_val.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
			public void onGlobalLayout()
			{
				if(date.getHeight()!=0)
					{
						src_val.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						box1.getLayoutParams().height=(int)(9*(src_val.getY()-date.getY())/7.0);
					}
			}
		});
		
		//Making box2
		
		RelativeLayout.LayoutParams blp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		blp.setMargins(0, h/40, 0, h/40);
		blp.addRule(RelativeLayout.BELOW,10);
		box2.setLayoutParams(blp);
		
		final TextView b2t = new TextView(this);
		b2t.setText("kWh used in last 24 hours");
		b2t.setTypeface(null,Typeface.BOLD);
		b2t.setTextColor(wh);
		RelativeLayout.LayoutParams btlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		btlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		b2t.setLayoutParams(btlp);
		b2t.setId(21);
		box2.addView(b2t);
		
		TextView glbl = new TextView(this);
		glbl.setId(22);
		glbl.setText("Grid");
		RelativeLayout.LayoutParams glp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		glp.addRule(RelativeLayout.BELOW,21);
		glp.setMargins(w/20, h/40, 0, 0);
		glbl.setLayoutParams(glp);
		glbl.setTextColor(wh);
		box2.addView(glbl);
		
		TextView dglbl = new TextView(this);
		dglbl.setId(23);
		dglbl.setText("DG");
		RelativeLayout.LayoutParams dglp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		dglp.addRule(RelativeLayout.BELOW,24);
		dglp.setMargins(w/20, h/40, 0, 0);
		dglbl.setLayoutParams(dglp);
		dglbl.setTextColor(wh);
		box2.addView(dglbl);		
		
		ProgressBar pgg = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
		pgg.setMax(100);		
		
		RelativeLayout.LayoutParams pglp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,h/40);
		pglp1.setMargins(0, 0, w/40, 0);
		pglp1.addRule(RelativeLayout.RIGHT_OF, 22);
		pglp1.addRule(RelativeLayout.BELOW,22);
		pgg.setLayoutParams(pglp1);
		pgg.setId(24);
		box2.addView(pgg);
		
		
		final ProgressBar pgdg = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
		pgdg.setMax(100);		
		RelativeLayout.LayoutParams pglp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		pglp2.setMargins(0, 0, w/40, 0);
		pgdg.setLayoutParams(pglp2);
		pglp2.addRule(RelativeLayout.ALIGN_LEFT, 24);
		pglp2.addRule(RelativeLayout.BELOW,23);
		pgdg.setId(25);
		box2.addView(pgdg);
		
		try{
			//pgg.setProgress(Math.round(Float.parseFloat(getVal("daily_grid_unit"))));
			pgg.setProgress(MyJson.getInt("daily_grid_unit", json));
		}
		catch(NumberFormatException e)
		{
			pgg.setProgress(50);
			//Toast.makeText(this, R.string.guniterr, Toast.LENGTH_LONG);
		}
		try
		{
			pgdg.setProgress(Math.round(Float.parseFloat(getVal("daily_dg_unit"))));
			// For use in actual pgdg.setProgress(MyJson.getInt("daily_dg_unit", json));
		}
		catch(NumberFormatException e)
		{
			pgdg.setProgress(50);
			//Toast.makeText(this, R.string.dguniterr, Toast.LENGTH_LONG).show();
		}
		
		//Setting sc's height
		OnGlobalLayoutListener b2gll = new OnGlobalLayoutListener(){
			public void onGlobalLayout()
			{
				if(pgdg.getHeight()!=0)
				{
					box2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
					box2.getLayoutParams().height=pgdg.getBottom()+h/40;
				}
			}
		};
		box2.getViewTreeObserver().addOnGlobalLayoutListener(b2gll);
		OnGlobalLayoutListener b2gll2 = new OnGlobalLayoutListener()
		{
			public void onGlobalLayout()
			{
				if(box2.getHeight()==pgdg.getBottom())
				{
					sc.getViewTreeObserver().removeOnGlobalLayoutListener(this);
					sc.getLayoutParams().height=box2.getBottom();
				}
			}
		};
		sc.getViewTreeObserver().addOnGlobalLayoutListener(b2gll2);
		
		
		//Recharge button and powered by radius
		TextView pwr = new TextView(this);
		pwr.setText("Powered by radius");
		mrl.addView(pwr);
		((RelativeLayout.LayoutParams)pwr.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		((RelativeLayout.LayoutParams)pwr.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		((RelativeLayout.LayoutParams)pwr.getLayoutParams()).setMargins(0, 0, w/40, h/80);
		pwr.setId(40);
		pwr.setTextColor(wh);
				
		ImageView rchrg = new ImageView(this);
		rchrg.setOnClickListener(this);
		mrl.addView(rchrg);
		rchrg.setImageResource(R.drawable.rchrgb);
		rchrg.setId(41);
		((RelativeLayout.LayoutParams)rchrg.getLayoutParams()).addRule(RelativeLayout.ALIGN_LEFT,sc.getId());
		((RelativeLayout.LayoutParams)rchrg.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
	}

	public void onClick(View v)
	{
		switch(v.getId()){
		case 41:
		{
			openRechargeActivity();
		}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}
	String getVal(String key)
	{
		String result = MyJson.getString(key, json);
		return "   "+result;
	}
	public void openRechargeActivity()
	{
		Intent i = new Intent(this,RechargeActivity.class);
		i.putExtra("usname",usname);
		i.putExtra("pwd", pwd);
		startActivity(i);
	}
}
