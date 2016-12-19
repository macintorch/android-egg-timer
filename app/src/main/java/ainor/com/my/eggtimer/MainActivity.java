package ainor.com.my.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;

    public void updateTimer (int secondLeft) {
        int minutes = (int) secondLeft / 60;

        int seconds = secondLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
               secondString = "0" + secondString;
            }


        timerTextView.setText( Integer.toString(minutes) + ":" + secondString);
    }

    public void controlTimer(View view) {
        new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long l) {
                updateTimer((int) l/1000);
            }

            @Override
            public void onFinish() {

                timerTextView.setText("0:00");
                Log.i("finished", "timer is done!");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
