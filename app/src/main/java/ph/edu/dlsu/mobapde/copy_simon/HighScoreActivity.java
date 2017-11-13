package ph.edu.dlsu.mobapde.copy_simon;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        rvScore=(RecyclerView) findViewById(R.id.rvscore);
        dbhelper = new DatabaseHelper(getBaseContext());

        dbhelper.addScore(new Score("Tom",100));
        dbhelper.addScore(new Score("Jake",90));
        dbhelper.addScore(new Score("Hassan",80));
        dbhelper.addScore(new Score("Ching",70));
        dbhelper.addScore(new Score("Cho",60));
        dbhelper.addScore(new Score("Hans",50));
        dbhelper.addScore(new Score("Nick",40));
        dbhelper.addScore(new Score("Juan",30));
        dbhelper.addScore(new Score("Samael",20));
        dbhelper.addScore(new Score("Jamal",15));
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
