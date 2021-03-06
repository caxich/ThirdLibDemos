package com.cxc.viewanimationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        Button btn_ani = (Button)findViewById(R.id.btn_ani);
        //XML文件实现的动画
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_text);
//        btn_ani.startAnimation(animation);
        //代码实现的动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        btn_ani.startAnimation(alphaAnimation);
    }

}
