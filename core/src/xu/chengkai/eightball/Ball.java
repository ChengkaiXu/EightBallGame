package xu.chengkai.eightball;

import com.badlogic.gdx.physics.box2d.*;

public class Ball {
    private final BallType ballType;
    private final BodyDef bodyDef = new BodyDef();
    private final PoolTable table;

    public Ball(BallType ballType, PoolTable table){
        this.ballType = ballType;
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.table = table;
        Body body = table.world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        fixtureDef.isSensor = true;
        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }
}
