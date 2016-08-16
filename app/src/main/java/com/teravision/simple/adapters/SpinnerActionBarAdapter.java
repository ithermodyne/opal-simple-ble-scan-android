package com.teravision.simple.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.teravision.simple.R;

/**
 * Created by SinAsignarT1 on 22/07/2016.
 */
public class SpinnerActionBarAdapter extends ArrayAdapter<String>{

    private Activity context;
    String[] objects;
    public SpinnerActionBarAdapter(Context context, int row_spinner, int textViewResourceId, String[] objects) {
        super(context,row_spinner,textViewResourceId, objects);
        this.context = (Activity) context;
        this.objects = objects;
    }


    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        view.setVisibility(View.GONE);
        return view;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater= context.getLayoutInflater();
        View row=inflater.inflate(R.layout.row_spinner, parent, false);

        ImageView icon=(ImageView)row.findViewById(R.id.spinner_icon);
        TypedArray imgs = context.getResources().obtainTypedArray(R.array.mydrawables);
        icon.setImageResource(imgs.getResourceId(position, -1));

        return row;
    }
}
