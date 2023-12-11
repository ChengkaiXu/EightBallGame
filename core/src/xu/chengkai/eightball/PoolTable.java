package xu.chengkai.eightball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTable extends InputAdapter {

    public static final float SCALE = 50;

    public static final float WIDTH = 1220 / SCALE;

    public static final float HEIGHT = 677 / SCALE;
    Texture background = new Texture("Pooltable.jpg");
    World world = new World(new Vector2(0, 0), true);
    List<Ball> stripeBalls = new ArrayList<>();
    List<Ball> solidBalls = new ArrayList<>();
    Ball black;
    Ball white;

    public PoolTable(){
        Gdx.input.setInputProcessor(this);
        for (int i = 1; i < 8; i++) {
            stripeBalls.add(new Ball(BallType.STRIPE, this));
            solidBalls.add(new Ball(BallType.SOLID, this));
        }
        black = new Ball(BallType.BLACK, this);
        white = new Ball(BallType.WHITE, this);

        addBorder(0, 0, WIDTH, 1);
        addBorder(0, HEIGHT - 50 / SCALE, WIDTH, 1);
        addBorder(0, 0, 1, HEIGHT);
        addBorder(WIDTH - 50 / SCALE, 0, 1, HEIGHT);
    }

    private void addBorder(float x, float y, float width, float height) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(x + width / 2, y + height / 2));
        Body body = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.6f;
        fixtureDef.restitution = 0.2f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    int timer = 0;

    public void render() {
        world.step(1 / 60f, 6, 2);

        EightBallGame.batch.begin();
        EightBallGame.batch.draw(background, 0, 0);
        EightBallGame.batch.end();

//        debugRenderer.render(world, camera.combined);
        solidBalls.forEach(Ball::render);
        stripeBalls.forEach(Ball::render);
        black.render();
        white.render();
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        white.getBody().applyForceToCenter(ThreadLocalRandom.current().nextFloat(0, 1000000), 500, true);
        return false;
    }

}
