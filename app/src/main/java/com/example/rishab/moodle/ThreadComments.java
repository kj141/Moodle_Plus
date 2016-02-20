package com.example.rishab.moodle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class ThreadComments extends ActionBarActivity {
    String URL="http://10.0.2.2:8000/threads/thread.json/";
    String t_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_comments);
        String id=getIntent().getStringExtra("id");
        t_id=id;
        fillFields();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thread_comments, menu);
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
    public void addComment(View view){
        Intent intent=new Intent(ThreadComments.this,NewThreadComment.class);
        intent.putExtra("id",t_id);
        startActivityForResult(intent,100);
//        setResult(RESULT_OK, intent);
//        finish();
    }
    public void fillFields(){
        URL+=t_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                //add the logic after recieving the data
                try {
                    Log.d("response", string);
                    JSONObject data = new JSONObject("s");
                    String title,description;
                    String[] comments={"ss","ss","ss","ss"};
                    CustomCommentListAdapter adapter=new CustomCommentListAdapter(ThreadComments.this,comments);
                    ListView listView=(ListView)findViewById(R.id.comment_list);
                    listView.setAdapter(adapter);

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
    public void onActivityResult(int req_code,int resultcode,Intent data){
        fillFields();
    }
}
