package com.drcom.ListViewDemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.drcom.ListViewDemo.Http.GetRequest_Interface;
import com.drcom.ListViewDemo.obj.Translation;
import com.drcom.ListViewDemo.obj.Translation2;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求嵌套回调
 * 功能：需要进行嵌套网络请求：即在第1个网络请求成功后，继续再进行一次网络请求
 * https://www.jianshu.com/p/5f5d61f04f96
 */
public class RxJavafixRetrofitActivity3 extends AppCompatActivity {
    private static final String TAG = "Rxjava";

    // 定义Observable接口类型的网络请求对象
    Observable<Translation> observable1;
    Observable<Translation2> observable2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe_activity);

        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        // 步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        observable1 = request.getCall();
        observable2 = request.getCall_2();

        observable1.subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1（注册请求）
                .observeOn(AndroidSchedulers.mainThread())  // （新观察者）切换到主线程 处理网络请求1的结果
                .doOnNext(new Consumer<Translation>() {
                    @Override
                    public void accept(Translation result) {
                        Log.d(TAG, "第1次网络请求成功");
                        result.show();
                    }
                })
                // TODO: 2018/10/24 mark 判断是那个网络请求出错 
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.d(TAG, "第1次网络请求失败");
                    }
                })
                .observeOn(Schedulers.io())                 // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求


                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                // 但对于初始观察者，它则是新的被观察者
                .flatMap(new Function<Translation, ObservableSource<Translation2>>() {
                    @Override
                    public ObservableSource<Translation2> apply(Translation translation) {
                        //注册失败的处理
                        if (translation == null) {
                            Log.d(TAG, "第1次网络请求失败2");
                            return Observable.empty();
                        }

                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        else {
                            return observable2;
                        }
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Translation2>() {
                    @Override
                    public void accept(Translation2 result) {
                        Log.d(TAG, "第2次网络请求成功");
                        result.show();
                        // 对第2次网络请求返回的结果进行操作 = 显示翻译结果
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        System.out.println("登录失败");
                    }
                });

    }
}
