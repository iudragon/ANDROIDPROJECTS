package dragon.bakuman.iu.sqlitecoursedoc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;
import dragon.bakuman.iu.sqlitecoursedoc.utils.ContactPropertyListAdapter;
import dragon.bakuman.iu.sqlitecoursedoc.utils.UniversalImageLoader;


public class ContactFragment extends Fragment {

    private static final String TAG = "ContactFragment";

    public interface OnEditContactListener {

        void OnEditContactSelected(Contact contact);

    }

    OnEditContactListener mOnEditContactListener;


    //this will evade the nullpointerexception when adding to a new bundle from MainActivity
    public ContactFragment() {
        super();
        setArguments(new Bundle());
    }

    private TextView mContactName;
    private CircleImageView mContactImage;


    private Toolbar toolbar;
    private Contact mContact;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        toolbar = view.findViewById(R.id.contactToolbar);
        mContactName = view.findViewById(R.id.contactName);
        mContactImage = view.findViewById(R.id.contactImage);
        mListView = view.findViewById(R.id.lvContactProperties);
        Log.d(TAG, "onCreateView: started.");

        //as soon as it start, this will retrieve the contact
        mContact = getContactFromBundle();


        //required for setting up the toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        init();


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
                mOnEditContactListener.OnEditContactSelected(mContact);

            }
        });

        return view;
    }

    private void init() {

        mContactName.setText(mContact.getName());
        UniversalImageLoader.setImage(mContact.getProfileImage(), mContactImage, null, "http://");


        ArrayList<String> properties = new ArrayList<>();
        properties.add(mContact.getPhoneNumber());
        properties.add(mContact.getEmail());
        ContactPropertyListAdapter adapter = new ContactPropertyListAdapter(getActivity(), R.layout.layout_cardview, properties);
        mListView.setAdapter(adapter);
        mListView.setDivider(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuitem_delete:
                Log.d(TAG, "onOptionsItemSelected: deleting contact.");

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     *
     * Retrieves the selected contact from the bundle coming form MainActivity
     *
     * @return
     */

    //to retrieve the information
    private Contact getContactFromBundle() {

        Log.d(TAG, "getContactFromBundle: arguments: " + getArguments());
        Bundle bundle = this.getArguments();
        //this will receive the incoming bundle when we navigate to the contactfragment
        if (bundle != null) {
            return bundle.getParcelable(getString(R.string.contact));

        } else {

            return null;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnEditContactListener = (OnEditContactListener) getActivity();
        }catch (ClassCastException e){

            Log.d(TAG, "onAttach: classcastexception: " + e.getMessage());

        }
    }
}












