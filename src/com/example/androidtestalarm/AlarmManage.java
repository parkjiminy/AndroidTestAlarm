package com.example.androidtestalarm;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmManage {

	public void setAlarms(AlarmManager am, Context context, int[] currentTime) {
		long cycleTime = 24 * 60 * 60 * 1000;

		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent,
				Intent.FILL_IN_DATA);
		Calendar calendar = Calendar.getInstance();

		// 알램 테스트시 사용
		calendar = Calendar.getInstance();

		intent.putExtra("msg", currentTime[0] + "시 " + currentTime[1] + "");

		sender = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), currentTime[0], currentTime[1], 0);
		calendar = caculateCalendar(calendar);
		am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
				cycleTime, sender);
	}

	public void emptyAlarms(AlarmManager am, Context context) {
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent,
				Intent.FILL_IN_DATA);

		am.cancel(sender);

	}

	private Calendar caculateCalendar(Calendar calendar) {
		Calendar calendar1 = Calendar.getInstance();

		if (calendar.getTimeInMillis() < calendar1.getTimeInMillis()) {
			calendar.add(Calendar.DATE, 1);
		}

		return calendar;
	}
}
