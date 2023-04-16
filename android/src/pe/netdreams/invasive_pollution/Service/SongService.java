package pe.netdreams.invasive_pollution.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class SongService extends Service {

    public MediaPlayer mediaPlayer;
    public float volume;
    double maxVolume = 100;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        double currVolume = 50;
        volume = (float) (1-(Math.log(maxVolume - currVolume) / Math.log(maxVolume)));
        mediaPlayer = MediaPlayer.create(this, R.raw.fondo2);
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        mediaPlayer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        double currVolume = 50;
        volume = (float) (1-(Math.log(maxVolume - currVolume) / Math.log(maxVolume)));
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.setLooping(true);
        return super.onStartCommand(intent, flags, startId);
    }
}
