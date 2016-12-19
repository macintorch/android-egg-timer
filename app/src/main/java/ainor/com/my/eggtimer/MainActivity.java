package ainor.com.my.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;
    CountDownTimer mCountDownTimer;

    public void resetTimer(){

        timerTextView.setText("0:05");
        timerSeekBar.setProgress(5);
        mCountDownTimer.cancel();
        controllerButton.setText("GO!");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;

    }

    public void updateTimer (int secondLeft) {
        int minutes = (int) secondLeft / 60;

        int seconds = secondLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
               secondString = "0" + secondString;
            }


        timerTextView.setText( Integer.toString(minutes) + ":" + secondString);
    }

    public void controlTimer (View view) {

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("STOP!");

            mCountDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    timerTextView.setText("0:00");

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        } else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controllerButton = (Button) findViewById(R.id.controllerButton);


        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        timerSeekBar.setMax(300);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
