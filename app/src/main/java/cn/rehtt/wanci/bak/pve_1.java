package cn.rehtt.wanci.bak;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.ArrayList;

import cn.rehtt.wanci.Game;
import cn.rehtt.wanci.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class pve_1 extends AppCompatActivity {
    private String[] word = new String[16];
    private TextView textViw;
    TextView textView2;
    private static int severalTimes =1;
    Context context;
    Button button;
    Game game=new Game();
    private String recordresult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pve_1);
        textViw=(TextView)findViewById(R.id.textView10);

        textView2=(TextView)findViewById(R.id.textView11);
        context=this;
        getWord();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SpeechMode();
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
            show();
  //          textViw.setText("iiiiiiii");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void show(){
String a =word[severalTimes];
        if(severalTimes != 16){
            Log.e("888888888",a);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViw.setText(severalTimes);
                }
            });


        }
        else {

        }


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
