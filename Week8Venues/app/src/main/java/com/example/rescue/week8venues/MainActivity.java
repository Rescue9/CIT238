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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends AppCompatActivity {

    // declare and instantiate various objects //

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
    String cafeHtml = "";
    String cafeHtmlLabel = "";
    String cafeHistory = "";
    int cafeImage = 0;
    int imgNumber = 0;

    /**
     * Creates tha activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an image switcher object that allows the images to be changed on-the-fly //
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

        // set initial image instead of big whitespace //
        imageSwitcher.setImageResource(coffeeImages[0]);

        // setup a grid of images that will be used to control the imageswitcher //
        GridView gridView = (GridView) findViewById(R.id.grdLogos);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgNumber = position;
                imageSwitcher.setImageResource(coffeeImages[imgNumber]);
            }
        });

        /* setup a click listener so that  if the main image is clicked, it will perform
            the same action as clicking the menu */
        imageSwitcher.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                getSwitchCase(coffeeImages[imgNumber]);

                Intent intent = new Intent(getApplicationContext(), CafeDetailsActivity.class);
                bundleExtras();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * This adapter controls the imageview, sets various parameters, and sets
     * the image based upon which logoimage was clicked
     */
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

    /**
     * Create the options menu
     * @param menu the menu view object
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Catch the menu item click and start the CafeDetails intent
     * @param item the item clicked in the menu view
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        // get switch case //
        getSwitchCase(item);

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
        bundle.putString("cafeHtmlLabel", getResources().getString(R.string.visit_website));
        bundle.putString("cafeHistory", cafeHistory);
        bundle.putInt("cafeImage", cafeImage);

        return bundle;
    }

    /**
     * Creates a switch method that operates on a menu item object to set various
     * variables that will be bundled to the next intent
     * @param item the menu item clicked
     */
    public void getSwitchCase(MenuItem item) {
        // create switch for different cafes //
        switch (item.getItemId()) {
            case R.id.cafe_du_monde: {
                cafeName = getResources().getString(R.string.cafe_du_monde);
                cafeAddress = getResources().getString(R.string.cdm_addy);
                cafePhone = getResources().getString(R.string.cdm_phone);
                cafeHours = getResources().getString(R.string.cdm_hours);
                cafeHtml = getResources().getString(R.string.cdm_html);
                cafeHtmlLabel = getResources().getString(R.string.cafe_du_monde);
                cafeHistory = getResources().getString(R.string.cdm_history);
                cafeImage = R.mipmap.coffeeimage_600_0;
                break;
            }

            case R.id.la_colombe: {
                cafeName = getResources().getString(R.string.la_colombe);
                cafeAddress = getResources().getString(R.string.lac_addy);
                cafePhone = getResources().getString(R.string.lac_phone);
                cafeHours = getResources().getString(R.string.lac_hours);
                cafeHtml = getResources().getString(R.string.lac_html);
                cafeHtmlLabel = getResources().getString(R.string.la_colombe);
                cafeHistory = getResources().getString(R.string.lac_history);
                cafeImage = R.mipmap.coffeeimage_600_1;
                break;
            }

            case R.id.stumptown: {
                cafeName = getResources().getString(R.string.stumptown);
                cafeAddress = getResources().getString(R.string.stm_addy);
                cafePhone = getResources().getString(R.string.stm_phone);
                cafeHours = getResources().getString(R.string.stm_hours);
                cafeHtml = getResources().getString(R.string.stm_html);
                cafeHtmlLabel = getResources().getString(R.string.stumptown);
                cafeHistory = getResources().getString(R.string.stm_history);
                cafeImage = R.mipmap.coffeeimage_600_2;
                break;
            }

            case R.id.sightglass: {
                cafeName = getResources().getString(R.string.sightglass);
                cafeAddress = getResources().getString(R.string.sgl_addy);
                cafePhone = getResources().getString(R.string.sgl_phone);
                cafeHours = getResources().getString(R.string.sgl_hours);
                cafeHtml = getResources().getString(R.string.sgl_html);
                cafeHtmlLabel = getResources().getString(R.string.sightglass);
                cafeHistory = getResources().getString(R.string.sgl_history);
                cafeImage = R.mipmap.coffeeimage_600_3;
                break;
            }

            case R.id.blue_bottle: {
                cafeName = getResources().getString(R.string.blue_bottle);
                cafeAddress = getResources().getString(R.string.bbl_addy);
                cafePhone = getResources().getString(R.string.bbl_phone);
                cafeHours = getResources().getString(R.string.bbl_hours);
                cafeHtml = getResources().getString(R.string.bbl_html);
                cafeHtmlLabel = getResources().getString(R.string.blue_bottle);
                cafeHistory = getResources().getString(R.string.bbl_history);
                cafeImage = R.mipmap.coffeeimage_600_4;
                break;
            }
        }
    }

    /**
     * Creates a switch method that operates on a menu item object to set various
     * variables that will be bundled to the next intent
     * @param number the image resource that was clicked
     */
    public void getSwitchCase(int number) {
        // create switch for different cafes //
        switch (number) {
            case R.mipmap.coffeeimage_600_0: {
                cafeName = getResources().getString(R.string.cafe_du_monde);
                cafeAddress = getResources().getString(R.string.cdm_addy);
                cafePhone = getResources().getString(R.string.cdm_phone);
                cafeHours = getResources().getString(R.string.cdm_hours);
                cafeHtml = getResources().getString(R.string.cdm_html);
                cafeHtmlLabel = getResources().getString(R.string.cafe_du_monde);
                cafeHistory = getResources().getString(R.string.cdm_history);
                cafeImage = R.mipmap.coffeeimage_600_0;
                break;
            }

            case R.mipmap.coffeeimage_600_1: {
                cafeName = getResources().getString(R.string.la_colombe);
                cafeAddress = getResources().getString(R.string.lac_addy);
                cafePhone = getResources().getString(R.string.lac_phone);
                cafeHours = getResources().getString(R.string.lac_hours);
                cafeHtml = getResources().getString(R.string.lac_html);
                cafeHtmlLabel = getResources().getString(R.string.la_colombe);
                cafeHistory = getResources().getString(R.string.lac_history);
                cafeImage = R.mipmap.coffeeimage_600_1;
                break;
            }

            case R.mipmap.coffeeimage_600_2: {
                cafeName = getResources().getString(R.string.stumptown);
                cafeAddress = getResources().getString(R.string.stm_addy);
                cafePhone = getResources().getString(R.string.stm_phone);
                cafeHours = getResources().getString(R.string.stm_hours);
                cafeHtml = getResources().getString(R.string.stm_html);
                cafeHtmlLabel = getResources().getString(R.string.stumptown);
                cafeHistory = getResources().getString(R.string.stm_history);
                cafeImage = R.mipmap.coffeeimage_600_2;
                break;
            }

            case R.mipmap.coffeeimage_600_3: {
                cafeName = getResources().getString(R.string.sightglass);
                cafeAddress = getResources().getString(R.string.sgl_addy);
                cafePhone = getResources().getString(R.string.sgl_phone);
                cafeHours = getResources().getString(R.string.sgl_hours);
                cafeHtml = getResources().getString(R.string.sgl_html);
                cafeHtmlLabel = getResources().getString(R.string.sightglass);
                cafeHistory = getResources().getString(R.string.sgl_history);
                cafeImage = R.mipmap.coffeeimage_600_3;
                break;
            }

            case R.mipmap.coffeeimage_600_4: {
                cafeName = getResources().getString(R.string.blue_bottle);
                cafeAddress = getResources().getString(R.string.bbl_addy);
                cafePhone = getResources().getString(R.string.bbl_phone);
                cafeHours = getResources().getString(R.string.bbl_hours);
                cafeHtml = getResources().getString(R.string.bbl_html);
                cafeHtmlLabel = getResources().getString(R.string.blue_bottle);
                cafeHistory = getResources().getString(R.string.bbl_history);
                cafeImage = R.mipmap.coffeeimage_600_4;
                break;
            }
        }
    }
}
