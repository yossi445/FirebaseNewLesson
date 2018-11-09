package com.example.yossi.firebasenewlesson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllArticlesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    ListView lv;
    ArrayList<Article> articles;
    AllArticlesAdapter allArticlesAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_articles);

        lv = findViewById(R.id.lv);


         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Posts");

        this.retrieveData();
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);


    }


    public void retrieveData()
    {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                articles = new ArrayList<Article>();

                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    Article a = data.getValue(Article.class);

                    articles.add(a);

                }


                allArticlesAdapter = new AllArticlesAdapter(AllArticlesActivity.this,0,0,articles);
                lv.setAdapter(allArticlesAdapter);





                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Article article = articles.get(position);

        Intent intent = new Intent(this,EditArticleActivity.class);
        intent.putExtra("key",article.getKey());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Article article = articles.get(position);
        DatabaseReference current = FirebaseDatabase.getInstance().getReference("Posts/"+article.key);
        current.removeValue();


        return true;
    }
}
