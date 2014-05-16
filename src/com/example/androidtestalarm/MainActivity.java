package com.example.androidtestalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

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
				System.out.println("등록 클릭!!!");
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("취소 클릭!!!!");
			}
		});
	}

	private void initTime() {
		timepicker_alarmtime = (TimePicker) findViewById(R.id.timePicker1);
	}

	private void chkCurrentTime() {

	}

	private void chkPicTime() {

	}
}
