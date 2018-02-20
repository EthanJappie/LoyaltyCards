package com.example.ethan.loyaltycards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SignUpActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private FirebaseAuth mAuth;
    private EditText loginEmail;
    private EditText loginPassword;
    private String TAG = "SignUpActivty";
    private Button btnLogin;
    private Button btnSignUp;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initBackground();
        mAuth = FirebaseAuth.getInstance();

        btnSignUp = findViewById(R.id.btnSignUpFinal);
        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                signUp();
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    private void signUp(){
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginEmail);

        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                            Snackbar.make(frameLayout,"Successfully Signed Up",2000);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Snackbar.make(frameLayout,"Sign Up Failed",2000);
                        }

                        // ...
                    }
                });
    }

    private void initBackground(){
        frameLayout = findViewById(R.id.signUpLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) frameLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }

}
