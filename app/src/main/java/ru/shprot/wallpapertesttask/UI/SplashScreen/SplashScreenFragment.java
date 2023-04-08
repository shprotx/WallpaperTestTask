package ru.shprot.wallpapertesttask.UI.SplashScreen;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.shprot.wallpapertesttask.R;
import ru.shprot.wallpapertesttask.databinding.FragmentSplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreenFragment extends Fragment {

    FragmentSplashScreenBinding binding;
    SplashScreenViewModel viewModel;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSplashScreenBinding.bind(inflater
                .inflate(R.layout.fragment_splash_screen, container, false));

        viewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        viewModel.checkServerAnswer();

        return binding.getRoot();
    }




    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        viewModel.result.subscribe(integer -> {

            switch (integer) {
                case 1: navController.navigate(R.id.action_splash_wallpaper);
                        break;
                case 2: navController.navigate(R.id.action_splash_webview);
                        break;
                case -1:
                    Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }

        });
    }


}
