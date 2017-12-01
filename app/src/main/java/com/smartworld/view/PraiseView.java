package com.smartworld.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ${charles}     on 2017/11/30.
 *
 * @desc ${TODO}
 */

public class PraiseView extends View
{
    private String msg = "点赞";
    private Paint circlePaint;
    private Paint textPaint;
    private Paint progressPaint;
    private Paint arcPaint;
    private Paint numPaint,praisePaint;

    private Rect bounds;
    private int radious ;
    private int viewWidth;

    private int arcWidth;

    private RectF rectF;

    private int  interval = 6;

    private float leftNum = 0;
    private float rightNum = 0;
    private float total = 0;

    private float progressWidth;
    public PraiseView(Context context, AttributeSet attrs)
    {
        super(context, attrs);


        circlePaint = new Paint();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setTextSize(34);
        textPaint.setColor(Color.WHITE);

        progressPaint = new Paint();
        progressPaint.setStrokeWidth(28);
        progressPaint.setColor(Color.WHITE);

        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(22);


        numPaint = new Paint();
        numPaint.setTextSize(46);
        numPaint.setColor(Color.BLACK);

        praisePaint = new Paint();
        praisePaint.setTextSize(36);
        praisePaint.setColor(Color.BLACK);

        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        circlePaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        praisePaint.setAntiAlias(true);
        numPaint.setAntiAlias(true);
        progressPaint.setAntiAlias(true);
        progressPaint.setAntiAlias(true);

        total = leftNum + rightNum;

        arcWidth = (int) arcPaint.getStrokeWidth();

        viewWidth = getMeasuredWidth() / 2;

        radious = getMeasuredWidth() / 12;

        rectF = new RectF(viewWidth-radious-arcWidth/2,arcWidth/2,viewWidth+radious+arcWidth - arcWidth/2,2*radious+ 2*arcWidth - arcWidth/2);
        progressWidth = viewWidth-radious-arcWidth;

        canvas.drawCircle(viewWidth,radious+arcWidth,radious,circlePaint);

        textPaint.getTextBounds(msg,0,msg.length(),bounds);
        canvas.drawText(msg,viewWidth-(bounds.width()/2),radious+arcWidth+interval + (bounds.height())/2 + 16,textPaint);

        textPaint.getTextBounds(String.valueOf(total),0,String.valueOf(total).length(),bounds);
        canvas.drawText(String.valueOf((int)total),viewWidth- (textPaint.measureText(String.valueOf((int)total)) / 2),radious+arcWidth+interval - bounds.height()/4,textPaint);

        progressPaint.setColor(Color.WHITE);
        canvas.drawLine(0,radious+arcWidth+interval,viewWidth-radious-arcWidth,radious+arcWidth+interval,progressPaint);

        progressPaint.setColor(Color.WHITE);
        canvas.drawLine(viewWidth + radious+arcWidth,radious+arcWidth+interval,viewWidth*2,radious+arcWidth+interval,progressPaint);

        progressPaint.setColor(Color.BLUE);
        float leftWidth = leftNum / total * progressWidth;
        Log.e("leftWidth",leftWidth + "");
        if (leftWidth > 0)
        canvas.drawLine(viewWidth-radious-arcWidth,radious+arcWidth+interval,progressWidth-leftWidth,radious+arcWidth+interval,progressPaint);

        progressPaint.setColor(Color.RED);
        float rightWidth = rightNum / total * progressWidth;
        if (rightWidth > 0)
        canvas.drawLine(viewWidth + radious+arcWidth,radious+arcWidth+interval,(viewWidth + radious+arcWidth) + rightWidth ,radious+arcWidth+interval,progressPaint);


        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF,0,360,false,arcPaint);

        arcPaint.setColor(Color.RED);
        float leftAngle = leftNum / total * 360-8;
        canvas.drawArc(rectF,180,leftAngle/2,false,arcPaint);
        canvas.drawArc(rectF,180-(leftAngle/2),leftAngle/2,false,arcPaint);

        arcPaint.setColor(Color.BLUE);
        float rightAngle = rightNum / total * 360-8;
        canvas.drawArc(rectF,(360 - rightAngle/2),rightAngle/2,false,arcPaint);
        canvas.drawArc(rectF,0,rightAngle/2,false,arcPaint);


        canvas.drawText(String.valueOf((int) leftNum) + "  赞",0,(radious+arcWidth+interval) - arcWidth-6,praisePaint);

        String leftPercent = (int)(leftNum / total * 100) + "%";
        canvas.drawText(leftPercent,viewWidth - (radious+arcWidth+interval) - numPaint.measureText(leftPercent),(radious+arcWidth+interval) - arcWidth-6,numPaint);

        String rightMsg = String.valueOf((int) rightNum) + "  赞";
        canvas.drawText(rightMsg,2*viewWidth- praisePaint.measureText(rightMsg),(radious+arcWidth+interval) - arcWidth-6 ,praisePaint);

        String rightPercent = (int)(rightNum / total * 100) + "%";
        canvas.drawText(rightPercent,viewWidth + (radious+arcWidth+interval) ,(radious+arcWidth+interval) - arcWidth-6,numPaint);


    }


    public void setLeftNum(int leftNum){
        this.leftNum = leftNum;
    }

    public void setRightNum(int rightNum){
        this.rightNum = rightNum;
    }


}
