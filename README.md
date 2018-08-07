# NotificationUtils
android 通知工具类 兼容8.0


#如何使用？
   *   初始化
```java
    NotificationUtils.ChannelBuilder channelBuilder = new NotificationUtils.ChannelBuilder("1号频道组", "1号频道", NotificationManager.IMPORTANCE_DEFAULT)
                .setChannelName("一号频道名字")
                .setByPassDnd(true) //是否绕过免打扰
                .setLightColor(Color.GREEN) //LED指示灯的颜色
                .setShowBadge(false) //是否在桌面显示未读消息数量
                .setEnableLight(true) //是否需要显示指示灯
                .setEnableSound(true) //是否开启铃声
                .setEnableVibrate(true) //是否开启震动
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC); //锁屏界面是否可见

    NotificationUtils  notificationUtils = new NotificationUtils(this, channelBuilder);

    notificationUtils.init("一号频道", "一号频道名字", "一号频道组", "一号频道组名字");

    
```

   * 发送一条简单的通知
   ```java
/**
     * 创建一个Notification
     *
     * @param pendingIntent 通知内容被点击处理
     * @param largeIcon
     * @param smallIcon
     * @param ticker ticker在5.0以上没有显示 在[4.0,5.0)在状态栏有短暂的停留
     * @param subText
     * @param contentTitle
     * @param contentText
     */
       notificationUtils.notifyMessage(notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);

```

* 发送浮动通知

```java
   /**
     * 浮动通知 在[5.0,8.0)的系统中浮动通知的产生条件
     * 是NotificationCompat.Builder中设置setPriority()的参数
     * 必须在NotificationCompat.PRIORITY_HIGH及以上并且有铃声或者震动才能有效果
     * 但在[8.0，8.0+)的时候因为NotificationChannel中的设置高于一切 所以
     * NotificationChannel中的importance必须要在NotificationManager.IMPORTANCE_HIGH及以上(!注意 此时会有默认的铃声和震动的效果哦～)
     * 5.0以下的系统就不支持啦
     *
     * @param noticationId
     * @param pendingIntent
     * @param largeIcon
     * @param smallIcon
     * @param ticker
     * @param subText
     * @param contentTitle
     * @param contentText
     * @param sound
     * @param vibrate
     * @param light
     */
    notificationUtils.notifyHeadUp(notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, sound, vibrate, light);

```

* 发送自定义布局的通知
```java
    RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_views_layout);
    remoteViews.setTextViewText(R.id.title, contentTitle);
    remoteViews.setTextViewText(R.id.content, contentText);
    twoUtils.notifyRemoteViews(remoteViews, notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);
       
```

* 更多···