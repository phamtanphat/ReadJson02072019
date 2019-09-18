package phamtanphat.ptp.khoaphamtraining.readjson02072019;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView txtJsonDemo1;
    Button btnJsonDemo1;
    // Dai quan sat : Noi chua du lieu se phat tan ra ngoai
    Observable<Sinhvien> mData;
    Disposable disposable;
    Sinhvien sinhvien;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtJsonDemo1 = findViewById(R.id.textviewJson);
        btnJsonDemo1 = findViewById(R.id.buttonJsonDemo1);

//        btnJsonDemo1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                xulydocjsondemo1();
//            }
//        });
        // Viet ra 1 observable cho Doi tuong sinh vien
        // Khi doi tuong sinhvien thay doi thi onNext se chay lai
        sinhvien = new Sinhvien();
        sinhvien.ten = "phat";


        mData = Observable.defer(new Callable<ObservableSource<? extends Sinhvien>>() {
            @Override
            public ObservableSource<? extends Sinhvien> call() throws Exception {
                return Observable.just(sinhvien);
            }
        });
        sinhvien = new Sinhvien();
        sinhvien.ten = "Tuan";
        mData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Sinhvien>() {
                @Override
                public void accept(Sinhvien sinhvien) throws Exception {
                    Log.d("BBB", sinhvien.ten);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {

                }
            }, new Action() {
                @Override
                public void run() throws Exception {

                }
            }, new Consumer<Disposable>() {
                @Override
                public void accept(Disposable dis) throws Exception {
                    disposable = dis;
                }
            });

    }

    @Override
    protected void onStop() {
        if (disposable != null){
            disposable.dispose();
        }
        super.onStop();
    }
    //    @SuppressLint("CheckResult")
//    private void xulydocjsondemo1(){
//        mData = Observable.defer(new Callable<ObservableSource<? extends String>>() {
//            @Override
//            public ObservableSource<? extends String> call() throws Exception {
//                return Observable.just(docNoiDung_Tu_URL("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json"));
//            }
//        });
//
//        mData
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(new Consumer<String>() {
//                @Override
//                public void accept(String s) throws Exception {
//                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                }
//            });
//    }
//    private String docNoiDung_Tu_URL(String theUrl){
//        StringBuilder content = new StringBuilder();
//        try    {
//            // create a url object
//            URL url = new URL(theUrl);
//
//            // create a urlconnection object
//            URLConnection urlConnection = url.openConnection();
//
//            // wrap the urlconnection in a bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//
//            // read from the urlconnection via the bufferedreader
//            while ((line = bufferedReader.readLine()) != null){
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        }
//        catch(Exception e)    {
//            e.printStackTrace();
//        }
//        return content.toString();
//    }
}
