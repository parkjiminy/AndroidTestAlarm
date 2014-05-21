package com.example.androidtestalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn_reg;
	private Button btn_cancel;
	private TimePicker timepicker_alarmtime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initBtn();
		initTime();
	}

	private void initBtn() {
		btn_reg = (Button) findViewById(R.id.btn_register);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);

		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				regAlarm();
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelAlarm();
			}
		});
	}

	private void initTime() {
		timepicker_alarmtime = (TimePicker) findViewById(R.id.timePicker1);
	}

	private int[] chkPickerHour() {
		int picHour = timepicker_alarmtime.getCurrentHour();
		int minute = timepicker_alarmtime.getCurrentMinute();

		int[] currentPicTime = { picHour, minute };

		return currentPicTime;
	}

	private void regAlarm() {
		Toast.makeText(MainActivity.this, "알람 등록", Toast.LENGTH_SHORT).show();
		int[] picTime = chkPickerHour();
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

		AlarmManage al = new AlarmManage();
		al.setAlarms(am, MainActivity.this, picTime);

	}

	private void cancelAlarm() {
		Toast.makeText(MainActivity.this, "알람 취소", Toast.LENGTH_SHORT).show();
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

		AlarmManage al = new AlarmManage();
		al.emptyAlarms(am, MainActivity.this);
	}
}
