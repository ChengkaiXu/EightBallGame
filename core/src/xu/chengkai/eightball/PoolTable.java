package xu.chengkai.eightball;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

public class PoolTable {
    Texture background = new Texture("Pooltable.jpg");
    World world = new World(new Vector2(0, 0), true);
    List<Ball> stripeBalls = new ArrayList<>();
    List<Ball> solidBalls = new ArrayList<>();
    Ball black;
    Ball white;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    OrthographicCamera camera = new OrthographicCamera();

    public PoolTable(){
        for (int i = 1; i < 8; i++) {
            stripeBalls.add(new Ball(BallType.STRIPE, this));
            solidBalls.add(new Ball(BallType.SOLID, this));
        }
        black = new Ball(BallType.BLACK, this);
        white = new Ball(BallType.WHITE, this);

        addBorder(0, 0, 1000, 0);
        addBorder(0, 0, 0, 1000);
        addBorder(0, 500, 1000, 500);
        addBorder(500, 0, 500, 1000);
    }

    private void addBorder(float x1, float y1, float x2, float y2) {
        BodyDef def = new BodyDef();
        def.position.set(new Vector2(x1, y1));
        Body body = world.createBody(def);
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(x1, y1), new Vector2(x2, y2));
        body.createFixture(shape, 0);
    }

    public void render() {
        EightBallGame.batch.begin();
        EightBallGame.batch.draw(background, 0, 0);
        EightBallGame.batch.end();

//        debugRenderer.render(world, camera.combined);
        solidBalls.forEach(Ball::render);
        stripeBalls.forEach(Ball::render);
        black.render();
        white.render();
    }

}
