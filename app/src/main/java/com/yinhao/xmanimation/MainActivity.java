package com.yinhao.xmanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview);
    }

    public void move(View view) {
        //更加丰富的控制效果
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "translationY", 0f, 200f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        AnimatorSet set = new AnimatorSet();
        //set.playTogether(animator1, animator2, animator3);
        //set.playSequentially(animator1, animator2, animator3);
        set.play(animator1).with(animator2);
        set.play(animator2).after(animator3);
        set.setDuration(1000);
        set.start();
    }

    public void click(View view) {
        Log.i(TAG, "click: 点击了imageview");
    }

    public void buzhidao() {
        //优化
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", 0f, 200f);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", 0f, 200f);
        ObjectAnimator.ofPropertyValuesHolder(imageView, p1, p2, p3).setDuration(1000).start();

        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 0);
        ta.setDuration(1000);
        ta.setFillAfter(true);
        imageView.startAnimation(ta);

        ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f).setDuration(1000).start();//1 操纵的对象 2 所需要操纵的对象属性 3 动画变化的范围
        ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f).setDuration(1000).start();//异步过程
        ObjectAnimator.ofFloat(imageView, "translationY", 0f, 200f).setDuration(1000).start();

    }
}
