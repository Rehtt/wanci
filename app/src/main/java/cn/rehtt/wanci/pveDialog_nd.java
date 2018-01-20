package cn.rehtt.wanci;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rehtt on 2017/11/14.
 */

public class pveDialog_nd extends Dialog {
    Context mcontext;

    TextView pve_1;
    public pveDialog_nd(@NonNull Context context) {
        super(context);
        mcontext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pve_dialog_nd);
        pve_1=(TextView)findViewById(R.id.textView);
        pve_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(mcontext,pve_1.class);
                mcontext.startActivity(intent);
//                ((Activity)mcontext).finish();
            }
        });
    }

}
