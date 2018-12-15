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


public class ContactFragment extends Fragment {

    private static final String TAG = "ContactFragment";
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        toolbar = view.findViewById(R.id.contactToolbar);
        Log.d(TAG, "onCreateView: started.");

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
                EditContactFragment fragment = new EditContactFragment();
                //we create FragmentTransaction object (transaction)
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //we wanna replace whatever is in the 'fragment_container' currently and then we wanna pass the new 'fragment'
                //Add the transaction to the backstack so the user can navigate back
                transaction.replace(R.id.fragment_container, fragment);
                //in addToBackStack parameter we pass the TAG to identify the fragment. But we are not worried in This case. So we just pass null
                //backstack works like the back button basically. Everytime you replace a fragment, you add the previous one to the backstack; that way when you press back it will navigate in the correct order.

                //inside addToBackStack is the identifier
                transaction.addToBackStack(getString(R.string.edit_contact_fragment));
                transaction.commit();
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
}












