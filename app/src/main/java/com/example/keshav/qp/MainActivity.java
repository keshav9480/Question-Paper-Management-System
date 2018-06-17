package com.example.keshav.qp;

import android.content.Intent;
import android.net.MailTo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText usn;
    EditText pin;
    Button signin;
    Button signup;
  //  CheckBox stud;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser=mAuth.getCurrentUser();
      //  Toast.makeText(this,"alreadyin",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   usn=(EditText) findViewById(R.id.editText);
      //  pin=(EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText);
        password =(EditText) findViewById(R.id.editText2);
        mAuth = FirebaseAuth.getInstance();
     //   stud=(CheckBox) findViewById(R.id.checkBox);


      //  mAuth = FirebaseAuth.getInstance();

     /*   signup=(Button) findViewById(R.id.signup);
        signup.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Signup",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("com.example.keshav.qp.Main2Activity");
                        startActivity(intent);

                    }
                }
        );*/

    }



    public void Signin(View view)
    {

//        Intent intent2 = new Intent("com.example.qp.Main3Activity");
  //      startActivity(intent2);


        signin =(Button) findViewById(R.id.signin);
        signin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String student = "stud";
                        final String mymail=email.getText().toString();
                        final String mypass=password.getText().toString();
                        mAuth.signInWithEmailAndPassword(mymail, mypass)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());
                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Log.w("TAG", "signInWithEmail:failed", task.getException());
                                            Toast.makeText(MainActivity.this,"Wrong_credentials",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(MainActivity.this, "SignInSuccess", Toast.LENGTH_SHORT).show();
                                            // Intent intent2 = new Intent("com.example.keshav.qp.Main3Activity");
                                            //  startActivity(intent2);
                                            if (mymail.toLowerCase().contains(student.toLowerCase())) {
                                                Intent intent2 = new Intent(MainActivity.this, Studdetails.class);
                                                startActivityForResult(intent2, 2);
                                            } else {
                                                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                                                startActivityForResult(intent, 1);

                                            }
                                        }
                                    }
                                });
                    }
                }
        );
    }

    public void signup(View view){

      //  email = (EditText) findViewById(R.id.editText);
      //  email.setText("****");
      //  password =(EditText)findViewById(R.id.editText2);
      //  password.setText("****");

        signup=(Button) findViewById(R.id.signup) ;
        signup.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View view) {

                  /*      final String mymail=email.getText().toString();
                        final String mypass=password.getText().toString();
                        mAuth.createUserWithEmailAndPassword(mymail,mypass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Log.i("tag","createuserwithemail_success");
                                            Toast.makeText(MainActivity.this,"succesfullysigned",Toast.LENGTH_SHORT).show();
                                            FirebaseUser user =mAuth.getCurrentUser();
                                        }
                                        else
                                        {
                                            Log.i("tag","failure",task.getException());
                                            Toast.makeText(MainActivity.this,"authfailed",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });*/
                          email = (EditText) findViewById(R.id.editText);
                          email.setText("****");
                          password =(EditText)findViewById(R.id.editText2);
                          password.setText("****");
                        Toast.makeText(MainActivity.this,"Signup",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("com.example.keshav.qp.Main2Activity");
                        startActivity(intent);

                    }
                }
        );
        /*
        final String login= usn.getText().toString();
        final String password= pin.getText().toString();
        mAuth.createUserWithEmailAndPassword(login,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("tag","createuserwithemail_success");
                            Toast.makeText(MainActivity.this,"successful_signin",Toast.LENGTH_SHORT).show();
                            FirebaseUser user=mAuth.getCurrentUser();
                        }
                        else{
                            Log.i("tag","failure",task.getException());
                            Toast.makeText(MainActivity.this,"authfailed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        */
   }
}
