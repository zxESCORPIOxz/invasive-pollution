package pe.netdreams.invasive_pollution.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.Model.Nave;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class NaveAdapter extends RecyclerView.Adapter<NaveAdapter.ViewHolder> {

    private ArrayList<Nave> mValues;
    private Context ctx;
    private int equipado;

    public NaveAdapter(ArrayList<Nave> mValues, Context ctx) {
        this.mValues = mValues;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public NaveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nave_list, parent, false);
        return new NaveAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull NaveAdapter.ViewHolder holder, int position) {

        this.equipado = SharedPreferencesManager.getIntValue(ctx, Constans.NAVE_SET);

        if(equipado == mValues.get(position).getId()){
            holder.content.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.orange));
        }else {
            holder.content.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.purple_acent));
        }

        holder.ivnave.setImageResource(mValues.get(position).getRecurso());
        holder.tvnombre.setText(mValues.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView content;
        ImageView ivnave;
        TextView tvnombre;
        public ViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.content);
            ivnave = view.findViewById(R.id.ivnave);
            tvnombre = view.findViewById(R.id.tvnombre);
        }

    }
}

