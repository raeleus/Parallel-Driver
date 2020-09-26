package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;

import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public PlayerEntity(String name) {
        switch (name) {
            case "car-porch":
                setSkeletonData(spine_carPorch, spine_carPorchAnimationData);
                break;
        }
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
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
