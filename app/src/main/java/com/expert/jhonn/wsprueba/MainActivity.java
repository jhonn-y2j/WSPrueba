package com.expert.jhonn.wsprueba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.expert.jhonn.wsprueba.modelo.Lugar;
import com.expert.jhonn.wsprueba.presenter.IMainPresenter;
import com.expert.jhonn.wsprueba.presenter.MainPresenter;
import com.expert.jhonn.wsprueba.view.IMainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView, onItemClickListener{

    RecyclerView recyclerView;
    IMainPresenter iMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        iMainPresenter = new MainPresenter(getApplicationContext(),this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void generarLinearLayout() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public LugarAdapter crearLugarAdapter(List<Lugar> lsLugars) {
        LugarAdapter lugarAdapter= new LugarAdapter(getApplicationContext(),lsLugars,this);
        return lugarAdapter;
    }

    @Override
    public void inicializarAdapter(LugarAdapter lugarAdapter) {
        recyclerView.setAdapter(lugarAdapter);
        lugarAdapter.notifyDataSetChanged();
    }

    @Override
    public void irMapa(Lugar lugar) {
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", lugar.getLatitud()+","+lugar.getLongitud()).build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(MainActivity.class.getSimpleName(), "Error" + lugar.getLatitud()+","+lugar.getLongitud() );
        }
    }

    @Override
    public void itemCLick(Lugar lugar) {
        irMapa(lugar);
        Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
    }
}
