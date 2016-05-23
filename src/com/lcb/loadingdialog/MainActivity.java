package com.lcb.loadingdialog;

import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;

/**
 * 仿UC弹球的动画
 * 
 * @author lichuanbei
 * @date 2016-1-23 下午6:26:06
 */
public class MainActivity extends Activity {

	private int[] imageIds = { R.drawable.sanjiao, R.drawable.yuan, R.drawable.zhengfang };
	private int[] temp = { 0, 1, 2 };
	private ImageView iv;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.activity_img);
		float ivY = iv.getTranslationY();
		ObjectAnimator objTranslationY = ObjectAnimator.ofFloat(iv, "translationY", ivY - 150f, ivY + 150f, ivY - 150f);
		ObjectAnimator objRotation = ObjectAnimator.ofFloat(iv, "rotation", 0.0f, 720.0f);
		ObjectAnimator objRotationX = ObjectAnimator.ofFloat(iv, "rotationX", 0.0f, 720.0f);
		ObjectAnimator objRotationY = ObjectAnimator.ofFloat(iv, "rotationY", 0.0f, 720.0f);

		objRotation.setRepeatMode(ValueAnimator.RESTART);
		objRotation.setRepeatCount(10000);
		objTranslationY.setRepeatMode(ValueAnimator.REVERSE);
		objTranslationY.setRepeatCount(Integer.MAX_VALUE);
		objTranslationY.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				Random random = new Random();
				i = random.nextInt(3);
				iv.setImageResource(imageIds[i]);

			}

			@Override
			public void onAnimationEnd(Animator animation) {

			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}
		});
		AnimatorSet animator = new AnimatorSet();
		animator.setDuration(700);
		animator.play(objTranslationY).with(objRotation).with(objRotationX).with(objRotationY);
		animator.setInterpolator(new AnticipateInterpolator());
		animator.start();

	}

	// private void reSet(){
	// for(i=0;i<3;i++){
	// int temp=imageIds[i];
	// int index=(int) (Math.random()*2);
	// imageIds[i]=imageIds[index];
	// imageIds[index]=temp;
	// }
	// }

}
