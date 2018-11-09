package com.example.yossi.firebasenewlesson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditArticleActivity extends AppCompatActivity implements View.OnClickListener {



    EditText etTitle, etBody;
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference myRef;

    Article article;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        etTitle =findViewById(R.id.etTitle);
        etBody =findViewById(R.id.etBody);

        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        Intent intent = getIntent();
        key = intent.getExtras().getString("key");

         database = FirebaseDatabase.getInstance();

         myRef = database.getReference("Posts/"+key);

         retrieveData();


    }

    public void retrieveData() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                article = dataSnapshot.getValue(Article.class);
                etBody.setText(article.getBody());
                etTitle.setText(article.getTitle());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    @Override
    public void onClick(View v) {

        myRef = database.getReference("Posts/"+key);
        article.title = etTitle.getText().toString();
        article.body = etBody.getText().toString();

        myRef.setValue(article);
        finish();







    }
}
