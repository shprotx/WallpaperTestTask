package ru.shprot.wallpapertesttask.UI.WebViewScreen;

import android.app.Application;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class WebViewModel extends AndroidViewModel {

    public WebView webView = new WebView(getApplication().getBaseContext());

    public WebViewModel(@NonNull Application application) {
        super(application);
    }

}
