package pe.netdreams.invasive_pollution.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pe.netdreams.invasive_pollution.Adapters.JugadorAdapter;
import pe.netdreams.invasive_pollution.Model.Jugador;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.FirebaseManager;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class frg_puntaje extends Fragment {

    TextView tvscore, tvlevel;
    EditText tvnombre;
    ImageView btnsave;

    RecyclerView rvRank;
    ArrayList<Jugador> list_jugador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frg_puntaje, container, false);

        list_jugador = new ArrayList<>();

        tvscore = view.findViewById(R.id.tvscore);
        tvlevel = view.findViewById(R.id.tvlevel);
        tvnombre = view.findViewById(R.id.tvnombre);

        btnsave = view.findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManager.setStringValue(getContext(), Constans.NOMBRE_USER, tvnombre.getText().toString());
                FirebaseManager.saveData(getContext());
            }
        });

        rvRank = view.findViewById(R.id.rvRank);

        tvscore.setText(String.valueOf(SharedPreferencesManager.getIntValue(getContext(), Constans.BEST_SCORE)));
        tvlevel.setText(String.valueOf(SharedPreferencesManager.getIntValue(getContext(), Constans.BEST_LEVEL)));
        tvnombre.setText(String.valueOf(SharedPreferencesManager.getStringValue(getContext(), Constans.NOMBRE_USER)));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Jugadores");

        // Ordenar por la propiedad "score" de forma descendente
        Query query = ref.orderByChild("score").limitToLast(100);

        // Agregar un listener para escuchar los cambios en los datos
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_jugador.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println(snapshot.child("score").getValue());
                    Jugador jugador = new Jugador(
                            Integer.parseInt(snapshot.child("score").getValue().toString()),
                            Integer.parseInt(snapshot.child("total_coins").getValue().toString()),
                            Integer.parseInt(snapshot.child("max_lvl").getValue().toString())
                    );
                    jugador.setNombre(snapshot.child("nombre").getValue().toString());
                    list_jugador.add(jugador);
                }

                // Ordenar la lista de jugadores por score de forma descendente
                Collections.sort(list_jugador, new Comparator<Jugador>() {
                    @Override
                    public int compare(Jugador j1, Jugador j2) {
                        return j2.getScore() - j1.getScore();
                    }
                });

                JugadorAdapter jugadorAdapter =  new JugadorAdapter(list_jugador, getContext());

                rvRank.setAdapter(jugadorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar el error aquí
            }
        });

        return view;

    }
}