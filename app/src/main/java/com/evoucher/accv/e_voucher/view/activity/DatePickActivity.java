package com.evoucher.accv.e_voucher.view.activity;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.application.Config;
import com.evoucher.accv.e_voucher.http.XHttp;
import com.evoucher.accv.e_voucher.view.w.ChineseCalendar;
import com.evoucher.accv.e_voucher.view.w.GregorianLunarCalendarView;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by 李小白 on 2017/9/20.  日期选择
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_date_pick)
public class DatePickActivity extends BaseActivity {
    @ViewInject(R.id.calendarView)
    GregorianLunarCalendarView calendarView;
    @ViewInject(R.id.startTimeTv)
    TextView startTimeTv;
    @ViewInject(R.id.startTimeView)
    View startTimeView;
    @ViewInject(R.id.endTimeView)
    View endTimeView;
    @ViewInject(R.id.endTimeTv)
    TextView endTimeTv;
    @ViewInject(R.id.startTimeLine)
    View startTimeLine;
    @ViewInject(R.id.endTimeLine)
    View endTimeLine;
    @ViewInject(R.id.chooseTimePickTv)
    TextView chooseTimePickTv;
    boolean isChooseStart = true;
    boolean isChooseTimePick = true;
    
    @Override
    protected void initData() {
        setTitle("账单详情");
        setTitleMoreImg(INVISIBLE);
        
        setTitleBackdrop(ContextCompat.getColor(getContext(), R.color.white));
        String year = new SimpleDateFormat("yyyy").format(new Date());
        
        chooseView();
        calendarView.init();
        calendarView.setNormalColor(ContextCompat.getColor(getContext(), R.color.small_grey));
        calendarView.setThemeColor(ContextCompat.getColor(getContext(), R.color.black));
        ((NumberPickerView) calendarView.getNumberPickerYear()).setWrapSelectorWheel(false);
        ((NumberPickerView) calendarView.getNumberPickerYear()).setMaxValue(Integer.valueOf(year));
        ((NumberPickerView) calendarView.getNumberPickerYear()).setValue(Integer.valueOf(year));
        calendarView.setOnDateChangedListener(new GregorianLunarCalendarView.OnDateChangedListener() {
            @Override
            public void onDateChanged(GregorianLunarCalendarView.CalendarData calendarData) {
                Calendar calendar = calendarData.getCalendar();
                String showToast;
                if (isChooseTimePick) {
                    showToast = calendar.get(Calendar.YEAR) + "-"
                            + (calendar.get(Calendar.MONTH) + 1) + "-"
                            + calendar.get(Calendar.DAY_OF_MONTH);
                } else {
                    showToast = calendar.get(Calendar.YEAR) + "-"
                            + (calendar.get(Calendar.MONTH) + 1);
                }
                
                
                if (isChooseStart)
                    startTimeTv.setText(showToast);
                else
                    endTimeTv.setText(showToast);
            }
        });
        
        
    }
    
    @Event(value = {R.id.startTimeView, R.id.endTimeView, R.id.chooseTimePickView})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.startTimeView:
                isChooseStart = true;
                chooseView();
                break;
            case R.id.endTimeView:
                isChooseStart = false;
                chooseView();
                break;
            case R.id.chooseTimePickView:
                chooseTimePickView();
                break;
        }
        
    }
    
    private void chooseView() {
        if (isChooseStart) {
            startTimeLine.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
            endTimeLine.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.small_grey));
            startTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            endTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.small_grey));
        } else {
            startTimeLine.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.small_grey));
            endTimeLine.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
            startTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.small_grey));
            endTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }
    }
    
    private void chooseTimePickView() {
        if (isChooseTimePick) {
            chooseTimePickTv.setText("按月选择");
            calendarView.getNumberPickerDay().setVisibility(View.GONE);
            isChooseTimePick = false;
        } else {
            chooseTimePickTv.setText("按日选择");
            calendarView.getNumberPickerDay().setVisibility(View.VISIBLE);
            isChooseTimePick = true;
        }
    }
}
