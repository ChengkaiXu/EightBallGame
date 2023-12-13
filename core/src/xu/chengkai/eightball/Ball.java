package xu.chengkai.eightball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Ball {
    private final BallType ballType;
    private final PoolTable table;
    private final Body body;
    private final Color color;

    public Ball(BallType ballType, PoolTable table){
        this.ballType = ballType;
        this.table = table;

        BodyDef bodyDef = new BodyDef();
        Random random = new Random();

        switch (ballType) {
            case WHITE -> {
                bodyDef.position.set(PoolTable.WIDTH / 4, PoolTable.HEIGHT / 2);
            }
            case BLACK -> {
                bodyDef.position.set(PoolTable.WIDTH / 4 * 3, PoolTable.HEIGHT / 2);
            }
            case SOLID, STRIPE -> {
                bodyDef.position.set(PoolTable.WIDTH / 4 * 2, PoolTable.HEIGHT / 2);
            }
        }

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = table.world.createBody(bodyDef);


        CircleShape circle = new CircleShape();
        circle.setRadius(18f / PoolTable.SCALE);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1700;
        fixtureDef.friction = 0.06f;
        fixtureDef.restitution = 0.93f;

        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();


        FrictionJointDef frictionJointDef = new FrictionJointDef();
        frictionJointDef.maxForce = 1000;
        frictionJointDef.maxTorque = 0;
        frictionJointDef.initialize(table.table, body, new Vector2(body.getPosition().x, body.getPosition().y));
        table.world.createJoint(frictionJointDef);


        this.color = getRandomColor();
    }

    public static Color getRandomColor() {
        float r = ThreadLocalRandom.current().nextFloat();
        float g = ThreadLocalRandom.current().nextFloat();
        float b = ThreadLocalRandom.current().nextFloat();
        float a = 1.0f;

        return new Color(r, g, b, a);
    }

    public Body getBody() {
        return body;
    }

    public void render() {
        switch (ballType) {
            case BLACK -> {
                EightBallGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                EightBallGame.shapeRenderer.setColor(Color.BLACK);
            }
            case WHITE -> {
                EightBallGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                EightBallGame.shapeRenderer.setColor(Color.WHITE);
            }
            case SOLID -> {
                EightBallGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                EightBallGame.shapeRenderer.setColor(color);

                //EightBallGame.shapeRenderer.setColor(Color.CYAN);
            }
            case STRIPE -> {
                EightBallGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                Gdx.gl.glLineWidth(4);
                EightBallGame.shapeRenderer.setColor(color);
            }
        }
        EightBallGame.shapeRenderer.circle(body.getPosition().x * PoolTable.SCALE, body.getPosition().y * PoolTable.SCALE, 18f);
        EightBallGame.shapeRenderer.end();

        if (ballType == BallType.BLACK) {
            EightBallGame.batch.begin();
            EightBallGame.font.getData().setScale(1.5f);
            EightBallGame.font.draw(EightBallGame.batch, "8", body.getPosition().x * PoolTable.SCALE - 6, body.getPosition().y * PoolTable.SCALE + 6);
            EightBallGame.batch.end();
        }

    }

}
