package cn.rehtt.wanci;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static cn.rehtt.wanci.Game.recordResult;

public class pve_1 extends AppCompatActivity {
    private String[] word = new String[16];
    private TextView textView;
    TextView textView2;
    TextView textViewP;
    TextView textViewR;
    ImageView imageView;
    private static int severalTimes =1;
    Context context;

    private String recordresult="";
    int blood=5;
    int robotBlood=5;
    long starttime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pve_1);
        getSupportActionBar().hide();
        textView=(TextView)findViewById(R.id.textView10);

        textView2=(TextView)findViewById(R.id.textView11);
        textViewP=(TextView)findViewById(R.id.textView12);
        textViewR=(TextView)findViewById(R.id.textView13);
        imageView=(ImageView)findViewById(R.id.imageView16);
        context=this;
        getWord();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
               // String a =word[severalTimes];

//                textView.setText(a);
               // severalTimes++;
             //   Toast.makeText(context,"dianji kaishi",Toast.LENGTH_LONG).show();
              //  textView.setText(a);
                show();
               // textView.setText(a);
              //  while(blood!=0&&robotBlood!=0){








              //  }
              //  SpeechMode();


//                game.main();
            }
        });




    }

    OkHttpClient okHttpClient= new OkHttpClient();
    private void getWord(){
        String url="http://yellow948.cn/wanciwang/getSomeWord.php";
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();

                jsonToArray(res);
                Log.e("dsd",res);
            }
        });
    }
    private void jsonToArray(String res){

        try {
            JSONObject jsonObject = new JSONObject(res);
            for(int i=1;i<16;i++){
                String j = String.valueOf(i);
                word[i]=jsonObject.getString(j);

            }
//            show();
  //          textViw.setText("iiiiiiii");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void show(){

        String record=null;

        Toast.makeText(context,"kaishixianshi",Toast.LENGTH_LONG).show();
        //final String  a =word[severalTimes];

       // 显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(word[severalTimes]);

                        }
                    });

        starttime=new Date().getTime();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                long starttime=new Date().getTime();
//              //  long end=starDate2;
//
//
//
//            }
//        }).start();

        SpeechMode ();





        //      while(blood!=0&&robotBlood!=0){



                    //计时
               //     SimpleDateFormat sdf= new   SimpleDateFormat("hhmmss");
                    //String starDate1 = sdf.format(new Date());//获取游戏开始时间
                    //System.out.println("开始时间:"+starDate1);

//			System.out.println(Integer.parseInt(starDate1));
             //       long starDate2=new Date().getTime();
              //      long end=starDate2;


//
//                    while(  (end -starDate2)<=5000       ){
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                SpeechMode ();
//
//                            }
//                        }).start();
//                     //
//                      //  while(getRecordresult()!=null) {
//
//
//                       //     record = getRecordresult();
//                      //  }
//
//
//
//                        //	System.out.println("读到的单词:"+record);
////                        if(record.equals(a)){//对
////                            robotBlood--;
////                            break;
////                            //动画
////                            //词飞走
////                        }
////                        //错
////
////
////                        //	System.out.println("测试时间:"+new Date().getTime());
////                        end= new Date().getTime();
////                        //	System.out.println("开始时间:"+starDate2);
////                        //	System.out.println("结束时间:"+end);
////                        //	System.out.println("时间:"+(end-starDate2));
////                    }
////                    if(end-starDate2>5000){
////                        blood--;
////                        //动画
////                        //词飞
////                    }
////                    showBlood(blood,robotBlood);
//////
//////
//////
//              }//
//////
////                showBlood(blood,robotBlood);





    }

    public void gamepro(){

        long endtime=new Date().getTime();

      //  if(endtime-starttime<5000) {

            if (recordresult.equals(word[severalTimes++])) {
                robotBlood--;

            }else
            {
                blood--;
            }

        showBlood(blood,robotBlood);
        imageView.setVisibility(View.VISIBLE);

        if(blood==0){
            //弹出比赛结果
            Toast.makeText(context,"显示比赛结果",Toast.LENGTH_LONG).show();
            //打开开始按钮
            imageView.setVisibility(View.GONE);
        }
    //
     //       blood--;
    //    }


    }

    private void showBlood(final int p, final int r){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewP.setText("玩家血量"+p);
                textViewR.setText("机器血量"+r);
            }
        });
    }

    private String getRecordresult(){
        return recordresult;
    }

    public void SpeechMode(){
       // final String[] result0 = {""};
        SpeechUtility.createUtility(context, SpeechConstant.APPID +"=59e45c55");
        RecognizerDialog recognizerDialog = new RecognizerDialog(context,null);
        recognizerDialog.setParameter(SpeechConstant.LANGUAGE,"en_us");
        recognizerDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                if (!b) {
                    Log.i("讯飞识别的结果", recognizerResult.getResultString());
                    final String result = parseVoice(recognizerResult.getResultString());
                    recordresult=result;
                    gamepro();
                   // result0[0] =result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(result);
                        }
                    });
                    Log.e("99999999",result);
                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        recognizerDialog.show();
     //   return result0[0];
    }

    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }

    /**
     * 语音对象封装
     */
    public class Voice {

        public ArrayList<WSBean> ws;

        public class WSBean {
            public ArrayList<CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }



}
