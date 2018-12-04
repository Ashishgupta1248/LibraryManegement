package com.example.ashish.login_form.Teacher;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.R;

public class MainActivity extends AppCompatActivity {
    TextInputEditText first_name,last_name,Email_id,phone_number,password,coofirm_password;
    RadioButton male,female;
    Button register;
    TextView Already;
    Database_tbls Database_tbls;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("HOME");
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        Email_id=findViewById(R.id.Email_id);
        phone_number=findViewById(R.id.phone_number);
        password=findViewById(R.id.password);
        coofirm_password=findViewById(R.id.coofirm_password);
        register=findViewById(R.id.register);
        Already=findViewById(R.id.Already);
        Database_tbls =new Database_tbls(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    if(Database_tbls.InsertTeacher(first_name.getText().toString(),last_name.getText().toString(),Email_id.getText().toString(),phone_number.getText().toString(),password.getText().toString())){
                        Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,login.class);
                        startActivity(intent);
                    }
                }else
                    Toast.makeText(MainActivity.this, "Enter valid detail", Toast.LENGTH_SHORT).show();

            }
        });
        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });

    }

  /*  private void readfromDB() {
        Cursor db=sqLiteDatabase.rawQuery("SELECT *FROM login_form",null);
        if (db!=null && db.moveToFirst()){
            do{
                Log.d("DBlog", "showDAtaBAse: "+db.getString(1)+"----"+db.getString(5));

            }while (db.moveToNext());
        }else{
            Toast.makeText(MainActivity.this, "No Data found", Toast.LENGTH_SHORT).show();
        }
    }
*/
   /* private void insertData() {
          String fName=first_name.getText().toString().trim();
          String lNmae=last_name.getText().toString().trim();
          String Email=Email_id.getText().toString().trim();
          String phone=phone_number.getText().toString().trim();
          String pass=password.getText().toString().trim();
          String InsertSQL= "INSERT INTO login_form\n"+"(fisrt_name,last_name,email_id,phone_number,password)\n"+("VALUES")+"(?,?,?,?,?);";
          sqLiteDatabase.execSQL(InsertSQL, new String[]{fName,lNmae,Email,phone,pass});
          first_name.setText("");
          last_name.setText("");
          Email_id.setText("");
          phone_number.setText("");
          password.setText("");
          coofirm_password.setText("");

        Intent intent=new Intent(MainActivity.this,login.class);
        startActivity(intent);
    }
*/
   /* private void createTable() {
   sqLiteDatabase.execSQL(
           "CREATE TABLE IF NOT EXISTS login_form(id INTEGER PRIMARY KEY AUTOINCREMENT,fisrt_name TEXT NOT NULL,last_name TEXT NOT NULL,email_id TEXT NOT NULL ,phone_number NOT NULL,password NOT  NULL)"
   );
    }*/

    private boolean validate() {
        if(first_name.getText().toString().isEmpty())
        {
            first_name.setError("Enter valid name");
            return  false;
        }
        if(last_name.getText().toString().isEmpty())
        {
            last_name.setError("Enter  last name");
        return false;
        }

       /* String EmailPattren="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        if(!Email_id.getText().toString().matches(EmailPattren)||!(Email_id.getText().toString().length()>0))
        {
         Email_id.setError("Enter Valid email address");
         return false;
        }*/
        if (phone_number.getText().toString().isEmpty()|| !(phone_number.getText().toString().length() <=10) || !(phone_number.getText().toString().length() >9))
        {
            phone_number.setError("Enter phone number");
            return false;
        }
        String passwordPattren="[a-zA-Z]+@[0-9]+";
        if(!password.getText().toString().matches(passwordPattren)||!(password.getText().toString().length()>0))
        {
            password.setError("Enter Strong password Example Ashish@123");
            return false;
        }
        if(!coofirm_password.getText().toString().equals(password.getText().toString()))
        {
            coofirm_password.setError("your password is not match");
            return false;
        }
        if(!male.isChecked()&&!(female.isChecked()))
        {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked=((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.male:
                if(checked)
                    break;
            case R.id.female:
                if (checked)
                    break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
