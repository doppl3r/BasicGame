package game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import buttons.Button;

public class GUI {
    Context context;
    boolean buildButtons;
    private Button button1;

    public GUI(Context context){
        this.context=context;
    }
    public void draw(Canvas canvas,Paint paint){
        if (!buildButtons){
            button1 = new Button(GamePanel.textures.start,canvas.getWidth()/2,canvas.getHeight()/2,1,2,true,context);
            button1.resize(GamePanel.getHeight()/128); //doesn't need to be 128
            buildButtons = true;
        }
        else{
            button1.draw(canvas);
        }
    }
    public void update(){

    }
    public boolean down(int x, int y){
        button1.down(x,y);
        return false;
    }
    public boolean move(int x, int y){
        button1.move(x,y);
        return false;
    }
    public boolean up(int x, int y){
        if (button1.up(x,y)){
            GamePanel.game.world.adjustXY(0,0); //reset world position if pressed
            button1.hide();
            GamePanel.audio.playSound(0);
        }
        return false;
    }
}
