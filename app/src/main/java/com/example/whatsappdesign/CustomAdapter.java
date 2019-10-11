package com.example.whatsappdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter  extends BaseAdapter {
    Context context;
    String ShareList[];
    int Icons[];
    LayoutInflater inflter;

    public CustomAdapter(Context context, String[]ShareList , int[] Icons) {
        this.context = context;
        this.ShareList = ShareList;
        this.Icons = Icons;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return ShareList.length;
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
        convertView=inflter.inflate(R.layout.list_item,null);
        TextView title=(TextView) convertView.findViewById(R.id.textTitle);
        TextView Description=(TextView) convertView.findViewById(R.id.textDescription);
        ImageView Icon=(ImageView) convertView.findViewById(R.id.imageView);
        title.setText(ShareList[position]);
        Description.setText("No messages scheduled");
        Icon.setImageResource(Icons[position]);
        return convertView;
    }
}
