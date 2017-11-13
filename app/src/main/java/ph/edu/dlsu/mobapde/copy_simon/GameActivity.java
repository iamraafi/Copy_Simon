package ph.edu.dlsu.mobapde.copy_simon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView tvLevel, tvCountdown;
    ImageView ivGreen, ivRed, ivYellow, ivBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvLevel = findViewById(R.id.tv_level);
        tvCountdown = findViewById(R.id.tv_countdown);
        ivGreen = findViewById(R.id.iv_green);
        ivRed = findViewById(R.id.iv_red);
        ivYellow = findViewById(R.id.iv_yellow);
        ivBlue = findViewById(R.id.iv_blue);
    }
}
