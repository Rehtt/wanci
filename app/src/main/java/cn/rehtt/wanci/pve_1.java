package cn.rehtt.wanci;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import cn.rehtt.wanci.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class pve_1 extends AppCompatActivity {
    private String[] word = new String[16];
    private TextView textView;
    TextView textView2;
    TextView textViewP;
    TextView textViewR;
    ImageView imageView;
    private static int severalTimes =1;     //记录测试
    Context context;

    String word_r="";       //装载单词对比结果，错误为0，正确为1。eg: word_r="apple , 0 , word , 1"

    private String recordresult="";       //语音识别结果
    int blood=5;              //初始玩家血量
    int robotBlood=5;         // 初始电脑血量
    long starttime;           //时间

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
        handler.sendEmptyMessage(1);         //显示获取词库

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
                for(int i=0;i<5;i++)
                show();
                // textView.setText(a);
                //  while(blood!=0&&robotBlood!=0){








                //  }
                //  SpeechMode();


//                game.main();
            }
        });




    }


    private void getWord(){
        OkHttpClient okHttpClient= new OkHttpClient();
        String url="https://wanci.rehtt.cn/wanciwang/getSomeWord.php";
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("getWord()","no");
                Log.e("getWord()",e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                jsonToArray(res);
                handler.sendEmptyMessage(0);    //关闭获取词库框
                Log.e("getWord()",res);
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

//        SpeechMode ();
        recordresult="as";
        gamepro();
        // result0[0] =result;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView2.setText(recordresult);
            }
        });




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

        if (recordresult.equals(word[severalTimes])) {
            word_r = word_r + "," + word[severalTimes++] + ",1";
            robotBlood--;

        }else
        {
            word_r = word_r + "," + word[severalTimes++] + ",0";
            blood--;
        }

        showBlood(blood,robotBlood);
        imageView.setVisibility(View.VISIBLE);


        if(blood==0){
            //弹出比赛结果
            Toast.makeText(context,"显示比赛结果",Toast.LENGTH_LONG).show();
            Log.e("wqwq",word_r);

            String[] word_r_toArray;
            word_r_toArray=word_r.split(",");

            JSONArray jsonArray = new JSONArray();
            for(int i=1;i<word_r_toArray.length;i++){
                JSONObject b =new JSONObject();
                try {
                    b.put("word",word_r_toArray[i]);
                    b.put("state",Integer.parseInt(word_r_toArray[++i]));
                    jsonArray.put(b);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            JSONObject maind = new JSONObject();
            try {
                maind.put("user","asd");
                maind.put("date",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("wqwq",maind.toString());

            returnJsonToServer(maind);

            //打开开始按钮
//            imageView.setVisibility(View.GONE);
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

                    byte[] bytes;
                    bytes=result.getBytes();
                    for (int q=0;q<bytes.length;q++){
                        if (bytes[q]>64&&bytes[q]<91){
                            bytes[q]+=32;
                            System.out.println(bytes[q]);
                        }
                    }

                    final String as=new String(bytes);

                    recordresult=as;
                    gamepro();
                    // result0[0] =result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(recordresult);
                        }
                    });
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

    //显示正在获取词库对话框
    ProgressDialog progressDialog=null;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    progressDialog = ProgressDialog.show(context,"","正在获取词库，请稍后");
                    break;
                case 0:
                    progressDialog.dismiss();
            }
        }
    };

    private void returnJsonToServer (JSONObject jsonObject){

        String url = "http://yellow948.cn/wanciwang/updateNote.php";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = null ;
        try {
            body = new FormBody.Builder()
                    .add("user",jsonObject.getString("user"))
                    .add("date",jsonObject.getString("date"))
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder().url(url).post(body).build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("wqwq","onF"+e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("wqwq","onR"+response.body().string());
            }
        });


    }




}
