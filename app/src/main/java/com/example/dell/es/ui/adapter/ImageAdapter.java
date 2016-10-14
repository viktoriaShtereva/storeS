package com.example.dell.es.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.es.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dell on 10/11/2016.
 */
public class ImageAdapter extends PagerAdapter {
    Context context;
    String[] images;

  public   ImageAdapter(Context context,String[] images){
        this.context=context;
      this.images=images;

    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView  imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(context).load(images[position]).resize(800,800).into(imageView);
        container.addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

}
