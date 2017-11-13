package ph.edu.dlsu.mobapde.copy_simon;

/**
 * Created by Abdul Bandlang on 11/13/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    public long addScore(Score score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Score.COLUMN_NAME,score.getName());
        cv.put(Score.COLUMN_POINTS,score.getPoints());
        long id=db.insert(Score.TABLE_NAME,null,cv);
        db.close();
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

    // getAllKoreans
    public Cursor getAllScoresCursor(){
        return getReadableDatabase().query(Score.TABLE_NAME, null,null,null,null,null,null);
    }
}
