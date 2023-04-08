package ru.shprot.wallpapertesttask.UI.WallpaperScreen;

import static ru.shprot.wallpapertesttask.Utils.Library.KEY_POSITION;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.shprot.wallpapertesttask.R;
import ru.shprot.wallpapertesttask.Utils.PictureAdapter;
import ru.shprot.wallpapertesttask.databinding.FragmentWallpaperScreenBinding;

public class WallpaperScreenFragment extends Fragment {

    FragmentWallpaperScreenBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentWallpaperScreenBinding
                .bind(inflater.inflate(R.layout.fragment_wallpaper_screen, container, false));

        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.viewPager.setAdapter(new PictureAdapter(this, getImages()));
        binding.setAsWallpaperButton.setOnClickListener(v -> setImageAsWallpaper());

        if (savedInstanceState != null)
            binding.viewPager.setCurrentItem(savedInstanceState.getInt(KEY_POSITION, 0));

    }




    private void setImageAsWallpaper() {

        Completable.fromAction(() -> {

                    int position = binding.viewPager.getCurrentItem();
                    Bitmap bitmap = BitmapFactory
                            .decodeResource(getResources(), getImages()[position]);
                    WallpaperManager.getInstance(getContext()).setBitmap(bitmap);

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setAsWallpaperButton.setEnabled(false);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), getString(R.string.successful),
                                Toast.LENGTH_LONG).show();
                        binding.setAsWallpaperButton.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), getString(R.string.issue),
                                Toast.LENGTH_LONG).show();
                        binding.setAsWallpaperButton.setEnabled(true);
                    }

                });
    }



    private int[] getImages() {

        return new int[]{
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4,
                R.drawable.img_5,
                R.drawable.img_6,
                R.drawable.img_7,
                R.drawable.img_8,
                R.drawable.img_9,
                R.drawable.img_10,
                R.drawable.img_11
        };

    }




    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, binding.viewPager.getCurrentItem());
    }

}
