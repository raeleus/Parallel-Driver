package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.ray3k.template.*;
import com.ray3k.template.OgmoReader.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.screens.DialogPause.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color(Color.BLACK);
    public Stage stage;
    public static ShapeDrawer shapeDrawer;
    public boolean paused;
    private ChainVfxEffect vfxEffect;
    private String levelName;
    private Array<Entity> addEntities;
    
    public GameScreen() {
        this(null, "test-level");
    }
    
    public GameScreen(Array<Entity> addEntities, String levelName) {
        this.addEntities = addEntities == null ? new Array<>() : new Array<>(addEntities);
        this.levelName = levelName;
    }
    
    @Override
    public void show() {
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

            entityController.add(entity);
        }
        
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
                    default:
                        var player = new PlayerEntity(x, y, name);
                        entityController.add(player);
                        break;
                }
            }
        });
        ogmoReader.readFile(Gdx.files.internal("levels/" + levelName + ".json"));
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            entityController.act(delta);
            vfxManager.update(delta);
        }
        stage.act(delta);
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
        entityController.dispose();
    }
}
