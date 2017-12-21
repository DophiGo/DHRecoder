package com.example.user.qrrecoder.materdesign;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.utils.SizeUtils;

/**
 * Created by dxs on 2017/12/20.
 */

public class TipTextView extends AppCompatTextView {
    private RectF line;
    private int Line_w=5;
    private Paint linePaint=new Paint();
    public TipTextView(Context context) {
        super(context);
    }

    public TipTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TipTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initRect(int w,int h) {
        line=new RectF(0,0, SizeUtils.dp2px(Line_w),h);
        linePaint.setAntiAlias(true);
        linePaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initRect(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    /**
     * 更新文本
     * @param text
     */
    public void updateText(CharSequence text){
        setText(text);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLineTips(canvas);
    }

    private void drawLineTips(Canvas canvas){
        if(line!=null&&linePaint!=null){
            canvas.drawRect(line,linePaint);
        }
    }
}
