package com.widiarifki.screentest.presentation.mapview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.widiarifki.screentest.R;

/**
 * Created by widiarifki on 26/02/2018.
 */

public class ImageSlideFragment extends Fragment {

    public static final ImageSlideFragment newInstance(String name, int image)
    {
        ImageSlideFragment imageSlideFragment = new ImageSlideFragment();

        Bundle args = new Bundle(1);
        args.putString("NAME", name);
        args.putInt("IMAGE", image);
        imageSlideFragment.setArguments(args);

        return imageSlideFragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String name = getArguments().getString("NAME");
        int image = getArguments().getInt("IMAGE");

        View view = inflater.inflate(R.layout.fragment_imageslide, container, false);

        TextView textView = (TextView) view.findViewById(R.id.txtName);
        textView.setText(name);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(image);

        return view;
    }

}
