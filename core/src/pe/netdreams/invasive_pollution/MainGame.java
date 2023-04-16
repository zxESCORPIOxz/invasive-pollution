package pe.netdreams.invasive_pollution;

import static pe.netdreams.invasive_pollution.Models.Player.x;
import static pe.netdreams.invasive_pollution.Models.Player.y;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Random;

import pe.netdreams.invasive_pollution.Models.Ammo;
import pe.netdreams.invasive_pollution.Models.Ammo_enemy;
import pe.netdreams.invasive_pollution.Models.Enemy;
import pe.netdreams.invasive_pollution.Models.Player;

public class MainGame extends ApplicationAdapter {
	public static int REC_MONEY = 0;
	public static int SCORE = 0;
	public static int LEVEL = 0;
	public static final int STATE_GAME_OVER = 0;
	public static final int STATE_PAUSE = 1;
	public static final int STATE_RUNNING= 2;
	public static final int STATE_WIN= 3;
	public static int STATE = STATE_RUNNING;
	public static Player player;
	SpriteBatch batch;
	public static Texture img;
	public static Texture img_bullet;
	public static Texture img_bullet_enemy;
	Texture barra_hp, barra_hp_r, barra_hp_g, barra_hp_o;
	Texture fondo;
	Texture fondo_win;
	Texture fondo_lose;
	Texture fondo_degradado;
	Texture fondo_cont_top;
	Texture fondo_cont_botton;
	Texture game_over_logo;
	Texture win_logo;
	Texture logo;
	Texture bg_text;
	Texture ic_corazon, ic_score, ic_money, ic_level;
	private Texture whiteTexture;

	int nave, ammo, gun;
	int currCoins;
	int currScore;
	int currLevel;
	float volume;
	public static int cant_enemys_w = 6;
	public static int cant_enemys_h = 6;
	public static int spacing;

	public static ArrayList<Enemy> list_enemys;
	public static ArrayList<Ammo_enemy> list_ammo_enemy;
	public static boolean dir_enemys = false;

	private static MyGameCallback myGameCallback;

	public static int hec;
	public static Sound sound_bala;
	private static Sound sound_fondo;
	private static Sound sound_die;
	private static Sound sound_game_over, sound_win, sound_level_up;
	ShapeRenderer shapeRenderer;
	public static FreeTypeFontGenerator generator;
	public static BitmapFont font;
	public static BitmapFont font_damage;
	public static BitmapFont font_title;
	public static BitmapFont font_resume;
	public static float speed_enemy_increase =.05f;
	public static int blindaje;
	public static float speed;
	public static int hp;
	public static int damage;

	public static int nave_blindaje;
	public static float nave_speed;
	public static int nave_hp;
	public static int nave_damage;
	public Sprite btnreintentar, btnsalir, btnnextlevel, btnpause, btncontinuar;
	public OrthographicCamera oCam;
	private static final GlyphLayout glyphLayout = new GlyphLayout();
	private Music music;
	float stateTime = 0f;
	public static Animation<Texture> animation_ex;
	public MainGame(int nave, int ammo, int gun, int nave_blindaje, float nave_speed, int nave_hp, int nave_damage, int currCoins, int currScore, int currLevel, float volume) {
		this.nave = nave;
		this.ammo = ammo;
		this.gun = gun;

		MainGame.nave_blindaje = nave_blindaje;
		MainGame.nave_speed = nave_speed;
		MainGame.nave_hp = nave_hp;
		MainGame.nave_damage = nave_damage;

		this.currCoins = currCoins;
		this.currScore = currScore;
		this.currLevel = currLevel;

		double currVolume = 100;
		this.volume = (float) (1-(Math.log(100 - currVolume) / Math.log(100)));
		resetStates();
	}

