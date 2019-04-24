package com.wenlong.hope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;

public class splashActivity extends Activity{
    protected  boolean active = true;
    protected int splashTime = 2000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);

       Thread spreadTread = new Thread() {
           @Override
           public void run() {
               try {
                   int waited = 0;
                   while(active && waited < splashTime) {
                       sleep(100);
                       if(active) {
                           waited += 100;
                       }
                   }
               }catch (Exception e) {

               }finally {
                   startActivity(new Intent(splashActivity.this,MainActivity.class));
                   finish();
               }
           };
       };
       spreadTread.start();
    }
}
