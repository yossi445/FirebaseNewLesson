package com.example.yossi.firebasenewlesson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddArticleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTitle, etBody;
    Button btnAdd;

    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        etTitle =findViewById(R.id.etTitle);
        etBody =findViewById(R.id.etBody);

        btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();





    }

    @Override
    public void onClick(View v) {


        String uid = FirebaseAuth.getInstance().getUid().toString();
        Article a = new Article(uid, etTitle.getText().toString(),etBody.getText().toString(),0,"");

        DatabaseReference postsRef = database.getReference("Posts").push();
        a.key = postsRef.getKey();
        postsRef.setValue(a);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
