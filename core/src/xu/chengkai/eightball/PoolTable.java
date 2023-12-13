package xu.chengkai.eightball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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

    Body table;

    public PoolTable(){
        Gdx.input.setInputProcessor(this);
        world.setContactListener(new ListenerClass());

        BodyDef tableDef = new BodyDef();
        tableDef.position.set(0, 0);
        tableDef.type = BodyDef.BodyType.StaticBody;
        table = world.createBody(tableDef);

        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);
        def.friction = 0.5f;
        def.density = 0;
        def.shape = shape;
        table.createFixture(def);


        for (int i = 1; i < 8; i++) {
            stripeBalls.add(new Ball(BallType.STRIPE, this));
            solidBalls.add(new Ball(BallType.SOLID, this));
        }
        black = new Ball(BallType.BLACK, this);
        white = new Ball(BallType.WHITE, this);

//        addHole(WIDTH / 2, HEIGHT / 2);
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
        if (white.getBody().getLinearVelocity().isZero(0.01f)){
        white.getBody().applyForceToCenter((white.getBody().getPosition().x - screenX / SCALE) * 100000,
                (screenY / SCALE - white.getBody().getPosition().y) * 100000, true);
        }
        return false;
    }

    public void addHole(float x, float y){
        BodyDef holedef = new BodyDef();
        holedef.type = BodyDef.BodyType.StaticBody;
        holedef.position.set(x, y);
        Body body = world.createBody(holedef);
        CircleShape circle = new CircleShape();
        circle.setRadius(18f / PoolTable.SCALE);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);

    }

    public class ListenerClass implements ContactListener{

        @Override
        public void beginContact(Contact contact) {
            if (contact.getFixtureA().isSensor() || contact.getFixtureB().isSensor()){
                world.destroyBody(contact.getFixtureA().isSensor() ?
                        contact.getFixtureB().getBody() : contact.getFixtureA().getBody());
                System.out.println("Collision");
            }
        }

        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }
    }
}
