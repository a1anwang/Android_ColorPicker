package com.example.colorpickdemo.colorpick;

import com.example.colorpickdemo.R;
import com.example.colorpickdemo.colorpick.ColorPickerView.OnColorPickListener;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ColorPickActivity extends  Activity {

	View view_color;
	TextView tv_r, tv_g, tv_b;
	ColorPickerView colorPickerView;

	PointerView pointerview;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_colorpick);
		colorPickerView = (ColorPickerView) findViewById(R.id.colorpickview);
		tv_r=(TextView) findViewById(R.id.tv_r);
		tv_g=(TextView) findViewById(R.id.tv_g);
		tv_b=(TextView) findViewById(R.id.tv_b);
		view_color=findViewById(R.id.view_color);
		
		pointerview=(PointerView) findViewById(R.id.pointerview);
		
		colorPickerView.setColorPickListener(new OnColorPickListener() {
			
			@Override
			public void onColorChanged(int red, int green, int blue,float rotation) {
				int color=Color.rgb(red, green, blue);
				
				view_color.setBackgroundColor(color);
				
				
				
				tv_r.setText("R:"+red);
				tv_g.setText("G:"+green);
				tv_b.setText("B:"+blue);
				
				
				pointerview.setPointerColorAndRotate(color, rotation);
			}
		});
		
		/*
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				 (colorPickerView.  getDrawable()). ;
			}
		}, 3000);*/
	}

}
