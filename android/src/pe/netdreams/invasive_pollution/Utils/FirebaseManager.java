package pe.netdreams.invasive_pollution.Utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import pe.netdreams.invasive_pollution.Model.Jugador;

public class FirebaseManager {

    public static void saveData(Context context) {
        // Inicializar Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Obtener una referencia a la ubicación deseada en la base de datos
        DatabaseReference jugadoresRef = database.getReference("Jugadores");

        Jugador jugador = new Jugador(
                SharedPreferencesManager.getIntValue(context, Constans.BEST_SCORE),
                SharedPreferencesManager.getIntValue(context, Constans.TOTAL_COINS),
                SharedPreferencesManager.getIntValue(context, Constans.BEST_LEVEL)
        );

        String id;

        if(Objects.isNull(SharedPreferencesManager.getStringValue(context, Constans.ID_USER))){
            id = jugadoresRef.push().getKey();
            SharedPreferencesManager.setStringValue(context,Constans.ID_USER, id);
        }else{
            id = SharedPreferencesManager.getStringValue(context, Constans.ID_USER);
        }

        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (task.isSuccessful()) {
                        jugador.setToken(task.getResult());

                        if(Objects.isNull(SharedPreferencesManager.getStringValue(context, Constans.NOMBRE_USER))){
                            jugador.setNombre(id);
                            SharedPreferencesManager.setStringValue(context, Constans.NOMBRE_USER, id);
                        }else{
                            jugador.setNombre(SharedPreferencesManager.getStringValue(context, Constans.NOMBRE_USER));
                        }

                        jugadoresRef.child(id).setValue(jugador);

                        return;
                    }
                }
            });

    }

}
