package phamtanphat.ptp.khoaphamtraining.readjson02072019;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel implements LifecycleObserver {
    //Viewmodel
//            + Giu lai du lieu cho activity
//            + Ban thong tin qa cho activity

    public MainViewModel() {

    }

    MutableLiveData<String> mDataDemo1 = new MutableLiveData<>();
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    @SuppressLint("CheckResult")
    public void callDataDemo1(final String url){
        mCompositeDisposable.add(
            Observable.defer(new Callable<ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> call() throws Exception {
                        return Observable.just(docNoiDung_Tu_URL(url));
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object o) throws Exception {
                        return o.toString();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        JSONObject jsonObject = new JSONObject(s);
                        String monhoc = jsonObject.getString("monhoc");
                        mDataDemo1.setValue(monhoc);
                    }
                })
        );

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void clearDisposable(){
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }




    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
