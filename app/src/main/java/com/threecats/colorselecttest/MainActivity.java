package com.threecats.colorselecttest;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.threecats.colorselect.ColorSelectDialog;

public class MainActivity extends AppCompatActivity implements ColorSelectDialog.OnColorSelectListener {
    Button mColorButton;
    int mColor = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorButton = (Button)findViewById(R.id.button);
        mColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new ColorSelectDialog(MainActivity.this, mColor, MainActivity.this);
                d.show();
            }
        });
        updateColorButton();
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
