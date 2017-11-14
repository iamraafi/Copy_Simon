package ph.edu.dlsu.mobapde.copy_simon;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView tvLevel, tvCountdown;
    ImageButton ivGreen, ivRed, ivYellow, ivBlue;
    Boolean playerLost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize variables and elements

        tvLevel = findViewById(R.id.tv_level);
        tvCountdown = findViewById(R.id.tv_countdown);

        ivGreen = findViewById(R.id.iv_green);
        ivRed = findViewById(R.id.iv_red);
        ivYellow = findViewById(R.id.iv_yellow);
        ivBlue = findViewById(R.id.iv_blue);

        playerLost = false;

        // TODO: get intent for game mode
        Intent gameIntent = getIntent();
        String gameMode = gameIntent.getExtras().getString(MainActivity.GAME_MODE);


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

    public void playGame(String gameMode){
        switch (gameMode){
            case MainActivity.CLASSIC_MODE:
                // TODO: loop rounds until user loses then go to high score
                while (!playerLost){

                    // (at the start of each round)
                    // TODO: generate new key
                    // TODO: set level # to size of list of keys
                    // TODO: animate the pattern and disable clicking of buttons while animating


                /*    Random random = new Random();

                    ArrayList<Integer> arrayList = new ArrayList<Integer>();

                    *//*for (i = 0; i < 50; i++) {
                        n = random.nextInt(4) + 1;
                        arrayList.add(i, n);
                        Log.i("BOOM", arrayList.get(i).toString());
                    }*/
                }
                break;
            case MainActivity.COOP_MODE:
                break;
            case MainActivity.SPEED_MODE:
                break;
            default:
                // invalid/unreleased game mode; notify and return to main activity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
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
