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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;
import dragon.bakuman.iu.sqlitecoursedoc.utils.ContactListAdapter;

//Holds the code for the fragment
//this will display all the contacts in the contacts database
public class ViewContactsFragment extends Fragment {
    private static final String TAG = "ViewContactsFragment";
    private String testImageUrl = "0.soompi.io/wp-content/uploads/2016/12/07204033/g-dragon1.jpg";

    //interface
    public interface OnContactSelectedListener{
        void OnContactSelected(Contact con);

    }

    OnContactSelectedListener mOnContactListener;






    //creating global variable
    //These below are the two states we are gonna bounce in between
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;

    private int mAppBarState;
    //create widgets. Defined in respective snippets.
    private AppBarLayout viewContactsBar, searchBar;

    private ContactListAdapter adapter; //Global one.
    private ListView contactsList; //Create ListView alsp


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
        contactsList = view.findViewById(R.id.contactsList);
        Log.d(TAG, "onCreateView: started.");

        setAppBarState(STANDARD_APPBAR);
        setUpContactsList();

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

    //MUST when implementing interface
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnContactListener = (OnContactSelectedListener) getActivity();

        }catch (ClassCastException e){
            Log.d(TAG, "onAttach: ClassCastException: " + e.getMessage());

        }
    }

    private void setUpContactsList() {
        //hoping for automatic final: RESPONSE -->
        final ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        contacts.add(new Contact("G_DRAGON", "44777744470", "Mobile", "gd@gmail.com", testImageUrl));
        // If in a Fragment to get a context, we do getActivity()
        adapter = new ContactListAdapter(getActivity(), R.layout.layout_contactlistitems, contacts, "https://");
        contactsList.setAdapter(adapter);

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "onClick: navigating to: " + getString(R.string.contact_fragment));

                //pass the contact to the interface and send it to MainActivity
                mOnContactListener.OnContactSelected(contacts.get(position));


            }
        });

    }


    //this is going to activate the toggle between two states. Search version and standard version.
    //initiates the app bar state toggle
    private void toggleToolbarState() {

        Log.d(TAG, "toggleToolbarState: toggling app bar state");

        if (mAppBarState == STANDARD_APPBAR) {
            //if the above is true, then it will set to SEARCH_APPBAR
            setAppBarState(SEARCH_APPBAR);
        } else {
            setAppBarState(STANDARD_APPBAR);

        }

    }

    /**
     * sets the AppBarState for either the search mode or the standard mode
     *
     * @param state
     */

    private void setAppBarState(int state) {
        Log.d(TAG, "setAppBarState: changing app bar state to: " + state);
        mAppBarState = state;

        //this were the logic for hiding the toolbar will be
        if (mAppBarState == STANDARD_APPBAR) {
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);
            //Declare view
            //hide the keyboard
            View view = getView();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

            // try catch is potential error. Anticipates the error
            try {

                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            } catch (NullPointerException e) {

                Log.d(TAG, "setAppBarState: Nullpointerexception " + e.getMessage());

            }

        } else if (mAppBarState == SEARCH_APPBAR) {
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
