package com.example.dell.es.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.es.R;
import com.example.dell.es.ui.iteminfo.ActivityList;

public class FragmentEntry extends Fragment implements View.OnClickListener{
   private Button mBotique, mAboutUS, mCampaign, mContact;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        mBotique= (Button) view.findViewById(R.id.boutique);
        mBotique.setOnClickListener(this);
        mAboutUS= (Button) view.findViewById(R.id.aboutUs);
        mAboutUS.setOnClickListener(this);
        mCampaign= (Button) view.findViewById(R.id.campaign);
        mCampaign.setOnClickListener(this);
        mContact= (Button) view.findViewById(R.id.contact);
        mContact.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.boutique){
            Intent intent=new Intent(getActivity(),ActivityList.class);
            startActivity(intent);

        }
        else  if(v.getId()==R.id.aboutUs){
            Intent intent=new Intent(getActivity(),InfoStoreActivity.class);
            startActivity(intent);

        }
        else  if(v.getId()==R.id.contact){
            Intent intent=new Intent(getActivity(),ContactActivity.class);
            startActivity(intent);

        }
        else  if(v.getId()==R.id.campaign){
            Intent intent=new Intent(getActivity(),ImagePickActivity.class);
            startActivity(intent);
        }

    }
}
