package com.teacherassistant.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class QuestionIndicator extends LinearLayout{

	private Paint mPaint;
	private Path mPath;
	private int mTriangleWidth;
	private int mTriangleHeight;
	private static final float RADIO_TRIANGLE_WIDTH = 1/6F;
	private int mInitTranslationX;
	private int mTranslationX;
	
	public QuestionIndicator(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public QuestionIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint =new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#eeeeee"));
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		canvas.save();
		canvas.translate(mInitTranslationX+mTranslationX, getHeight()+2);
		canvas.drawPath(mPath, mPaint);
		canvas.restore();
		
		super.dispatchDraw(canvas);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		mTriangleWidth =(int) (w / 3 * RADIO_TRIANGLE_WIDTH);
		mInitTranslationX = w / 3 / 2 - mTriangleWidth/2;
		
		initTriangle();
	}

	private void initTriangle() {
		// TODO Auto-generated method stub
		mTriangleHeight = mTriangleWidth/2;
		mPath =new Path();
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth/2, -mTriangleHeight);
		mPath.close();
	}

	public void scroll(int position, float offset) {
		// TODO Auto-generated method stub
		int tabWidth = getWidth()/3;
		mTranslationX = (int) ((position + offset )* tabWidth);
		invalidate();
	}

}
