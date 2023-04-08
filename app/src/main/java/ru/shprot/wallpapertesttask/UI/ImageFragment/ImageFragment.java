package ru.shprot.wallpapertesttask.UI.ImageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.shprot.wallpapertesttask.R;
import ru.shprot.wallpapertesttask.databinding.FragmentImageBinding;

public class ImageFragment extends Fragment {

    private int imageResource;
    FragmentImageBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentImageBinding
                .bind(inflater.inflate(R.layout.fragment_image, container, false));
        binding.image.setImageResource(imageResource);

        return binding.getRoot();
    }



    public static ImageFragment getInstance(int image) {

        ImageFragment imageFragment = new ImageFragment();
        imageFragment.imageResource = image;

        return imageFragment;
    }
}
