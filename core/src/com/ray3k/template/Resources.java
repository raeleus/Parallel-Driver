package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class Resources {
    public static Skin skin_skin;

    public static SkeletonData spine_carDicksWeiner;

    public static AnimationStateData spine_carDicksWeinerAnimationData;

    public static SkeletonData spine_carGroundRoller;

    public static AnimationStateData spine_carGroundRollerAnimationData;

    public static SkeletonData spine_carHardly;

    public static AnimationStateData spine_carHardlyAnimationData;

    public static SkeletonData spine_carHoopTee;

    public static AnimationStateData spine_carHoopTeeAnimationData;

    public static SkeletonData spine_carIceCube;

    public static AnimationStateData spine_carIceCubeAnimationData;

    public static SkeletonData spine_carLadybird;

    public static AnimationStateData spine_carLadybirdAnimationData;

    public static SkeletonData spine_carMissileAmerica;

    public static AnimationStateData spine_carMissileAmericaAnimationData;

    public static SkeletonData spine_carPorch;

    public static AnimationStateData spine_carPorchAnimationData;

    public static SkeletonData spine_carStumbler;

    public static AnimationStateData spine_carStumblerAnimationData;

    public static SkeletonData spine_carTanksAlot;

    public static AnimationStateData spine_carTanksAlotAnimationData;

    public static SkeletonData spine_carTursula;

    public static AnimationStateData spine_carTursulaAnimationData;

    public static SkeletonData spine_egg;

    public static AnimationStateData spine_eggAnimationData;

    public static SkeletonData spine_libgdx;

    public static AnimationStateData spine_libgdxAnimationData;

    public static SkeletonData spine_ray3k;

    public static AnimationStateData spine_ray3kAnimationData;

    public static SkeletonData spine_stopSign;

    public static AnimationStateData spine_stopSignAnimationData;

    public static SkeletonData spine_title;

    public static AnimationStateData spine_titleAnimationData;

    public static SkeletonData spine_zaida;

    public static AnimationStateData spine_zaidaAnimationData;

    public static TextureAtlas textures_textures;

    public static Sound sfx_click;

    public static Sound sfx_glitch;

    public static Sound sfx_logoLibgdxChop;

    public static Sound sfx_logoLibgdxKikiki;

    public static Sound sfx_logoLibgdxLoser;

    public static Sound sfx_logoLibgdxMaskSound;

    public static Sound sfx_logoLibgdxScream;

    public static Sound sfx_logoLibgdxTitle;

    public static Sound sfx_logoRay3kTune;

    public static Sound sfx_logoRay3k;

    public static Sound sfx_logoWoosh;

    public static Sound sfx_titleBoom;

    public static Sound sfx_titleDoppler;

    public static Sound sfx_wreck;

    public static Music bgm_audioSample;

    public static Music bgm_engine;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        spine_carDicksWeiner = assetManager.get("spine/car-dicks-weiner.json");
        spine_carDicksWeinerAnimationData = assetManager.get("spine/car-dicks-weiner.json-animation");
        CarDicksWeinerAnimation.animation = spine_carDicksWeiner.findAnimation("animation");
        spine_carGroundRoller = assetManager.get("spine/car-ground-roller.json");
        spine_carGroundRollerAnimationData = assetManager.get("spine/car-ground-roller.json-animation");
        CarGroundRollerAnimation.animation = spine_carGroundRoller.findAnimation("animation");
        spine_carHardly = assetManager.get("spine/car-hardly.json");
        spine_carHardlyAnimationData = assetManager.get("spine/car-hardly.json-animation");
        CarHardlyAnimation.animation = spine_carHardly.findAnimation("animation");
        spine_carHoopTee = assetManager.get("spine/car-hoop-tee.json");
        spine_carHoopTeeAnimationData = assetManager.get("spine/car-hoop-tee.json-animation");
        CarHoopTeeAnimation.animation = spine_carHoopTee.findAnimation("animation");
        spine_carIceCube = assetManager.get("spine/car-ice-cube.json");
        spine_carIceCubeAnimationData = assetManager.get("spine/car-ice-cube.json-animation");
        CarIceCubeAnimation.animation = spine_carIceCube.findAnimation("animation");
        spine_carLadybird = assetManager.get("spine/car-ladybird.json");
        spine_carLadybirdAnimationData = assetManager.get("spine/car-ladybird.json-animation");
        CarLadybirdAnimation.animation = spine_carLadybird.findAnimation("animation");
        spine_carMissileAmerica = assetManager.get("spine/car-missile-america.json");
        spine_carMissileAmericaAnimationData = assetManager.get("spine/car-missile-america.json-animation");
        CarMissileAmericaAnimation.animation = spine_carMissileAmerica.findAnimation("animation");
        spine_carPorch = assetManager.get("spine/car-porch.json");
        spine_carPorchAnimationData = assetManager.get("spine/car-porch.json-animation");
        CarPorchAnimation.animation = spine_carPorch.findAnimation("animation");
        spine_carStumbler = assetManager.get("spine/car-stumbler.json");
        spine_carStumblerAnimationData = assetManager.get("spine/car-stumbler.json-animation");
        CarStumblerAnimation.animation = spine_carStumbler.findAnimation("animation");
        spine_carTanksAlot = assetManager.get("spine/car-tanks-alot.json");
        spine_carTanksAlotAnimationData = assetManager.get("spine/car-tanks-alot.json-animation");
        CarTanksAlotAnimation.animation = spine_carTanksAlot.findAnimation("animation");
        spine_carTursula = assetManager.get("spine/car-tursula.json");
        spine_carTursulaAnimationData = assetManager.get("spine/car-tursula.json-animation");
        CarTursulaAnimation.animation = spine_carTursula.findAnimation("animation");
        spine_egg = assetManager.get("spine/egg.json");
        spine_eggAnimationData = assetManager.get("spine/egg.json-animation");
        EggAnimation.animation = spine_egg.findAnimation("animation");
        spine_libgdx = assetManager.get("spine/libgdx.json");
        spine_libgdxAnimationData = assetManager.get("spine/libgdx.json-animation");
        LibgdxAnimation.animation = spine_libgdx.findAnimation("animation");
        LibgdxAnimation.stand = spine_libgdx.findAnimation("stand");
        spine_ray3k = assetManager.get("spine/ray3k.json");
        spine_ray3kAnimationData = assetManager.get("spine/ray3k.json-animation");
        Ray3kAnimation.animation = spine_ray3k.findAnimation("animation");
        Ray3kAnimation.stand = spine_ray3k.findAnimation("stand");
        spine_stopSign = assetManager.get("spine/stop-sign.json");
        spine_stopSignAnimationData = assetManager.get("spine/stop-sign.json-animation");
        StopSignAnimation.animation = spine_stopSign.findAnimation("animation");
        spine_title = assetManager.get("spine/title.json");
        spine_titleAnimationData = assetManager.get("spine/title.json-animation");
        TitleAnimation.animation = spine_title.findAnimation("animation");
        TitleAnimation.stand = spine_title.findAnimation("stand");
        spine_zaida = assetManager.get("spine/zaida.json");
        spine_zaidaAnimationData = assetManager.get("spine/zaida.json-animation");
        ZaidaAnimation.animation = spine_zaida.findAnimation("animation");
        ZaidaAnimation.stand = spine_zaida.findAnimation("stand");
        textures_textures = assetManager.get("textures/textures.atlas");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_glitch = assetManager.get("sfx/glitch.mp3");
        sfx_logoLibgdxChop = assetManager.get("sfx/logo/libgdx-chop.mp3");
        sfx_logoLibgdxKikiki = assetManager.get("sfx/logo/libgdx-kikiki.mp3");
        sfx_logoLibgdxLoser = assetManager.get("sfx/logo/libgdx-loser.mp3");
        sfx_logoLibgdxMaskSound = assetManager.get("sfx/logo/libgdx-mask-sound.mp3");
        sfx_logoLibgdxScream = assetManager.get("sfx/logo/libgdx-scream.mp3");
        sfx_logoLibgdxTitle = assetManager.get("sfx/logo/libgdx-title.mp3");
        sfx_logoRay3kTune = assetManager.get("sfx/logo/ray3k-tune.mp3");
        sfx_logoRay3k = assetManager.get("sfx/logo/ray3k.mp3");
        sfx_logoWoosh = assetManager.get("sfx/logo/woosh.mp3");
        sfx_titleBoom = assetManager.get("sfx/title/boom.mp3");
        sfx_titleDoppler = assetManager.get("sfx/title/doppler.mp3");
        sfx_wreck = assetManager.get("sfx/wreck.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_engine = assetManager.get("bgm/engine.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class CarDicksWeinerAnimation {
        public static Animation animation;
    }

    public static class CarGroundRollerAnimation {
        public static Animation animation;
    }

    public static class CarHardlyAnimation {
        public static Animation animation;
    }

    public static class CarHoopTeeAnimation {
        public static Animation animation;
    }

    public static class CarIceCubeAnimation {
        public static Animation animation;
    }

    public static class CarLadybirdAnimation {
        public static Animation animation;
    }

    public static class CarMissileAmericaAnimation {
        public static Animation animation;
    }

    public static class CarPorchAnimation {
        public static Animation animation;
    }

    public static class CarStumblerAnimation {
        public static Animation animation;
    }

    public static class CarTanksAlotAnimation {
        public static Animation animation;
    }

    public static class CarTursulaAnimation {
        public static Animation animation;
    }

    public static class EggAnimation {
        public static Animation animation;
    }

    public static class LibgdxAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class Ray3kAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class StopSignAnimation {
        public static Animation animation;
    }

    public static class TitleAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class ZaidaAnimation {
        public static Animation animation;

        public static Animation stand;
    }
}
