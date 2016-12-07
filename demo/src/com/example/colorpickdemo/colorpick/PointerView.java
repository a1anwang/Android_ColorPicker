package com.example.colorpickdemo.colorpick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PointerView extends View{

	private String TAG="PointerView";
	private int width, height;
	
	private float  rectangleScale=0.09f;//矩形占 总宽度的比例
	private float  rectangleWidth;
	
	private Paint paint;
	
	private RectF circleRectF;
	private float circleRadius;
	
	
	float degree;//矩形所占角度
	
	float margin;//矩形内部三角形 距离矩形
	
	
	private int pointerColor;
	
	Path path;
	
	private float desnity;
 
	public PointerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initDefault();
	}

	public PointerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initDefault();
	}

	public PointerView(Context context) {
		super(context);initDefault();
	}

	private void initDefault() {
		 paint=new Paint();
		 paint.setAntiAlias(true);
		 
		 desnity=getResources().getDisplayMetrics().density;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		
		rectangleWidth=width*rectangleScale;
		
		
		circleRadius=width/2.0f-rectangleWidth/2.0f;
		
		circleRectF=new RectF(rectangleWidth/2.0f, rectangleWidth/2.0f, rectangleWidth/2.0f+circleRadius*2,  rectangleWidth/2.0f+circleRadius*2);
		
		
		
		degree= (int) (Math.atan(rectangleWidth/2.0f/circleRadius)* 180/Math.PI)*2;
		// Log.e(TAG, " degree:"+degree);
		 
		 
		 margin=0.3f*rectangleWidth;
		 
		 path=new Path();
		 path.moveTo(width/2.0f-rectangleWidth/2.0f+margin/2.0f, margin  );
		 
		 path.lineTo(width/2.0f+rectangleWidth/2.0f-margin/2.0f,  margin  );
		 path.lineTo(width/2.0f , (rectangleWidth )-margin/2.0f );
		 path.close();
	}
	
	
	public void setPointerColorAndRotate(int pointerColor,float rotation) {
		this.pointerColor = pointerColor;
 		invalidate();
 		setRotation(rotation);
	}
	
	 @Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setStrokeWidth(2*desnity);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.LTGRAY);
		
		
		canvas.drawArc(circleRectF,-90+ degree/2, 360-degree, false, paint);
		
		paint.setStyle(Style.FILL);
		paint.setColor(pointerColor);
		canvas.drawPath(path, paint);
		
	}
	
	 
	 
}
