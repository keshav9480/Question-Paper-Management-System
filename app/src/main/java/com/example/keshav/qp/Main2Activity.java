package com.example.keshav.qp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText usn;
    EditText department;
    EditText confirmpass;
    EditText mail;
    EditText pin;

    private FirebaseAuth mAuth;
    private FirebaseDatabase users;


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Enter your details", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this, "Enter your details", Toast.LENGTH_SHORT).show();
        email = (EditText) findViewById(R.id.editText);
        password =(EditText) findViewById(R.id.editText2);
        name = (EditText) findViewById(R.id.name);
        usn = (EditText) findViewById(R.id.usn);
        department = (EditText) findViewById(R.id.dept);
        confirmpass = (EditText) findViewById(R.id.confirm);
        mail = (EditText) findViewById(R.id.mail);
        pin = (EditText) findViewById(R.id.PIN);
       mAuth = FirebaseAuth.getInstance();
    }

    public  void onRegister(View view){
        final String mymail=mail.getText().toString();
        final String mypass=pin.getText().toString();
        final String user = name.getText().toString();
        final String userusn = usn.getText().toString();
        final String userdept = department.getText().toString();
        final String confirm = confirmpass.getText().toString();

        if(user.equals("") || userusn.equals("") || userdept.equals("") || pin.equals("") || confirm.equals(""))
        {
            Toast.makeText(Main2Activity.this, " Enter the required fields",Toast.LENGTH_SHORT).show();
        }

        if(mypass.equals(confirm))
        {
            if(user==null || userusn == null || userdept == null || pin == null || confirm == null)
            {
                Toast.makeText(Main2Activity.this, " Enter the required fields",Toast.LENGTH_SHORT).show();
            }
            else
            {

                mAuth.createUserWithEmailAndPassword(mymail,mypass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.i("tag","createuserwithemail_success");
                                    Toast.makeText(Main2Activity.this,"succesfullysigned",Toast.LENGTH_SHORT).show();
                                    FirebaseUser user =mAuth.getCurrentUser();

                                }
                                else
                                {
                                    Log.i("tag","failure",task.getException());
                                    Toast.makeText(Main2Activity.this,"authfailed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                DatabaseReference myrefchild = myRef.child("users");
                DatabaseReference users = myrefchild.child(userusn);
                // myRef.setValue("Hello, World!");
                users.child("name").setValue(user);
                users.child("mail_id").setValue(mymail);
                users.child("dept").setValue(userdept);


            }


        }
        else
        {
            Toast.makeText(Main2Activity.this,"PIN does not match",Toast.LENGTH_SHORT).show();
            pin.setText("");
            confirmpass.setText("");
        }
        finish();
        //Intent intent2 = new Intent("com.example.keshav.qp.MainActivity");
        //startActivity(intent2);

    }

}
