package ph.edu.dlsu.mobapde.copy_simon;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView tvLevel, tvCountdown;
    ImageButton ivGreen, ivRed, ivYellow, ivBlue;

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

        /*
        ivBlue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ivBlue.setBackgroundResource(R.drawable.blue_lit_game_button);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    ivBlue.setBackgroundResource(R.drawable.blue_game_button);
                    //  *******start Intent here********
                }
                return false;
            }
        });*/
    }

    @Override
    public void onBackPressed() {

            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.LightDialogTheme);
            //builder.setCancelable(false);
            builder.setMessage("Do you want to Exit?");
            final AlertDialog alert = builder.create();
            /*
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();*/
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alert.dismiss();
                }
            });
            alert.show();

    }
}
