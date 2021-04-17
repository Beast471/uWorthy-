package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Singlecounsellorhome extends AppCompatActivity {
    DatabaseReference childdata;
    String childname;
    Button vbtn,conmebtn;
TextView emailidtxt,usernametxt;
String loginemail,chatemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlecounsellorhome);

     vbtn=findViewById(R.id.videobtn);
emailidtxt=findViewById(R.id.emailid);
usernametxt=findViewById(R.id.counname);
usernametxt.setText(UserDetails.chatWith);
conmebtn=findViewById(R.id.button12);

        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        //String receivedName =  intent.getStringExtra("name");
        loginemail = intent.getStringExtra("name");




        Toast.makeText(this, "dkhfksdfk"+loginemail, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "dkhfksdfk"+chatemail, Toast.LENGTH_SHORT).show();

        conmebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent ichat =new Intent(getApplicationContext(),ChatActivity.class);
        startActivity(ichat);
    }
});







        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("Combodata")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        chatemail= dataSnapshot.child(UserDetails.chatWith).child("emailid").getValue(String.class);
                        emailidtxt.setText(chatemail);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
