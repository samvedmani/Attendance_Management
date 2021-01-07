package com.example.attendance_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreDateActivity extends AppCompatActivity {

    String date;
    DatabaseReference databaseReference;
    String setDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_date);
        Bundle bundle=getIntent().getExtras();
        date=bundle.getString("date");
        Toast.makeText(this, date , Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference("DATE");
    }

    public void store(View view) {

        //databaseReference.child("Date").setValue(date);

        databaseReference.child("Date").setValue(date).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(StoreDateActivity.this, "Date added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StoreDateActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent=new Intent(StoreDateActivity.this,SubjectList.class);
        startActivity(intent);

    }
}
