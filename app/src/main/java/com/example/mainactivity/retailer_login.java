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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class retailer_login extends AppCompatActivity {


    EditText email;
    EditText password;
    Button button_RET_Login;
    String Email_Validation = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_retailer_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        button_RET_Login=findViewById(R.id.button_login);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        button_RET_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perform_login();
            }
        });
    }
    private void perform_login() {
        String email_log = email.getText().toString();
        String Password_log = password.getText().toString();

        if(!email_log.matches(Email_Validation))
        {
            email.setError("Enter Correct Email");
        }
        else if(Password_log.isEmpty() || Password_log.length()<6)
        {
            password.setError("Enter Correct Password");
        }
        else
        {
            progressDialog.setMessage("Please Wait While Log_IN");
            progressDialog.setTitle("LOG_IN");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email_log,Password_log).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(retailer_login.this,"LOG_IN Sucessfull",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(retailer_login.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent= new Intent(retailer_login.this,R_Add_Product.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}