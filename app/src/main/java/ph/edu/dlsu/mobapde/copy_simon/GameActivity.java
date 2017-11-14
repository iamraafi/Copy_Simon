package ph.edu.dlsu.mobapde.copy_simon;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        final Intent gameIntent = getIntent();
        final String gameMode = gameIntent.getExtras().getString(MainActivity.GAME_MODE);

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

        // call playGame and pass the gameMode

        ivGreen.setClickable(false);
        ivRed.setClickable(false);
        ivYellow.setClickable(false);
        ivBlue.setClickable(false);

        tvCountdown.setText("Start");
        tvCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trial(gameMode);
                // playGame(gameMode);
            }
        });
    }

    public void trial(String gameMode){
        tvLevel.setText("Level 1");
        playGame(gameMode);
    }

    public void playGame(String gameMode){

        SystemClock.sleep(1000);

        Log.i("playGame", "entered playGame");

        int newKey, timeDelay, remainingKeys;
        Random random = new Random();
        ArrayList<Integer> keyPatterns = new ArrayList<>();
        final ArrayList<ImageButton> gameButtons = new ArrayList<>();
        gameButtons.add(ivGreen);
        gameButtons.add(ivRed);
        gameButtons.add(ivYellow);
        gameButtons.add(ivBlue);
        ImageButton holderButton;
        //Handler handler = new Handler();

        Log.i("playGame", "initialized variables");

        playerLost = false;

        //animation definition
        Animation buttonBlink = new AlphaAnimation(1,0);
        buttonBlink.setDuration(500);
        buttonBlink.setInterpolator(new LinearInterpolator());
        buttonBlink.setRepeatCount(1);
        buttonBlink.setRepeatMode(Animation.REVERSE);


        // green == 1, red == 2, yellow == 3, blue == 4
        switch (gameMode){
            case MainActivity.CLASSIC_MODE:
                Log.i("playGame", "starting classic mode");
                // TODO: loop rounds until user loses then go to high score
                while (!playerLost){

                // (at the start of each round)
                    // generate new key and add to list
                        // place holder
                        keyPatterns.add(5);
                        for (int i=0; i<1; i++){
                            newKey = random.nextInt(4)+1;
                            keyPatterns.add(newKey);
                        }

                        Log.i("playGame", "generated new key");
                    // set level # to size of list of keys
                        int level = keyPatterns.size()-1;
                        tvLevel.setText("Level " + level);
                        tvLevel.invalidate();

                        Log.i("playGame", "set level");

                    //set remaining keys to size of list of keys
                        remainingKeys = level;
                        tvCountdown.setText(String.valueOf(remainingKeys));
                        tvCountdown.invalidate();
                        Log.i("playGame", "set initial keys");

                    // disable clicking of buttons
                        for (int i=0; i<gameButtons.size(); i ++){
                            gameButtons.get(i).setClickable(true); // TODO: Change to false
                        }
                        Log.i("playGame", "set unclickable");


                    // TODO: animate the pattern
                        Integer[] keyIntArray = keyPatterns.toArray(new Integer[keyPatterns.size()]);
                        new PatternTask().execute(keyIntArray);


                    // TODO: re-enable clicking of buttons

                        for (int i=0; i<gameButtons.size(); i ++){
                            gameButtons.get(i).setClickable(true);
                        }
                        Log.i("playGame", "set clickable");

                    // TODO: if user pressed wrong button: lose
                        if(true){
                            playerLost = true;
                        }
                        Log.i("playGame", "player has lost");
                }
                break;
            /*case MainActivity.SPEED_MODE:
                break;*/
            /*case MainActivity.COOP_MODE:
                break;*/
            default:
                // invalid/unreleased game mode; notify and return to main activity
                Log.i("playGame", "invalid/unreleased game mode");
                Toast invalidModeToast = new Toast(getBaseContext());
                invalidModeToast.setText("Invalid//unreleased game mode");
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public class PatternTask extends AsyncTask<Integer, Integer, Void> {

        int i = 0;
        boolean delayCheck = false;
        Integer[] pattern, delayArray;

        @Override
        protected Void doInBackground(Integer... integers) {
            pattern = integers;
            delayArray = new Integer[1];
            delayArray[0] = 5;
            while (i<pattern.length-1){
                if(!delayCheck){
                    i++;
                    delayCheck = true;
                    publishProgress(pattern[i]);
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    publishProgress(delayArray[0]);
                    delayCheck = false;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            publishProgress(delayArray[0]);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


            switch (values[0]){
                case 1: ivGreen.setBackground(getDrawable(R.drawable.green_lit_game_button)); break;
                case 2: ivRed.setBackground(getDrawable(R.drawable.red_lit_game_button)); break;
                case 3: ivYellow.setBackground(getDrawable(R.drawable.yellow_lit_game_button)); break;
                case 4: ivBlue.setBackground(getDrawable(R.drawable.blue_lit_game_button)); break;

                case 5: ivGreen.setBackground(getDrawable(R.drawable.green_game_button));
                        ivRed.setBackground(getDrawable(R.drawable.red_game_button));
                        ivYellow.setBackground(getDrawable(R.drawable.yellow_game_button));
                        ivBlue.setBackground(getDrawable(R.drawable.blue_game_button));
                        break;
            }

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
