package com.ray3k.template.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import com.ray3k.template.transitions.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public Inputter inputter;
    public InputRecorder inputRecorder;
    
    public float startX;
    public float startY;
    public String name;
    
    float rotation;
    
    float wheelBase = 40;
    float steeringAngle = 15;
    float enginePower = 800;
    float friction = -.9f;
    float drag = -.0015f;
    float dragTurning = -.0025f;
    float slipSpeed = 400;
    float tractionFast = .1f;
    float tractionSlow = .7f;
    Vector2 velocity = new Vector2();
    Vector2 acceleration = new Vector2();
    Vector2 rearWheel = new Vector2();
    Vector2 frontWheel = new Vector2();
    Vector2 temp = new Vector2();
    Vector2 newHeading = new Vector2();
    Vector2 frictionForce = new Vector2();
    Vector2 dragForce = new Vector2();
    float steerAngle;
    
    public PlayerEntity(float x, float y, String name) {
        startX = x;
        startY = y;
        this.name = name;
    }
    
    @Override
    public void create() {
        switch (name) {
            case "car-dick's-weiner":
                setSkeletonData(spine_carDicksWeiner, spine_carDicksWeinerAnimationData);
                wheelBase = 50;
                steeringAngle = 25;
                enginePower = 400;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-ground-roller":
                setSkeletonData(spine_carGroundRoller, spine_carGroundRollerAnimationData);
                wheelBase = 40;
                steeringAngle = 18;
                enginePower = 1000;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .06f;
                tractionSlow = .7f;
                break;
            case "car-hardly":
                setSkeletonData(spine_carHardly, spine_carHardlyAnimationData);
                wheelBase = 30;
                steeringAngle = 25;
                enginePower = 1600;
                friction = -.9f;
                drag = -.0010f;
                dragTurning = -.0015f;
                slipSpeed = 200;
                tractionFast = .025f;
                tractionSlow = .5f;
                break;
            case "car-hoop-tee":
                setSkeletonData(spine_carHoopTee, spine_carHoopTeeAnimationData);
                wheelBase = 40;
                steeringAngle = 30;
                enginePower = 1000;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .02f;
                tractionSlow = .03f;
                break;
            case "car-ice-cube":
                setSkeletonData(spine_carIceCube, spine_carIceCubeAnimationData);
                wheelBase = 40;
                steeringAngle = 45;
                enginePower = 1000;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0015f;
                slipSpeed = 400;
                tractionFast = .009f;
                tractionSlow = .009f;
                break;
            case "car-ladybird":
                setSkeletonData(spine_carLadybird, spine_carLadybirdAnimationData);
                wheelBase = 30;
                steeringAngle = 50;
                enginePower = 400;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 200;
                tractionFast = .1f;
                tractionSlow = .3f;
                break;
            case "car-missile-america":
                setSkeletonData(spine_carMissileAmerica, spine_carMissileAmericaAnimationData);
                wheelBase = 120;
                steeringAngle = 60;
                enginePower = 300;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 200;
                tractionFast = .1f;
                tractionSlow = .3f;
                break;
            case "car-porch":
                setSkeletonData(spine_carPorch, spine_carPorchAnimationData);
                wheelBase = 40;
                steeringAngle = 25;
                enginePower = 2000;
                friction = -.9f;
                drag = -.001f;
                dragTurning = -.003f;
                slipSpeed = 400;
                tractionFast = .03f;
                tractionSlow = .05f;
                break;
            case "car-stumbler":
                setSkeletonData(spine_carStumbler, spine_carStumblerAnimationData);
                wheelBase = 40;
                steeringAngle = 25;
                enginePower = 2500;
                friction = -.9f;
                drag = -.001f;
                dragTurning = -.004f;
                slipSpeed = 400;
                tractionFast = .03f;
                tractionSlow = .04f;
                break;
            case "car-tanks-alot":
                setSkeletonData(spine_carTanksAlot, spine_carTanksAlotAnimationData);
                wheelBase = 40;
                steeringAngle = 20;
                enginePower = 300;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 200;
                tractionFast = .1f;
                tractionSlow = .3f;
                break;
            case "car-tursula":
                setSkeletonData(spine_carTursula, spine_carTursulaAnimationData);
                wheelBase = 40;
                steeringAngle = 25;
                enginePower = 1600;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
        }
    
        var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
        var verts = skeletonBounds.getPolygon(bbox);
    
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
    
        for (int i = 0, j = 1; i < verts.size && j < verts.size; i += 2, j+=2) {
            float xValue = verts.get(i);
            float yValue = verts.get(j);
        
            if (xValue < minX) minX = xValue;
            if (xValue > maxX) maxX = xValue;
            if (yValue < minY) minY = yValue;
            if (yValue > maxY) maxY = yValue;
        }
    
        setCollisionBox(minX, minY, maxX - minX, maxY - minY, PLAYER_COLLISION_FILTER);
        setPosition(startX, startY);
        world.update(item, x, y);
        
        inputter.reset();
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        //input recorder
        if (inputRecorder != null) {
            inputRecorder.update(delta);
        }
        
        //input
        inputter.update(delta);
        int turn = 0;
        if (inputter.isBindingPressed(TURN_RIGHT)) {
            turn -= 1;
        }
        if (inputter.isBindingPressed(TURN_LEFT)) {
            turn += 1;
        }
        steerAngle = turn * steeringAngle;
        
        //physics
        acceleration.set(enginePower, 0);
        acceleration.rotate(velocity.angle());
        //friction
        frictionForce.set(velocity).scl(friction);
        dragForce.set(velocity).scl(velocity.len()).scl(turn == 0 ? drag : dragTurning);
        if (velocity.len() < 100) frictionForce.scl(3);
        acceleration.add(dragForce).add(frictionForce);
        velocity.add(acceleration.x * delta, acceleration.y * delta);
        
        //calculate steering
        rearWheel.set(wheelBase, 0);
        rearWheel.rotate(rotation + 180);
        rearWheel.add(x, y);
        
        frontWheel.set(wheelBase, 0);
        frontWheel.rotate(rotation);
        frontWheel.add(x, y);
    
        rearWheel.add(velocity.x * delta, velocity.y * delta);
        temp.set(velocity.len() * delta, 0);
        temp.rotate(velocity.angle() + steerAngle);
        frontWheel.add(temp.x, temp.y);
        
        newHeading.set(frontWheel);
        newHeading.sub(rearWheel);
        newHeading.nor();
        
        float traction = tractionSlow;
        if (velocity.len() > slipSpeed) {
            traction = tractionFast;
        }
        
        temp.set(newHeading.x * velocity.len(), newHeading.y * velocity.len());
        velocity.interpolate(temp, traction, Interpolation.linear);
        
        deltaX = velocity.x;
        deltaY = velocity.y;
        rotation = newHeading.angle();
        
        skeleton.getRootBone().setRotation(rotation);
        updateBbox();
        
        gameScreen.camera.position.set(x, y, 0);
    }
    
    @Override
    public void draw(float delta) {
        var rect = world.getRect(item);
        if (rect != null) {
            shapeDrawer.setColor(Color.GREEN);
            shapeDrawer.setDefaultLineWidth(1);
            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
            
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledRectangle(rearWheel.x - 5f, rearWheel.y - 5f, 10f, 10f);
    
            shapeDrawer.setColor(Color.PURPLE);
            shapeDrawer.filledRectangle(frontWheel.x - 5f, frontWheel.y - 5f, 10f, 10f);
            shapeDrawer.setColor(Color.ORANGE);
            var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
            polygon1.setVertices(Utils.boundingBoxAttachmentToTriangles(skeletonBounds, bbox));
            shapeDrawer.polygon(polygon1);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    Polygon polygon1 = new Polygon();
    Polygon polygon2 = new Polygon();
    @Override
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(0);
            if (collision.other.userData instanceof WallEntity) {
                var wall = (WallEntity) collision.other.userData;
                var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
                var verts = Utils.boundingBoxAttachmentToTriangles(skeletonBounds, bbox);
                for (int j = 0; j < verts.length; j += 6) {
                    polygon1.setVertices(new float[]{verts[j], verts[j+1], verts[j+2], verts[j+3], verts[j+4], verts[j+5]});
                    polygon2.setVertices(new float[]{wall.x + wall.bboxX, wall.y + wall.bboxY, wall.x + wall.bboxX + wall.bboxWidth, wall.y + wall.bboxY, wall.x + wall.bboxX + wall.bboxWidth, wall.y + wall.bboxY + wall.bboxHeight, wall.x + wall.bboxX, wall.bboxY + wall.bboxHeight});
                    if (Intersector.overlapConvexPolygons(polygon1, polygon2, null)) {
                        if (inputter instanceof PlayerInput) {
                            core.transition(new GameScreen(null, "test-level", gameScreen.currentId),
                                    new TransitionSlide(270, Interpolation.bounce), .5f);
                        } else {
                            destroy = true;
                        }
                        break;
                    }
                }
            } else if (collision.other.userData instanceof ExitEntity) {
                var exit = (ExitEntity) collision.other.userData;
                var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
                var verts = Utils.boundingBoxAttachmentToTriangles(skeletonBounds, bbox);
                for (int j = 0; j < verts.length; j += 6) {
                    polygon1.setVertices(new float[]{verts[j], verts[j+1], verts[j+2], verts[j+3], verts[j+4], verts[j+5]});
                    polygon2.setVertices(new float[]{exit.x + exit.bboxX, exit.y + exit.bboxY, exit.x + exit.bboxX + exit.bboxWidth, exit.y + exit.bboxY, exit.x + exit.bboxX + exit.bboxWidth, exit.y + exit.bboxY + exit.bboxHeight, exit.x + exit.bboxX, exit.bboxY + exit.bboxHeight});
                    if (Intersector.overlapConvexPolygons(polygon1, polygon2, null)) {
                        if (inputter instanceof PlayerInput) {
                            var aiInput = new AiInput(inputRecorder);
                            var newPlayer = new PlayerEntity(startX, startY, name);
                            newPlayer.inputter = aiInput;
                            gameScreen.addEntities.add(newPlayer);
                            core.transition(
                                    new GameScreen(gameScreen.addEntities, "test-level", gameScreen.currentId + 1),
                                    new TransitionSlide(270, Interpolation.bounce), .5f);
                        } else {
                            destroy = true;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void updateBbox() {
        var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
        var verts = skeletonBounds.getPolygon(bbox);
    
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
    
        for (int i = 0, j = 1; i < verts.size && j < verts.size; i += 2, j+=2) {
            float xValue = verts.get(i);
            float yValue = verts.get(j);
        
            if (xValue < minX) minX = xValue;
            if (xValue > maxX) maxX = xValue;
            if (yValue < minY) minY = yValue;
            if (yValue > maxY) maxY = yValue;
        }
    
        world.update(item, minX, minY, maxX - minX, maxY - minY);
    }
    
    private final static CollisionFilter PLAYER_COLLISION_FILTER = (item, other) -> {
        if (other.userData instanceof WallEntity || other.userData instanceof ExitEntity) return Response.cross;
        else return null;
    };
    
    public interface Inputter {
        boolean isBindingPressed(Binding binding);
        void update(float delta);
        void reset();
    }
    
    public final static class PlayerInput implements Inputter {
        @Override
        public boolean isBindingPressed(Binding binding) {
            return gameScreen.isBindingPressed(binding);
        }
    
        @Override
        public void update(float delta) {
        
        }
    
        @Override
        public void reset() {
        
        }
    }
    
    public final static class NullInput implements Inputter {
        @Override
        public boolean isBindingPressed(Binding binding) {
            return false;
        }
    
        @Override
        public void update(float delta) {
        
        }
    
        @Override
        public void reset() {
        
        }
    }
    
    public final static class AiInput implements  Inputter {
        public float frame;
        public InputRecorder inputRecorder;
    
        public AiInput(InputRecorder inputRecorder) {
            this.inputRecorder = inputRecorder;
        }
    
        @Override
        public boolean isBindingPressed(Binding binding) {
            if (binding == TURN_LEFT) {
                InputRecord selectedRecord = null;
                for (var inputRecord : inputRecorder.leftInputs) {
                    if (frame > inputRecord.frame) selectedRecord = inputRecord;
                    else break;
                }
                
                if (selectedRecord != null) return selectedRecord.binding != null;
            }
    
            if (binding == TURN_RIGHT) {
                InputRecord selectedRecord = null;
                for (var inputRecord : inputRecorder.rightInputs) {
                    if (frame > inputRecord.frame) selectedRecord = inputRecord;
                    else break;
                }
    
                if (selectedRecord != null) return selectedRecord.binding != null;
            }
            return false;
        }
    
        @Override
        public void update(float delta) {
            frame += delta;
        }
    
        @Override
        public void reset() {
            frame = 0;
        }
    }
    
    public final static class InputRecorder {
        public Array<InputRecord> leftInputs = new Array<>();
        public Array<InputRecord> rightInputs = new Array<>();
        public float frame;
        
        public void update(float delta) {
            frame += delta;
            if (leftInputs.size == 0 || leftInputs.peek().binding == null) {
                if (gameScreen.isBindingPressed(TURN_LEFT)) {
                    leftInputs.add(new InputRecord(frame, TURN_LEFT));
                }
            } else if (leftInputs.size != 0 && leftInputs.peek().binding != null) {
                if (!gameScreen.isBindingPressed(TURN_LEFT)) {
                    leftInputs.add(new InputRecord(frame, null));
                }
            }
    
            if (rightInputs.size == 0 || rightInputs.peek().binding == null) {
                if (gameScreen.isBindingPressed(TURN_RIGHT)) {
                    rightInputs.add(new InputRecord(frame, TURN_RIGHT));
                }
            } else if (rightInputs.size != 0 && rightInputs.peek().binding != null) {
                if (!gameScreen.isBindingPressed(TURN_RIGHT)) {
                    rightInputs.add(new InputRecord(frame, null));
                }
            }
        }
    
        @Override
        public String toString() {
            
            return leftInputs.toString() + "\n~~~\n" + rightInputs.toString();
        }
    }
    
    public final static class InputRecord {
        public float frame;
        public Binding binding;
    
        public InputRecord(float frame, Binding binding) {
            this.frame = frame;
            this.binding = binding;
        }
    
        @Override
        public String toString() {
            return frame + " " + binding;
        }
    }
}
