package ru.shprot.wallpapertesttask.UI.SplashScreen;

import static ru.shprot.wallpapertesttask.Utils.Library.RESPONSE_URL;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shprot.wallpapertesttask.R;

public class SplashScreenViewModel extends AndroidViewModel {

    Single<Integer> result;

    public SplashScreenViewModel(@NonNull Application application) {
        super(application);
    }



    public void checkServerAnswer() {

        result = Single.fromCallable(() -> {

            URLConnection urlConnection = new URL(RESPONSE_URL).openConnection();

            long fileFromServerSize = urlConnection.getContentLengthLong();
            long fileOneSize = getApplication().getResources().openRawResource(R.raw.one).available();
            long fileTwoSize = getApplication().getResources().openRawResource(R.raw.two).available();

            if (fileFromServerSize == fileOneSize) return 1;
            else if (fileFromServerSize == fileTwoSize) return 2;
            else return -1;

        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


}
