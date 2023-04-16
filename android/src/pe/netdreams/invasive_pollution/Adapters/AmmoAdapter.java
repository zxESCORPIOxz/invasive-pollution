package pe.netdreams.invasive_pollution.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.Model.Ammo;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class AmmoAdapter extends RecyclerView.Adapter<AmmoAdapter.ViewHolder> {

    private ArrayList<Ammo> mValues;
    private Context ctx;
    private int equipado;

    public AmmoAdapter(ArrayList<Ammo> mValues, Context ctx) {
        this.mValues = mValues;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AmmoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ammo_list, parent, false);
        return new AmmoAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull AmmoAdapter.ViewHolder holder, int position) {
        this.equipado = SharedPreferencesManager.getIntValue(ctx, Constans.AMMO_SET);
        if(equipado == mValues.get(position).getId()){
            holder.crselected.setImageResource(R.drawable.btn_cir_orange);
        }else {
            holder.crselected.setImageResource(R.drawable.btn_cir_purple);
        }

        holder.lvammo.setImageResource(mValues.get(position).getRecurso());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView crselected;
        ImageView lvammo;
        public ViewHolder(View view) {
            super(view);
            crselected = view.findViewById(R.id.crselected);
            lvammo = view.findViewById(R.id.lvammo);
        }

    }
}