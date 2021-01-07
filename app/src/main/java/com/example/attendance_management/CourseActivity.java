package com.example.attendance_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    private EditText course_id, course_name;
    DatabaseReference databaseReference;
    String courseid,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        course_id = findViewById(R.id.et1);
        course_name = findViewById(R.id.et2);

        databaseReference = FirebaseDatabase.getInstance().getReference("COURSES");

    }

    public void fnCourse(View view) {

        if(course_id.getText().toString().equals("")){
            course_id.setError("Enter course ID");
            course_id.requestFocus();
            return;
        }
        if(course_name.getText().toString().equals("")){
            course_name.setError("Enter course name");
            course_name.requestFocus();
            return;
        }

        courseid = course_id.getText().toString().trim();
        name = course_name.getText().toString().trim();

        if(courseid.contains(".") || name.contains("/")){
            course_id.setError(". and / are not allowed");
            course_id.requestFocus();
            return;
        }

        databaseReference.child(courseid).child("name").setValue(name);

        databaseReference.child(courseid).setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CourseActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CourseActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
