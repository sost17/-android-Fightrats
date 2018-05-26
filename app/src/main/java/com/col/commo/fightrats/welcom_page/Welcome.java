package com.col.commo.fightrats.welcom_page;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.col.commo.fightrats.R;

import static java.lang.System.exit;

/**
 * Created by commo on 2017/5/30.
 */

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    private ImageButton button1,button2,button3,returns,user,audio,help,leaderbroad;
    private TextView help_view;
    int flag = -1,music_isplaying = 0,help_show= 0;
    private static MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome);

        //音乐
        open_music();

        button1 = (ImageButton) findViewById(R.id.button1);
        button2 = (ImageButton) findViewById(R.id.button2);
        button3 = (ImageButton) findViewById(R.id.button3);
        returns = (ImageButton) findViewById(R.id.returns);
        user = (ImageButton) findViewById(R.id.user_btn);
        audio = (ImageButton) findViewById(R.id.audio);
        help = (ImageButton) findViewById(R.id.help);
        leaderbroad = (ImageButton) findViewById(R.id.leadrbroad);
        help_view = (TextView) findViewById(R.id.help_text);

        button1.setOnClickListener(this);
        returns.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        user.setOnClickListener(this);
        audio.setOnClickListener(this);
        help.setOnClickListener(this);
        leaderbroad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                if(flag == -1 ){
                    button1.setImageResource(R.drawable.easy_label);
                    button2.setImageResource(R.drawable.normal_label);
                    button3.setImageResource(R.drawable.diffcult_label);
                    returns.setImageResource(R.drawable.returns);
                    flag = 0;
                }else if(flag == 1){
                    button1.setImageResource(R.drawable.easy_label);
                    button2.setImageResource(R.drawable.normal_label);
                    button3.setImageResource(R.drawable.diffcult_label);
                    returns.setImageResource(R.drawable.returns);
                    flag = 2;
                }else if(flag == 0 || flag == 2){
                    Intent intent = new Intent(Welcome.this,Countdown.class);
                    intent.putExtra("grade","easy");
                    startActivity(intent);
                    finish();
                }

                break;
            case R.id.button2:
                if(flag == -1){
                    button1.setImageResource(R.drawable.internet_pk);
                    button2.setImageResource(R.drawable.wifi_pk);
                    button3.setImageResource(0);
                    returns.setImageResource(R.drawable.returns);
                    flag = 1;
                }else if(flag == 1){
                    button1.setImageResource(R.drawable.easy_label);
                    button2.setImageResource(R.drawable.normal_label);
                    button3.setImageResource(R.drawable.diffcult_label);
                    returns.setImageResource(R.drawable.returns);
                    flag = 2;
                }else if(flag == 0 || flag == 2){
                    Intent intent = new Intent(Welcome.this,Countdown.class);
                    intent.putExtra("grade","normal");
                    startActivity(intent);
                    finish();
                }

                break;
            case R.id.button3:
                if(flag == -1){
                    new AlertDialog.Builder(Welcome.this)
                            .setMessage("你确定不玩了！！！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    exit(0);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .create().show();
                }else if(flag == 0 || flag == 2){
                    Intent intent = new Intent(Welcome.this,Countdown.class);
                    intent.putExtra("grade","diffcult");
                    startActivity(intent);
                    finish();
                }

                break;
            case R.id.returns:
                if(flag == 0 || flag == 1){
                    button1.setImageResource(R.drawable.single_label);
                    button2.setImageResource(R.drawable.many_label);
                    button3.setImageResource(R.drawable.end_game);
                    returns.setImageResource(0);
                    flag = -1;
                }else if(flag == 2){
                    button1.setImageResource(R.drawable.internet_pk);
                    button2.setImageResource(R.drawable.wifi_pk);
                    button3.setImageResource(0);
                    returns.setImageResource(R.drawable.returns);
                    flag =1;
                }

                break;
            case R.id.user_btn:

                break;
            case R.id.audio:
                if(music_isplaying == 0){
                    audio.setImageResource(R.drawable.close_audio);
                    close_music();
                    music_isplaying = 1;
                } else if(music_isplaying == 1){
                    audio.setImageResource(R.drawable.open_audio);
                    open_music();
                    music_isplaying = 0;
                }
                break;
            case R.id.help:
                LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout_help);
                if(help_show == 0){
                    ll.setBackgroundResource(R.drawable.background_help);
                    help_view.setText("  这是个帮助！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
                    help_show = 1;
                }else if(help_show == 1){
                    ll.setBackgroundResource(0);
                    help_view.setText("");
                    help_show = 0;
                }

                break;
            case R.id.leadrbroad:
                Intent intent = new Intent(Welcome.this,Ranking.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }

    public void open_music(){
        if(mp != null){
            mp.release();
        }
        mp = MediaPlayer.create(Welcome.this, R.raw.starting);
        mp.start();
    }
    public void close_music(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp=null;
        }
    }

    @Override
    protected void onDestroy() {
        if(mp != null){
            mp.stop();
            mp.release();
            mp=null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(Welcome.this)
                    .setMessage("你确定不玩了！！！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            exit(0);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        }

        return false;
    }
}
