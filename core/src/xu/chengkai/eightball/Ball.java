package xu.chengkai.eightball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;


public class Ball {
    private final BallType ballType;
    private final PoolTable table;
    private final Body body;

    public Ball(BallType ballType, PoolTable table){
        this.ballType = ballType;
        this.table = table;

        BodyDef bodyDef = new BodyDef();
        Random random = new Random();
        bodyDef.position.set(random.nextFloat(0, 1000), random.nextFloat(0, 1000));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = table.world.createBody(bodyDef);


        CircleShape circle = new CircleShape();
        circle.setRadius(18f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();

        body.setLinearVelocity(5000f, 5000f);
    }

    public void render() {
        EightBallGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        switch (ballType) {
            case BLACK -> EightBallGame.shapeRenderer.setColor(Color.GRAY);
            case WHITE -> EightBallGame.shapeRenderer.setColor(Color.WHITE);
            case SOLID -> EightBallGame.shapeRenderer.setColor(Color.CYAN);
            case STRIPE -> EightBallGame.shapeRenderer.setColor(Color.TAN);
        }
        EightBallGame.shapeRenderer.circle(body.getPosition().x, body.getPosition().y, 18f);

        EightBallGame.shapeRenderer.end();
    }

}
