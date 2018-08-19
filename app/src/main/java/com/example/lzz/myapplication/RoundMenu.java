package com.example.lzz.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lzz on 2018/8/5.
 */

public class RoundMenu extends View {
    private static final String SHOPPING_CAR = "购物车";

    private int mArcColor = Color.argb(255, 72, 209, 204);
    private int mArcFocusColor = Color.argb(80, 72, 209, 204);

    private Paint mArcPaint;
    private int mWidth;
    private int mMargin;
    private RectF mOval;
    private Path mOvalPath;

    private int mTextSize;
    private TextPaint mTextPaint;

    private String[] mProductTypes;
    private long mTouchTime;
    private int mClickState = -2;

    private OnMenuClickListener onMenuClickListener;

    public RoundMenu(Context context) {
        this(context, null);
    }

    public RoundMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mProductTypes = getResources().getStringArray(R.array.product_type);

        mMargin = DensityUtil.dip2px(getContext(), 16);
        mTextSize = DensityUtil.dip2px(getContext(), 16);

        mArcPaint = new Paint();
        mArcPaint.setDither(true);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(mArcColor);
        mArcPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new TextPaint();
        mTextPaint.setDither(true);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextPaint.setLetterSpacing(0.5f);
        }

        mOvalPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = getMeasuredWidth();
        if (mOval == null) {
            mOval = new RectF(0, 0, mWidth, mWidth);
            mOvalPath.addArc(new RectF(mWidth / 8, mWidth / 8, mWidth * 7 / 8, mWidth * 7 / 8), -125, 70);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mArcPaint.setColor(mArcColor);
        for (int i = 0; i < mProductTypes.length; i++) {
            canvas.save();
            canvas.rotate(72 * i, mWidth / 2, mWidth / 2);
            mArcPaint.setColor(mClickState == i ? mArcFocusColor : mArcColor);
            canvas.drawArc(mOval, -125, 70, true, mArcPaint);
            canvas.drawTextOnPath(mProductTypes[i], mOvalPath, 0, mTextSize / 2, mTextPaint);
            canvas.restore();
        }

        mArcPaint.setColor(Color.WHITE);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 4, mArcPaint);

        mArcPaint.setColor(mClickState == -1 ? mArcFocusColor : mArcColor);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 4 - mMargin, mArcPaint);
        canvas.drawText(SHOPPING_CAR, mWidth / 2, mWidth / 2, mTextPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchTime = System.currentTimeMillis();
                float textX = event.getX();
                float textY = event.getY();
                int distanceLine = (int) getDisForTwoSpot(mWidth / 2, mWidth / 2, textX, textY);//距离中心点之间的直线距离
                if (distanceLine <= (mWidth / 4)) {
                    //点击的是中心圆；按下点到中心点的距离小于中心园半径，那就是点击中心园了
                    mClickState = -1;
                } else if (distanceLine <= getWidth() / 2) {
                    //点击的是某个扇形；按下点到中心点的距离大于中心圆半径小于大圆半径，那就是点击某个扇形了
                    float sweepAngle = 70;//每个弧形的角度
                    int angle = getRotationBetweenLines(mWidth / 2, mWidth / 2, textX, textY);
                    //这个angle的角度是从正Y轴开始，而我们的扇形是从正X轴开始，再加上偏移角度，所以需要计算一下
                    angle = (angle + 35) % 360;
                    mClickState = (int) (angle / sweepAngle);//根据角度得出点击的是那个扇形
                } else {
                    //点击了外面
                    mClickState = -2;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if ((System.currentTimeMillis() - mTouchTime) < 300) {
                    if (onMenuClickListener != null) {
                        onMenuClickListener.onMenuClick(mClickState);
                    }
                }
                mClickState = -2;
                invalidate();
                break;
        }
        return true;
    }

    public static int getRotationBetweenLines(float centerX, float centerY, float xInView, float yInView) {
        double rotation = 0;

        double k1 = (double) (centerY - centerY) / (centerX * 2 - centerX);
        double k2 = (double) (yInView - centerY) / (xInView - centerX);
        double tmpDegree = Math.atan((Math.abs(k1 - k2)) / (1 + k1 * k2)) / Math.PI * 180;

        if (xInView > centerX && yInView < centerY) {  //第一象限
            rotation = 90 - tmpDegree;
        } else if (xInView > centerX && yInView > centerY) //第二象限
        {
            rotation = 90 + tmpDegree;
        } else if (xInView < centerX && yInView > centerY) { //第三象限
            rotation = 270 - tmpDegree;
        } else if (xInView < centerX && yInView < centerY) { //第四象限
            rotation = 270 + tmpDegree;
        } else if (xInView == centerX && yInView < centerY) {
            rotation = 0;
        } else if (xInView == centerX && yInView > centerY) {
            rotation = 180;
        }
        return (int) rotation;
    }

    /**
     * 求两个点之间的距离
     *
     * @return
     */
    public static double getDisForTwoSpot(float x1, float y1, float x2, float y2) {
        float width, height;
        if (x1 > x2) {
            width = x1 - x2;
        } else {
            width = x2 - x1;
        }

        if (y1 > y2) {
            height = y2 - y1;
        } else {
            height = y2 - y1;
        }
        return Math.sqrt((width * width) + (height * height));
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    interface OnMenuClickListener {
        void onMenuClick(int position);
    }
}
