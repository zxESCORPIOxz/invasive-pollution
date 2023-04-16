package pe.netdreams.invasive_pollution.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import pe.netdreams.invasive_pollution.Adapters.IdiomaAdapter;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;


public class frm_config extends Fragment {

    Spinner spinnerIdioma;

    SeekBar sbrmusic, skbsound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm_config, container, false);

        spinnerIdioma = view.findViewById(R.id.spinnerIdioma);
        sbrmusic = view.findViewById(R.id.seekBar_musica);
        skbsound = view.findViewById(R.id.seekBar_musica);

        sbrmusic.setProgress(SharedPreferencesManager.getVolume(getContext(), Constans.VOLUME_MUSIC));
        skbsound.setProgress(SharedPreferencesManager.getVolume(getContext(), Constans.VOLUME_SOUND));

        IdiomaAdapter adapter = new IdiomaAdapter(getContext(), Constans.getListaIdioma(getContext()));
        spinnerIdioma.setAdapter(adapter);



        return view;
    }
}