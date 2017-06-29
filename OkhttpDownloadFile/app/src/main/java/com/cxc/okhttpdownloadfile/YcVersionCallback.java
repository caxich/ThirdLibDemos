package com.cxc.okhttpdownloadfile;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by cxc on 2017-06-29.
 */

public abstract class YcVersionCallback extends Callback<VersionInfo>{
    @Override
    public VersionInfo parseNetworkResponse(Response response, int id) throws Exception {
        String str = response.body().string();
        VersionInfo device = new Gson().fromJson(str,VersionInfo.class);
        return device;
    }
}
