package com.col.commo.fightrats.welcom_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.col.commo.fightrats.R;
import com.col.commo.fightrats.dbutil.SingleSqlite;

/**
 * Created by commo on 2017/6/4.
 */

public class Ranking extends AppCompatActivity {
    private ImageView easy,normal,diffcult;
    private ImageButton back;
    private TextView text_easy,text_normal,text_diffcult;
    private SingleSqlite dbhelper;
    int jf_easy,jf_normal,jf_diffcult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ranking);

        dbhelper = new SingleSqlite(this,"singleRanking_table");
        db_con();

        initview();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ranking.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void initview(){
        easy = (ImageView) findViewById(R.id.easy);
        normal = (ImageView) findViewById(R.id.normal);
        diffcult = (ImageView) findViewById(R.id.diffcult);
        text_easy = (TextView) findViewById(R.id.text_easy);
        text_normal = (TextView) findViewById(R.id.text_normal);
        text_diffcult = (TextView) findViewById(R.id.text_diffcult);
        back = (ImageButton) findViewById(R.id.back);
        back.setImageResource(R.drawable.returns);
        easy.setImageResource(R.drawable.easy_label);
        normal.setImageResource(R.drawable.normal_label);
        diffcult.setImageResource(R.drawable.diffcult_label);
        text_easy.setText(String.valueOf(jf_easy));
        text_normal.setText(String.valueOf(jf_normal));
        text_diffcult.setText(String.valueOf(jf_diffcult));
    }

    public void db_con(){
        jf_easy = dbhelper.getRanking("easy");
        jf_normal = dbhelper.getRanking("normal");
        jf_diffcult = dbhelper.getRanking("diffcult");

    }
}
