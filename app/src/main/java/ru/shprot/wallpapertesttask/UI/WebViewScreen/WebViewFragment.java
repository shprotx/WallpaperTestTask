package ru.shprot.wallpapertesttask.UI.WebViewScreen;

import static ru.shprot.wallpapertesttask.Utils.Library.HTML5_URL;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.shprot.wallpapertesttask.R;
import ru.shprot.wallpapertesttask.databinding.FragmentWebviewScreenBinding;

public class WebViewFragment extends Fragment {

    FragmentWebviewScreenBinding binding;
    WebViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentWebviewScreenBinding
                .bind(inflater.inflate(R.layout.fragment_webview_screen, container, false));

        viewModel = new ViewModelProvider(this).get(WebViewModel.class);
        binding.html5ConstraintLayout.addView(viewModel.webView);

        return binding.getRoot();
    }



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            viewModel.webView.getSettings().setJavaScriptEnabled(true);
            viewModel.webView.loadUrl(HTML5_URL);
        }

    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        viewModel.webView.saveState(outState);

    }



    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        viewModel.webView.restoreState(savedInstanceState);

    }

}
