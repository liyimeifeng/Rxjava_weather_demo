package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class FlowableExampleActivity extends AppCompatActivity {

    private static final String TAG = FlowableExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    /*
     * simple example using Flowable
     */
    private void doSomeWork() {

        // reduce对所有数据进行处理，最终emit一个数据出来
        Flowable.just(1,2,3,4).reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                Log.e(TAG, "apply: t1---->" + t1 + "--t2---> " + t2 );
                return t1 + t2;
            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());

            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                textView.append(" onSuccess : value : " + integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onSuccess : value : " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        });

    }

}