package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;

import static com.ray3k.template.screens.GameScreen.*;

public class CameraEntity extends Entity {
    private final static float WORLD_WIDTH = 1024;
    private final static float WORLD_HEIGHT = 576;
    private PlayerEntity player;
    float levelWidth;
    float levelHeight;
    
    public CameraEntity(PlayerEntity player, float levelWidth, float levelHeight) {
        this.player = player;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
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
        
        if (x < WORLD_WIDTH / 2) x = WORLD_WIDTH / 2;
        if (x > levelWidth - WORLD_WIDTH / 2) x = levelWidth - WORLD_WIDTH / 2;
        if (y < WORLD_HEIGHT / 2) y = WORLD_HEIGHT / 2;
        if (y > levelHeight - WORLD_HEIGHT / 2) y = levelHeight - WORLD_HEIGHT / 2;
        
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
