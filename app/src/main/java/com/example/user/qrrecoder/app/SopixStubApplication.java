package com.example.user.qrrecoder.app;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Keep;
import android.util.Log;

import com.example.user.qrrecoder.BuildConfig;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by dxs on 2017/12/18.
 */

public class SopixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    private final String AppId="24735220-1";
    private final String AppSecret="023fbaf9b6c279ed8c7c47d3672a3d99";
    private final String RSA="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCfyvVYbOTWskBn9nWnxFpyNJywRoBzXfDCnHrXXnXhipfLRP2WUdnaW6TUHaPqJOSQn3U39P9Lx/HVm2rXf8QKubiT06ByGcWMjfNSZwAlwYXyJji/tR9KIjsd+ilLFir6H88/ya7hES/2JPVkYX4e+yfuZlGb9xj+PcC9m8feE0HxG2MFKQShakMLznbo2deE+OilzedQxfte1v8ly9PpQo/zNME9eZN1k0IQDdnVvY5N2FSBaVD38MR3GAwPuZgh5n0f5yOMlDFFHHfoxmFVl1FAeV1jUF9WvcVZbtQlmO+1zmrmZztV1cEpy+D4QxcGorUpxaz1p8XpJVAmXma5AgMBAAECggEBAJ6qljIGdrgk6UUR+cPree/bBBcY+mUTy4RCgsul1SFuT8mcKL2BavOieinRgLh3h6l/XJKCqJlj4WM8Xru69zu7LGdFBRUnN/yX2xtggUn8PneysjjRrU8cbF0Y0LXpPiV9PnpVVlvPiDM+gZf6IpS6yOy1FqXqlxukz+S2caC1INqzk33RyNAQSWGOnBbmNJwOhvRZaqE5etSTaGTQVGeTAvZzDcEP9bq7Xx7MUKw6UfrFr91bt6Gy+GC2Lzt4AMu/xp4KQ3JFIA080Dl2znFqwJP51QvPSRB6OjC8gNX2ORz2COaPuMJRhTtlXppLOYKEv6Ivxi6hXPeX7z1PMfECgYEA8H5skh/XurAFR7lbufidquG7GbLiQM5Yxxe+BpSJHii6kdGx8ktDryvMG76SY1dDRgJMRtEfQNePwyzfpyvknMouGf1Si4gqt8GKGFyfRzdGvtLnqKNeGaUOpuft/8759DkkVXTAgue8KQtYqSzdv6kN8S7i73XhdAhDEf9Pn+0CgYEAqhh89u5qZYY239khR5puXYL9mE2jpNzR34FislydLnP7uwMvLiWHl58Gj6gA1TF3nEavoh+K0sFyc6Fw5bIhPmi9dulgtBZtWCklrFcppcuedbEX5F4NqCIwbKBUdd4IxesDpt/xtib1NAlOisAeNHX7crymQjddYU62HduokH0CgYA11TbcZsBVi71C1t0PffVdIPZOxFodA5OfzVK+ClW/KILKzVO11t8pwPYJZ9lA33GxpCYK3BjQ3O/TUqIlu4+LAT7clnX5wds+EtakqWcF+/ucD33Ho/oWZ3omJliCIISXBIt7jYziQJk/3shT5jUlB+SVDlfCHnL/FDJfPvVF6QKBgQCHlvG2naLuvyhrFFi1DWPA4zRn/ivetbAPLMKST8V0TDx0fTtU29HUiY3fYhsX11DzoF1NLTuqeU5U4nJuh6p8Z13wtmTB6cbMKjC/ytOGOGReiQb0ogzc5DQZw0zQkGgJ7SeCFK5Hd5KrImgbrr3lL897TOpzilLDyMzfzEP4uQKBgQC0nwULc12pHeBiDHOEUNdKIPAA+t1GuGSaoByWHqP3XM0oQgNNHRysZDQa31Y8W9UYlX4OnZs4BL7C34Ydo03X3n29FCwVPf67/5/0D/dMx4cQkjGF3DbtopJNc9YLLW5OHtOB65cHx4qbX8blQ15ldHaY1I/bBy6LX1U9LV8QxQ==";

    // 此处SophixEntry应指定真正的Application，也就是你的应用中原有的主Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApp.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //         如果需要使用MultiDex，需要在此处调用。
        //         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(BuildConfig.VERSION_NAME)
                .setSecretMetaData(AppId, AppSecret, RSA) //三个参数分别对应AndroidManifest里面的//AppId、AppSecret、RSA密钥，可以不在AndroidManifest设置而是用此函数来设置//Secret。放到代码里面进行设置可以自定义混淆代码，更加安全，此函数的设置会覆盖//AndroidManifest里面的设置，如果对应的值设为null，默认会在使用AndroidManifest里面//的。
                .setEnableDebug(true)//默认为false，设为true即调试模式下会输出日志以及不进行  //补丁签名校验. 线下调试此参数可以设置为true, 它会强制不对补丁进行签名校验, 所有就算//补丁未签名或者签名失败也发现可以加载成功. 但是正式发布该参数必须为false, false会//对补丁做签名校验, 否则就可能存在安全漏洞风险。
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                            /** 不可以直接Process.killProcess(Process.myPid())来杀进程，这样会扰乱Sophix的内部状态。
                             * 因此如果需要杀死进程，建议使用这个方法，它在内部做一些适当处理后才杀死本进程。*/
                            instance.killProcessSafely();
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
