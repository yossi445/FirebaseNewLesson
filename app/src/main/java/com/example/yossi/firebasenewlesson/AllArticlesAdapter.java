package com.example.yossi.firebasenewlesson;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AllArticlesAdapter extends ArrayAdapter<Article> {

    Context context;
    List<Article> articlesList;


    public AllArticlesAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Article> articlesList) {
        super(context, resource, textViewResourceId, articlesList   );

        this.context = context;
        this.articlesList = articlesList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_article,parent,false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);

        Article temp = articlesList.get(position);

        tvTitle.setText(temp.getTitle());

        return view;
    }
}
