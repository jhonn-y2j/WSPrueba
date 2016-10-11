package com.expert.jhonn.wsprueba.restApi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jhonn on 11/10/2016.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleySingleton(Context context){
        VolleySingleton.context=context;

    }

    public static synchronized VolleySingleton getInstance(Context context){
        if (volleySingleton==null){
            volleySingleton= new VolleySingleton(context);
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void addRequestQueue(Request request){
        getRequestQueue().add(request);
    }

}
