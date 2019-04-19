package com.drcom.ListViewDemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.drcom.ListViewDemo.Http.GetRequest_Interface;
import com.drcom.ListViewDemo.obj.Translation;
import com.drcom.ListViewDemo.obj.Translation2;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 合并数据源 & 同时展示
 * https://www.jianshu.com/p/fc2e551b907c
 */
public class RxJavafixRetrofitActivity4 extends AppCompatActivity {

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
        observable1 = request.getCall().subscribeOn(Schedulers.io()); // 新开线程进行网络请求1
        observable2 = request.getCall_2().subscribeOn(Schedulers.io());// 新开线程进行网络请求2
        // 即2个网络请求异步 & 同时发送


        Observable.zip(observable1, observable2,
                new BiFunction<Translation, Translation2, List<String>>() {
                    // 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
                    @Override
                    public List<String> apply(Translation translation1,
                                        Translation2 translation2) {
                        List<String> arrayList = new ArrayList<>();
                        arrayList.add(translation1.show2());
                        arrayList.add(translation2.show2());
                        return arrayList;
                    }

                }).observeOn(AndroidSchedulers.mainThread())// 在主线程接收 & 处理数据
                .subscribe(new Consumer<List<String>>() {
                    // 成功返回数据时调用
                    @Override
                    public void accept(List<String> strings) {
                        // 结合显示2个网络请求的数据结果
                        for (int i = 0; i < strings.size(); i++) {
                            Log.d(TAG, "最终接收到的数据是：" + strings.get(i));
                        }


                    }
                }, new Consumer<Throwable>() {
                    // 网络请求错误时调用
                    @Override
                    public void accept(Throwable throwable) {
                        System.out.println("登录失败");
                    }
                });
    }
}
