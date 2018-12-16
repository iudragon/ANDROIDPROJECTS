package dragon.bakuman.iu.sqlitecoursedoc;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import de.hdodenhof.circleimageview.CircleImageView;
import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;
import dragon.bakuman.iu.sqlitecoursedoc.utils.UniversalImageLoader;

public class EditContactFragment extends Fragment {

    private static final String TAG = "EditContactFragment";


    //this will evade the nullpointerexception when adding to a new bundle from MainActivity
    public EditContactFragment() {
        super();
        setArguments(new Bundle());
    }

    private Contact mContact;
    private EditText mPhoneNumber, mName, mEmail;
    private CircleImageView mContactImage;
    private Spinner mSelectDevice;
    private Toolbar toolbar;
    private String mSelectedImagePath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editcontact, container, false);

        mPhoneNumber = view.findViewById(R.id.etContactPhone);
        mName = view.findViewById(R.id.etContactName);
        mEmail = view.findViewById(R.id.etContactEmail);
        mContactImage = view.findViewById(R.id.contactImage);
        mSelectDevice = view.findViewById(R.id.selectDevice);
        toolbar = view.findViewById(R.id.editcontactToolbar);


        Log.d(TAG, "onCreateView: started.");

        //required for setting up the toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        //get the contact from the bundle
        mContact = getContactFromBundle();
        if (mContact != null) {

            init();
        }

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

        //save changes to the contact
        ImageView ivCheckMark = view.findViewById(R.id.ivCheckMark);
        ivCheckMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: saving the edited contact ");
                //execute save method for the database
            }
        });

        //initiate the dialog box for choosing the image
        ImageView ivCamera = view.findViewById(R.id.ivCamera);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening the image selection dialog box");

            }
        });

        return view;
    }


    private void init() {
        mPhoneNumber.setText(mContact.getPhoneNumber());
        mName.setText(mContact.getName());
        mEmail.setText(mContact.getEmail());
        UniversalImageLoader.setImage(mContact.getProfileImage(), mContactImage, null, "http://");

        //setting the selected device to the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.device_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectDevice.setAdapter(adapter);
        int position = adapter.getPosition(mContact.getDevice());
        mSelectDevice.setSelection(position);
    }


    /**
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
}
