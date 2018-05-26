package com.col.commo.fightrats.welcom_page;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.col.commo.fightrats.R;

import static java.lang.System.exit;

/**
 * Created by commo on 2017/5/30.
 */

public class Starting extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.countdown);

        handler= new Handler(){

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch (msg.what) {
                    case 0x100:
                        FrameLayout ll=(FrameLayout)findViewById(R.id.framelayout1);
                        ll.setBackgroundResource(R.drawable.dds);
                        break;

                    default:
                        break;
                }
            }

        };
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for(int i=0;i<2;i++){
                    Message msg = Message.obtain();
                    msg.what = 0x100;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(Starting.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(Starting.this)
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
