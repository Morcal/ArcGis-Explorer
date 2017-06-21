package com.lyqdhgo.environment.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyqdhgo.environment.R;

public class MenuButton extends LinearLayout {
	private int index;
	private int fid;
	private ImageView ivIcon;
	private TextView ivText;
	private TextView ivText2;
	private TextView ivTip;
	private Display display;
	public MenuButton(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.layout_menu_button, this);
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
		ivIcon = (ImageView) findViewById(R.id.imageview_menu_icon);
		ivText = (TextView) findViewById(R.id.textview_menu_text);
		ivText2 = (TextView) findViewById(R.id.textview_menu_text2);
		ivTip = (TextView) findViewById(R.id.textview_menu_tip);
	}
	
	public MenuButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.layout_menu_button, this);
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
		ivIcon = (ImageView) findViewById(R.id.imageview_menu_icon);
		ivText = (TextView) findViewById(R.id.textview_menu_text);
		ivText2 = (TextView) findViewById(R.id.textview_menu_text2);
		ivTip = (TextView) findViewById(R.id.textview_menu_tip);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public void setIcon(int resid) {
		ivIcon.setImageResource(resid);
		ivIcon.setLayoutParams(new LayoutParams(display.getHeight()/4, display.getHeight()/4));
	}
	
	public void setText(int resid,int resid2) {
		ivText.setText(resid);
		if(getResources().getString(resid2).isEmpty()){
			ivText2.setVisibility(View.GONE);
		}else{
			ivText2.setText(resid2);
		}
	}
	public void setTextColor(int color) {
		ivText.setTextColor(color);
		ivText2.setTextColor(color);
	}
	
	public void setTip(String tip) {
		ivTip.setText(tip);
	}
}
