package com.col.commo.fightrats.end_game;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.col.commo.fightrats.R;
import com.col.commo.fightrats.dbutil.SingleSqlite;
import com.col.commo.fightrats.starting_game.MainActivity_diffcult;
import com.col.commo.fightrats.starting_game.MainActivity_easy;
import com.col.commo.fightrats.starting_game.MainActivity_normal;
import com.col.commo.fightrats.welcom_page.Welcome;

import static java.lang.System.exit;

/**
 * Created by commo on 2017/5/27.
 */

public class Finish extends Activity {

    private SingleSqlite dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.finish);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ImageView mouse_view = (ImageView) findViewById(R.id.mouse_grade);
        TextView view = (TextView) findViewById(R.id.textView1);

        dbhelper = new SingleSqlite(this,"singleRanking_table");

        int ifexists = dbhelper.ifexitsts();

        if(ifexists == 0){
            dbhelper.initDB();
            ifexists = 1;
        }

        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        if(bundle.getString("grade").equals("easy")){
            String jf = bundle.getInt("num")+"0";
            view.setText("你的积分："+jf);
            if(ifexists == 1){
                dbhelper.updateRanking("easy",jf);
            }
            mouse_view.setImageResource(R.drawable.easy_mouse);
            start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent inten = new Intent(Finish.this,MainActivity_easy.class);
                    startActivity(inten);
                    finish();
                }
            });
        }else if(bundle.getString("grade").equals("normal")){
            int jf = bundle.getInt("num");
            view.setText("你的积分："+jf);
            if(ifexists == 1){
                dbhelper.updateRanking("normal",String.valueOf(jf));
            }
            mouse_view.setImageResource(R.drawable.normal_mouse);
            start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent inten = new Intent(Finish.this,MainActivity_normal.class);
                    startActivity(inten);
                    finish();
                }
            });
        }else if(bundle.getString("grade").equals("diffcult")){
            String jf = bundle.getInt("num")+"0";
            view.setText("你的积分："+jf);
            if(ifexists == 1){
                dbhelper.updateRanking("diffcult",jf);
            }
            mouse_view.setImageResource(R.drawable.diffcult_mouse);
            start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent inten = new Intent(Finish.this,MainActivity_diffcult.class);
                    startActivity(inten);
                    finish();
                }
            });
        }
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finish.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(Finish.this)
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
