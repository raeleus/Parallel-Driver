package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.collisions.NullCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class WallEntity extends Entity {
    public WallEntity(float spawnX, float spawnY, float width, float height) {
        setCollisionBox(spawnX, spawnY, width, height, nullCollisionFilter);
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
//        var rect = world.getRect(item);
//        if (rect != null) {
//            shapeDrawer.setColor(Color.RED);
//            shapeDrawer.setDefaultLineWidth(4);
//            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
//        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
