package pe.netdreams.invasive_pollution.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import pe.netdreams.invasive_pollution.MainGame;

public class Ammo_enemy {

    public Vector2 position;
    public Texture src;
    public Sprite sprite;
    public float speed_bullet;
    public float rotation = 0f;
    public boolean exist = true;

    public Ammo_enemy(float x, float y) {
        sprite = new Sprite(MainGame.img_bullet_enemy);
        src = MainGame.img_bullet_enemy;
        speed_bullet = Enemy.speed*4;
        sprite.setSize(sprite.getWidth()*.5f, sprite.getHeight()*.5f);
        position = new Vector2(x,y);
    }


    public void update(){
        if(MainGame.STATE != MainGame.STATE_PAUSE) {
            if (position.y > 0) {
                position.y -= speed_bullet;
            } else {
                exist = false;
            }
        }
    }

    public void Draw(SpriteBatch batch){
        update();
        sprite.setPosition(position.x, position.y);
        sprite.setOriginCenter();
        rotation+=MainGame.speed_enemy_increase*50;
        sprite.setRotation(rotation);
        sprite.draw(batch);
    }
}
