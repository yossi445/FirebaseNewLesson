package com.example.yossi.firebasenewlesson;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    TextView tv;
    Button btnAdd,btnAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv);
        mAuth = FirebaseAuth.getInstance();
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnAll = findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        createAccount("b@gmail.com","123456");





    }

    private void createAccount(String email, String password) {



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        tv.setText(currentUser.getEmail().toString());

        //updateUI(currentUser);
    }

    @Override
    public void onClick(View v) {


        if(v == btnAdd) {
            Intent intent = new Intent(this, AddArticleActivity.class);
            startActivity(intent);
        }
        else if(v == btnAll)
        {

            Intent intent = new Intent(this, AllArticlesActivity.class);
            startActivity(intent);


        }



    }
}
