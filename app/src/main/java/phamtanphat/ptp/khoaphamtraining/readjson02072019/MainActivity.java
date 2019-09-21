package phamtanphat.ptp.khoaphamtraining.readjson02072019;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

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
    ImageView imgDemo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDemo1 = findViewById(R.id.buttonJsonDemo1);
        txtDemo1 = findViewById(R.id.textviewJson);
        imgDemo1 = findViewById(R.id.imageview);
        mainViewModel = new MainViewModel();
        // dung thu vien lifecycle giu lai thong cua activity cho viewmodel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // truyen viewmodel cho activty de quan sat lifecycle
        getLifecycle().addObserver(mainViewModel);
        mainViewModel.mDataDemo1.observe(this, new Observer<Demo1>() {
            @Override
            public void onChanged(Demo1 demo1) {
                if (demo1 != null){
                    String demo =
                            "Mon hoc : " +demo1.getMonhoc() + "\n".concat(
                                "Noi hoc : " +demo1.getNoihoc() + "\n".concat(
                                    "Website : " +demo1.getWebsite() + "\n".concat(
                                        "Fanpage : " +demo1.getFanpage() + "\n"
                                    )
                                )
                            );
                    Glide
                        .with(MainActivity.this)
                        .load(demo1.getLogo())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imgDemo1);
                    txtDemo1.setText(demo);
                }
            }
        });
        btnDemo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.callDataDemo1("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
            }
        });

    }

}



