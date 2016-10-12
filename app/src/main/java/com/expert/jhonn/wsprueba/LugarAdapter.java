package com.expert.jhonn.wsprueba;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.expert.jhonn.wsprueba.modelo.Lugar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jhonn on 11/10/2016.
 */
public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.LugarViewHolder> {

    private List<Lugar> lugarList;

    private Context context;

    private LayoutInflater layoutInflater;

    private onItemClickListener ItemClickListener;

    public LugarAdapter(Context context, List<Lugar> lugars,onItemClickListener ItemClickListener) {
        layoutInflater = LayoutInflater.from(context);
        this.lugarList = lugars;
        this.context = context;
        this.ItemClickListener=ItemClickListener;
    }

    @Override
    public LugarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item, parent, false);
        LugarViewHolder lugarViewHolder = new LugarViewHolder(v);
        return lugarViewHolder;
    }

    @Override
    public void onBindViewHolder(LugarViewHolder holder, int position) {
        Lugar lugar = lugarList.get(position);
        holder.description.setText(lugar.getDescripcion());
        holder.coordena.setText(lugar.getUbicacion());
        Glide.with(holder.imageView.getContext()).load(lugar.getImagen()).into(holder.imageView);
        holder.setOnItemClickListener(lugar,ItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (lugarList!=null) {
            return lugarList.size();
        }
        return 0;
    }

    public static class LugarViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.text_descripcion)
        TextView description;
        @BindView(R.id.text_coordenada)
        TextView coordena;

        public LugarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Lugar element, final onItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.itemCLick(element);
                }
            });
        }
    }
}
