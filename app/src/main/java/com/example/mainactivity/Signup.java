package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText email;
    EditText name;
    EditText Mobile_num;
    EditText password;

    Button button_Reg;
    String Email_Validation = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference Retailer_DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        /////Initilizing the values

        email=findViewById(R.id.email);
        name=findViewById(R.id.name);

        Mobile_num=findViewById(R.id.Mobile_num);
        password=findViewById(R.id.password);
        button_Reg=findViewById(R.id.button_Reg);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        Retailer_DB = FirebaseDatabase.getInstance().getReference().child("User_Info");




        TextView btn = (TextView) findViewById(R.id.register_to_login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Login.class));
                finish();
            }
        });


        button_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    ////Insert User Data
    private void insert_retailer_data() {

        String email_ret = email.getText().toString();
        String Name_ret = name.getText().toString();
        String Mobile_ret = Mobile_num.getText().toString();


        user_class U_data = new user_class(email_ret,Name_ret,Mobile_ret);

        Retailer_DB.push().setValue(U_data);
        Toast.makeText(Signup.this,"User-Registred",Toast.LENGTH_SHORT).show();

    }

    ////Authentification User

    private void PerformAuth() {
        String email_reg = email.getText().toString();
        String Name_reg = name.getText().toString();
        String Mobile_number = Mobile_num.getText().toString();
        String Password_REG = password.getText().toString();

        if(!email_reg.matches(Email_Validation))
        {
                email.setError("Enter Correct Email");
        }
        else if(Password_REG.isEmpty() || Password_REG.length()<6)
        {
            password.setError("Enter Correct Password");
        }
        else if(Mobile_number.isEmpty() || Mobile_number.length()<11)
        {
            password.setError("Enter Correct Mobile Number");
        }
        else if(Name_reg.isEmpty())
        {
            name.setError("Enter Name Please");
        }
        else
        {
                progressDialog.setMessage("Please Wait While Registration Done");
                progressDialog.setTitle("Registration");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email_reg,Password_REG).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {
                         insert_retailer_data();
                         progressDialog.dismiss();
                         sendUserToNextActivity();

                     }
                     else
                     {
                     Toast.makeText(Signup.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                     }
                    }
                });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(Signup.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}