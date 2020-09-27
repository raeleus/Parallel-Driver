package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;

import static com.ray3k.template.screens.GameScreen.*;

public class CameraEntity extends Entity {
    private PlayerEntity player;
    
    public CameraEntity(PlayerEntity player) {
        this.player = player;
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        setPosition(player.x, player.y);
        
        gameScreen.camera.zoom = 1;
        gameScreen.camera.position.set(x, y, 0);
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
