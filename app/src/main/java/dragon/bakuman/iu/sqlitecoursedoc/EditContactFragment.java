package dragon.bakuman.iu.sqlitecoursedoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;

public class EditContactFragment  extends Fragment {

    private static final String TAG = "EditContactFragment";


    //this will evade the nullpointerexception when adding to a new bundle from MainActivity
    public EditContactFragment() {
        super();
        setArguments(new Bundle());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editcontact, container, false);
        Log.d(TAG, "onCreateView: started.");


        getContactFromBundle();
        return view;
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
}
