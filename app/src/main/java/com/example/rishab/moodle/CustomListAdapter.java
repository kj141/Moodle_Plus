package com.example.rishab.moodle;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Rishab on 18-02-2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private String[] data_title;
    private String[] data_desc;
    public CustomListAdapter(Activity act, String[] data_title,String[] data_desc){
        super();
        this.data_title=data_title;
        activity=act;
        this.data_desc=data_desc;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return data_title.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.comment_list,null,true);
        TextView title=(TextView) row.findViewById(R.id.title);
        TextView desc=(TextView) row.findViewById(R.id.Description);
        title.setText(data_title[position]);
        desc.setText(data_desc[position]);
        return row;
    }
}
