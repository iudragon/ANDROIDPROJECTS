package dragon.bakuman.iu.sqlitecoursedoc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

//Holds the code for the fragment
//this will display all the contacts in the contacts database
public class ViewContactsFragment extends Fragment {
    private static final String TAG = "ViewContactsFragment";

    //creating global variable
    //These below are the two states we are gonna bounce in between
    private static final int STANDARD_APPBAR  = 0;
    private static final int SEARCH_APPBAR = 1;

    private int mAppBarState;
    //create widgets. Defined in respective snippets.
    private AppBarLayout viewContactsBar, searchBar;



    //same as onCreate method but for Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Manually create the view
        //Here layout file is attached to the view and then we return view
        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);

        //Declaring widgets
        viewContactsBar = view.findViewById(R.id.viewContactToolbar);
        searchBar = view.findViewById(R.id.searchToolbar);
        Log.d(TAG, "onCreateView: started.");

        setAppBarState(STANDARD_APPBAR);

        //navigate to add contacts fragment
        FloatingActionButton fab = view.findViewById(R.id.fabAddContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked fab.");
            }
        });

        //We create ImageView widget
        ImageView ivSearchContact = view.findViewById(R.id.ivSearchIcon);
        ivSearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked Search icon");
                //toggles search version and standard version
                toggleToolbarState();
            }
        });

        /**
         * Same toggleToolbarstate method in ivSearchContact and ivBackArrow to toggle different snippets
         */

        //attach the ID for backarrow
        ImageView ivBackArrow = view.findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                //toggles search version and standard version
                toggleToolbarState();
            }
        });


        return view;

    }

    //this is going to activate the toggle between two states. Search version and standard version.
    //initiates the app bar state toggle
    private void toggleToolbarState() {

        Log.d(TAG, "toggleToolbarState: toggling app bar state");

        if (mAppBarState == STANDARD_APPBAR){
            //if the above is true, then it will set to SEARCH_APPBAR
            setAppBarState(SEARCH_APPBAR);
        } else {
            setAppBarState(STANDARD_APPBAR);

        }

    }

    /**
     *
     * sets the AppBarState for either the search mode or the standard mode
     * @param state
     */

    private void setAppBarState(int state) {
        Log.d(TAG, "setAppBarState: changing app bar state to: " + state);
        mAppBarState = state;

        //this were the logic for hiding the toolbar will be
        if (mAppBarState == STANDARD_APPBAR){
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);
            //Declare view
            //hide the keyboard
            View view = getView();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

            // try catch is potential error. Anticipates the error
            try {

                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            } catch (NullPointerException e){

                Log.d(TAG, "setAppBarState: Nullpointerexception " + e.getMessage());

            }

        }
        else if (mAppBarState == SEARCH_APPBAR){
            viewContactsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);

            //This will make the keyboard pop-up
            //open the keyboard

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_APPBAR);
    }
}
