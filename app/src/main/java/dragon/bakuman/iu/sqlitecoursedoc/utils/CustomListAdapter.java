package dragon.bakuman.iu.sqlitecoursedoc.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;

//ArrayAdapter is of Contact type
public class CustomListAdapter extends ArrayAdapter<Contact> {

    //LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
    //In other words, it takes as input an XML file and builds the View objects from it.

    private LayoutInflater mInflater;
    private List<Contact> mContacts = null;
    private ArrayList<Contact> arrayList; //used for search bar
    private int layoutResource;
    private Context mContext;
    private String mAppend;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);


    }
}
