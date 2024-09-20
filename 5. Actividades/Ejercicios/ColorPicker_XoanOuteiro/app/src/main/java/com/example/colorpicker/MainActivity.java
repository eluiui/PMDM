package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;
    private SeekBar alphaBar;
    private TextView valueText;
    private TextView colorBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init picker bars
        this.redBar = findViewById(R.id.redBar);
        this.greenBar = findViewById(R.id.greenBar);
        this.blueBar = findViewById(R.id.blueBar);
        this.alphaBar = findViewById(R.id.alphaBar);

        //Init text views
        this.valueText = findViewById(R.id.valueText);
        this.colorBlock = findViewById(R.id.colorBlock);


        //Define listener for change
        SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int redVal = redBar.getProgress();
                int greenVal = greenBar.getProgress();
                int blueVal = blueBar.getProgress();
                int alphaVal = alphaBar.getProgress();

                String rgbShow = redVal + "/" + greenVal + "/" + blueVal + "/" + alphaVal;

                valueText.setText(rgbShow);
                colorBlock.setBackgroundColor(getColor(redVal,greenVal,blueVal,alphaVal));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        };

        //Assign listeners
        this.redBar.setOnSeekBarChangeListener(changeListener);
        this.greenBar.setOnSeekBarChangeListener(changeListener);
        this.blueBar.setOnSeekBarChangeListener(changeListener);
        this.alphaBar.setOnSeekBarChangeListener(changeListener);
    }

    private int getColor(int r, int g, int b, int a){

        return Color.argb(a,r,g,b);

    }

    //On change restoration


}