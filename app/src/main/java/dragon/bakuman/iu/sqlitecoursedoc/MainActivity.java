package dragon.bakuman.iu.sqlitecoursedoc;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;

import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;
import dragon.bakuman.iu.sqlitecoursedoc.utils.UniversalImageLoader;

public class MainActivity extends AppCompatActivity implements
        ViewContactsFragment.OnContactSelectedListener,
        ContactFragment.OnEditContactListener
{

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1;



    @Override
    public void OnEditContactSelected(Contact contact) {
        Log.d(TAG, "OnContactSelected: contact selected from: " + getString(R.string.edit_contact_fragment) + " " + contact.getName());

        //Create the fragment we are going to navigate to
        EditContactFragment fragment = new EditContactFragment();
        //We need to create Bundle cause we need to pass the contact somehow
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.contact), contact);
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(getString(R.string.edit_contact_fragment));
        transaction.commit();
    }


    @Override
    public void OnContactSelected(Contact contact) {
        Log.d(TAG, "OnContactSelected: contact selected from: " + getString(R.string.view_contact_fragment) + " " + contact.getName());

        //Create the fragment we are going to navigate to
        ContactFragment fragment = new ContactFragment();
        //We need to create Bundle cause we need to pass the contact somehow
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.contact), contact);
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(getString(R.string.contact_fragment));
        transaction.commit();


    }

    //onCreate Method is called very first in the Activity Lifecycle when the Activity is first created. Then it inflated the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageLoader();

        init();
    }

    /* master (test)
     *
     * Now when we actually start the application MainActivity will get called (onCreate), it will inflate activity_main layout and then init() method will run and it will swap out the current fragment which is nothing (in this case) with the ViewContactsFragment ('fragment')
     *
     */

    /**
     * initialize the first Fragment (ViewContactsFragment)
     */

    //init method to initialize
    private void init() {
        ViewContactsFragment fragment = new ViewContactsFragment();
        //we create FragmentTransaction object (transaction)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //we wanna replace whatever is in the 'fragment_container' currently and then we wanna pass the new 'fragment'
        //Add the transaction to the backstack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        //in addToBackStack parameter we pass the TAG to identify the fragment. But we are not worried in This case. So we just pass null
        //backstack works like the back button basically. Everytime you replace a fragment, you add the previous one to the backstack; that way when you press back it will navigate in the correct order.
        transaction.addToBackStack(null);
        transaction.commit();


    }

    //ImageLoader has to be initialised atleast once and as our MainActivity hosts all our fragments. We do it here
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(MainActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    /**
     *Compress the bitmap by the @param "quality"
     * Quality can be anywhere from one to hundred. Hundred being the highest quality
     *
     * @param bitmap
     * @param quality
     * @return
     */

    public Bitmap compressBitmap(Bitmap bitmap, int quality){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
         return bitmap;
    }





    //Generalized methods to explicitly ask for permissions. Can pass any array of permissions
    public void verifyPermissions(String[] permissions) {
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        ActivityCompat.requestPermissions(
                MainActivity.this,
                permissions,
                REQUEST_CODE
        );
    }

    //checks to see if the permission was granted for passed parameter
    // ONLY ONE PERMISSION CAN BE CHECKED AT A TIME.
    public boolean checkPermission(String[] permission) {
        Log.d(TAG, "checkPermission: checking permissions for: " + permission[0]);
        int permissionRequest = ActivityCompat.checkSelfPermission(MainActivity.this, permission[0]);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkPermission: permission was not granted for: " + permission[0]);
            return false;
        } else {

            return true;
        }


    }

    //this will everytime a permission was granted or denied.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG, "onRequestPermissionsResult: requestcode: " + requestCode);
        switch (requestCode) {
            case REQUEST_CODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        Log.d(TAG, "onRequestPermissionsResult: user has allowed permission to access: " + permissions[i]);
                    } else {


                        break;
                    }

                }
                break;
        }


    }


}
