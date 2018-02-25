package game.introduction.project.android.startanimation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by appu2 on 2018/02/24.
 */

public class StartActivity extends AppCompatActivity {
//    全体
    private RelativeLayout layout_main_start;
//    女の子が歩くアニメ
    private ImageView default_walk_image;
    private AnimationDrawable animationWalkGirl;
    private TranslateAnimation jumpTransAnime;
//    太陽が回るアニメ
    private RotateAnimation routeSunAnimation;
    private ImageView sumImageView;
//    くも
    private ImageView kumoView1;
    private ImageView kumoView2;
    private ImageView kumoView3;
    private ImageView kumoView4;
    private ImageView kumoView5;
    private TranslateAnimation kumoTransAnimation;
    private ImageView[] ImageList;
//    音楽
    private MediaPlayer flowerPlayer;
    private MediaPlayer TouchPlayer;
//    セリフ
    private MediaPlayer syoukaiPlayer;
//    広告
    private AdView mAdView;
//    タッチ
    private ImageView touch_love_view;
//    インテント
    private Intent infoIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
//        広告
        MobileAds.initialize(this,"ca-app-pub-8822482186754971~1906465855\n");
//        バナー

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8822482186754971/2644832456\n");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest =  new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        女の子が歩くアニメーション呼び出し
        default_girl_walk();
//        太陽アニメーション呼び出し
         sunrotate();
//         雲動くアニメーション
       kumo_move_animation();

//         音楽を鳴らす
        flowerPlayer_sound();
//        コメントを繰り返す
        comment_sound();
    }
//    画面が表示されるたび
    @Override
    protected void onResume(){
        super.onResume();
        flower_start_bgm();
    }
//    画面が非表示になる旅
    @Override
    protected void onPause(){
        super.onPause();
        flower_bgm_stop();
        syoukaiPlayer.stop();
    }
//    アプリが終了
    @Override
    protected void onDestroy(){
        super.onDestroy();
        flower_bgm_bye();
    }
//    せりふ
    private void comment_sound(){

        syoukaiPlayer = MediaPlayer.create(this,R.raw.comment);
        syoukaiPlayer.setVolume(1.0f,1.0f);
        syoukaiPlayer.setLooping(true);
        syoukaiPlayer.start();

    }
//    音楽
    private void flowerPlayer_sound(){
        flowerPlayer = MediaPlayer.create(this,R.raw.flower);
        flowerPlayer.setVolume(0.3f,0.3f);
        flowerPlayer.setLooping(true);
        flowerPlayer.start();
    }
//    音楽開始
    private void flower_start_bgm(){
        flowerPlayer.start();
        syoukaiPlayer.start();
    }
//    音楽停止
    private void flower_bgm_stop(){
        flowerPlayer.pause();
        syoukaiPlayer.pause();
    }
//    音楽メモリ解散
    private void flower_bgm_bye(){
        flowerPlayer.release();
        flowerPlayer = null;
        syoukaiPlayer.release();
        syoukaiPlayer = null;
    }

//    アニメーション
//    女の子が歩くアニメーション
    private void default_girl_walk(){
        default_walk_image = findViewById(R.id.girl_walk_image);
//        jumpTransAnime = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0,
//                                                    TranslateAnimation.RELATIVE_TO_SELF,0,
//                                                    TranslateAnimation.RELATIVE_TO_SELF,-1,
//                                                    TranslateAnimation.RELATIVE_TO_SELF,0);
        jumpTransAnime = new TranslateAnimation(0,0,0,80);
        jumpTransAnime.setDuration(1000);
        jumpTransAnime.setRepeatMode(Animation.REVERSE);
        jumpTransAnime.setRepeatCount(Animation.INFINITE);
        default_walk_image.startAnimation(jumpTransAnime);

        default_walk_image.setBackgroundResource(R.drawable.girl_walk_animation);
        animationWalkGirl = (AnimationDrawable)default_walk_image.getBackground();
        animationWalkGirl.start();
    }
//    太陽傾くアニメーション
    private void sunrotate(){
        sumImageView = findViewById(R.id.sun_Image);
        routeSunAnimation = new RotateAnimation(-20,20,sumImageView.getWidth()/2,sumImageView.getHeight()/2);
        routeSunAnimation.setDuration(3000);
        routeSunAnimation.setRepeatMode(Animation.REVERSE);
        routeSunAnimation.setRepeatCount(Animation.INFINITE);
        sumImageView.startAnimation(routeSunAnimation);

    }
//    雲が動くアニメーション
    private void kumo_move_animation(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                layout_main_start = findViewById(R.id.layout_start);
                kumoTransAnimation = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_PARENT,-1,
                        TranslateAnimation.RELATIVE_TO_PARENT,1,
                        TranslateAnimation.RELATIVE_TO_PARENT,0,
                        TranslateAnimation.RELATIVE_TO_PARENT,0);
//        kumoTransAnimation.setRepeatMode(Animation.REVERSE);
//        kumoTransAnimation.setRepeatCount(Animation.INFINITE);
                kumoTransAnimation.setDuration(6000);


                kumoView1 = findViewById(R.id.kumoview1);
                kumoView2 = findViewById(R.id.kumoview2);
                kumoView3 = findViewById(R.id.kumoview3);
                kumoView4 = findViewById(R.id.kumoview4);
                kumoView5 = findViewById(R.id.kumoview5);
                ImageList = new ImageView[]{kumoView1,kumoView2,kumoView3,kumoView4,kumoView5};
                for(ImageView animeImage: ImageList){
                    animeImage.startAnimation(kumoTransAnimation);
                }
                kumo_move_animation();

            }
        },3000);

    }
//    タッチイベント
    @Override
    public boolean onTouchEvent(MotionEvent event){

        TouchPlayer = MediaPlayer.create(this,R.raw.ok);
        TouchPlayer.start();
        touch_love_view = findViewById(R.id.touch_loveView);
        touch_love_view.setImageResource(R.drawable.love);
        int width = touch_love_view.getWidth()/2;
        int height = touch_love_view.getHeight()/2;
        touch_love_view.setX(event.getX() - width);
        touch_love_view.setY(event.getY() - height);
//        info_activityに移動する
        infoIntent = new Intent(this,InfoActivity.class);
        startActivity(infoIntent);
        Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
        return true;
    }

}
