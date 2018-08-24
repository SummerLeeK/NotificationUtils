package com.fudaoculture.lee.notificationmanagerutils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RemoteViews;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button addMessage, cancelMessage, cancalAll, cancelSome, headsUp;

    private NotificationUtils notificationUtils, twoUtils;
    private NotificationUtils.ChannelBuilder channelBuilder, twoChannelBuilder;

    private PendingIntent pendingIntent;

    private PendingIntent leftPendingIntent, rightPendingIntent;

    private int notificationId;

    private String ticker, subText, contentTitle, contentText;

    private boolean sound, light, vibrate;
    private int curProgress = 0;
    private String longContent;

    private CheckBox soundRadio, lightRadio, vibrateRadio;

    private String picPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "1531989482398787.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        soundRadio = findViewById(R.id.sound);
        lightRadio = findViewById(R.id.light);
        vibrateRadio = findViewById(R.id.vibrate);

        soundRadio.setOnCheckedChangeListener(this);
        lightRadio.setOnCheckedChangeListener(this);
        vibrateRadio.setOnCheckedChangeListener(this);

        addMessage = findViewById(R.id.addMessage);
        cancelMessage = findViewById(R.id.cancelMessage);
        cancalAll = findViewById(R.id.cancalAll);
        cancelSome = findViewById(R.id.cancelSome);

        findViewById(R.id.big_pic).setOnClickListener(this);
        findViewById(R.id.big_text).setOnClickListener(this);
        findViewById(R.id.progress).setOnClickListener(this);
        findViewById(R.id.remoteviews).setOnClickListener(this);
        findViewById(R.id.twobutton).setOnClickListener(this);

        headsUp = findViewById(R.id.heads_up);

        addMessage.setOnClickListener(this);
        cancelMessage.setOnClickListener(this);
        cancalAll.setOnClickListener(this);
        cancelSome.setOnClickListener(this);
        headsUp.setOnClickListener(this);
        channelBuilder = new NotificationUtils.ChannelBuilder("1号频道组", "1号频道", "一号频道名字", NotificationManager.IMPORTANCE_DEFAULT)
                .setChannelName("一号频道名字").setByPassDnd(true).setLightColor(Color.GREEN)
                .setShowBadge(false).setEnableLight(true).setEnableSound(true).setEnableVibrate(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        notificationUtils = new NotificationUtils(this, channelBuilder);

        notificationUtils.init("一号频道", "一号频道名字", "一号频道组", "一号频道组名字");


        twoChannelBuilder = new NotificationUtils.ChannelBuilder("一号频道组", "二号频道", "二号频道名字", NotificationManager.IMPORTANCE_HIGH)
                .setChannelName("二号频道名字").setByPassDnd(true).setLightColor(Color.GREEN)
                .setShowBadge(false).setEnableLight(false).setEnableSound(false).setEnableVibrate(false)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        twoUtils = new NotificationUtils(this, twoChannelBuilder);

        twoUtils.init("二号频道", "二号频道名字", "一号频道组", "一号频道组名字");

//        FLAG_CANCEL_CURRENT:如果当前系统中已经存在一个相同的 PendingIntent 对象，那么就将先将已有的 PendingIntent 取消，然后重新生成一个 PendingIntent 对象。
//        FLAG_IMMUTABLE:创建的PendingIntent不可变，API23加入。
//        FLAG_NO_CREATE:如果当前系统中不存在相同的 PendingIntent 对象，系统将不会创建该 PendingIntent 对象而是直接返回 null 。
//        FLAG_ONE_SHOT:该 PendingIntent 只作用一次。
//        FLAG_UPDATE_CURRENT:如果系统中已存在该 PendingIntent 对象，那么系统将保留该 PendingIntent 对象，但是会使用新的 Intent 来更新之前 PendingIntent 中的 Intent 对象数据，例如更新 Intent 中的 Extras。

        pendingIntent = PendingIntent.getActivity(this, 100, new Intent(this, ContentActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        leftPendingIntent = PendingIntent.getActivity(this, 101, new Intent(this, FullScreenActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        rightPendingIntent = PendingIntent.getActivity(this, 102, new Intent(this, DeleteActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);


        notificationId = 0;
        ticker = "ticker";
        subText = "subText";
        contentTitle = "contentTitle";
        contentText = "contentText";
        longContent = "contentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentTextcontentText";

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMessage:
                notificationId += 1;
                twoUtils.notifyMessage(notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);

                break;
            case R.id.heads_up:
                notificationId += 1;
                twoUtils.notifyHeadUp(notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, sound, vibrate, light);
                break;
            case R.id.big_pic:
                notificationId += 1;

                Intent intent = new Intent();

//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.addCategory(Intent.CATEGORY_APP_GALLERY);
                intent.setAction(Intent.ACTION_VIEW);
                File file = new File(picPath);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri uriFile = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file);
                    intent.setDataAndType(uriFile, "image/*");
                } else {
                    intent.setDataAndType(Uri.fromFile(new File(picPath)), "image/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
                twoUtils.notifyBigPic(notificationId, pendingIntent1, R.mipmap.ic_launcher, picPath, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);


                break;
            case R.id.big_text:
                twoUtils.notifyBigText(notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, longContent, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);
                break;

            case R.id.progress:
                curProgress += 10;
                twoUtils.notifyProgress(notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, 100, curProgress);

                break;
            case R.id.twobutton:
                twoUtils.notifyButton(notificationId, pendingIntent, leftPendingIntent, 0, "full", rightPendingIntent, 0, "delete", R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, longContent, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);
                break;

            case R.id.remoteviews:
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_views_layout);
                remoteViews.setTextViewText(R.id.title, contentTitle);
                remoteViews.setTextViewText(R.id.content, contentText);
                twoUtils.notifyRemoteViews(remoteViews, notificationId, pendingIntent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, ticker, subText, contentTitle, contentText, NotificationCompat.PRIORITY_HIGH, sound, vibrate, light);
                break;
            case R.id.cancelMessage:
                notificationUtils.cancelCurrentNotification();
                break;
            case R.id.cancalAll:
                notificationUtils.cancelAll();
                break;
            case R.id.cancelSome:
                notificationUtils.cancelNoti(notificationId);
                notificationId -= 1;
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.sound:
                sound = b;
                break;
            case R.id.light:
                light = b;
                break;
            case R.id.vibrate:
                vibrate = b;
                break;

        }

    }
}
