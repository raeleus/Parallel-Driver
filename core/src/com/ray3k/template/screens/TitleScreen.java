package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.utils.SkeletonDrawable;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.ray3k.template.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.TitleAnimation.*;

public class TitleScreen extends JamScreen {
    private Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private ObjectSet<Sound> sounds;
    
    @Override
    public void show() {
        super.show();
        
        bgm_menu.stop();
        
        spineDrawables = new Array<>();
        sounds = new ObjectSet<>();
    
        Skeleton skeleton = new Skeleton(spine_title);
        AnimationState animationState = new AnimationState(spine_titleAnimationData);
        var spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.getAnimationState().setAnimation(0, animation, false);
        spineDrawable.getAnimationState().apply(spineDrawable.getSkeleton());
        spineDrawables.add(spineDrawable);
        
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
    
        stage.addAction(Actions.delay(.05f, Actions.run(() -> {
            sfx_titleBoom.play(sfx);
        })));
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        var stack = new Stack();
        root.add(stack).grow();
        
        Image image = new Image(spineDrawable);
        spineDrawable.getAnimationState().setAnimation(0, stand, false);
        image.setScaling(Scaling.fill);
        stack.add(image);
    
        var container = new Container<>();
        container.pad(20);
        container.fill();
        stack.add(container);
        
        var typingLabel = new TypingLabel("{COLOR=BLACK}In the \"Many Worlds\" interpretation of quantum theory, parallel universes can interact with our own.\n" +
                "Thus, the parallel driver was born...", skin, "big");
        typingLabel.setWrap(true);
        container.setActor(typingLabel);
        
        typingLabel.setTypingListener(new TypingAdapter() {
            @Override
            public void end() {
                typingLabel.addAction(sequence(delay(1.5f), fadeOut(.25f), delay(.75f), run(() -> {
                    spineDrawable.getAnimationState().setAnimation(0, animation, false);
                })));
            }
        });
        
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {
                if (entry.getAnimation() == animation) {
                    core.transition(new GameScreen());
                }
            }
            
            @Override
            public void event(AnimationState.TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null && !event.getData().getAudioPath().equals("")) {
                    Sound sound = assetManager.get("sfx/" + event.getData().getAudioPath());
                    sound.play();
                    sounds.add(sound);
                }
            }
        });
        
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                core.transition(new GameScreen());
                return true;
            }
            
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                core.transition(new GameScreen());
                return true;
            }
        });
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        
        for (SkeletonDrawable skeletonDrawable : spineDrawables) {
            skeletonDrawable.update(delta);
        }
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void hide() {
        super.hide();
        for (Sound sound : sounds) {
            sound.stop();
        }
    }
}
