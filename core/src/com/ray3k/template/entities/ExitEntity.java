package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.collisions.NullCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class ExitEntity extends Entity {
    public int id;
    
    public ExitEntity(float spawnX, float spawnY, float width, float height, int id) {
        setCollisionBox(spawnX, spawnY, width, height, nullCollisionFilter);
        this.id = id;
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
        var rect = world.getRect(item);
        if (rect != null) {
            shapeDrawer.setColor(Color.PURPLE);
            shapeDrawer.setDefaultLineWidth(1);
            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
