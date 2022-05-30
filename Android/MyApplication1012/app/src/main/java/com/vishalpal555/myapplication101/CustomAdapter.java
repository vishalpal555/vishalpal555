package com.vishalpal555.myapplication101;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {
    private String[] arr;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull String[] arr) {
        super(context, resource, arr);
        this.arr = arr;
    }
    @Nullable
    @Override
    public String getItem(int position) {
        return this.arr[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.vishal_custom_layout, parent,false);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(getItem(position));
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "You clicked on " +position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
