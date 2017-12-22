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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Rehtt on 2017/11/8.
 */

public class dialog_login_z extends Dialog{
    Context mcontext;

    public dialog_login_z(@NonNull Context context) {
        super(context);
        mcontext=context;
    }

    EditText login_z_user;
    EditText login_z_password;
    EditText login_z_phone;
    ImageView imageView_z;
    ImageView imageView_d;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login_z);
        setCanceledOnTouchOutside(false);          //设置点击空白处不消失
//        setCancelable(false);                      ////设置点击返回键不消失

        login_z_user=(EditText)findViewById(R.id.editText2);
        login_z_password=(EditText)findViewById(R.id.editText5);
        login_z_phone=(EditText)findViewById(R.id.editText4);
        imageView_z=(ImageView) findViewById(R.id.imageView9);
        imageView_d=(ImageView)findViewById(R.id.imageView10);
        imageView_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path="http://yellow948.cn/wanciwang/register.php";
                RequestBody body = new FormBody.Builder()
                        .add("name",login_z_user.getText().toString())
                        .add("password",login_z_password.getText().toString())
                        .add("phone",login_z_phone.getText().toString())
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
                        String res = response.body().string();

                        Gson gson = new Gson();
                        json_jie data=gson.fromJson(res,json_jie.class);

                        if (data.getData().equals("500")){
//                            Toast.makeText(,"成功",Toast.LENGTH_LONG).show();
                            Snackbar.make(imageView_z,"注册成功",Snackbar.LENGTH_INDEFINITE).setAction("去登陆", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dismiss();
                                }
                            }).show();

                        }else {

                            Snackbar.make(imageView_z,"注册失败",Snackbar.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        imageView_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
