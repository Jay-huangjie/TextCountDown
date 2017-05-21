package com.example.jie.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Date;


/***
 * author: huangjie
 * 倒计时通用类
 */
@SuppressLint({"ViewConstructor", "SimpleDateFormat"})
public class TextCountDown extends Chronometer {

    private long mTime;
    private long mNextTime;
    private OnTimeListener mListener;
    private SimpleDateFormat mTimeFormat;
    private String unit;
    private String starText;
    private String endText;
    private Statue statue;
    private String prefix;

    private String runningTime;

    private boolean FirstZero;

    private int TickBackgroundResource;

    private int FinishBackgroundResource;

    private int TickTextColor;

    private int FinishTextColor;

    private int TickBackgroundColor;

    private int FinishBackgroundColor;
    private boolean clickable;

    public enum Statue {
        star, running, finish
    }

    public TextCountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setOnChronometerTickListener(listener);
    }

    public TextCountDown init(long _time_s) {
        mTime = mNextTime = _time_s;
        statue = Statue.star;
        return this;
    }

    private void updateTimeText() {
        if (statue == Statue.star) {
            setEmptyTextView(starText);
        } else if (statue == Statue.finish) {
            setEmptyTextView(endText);
        } else {
            String time = "0";
            if (mTimeFormat!=null)
                time = mTimeFormat.format(new Date(mNextTime * 1000));
            if (!FirstZero) {
                if (time.length() > 1) {
                    if (time.indexOf("0") == 0) {
                        runningTime = time.substring(1, time.length());
                    } else {
                        runningTime = time;
                    }
                } else {
                    runningTime = time;
                }
            } else {
                runningTime = time;
            }
            if (TextUtils.isEmpty(prefix)) prefix = "";
            if (TextUtils.isEmpty(unit)) unit = "";
            setText(prefix + runningTime + unit);
        }
    }


    private void setEmptyTextView(String Text){
        if (!TextUtils.isEmpty(Text)) {
            setText(Text);
        } else {
            setText("");
        }
    }

    public void Bulid() {
        updateTimeText();
    }

    public TextCountDown setStarText(String starText) {
        this.starText = starText;
        return this;
    }

    public TextCountDown setEndText(String endText) {
        this.endText = endText;
        return this;
    }

    public TextCountDown setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public TextCountDown setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public TextCountDown setFirstZero(boolean FirstZero) {
        this.FirstZero = FirstZero;
        return this;
    }

    public TextCountDown setTickBackgroundResource(@DrawableRes int resid) {
        this.TickBackgroundResource = resid;
        TickBackgroundColor = 0;
        return this;
    }


    public TextCountDown setFinishBackgroundResource(@DrawableRes int resid) {
        this.FinishBackgroundResource = resid;
        FinishBackgroundColor = 0;
        return this;
    }

    public TextCountDown setTickBackgroundColor(@ColorInt int resid) {
        this.TickBackgroundColor = resid;
        TickBackgroundResource = 0;
        return this;
    }


    public TextCountDown setFinishBackgroundColor(@ColorInt int resid) {
        this.FinishBackgroundColor = resid;
        FinishBackgroundResource = 0;
        return this;
    }

    public TextCountDown setTickTextColor(@ColorInt int color) {
        this.TickTextColor = color;
        return this;
    }

    public TextCountDown setFinishTextColor(@ColorInt int color) {
        this.FinishTextColor = color;
        return this;
    }

    public TextCountDown setTickClickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public void reStart(long time) {
        if (time == -1) {
            mNextTime = mTime;
        } else {
            mTime = mNextTime = time;
        }
        this.start();
    }

    public void reStart() {
        reStart(-1);
    }

    public void onStart() {
        this.start();
    }

    public void onResume() {
        this.start();
    }

    public void onPause() {
        this.stop();
    }

    public TextCountDown setTimeFormat(String pattern) {
        mTimeFormat = new SimpleDateFormat(pattern);
        return this;
    }

    public TextCountDown setOnTimeListener(OnTimeListener l) {
        mListener = l;
        return this;
    }

    public TextCountDown setClickListener(OnClickListener l) {
        setOnClickListener(l);
        return this;
    }


    OnChronometerTickListener listener = new OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            if (mNextTime <= 0) {    //finish
                mNextTime = 0;
                stop();
                if (null != mListener) mListener.onTimeComplete();
                statue = Statue.finish;
                if (FinishBackgroundResource != 0) setBackgroundResource(FinishBackgroundResource);
                if (FinishBackgroundColor != 0) setBackgroundColor(FinishBackgroundColor);
                if (FinishTextColor != 0) setTextColor(FinishTextColor);
                if (!clickable) setClickable(true);
            }
            mNextTime--;
            if (mNextTime > 0) {      //Tick
                statue = Statue.running;
                if (TickBackgroundResource != 0) setBackgroundResource(TickBackgroundResource);
                if (TickBackgroundColor != 0) setBackgroundColor(TickBackgroundColor);
                if (TickTextColor != 0) setBackgroundColor(TickTextColor);
                if (null != mListener) mListener.onTimeRunning(mNextTime);
                setClickable(clickable);
            }
            updateTimeText();
        }
    };


    public static final String SECONDS = "ss";
    public static final String MINUTES = "mm";


    interface OnTimeListener {
        void onTimeComplete();

        void onTimeRunning(long mNextTime);
    }

    /**********自定义方法区,可根据项目具体修改定制*************/
    public TextCountDown buildSmsDefault(long time) {
        init(time).setTimeFormat(SECONDS)
                .setStarText("发送验证码")
                .setEndText("重新发送")
                .setUnit("秒")
                .setTickBackgroundColor(Color.GRAY)
                .setFinishBackgroundColor(Color.BLUE)
                .setTickClickable(false)
                .Bulid();
        return this;
    }

    public TextCountDown buildSkipDefault(long time){
        init(time).setTimeFormat(TextCountDown.SECONDS)
                .setPrefix("跳过(")
                .setUnit(")")
                .setFinishBackgroundColor(Color.BLUE)
                .Bulid();
        return this;
    }

}
