package ph.edu.dlsu.mobapde.copy_simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PostGameActivity extends AppCompatActivity {

    TextView tvScore, tvHighest;
    ImageView ivHighScores, ivPlay, ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);

        tvScore = findViewById(R.id.tv_score);
        tvHighest = findViewById(R.id.tv_highest);
        ivHighScores = findViewById(R.id.iv_highscores);
        ivPlay = findViewById(R.id.iv_play);
        ivHome = findViewById(R.id.iv_home);
        DatabaseHelper dbhelper=new DatabaseHelper(getBaseContext());
        Intent i = getIntent();
        int score = i.getExtras().getInt("Score");
        tvScore.setText(""+score);
        tvHighest.setText(""+dbhelper.getHighest());
        ivHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HighScoreActivity.class);
                startActivity(intent);
            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra(MainActivity.GAME_MODE, MainActivity.CLASSIC_MODE);

                startActivity(intent);
                finish();
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
