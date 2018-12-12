package dragon.bakuman.iu.sqlitecoursedoc;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //onCreate Method is called very first in the Activity Lifecycle when the Activity is first created. Then it inflated the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        init();
    }

    /* master (test)
     *
     * Now when we actually start the application MainActivity will get called (onCreate), it will inflate activity_main layout and then init() method will run and it will swap out the current fragment which is nothing (in this case) with the ViewContactsFragment ('fragment')
     *
     */

    /**
     *
     * initialize the first Fragment (ViewContactsFragment)
     *
     *
     *
     */

    //init method to initialize
    private void init(){
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
}
