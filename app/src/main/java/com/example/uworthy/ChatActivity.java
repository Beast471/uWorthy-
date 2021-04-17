package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ChatActivity extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    SimpleDateFormat sdf;
    FirebaseAuth firebaseAuth;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


      //  Toast.makeText(ChatActivity.this, " chat with username is "+UserDetails.chatWith, Toast.LENGTH_SHORT).show();
       // Toast.makeText(ChatActivity.this, " login username  with username is "+UserDetails.useername, Toast.LENGTH_SHORT).show();

        sdf = new SimpleDateFormat("EEE, MMM d 'AT' HH:mm a");
       Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
      name =  intent.getStringExtra("name");
      UserDetails.useername=name;

        Toast.makeText(this, "name id data"+UserDetails.useername, Toast.LENGTH_SHORT).show();
       // UserDetails.useername=name;
        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (RelativeLayout)findViewById(R.id.layout2);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        scrollView.fullScroll(View.FOCUS_DOWN);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://uworthy-6fd8e.firebaseio.com/messages/" + UserDetails.useername+ "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://uworthy-6fd8e.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.useername);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String,String>();
                    String currentDateandTime = sdf.format(new Date());
                    map.put("message", messageText);
                    map.put("user", UserDetails.useername);
                    map.put("time", currentDateandTime);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });
        reference1.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();
                String time = map.get("time").toString();

                if(userName.equals(UserDetails.useername)){
                    addMessageBox("You " , message,time, 1);
                }
                else{
                    addMessageBox(UserDetails.chatWith , message,time, 2);
                }

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    public void addMessageBox(String name,String message,String time, int type){

        TextView textmsg = new TextView(ChatActivity.this);
        TextView textname = new TextView(ChatActivity.this);
        TextView texttime = new TextView(ChatActivity.this);

        textname.setText(name);
        textname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        textmsg.setText(message);
        texttime.setText(time);
        texttime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;
        if(type == 1) {
            lp1.gravity = Gravity.RIGHT;
            lp2.gravity = Gravity.RIGHT;
            lp3.gravity = Gravity.RIGHT;
            textmsg.setBackgroundResource(R.drawable.text_in);
        }
        else{
            lp1.gravity = Gravity.LEFT;
            lp2.gravity = Gravity.LEFT;
            lp3.gravity = Gravity.LEFT;
            textmsg.setBackgroundResource(R.drawable.text_out);
        }
        textname.setLayoutParams(lp1);
        textmsg.setLayoutParams(lp2);
        texttime.setLayoutParams(lp3);
        layout.addView(textname);
        layout.addView(textmsg);
        layout.addView(texttime);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}