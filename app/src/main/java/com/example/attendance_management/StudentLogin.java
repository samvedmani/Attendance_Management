package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.app.ProgressDialog;
import android.content.Intent;


public class StudentLogin extends AppCompatActivity {
    private EditText emails,passwords;
    Button signbutton;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        emails=(EditText)findViewById(R.id.editText3);
        passwords=(EditText)findViewById(R.id.editText4);
        signbutton=(Button)findViewById(R.id.signinButton);
        tvSignUp=(TextView)findViewById(R.id.link_signup);
        pd=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intSignUp =new Intent(StudentLogin.this,ResetPasswordActivity.class);
                startActivity(intSignUp);
            }
        });


    }

    public void doOnClick(View view) {
        final String email=emails.getText().toString().trim();
        String password=passwords.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(StudentLogin.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return ;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(StudentLogin.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return ;
        }
        pd.setMessage("Siging In");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "SignIn Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(StudentLogin.this,StudentInformationActivity.class);
                    intent.putExtra("Name","Email");
                    intent.putExtra("Email",email);
                    firebaseAuth.signOut();
                    startActivity(intent);
                    pd.dismiss();
                }
                else{
                    Toast.makeText(StudentLogin.this, "Oops!! Wrong credentials", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });

    }
}

