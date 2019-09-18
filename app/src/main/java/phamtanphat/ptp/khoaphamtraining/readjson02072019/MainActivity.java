package phamtanphat.ptp.khoaphamtraining.readjson02072019;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView txtJsonDemo1;
    Button btnJsonDemo1;
    // Dai quan sat : Noi chua du lieu se phat tan ra ngoai
    Observable<String> mData;
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
        mData = Observable.just("Hoa","Phat","Tuan","Loi","Thuy");
        mData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

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
