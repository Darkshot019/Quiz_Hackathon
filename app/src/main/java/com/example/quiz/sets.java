package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class sets extends AppCompatActivity {

    private GridView sets_grid_view;
   // private FirebaseFirestore firestore;
    TextView back;
    public static int category_id;
   // private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        sets_grid_view = findViewById(R.id.setsGridView);
        category_id = getIntent().getIntExtra("CATEGORY_ID", 1);


      /*  firestore = FirebaseFirestore.getInstance();
        loadSets();

        loadingDialog = new Dialog(sets.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.pogressbackground);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();*/


        setsAdaptor adaptor = new setsAdaptor(1);
        sets_grid_view.setAdapter(adaptor);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sets.this, DashboardActivity.class);
                startActivity(intent);
            }
        });


    }


   /* private void loadSets() {
        firestore.collection("QUIZ").document("CAT" + String.valueOf(category_id))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();

                            if (doc.exists()) {
                                long sets = (long) doc.get("SETS");

                                setsAdaptor adaptor = new setsAdaptor((int) sets);
                                sets_grid_view.setAdapter(adaptor);


                            } else {
                                Toast.makeText(sets.this, "No CAT Document Exists!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else {
                            Toast.makeText(sets.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        loadingDialog.cancel();
                    }
                });

    }*/

}