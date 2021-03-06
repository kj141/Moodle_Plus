package com.example.rishab.moodle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;


public class NewThread extends ActionBarActivity {
    String URL="http://127.0.0.1:8000/";
    String course_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thread);
        course_code=getIntent().getStringExtra("course_code");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_thread, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void confirm(View view){

        EditText editText=(EditText)findViewById(R.id.thread_title);
        String title=(String) editText.getText().toString();
        editText=(EditText)findViewById(R.id.Thread_Description);
        String description=(String) editText.getText().toString();
        URL+="threads/new.json?title="+title+"&description="+description+"&course_code="+course_code;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                //add the logic after recieving the data
                try {
                    Log.d("response", string);
                    JSONObject data = new JSONObject(string);
                    //intent to start the show all threads activity
                }
                catch (JSONException e){
                    Log.d("check","error in getting the threads");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError string) {
                Log.d("check",string.toString());

            }

            ;
        });
        //the following is the global request queue to prevent construction of
        // request queue again and again
        SingletonNetworkClass.getInstance(this).addToRequestQueue(stringRequest);
        //        queue.add(stringRequest);

    }
}
