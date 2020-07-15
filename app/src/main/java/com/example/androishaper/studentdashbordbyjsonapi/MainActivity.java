package com.example.androishaper.studentdashbordbyjsonapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    String url="https://next.json-generator.com/api/json/get/NJZrFEmhd";
    RecyclerView recyclerView;
    private List<MyItem> listItem;
    MyAdapter myAdapter;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recylerViewId);
        swipeRefreshLayout=findViewById(R.id.swiperefresh);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        listItem=new ArrayList<>();
        int a=1;
        LoadData(a);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int b=1;

                LoadData(b);
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

//    private void RefreshData() {
//        progressDialog=new ProgressDialog(this);
////        progressDialog.setMessage("Loooding...");
////        progressDialog.show();
//        //listItem.clear();
//
//        final StringRequest stringReques=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                progressDialog.dismiss();
//                try {
//
//                    JSONObject jsonObject=new JSONObject(response);
//                    JSONArray jsonArray=jsonObject.getJSONArray("MyData");
//                    String name="amin rahman";
//                    for(int i=0;i<jsonArray.length();i++)
//                    {
//                        JSONObject recive=jsonArray.getJSONObject(i);
////                        if (recive.getString("name").equals(name)) {
////                            MyItem Item=new MyItem(recive.getString("name"),recive.getString("role"));
////                            listItem.add(Item);
////                        }
//                        MyItem Item=new MyItem(recive.getString("name"),recive.getString("role"));
//                        listItem.add(Item);
//
//
//                    }
//                    myAdapter=new MyAdapter(listItem,getApplicationContext());
//
//                    myAdapter.notifyDataSetChanged();
//                    recyclerView.setAdapter(myAdapter);
//                    swipeRefreshLayout.setRefreshing(false);
//
//                }catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(MainActivity.this,"Server error",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//        );
//        RequestQueue quque= Volley.newRequestQueue(this);
//        quque.add(stringReques);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.add)
        {
            //LinearLayout linearLayout=findViewById(R.id.bottomsheet);

            final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this,R.style.BottomSheet);
            View bottomSheet= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dailog,(LinearLayout)findViewById(R.id.bottomsheet));
            bottomSheetDialog.setContentView(bottomSheet);
            bottomSheetDialog.show();
//            BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(linearLayout);
//            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);



            bottomSheetDialog.setCanceledOnTouchOutside(true);
            final EditText editTextName=bottomSheetDialog.findViewById(R.id.edtName);
            final EditText editTextRole=bottomSheetDialog.findViewById(R.id.edtRole);
            Button buttonAdd=bottomSheetDialog.findViewById(R.id.buttonAdd);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=editTextName.getText().toString();
                    String role=editTextRole.getText().toString();

                    if (name.equals("rakib")&&role.equals("student"))
                    {
                        final AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle(name);
                        builder.setMessage(role);
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        bottomSheetDialog.dismiss();
                        alertDialog.show();
                    }else {
                        Toast.makeText(MainActivity.this,"Cancel Click",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            bottomSheetDialog.show();
        }
        else if(item.getItemId()==R.id.setting)
        {
            Toast.makeText(MainActivity.this,"Setting Click",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void LoadData(int a)
    {

        progressDialog=new ProgressDialog(this);
        if(a==1)
        {
            progressDialog.setMessage("Loooding...");
            progressDialog.show();
        }

        listItem.clear();

        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("MyData");
                    String name="amin rahman";
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject recive=jsonArray.getJSONObject(i);
//                        if (recive.getString("name").equals(name)) {
//                            MyItem Item=new MyItem(recive.getString("name"),recive.getString("role"));
//                            listItem.add(Item);
//                        }
                        MyItem Item=new MyItem(recive.getString("name"),recive.getString("role"));
                        listItem.add(Item);


                    }
                    myAdapter=new MyAdapter(listItem,getApplicationContext());
                    myAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(myAdapter);

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Server error",Toast.LENGTH_SHORT).show();
            }
        }


        );
        RequestQueue quque= Volley.newRequestQueue(this);
        quque.add(stringRequest);

    }


}
