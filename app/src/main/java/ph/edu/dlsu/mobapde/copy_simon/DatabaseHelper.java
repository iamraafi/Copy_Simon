package ph.edu.dlsu.mobapde.copy_simon;

/**
 * Created by Abdul Bandlang on 11/13/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String SCHEMA="appdb";
    public static final int VERSION=1;
    public DatabaseHelper(Context context){
        super(context,SCHEMA,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + Score.TABLE_NAME + " ("
                + Score.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Score.COLUMN_NAME + " TEXT,"
                + Score.COLUMN_POINTS + " INTEGER"
                + ");";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + Score.TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        // call onCreate
        onCreate(sqLiteDatabase);
    }
    private boolean cleanup(){
        if(getCount()>10){
            SQLiteDatabase db=this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT max("+Score.COLUMN_ID+")'max' FROM "+Score.TABLE_NAME,null);
            int s=0 ;
            if(c.moveToFirst()){
                s = c.getInt(c.getColumnIndex("max"));
            }
            c.close();
            int rowsAffected=db.delete(Score.TABLE_NAME,Score.COLUMN_ID+"=?",new String[]{s+""});
            db.close();
            return rowsAffected>0;
        }
        return true;
    }
    private Score shiftScore(Score score,Score original){
        if(original.getPoints()>=score.getPoints())
            return score;
        else{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Score.COLUMN_NAME,score.getName());
            cv.put(Score.COLUMN_POINTS,score.getPoints());
            db.update(Score.TABLE_NAME,cv,
                    Score.COLUMN_ID+"=?",
                    new String[]{original.getId()+""});
            db.close();
            return original;
        }

    }

    public long addScore(Score score){
        ArrayList<Score> slist=getScoresList();
        for(Score curr:slist){
            score=shiftScore(score,curr);
        }
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Score.COLUMN_NAME,score.getName());
        cv.put(Score.COLUMN_POINTS,score.getPoints());
        long id=db.insert(Score.TABLE_NAME,null,cv);
        db.close();
        cleanup();
        return id;
    }

    public Score getScore(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Score.TABLE_NAME,
                null,
                Score.COLUMN_ID + "=?",
                new String[]{ id+"" },
                null,
                null,
                null);
        Score s = null;
        if(c.moveToFirst()){
            s = new Score(c.getString(c.getColumnIndex(Score.COLUMN_NAME)),
                    c.getInt(c.getColumnIndex(Score.COLUMN_POINTS)));
        }

        c.close();
        db.close();

        return s;
    }
    public int getCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*)'count' FROM "+Score.TABLE_NAME,null);
        int s=0 ;
        if(c.moveToFirst()){
            s = c.getInt(c.getColumnIndex("count"));
        }
        c.close();
        db.close();
        return s;
    }
    private ArrayList<Score> getScoresList(){
        ArrayList<Score> slist=new ArrayList<Score>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Score.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        //Score s = null;
        /*
        if(c.moveToFirst()){
            s = new Score(c.getString(c.getColumnIndex(Score.COLUMN_NAME)),
                    c.getInt(c.getColumnIndex(Score.COLUMN_POINTS)));
        }*/
        try{
            while(c.moveToNext()){
                Score s =new Score(c.getString(c.getColumnIndex(Score.COLUMN_NAME)),
                        c.getInt(c.getColumnIndex(Score.COLUMN_POINTS)));
                s.setId(c.getInt(c.getColumnIndex(Score.COLUMN_ID)));
                slist.add(s);
            }
        }finally {
            c.close();
        }

        c.close();
        db.close();

        return slist;
    }

    // getAllKoreans
    public Cursor getAllScoresCursor(){
        return getReadableDatabase().query(Score.TABLE_NAME, null,null,null,null,null,null);
    }
}
