package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import mehdi.sakout.fancybuttons.FancyButton;

public class RegisterActivity extends AppCompatActivity {
    RelativeLayout rlayout;
    Animation animation;
    EditText email,username,password,fullname;
    FancyButton register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        email=(EditText)findViewById(R.id.registerEmail);
        username=(EditText)findViewById(R.id.registerUname);
        fullname=(EditText)findViewById(R.id.registerFullname);
        password=(EditText)findViewById(R.id.registerPass);
        register=(FancyButton)findViewById(R.id.RegisterButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
                String newEmail  = email.getText().toString();
                String newUsername  = username.getText().toString();
                String newFullname  = fullname.getText().toString();
                String newPassword  = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(newEmail,newEmail);
                editor.commit();
                editor.putString(newUsername,newUsername);
                editor.commit();
                editor.putString(newFullname,newFullname);
                editor.commit();
                editor.putString(newPassword,newPassword);
                editor.commit();
            }
        });
    }
}