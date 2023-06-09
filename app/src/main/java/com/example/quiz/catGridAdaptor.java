package com.example.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class catGridAdaptor extends BaseAdapter {

    private final List<String> catList;

    public catGridAdaptor(List<String> catList) {
        this.catList = catList;
        System.out.print("ArrayList: ");
        for(String cat : catList) {
            System.out.print("Cats:"+cat);
        }
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout, parent, false);
        } else {
            view = convertView;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(),sets.class);
                intent.putExtra("CATEGORY",catList.get(position));
                intent.putExtra("CATEGORY_ID",position + 1);
                parent.getContext().startActivity(intent);
            }
        });

        ((TextView) view.findViewById(R.id.catName)).setText(catList.get(position));

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        view.setBackgroundColor(color);
        return view;
    }
}
