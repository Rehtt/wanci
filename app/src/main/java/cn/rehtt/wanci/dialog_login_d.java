package cn.rehtt.wanci;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import cn.rehtt.wanci.bak.DataSave;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Rehtt on 2017/11/5.
 */

public class dialog_login_d extends Dialog {
//    private String dialogName;
    private EditText login_d_y;
    private EditText login_d_m;
    private ImageView imageView6;

    Context mcontext;

    Handler handler_user;



    public dialog_login_d(@NonNull Context context) {

        super(context);
        mcontext=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login_d);
        setCanceledOnTouchOutside(false);          //设置点击空白处不消失
        setCancelable(false);                      //设置点击返回键不消失

        SharedPreferences sharedPreferences=mcontext.getSharedPreferences("data",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user",null);
        if (user != null){
            Login_D(user,sharedPreferences.getString("passwd",null));
        }


        login_d_y=(EditText)findViewById(R.id.editText);
        login_d_m=(EditText)findViewById(R.id.editText3);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_D(login_d_y.getText().toString(),login_d_m.getText().toString());

            }
        });



        handler_user = new Handler(){
            SharedPreferences sharedPreferences = mcontext.getSharedPreferences("data",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    Toast.makeText(getContext(),"欢迎你，"+login_d_y.getText().toString(),Toast.LENGTH_LONG).show();
                    if (sharedPreferences.getString("user",null) == null){
                        editor.putString("user",login_d_y.getText().toString());
                        editor.putString("passwd",login_d_m.getText().toString());
                        editor.commit();
                    }
                }
                else if (msg.what == 0){
                    Toast.makeText(getContext(),"登陆失败，请检查用户名和密码",Toast.LENGTH_LONG).show();
                }
                else {

                }
            }
        };
    }



    OkHttpClient okHttpClient = new OkHttpClient();
    public void Login_D(final String name, String password){

        String path="http://wanci.rehtt.cn/wanciwang/login.php";
        RequestBody body = new FormBody.Builder()
                .add("name",name)
                .add("password",password)
                .build();
        final Request request = new Request.Builder()
                .url(path)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String ress = response.body().string();
                Log.e("qqqqqqq",ress);

                Gson gson = new Gson();
                json_jie data = gson.fromJson(ress,json_jie.class);
                Log.i("qqqqqqqqqq",data.getData());
//                Toast.makeText(dialog_login_d,"qq",Toast.LENGTH_LONG).show();
                Message msg =new Message();
                if (data.getData().equals("500")) {


                    msg.what = 1;
                    handler_user.sendMessage(msg);
                    dismiss();
                    new AppData().setUserName(name);
//                    Snackbar.make(imageView6, "登陆成功", Snackbar.LENGTH_LONG).setAction("开始畅玩", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dismiss();
//
//                        }
//                    }).show();
                }else {
                    msg.what = 0;
                    handler_user.sendMessage(msg);
                }



            }
        });

    }




    //双击退出
    private static  boolean isExit = false;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return  false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit(){
        if (!isExit){
            isExit = true;
            Snackbar.make(imageView6,"再按一次退出",Snackbar.LENGTH_LONG).show();
            handler.sendEmptyMessageDelayed(0,2000);
        }
        else {
            System.exit(0);
        }
    }
}
