package com.example.rescue.week8venues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;

    Integer[] logoImages = {
            R.mipmap.coffeelogo_150px_0,
            R.mipmap.coffeelogo_150px_1,
            R.mipmap.coffeelogo_150px_2,
            R.mipmap.coffeelogo_150px_3,
            R.mipmap.coffeelogo_150px_4
    };

    Integer[] coffeeImages = {
            R.mipmap.coffeeimage_600_0,
            R.mipmap.coffeeimage_600_1,
            R.mipmap.coffeeimage_600_2,
            R.mipmap.coffeeimage_600_3,
            R.mipmap.coffeeimage_600_4,
    };

    Bundle bundle = new Bundle();

    // cafe object placeholders //
    String cafeName = "";
    String cafeAddress = "";
    String cafePhone = "";
    String cafeHours = "";
    String cafeMap = "";
    String cafeMapLabel = "";
    String cafeHtml = "";
    String cafeHtmlLabel = "";
    String cafeHistory = "";
    int cafeImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imsLogoImage);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        imageSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ScaleType.CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        GridView gridView = (GridView) findViewById(R.id.grdLogos);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageSwitcher.setImageResource(coffeeImages[position]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }

        //---returns the number of images---
        public int getCount() {
            return logoImages.length;
        }

        //---returns the item---
        public Object getItem(int position) {
            return position;
        }

        //---returns the ID of an item---
        public long getItemId(int position) {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new
                        GridView.LayoutParams(200, 200));
                imageView.setScaleType(
                        ScaleType.CENTER_INSIDE);
                imageView.setPadding(5, 5, 5, 5);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(logoImages[position]);
            return imageView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        Toast.makeText(this, "Selected: " + item, Toast.LENGTH_LONG).show();
        cafeName = item.toString();

        // create switch for different cafes //
        switch (item.getItemId()) {
            case R.id.cafe_du_monde: {
                cafeAddress = getResources().getString(R.string.cdm_addy);
                cafePhone = getResources().getString(R.string.cdm_phone);
                cafeHours = getResources().getString(R.string.cdm_hours);
                cafeHtml = getResources().getString(R.string.cdm_html);
                cafeHtmlLabel = getResources().getString(R.string.cdm_html_label);
                cafeHistory = getResources().getString(R.string.cdm_history);
                cafeImage = R.mipmap.coffeeimage_600_0;
            }
        }


        Intent intent = new Intent(this, CafeDetailsActivity.class);
        bundleExtras();
        intent.putExtras(bundle);
        startActivity(intent);
        return true;
    }

    /**
     * Creates a bundle for use in passing extras to the various intents.
     * @return the bundle object
     */
    public Bundle bundleExtras() {
        /* Since these are non-translatable, I didn't put these in strings.xml */
        bundle.putString("cafeName", cafeName);
        bundle.putString("cafeAddress", cafeAddress);
        bundle.putString("cafePhone", cafePhone);
        bundle.putString("cafeHours", cafeHours);
        bundle.putString("cafeHtml", cafeHtml);
        bundle.putString("cafeHtmlLabel", cafeHtmlLabel);
        bundle.putString("cafeMap", cafeMap);
        bundle.putString("cafeMapLabel", cafeMapLabel);
        bundle.putString("cafeHistory", cafeHistory);
        bundle.putInt("cafeImage", cafeImage);

        return bundle;
    }

}
