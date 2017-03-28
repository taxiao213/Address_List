package com.han.address_list.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.han.address_list.R;

/**
 * Created by aaa on 2017/3/21.
 */

public class QuickIndexBar extends View {

    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    Paint paint = null;
    int ColorDefault = Color.GRAY;//默认颜色

    int ColorPressed = Color.BLUE;//按下颜色

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setColor(ColorDefault);
        int size = getResources().getDimensionPixelSize(R.dimen.text_size);
        paint.setTextSize(size);

        //文字绘制的默认起点是文字的左下角,CENTER表示底边的中心，而不是正中心
        paint.setTextAlign(Paint.Align.CENTER);//Baseline：基准线
    }

    float cellHeight;//一个格子的高度

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cellHeight = getMeasuredHeight() * 1f / indexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //遍历绘制26个字母
        for (int i = 0; i < indexArr.length; i++) {
            String text = indexArr[i];
            float x = getMeasuredWidth() / 2;//整个宽的一半

            //格子高度一半+文字高度一半+i*格子高度
            int textHeight = getTextHeight(text);
            float y = cellHeight / 2 + textHeight / 2 + i * cellHeight;

            //变色
            paint.setColor(i == index ? ColorPressed : ColorDefault);

            canvas.drawText(text, x, y, paint);
        }

    }

    int index = -1;//用来记录触摸的索引的

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int temp = (int) (event.getY() / cellHeight);
                if (temp != index) {
                    index = temp;

                    //对index进行一下安全性的检查
                    if (index >= 0 && index < indexArr.length) {
                        String word = indexArr[index];
                        if (listener != null) {
                            listener.onLetterChange(word);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //抬起重置变量
                index = -1;
                break;
        }

        //重绘
        invalidate();
        return true;
    }

    /**
     * 获取文字的高度
     *
     * @param text
     * @return
     */
    private int getTextHeight(String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);//对bounds进行赋值
        return bounds.height();
    }

    private OnLetterChangeListener listener;

    public void setOnLetterChangeListener(OnLetterChangeListener listener) {
        this.listener = listener;
    }

    public interface OnLetterChangeListener {
        void onLetterChange(String word);
    }

}
