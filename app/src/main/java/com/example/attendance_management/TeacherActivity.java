package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button5);
        b3=(Button)findViewById(R.id.button4);
    }
    public void fn(View view) {
        if(view==b1){
            Intent intent=new Intent(TeacherActivity.this,DateActivity.class);
            startActivity(intent);
        }
        else if(view==b2){
            Intent intent=new Intent(TeacherActivity.this,HomeActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent(TeacherActivity.this,SelectRollNo.class);
            startActivity(intent);
        }

    }

}

