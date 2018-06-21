package jianqiang.com.plugin1;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.jianqiang.mypluginlibrary.IDynamic;
import com.example.jianqiang.mypluginlibrary.YKCallBack;

public class Dynamic implements IDynamic {
    @Override
    public void methodWithCallBack(YKCallBack paramYKCallBack) {
        Bean bean = new Bean();
        bean.setName("Jianqiang.Bao 666");
        paramYKCallBack.callback(bean);
    }

    @Override
    public void showPluginWindow(Context context) {
        Builder builder = new Builder(context);
        builder.setMessage(R.string.myplugin1_hello_world);
        builder.setTitle("hi");
        builder.setNegativeButton("OK", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void startPluginActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

    @Override
    public String getStringForResId(Context context) {
        return context.getResources().getString(R.string.myplugin1_hello_world);
    }
}
