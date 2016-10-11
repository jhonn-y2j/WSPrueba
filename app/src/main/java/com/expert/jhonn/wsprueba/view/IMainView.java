package com.expert.jhonn.wsprueba.view;

import com.expert.jhonn.wsprueba.LugarAdapter;
import com.expert.jhonn.wsprueba.modelo.Lugar;

import java.util.List;

/**
 * Created by Jhonn on 11/10/2016.
 */
public interface IMainView {

    public void generarLinearLayout();
    public LugarAdapter crearLugarAdapter(List<Lugar> lsLugars);
    public void inicializarAdapter(LugarAdapter lugarAdapter);

    public void irMapa(Lugar lugar);

}
