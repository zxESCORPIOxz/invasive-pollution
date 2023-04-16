package pe.netdreams.invasive_pollution.Fragments;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.Adapters.AmmoAdapter;
import pe.netdreams.invasive_pollution.Adapters.GunAdapter;
import pe.netdreams.invasive_pollution.Adapters.NaveAdapter;
import pe.netdreams.invasive_pollution.Model.Ammo;
import pe.netdreams.invasive_pollution.Model.Gun;
import pe.netdreams.invasive_pollution.Model.Nave;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.DataBase;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;
import pe.netdreams.invasive_pollution.listener.RecyclerItemClickListener;

public class frg_garage extends Fragment implements View.OnClickListener {
    RecyclerView rvNaves, rvAmmos, rvGuns;
    ImageView ivnave;
    TextView tvblindage, tvvida, tvcadencia, tvdamage;

    TextView btnnaves;
    TextView btnarmas;
    TextView btnbalas;
    ArrayList<Nave> list_naves;
    ArrayList<Ammo> list_ammos;
    ArrayList<Gun> list_gun;
    int statelis=0;
    YoYo.AnimationComposer animationGlobal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frg_garage, container, false);

        tvblindage = view.findViewById(R.id.tvblindage);
        tvvida = view.findViewById(R.id.tvvida);
        tvcadencia = view.findViewById(R.id.tvcadencia);
        tvdamage = view.findViewById(R.id.tvdamage);

        rvNaves = view.findViewById(R.id.rvNaves);
        rvAmmos = view.findViewById(R.id.rvAmmos);
        rvGuns = view.findViewById(R.id.rvGuns);

        btnnaves = view.findViewById(R.id.btnnaves);
        btnnaves.setOnClickListener(this);
        btnarmas = view.findViewById(R.id.btnarmas);
        btnarmas.setOnClickListener(this);
        btnbalas = view.findViewById(R.id.btnbalas);
        btnbalas.setOnClickListener(this);

        ivnave = view.findViewById(R.id.ivnave);

        list_naves = DataBase.getNaves(getContext());

        NaveAdapter naveAdapter = new NaveAdapter(list_naves, getContext());

        rvNaves.setAdapter(naveAdapter);

        rvNaves.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rvNaves, new RecyclerItemClickListener.OnItemClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onItemClick(View view, int position) {
                    YoYo.with(Techniques.SlideOutRight)
                        .duration(500)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                SharedPreferencesManager.setIntValue(getContext(), Constans.NAVE_SET, list_naves.get(position).getId());
                                naveAdapter.notifyDataSetChanged();
                                ivnave.setImageResource(list_naves.get(position).getRecurso());
                                YoYo.with(Techniques.SlideInLeft)
                                    .duration(500)
                                        .onEnd(new YoYo.AnimatorCallback() {
                                            @Override
                                            public void call(Animator animator) {
                                                getStats();
                                            }
                                        })
                                    .playOn(ivnave);
                                rvNaves.scrollToPosition(SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET)-1);
                            }
                        })
                        .playOn(ivnave);
                }
                @Override
                public void onLongItemClick(View view, int position) {

                }
            })
        );

        list_ammos = DataBase.getAmmos(getContext());

        AmmoAdapter ammoAdapter = new AmmoAdapter(list_ammos, getContext());

        rvAmmos.setAdapter(ammoAdapter);

        rvAmmos.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rvAmmos, new RecyclerItemClickListener.OnItemClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onItemClick(View view, int position) {
                    SharedPreferencesManager.setIntValue(getContext(), Constans.AMMO_SET, list_ammos.get(position).getId());
                    ammoAdapter.notifyDataSetChanged();
                    YoYo.with(Techniques.Swing)
                            .duration(500)
                            .playOn(ivnave);
                    getStats();
                }
                @Override
                public void onLongItemClick(View view, int position) {

                }
            })
        );

        list_gun = DataBase.getGuns(getContext());

        GunAdapter gunAdapter = new GunAdapter(list_gun, getContext());

        rvGuns.setAdapter(gunAdapter);

        rvGuns.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rvGuns, new RecyclerItemClickListener.OnItemClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onItemClick(View view, int position) {
                        SharedPreferencesManager.setIntValue(getContext(), Constans.GUN_SET, list_gun.get(position).getId());
                        gunAdapter.notifyDataSetChanged();
                        YoYo.with(Techniques.Swing)
                                .duration(500)
                                .playOn(ivnave);
                        getStats();
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        ivnave.setImageResource(list_naves.get(SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET)).getRecurso());
        rvNaves.scrollToPosition(SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET)-1);

        getStats();

        return view;
    }

    public void getStats(){
        YoYo.AnimationComposer animationGlobal = YoYo.with(Techniques.Swing)
                .duration(500);
        animationGlobal.playOn(tvblindage);
        animationGlobal.playOn(tvvida);
        animationGlobal.playOn(tvcadencia);
        animationGlobal.playOn(tvdamage);
        tvblindage.setText(""+list_naves.get(SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET)).getBlindaje());
        tvvida.setText(""+list_naves.get(SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET)).getVida());
        tvcadencia.setText(""+list_naves.get(SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET)).getVelocidad());
        tvdamage.setText(""+
                (list_ammos.get(SharedPreferencesManager.getIntValue(getContext(), Constans.AMMO_SET)).getDamage()+
                list_gun.get(SharedPreferencesManager.getIntValue(getContext(), Constans.GUN_SET)).getDamage()));
    }

    @Override
    public void onClick(View v) {

        YoYo.AnimationComposer animation = YoYo.with(Techniques.SlideInRight)
                .duration(500);

        YoYo.AnimationComposer animationComposer = YoYo.with(Techniques.Swing)
                .duration(500);
        switch (v.getId()){
            case R.id.btnnaves:{
                if(statelis!=1){
                    rvNaves.setVisibility(View.VISIBLE);
                    rvAmmos.setVisibility(View.GONE);
                    rvGuns.setVisibility(View.GONE);
                    btnnaves.setBackgroundResource(R.drawable.rectangulo_top_purple_acent);
                    btnarmas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnbalas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    statelis=1;
                } else if (statelis == 1) {
                    rvNaves.setVisibility(View.GONE);
                    rvAmmos.setVisibility(View.GONE);
                    rvGuns.setVisibility(View.GONE);
                    btnnaves.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnarmas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnbalas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    statelis=0;
                }
                animation.playOn(rvNaves);
                animationComposer.playOn(btnnaves);
            }break;
            case R.id.btnarmas:{
                if(statelis!=2){
                    rvNaves.setVisibility(View.GONE);
                    rvAmmos.setVisibility(View.GONE);
                    rvGuns.setVisibility(View.VISIBLE);
                    btnnaves.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnarmas.setBackgroundResource(R.drawable.rectangulo_top_purple_acent);
                    btnbalas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    statelis=2;
                } else if (statelis == 2) {
                    rvNaves.setVisibility(View.GONE);
                    rvAmmos.setVisibility(View.GONE);
                    rvGuns.setVisibility(View.GONE);
                    btnnaves.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnarmas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnbalas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    statelis=0;
                }
                animation.playOn(rvGuns);
                animationComposer.playOn(btnarmas);
            }break;
            case R.id.btnbalas:{
                if(statelis!=3){
                    rvNaves.setVisibility(View.GONE);
                    rvAmmos.setVisibility(View.VISIBLE);
                    rvGuns.setVisibility(View.GONE);
                    btnnaves.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnarmas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnbalas.setBackgroundResource(R.drawable.rectangulo_top_purple_acent);
                    statelis=3;
                } else if (statelis == 3) {
                    rvNaves.setVisibility(View.GONE);
                    rvAmmos.setVisibility(View.GONE);
                    rvGuns.setVisibility(View.GONE);
                    btnnaves.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnarmas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    btnbalas.setBackgroundResource(R.drawable.rectangulo_top_purple_light);
                    statelis=0;
                }
                animation.playOn(rvAmmos);
                animationComposer.playOn(btnbalas);
            }break;
        }
    }
}