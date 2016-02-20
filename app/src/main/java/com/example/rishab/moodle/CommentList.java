package com.example.rishab.moodle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class CommentList extends ActionBarActivity {
//    String URL="http://127.0.0.1:8000/";
    String URL="http://10.0.2.2:8000/courses/course.json/cop290/threads";
    String course_code="cop290";
    String[] data_title;
    String[] data_desc;
    String[] data_id;

//    JSONObject data=new JSONObject("{assignments: [], registered: {starting_date: 2016-01-01 00:00:00, id: 1, professor: 5, semester: 2, ending_date: 2016-05-10 00:00:00, year_: 2016, course_id: 1}, course_threads: [{user_id: 5, description: this is a test thread, title: test, created_at: 2016-02-18 23:14:35, registered_course_id: 1, updated_at: 2016-02-18 23:14:35, id: 1}], course: {code: cop290, name: Design Practices in Computer Science, description: Design Practices in Computer Science., credits: 3, id: 1, l_t_p: 0-0-6}, grades: [], tab: threads, year: 2016, sem: 2, resources: [], previous: []}");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        String[] t={"d","d","d","d"};
        String[] d={"d","d","d","d"};
        CustomListAdapter a=new CustomListAdapter(this,t,d);
        ListView listview=(ListView)findViewById(R.id.course_threads);
        listview.setAdapter(a);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idd=data_id[position];
                Intent intent=new Intent(CommentList.this,ThreadComments.class);
                intent.putExtra("id",idd);
            }
        });
//        l.OnItemClickListener();
//        String course_code=getIntent().getStringExtra("course_code");
//        URL+="courses/course.json/"+course_code+"/threads";
        Log.d("ds", "d" + ":" + URL);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                //add the logic after recieving the data
                try {
                    Log.d("response",string);
                    JSONObject data = new JSONObject("s");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment_list, menu);
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
    public void creatThread(View view){
        Intent intent=new Intent(CommentList.this,NewThread.class);
        intent.putExtra("course_code",course_code);
        startActivity(intent);
    }
}
