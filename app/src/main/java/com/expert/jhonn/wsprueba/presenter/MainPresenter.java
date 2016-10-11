package com.expert.jhonn.wsprueba.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.expert.jhonn.wsprueba.LugarAdapter;
import com.expert.jhonn.wsprueba.modelo.Lugar;
import com.expert.jhonn.wsprueba.restApi.VolleySingleton;
import com.expert.jhonn.wsprueba.view.IMainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhonn on 11/10/2016.
 */
public class MainPresenter implements IMainPresenter{

    private Context context;
    private IMainView iMainView;
    private LugarAdapter lugarAdapter;
    private List<Lugar> lugars;
    private final String URL="http://touchmobile.pe/mobiletest.json";

    public MainPresenter(Context context, IMainView iMainView) {
        this.context = context;
        this.iMainView = iMainView;
        obtenerLugares();
    }

    @Override
    public void obtenerLugares() {
        JsonArrayRequest jsonObjectRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    lugars=parseJson(response);
                    mostrarLugaresRV();
                    Log.d(MainPresenter.class.getName(),"Error Volley ;(");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(MainPresenter.class.getName(),"Error Volley "+ error.getMessage());
            }
        });
        VolleySingleton.getInstance(context).addRequestQueue(jsonObjectRequest);

    }

    @Override
    public void mostrarLugaresRV() {
        lugarAdapter=iMainView.crearLugarAdapter(lugars);
        iMainView.inicializarAdapter(lugarAdapter);
        iMainView.generarLinearLayout();

    }

    public List<Lugar> parseJson(JSONArray jsonObject) throws JSONException {
        final String B="B";
        final String LAT = "G";
        final String LONG = "H";
        final String L = "L";

        List<Lugar> lugars= new ArrayList<>();
        for(int i = 0; i < jsonObject.length(); i++) {
            Lugar lugar= new Lugar();
            JSONObject lugarObject = jsonObject.getJSONObject(i);
            lugar.setDescripcion(lugarObject.getString(B));
            lugar.setLatitud(lugarObject.getString(LAT));
            lugar.setLongitud(lugarObject.getString(LONG));
            lugar.setImagen(lugarObject.getString(L));
            lugars.add(lugar);
        }

        return lugars;
    }

}
