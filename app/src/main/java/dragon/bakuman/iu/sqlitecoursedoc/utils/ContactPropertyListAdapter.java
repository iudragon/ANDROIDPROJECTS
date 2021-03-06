package dragon.bakuman.iu.sqlitecoursedoc.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dragon.bakuman.iu.sqlitecoursedoc.MainActivity;
import dragon.bakuman.iu.sqlitecoursedoc.R;
import dragon.bakuman.iu.sqlitecoursedoc.models.Contact;

public class ContactPropertyListAdapter extends ArrayAdapter<String> {

    private static final String TAG = "ContactPropertyListAdap";

    //LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
    //In other words, it takes as input an XML file and builds the View objects from it.

    private LayoutInflater mInflater;
    private List<String> mProperties = null;
    private int layoutResource;
    private Context mContext;

    public ContactPropertyListAdapter(@NonNull Context context, int resource, @NonNull List<String> properties) {
        super(context, resource, properties);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mContext = context;
        this.mProperties = properties;


    }

    private static class ViewHolder {

        TextView property;
        ImageView rightIcon;
        ImageView leftIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ViewHolder holds the widgets in memory ahead of time so that it does not slow down your app.
        /**
         * ViewHolder build patter start
         */
        final ViewHolder holder;

        //In other words, this parameter is used strictly to increase the performance of your Adapter. When a  ListView uses an Adapter to fill its rows with Views, the adapter populates each list item with a View object by calling getView() on each row. The Adapter uses the convertView as a way of recycling old View objects that are no longer being used. In this way, the ListView can send the Adapter old, "recycled" view objects that are no longer being displayed instead of instantiating an entirely new object each time the Adapter wants to display a new list item. This is the purpose of the  convertView parameter.
        if (convertView == null) {
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.property = convertView.findViewById(R.id.tvMiddleCardView);
            holder.rightIcon = convertView.findViewById(R.id.iconRightCardView);
            holder.leftIcon = convertView.findViewById(R.id.iconLeftCardView);
            //storing widgets using setTag
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final String property = getItem(position);
        holder.property.setText(property);

        //check if itsan email or a phone number
        //email
        if (property.contains("@")) {

            holder.leftIcon.setImageResource(mContext.getResources().getIdentifier("@drawable/ic_email", null, mContext.getPackageName()));

            holder.leftIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: opening email.");
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {property});
                    mContext.startActivity(emailIntent);

                }
            });


        } else if (property.length() != 0) {

            holder.leftIcon.setImageResource(mContext.getResources().getIdentifier("@drawable/ic_phone", null, mContext.getPackageName()));

            holder.leftIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //this Init.PHONE_PERMISSIONS gonna pass that array in checkPermission list
                    if (((MainActivity) mContext).checkPermission(Init.PHONE_PERMISSIONS)) {
                        Log.d(TAG, "onClick: initiating phone call");

                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", property, null));
                        mContext.startActivity(callIntent);
                    } else {

                        ((MainActivity) mContext).verifyPermissions(Init.PHONE_PERMISSIONS);
                    }


                }
            });


            holder.rightIcon.setImageResource(mContext.getResources().getIdentifier("@drawable/ic_chat", null, mContext.getPackageName()));

            holder.rightIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: iniitiating text message");
                    //the number that we want to sent sms
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", property, null));
                    mContext.startActivity(smsIntent);
                }
            });

        }

        return convertView;


    }
}



















