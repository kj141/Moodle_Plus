package com.example.rishab.moodle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


public class All_Grades extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__grades);
        ListView listView = (ListView)findViewById(R.id.list2);
        Intent intent_r = getIntent();
        String URL = "/default/grades.json";
        Request a = new Request(this,URL);
        a.request();
        while(a.data==null){}
        JSONObject data_received = a.data;
        ArrayList<HashMap<String, String>> gradelist = new ArrayList<HashMap<String, String>>();
        String newline = System.getProperty("line.separator");
        int no_of_courses=0;
        int[] ids = new int[20];
        for(int i=0;i<20;i++){ids[i]=-1;}
        float[] current_scores = new float[20];
        float[] max_scores = new float[20];
        String[] codes = new String[20];
        if(data_received!=null)
        {
            try{
                JSONArray courses = data_received.getJSONArray("courses");
                for(int i=0;i<courses.length();i++)
                {
                    JSONObject c = courses.getJSONObject(i);
                    int id = c.getInt("id");
                    if(!(ids[id]==id)){no_of_courses++;ids[id]=id;codes[id] = c.getString("code");}
                }
                JSONArray grd = data_received.getJSONArray("grades");
                for(int i=0;i<grd.length();i++)
                {
                    JSONObject c = grd.getJSONObject(i);
                    int rcid = c.getInt("registered_course_id");
                    float weight = BigDecimal.valueOf(c.getDouble("weightage")).floatValue();
                    String name = c.getString("name");
                    float out_of = BigDecimal.valueOf(c.getDouble("out_of")).floatValue();
                    float score = BigDecimal.valueOf(c.getDouble("score")).floatValue();
                    float ratio = weight/out_of;
                    current_scores[rcid] = current_scores[rcid] + (ratio*score);
                    max_scores[rcid] = max_scores[rcid] + (weight);
                }
                HashMap<String, String> course_grade = new HashMap<String, String>();
                for(int i=0;i<20;i++)
                {
                    if(ids[i]==i)
                    {
                        course_grade.put("key1",codes[i]);
                        course_grade.put("key2","Current Grade: "+Float.toString(current_scores[i])+" out of "+Float.toString(max_scores[i]));
                        gradelist.add(course_grade);
                        ListAdapter adapt = new SimpleAdapter(
                                All_Grades.this, gradelist,
                                R.layout.list_item2, new String[] { "key1", "key2"}, new int[] { R.id.t1,
                                R.id.t2});
                        listView.setAdapter(adapt);
                    }

                }

            }
            catch(JSONException E){
                E.printStackTrace();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all__grades, menu);
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
}
