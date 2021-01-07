package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
    }
    public void fn(View view) {
        if(view==b1){
            Intent intent=new Intent(this,CourseActivity.class);
            startActivity(intent);
        }
        else if(view==b2)
        {
            Intent intent=new Intent(AdminActivity.this,AddStudentActivity.class);
            startActivity(intent);
        }
        else if(view==b3){
            Intent intent=new Intent(AdminActivity.this,SelectRollNo.class);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(AdminActivity.this,HomeActivity.class);
            startActivity(intent);
        }

    }

}

