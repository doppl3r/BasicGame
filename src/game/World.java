package game;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class World {
	private int mainX, mainY;
	private int tileSize = GamePanel.getHeight()/8;
	private int downX;
	private int downY;
	private int dragX;
	private int dragY;
    private Rect edgeRect;
	private Level level;
	
	public World(Context context){
		mainX = 0;
		mainY = 0;
		level = new Level(context, tileSize);
        edgeRect = new Rect();
	}
	public void draw(Canvas canvas, Paint paint){
		level.draw(canvas, paint);
	}
	public void update(double mod){
        //draw map
		level.update(mod, (mainX - (downX - dragX)), (mainY - (downY - dragY)));
        //world.update(mod, 0,0);
	}
	public void down(int x1, int y1){
        //create point of origin
        downX = dragX = x1;
        downY = dragY = y1;
	}
	public void move(int x1, int y1){
		//check drag
        dragX = x1;
        dragY = y1;
	}
	public void up(int x1, int y1){
        if (Math.abs(downX-dragX) < 8 && Math.abs(downY-dragY) < 8){
            //tell your player to do something instead of dragging the map
            Log.d("Move Play?","true");
        }
		//release drag
		mainX -= (downX-dragX);
		mainY -= (downY-dragY);
        resetEdgeRect();
		downX = downY = dragX = dragY = 0;
	}
    public void resetEdgeRect(){ edgeRect.top=edgeRect.right=edgeRect.bottom=edgeRect.left=0; }
    public void adjustXY(int x, int y){ mainX = x; mainY = y; }
}
