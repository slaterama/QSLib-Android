package com.slaterama.qslib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

/**
 * Utility class for performing application-wide tasks or getting application-level information.
 */
public class AppUtils {

	/**
	 * Return the application label as specified in the AndroidManifest.
	 * @param context The context whose application label you want.
	 * @return The application label.
	 */
	public static CharSequence getApplicationLabel(Context context) {
		CharSequence appLabel;
		try {
			int labelResId = context.getApplicationInfo().labelRes;
			appLabel = context.getString(labelResId);
		} catch (Resources.NotFoundException e) {
			try {
				PackageManager packageManager = context.getPackageManager();
				ApplicationInfo contextInfo = context.getApplicationInfo();
				ApplicationInfo applicationInfo = packageManager.getApplicationInfo(contextInfo.packageName, 0);
				appLabel = packageManager.getApplicationLabel(applicationInfo);
			} catch (PackageManager.NameNotFoundException e1) {
				appLabel = "Unknown";
			}
		}
		return appLabel;
	}

	private AppUtils() {}

}