package pe.netdreams.invasive_pollution.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.MainGame;

public class Player {
    public Vector2 position;
    public static Texture src;
    public static float x, y;
    public Sprite sprite;
    public float speed;

    public ArrayList<Ammo> list_ammo;
    public Player() {
        list_ammo = new ArrayList<>();
        sprite = new Sprite(MainGame.img);
        src = MainGame.img;
        speed = MainGame.speed;
        sprite.setSize(sprite.getWidth()*.6f,sprite.getHeight()*.6f);
        x = Gdx.graphics.getWidth()/2-sprite.getWidth()/2;
        y = MainGame.hec+1;
        position = new Vector2(x,y);
    }

    public void update(float deltatimes){
        if(MainGame.STATE != MainGame.STATE_PAUSE) {

            float azimuth = Gdx.input.getAccelerometerX() / 10;

            if (azimuth < 0) {
                if (position.x < Gdx.graphics.getWidth() - sprite.getWidth() / 2)
                    position.x += deltatimes * speed;
            } else if (azimuth > 0) {
                if (position.x > 0 - sprite.getWidth() / 2)
                    position.x -= deltatimes * speed;
            }
        }
    }
    public void Draw(SpriteBatch batch){
        update(Gdx.graphics.getDeltaTime());

        updateAmmos(batch);

        deleteAmmos();

        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void updateAmmos(SpriteBatch batch){
        for (Ammo elemento : list_ammo) {
            elemento.Draw(batch);
            elemento.update();
        }
    }

    public void deleteAmmos(){//si se salen de la pantalla
        ArrayList<Ammo> ammoToRemove = new ArrayList<>();
        for (Ammo ammo : list_ammo) {
            if (ammo.position_bullet.y >= (Gdx.graphics.getHeight()-(MainGame.hec*2)-ammo.sprite_bullet.getHeight())) {
                ammoToRemove.add(ammo);
            }
        }
        list_ammo.removeAll(ammoToRemove);
    }
}
