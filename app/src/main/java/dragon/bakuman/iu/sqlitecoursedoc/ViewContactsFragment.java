package dragon.bakuman.iu.sqlitecoursedoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//Holds the code for the fragment
//this will display all the contacts in the contacts database
public class ViewContactsFragment extends Fragment {
    private static final String TAG = "ViewContactsFragment";

    //same as onCreate method but for Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Manually create the view
        //Here layout file is attached to the view and then we return view
        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);
        Log.d(TAG, "onCreateView: started.");

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
            }
        });

        return view;

    }
}
