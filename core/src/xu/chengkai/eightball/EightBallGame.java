package xu.chengkai.eightball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class EightBallGame extends ApplicationAdapter {
	SpriteBatch batch;
	Camera camera = new Camera() {
		@Override
		public void update() {

		}

		@Override
		public void update(boolean updateFrustum) {

		}
	};
	TextButton button;
	PoolTable poolTable;
	Box2DDebugRenderer renderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		poolTable = new PoolTable();

		Gdx.input.setInputProcessor(poolTable);

		renderer = new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		renderer.render(poolTable.getWorld(), camera.combined);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose();
	}
}
