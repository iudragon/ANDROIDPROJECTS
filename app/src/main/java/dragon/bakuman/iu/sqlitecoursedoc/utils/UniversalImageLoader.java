package dragon.bakuman.iu.sqlitecoursedoc.utils;

import android.content.Context;


import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import dragon.bakuman.iu.sqlitecoursedoc.R;

//this is where we store configuration properties. Since we store only one type, theres no point in repeating
public class UniversalImageLoader {

    private static final int defaultImage = R.drawable.ic_person_add;
    private Context mContext;

    public UniversalImageLoader(Context context) {
        mContext = context;

    }

    //method for getting the configuration for the image loader

    public ImageLoaderConfiguration getConfig() {

        // Universal Image Loader setup
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        return new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024)
                .build();


    }

}
