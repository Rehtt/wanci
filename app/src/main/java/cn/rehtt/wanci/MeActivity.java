package cn.rehtt.wanci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MeActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_me);
        getSupportActionBar().hide();
        imageView=(ImageView)findViewById(R.id.imageView11);
        imageView2=(ImageView)findViewById(R.id.imageView12);
        imageView3=(ImageView)findViewById(R.id.imageView13);
        imageView4=(ImageView)findViewById(R.id.imageView14);
        imageView5=(ImageView)findViewById(R.id.imageView15);
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.GONE);
        imageView5.setVisibility(View.GONE);
    }

    //个人信息
    public void grxx(View view) {
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.GONE);
        imageView5.setVisibility(View.GONE);
    }

    //好友列表
    public void hylb(View view) {
        imageView.setVisibility(View.GONE);
        imageView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.GONE);
        imageView5.setVisibility(View.GONE);
    }

    //个人榜
    public void grb(View view) {
        imageView.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.GONE);
        imageView5.setVisibility(View.GONE);
    }

    //系统设置
    public void xtsz(View view) {
        imageView.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.VISIBLE);
        imageView5.setVisibility(View.GONE);
    }

    //更多
    public void gd(View view) {
        imageView.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.GONE);
        imageView5.setVisibility(View.VISIBLE);
    }
}
