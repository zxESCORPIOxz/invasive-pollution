package pe.netdreams.invasive_pollution;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import pe.netdreams.invasive_pollution.Model.Ammo;
import pe.netdreams.invasive_pollution.Model.Gun;
import pe.netdreams.invasive_pollution.Model.Nave;
import pe.netdreams.invasive_pollution.Service.SongService;
import pe.netdreams.invasive_pollution.Utils.Constans;
import pe.netdreams.invasive_pollution.Utils.DataBase;
import pe.netdreams.invasive_pollution.Utils.FirebaseManager;
import pe.netdreams.invasive_pollution.Utils.SharedPreferencesManager;

public class AndroidLauncher extends AndroidApplication implements MainGame.MyGameCallback {

	private Handler handler;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);// Finaliza la actividad actual

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		Nave nave = DataBase.getNaveById(this, SharedPreferencesManager.getIntValue(this, Constans.NAVE_SET));
		Ammo ammo = DataBase.getAmmoById(this, SharedPreferencesManager.getIntValue(this, Constans.AMMO_SET));
		Gun gun = DataBase.getGunById(this, SharedPreferencesManager.getIntValue(this, Constans.GUN_SET));

		MainGame mainGame = new MainGame(
				nave.getId(),
				ammo.getId(),
				gun.getId(),
				nave.getBlindaje(),
				nave.getVelocidad(),
				nave.getVida(),
				ammo.getDamage() + gun.getDamage(),
				SharedPreferencesManager.getIntValue(this, Constans.TOTAL_COINS),
				SharedPreferencesManager.getIntValue(this, Constans.BEST_SCORE),
				SharedPreferencesManager.getIntValue(this, Constans.BEST_LEVEL),
				SharedPreferencesManager.getIntValue(this, Constans.VOLUME_SOUND)
		);
		mainGame.setMyGameCallback(this);
		initialize(mainGame, config);

		handler = new Handler(Looper.getMainLooper());
	}


	@Override
	public void onStartActivityScoreBoard(int score, int coins, int gameCounter) {

	}

	@Override
	public void saveCoinsToSharedPref(int coins) {
		SharedPreferencesManager.setIntValue(this, Constans.TOTAL_COINS, coins);
	}

	@Override
	public void saveBestScoreToSharedPref(int score) {
		SharedPreferencesManager.setIntValue(this, Constans.BEST_SCORE, score);
		FirebaseManager.saveData(this);
	}

	@Override
	public void saveBestLevelToSharedPref(int level) {
		SharedPreferencesManager.setIntValue(this, Constans.BEST_LEVEL, level);
	}

	@Override
	public void onStartActivityB() {

	}

	@Override
	public void onStartSomeActivity(int someParameter, String someOtherParameter) {

	}

	@Override
	public void onBackPress() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				AndroidLauncher.super.onBackPressed();
				finish();
			}
		});
		MainActivity.STATE = MainActivity.STATE_IN;
	}

}
