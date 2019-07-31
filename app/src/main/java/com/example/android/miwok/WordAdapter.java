package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColourResource;


     WordAdapter(Activity context, ArrayList<Word> listwords, int backgroundColourResource){

        super(context,0,listwords);
        mBackgroundColourResource=backgroundColourResource;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Word currentWord= getItem(position);

        View listView= convertView;
        if( listView == null)
        {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);
        }


        TextView miwokTextView = listView.findViewById(R.id.text_view_1);
        miwokTextView.setText(currentWord.getmiwokWordTranslation());



        TextView defaultTextView = listView.findViewById(R.id.text_view_2);
        defaultTextView.setText(currentWord.getdefaultWordTranslation());



        ImageView pictureImageView = listView.findViewById(R.id.imageView);
        int k = currentWord.getImageResourceId();
        if(k!=0)
        {
            pictureImageView.setImageResource(currentWord.getImageResourceId());
        }
        else
        {
            pictureImageView.setVisibility(View.GONE);

        }


        View linear_colour = listView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mBackgroundColourResource);
        linear_colour.setBackgroundColor(color);




        return listView;

    }

}
