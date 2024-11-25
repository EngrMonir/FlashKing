package com.allien.torch;

import static java.nio.channels.SocketChannel.open;

import android.content.Context;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Parameter;
import java.security.Policy;

public class MainActivity extends AppCompatActivity {


    ToggleButton Switch;
    RelativeLayout light;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch = findViewById(R.id.Switch);
        light = findViewById(R.id.light);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        FlashON();


//====================
    Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            CameraManager camManager = null;
            String cameraId = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && b) {
                Switch.setBackgroundResource(R.drawable.power_off);
                light.setVisibility(View.GONE);
                camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                cameraId = null;

                try {
                    cameraId = camManager.getCameraIdList()[0];
                } catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }
                try {
                    camManager.setTorchMode(cameraId, false);

                } catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }

            } else {

                FlashON();
          }

        }
    });
//====================






    }
 //====================
   public void FlashON (){

       CameraManager camManager = null;
       String cameraId = null;

       Switch.setBackgroundResource(R.drawable.power_on);
       light.setVisibility(View.VISIBLE);

       camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
       cameraId = null;

       try {
           cameraId = camManager.getCameraIdList()[0];
       } catch (CameraAccessException e) {
           throw new RuntimeException(e);
       }
       try {
           camManager.setTorchMode(cameraId, true);

       } catch (CameraAccessException e) {
           throw new RuntimeException(e);
       }

   }
//====================





   }
