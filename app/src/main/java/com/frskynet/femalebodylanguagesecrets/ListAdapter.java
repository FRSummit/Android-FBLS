package com.frskynet.femalebodylanguagesecrets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by F R Summit on 23th September,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;

    public ListAdapter(Context context, String [] values, String [] numbers, int [] images){
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.single_list_item_title);
            viewHolder.subtitle = (TextView) convertView.findViewById(R.id.single_list_item_subtitle);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.single_list_item_image);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.title.setText(values[position]);
        viewHolder.subtitle.setText(numbers[position]);
        viewHolder.image.setImageResource(images[position]);

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView subtitle;
        ImageView image;
    }

}
