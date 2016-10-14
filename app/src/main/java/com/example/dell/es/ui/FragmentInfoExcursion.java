package com.example.dell.es.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.es.R;


public class FragmentInfoExcursion extends Fragment  {
   private TextView txt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info_excursion, container, false);

        txt= (TextView) view.findViewById(R.id.infoExcursion);
        txt.setText("You can win an excursion in Hawaii.Just send to us photo which captures a moment of surprise when you give a gift one of our`s product");
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), ImagePickActivity.class);
                        startActivity(intent);

                        return true;
                    }
                }
                return false;
            }
        });


        return view;
    }

}
