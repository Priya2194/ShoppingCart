package com.example.shoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name_edit,email_edit,password_edit,referralcode;
    Button signUp,login;
    Activity activity;

    AwesomeValidation mAwesomeValidation ;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

         activity=this;

        initView();
        initComponant();

    }

    private void initComponant()
    {
        mAwesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);

        AwesomeValidation.disableAutoFocusOnFirstFailure();

        mAwesomeValidation.addValidation(activity, R.id.editName,  RegexTemplate.NOT_EMPTY, R.string.err_name);
        mAwesomeValidation.addValidation(activity, R.id.editemail,  android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        mAwesomeValidation.addValidation(activity, R.id.editpassword, RegexTemplate.NOT_EMPTY, R.string.err_password);

        //Progressdialog

        progressDialog=new ProgressDialog(activity);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("We are Creating Your Account");
     signUp.setOnClickListener(v->
        {

                if(mAwesomeValidation.validate())
                {
                    String Name=name_edit.getText().toString();
                    String Email=email_edit.getText().toString();
                    String Password=password_edit.getText().toString();
                    String Ref_Code=referralcode.getText().toString();

                    Toast.makeText(activity, ""+Name, Toast.LENGTH_SHORT).show();

                    signUpWithEmailAndPassword(Name,Email,Password,Ref_Code);
                }
        });
    }

    private void signUpWithEmailAndPassword(String name, String email, String password, String ref_code)
    {
        progressDialog.show();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                        }
                        else
                        {
                            Toast.makeText(activity, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView()
    {
        name_edit=findViewById(R.id.editName);
        email_edit=findViewById(R.id.editemail);
        password_edit=findViewById(R.id.editpassword);
        referralcode=findViewById(R.id.editRCode);
        signUp=findViewById(R.id.signUp);
        login=findViewById(R.id.loginR);
    }
}