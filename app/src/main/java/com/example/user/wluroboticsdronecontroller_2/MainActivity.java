package com.example.user.wluroboticsdronecontroller_2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {


    private TextView mTextViewAngleLeft;
    private TextView mTextViewStrengthLeft;

    private TextView mTextViewAngleRight;
    private TextView mTextViewStrengthRight;
    private TextView mTextViewCoordinateRight;
    boolean connection = true;
    private ImageView connectImageView = findViewById(R.id.connection_status);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewAngleLeft = (TextView) findViewById(R.id.textView_angle_left);
        mTextViewStrengthLeft = (TextView) findViewById(R.id.textView_strength_left);

        JoystickView joystickLeft = (JoystickView) findViewById(R.id.joystickView_left);
        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTextViewAngleLeft.setText(angle + "°");
                mTextViewStrengthLeft.setText(strength + "%");
                if (45 < angle && angle < 135) {
                    // send strength to arduino ascending upwards
                }
                else if (225 < angle && angle < 315) {
                    // send strength to arduino descending downwards
                }
                else if (135 < angle && angle < 225) {
                    // send strength to arduino turning clockwise/to the left
                }
                else if ((0 < angle && angle < 45) || (315 < angle && angle < 360)) {
                    // send strength to arduino turning counterclockwise/to the right
                }
            }
        });


        mTextViewAngleRight = (TextView) findViewById(R.id.textView_angle_right);
        mTextViewStrengthRight = (TextView) findViewById(R.id.textView_strength_right);
        mTextViewCoordinateRight = findViewById(R.id.textView_coordinate_right);

        final JoystickView joystickRight = (JoystickView) findViewById(R.id.joystickView_right);
        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onMove(int angle, int strength) {
                mTextViewAngleRight.setText(angle + "°");
                mTextViewStrengthRight.setText(strength + "%");
                mTextViewCoordinateRight.setText(
                        String.format("x%03d:y%03d",
                                joystickRight.getNormalizedX(),
                                joystickRight.getNormalizedY())
                );
                if (45 < angle && angle < 135) {
                    // send strength to arduino moving forwards
                }
                else if (225 < angle && angle < 315) {
                    // send strength to arduino moving backwards
                }
                else if (135 < angle && angle < 225) {
                    // send strength to arduino moving left
                }
                else if ((0 < angle && angle < 45) || (315 < angle && angle < 360)) {
                    // send strength to arduino moving right
                }
            }
        });

        if (connection == true) {
            connectImageView.setImageResource(R.drawable.connection_estab);

        }

        else {
            connectImageView.setImageResource(R.drawable.connection_denied);

        }



    }
}
