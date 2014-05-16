package com.example.androidtestalarm;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

	private static PowerManager.WakeLock sCpuWakeLock;
	private static final String TAG = "water";

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onReceive(Context context, Intent intent1) {
		acquireCpuWakeLock(context);
		int icon = R.drawable.ic_launcher;
		String title = context.getResources().getString(R.string.app_name);

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent i = new Intent(context, MainActivity.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Bundle b = intent1.getExtras();
		String msg = b.getString("msg",
				context.getResources().getString(R.string.default_noti_msg));

		// TODO 다이얼로그 표시 테스트중.
		// showDialog(msg, context);

		Notification.Builder mBuilder = new Notification.Builder(context);
		mBuilder.setSmallIcon(icon);
		mBuilder.setTicker(title);
		mBuilder.setContentTitle(title);
		mBuilder.setContentText(msg);
		mBuilder.setDefaults(Notification.DEFAULT_SOUND
				| Notification.DEFAULT_VIBRATE);
		mBuilder.setContentIntent(pendingIntent);
		mBuilder.setAutoCancel(true);

		mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

		mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
		mBuilder.setSound(RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
		mBuilder.setLights(0xFF0000FF, 500, 500);

		notificationManager.notify(111, mBuilder.build());

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		releaseCpuLock();
	}

	static void acquireCpuWakeLock(Context context) {
		if (sCpuWakeLock != null) {
			return;
		}
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		sCpuWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.ON_AFTER_RELEASE, TAG);
		sCpuWakeLock.acquire();
	}

	static void releaseCpuLock() {
		if (sCpuWakeLock != null) {
			sCpuWakeLock.release();
			sCpuWakeLock = null;
		}
	}

}