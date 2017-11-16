/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    ContactListActivity

*/
package com.example.sharangirdhani.inclass10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private ListView conactListView;
    private List<Profile> contactList = new ArrayList<>();
    private ContactListAdapter contactListAdapter;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        getSupportActionBar().setTitle(getString(R.string.contactListActivityTitle));

        conactListView = (ListView) findViewById(R.id.listViewContactList);
        contactListAdapter = new ContactListAdapter(this,R.layout.contact_row, contactList);
        conactListView.setAdapter(contactListAdapter);
        conactListView.setOnItemLongClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userContacts = firebaseDatabase.getReference("contacts").child(firebaseAuth.getCurrentUser().getUid());

        userContacts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                contactList.add(dataSnapshot.getValue(Profile.class));
                contactListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                contactListAdapter.notifyDataSetChanged();
//                if (contactList.size() == 0) {
//                    findViewById(R.id.textNoExpense).setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        findViewById(R.id.buttonCreateNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, CreateNewContactActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Profile prof = contactListAdapter.getItem(position);
        contactList.remove(prof);
        Toast.makeText(ContactListActivity.this,"Contact Deleted : "+ prof.name, Toast.LENGTH_LONG).show();
        contactListAdapter.notifyDataSetChanged();

        DatabaseReference userContacts = FirebaseDatabase.getInstance().getReference("contacts").child(firebaseAuth.getCurrentUser().getUid());
        userContacts.child(prof.profileId).removeValue();
        return true;
    }
}