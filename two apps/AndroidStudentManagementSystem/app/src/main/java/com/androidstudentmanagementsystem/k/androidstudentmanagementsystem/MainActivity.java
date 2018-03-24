package com.androidstudentmanagementsystem.k.androidstudentmanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText name,rollnum,emark;
    Button add,delete,show,modify,viewall,view;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        rollnum = (EditText) findViewById(R.id.roll_no);
        emark = (EditText) findViewById(R.id.mark);
        add  = (Button) findViewById(R.id.add);
        modify = (Button) findViewById(R.id.modify) ;
        delete = (Button) findViewById(R.id.delete);
        show = (Button) findViewById(R.id.showbtn);
        view  = (Button) findViewById(R.id.view);
        viewall = (Button) findViewById(R.id.viewall);
        db = openOrCreateDatabase("student_management", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno INTEGER,name VARCHAR,mark INTEGER);");


        //here is the actions that takes place where the buttons gonna clicked
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rollnum.getText().toString().trim().length() ==0||
                    name.getText().toString().trim().length() == 0 || emark.getText().toString().trim().length() ==0){
                    showMessage("Error","please enter all the value");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('" + rollnum.getText()+"','"+name.getText()+"','"+emark.getText()+"');");
                showMessage("success","Record added succesfuly");
                clearText();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rollnum.getText().toString().trim().length() ==0){
                    showMessage("Error","Please enter roll number");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno = '"+rollnum.getText()+"'",null);
                if(c.moveToFirst()){
                    db.execSQL("DELETE FROM  student WHERE rollno = '"+rollnum.getText()+"'");
                    showMessage("Success","Record deleted successfully");
                }
                else{
                    showMessage("Error","Invalid RollNo.");
                }
                clearText();


            }
        });


        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rollnum.getText().toString().trim().length() == 0){
                    showMessage("Error","please enter the roll number!");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno = '"+rollnum.getText()+"'",null);
                if(c.moveToFirst()){
                    db.execSQL("UPDATE student SET name = '"+name.getText()+"',mark = '"+emark.getText()+"' WHERE rollno = '"+rollnum.getText()+"'" );
                    showMessage("Success","record modifed!");
                }
                else{
                    showMessage("Error","Invalid roll number");
                }
                clearText();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rollnum.getText().toString().length() == 0){
                    showMessage("Error","Please enter roll number!");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno = '"+rollnum.getText()+"'",null);
                if(c.moveToFirst()){
                    name.setText(c.getString(1));
                    emark.setText(c.getString(2));
                }
                else{
                    showMessage("Error","Invalid Roll number");
                    clearText();
                }

            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM student ",null);
                if(c.getCount()==0){
                    showMessage("Error","No records found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while(c.moveToNext()){
                    stringBuffer.append("RollNo:"+c.getString(0)+"\n");
                    stringBuffer.append("Name:"+c.getString(1)+"\n");
                    stringBuffer.append("Marks:"+c.getString(2)+"\n\n");

                }
                showMessage("Student Details",stringBuffer.toString());
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Simple student management system","Developed by kena as an assignment for seII course design pattern");
            }
        });
    }

    private void clearText() {
        rollnum.setText("");
        name.setText("");
        emark.setText("");
        rollnum.requestFocus();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }
}