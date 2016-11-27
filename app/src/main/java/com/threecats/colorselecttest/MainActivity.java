package com.threecats.colorselecttest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.threecats.colorselect.ColorSelect;

public class MainActivity extends AppCompatActivity implements ColorSelect.ColorSelectListener {
    Button mColorButton;
    int mColor = Color.RED;
    static final String state_color = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mColor = savedInstanceState.getInt(state_color, Color.RED);
        }

        setContentView(R.layout.activity_main);

        mColorButton = (Button)findViewById(R.id.button);
        mColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelect newFrag = ColorSelect.newInstance(mColor);
                newFrag.show(getSupportFragmentManager(), "colorSelect");
            }
        });

        updateColorButton();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(state_color, mColor);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onNewColor(int color) {
        mColor = color;
        updateColorButton();
    }

    private void updateColorButton() {
        mColorButton.setBackgroundColor(mColor);
        float hsv[] = {0,0,0};
        Color.colorToHSV(mColor, hsv);
        mColorButton.setTextColor(hsv[2] > 0.5 ? 0xFF000000 : 0xFFFFFFFF );
        mColorButton.setText(String.format("#%06x", (0XFFFFFF & mColor)));
    }
}
