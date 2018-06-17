package com.example.keshav.qp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Studdetails extends AppCompatActivity {


    EditText dept;
    EditText sem;
    EditText sub;
    Button fetch;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studdetails);

        dept = (EditText) findViewById(R.id.dept);
        sem = (EditText) findViewById(R.id.sem1);
        sub = (EditText) findViewById(R.id.subject);
        fetch = (Button) findViewById(R.id.view);


    }

    public void fetching (View view){

        dept = (EditText) findViewById(R.id.dept1);
        sem = (EditText) findViewById(R.id.sem1);
        sub = (EditText) findViewById(R.id.subject);

        fetch = (Button) findViewById(R.id.view);
        fetch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(dept.getText()!=null
                                 && sem.getText()!=null && sub.getText()!=null) {


                            final String department = dept.getText().toString();
                            final String semester = sem.getText().toString();
                            final String subject = sub.getText().toString();

                            Intent intent = new Intent(Studdetails.this, ViewUploadsActivity.class);

                            intent.putExtra("dept", department);
                            intent.putExtra("sem", semester);
                            intent.putExtra("sub", subject);
                            startActivityForResult(intent, 1);

                        }
                    }
                }
        );



    }
}
