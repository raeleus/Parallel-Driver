package com.ray3k.template.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.utils.Disposable;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class BackgroundEntity extends Entity implements Disposable {
    private Texture texture;
    
    public BackgroundEntity(String name, float centerX, float centerY) {
        texture = new Texture(Gdx.files.internal("backgrounds/" + name));
        setPosition(centerX - texture.getWidth() / 2, centerY - texture.getHeight() / 2);
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        batch.draw(texture, x, y);
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
    
    @Override
    public void dispose() {
        texture.dispose();
    }
}
