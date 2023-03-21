package com.example.quiz;


import static com.example.quiz.MainActivity.catList;

import androidx.annotation.NonNull;
import
        androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

    TextView back;
  public static List<String> catList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, studentLogin.class);
                startActivity(intent);
            }
        });
        GridView catGrid = findViewById(R.id.catGridView);

       List<String> catList = new ArrayList<>();
        catList.add("Cat 1");
  /*      catList.add("Cat 2");
        catList.add("Cat 3");
        catList.add("Cat 4");
        catList.add("Cat 5");
        catList.add("Cat 6");
        catList.add("Cat 7");*/
        catGridAdaptor adaptor = new catGridAdaptor(catList);
        catGrid.setAdapter(adaptor);
    }

}