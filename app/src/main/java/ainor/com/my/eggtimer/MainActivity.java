package ainor.com.my.eggtimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);

        final TextView timerTextView = (TextView) findViewById(R.id.timerTextView);

        timerSeekBar.setMax(300);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int minutes = (int) i / 60;

                int seconds = i - minutes * 60;

                String secondString = Integer.toString(seconds);

                if (secondString == "0") {
                    secondString = "00";
                }

                timerTextView.setText( Integer.toString(minutes) + ":" + secondString);

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
