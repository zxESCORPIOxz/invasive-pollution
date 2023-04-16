package pe.netdreams.invasive_pollution.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.Model.Jugador;
import pe.netdreams.invasive_pollution.Model.Nave;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.ViewHolder>  {
    private ArrayList<Jugador> mValues;
    private Context ctx;

    public JugadorAdapter(ArrayList<Jugador> mValues, Context ctx) {
        this.mValues = mValues;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public JugadorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank_list, parent, false);
        return new JugadorAdapter.ViewHolder(view);
    }

    @SuppressLint({"SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull JugadorAdapter.ViewHolder holder, int position) {
        Jugador jugador = mValues.get(position);

        holder.tvnombre.setText(jugador.getNombre());
        holder.tvscore.setText(""+jugador.getScore());
        holder.tvlevel.setText(""+jugador.getMax_lvl());
        if(position+1 == 1){
            holder.tvtop.setBackgroundResource(R.drawable.rank1);
        }
        else if(position+1 == 2){
            holder.tvtop.setBackgroundResource(R.drawable.rank2);
        }
        else if(position+1 == 3){
            holder.tvtop.setBackgroundResource(R.drawable.rank3);
        }else{
            holder.tvtop.setText(""+(position+1));
        }

        if(SharedPreferencesManager.getStringValue(ctx, Constans.NOMBRE_USER).equals(jugador.getNombre()))
            holder.contPlayer.setBackgroundResource(R.drawable.rectangulo_gray_dark);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnombre, tvlevel, tvscore, tvtop;
        RelativeLayout contPlayer;
        public ViewHolder(@NonNull View view) {
            super(view);
            contPlayer = view.findViewById(R.id.contPlayer);

            tvnombre = view.findViewById(R.id.tvnombre);
            tvlevel = view.findViewById(R.id.tvlevel);
            tvscore = view.findViewById(R.id.tvscore);
            tvtop = view.findViewById(R.id.tvtop);
        }
    }
}
