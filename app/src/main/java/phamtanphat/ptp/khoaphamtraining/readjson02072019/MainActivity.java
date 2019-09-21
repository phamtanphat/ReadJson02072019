package phamtanphat.ptp.khoaphamtraining.readjson02072019;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    Button btnDemo1;
    TextView txtDemo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDemo1 = findViewById(R.id.buttonJsonDemo1);
        txtDemo1 = findViewById(R.id.textviewJson);
        mainViewModel = new MainViewModel();
        // truyen viewmodel cho activty de quan sat lifecycle
        getLifecycle().addObserver(mainViewModel);
        mainViewModel.mDataDemo1.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){

                }
            }
        });

    }

}



