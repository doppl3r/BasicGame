package game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Game {
	private float time;
	private boolean isActive = true;
	public static World world;
    public static GUI gui;

	public Game(Context context){
        //create a world
        world = new World(context);
        gui = new GUI(context);
    }
	public void draw(Canvas canvas, Paint paint){
		if (isActive){
			//draw game components
			world.draw(canvas, paint);
            gui.draw(canvas, paint);
		}
	}
	public void update(double mod){
		if (isActive){
			if (time > 1440) time %= 1440;
			else time += (mod*25);
			//update game components
			world.update(mod);
		}
	}
	public void setActivity(boolean isActive){ this.isActive=isActive; }
	public void down(int x, int y){
		if (isActive){
			world.down(x, y);
            gui.down(x,y);
		}
	}
	public void move(int x, int y){
		if (isActive){
			world.move(x, y);
            gui.move(x,y);
		}
	}
	public void up(int x, int y){
		if (isActive){
			world.up(x, y);
            if (gui.up(x,y)) world.adjustXY(0,0); //reset map when clicked
		}
	}
}
