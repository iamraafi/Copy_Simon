package ph.edu.dlsu.mobapde.copy_simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button buttonClassic, buttonSpeedMode, buttonCoop;
    ImageView ivHighScores, ivHelp, ivSettings;

    public final static String GAME_MODE = "gameMode";
    public final static String CLASSIC_MODE = "classic";
    public final static String SPEED_MODE = "speed";
    public final static String COOP_MODE = "coop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClassic = findViewById(R.id.button_classic);
        buttonSpeedMode = findViewById(R.id.button_speed_mode);
        buttonCoop = findViewById(R.id.button_coop);
        ivHighScores = findViewById(R.id.iv_highscores);
        ivHelp = findViewById(R.id.iv_help);
        ivSettings = findViewById(R.id.iv_settings);

        // let user play TODO: allow other modes
        buttonClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra(GAME_MODE, CLASSIC_MODE);

                startActivity(intent);
            }
        });
        buttonSpeedMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra(GAME_MODE, SPEED_MODE);

                startActivity(intent);
            }
        });

        // view High scores
        ivHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HighScoreActivity.class);

                startActivity(intent);
            }
        });

        // view help/instructions
        ivHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), InstructionsActivity.class);
                startActivity(intent);
            }
        });

        // TODO: turn on/off sounds



    }
}
