package com.example.jianqiang.mypluginlibrary;

import android.content.Context;

/**
 * Created by baojianqiang on 16/2/23.
 */
public abstract interface IDynamic {
    void methodWithCallBack(YKCallBack paramYKCallBack);
    void showPluginWindow(Context paramContext);
    void startPluginActivity(Context context,Class<?> cls);
    String getStringForResId(Context context);
}
