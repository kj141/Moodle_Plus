package com.example.rishab.moodle;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Rishab on 18-02-2016.
 */
public class SingletonNetworkClass {
    private static SingletonNetworkClass single_netwrk;
    private RequestQueue requestQueue;
    private static Context mcontext;

    //setting the context
    public SingletonNetworkClass(Context context) {
        mcontext = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonNetworkClass getInstance(Context context) {
        if (single_netwrk == null) {
            single_netwrk = new SingletonNetworkClass(context);
        }
        return single_netwrk;
    }

    //get the request queue
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }

    //add a generic request to the the queue
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
