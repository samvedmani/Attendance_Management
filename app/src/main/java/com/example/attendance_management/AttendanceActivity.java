package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AttendanceActivity extends AppCompatActivity {
    ListView lv;
    Button submit;
    Integer position=-1;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    public List<StudentInformation> stdinfo=new ArrayList<StudentInformation>();
    public HashMap<String,Integer> hp=new HashMap<String,Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Intent intent=getIntent();
        final String pos=intent.getStringExtra("position");
        position=Integer.parseInt(pos.trim());
        lv=(ListView)findViewById(R.id.listView2);
        submit=(Button)findViewById(R.id.button6);
       // databaseReference=database.getReference("STUDENT");
        databaseReference = FirebaseDatabase.getInstance().getReference("STUDENT");


        try{
            //database=FirebaseDatabase.getInstance();

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            StudentInformation std = child.getValue(StudentInformation.class);

                            if(std.getSubjects().get(Integer.parseInt(pos))!=-1)
                                stdinfo.add(std);


                        }
                        fn(pos);
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch(Exception e){

        }


    }
    void fn(String pos){



        int siz= stdinfo.size();

        MyBaseAdapter mba=new MyBaseAdapter(AttendanceActivity.this);
        lv.setAdapter(mba);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(AttendanceActivity.this, "Hello", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void submitAttendance(View view){
        int siz=stdinfo.size();

        for(int i=0;i<siz;i++){
            try{
                hp.put(stdinfo.get(i).getRollno(),stdinfo.get(i).getSubjects().get(position));
            }catch(Exception e){

            }
        }
        for(int i=0;i<siz;i++){
            View newView=lv.getChildAt(i);
            String rollno=((TextView)newView.findViewById(R.id.textView13)).getText().toString().trim();
            CheckBox cb=(CheckBox)newView.findViewById(R.id.checkBoxA);
            Integer totalAttendance=hp.get(rollno);
            if(cb.isChecked()){
                hp.put(rollno,totalAttendance+1001);
            }
            else{
                hp.put(rollno, totalAttendance+1000);
            }
        }

        try{
//            FirebaseDatabase database=FirebaseDatabase.getInstance();
//            DatabaseReference databaseReference=database.getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            StudentInformation std = child.getValue(StudentInformation.class);
                            String rollno=std.getRollno();
                            if(hp.containsKey(rollno)){
                                int attendance=hp.get(rollno);
                                child.getRef().child("subjects").child(String.valueOf(position)).setValue(attendance);

                            }
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch(Exception e){
        }
        finish();
        Toast.makeText(getApplicationContext(), "Attendance Complete", Toast.LENGTH_SHORT).show();

    }
    public class MyBaseAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;

        MyBaseAdapter(Context context){
            this.context=context;
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return stdinfo.size();
        }

        @Override
        public StudentInformation getItem(int position) {
            return stdinfo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.cust_list_attendance, null);
            final StudentInformation std=stdinfo.get(position);
            TextView tv1=(TextView)view.findViewById(R.id.textView12);
            tv1.setText(std.getName());
            TextView tv2=(TextView)view.findViewById(R.id.textView13);
            tv2.setText(std.getRollno());
            final CheckBox cb=(CheckBox)view.findViewById(R.id.checkBoxA);

            return view;
        }

    }
}
