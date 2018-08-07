package com.fudaoculture.lee.notificationmanagerutils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public class NewNotificationUtils {


    public static final String DEFAULT_CHANNELID = "com.fudaoculture.lee";
    public static final String DEFAULT_GROUP_CHANNELID = "com.fudaoculture.lee";

    private NotificationManager notificationManager;

    private Notification current;


    private NotificationCompat.Builder builder;

    private int currentNotiId;

    private Context context;

    private NotificationChannelGroup notificationChannelGroup;

    private NotificationChannel notificationChannel;

    private String channelGroupId;
    private String channelGroupName;
    private String channelId;
    private String channelDesc;
    private String channelName;
    private boolean byPassDnd = false;
    private int visibility = Notification.VISIBILITY_PUBLIC;
    private boolean showBadge = false;
    private int importance = NotificationManager.IMPORTANCE_DEFAULT;

    private Uri soundUri;

    private long[] vibrateLong;

    private int lightColor = -1;


    public NewNotificationUtils(Context context, String channelGroupId, String channelGroupName, String channelId, int importance) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.channelGroupName = channelGroupName;
        this.channelGroupId = channelGroupId;
        this.channelId = channelId;
        this.importance = importance;
    }


    public NewNotificationUtils setChannelGroupId(String channelGroupId) {
        this.channelGroupId = channelGroupId;
        return this;
    }

    public NewNotificationUtils setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
        return this;
    }

    public NewNotificationUtils setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public NewNotificationUtils setByPassDnd(boolean byPassDnd) {
        this.byPassDnd = byPassDnd;
        return this;
    }

    public NewNotificationUtils setVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public NewNotificationUtils setShowBadge(boolean showBadge) {
        this.showBadge = showBadge;
        return this;
    }

    public NewNotificationUtils setImportance(int importance) {
        this.importance = importance;
        return this;
    }

    public NewNotificationUtils setSoundUri(Uri soundUri) {
        this.soundUri = soundUri;
        return this;
    }

    public NewNotificationUtils setVibrate(long[] vibrate) {
        this.vibrateLong = vibrate;
        return this;
    }


    public NewNotificationUtils setLightColor(int lightColor) {
        this.lightColor = lightColor;
        return this;
    }


    public void build() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelGroup(channelGroupId, channelGroupName);
            createChannel(channelId, channelId, channelName, channelGroupId, false, NotificationCompat.VISIBILITY_PUBLIC, true, NotificationManager.IMPORTANCE_HIGH, soundUri, vibrateLong, lightColor);
        }

        builder = new NotificationCompat.Builder(context, channelId);
    }

