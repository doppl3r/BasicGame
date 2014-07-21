package game;

import android.content.Context;
import mapping.TileBuffer;
import textures.SpriteSheet;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Level {
    private SpriteSheet terrain;
    private TileBuffer map;
    private int tileSize;
	
	public Level(Context context, int tileSize){
        this.tileSize=tileSize;
        terrain = new SpriteSheet(GamePanel.textures.terrain,8,8,0);
        map = new TileBuffer(context,tileSize);
	}
	public void draw(Canvas canvas, Paint paint){
        map.draw(canvas, GamePanel.getWidth(), GamePanel.getHeight(), terrain);
	}
	public void update(double mod, double x1, double y1){
        map.update(x1,y1,tileSize,0,mod);
	}
}