	public void resetStates(){
		if(currScore < SCORE){
			currScore = SCORE;
		}
		if(currLevel < LEVEL){
			currLevel = LEVEL;
		}

		currCoins+=REC_MONEY;

		SCORE = 0;
		REC_MONEY = 0;
		LEVEL = 1;

		blindaje = MainGame.nave_blindaje;
		speed = MainGame.nave_speed;
		hp = MainGame.nave_hp;
		damage = MainGame.nave_damage;

		STATE = STATE_RUNNING;
	}
	public interface MyGameCallback {
		public void onStartActivityScoreBoard(int score, int coins, int gameCounter);

		public void saveCoinsToSharedPref(int coins);

		public void saveBestScoreToSharedPref(int score);
		public void saveBestLevelToSharedPref(int level);

		public void onStartActivityB();

		public void onStartSomeActivity(int someParameter, String someOtherParameter);

		public void onBackPress();
	}

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("lilitaone_regular.ttf"));

		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 35;
		font = generator.generateFont(parameter);
		parameter.size = 200;
		font_title  = generator.generateFont(parameter);
		parameter.size = 50;
		font_resume  = generator.generateFont(parameter);
		parameter.size = 25;
		font_damage  = generator.generateFont(parameter);
		generator.dispose();
		hec = Gdx.graphics.getWidth() / 12;

		batch = new SpriteBatch();

		img = new Texture("Naves/icnave"+(nave+1)+"_"+(gun+1)+".png");
		img_bullet = new Texture("Ammos/ammo"+(ammo+1)+".png");
		img_bullet_enemy = new Texture("Ammos/balaenemiga.png");

		logo = new Texture("logo_game.png");

		fondo_win = new Texture("Fondos/fondo_win.jpg");
		fondo_lose = new Texture("Fondos/fondo_lose.jpg");

		fondo = new Texture("Fondos/fondo1.jpg");
		fondo_degradado = new Texture("Fondos/negro_60.png");
		fondo_cont_top = new Texture("cont_stats_top.jpg");
		fondo_cont_botton = new Texture("cont_stats_botton.jpg");
		game_over_logo = new Texture("gameover.png");
		win_logo = new Texture("logo_win.png");

		ic_corazon = new Texture("Datos/img_corazon.png");
		ic_score = new Texture("Datos/img_nave.png");
		ic_money = new Texture("Datos/img_money.png");
		ic_level = new Texture("Datos/img_level.png");

		bg_text = new Texture("fondo_text.png");

		win_logo = new Texture("logo_win.png");

		barra_hp = new Texture("barra_white.jpg");
		barra_hp_g = new Texture("barra_green.jpg");
		barra_hp_o = new Texture("barra_orange.jpg");
		barra_hp_r = new Texture("barra_red.jpg");

		sound_bala = Gdx.audio.newSound(Gdx.files.internal("Sonidos/lacer0"+(ammo+1)+".mp3"));

		sound_die = Gdx.audio.newSound(Gdx.files.internal("Sonidos/die_enemy.mp3"));

		sound_game_over = Gdx.audio.newSound(Gdx.files.internal("Sonidos/lose.wav"));
		sound_win = Gdx.audio.newSound(Gdx.files.internal("Sonidos/win.wav"));
		sound_level_up = Gdx.audio.newSound(Gdx.files.internal("Sonidos/lvlup.wav"));

		sound_fondo = Gdx.audio.newSound(Gdx.files.internal("Sonidos/fondo_activo.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("Sonidos/fondo_activo.mp3"));
		music.setLooping(true);
		music.setVolume(volume);
		music.play();

		player = new Player();

		list_enemys = new ArrayList<>();
		list_ammo_enemy = new ArrayList<>();

		btnreintentar = new Sprite(new Texture("btnreintentar.png"));
		btnreintentar.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getWidth()/6);
		btnreintentar.setPosition(Gdx.graphics.getWidth()/2-btnreintentar.getWidth()/2,
				Gdx.graphics.getWidth()/2+hec);

		btncontinuar = new Sprite(new Texture("btncontinuar.png"));
		btncontinuar.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getWidth()/6);
		btncontinuar.setPosition(Gdx.graphics.getWidth()/2-btncontinuar.getWidth()/2,
				Gdx.graphics.getWidth()/2+hec);

		btnnextlevel = new Sprite(new Texture("btnnextlevel.png"));
		btnnextlevel.setSize(Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getWidth()/6);
		btnnextlevel.setPosition(Gdx.graphics.getWidth()/2-btnnextlevel.getWidth()/2,
				Gdx.graphics.getWidth()/2+hec);

		btnpause = new Sprite(new Texture("btnpause.png"));
		btnpause.setSize(hec*1.5f-20,hec*1.5f-20);
		btnpause.setPosition(Gdx.graphics.getWidth() - hec*1.5f + 10,
				Gdx.graphics.getHeight() - hec*1.5f + 10);

		btnsalir = new Sprite(new Texture("btnsalir.png"));
		btnsalir.setSize((Gdx.graphics.getWidth()/5)*2,Gdx.graphics.getWidth()/6);
		btnsalir.setPosition(Gdx.graphics.getWidth()/2-btnsalir.getWidth()/2,
				Gdx.graphics.getWidth()/6);

		oCam = new OrthographicCamera();
		oCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		oCam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

		Array<Texture> ani = new Array<>();
		ani.add(new Texture("Explotion/ex1.png"));
		ani.add(new Texture("Explotion/ex2.png"));
		ani.add(new Texture("Explotion/ex3.png"));
		ani.add(new Texture("Explotion/ex4.png"));
		ani.add(new Texture("Explotion/ex5.png"));
		ani.add(new Texture("Explotion/ex6.png"));

		animation_ex = new Animation<Texture>(.15f,ani);

		// Crea una textura blanca
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		whiteTexture = new Texture(pixmap);
		pixmap.dispose();

		if(list_enemys.size() == 0)
			createEnemys();
	}

	public void createEnemys(){
		spacing = Gdx.graphics.getWidth()/(cant_enemys_w+1);
		int base = Gdx.graphics.getHeight() - spacing*cant_enemys_h;
		for(int y = 0; y < cant_enemys_h; y++){
			for(int x = 0; x < cant_enemys_w; x++){
				list_enemys.add(new Enemy(new Vector2(x*spacing + (spacing/2) , base-(hec*2)+y*spacing), y));
			}
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				Vector3 temp = new Vector3();
				temp.set(screenX, screenY, 0);
				oCam.unproject(temp);
				if(MainGame.STATE == MainGame.STATE_RUNNING){
					if(btnpause.getBoundingRectangle().contains(temp.x, temp.y)){
						STATE = STATE_PAUSE;
					}
					player.list_ammo.add(new Ammo());
					sound_bala.play(volume);
				}else if(MainGame.STATE == MainGame.STATE_GAME_OVER){
					if(btnreintentar.getBoundingRectangle().contains(temp.x, temp.y)){
						resetStates();
						player.list_ammo.clear();
						list_enemys.clear();
						createEnemys();
						STATE = STATE_RUNNING;
					} else if (btnsalir.getBoundingRectangle().contains(temp.x, temp.y)) {
						resetStates();
						saveData();
						goBack();
					}
				}else if(MainGame.STATE == MainGame.STATE_WIN){
					if(btnnextlevel.getBoundingRectangle().contains(temp.x, temp.y)){
						player.list_ammo.clear();
						list_enemys.clear();
						Enemy.speed += speed_enemy_increase;
						LEVEL++;
						createEnemys();
						STATE = STATE_RUNNING;
						sound_level_up.play(volume);

					} else if (btnsalir.getBoundingRectangle().contains(temp.x, temp.y)){
						createEnemys();
						resetStates();
						saveData();
						goBack();
					}
				} else if (MainGame.STATE == MainGame.STATE_PAUSE) {

					if(btncontinuar.getBoundingRectangle().contains(temp.x, temp.y)){
						STATE = STATE_RUNNING;
					} else if (btnsalir.getBoundingRectangle().contains(temp.x, temp.y)){
						createEnemys();
						resetStates();
						saveData();
						goBack();
					}
				}
				return true;
			}
		});

		if(STATE == STATE_RUNNING || STATE == STATE_PAUSE){
			if(hp < 0){
				STATE = STATE_GAME_OVER;
			}
			int margin = 10;

			batch.draw(fondo, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

			batch.draw(fondo_cont_top, 0 , Gdx.graphics.getHeight() - hec, Gdx.graphics.getWidth()-hec*1.5f, hec);
			batch.draw(fondo_cont_botton, 0 , 0, Gdx.graphics.getWidth(), hec);


			shapeRenderer.setColor(Color.WHITE);
			btnpause.draw(batch);
			batch.draw(ic_corazon,
					margin, margin,
					hec-2*margin, hec-2*margin);
			batch.draw(barra_hp, hec+margin, margin,
			6*hec-2*margin, hec-2*margin);
			float currhp = hp;
			float hptotal = nave_hp;
			float prct = (float)(currhp/hptotal)*100;
			Texture frm_hp;
			if(prct > 60){
				frm_hp = barra_hp_g;
			} else if (prct > 30) {
				frm_hp = barra_hp_o;
			}else {
				frm_hp = barra_hp_r;
			}
			batch.draw(frm_hp, hec+margin, margin,
					(6*hec-2*margin)*(prct/100), hec-2*margin);
			batch.draw(ic_money,
					hec * 7 + margin , margin,
					hec-margin*2, hec-margin*2);
			font.draw(batch, ""+REC_MONEY,
					8*hec+margin, hec/2+margin);

			batch.draw(ic_score,
					margin , Gdx.graphics.getHeight() - hec + margin,
					hec-2*margin, hec-margin*2);
			font.draw(batch, ""+SCORE,
					0*(Gdx.graphics.getWidth()/2)+margin+hec, Gdx.graphics.getHeight() - margin*2);

			batch.draw(ic_level,
					5.25f*hec+margin , Gdx.graphics.getHeight() - hec+margin,
					hec-margin*2, hec-margin*2);
			font.draw(batch, ""+LEVEL,
					margin+hec*6.25f, Gdx.graphics.getHeight() - margin*2);

			player.Draw(batch);
			for(Enemy enemy : list_enemys){
				for (Ammo ammo : player.list_ammo) {
					if (enemy.alive && enemy.position.y <= 0) {
						STATE = STATE_GAME_OVER;
					}
					if (ammo.sprite_bullet.getBoundingRectangle().overlaps(enemy.sprite.getBoundingRectangle())
							&& enemy.alive) {
						ArrayList<Ammo> ammoToRemove = new ArrayList<>();

						ammoToRemove.add(ammo);
						player.list_ammo.removeAll(ammoToRemove);
						sound_die.play(volume);
						enemy.position_static = enemy.position;
						enemy.alive = false;


						SCORE += enemy.type;
						REC_MONEY += enemy.type*10;

						break;
					}
				}
				if(!enemy.die){
					enemy.Draw(batch);
				}
				Random random = new Random();
				int numeroAleatorio = random.nextInt(5000) + 1;
				if (numeroAleatorio <= 1 && MainGame.STATE != MainGame.STATE_PAUSE)
					list_ammo_enemy.add(new Ammo_enemy(enemy.position.x + enemy.sprite.getWidth()/2,enemy.position.y));
			}

			ArrayList<Ammo_enemy> ammoToRemove = new ArrayList<>();
			for(Ammo_enemy ammoEnemy : list_ammo_enemy){
				if(ammoEnemy.exist){
					ammoEnemy.Draw(batch);
					if (player.sprite.getBoundingRectangle().overlaps(ammoEnemy.sprite.getBoundingRectangle())) {
						ammoToRemove.add(ammoEnemy);
						hp -= 50;

						sound_die.play(volume);
					}
				}
				if(ammoEnemy.position.y < 0){
					ammoToRemove.add(ammoEnemy);
				}
			}
			list_ammo_enemy.removeAll(ammoToRemove);
			for(Enemy enemy : list_enemys){
				if (enemy.alive &&
					enemy.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
					STATE = STATE_GAME_OVER;

					sound_game_over.play(volume);
				}
				if(enemy.die){
					ArrayList<Enemy> enemyToRemove = new ArrayList<>();
					enemyToRemove.add(enemy);

					list_enemys.removeAll(enemyToRemove);
					break;
				}
			}
			if(list_enemys.size()==0){
				STATE = STATE_WIN;
				sound_win.play(volume);
			}
			if(STATE == STATE_PAUSE){
				batch.draw(fondo_degradado,
						0,
						0,
						Gdx.graphics.getWidth(),
						Gdx.graphics.getHeight());
				batch.draw(logo,
						hec*2,
						Gdx.graphics.getHeight()-Gdx.graphics.getWidth()+hec*2,
						Gdx.graphics.getWidth()-hec*4,
						Gdx.graphics.getWidth()-hec*4);
				btncontinuar.draw(batch);
				btnsalir.draw(batch);
			}
		} else if (STATE == STATE_GAME_OVER) {
			batch.draw(fondo_lose,
					0,
					0,
					Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			batch.draw(game_over_logo,
				Gdx.graphics.getWidth()/8,
				Gdx.graphics.getHeight() - Gdx.graphics.getWidth()/2,
				Gdx.graphics.getWidth()*6/8,
				Gdx.graphics.getWidth()/3);
			String text_lvl = "LV " + LEVEL;
			font_title.draw(batch, text_lvl,
					Gdx.graphics.getWidth()/2-getTextWidth(font_title, text_lvl)/2,
					Gdx.graphics.getHeight()/2);
			String text_resume = "Money : "+ REC_MONEY + "   SCORE : " + SCORE;
			font_resume.draw(batch, text_resume,
					Gdx.graphics.getWidth()/2-getTextWidth(font_resume, text_resume)/2,
					Gdx.graphics.getHeight()/2 + getTextHeight(font_title, text_lvl) + spacing);
			btnsalir.draw(batch);
			btnreintentar.draw(batch);
		} else if (STATE == STATE_WIN) {
			batch.draw(fondo_win,
					0,
					0,
					Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			batch.draw(win_logo,
				Gdx.graphics.getWidth()/8,
				Gdx.graphics.getHeight() - Gdx.graphics.getWidth()/2,
				Gdx.graphics.getWidth()*6/8,
				Gdx.graphics.getWidth()/3);
			String text_lvl = "LV " + LEVEL;
			font_title.draw(batch, text_lvl,
				Gdx.graphics.getWidth()/2-getTextWidth(font_title, text_lvl)/2,
				Gdx.graphics.getHeight()/2);
			String text_resume = "Money : "+ REC_MONEY + "   SCORE : " + SCORE;
			font_resume.draw(batch, text_resume,
					Gdx.graphics.getWidth()/2-getTextWidth(font_resume, text_resume)/2,
					Gdx.graphics.getHeight()/2 + getTextHeight(font_title, text_lvl) + spacing);
			btnsalir.draw(batch);
			btnnextlevel.draw(batch);
			if(stateTime >= .3f*6)
				stateTime = 0;

		}
		batch.end();
	}

	public static float getTextWidth(BitmapFont font, String text) {
		glyphLayout.setText(font, text);
		return glyphLayout.width;
	}

	public static float getTextHeight(BitmapFont font, String text) {
		glyphLayout.setText(font, text);
		return glyphLayout.height;
	}

	@Override
	public void dispose () {
		super.dispose();
		music.dispose();
	}
	public void setMyGameCallback(MyGameCallback callback) {
		myGameCallback = callback;
	}
	public static void goBack() {
		if (myGameCallback != null) {
			myGameCallback.onBackPress();
		}
	}

	public void saveData(){
		myGameCallback.saveCoinsToSharedPref(currCoins);
		myGameCallback.saveBestLevelToSharedPref(currLevel);
		myGameCallback.saveBestScoreToSharedPref(currScore);
	}
}
