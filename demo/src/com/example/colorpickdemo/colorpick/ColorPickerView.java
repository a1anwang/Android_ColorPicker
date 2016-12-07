package com.example.colorpickdemo.colorpick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ColorPickerView extends ImageView {
	private String TAG = "ColorPickerView";

	Bitmap bitmap;

	public interface OnColorPickListener {
		public void onColorChanged(int red, int green, int blue, float rotation);
	}

	private OnColorPickListener colorPickListener;

	public void setColorPickListener(OnColorPickListener colorPickListener) {
		this.colorPickListener = colorPickListener;
	}

	public ColorPickerView(Context context) {
		super(context);

	}
 

	public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public ColorPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private int width, height;

	private int bitmapWidth, bitmapHeight;

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

		width = w;
		height = h;

	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();

		if (bitmap == null) {
			bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
			bitmapWidth = (int) (bitmap.getWidth());
			bitmapHeight = (int) (bitmap.getHeight());

			Log.e(TAG, "onAttachedToWindow bitmap: " + bitmapWidth + "  "
					+ bitmapHeight);
			int pixel = bitmap.getPixel(bitmapWidth / 2, bitmapHeight / 8);

			// 鑾峰彇棰滆壊
			int redValue = Color.red(pixel);
			int blueValue = Color.blue(pixel);
			int greenValue = Color.green(pixel);

			if (colorPickListener != null && redValue != 0 && blueValue != 0
					&& greenValue != 0 && redValue != 34 && blueValue != 31
					&& greenValue != 41) {
				colorPickListener.onColorChanged(redValue, greenValue,
						blueValue, 0);
			}
		}
	}

	float bx, by;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if (bitmap == null) {
			bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
			bitmapWidth = (int) (bitmap.getWidth());
			bitmapHeight = (int) (bitmap.getHeight());

			Log.e(TAG, "bitmap: " + bitmapWidth + "  " + bitmapHeight);
			//

		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int bitmapX = 0, bitmapY = 0;

			Log.e(TAG, " x:" + event.getX() + " y:" + event.getY());

			float degree = 0;
			if (x > width / 2.0f && y < height / 2.0f) {
				// 鍙充笂瑙�
				degree = (float) (Math.atan((x - width / 2.0f)
						/ (height / 2.0f - y)) * 180 / Math.PI);

				bx = (float) (width / 2.0f + width / 2.0f * 0.6
						* Math.sin(degree * Math.PI / 180));

				by = (float) (height / 2.0f - height / 2.0f * 0.6
						* Math.cos(degree * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);

			} else if (x > width / 2.0f && y > height / 2.0f) {
				// 鍙充笅瑙�
				degree = (float) ((Math.atan((y - height / 2.0f)
						/ (x - width / 2.0f)) * 180 / Math.PI) + 90);

				bx = (float) (width / 2.0f + width / 2.0f * 0.6
						* Math.cos((degree - 90) * Math.PI / 180));

				by = (float) (height / 2.0f + height / 2.0f * 0.6
						* Math.sin((degree - 90) * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);
			} else if (x < width / 2.0f && y > height / 2.0f) {
				// 宸︿笅瑙�
				degree = (float) ((Math.atan((width / 2.0f - x)
						/ (y - height / 2.0f)) * 180 / Math.PI) + 180);
				bx = (float) (width / 2.0f - width / 2.0f * 0.6
						* Math.sin((degree - 180) * Math.PI / 180));

				by = (float) (height / 2.0f + height / 2.0f * 0.6
						* Math.cos((degree - 180) * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);
			} else {
				// 宸︿笂瑙�
				degree = (float) ((Math.atan((height / 2.0f - y)
						/ (width / 2.0f - x)) * 180 / Math.PI) + 270);
				bx = (float) (width / 2.0f - width / 2.0f * 0.6
						* Math.cos((degree - 270) * Math.PI / 180));

				by = (float) (height / 2.0f - height / 2.0f * 0.6
						* Math.sin((degree - 270) * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);
			}

			if (bitmapX < 0 || bitmapX >= bitmapWidth || bitmapY < 0
					|| bitmapY >= bitmapHeight)
				return true;
			int pixel = bitmap.getPixel(bitmapX, bitmapY);
			// 鑾峰彇棰滆壊
			int redValue = Color.red(pixel);
			int blueValue = Color.blue(pixel);
			int greenValue = Color.green(pixel);

			if (colorPickListener != null && redValue != 0 && blueValue != 0
					&& greenValue != 0 && redValue != 34 && blueValue != 31
					&& greenValue != 41) {
				colorPickListener.onColorChanged(redValue, greenValue,
						blueValue, degree);
			}

			// Log.e(TAG, " r:"+redValue +" g:"+greenValue);

		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			int bitmapX = 0, bitmapY = 0;

			float degree = 0;
			if (x > width / 2.0f && y < height / 2.0f) {
				// 鍙充笂瑙�
				degree = (float) (Math.atan((x - width / 2.0f)
						/ (height / 2.0f - y)) * 180 / Math.PI);

				bx = (float) (width / 2.0f + width / 2.0f * 0.6
						* Math.sin(degree * Math.PI / 180));

				by = (float) (height / 2.0f - height / 2.0f * 0.6
						* Math.cos(degree * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);

			} else if (x > width / 2.0f && y > height / 2.0f) {
				// 鍙充笅瑙�
				degree = (float) ((Math.atan((y - height / 2.0f)
						/ (x - width / 2.0f)) * 180 / Math.PI) + 90);

				bx = (float) (width / 2.0f + width / 2.0f * 0.6
						* Math.cos((degree - 90) * Math.PI / 180));

				by = (float) (height / 2.0f + height / 2.0f * 0.6
						* Math.sin((degree - 90) * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);

			} else if (x < width / 2.0f && y > height / 2.0f) {
				// 宸︿笅瑙�
				degree = (float) ((Math.atan((width / 2.0f - x)
						/ (y - height / 2.0f)) * 180 / Math.PI) + 180);

				bx = (float) (width / 2.0f - width / 2.0f * 0.6
						* Math.sin((degree - 180) * Math.PI / 180));

				by = (float) (height / 2.0f + height / 2.0f * 0.6
						* Math.cos((degree - 180) * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);
			} else {
				// 宸︿笂瑙�
				degree = (float) ((Math.atan((height / 2.0f - y)
						/ (width / 2.0f - x)) * 180 / Math.PI) + 270);
				bx = (float) (width / 2.0f - width / 2.0f * 0.6
						* Math.cos((degree - 270) * Math.PI / 180));

				by = (float) (height / 2.0f - height / 2.0f * 0.6
						* Math.sin((degree - 270) * Math.PI / 180));

				bitmapX = (int) (bx * 1.0f / width * bitmapWidth);
				bitmapY = (int) (by * 1.0f / height * bitmapHeight);
			}
			if (bitmapX < 0 || bitmapX >= bitmapWidth || bitmapY < 0
					|| bitmapY >= bitmapHeight)
				return true;
			int pixel = bitmap.getPixel(bitmapX, bitmapY);
			// 鑾峰彇棰滆壊
			int redValue = Color.red(pixel);
			int blueValue = Color.blue(pixel);
			int greenValue = Color.green(pixel);
			if (colorPickListener != null
			// && redValue!=0 && blueValue!=0&&greenValue!=0 && redValue!=34 &&
			// blueValue!=31&&greenValue!=41
			) {
				colorPickListener.onColorChanged(redValue, greenValue,
						blueValue, degree);
			}

			// Log.e(TAG, " r:"+redValue
			// +" g:"+greenValue+"  bitmapX:"+bitmapX+" bitmapY:"+bitmapY );
		} else if (event.getAction() == MotionEvent.ACTION_UP) {

		}
		return true;
	}

	Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		Log.e(TAG, " onDraw---------------- ");
		canvas.drawCircle(bx, by, 10, paint);

	}

}
