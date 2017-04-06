package rxbus2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by shuang.xiang on 2017/3/31.
 */

public class RxBus2Activity extends Activity {
    private TextView textView;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus2);
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.rx_activity_tv);
        Button button = (Button) findViewById(R.id.rx_activity_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus2.getInstance().post(new Event("RxBus2 Info!!!"));
            }
        });
        compositeDisposable = new CompositeDisposable();
        RxBus2.getInstance().toObservable(Event.class).subscribe(new Observer<Event>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Event event) {
                textView.setText(event.info);
            }

            @Override
            public void onError(Throwable e) {
                textView.setText(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    class Event {
        String info;
        Event(String info) {
            this.info = info;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
