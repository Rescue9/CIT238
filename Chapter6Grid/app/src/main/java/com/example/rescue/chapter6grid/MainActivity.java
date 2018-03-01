package com.example.rescue.chapter6grid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //-- images to display //
    Integer[] imageIds = {
            R.mipmap.autumn,
            R.mipmap.blowingsnow,
            R.mipmap.flower,
            R.mipmap.mountainclouds,
            R.mipmap.mountains,
            R.mipmap.snowylake,
            R.mipmap.snowylake,
            R.mipmap.sundetsnow
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "pic" + (position + 1) + " selected",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }

        // returns the number of images //
        public int getCount() {
            return imageIds.length;
        }

        // returns the item //
        public Object getItem(int position) {
            return position;
        }

        // return the ID of an item //
        public long getItemId(int position) {
            return position;
        }

        // returns an ImageView view //
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ScaleType.CENTER_CROP);
                imageView.setPadding(5, 5, 5, 5);

            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(imageIds[position]);
            return imageView;
        }
    }
}
