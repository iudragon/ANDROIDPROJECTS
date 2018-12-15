package dragon.bakuman.iu.sqlitecoursedoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;


public class ContactFragment extends Fragment {

    private static final String TAG = "ContactFragment";

    //this will evade the nullpointerexception when adding to a new bundle from MainActivity
    public ContactFragment(){
        super();
        setArguments(new Bundle());
    }


    private Toolbar toolbar;
    private Contact mContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        toolbar = view.findViewById(R.id.contactToolbar);
        Log.d(TAG, "onCreateView: started.");

        //as soon as it start, this will retrieve the contact
        mContact = getContactFromBundle();

        if (mContact != null){

            Log.d(TAG, "onCreateView: received contact: " + mContact.getName());
        }

        //required up setting up the toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);


        //navigation for the backarrow
        ImageView ivBackArrow = view.findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");

                //remove previous fragment from the backstack (therefore navigating back)
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        //navigate to the EditContactFragment tp edit the contact selected

        ImageView ivEdit = view.findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click edit inon ");

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuitem_delete:
                Log.d(TAG, "onOptionsItemSelected: deleting contact.");

        }
        return super.onOptionsItemSelected(item);
    }



    //to retrieve the information
    private Contact getContactFromBundle(){

        Log.d(TAG, "getContactFromBundle: arguments: " + getArguments());
        Bundle bundle = this.getArguments();
        //this will receive the incoming bundle when we navigate to the contactfragment
        if (bundle != null){
            return bundle.getParcelable(getString(R.string.contact));

        } else {

            return null;
        }
    }
}












