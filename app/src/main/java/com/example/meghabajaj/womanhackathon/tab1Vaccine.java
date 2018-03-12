package com.example.meghabajaj.womanhackathon;

/**
 * Created by KRITIKA SHARMA on 03-03-2018.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class tab1Vaccine extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vaccine, container, false);
        return rootView;
    }
}
