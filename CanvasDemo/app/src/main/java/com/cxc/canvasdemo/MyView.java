package com.cxc.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cxc on 2017/4/28.
 */

public class MyView extends View {
    private Paint mPaint;

    public MyView(Context context, AttributeSet attrs){
        super(context,attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(80);
        String text = "My View";
        canvas.drawText(text,getWidth()/4,getHeight()/2,mPaint);
    }
}
