package com.example.toshiba.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

/**
 * Created by Sarah Golder on 2/12/2018.
 */

public class Listener implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {
    private SeekBar redSeek = null;
    private SeekBar greenSeek = null;
    private SeekBar blueSeek = null;
    private RadioButton hairRad = null;
    private RadioButton eyesRad = null;
    private RadioButton skinRad = null;
    private Face face = null;
    //private Button randButton = null;
    String faceFeat;

    @Override
    public void onClick(View v) {
        //Randomize button listener
        Button cur = (Button)v; //recently clicked button
        String curLabel = (String)cur.getText();
        int[] colorArray = new int[3];
        Log.i("Listener", "onClick curLabel: "+curLabel);

        if(curLabel.equalsIgnoreCase("Generate Random Face")){
            //randButton = cur;
            face.randomize();
        }

        //RadioGroup
        if( skinRad.isChecked() )
        {
            colorArray = face.getSkinArray();
            updateSeekBars(colorArray);
            faceFeat = "Skin";
        }
        //SeekBar setProgress based on selection

        face.update();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        SeekBar cur = (SeekBar) seekBar;
        int curId = cur.getId();
        //Insert external citation
        String seekLabel = cur.getResources().
                getResourceEntryName(curId);

        if(seekLabel.equals("seekBarRed")){
            face.setColorVal(progress, 0, faceFeat);
        }
        else if(seekLabel.equals("seekBarGreen")){
            face.setColorVal(progress, 1, faceFeat);
        }
        else if(seekLabel.equals("seekBarBlue")){
            face.setColorVal(progress, 2, faceFeat);
        }

        face.update();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void addViews(SurfaceView initface, SeekBar[] seekBars,
                         RadioButton[] radGroup){
        face = (Face)initface;
        redSeek = seekBars[0];
        greenSeek = seekBars[1];
        blueSeek = seekBars[2];
        skinRad = radGroup[2];

    }

    public void updateSeekBars(int[] colorArray){
        redSeek.setProgress(colorArray[0]);
        greenSeek.setProgress(colorArray[1]);
        blueSeek.setProgress(colorArray[2]);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Will not use
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Will not use
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Nothing will display
    }
}
