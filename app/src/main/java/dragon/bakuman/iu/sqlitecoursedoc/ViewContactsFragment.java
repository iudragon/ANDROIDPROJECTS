package dragon.bakuman.iu.sqlitecoursedoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Holds the code for the fragment
public class ViewContactsFragment extends Fragment {
    private static final String TAG = "ViewContactsFragment";

    //same as onCreate method but for Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Manually create the view
        //Here layout file is attached to the view and then we return view
        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);
        return view;

    }
}
