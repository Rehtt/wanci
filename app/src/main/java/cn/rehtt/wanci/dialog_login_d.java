package cn.rehtt.wanci;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

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



    public dialog_login_d(@NonNull Context context) {

        super(context);
        mcontext=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login_d);
        login_d_y=(EditText)findViewById(R.id.editText);
        login_d_m=(EditText)findViewById(R.id.editText3);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_D(login_d_y.getText().toString(),login_d_m.getText().toString());

            }
        });
    }



    OkHttpClient okHttpClient = new OkHttpClient();
    public void Login_D(String name,String password){

        String path="http://yellow948.cn/wanciwang/login.php";
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

                Gson gson = new Gson();
                json_jie data = gson.fromJson(ress,json_jie.class);
                Log.i("qqqqqqqqqq",data.getData());
//                Toast.makeText(dialog_login_d,"qq",Toast.LENGTH_LONG).show();
                
                if (data.getData().equals("500")) {
                    dismiss();
//                    Snackbar.make(imageView6, "登陆成功", Snackbar.LENGTH_LONG).setAction("开始畅玩", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dismiss();
//
//                        }
//                    }).show();
                }else Snackbar.make(imageView6,"登陆失败",Snackbar.LENGTH_LONG).show();


            }
        });

    }
}
