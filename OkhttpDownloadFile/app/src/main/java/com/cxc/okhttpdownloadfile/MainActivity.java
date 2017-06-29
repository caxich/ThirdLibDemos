package com.cxc.okhttpdownloadfile;

import android.content.pm.ProviderInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String URL_CHECK_VERSION = "http://39.108.3.215:8078/api/getversion";
    public static final String LOG_TAG = "VersionCheck";

    private Button btn_dn;
    private VersionInfo versionInfo = new VersionInfo();

    private ProgressBar pbar;

    private YcVersionCallback ycVersionCallback = new YcVersionCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(LOG_TAG,"ycVersionCallback onError:"+e.toString());
            btn_dn.setText("网络连接错误");
        }

        @Override
        public void onResponse(VersionInfo response, int id) {
            if(response != null){
                Log.i(LOG_TAG,"ycVersionCallback onResponse:"+response.getDownload_url());
                versionInfo.setApp_name(response.getApp_name());
                versionInfo.setVersion_code(response.getVersion_code());
                versionInfo.setVersion_name(response.getVersion_name());
                versionInfo.setDownload_url(response.getDownload_url());
                btn_dn.setText(versionInfo.getVersion_name());
            }
        }
    };

    private FileCallBack fileCallBack = new FileCallBack(
            Environment.getExternalStorageDirectory().getAbsolutePath(),"tyyc.apk") {
        @Override
        public void inProgress(float progress, long total, int id) {
            pbar.setProgress((int)(100*progress));
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(LOG_TAG,"fileCallBack onError:"+e.toString());
        }

        @Override
        public void onResponse(File response, int id) {
            Log.i(LOG_TAG,"fileCallBack onResponse:"+response.getAbsolutePath());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData(){
        OkHttpUtils
                .get()
                .url(URL_CHECK_VERSION)
                .build()
                .execute(ycVersionCallback);
    }

    private void initView(){
        btn_dn = (Button)findViewById(R.id.btn_dn);
        btn_dn.setText("加载中...");
        btn_dn.setOnClickListener(this);

        pbar = (ProgressBar)findViewById(R.id.pbar);
    }

    private void downloadFile(){
        OkHttpUtils
                .get()
                .url(versionInfo.getDownload_url())
                .build()
                .execute(fileCallBack);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.btn_dn:
                downloadFile();
                break;
            default:
                break;
        }
    }
}
