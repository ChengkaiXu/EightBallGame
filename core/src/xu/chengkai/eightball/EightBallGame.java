package xu.chengkai.eightball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	Texture img;
	BitmapFont font;
	Label label;
	Stage stage;
	TextButton button;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		label = new Label("Daizi", new Label.LabelStyle(font, Color.WHITE));
		label.setPosition(500, 400);
		button = new TextButton("XiaoChouZiv", new TextButton.TextButtonStyle(null, null, null, font));
		button.setPosition(500, 500);
		stage = new Stage();
		stage.addActor(label);
		stage.addActor(button);
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				button.setText("DabianZiv");
			}
		});
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		stage.draw();
		stage.act();
		batch.begin();
		batch.draw(img, 0, 0);
		font.getData().setScale(10);
		font.draw(batch, "DaiziLeo", 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
