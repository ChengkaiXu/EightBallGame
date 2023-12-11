package xu.chengkai.eightball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class EightBallGame extends ApplicationAdapter {
	static SpriteBatch batch;
	static BitmapFont font;
	static ShapeRenderer shapeRenderer;

	PoolTable table;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		table = new PoolTable();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		table.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
