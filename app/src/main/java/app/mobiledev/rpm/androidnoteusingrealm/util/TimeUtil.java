package app.mobiledev.rpm.androidnoteusingrealm.util;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by rmanacmol on 11/20/2016.
 */

public class TimeUtil {
    public static long getUnix(){
        return new Date().getTime();
    }
    public static String unixToTimeAgo(String unix){
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(unix),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        return timeAgo.toString();
    }
}
