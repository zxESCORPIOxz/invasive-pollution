package pe.netdreams.invasive_pollution.Fragments;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import pe.netdreams.invasive_pollution.AndroidLauncher;
import pe.netdreams.invasive_pollution.MainActivity;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.DataBase;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class frg_home extends Fragment {
    TextView tvblindage, tvvida, tvcadencia, tvdamage;

    ImageView ivnave;
    ImageView ivammo, ivgun;
    TextView tvammo, tvgun;
    ImageView btnplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_home, container, false);

        tvblindage = view.findViewById(R.id.tvblindage);
        tvvida = view.findViewById(R.id.tvvida);
        tvcadencia = view.findViewById(R.id.tvcadencia);
        tvdamage = view.findViewById(R.id.tvdamage);

        ivnave = view.findViewById(R.id.ivnave);

        btnplay = view.findViewById(R.id.btnplay);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing)
                    .duration(200)
                    .playOn(btnplay);

                YoYo.with(Techniques.SlideOutUp)
                        .duration(200)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                goToActivity();
                            }
                        })
                        .playOn(ivnave);
            }
        });

        ivammo = view.findViewById(R.id.ivammo);
        ivgun = view.findViewById(R.id.ivgun);

        tvammo = view.findViewById(R.id.tvammo);
        tvgun = view.findViewById(R.id.tvgun);

        getStats();

        YoYo.AnimationComposer animation = YoYo.with(Techniques.Shake)//wave
                .duration(10000)
                .repeat(-1);

        animation.playOn(ivnave);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        YoYo.with(Techniques.SlideInUp)
                .duration(200)
                .playOn(ivnave);
    }

    @SuppressLint("SetTextI18n")
    public void getStats(){
        int nave = SharedPreferencesManager.getIntValue(getContext(), Constans.NAVE_SET);
        int ammo = SharedPreferencesManager.getIntValue(getContext(), Constans.AMMO_SET);
        int gun = SharedPreferencesManager.getIntValue(getContext(), Constans.GUN_SET);

        tvblindage.setText(""+ DataBase.getNaveById(getContext(), nave).getBlindaje());
        tvvida.setText(""+ DataBase.getNaveById(getContext(), nave).getVida());
        tvcadencia.setText(""+ DataBase.getNaveById(getContext(), nave).getVelocidad());
        tvdamage.setText(""+(DataBase.getAmmoById(getContext(), ammo).getDamage()
                +DataBase.getGunById(getContext(), gun).getDamage()));

        ivnave.setImageResource(DataBase.getNaveById(getContext(), nave).getRecurso());

        ivammo.setImageResource(DataBase.getAmmoById(getContext(), ammo).getRecurso());
        ivgun.setImageResource(DataBase.getGunById(getContext(), gun).getRecurso());

        tvammo.setText(""+ DataBase.getAmmoById(getContext(), ammo).getNombre());
        tvgun.setText(""+ DataBase.getGunById(getContext(), gun).getNombre());
    }
    public void goToActivity() {
        AndroidLauncher launcher = new AndroidLauncher();
        Intent intent = new Intent(getContext(), launcher.getClass());
        startActivity(intent);
    }
}