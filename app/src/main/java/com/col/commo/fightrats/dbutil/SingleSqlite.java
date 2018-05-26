package com.col.commo.fightrats.dbutil;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by commo on 2017/6/3.
 */

public class SingleSqlite {
    private DatabaseHelper dbhelper;
    private Context mcontext;
    private String tableName;

    public SingleSqlite(Context context, String tableName){
        this.mcontext = context;
        dbhelper = new DatabaseHelper(context);
        this.tableName = tableName;
    }

    public int ifexitsts(){
        Cursor cu = dbhelper.getReadableDatabase().rawQuery("select count(*) from singleRanking_table",null);
        cu.moveToFirst();
        int ifexists = cu.getInt(0);
        cu.close();
        dbhelper.close();
        return ifexists;
    }

    public void initDB(){
        dbhelper.getReadableDatabase().execSQL("insert into singleRanking_table values('single',0,0,0)");
        dbhelper.close();
    }

    public void updateRanking(String column,String jf){
        if(Integer.parseInt(jf) > getRanking(column)){
            dbhelper.getReadableDatabase().execSQL("update singleRanking_table set " + column + " = ? where flag = 'single'",new String[]{jf});
            dbhelper.close();
        }
    }

    public int getRanking(String column){
        Cursor cu = dbhelper.getReadableDatabase().rawQuery("select " + column + " from singleRanking_table",null);
        cu.moveToFirst();
        int jf = cu.getInt(0);
        cu.close();
        dbhelper.close();
        return jf;
    }
}