//    public static class ChannelBuilder {
////     * @param channelId            频道id
////     * @param channelDesc          具体描述
////     * @param channelName
////     * @param byPassDnd            是否绕过免打扰
////     * @param lockScreenVisibility 是否在锁屏界面显示 example: Notification.VISIBILITY_PRIVATE
////     * @param showBadge            是否在桌面图标上显示未读消息数量
////     * @param importance  NotificationManager.IMPORTANCE_MAX
//
////        NotificationManager.IMPORTANCE_UNSPECIFIED 状态栏不会显示应用的通知图标(smallIcon) 只有下拉才能看到缩略的通知 (此通知在下拉后还需用户手动展开才能看到完整的通知) 三星s8
////        NotificationManager.IMPORTANCE_MIN 同上
//
////        NotificationManager.IMPORTANCE_LOW 无声 状态栏有应用的通知图标(smallIcon)
//
////        NotificationManager.IMPORTANCE_DEFAULT 有声音震动 状态栏显示应用的通知图标(smallIcon)
//
////        NotificationManager.IMPORTANCE_HIGH 有声音震动 状态栏显示应用的通知图标(smallIcon) 并且会有heads-up的浮动通知效果
//
//        private String channelGroupId;
//        private String channelId;
//        private String channelDesc;
//        private String channelName;
//        private boolean byPassDnd = false;
//        private int visibility = Notification.VISIBILITY_PUBLIC;
//        private boolean showBadge = false;
//        private int importance = NotificationManager.IMPORTANCE_DEFAULT;
//
//        private Uri soundUri;
//
//        private long[] vibrateLong;
//
//        private int lightColor = -1;
//
//        public ChannelBuilder(String channelGroupId, String channelId, int importance) {
//            this.channelGroupId = channelGroupId;
//            this.channelId = channelId;
//            this.importance = importance;
//        }
//
//        public ChannelBuilder setChannelGroupId(String channelGroupId) {
//            this.channelGroupId = channelGroupId;
//            return this;
//        }
//
//        public ChannelBuilder setChannelDesc(String channelDesc) {
//            this.channelDesc = channelDesc;
//            return this;
//        }
//
//        public ChannelBuilder setChannelName(String channelName) {
//            this.channelName = channelName;
//            return this;
//        }
//
//        public ChannelBuilder setByPassDnd(boolean byPassDnd) {
//            this.byPassDnd = byPassDnd;
//            return this;
//        }
//
//        public ChannelBuilder setVisibility(int visibility) {
//            this.visibility = visibility;
//            return this;
//        }
//
//        public ChannelBuilder setShowBadge(boolean showBadge) {
//            this.showBadge = showBadge;
//            return this;
//        }
//
//        public ChannelBuilder setImportance(int importance) {
//            this.importance = importance;
//            return this;
//        }
//
//        public ChannelBuilder setSoundUri(Uri soundUri) {
//            this.soundUri = soundUri;
//            return this;
//        }
//
//        public ChannelBuilder setVibrate(long[] vibrate) {
//            this.vibrateLong = vibrate;
//            return this;
//        }
//
//
//        public ChannelBuilder setLightColor(int lightColor) {
//            this.lightColor = lightColor;
//            return this;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.O)
//        public NotificationChannel build() {
//            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
//            AudioAttributes.Builder builder = new AudioAttributes.Builder();
//            notificationChannel.setShowBadge(showBadge);
//            notificationChannel.setBypassDnd(byPassDnd);
//            notificationChannel.setLockscreenVisibility(visibility);
//
//            if (!TextUtils.isEmpty(channelGroupId)) {
//                notificationChannel.setGroup(channelGroupId);
//            }
//
//            if (!TextUtils.isEmpty(channelDesc)) {
//                notificationChannel.setDescription(channelDesc);
//            }
//
//            if (soundUri != null) {
//
//                notificationChannel.setSound(soundUri, builder.build());
//            } else {
////                用此方法达到静音的效果 = = （因为在8.0的heads-up效果中importance必须会有声音 如果想静音且想有heads-up效果的话就必须传空值）
//                notificationChannel.setSound(Uri.EMPTY, builder.build());
//            }
//
//            if (lightColor != -1) {
//                notificationChannel.setLightColor(lightColor);
//            } else {
//                notificationChannel.enableLights(false);
//            }
//
//            if (vibrateLong != null) {
//                notificationChannel.setVibrationPattern(vibrateLong);
//            } else {
//                notificationChannel.enableVibration(false);
//            }
//
//            return notificationChannel;
//        }
//    }

    /**
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(String channelId, String desc, String name, String channelGroupId, boolean byPassDnd, int lockScreenVisibility, boolean showBadge, int importance, Uri soundUri, long[] vibrateLong, int lightColor) {

        NotificationChannel notificationChannel = new NotificationChannel(channelId, name, importance);
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        notificationChannel.setShowBadge(showBadge);
        notificationChannel.setBypassDnd(byPassDnd);
        notificationChannel.setLockscreenVisibility(lockScreenVisibility);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setGroup(channelGroupId);
        notificationChannel.setSound(Uri.EMPTY, builder.build());

        if (!TextUtils.isEmpty(desc)) {
            notificationChannel.setDescription(desc);
        }

        if (soundUri != null) {

            notificationChannel.setSound(soundUri, builder.build());
        } else {
//                用此方法达到静音的效果 = = （因为在8.0的heads-up效果中importance必须会有声音 如果想静音且想有heads-up效果的话就必须传空值）
            notificationChannel.setSound(Uri.EMPTY, builder.build());
        }

        if (lightColor != -1) {
            notificationChannel.setLightColor(lightColor);
        } else {
            notificationChannel.enableLights(false);
        }

        if (vibrateLong != null) {
            notificationChannel.setVibrationPattern(vibrateLong);
        } else {
            notificationChannel.enableVibration(false);
        }
        this.notificationChannel = notificationChannel;

        notificationManager.createNotificationChannel(notificationChannel);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannelGroup(String channelGroupId, String channelGroupName) {
        this.channelGroupId = channelGroupId;
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(channelGroupId, channelGroupName);

        this.notificationChannelGroup = notificationChannelGroup;
        notificationManager.createNotificationChannelGroup(notificationChannelGroup);

    }

    public void notifyHeadUp(int noticationId, PendingIntent pendingIntent, @DrawableRes int largeIcon, @DrawableRes int smallIcon, String ticker, String subText, String contentTitle, String contentText) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationChannel.getImportance() < NotificationManager.IMPORTANCE_HIGH) {
                notificationChannel.setImportance(NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
//        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//
//        }
        builderNotification(pendingIntent, largeIcon, smallIcon, ticker, subText, contentTitle, contentText, NotificationCompat.PRIORITY_HIGH);


        currentNotiId = noticationId;

        notifyNotification();
    }


    //    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void notifyMessage(int noticationId, PendingIntent pendingIntent, @DrawableRes int largeIcon, @DrawableRes int smallIcon, String ticker, String subText, String contentTitle, String contentText, int priority) {

        builderNotification(pendingIntent, largeIcon, smallIcon, ticker, subText, contentTitle, contentText, priority);

        currentNotiId = noticationId;

        notifyNotification();

    }


    public void cancelNoti(int noticationId) {
        notificationManager.cancel(noticationId);
    }


    public void cancelCurrentNotification() {
        notificationManager.cancel(currentNotiId);
    }

    public void cancelAll() {
        notificationManager.cancelAll();
    }

    /**
     * 创建一个Notification 当安卓版本大于5.0且小于8.0的时候
     *
     * @param pendingIntent
     * @param largeIcon
     * @param smallIcon
     * @param ticker
     * @param subText
     * @param contentTitle
     * @param contentText
     */
    private void builderNotification(PendingIntent pendingIntent, @DrawableRes int largeIcon, @DrawableRes int smallIcon, String ticker, String subText, String contentTitle, String contentText, int priority) {

//    android 5.0以下 ticker 会在状态栏短暂显示一会上移消失 5.0以上 没有效果
        builder.setTicker(ticker);
        builder.setSubText(subText);
        builder.setContentTitle(contentTitle);
        builder.setContentText(contentText);

        builder.setSmallIcon(smallIcon);
        builder.setGroup(channelGroupId);
        builder.setChannelId(channelId);

//        builder.set
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon));

        builder.setContentIntent(pendingIntent);

        builder.setAutoCancel(true);

        builder.setWhen(System.currentTimeMillis());

//        builder.setPriority(priority);

        // 将Ongoing设为true 那么notification将不能滑动删除
        // notifyBuilder.setOngoing(true);
        /*
         * 从Android4.1开始，可以通过以下方法，设置notification的优先级，
		 * 优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
		 */
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        /*
         * Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
		 * Notification.DEFAULT_SOUND：系统默认铃声。
		 * Notification.DEFAULT_VIBRATE：系统默认震动。
		 * Notification.DEFAULT_LIGHTS：系统默认闪光。
		 * notifyBuilder.setDefaults(Notification.DEFAULT_ALL);
		 */
//        int defaults = 0;
//
//        if (sound) {
//            defaults |= Notification.DEFAULT_SOUND;
//        }
//        if (vibrate) {
//            defaults |= Notification.DEFAULT_VIBRATE;
//        }
//        if (lights) {
//            defaults |= Notification.DEFAULT_LIGHTS;
//        }
//
//        cBuilder.setDefaults(defaults);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
    }

    private void notifyNotification() {
        current = builder.build();
        notificationManager.notify(currentNotiId, current);
    }
}
