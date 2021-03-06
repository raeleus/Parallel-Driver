package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dongbat.jbump.World;
import com.ray3k.template.*;
import com.ray3k.template.OgmoReader.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.entities.PlayerEntity.*;
import com.ray3k.template.screens.DialogPause.*;
import com.ray3k.template.transitions.*;
import com.ray3k.template.vfx.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color(Color.BLACK);
    public Stage stage;
    public static ShapeDrawer shapeDrawer;
    public boolean paused;
    private GlitchEffect vfxEffect;
    public Array<Entity> addEntities;
    public int currentId;
    public int levelId;
    public Array<PlayerEntity> playerEntities = new Array<>();
    public boolean endGame = true;
    public static final int LAST_LEVEL = 9;
    
    public GameScreen() {
        this(null, 1, 0);
    }
    
    public GameScreen(Array<Entity> addEntities, int levelId, int currentId) {
        this.addEntities = addEntities == null ? new Array<>() : new Array<>(addEntities);
        this.levelId = levelId;
        this.currentId = currentId;
    }
    
    @Override
    public void show() {
        bgm_engine.setLooping(true);
        bgm_engine.setVolume(sfx);
        if (!bgm_engine.isPlaying()) bgm_engine.play();
        world = new World<>();
        gameScreen = this;
    
        paused = false;
    
        stage = new Stage(new ScreenViewport(), batch);
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!paused && keycode == Keys.ESCAPE) {
                    paused = true;
                
                    DialogPause dialogPause = new DialogPause(GameScreen.this);
                    dialogPause.show(stage);
                    dialogPause.addListener(new PauseListener() {
                        @Override
                        public void resume() {
                            paused = false;
                        }
                    
                        @Override
                        public void quit() {
                            core.transition(new MenuScreen());
                        }
                    });
                }
                return super.keyDown(event, keycode);
            }
        });
    
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("game/white"));
        shapeDrawer.setPixelSize(.5f);
    
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
    
        entityController.clear();
        
        for (var entity : addEntities) {
            entity.item = null;
            entity.destroy = false;
            entityController.add(entity);
            
            if (entity instanceof PlayerEntity) {
                playerEntities.add((PlayerEntity) entity);
            }
        }
    
        vfxEffect = new GlitchEffect();
        vfxEffect.setAmount(0);
        vfxEffect.rebind();
        vfxManager.addEffect(vfxEffect);
    
        stage.addAction(sequence(delay(.05f), new TemporalAction(.25f) {
            @Override
            protected void update(float percent) {
                vfxEffect.setAmount(percent * .2f);
                vfxEffect.rebind();
            }
        }, run(() -> sfx_glitch.play(sfx)), new TemporalAction(.25f) {
            @Override
            protected void update(float percent) {
                vfxEffect.setAmount((1 - percent) * .2f);
                vfxEffect.rebind();
            }
        }, run(() -> vfxManager.removeEffect(vfxEffect))));
        
        var ogmoReader = new OgmoReader();
        ogmoReader.addListener(new OgmoAdapter() {
            @Override
            public void entity(String name, int id, int x, int y, int width, int height, boolean flippedX,
                               boolean flippedY, int originX, int originY, int rotation, Array<EntityNode> nodes,
                               ObjectMap<String, OgmoValue> valuesMap) {
                switch (name) {
                    case "wall":
                        float minX = x;
                        float minY = y;
                        float maxX = x;
                        float maxY = y;
                        
                        for (var node : nodes) {
                            if (node.x < minX) minX = node.x;
                            if (node.x > maxX) maxX = node.x;
                            if (node.y < minY) minY = node.y;
                            if (node.y > maxY) maxY = node.y;
                        }
                        
                        var wall = new WallEntity(minX, minY, maxX - minX, maxY - minY);
                        entityController.add(wall);
                        break;
                    case "exit":
                        minX = x;
                        minY = y;
                        maxX = x;
                        maxY = y;
    
                        for (var node : nodes) {
                            if (node.x < minX) minX = node.x;
                            if (node.x > maxX) maxX = node.x;
                            if (node.y < minY) minY = node.y;
                            if (node.y > maxY) maxY = node.y;
                        }
    
                        var exit = new ExitEntity(minX, minY, maxX - minX, maxY - minY, valuesMap.get("id").asInt());
                        entityController.add(exit);
                        break;
                    default:
                        if (valuesMap.get("id").asInt() == currentId) {
                            var player = new PlayerEntity(x, y, rotation, name, currentId);
                            player.inputter = new PlayerInput();
                            player.inputRecorder = new InputRecorder();
                            entityController.add(player);
                            playerEntities.add(player);
                            endGame = false;
                            
                            var cameraEntity = new CameraEntity(player, levelWidth, levelHeight);
                            entityController.add(cameraEntity);
                        }
                        break;
                }
            }
            
            private float levelWidth;
            private float levelHeight;
            
            @Override
            public void level(String ogmoVersion, int width, int height, int offsetX, int offsetY,
                              ObjectMap<String, OgmoValue> valuesMap) {
                levelWidth = width;
                levelHeight = height;
                camera.position.set(width / 2f, height / 2f, 0);
                float widthRatio = (float) width / viewport.getWorldWidth();
                float heightRatio = (float) height / viewport.getWorldHeight();
                camera.zoom = Math.max(widthRatio, heightRatio);
            }
    
            private String layerName;
            
            @Override
            public void layer(String name, int gridCellWidth, int gridCellHeight, int offsetX, int offsetY) {
                layerName = name;
            }
    
            @Override
            public void decal(int centerX, int centerY, float scaleX, float scaleY, int rotation, String texture,
                              String folder) {
                if (layerName.equals("background")) {
                    var decal = new BackgroundEntity(texture, centerX, centerY);
                    entityController.add(decal);
                    decal.depth = BACKGROUND_DEPTH;
                } else if (layerName.equals("foreground")) {
                    var decal = new BackgroundEntity(texture, centerX, centerY);
                    entityController.add(decal);
                    decal.depth = FOREGROUND_DEPTH;
                }
            }
        });
        ogmoReader.readFile(Gdx.files.internal("levels/level-" + levelId + ".json"));
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            entityController.act(delta);
            vfxManager.update(delta);
        }
        stage.act(delta);
        
        if (endGame) {
            boolean nextLevel = true;
            for (var player : playerEntities) {
                if (!player.destroy) {
                    nextLevel = false;
                    break;
                }
            }
            
            if (nextLevel) {
                if (levelId < LAST_LEVEL) core.transition(new GameScreen(null, levelId + 1, 0), new TransitionSlide(270, Interpolation.bounce), .5f);
                else core.transition(new CompleteScreen(), new TransitionSlide(270, Interpolation.bounce), .5f);
            }
        } else {
            if (isBindingJustPressed(RESET)) {
                core.transition(new GameScreen(null, levelId, 0), new TransitionSlide(270, Interpolation.bounce), .5f);
            }
        }
    }
    
    @Override
    public void draw(float delta) {
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(paused ? 0 : delta);
        batch.end();
        
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
            
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        vfxEffect.dispose();
        entityController.dispose();
    }
}
