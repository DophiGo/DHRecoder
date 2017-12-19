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
    private final String AppId="24737096";
    private final String AppSecret="31c526bd2cb98d5f02d45e8cac8cb780";
    private final String RSA="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfei8RrZWBd+pegacmFf1YFBkdJj17JypTQrexxTpalXmgxNdDOMYIGyIEXIDMj/SQ+KlKMeVb84XXVajtYpyXpHMV8tcvrynJf08Ar6VKuOK1YByhXoEf54oPJoyHnvZoWMGu3P4fYN4KHXW4ek4Grou58b16rmtjVrzNLHErXAL6SKCVNTDPvua752iTLtK+kQsvIBs/tasAtrJNyaz+7a6/PMFW3xUynzBWV1owLzucA0UD8Llj6eiqC16GcgeIWy+XRmiq/6+p700xXT0BeR7BsEYWc/uhYi+W1iU6W8gFzZ+jbqDaD94o46+baVZSUuLbd+T5+qU9SMvetUeJAgMBAAECggEAPoGuZzhpK/irVOVudUATL22ZNlKhsF52eXHh+/x41b5WkMv2L3PWMqEn4Ru8o/Ecg6HjAKCz7Clz4SXC82A+TjV3ojevXhJCx6ZtebBekf4LZdnI/vrCk5W2CCAs3o9Qty9qorYdqIDYJpDLbkYJxRauz2/Pu4EXuDTsBWMQ7FQh8AMDrvuO8Mr5ywkjZODF9q39luE5E1gQCBLMoAatg4it0iMaPKiJ+lh5z1QeRpVUmXWFyTZodAybdVuI8NmqjxKxeolFuIBo7n1sKTzRT4wmfhOke+GUEhQfxuIBEvoq3MDMs/6hlnxgwdUVxYQ60B5SwXQuuTfrzC7ya760AQKBgQDfDS9KVW4g1RtMLtHQw11VI3OVrhrWqSFsDD5vMUdMUDwaAwOSq9JiaVN8QRx3i6BDvzEh4ky8rAX5xALA3IlneFRZ0RHrJkUierftUjApuWs8jZX/uhKa7vjB2baDPXmyXEz9viNZyo7482pNNJ/V7Elu1fhqH0wFGwJQHtHOCQKBgQC3COerM1FIkDERLYefBvB3isBK/WE8XtZOwof7U7qFfsO+514VSWxyViR8NGy62dx7owk3tEKmvGeH6K9JViCZ1hxqi92kk/czeGdvRdjtAgSD2hZSmqDSlVQnBN82TdDQnhg5AROH/R3HngpV8qRN/xNSKKYtNs14dSqK1NYNgQKBgGu5znE0OXAZ/Ikaw7Lz6AvKh3RvFf8+yCmK8Ex7nFe7GIvnkL4xxfZJsEx+leUWxlxx5qTayv0EiA5NIJdM6//RGcwXMXREk3dHlbVE0IYWSzh/mUZc88doSzQ0lfVkp3YBJh4WyrP/JHYP+fM3y0piSEUDimqWmvyMQt23RNUhAoGAO+amKtk4mM7XwA9uexUCTZ0TIXebAu7mpA/2AMJ+IIJD/Y4qtDy7SQ/4EM33Z481ISFSOvC7f2XZXBlChHWzZaU014m+l8X0+NtGvpvVSnuGjsAo3g/M4h4M1Fg71MHSvPOAaed4ROEJhfKq30Xxj7US1aiH+fQdenad3aGaDAECgYBlopywHPZAPHwMUeAYSFQ+KuU8kCzECluPxABrvcFG5Nn8+M4tuSzKi6ivRVubEJdNMrZoxY5QnXht9U95LKvTt5C0yPXdPubflmR5u/xiHC7ludzJUsfzng+vw0CdFfR0bNejE8W+h25zZT3987L86bnKqRjqIeVirtLZ4JXiBg==";

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
