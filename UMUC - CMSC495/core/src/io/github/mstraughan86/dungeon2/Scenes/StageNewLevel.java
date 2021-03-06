package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.*;
import io.github.mstraughan86.dungeon2.Characters.PC.DevPC;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageNewLevel extends Stage {

    TextButton cancelButton;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    DevPC player;
    TiledMap map;

    public StageNewLevel(Viewport viewport) {
        super(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        getViewport().setCamera(camera);

        map = new TmxMapLoader().load("Levels/Desert.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        createEntities();
        createActions();
        createListeners();
        createInputs();
    }

    private void createEntities() {
        cancelButton = UIFactory.textButton("CANCEL");

        cancelButton.setSize(160, 50);
        cancelButton.setPosition(20, 20);

        this.addActor(cancelButton);

        player = new DevPC();

        this.addActor(player);
        this.setKeyboardFocus(player);
    }

    private void createActions() {

    }

    private void createListeners() {
        cancelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.previousStage);
            }
        });
    }

    private void createInputs() {

    }

    @Override
    public void draw() {
        renderer.setView(camera);
        camera.position.x = player.getX();
        camera.position.y = player.getY();
        camera.position.x = MathUtils.clamp(camera.position.x, (camera.viewportWidth / 2), 1600 - (camera.viewportWidth / 2));
        camera.position.y = MathUtils.clamp(camera.position.y, (camera.viewportHeight / 2), 1600 - (camera.viewportHeight / 2));

        renderer.render();
        super.draw();
    }

}