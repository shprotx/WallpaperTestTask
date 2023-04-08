package ru.shprot.wallpapertesttask.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import ru.shprot.wallpapertesttask.UI.ImageFragment.ImageFragment;

public class PictureAdapter extends FragmentStateAdapter {

    private final int[] images;

    public PictureAdapter(@NonNull Fragment fragment, int[] images) {
        super(fragment);

        this.images = images;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return ImageFragment.getInstance(images[position]);
    }



    @Override
    public int getItemCount() {
        return 11;
    }
}
