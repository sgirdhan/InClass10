package com.example.sharangirdhani.inclass10;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    ContactListAdapter

*/

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sharangirdhani on 11/13/17.
 */

public class ContactListAdapter extends ArrayAdapter<Profile> {
    private final int rowResource;
    private Context context;
    private List<Profile> contactList;



    public ContactListAdapter(Context context, int resource, List<Profile> objects) {
        super(context, resource, objects);
        this.context = context;
        this.contactList = objects;
        this.rowResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(rowResource, parent, false);
        }

        Profile prf = contactList.get(position);
        Log.d("demo", prf.toString());
        ((TextView) convertView.findViewById(R.id.textViewName)).setText(prf.name);
        ((TextView) convertView.findViewById(R.id.textViewEmail)).setText(prf.email);
        ((TextView) convertView.findViewById(R.id.textViewDept)).setText(prf.department);
        ((TextView) convertView.findViewById(R.id.textViewPhone)).setText(prf.phone);

        switch (prf.image) {
            case "avatar_f_1":
                Picasso.with(convertView.getContext()).load(R.drawable.avatar_f_1).into((ImageView) convertView.findViewById(R.id.imageViewAvatar));
                break;
            case "avatar_f_2":
                Picasso.with(convertView.getContext()).load(R.drawable.avatar_f_2).into((ImageView) convertView.findViewById(R.id.imageViewAvatar));
                break;
            case "avatar_f_3":
                Picasso.with(convertView.getContext()).load(R.drawable.avatar_f_3).into((ImageView) convertView.findViewById(R.id.imageViewAvatar));
                break;
            case "avatar_m_1":
                Picasso.with(convertView.getContext()).load(R.drawable.avatar_m_1).into((ImageView) convertView.findViewById(R.id.imageViewAvatar));
                break;
            case "avatar_m_2":
                Picasso.with(convertView.getContext()).load(R.drawable.avatar_m_2).into((ImageView) convertView.findViewById(R.id.imageViewAvatar));
                break;
            case "avatar_m_3":
                Picasso.with(convertView.getContext()).load(R.drawable.avatar_m_3).into((ImageView) convertView.findViewById(R.id.imageViewAvatar));
                break;
        }


        return convertView;
    }
}
