package com.example.firstexamcourseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText name;
    TextView coursefees,coursehours,totalfees,totalhours;
    RadioButton graduated,undergraduated;
    Spinner availablecourses;
    CheckBox checkBox[] = new CheckBox[2];
    Button addcourse,finalresult;
    String courses[]={"Java","Swift","iOS","Android","Database"};
    double fees[]={1300, 1500, 1350, 1400, 1000};
    int hours[]={6,5,5,7,4};
    double initialfees = 0.0;
    int initialhours = 0;
    Set<String> alreadyselectedcourses = new HashSet<>();
    String coname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.txtName);
        coursefees=findViewById(R.id.txtFees);
        coursehours=findViewById(R.id.txtHours);
        totalfees=findViewById(R.id.txtTotalfees);
        totalhours=findViewById(R.id.txtTotalhours);
        graduated=findViewById(R.id.rdbGraduated);
        undergraduated=findViewById(R.id.rdbUndergraduated);
        availablecourses=findViewById(R.id.coursespinner);
        checkBox[0]=findViewById(R.id.chbAccomodation);
        checkBox[1]=findViewById(R.id.chbInsurance);
        addcourse=findViewById(R.id.btnAddcourse);
        finalresult=findViewById(R.id.btnfinalamount);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,courses);
        availablecourses.setAdapter(aa);

        availablecourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {

                coname = courses[index];
                coursefees.setText(String.valueOf(fees[index]));
                coursehours.setText(String.valueOf(hours[index]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Please Enter Your Name First",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(graduated.isChecked())
                    {
                        if((initialhours+Integer.parseInt(coursehours.getText().toString()))<=21 && !getCoursename(coname))
                        {

                            double course_fees = Double.parseDouble(coursefees.getText().toString());
                            int course_hours = Integer.parseInt(coursehours.getText().toString());
                            initialfees = initialfees + course_fees;
                            initialhours = initialhours + course_hours;
                            totalfees.setText(String.valueOf(initialfees));
                            totalhours.setText(String.valueOf(initialhours));

                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"You can't add this course.",Toast.LENGTH_LONG).show();
                        }
                        alreadyselectedcourses.add(coname);
                    }
                    if(undergraduated.isChecked())
                    {
                        if((initialhours+Integer.parseInt(coursehours.getText().toString()))<=19 && !getCoursename(coname))
                        {
                            double course_fees = Double.parseDouble(coursefees.getText().toString());
                            int course_hours = Integer.parseInt(coursehours.getText().toString());
                            initialfees = initialfees+course_fees;
                            initialhours = initialhours+course_hours;
                            totalfees.setText(String.valueOf(initialfees));
                            totalhours.setText(String.valueOf(initialhours));
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"You can't add this course.",Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

        finalresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total_fees = Double.parseDouble(totalfees.getText().toString());
                if (name.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Please Enter Your Name First",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (checkBox[0].isChecked() && !checkBox[1].isChecked())
                    {
                        total_fees = total_fees + 1000;
                        totalfees.setText(String.valueOf(total_fees));
                    }
                    else if (checkBox[1].isChecked() && !checkBox[0].isChecked())
                    {
                        total_fees = total_fees + 700;
                        totalfees.setText(String.valueOf(total_fees));
                    }
                    else if (checkBox[0].isChecked() && checkBox[1].isChecked())
                    {
                        total_fees = total_fees + 1000 + 700;
                        totalfees.setText(String.valueOf(total_fees));
                    }
                    else
                    {
                        totalfees.setText(String.valueOf(total_fees));
                    }
                }
            }
        });

    }
    public boolean getCoursename(String co_name)
    {
        for (String cn:alreadyselectedcourses)
        {
            if (cn.equals(co_name))
            {
                return true;
            }
        }
        return false;
    }
}