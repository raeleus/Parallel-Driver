package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public PlayerEntity(String name) {
        switch (name) {
            case "car-porch":
                setSkeletonData(spine_carPorch, spine_carPorchAnimationData);
                break;
        }
        
        var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
        var verts = bbox.getVertices();
        
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
        
        for (int i = 0, j = 1; i < verts.length && j < verts.length; i += 2, j+=2) {
            float x = verts[i];
            float y = verts[j];
            
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }
        
        setCollisionBox(minX, minY, maxX - minX, maxY - minY, PLAYER_COLLISION_FILTER);
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
        var rect = world.getRect(item);
        if (rect != null) {
            shapeDrawer.setColor(Color.GREEN);
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
    
    private final static CollisionFilter PLAYER_COLLISION_FILTER = new CollisionFilter() {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof WallEntity) return Response.cross;
            else return null;
        }
    };
}
