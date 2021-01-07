package com.example.attendance_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class DateActivity extends AppCompatActivity {

    CalendarView calendarView;
    String date1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        calendarView=findViewById(R.id.c1);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date1= dayOfMonth + "/" + (month+1) + "/" + year;

            }
        });

    }

//    public void date(View view) {
//
//        {
//            Intent intent = new Intent(this, SubjectList.class);
//            startActivity(intent);
//        }
//    }

    public void send(View view) {
        Intent intent =new Intent(DateActivity.this,StoreDateActivity.class);
        Bundle b=new Bundle();
        b.putString("date",date1);
        intent.putExtras(b);
        startActivity(intent);
    }
}
