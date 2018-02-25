package game.introduction.project.android.startanimation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by appu2 on 2018/02/25.
 */

public class InfoActivity extends AppCompatActivity {
//    koukoku
    //    広告
    private AdView mAdView;

    //    女の子
    private ImageView main_girl_view;
    private AnimationDrawable girl_nico_Anime;
//    リンゴボタン
    private Button ringo_button;
    private Intent intent_link;
//    音楽
    private MediaPlayer serif_player;
    private MediaPlayer info_bgm_player;
    private MediaPlayer select_player;
//    戻るボタン
    private Button ReturnButton;
    private Intent return_intent;
//    onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        //        広告
        MobileAds.initialize(this,"ca-app-pub-8822482186754971~1906465855\n");
//        バナー

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8822482186754971/2644832456\n");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest =  new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        女の子笑うアニメ呼び出し
        girl_smile_Anime();
//        リンゴボタンクリック
        click_ringo_button();
//        音楽
        serif_music();
        info_bgm_music();
//        戻るボタン
        click_return_button();
    }
    @Override
    protected  void onPause(){
        super.onPause();
        info_bgm_player.pause();
        serif_player.pause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        info_bgm_player.release();
        info_bgm_player = null;
        serif_player.release();
        serif_player = null;
    }
    @Override
    protected void onResume(){
        super.onResume();
        info_bgm_player.start();
        serif_player.start();
    }
//    音楽
    private void info_bgm_music(){
        info_bgm_player = MediaPlayer.create(this,R.raw.bgminfo);
        info_bgm_player.setLooping(true);
        info_bgm_player.setVolume(0.3f,0.3f);
        info_bgm_player.start();
    }
    private void serif_music(){
        serif_player = MediaPlayer.create(this,R.raw.serif);
        serif_player.setLooping(true);
        serif_player.setVolume(1.0f,1.0f);
        serif_player.start();
    }
//    女の子笑う
    private void girl_smile_Anime(){
        main_girl_view = findViewById(R.id.main_girl_view);
        main_girl_view.setBackgroundResource(R.drawable.niko_animation);
        girl_nico_Anime = (AnimationDrawable)main_girl_view.getBackground();
        girl_nico_Anime.start();
    }
//    リンクへ飛ぶ音楽
    private void link_tobu_music(){
        select_player = MediaPlayer.create(this,R.raw.select);
        select_player.start();
    }
//    リンゴボタン
    private void click_ringo_button(){
        ringo_button = findViewById(R.id.apple_button);
        ringo_button.setOnClickListener(new View.OnClickListener(){
          @Override
            public void onClick(View view){
              link_tobu_music();
              Uri uri = Uri.parse("http://web.editey.com/12eHhhTjECLHwskD42-d9FhCiNYMIuTtO/index.html");
                intent_link = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent_link);
          }
        });
    }
//    戻るボタン実装
    private void intent_return_button(){
        return_intent = new Intent(this,StartActivity.class);
        startActivity(return_intent);
    }
    private void click_return_button(){
        ReturnButton = findViewById(R.id.return_button);
        ReturnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent_return_button();
                link_tobu_music();
            }
        });
    }
}
