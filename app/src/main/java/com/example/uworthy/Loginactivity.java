package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Loginactivity extends AppCompatActivity {
    TextView registerUser;
    EditText username, password1;
    Button loginButton;
    TextView dis;
    String email, password,uname,iddata;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
   // Firebase databaseReference;
    DatabaseReference databaseReference;
    String CurrentID;
    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        firebaseAuth = FirebaseAuth.getInstance();
        dis=findViewById(R.id.textView62);
    //   CurrentID= firebaseAuth.getCurrentUser().getUid();
       firebaseAuth=FirebaseAuth.getInstance();
databaseReference=FirebaseDatabase.getInstance().getReference().child("Combodata");

        if (firebaseAuth.getCurrentUser() != null) {
         //   finish();
           Intent i = new Intent(getApplicationContext(), User_home.class);
         //  i.putExtra("name", UserDetails.username);
            startActivity(i);
          //  startActivity(new Intent(getApplicationContext(), User_home.class));
        }


        registerUser = (TextView) findViewById(R.id.textView5);
        username = (EditText) findViewById(R.id.emailedit);
        password1 = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.button5);


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this, Registerhome.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   loginuser();
            }
        });

//
    }

    public void loginuser() {
        email = username.getText().toString().trim();
        password = password1.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //  Userdata getdatass = new Userdata();
                            Toast.makeText(Loginactivity.this, "Login Succe..", Toast.LENGTH_SHORT).show();

                          Intent i = new Intent(getApplicationContext(),User_home.class);
                          startActivity(i);



                         // FirebaseDatabase.getInstance().getReference("Combodata").child(uid).child("usertype")
//                           databaseReference.addChildEventListener(new ChildEventListener() {
//                               @Override
//                               public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                                   String name =dataSnapshot.child(uid).child("usertype").getValue(String.class);
//                                   dis.setText(name);
//
//                               }
//
//                               @Override
//                               public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                                   Map<String ,String> map = dataSnapshot.getValue(Map.class);
//                                   String emailid = map.get("emailid");
//                                   String username = map.get("useername");
//                               }
//
//                               @Override
//                               public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                               }
//
//                               @Override
//                               public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                               }
//
//                               @Override
//                               public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                               }
//                           });

                            ;


//                            FirebaseDatabase.getInstance().getReference("Combodata")
//                                    .addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                            String utype = dataSnapshot.child(userh.getUid()).child("usertype").getValue(String.class);
//                                           //  uname = dataSnapshot.child(uid).child("emailid").getValue(String.class);
//                                            iddata= dataSnapshot.child( userh.getUid()).child("emailid").getValue(String.class);
//                                         //  iddata = dataSnapshot.child(uid).child("emailid").getValue(String.class);
//                                            Toast.makeText(Loginactivity.this, "" + utype, Toast.LENGTH_SHORT).show();
//                                            UserDetails.username = email;
//
//                                            UserDetails.password = password;
//                                            Toast.makeText(Loginactivity.this, "this is dATA"+iddata, Toast.LENGTH_SHORT).show();
////                                            if (utype.equals(2)) {
////
////
//////                                                UserDetails.username = email;
//////                                                UserDetails.password = password;
//////                                               Intent i = new Intent(getApplicationContext(), User_home.class);
//////                                               i.putExtra("loginname",iddata);
//////                                               startActivity(i);
////                                                Toast.makeText(Loginactivity.this, " this is Counsellor section.."+utype+iddata, Toast.LENGTH_SHORT).show();
////                                            }
////                                            else if (utype.equals(1)){
////                                              //  String uname = dataSnapshot.child(uid).child("useername").getValue(String.class);
//////                                                UserDetails.username = email;
//////                                                UserDetails.password = password;
//////                                                Intent i = new Intent(getApplicationContext(), User_home.class);
//////                                               // Intent i = new Intent(getApplicationContext(), User_home.class);
//////                                                i.putExtra("loginname", username.getText().toString());
//////                                                startActivity(i);
////                                                Toast.makeText(Loginactivity.this, "this is dATA"+ iddata+utype, Toast.LENGTH_SHORT).show();
////                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                        }
//                                    });
                          //  Toast.makeText(Loginactivity.this, "User id " + ut, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Loginactivity.this, "user not found..", Toast.LENGTH_SHORT).show();
                        }
//                        FirebaseDatabase.getInstance().getReference("Combodata").addValueEventListener( new ValueEventListener() {
//                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                            String ig = firebaseUser.getUid();
//
//                            @Override
//                            public void onDataChange( DataSnapshot dataSnapshot) {
//                                FirebaseUser userh = firebaseAuth.getCurrentUser();
//                                //  DataSnapshot dataSnapshot1;
//                             String mmm = String.valueOf(dataSnapshot.child("usertype").hasChild(firebaseAuth.getCurrentUser().getUid()));
//                                System.out.println("mmmmmmm"+mmm);
//                                Toast.makeText(Loginactivity.this, "final datgjghgjhg"+mmm, Toast.LENGTH_SHORT).show();
//                                for( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                                    String utype = dataSnapshot1.child(firebaseUser.getUid()).child("usertype").getValue(String.class);
//                                    String uemail = dataSnapshot1.child("emailid").getValue(String.class);
//
//                                    System.out.println(utype);
//                                    System.out.println(uemail);
//                                    dis.setText(utype);
//                                    Toast.makeText(Loginactivity.this, "emailid....."+uemail, Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(Loginactivity.this, "plx...."+utype, Toast.LENGTH_SHORT).show();
//                                }
//
//
//
//
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
                    }
                });

//
    }
}



