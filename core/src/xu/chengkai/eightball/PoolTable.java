package xu.chengkai.eightball;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public class PoolTable {
    World world = new World(new Vector2(0, 0), true);
    List<Ball> stripeBalls = new ArrayList<>();
    List<Ball> solidBalls = new ArrayList<>();
    Ball black;
    Ball white;

    public PoolTable(){
        for (int i = 1; i < 8; i++) {
            stripeBalls.add(new Ball(BallType.STRIPE, this));
            solidBalls.add(new Ball(BallType.SOLID, this));
        }
        black = new Ball(BallType.BLACK, this);
        white = new Ball(BallType.WHITE, this);

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.dispose();

    }
}
