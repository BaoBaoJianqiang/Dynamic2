package jianqiang.com.hostapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class BaseActivity extends Activity {

    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;
    private String dexpath = null;    //apk文件地址
    private File fileRelease = null;  //释放目录

    protected DexClassLoader classLoader = null;

    private String pluginName = "plugin1.apk";

    TextView tv;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        Utils.extractAssets(newBase, pluginName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File extractFile = this.getFileStreamPath(pluginName);
        dexpath = extractFile.getPath();

        fileRelease = getDir("dex", 0);

        classLoader = new DexClassLoader(dexpath, fileRelease.getAbsolutePath(), null, getClassLoader());
    }

    protected void loadResources() {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexpath);
            mAssetManager = assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = super.getResources();
        superRes.getDisplayMetrics();
        superRes.getConfiguration();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }

    @Override
    public AssetManager getAssets() {
        return mAssetManager == null ? super.getAssets() : mAssetManager;
    }

    @Override
    public Resources getResources() {
        return mResources == null ? super.getResources() : mResources;
    }

    @Override
    public Resources.Theme getTheme() {
        return mTheme == null ? super.getTheme() : mTheme;
    }
}