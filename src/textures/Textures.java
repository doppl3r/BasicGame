package textures;
import pack.basicgame.main.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.view.View;

public class Textures extends View {
	//game textures
	public Bitmap terrain;
    public Bitmap start;

    //gui textures
	public Textures(Context context){
		super(context);
		//set up bitmap for all sdk's
		BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Config.ARGB_8888;  // API Level 1          
        options.inScaled = false;
        //game textures need to be initiated here
		terrain = BitmapFactory.decodeResource(getResources(), R.drawable.terrain, options);
        start   = BitmapFactory.decodeResource(getResources(), R.drawable.start,   options);
	}
}
