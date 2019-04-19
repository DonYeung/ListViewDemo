package com.drcom.ListViewDemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 从磁盘 / 内存缓存中 获取缓存数据
 * https://www.jianshu.com/p/6f3b6b934787
 */
public class RxJavafixRetrofitActivity5 extends AppCompatActivity {

    private static final String TAG = "Rxjava";
    // 该2变量用于模拟内存缓存 & 磁盘缓存中的数据
    private String memoryCache = "从memoryCache中获取数据";
    private String diskCache = "从磁盘缓存中获取数据";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe_activity);



        /*
         * 设置第1个Observable：检查内存缓存是否有该数据的缓存
         **/
        Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {

                // 先判断内存缓存有无数据
                if (memoryCache != null) {
                    // 若有该数据，则发送
                    emitter.onNext(memoryCache);
                } else {
                    // 若无该数据，则直接发送结束事件
                    emitter.onComplete();
                }

            }
        });

        /*
         * 设置第2个Observable：检查磁盘缓存是否有该数据的缓存
         **/
        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {

                // 先判断磁盘缓存有无数据
                if (diskCache != null) {
                    // 若有该数据，则发送
                    emitter.onNext(diskCache);
                } else {
                    // 若无该数据，则直接发送结束事件
                    emitter.onComplete();
                }

            }
        });

        /*
         * 设置第3个Observable：通过网络获取数据
         **/
        Observable<String> network = Observable.just("从网络中获取数据");
        // 此处仅作网络请求的模拟


        /*
         * 通过concat（） 和 firstElement（）操作符实现缓存功能
         **/

        // 1. 通过concat（）合并memory、disk、network 3个被观察者的事件（即检查内存缓存、磁盘缓存 & 发送网络请求）
        //    并将它们按顺序串联成队列
        //  concat（） / concatArray（）
        //作用
        //组合多个被观察者一起发送数据，合并后 按发送顺序串行执行

        Observable.concat(memory, disk, network)
                // 2. 通过firstElement()，从串联队列中取出并发送第1个有效事件（Next事件），即依次判断检查memory、disk、network
//                .firstElement()
                // 即本例的逻辑为：
                // a. firstElement()取出第1个事件 = memory，即先判断内存缓存中有无数据缓存；由于memoryCache = null，即内存缓存中无数据，所以发送结束事件（视为无效事件）
                // b. firstElement()继续取出第2个事件 = disk，即判断磁盘缓存中有无数据缓存：由于diskCache ≠ null，即磁盘缓存中有数据，所以发送Next事件（有效事件）
                // c. 即firstElement()已发出第1个有效事件（disk事件），所以停止判断。

                // 3. 观察者订阅
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept( String s) throws Exception {
//                        Log.d(TAG,"最终获取的数据来源 =  "+ s);
//                    }
//                });
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "接收到了事件"+ value  );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
