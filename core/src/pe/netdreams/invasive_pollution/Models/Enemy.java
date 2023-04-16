package pe.netdreams.invasive_pollution.Models;

import static pe.netdreams.invasive_pollution.MainGame.SCORE;
import static pe.netdreams.invasive_pollution.MainGame.STATE_RUNNING;
import static pe.netdreams.invasive_pollution.MainGame.animation_ex;
import static pe.netdreams.invasive_pollution.MainGame.font_damage;
import static pe.netdreams.invasive_pollution.MainGame.list_enemys;
import static pe.netdreams.invasive_pollution.Models.Player.x;
import static pe.netdreams.invasive_pollution.Models.Player.y;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import pe.netdreams.invasive_pollution.MainGame;

public class Enemy {
    public Vector2 position;
    public Vector2 position_static;
    public Sprite sprite;
    public int type;
    public float stateTime = 0;
    public static float speed = .4f;//0.4f 5
    public boolean alive = true;
    public boolean die = false;

    public Enemy(Vector2 _position, int img){
        type = img;
        sprite = new Sprite(getImg(img));
        position = _position;
        sprite.setSize(MainGame.spacing*.7f, MainGame.spacing*.7f);
    }

    public void Draw(SpriteBatch batch){
        update();
        if(!alive){
            stateTime += Gdx.graphics.getDeltaTime();
            Animation<Texture> textures = animation_ex;
            batch.draw(textures.getKeyFrame(stateTime), position_static.x+sprite.getWidth()/4, position_static.y+sprite.getHeight()/4);


            font_damage.draw(batch, ""+(37-list_enemys.size()),
                    position_static.x+sprite.getWidth(), position_static.y+sprite.getHeight());
            if(stateTime >= .15f*6){
                die = true;
                stateTime = 0;
            }
        }else {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }
    }

    public void update(){
        if(MainGame.STATE != MainGame.STATE_PAUSE){
            if(alive){
                if(MainGame.dir_enemys){
                    if((position.x+sprite.getWidth()) < Gdx.graphics.getWidth()){
                        position.x += speed;
                    }else{
                        MainGame.dir_enemys = false;
                    }
                }else{
                    if(position.x > 0) {
                        position.x -= speed;
                    }else{
                        MainGame.dir_enemys = true;
                    }
                }
                position.y -= speed/2;
            }
        }
    }

    public Texture getImg(int tipe){
        return new Texture("Enemys/enemy"+(6-tipe)+".png");
    }
}
