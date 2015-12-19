package com.example.administrator.alldemos.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.alldemos.R;

/**
 * Created by ljgsonx on 2015/11/22.
 */
public class MyFragment extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity14_fragment_planet, container, false);
        imageView = (ImageView) view.findViewById(R.id.fragmentImage);

        int imgId = getArguments().getInt("imgId");
        imageView.setBackgroundResource(imgId);
        return view;
    }
}
