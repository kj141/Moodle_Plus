package com.example.rishab.moodle;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rishab on 20-02-2016.
 */
public class Request {
    JSONObject data;
    Context context;
    String url;
    public Request(Context c, String u){
        context=c;
        url=u;
    }
    public void request() {
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                //add the logic after recieving the data
                try {
                    Log.d("response2", string);
                    data = new JSONObject(string);
//                    JSONObject data = new JSONObject("s");


                } catch (JSONException e) {
                    Log.d("check", "error in getting the threads");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError string) {
                Log.d("check", string.toString());

            }

            ;
        });
        //the following is the global request queue to prevent construction of
        // request queue again and again
        SingletonNetworkClass.getInstance(context).addToRequestQueue(stringRequest);
        //        queue.add(stringRequest);

    }
    }
