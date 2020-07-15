package com.example.androishaper.studentdashbordbyjsonapi;
import com.android.volley.toolbox.StringRequest;

public class MyItem {
    private String name;
    private String role;



    public MyItem(String name, String role) {
        this.name = name;
        this.role = role;
    }



    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
