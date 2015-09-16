package com.cranberrygame.cordova.plugin.pushnotification.parsepushnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class ParseBroadcastReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "ParsePushNotificationPlugin";

    @Override
    public void onReceive(Context context, Intent intent) {
		if (ParsePushNotificationPlugin.destroyed() || !ParsePushNotificationPlugin.isInitialized()) {
			SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("cordova-plugin-pushnotification-parse", Context.MODE_PRIVATE);
			String applicationId = sharedPref.getString("applicationId", "");
			String clientKey = sharedPref.getString("clientKey", "");
			Parse.initialize(context.getApplicationContext(), applicationId, clientKey);
			ParseInstallation.getCurrentInstallation().saveInBackground();
            ParsePushNotificationPlugin.setInitialized();
		}
    }
    
}
