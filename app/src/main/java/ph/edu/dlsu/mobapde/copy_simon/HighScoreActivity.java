package ph.edu.dlsu.mobapde.copy_simon;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {
    RecyclerView rvScore;
    DatabaseHelper dbhelper;
    ScoreAdapter sa;
    public static boolean INITIALIZED=false;
    private void intialize(DatabaseHelper db){
        SharedPreferences shp=
                PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e =shp.edit();
        String in=shp.getString("initialized",null);
        if(in!=null)
            return;

        dbhelper.addScore(new Score("Samantha",150));
        dbhelper.addScore(new Score("Jason",90));
        dbhelper.addScore(new Score("Ahmed",110));
        dbhelper.addScore(new Score("Leon",100));
        dbhelper.addScore(new Score("Carlo",50));
        dbhelper.addScore(new Score("Vincent",100));
        dbhelper.addScore(new Score("Tomas",60));
        dbhelper.addScore(new Score("Hansel",90));
        dbhelper.addScore(new Score("Urich",20));
        dbhelper.addScore(new Score("Thomson",15));
        e.putString("initialized","yes");
        e.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        rvScore=(RecyclerView) findViewById(R.id.rvscore);
        dbhelper = new DatabaseHelper(getBaseContext());
        intialize(dbhelper);
        Log.d("Count of scores", " value of "+dbhelper.getCount());
        sa=new ScoreAdapter(getBaseContext(),
                dbhelper.getAllScoresCursor());

        /*
        ArrayList<Score> SList=new ArrayList<Score>();
        SList.add(new Score("Tom",100));
        SList.add(new Score("Jake",90));
        SList.add(new Score("Hassan",80));
        SList.add(new Score("Ching",70));
        SList.add(new Score("Cho",60));
        SList.add(new Score("Hans",50));
        SList.add(new Score("Nick",40));
        SList.add(new Score("Juan",30));
        SList.add(new Score("Samael",20));
        SList.add(new Score("Jamal",15));
        ScoreAdapter sa= new ScoreAdapter(SList);
        */
        rvScore.setAdapter(sa);

        rvScore.setLayoutManager(new LinearLayoutManager(
                //getBaseContext()
                this, LinearLayoutManager.VERTICAL, false));

    }

}
