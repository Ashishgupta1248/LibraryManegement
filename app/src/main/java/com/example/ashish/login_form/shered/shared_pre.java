package com.example.ashish.login_form.shered;

import android.content.Context;
import android.content.SharedPreferences;

public class shared_pre {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public shared_pre(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
    }
    public void savedata(String username, String passsword)
    {
         editor=sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",passsword);
        editor.apply();
    }
    public String getusername()
    {
        return sharedPreferences.getString("username","");
    }
    public String getpassword()
    {
        return sharedPreferences.getString("username","");
    }
}
