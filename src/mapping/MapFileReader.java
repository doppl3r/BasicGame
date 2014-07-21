package mapping;

import android.util.Log;
import pack.basicgame.main.R;
import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MapFileReader {
    private static InputStream in;
    private static BufferedReader input;
    private static TileMap tempMap;
    private static LinkedList<String> stack;

    public MapFileReader(){
        //convertToTileMap(fileName);
    }
    public static TileMap convertToTileMap(Context context){
        //load the level file of your choosing
        in = context.getResources().openRawResource(R.raw.level1);
        input = new BufferedReader(new InputStreamReader(in));
        stack = new LinkedList<String>();

        int idLength = 4;
        int rows = 0;
        int cols;
        String aux;
        boolean countRows = true;

        try {
            //read file content
            while ((aux = input.readLine()) != null) {
                stack.add(aux);
                if (countRows && aux.length() >= idLength){
                    if (aux.substring(0,idLength).matches("-?\\d+")) rows++;
                }
            }
            //initialize parameters
            if (rows > 0){
                cols = stack.get(0).length()/idLength;
                tempMap = new TileMap(cols, rows);

                int id = 0;
                String stringNum;
                for (int row = 0; row < rows; row++){
                    for (int col = 0; col < cols; col++){
                        if (stack.get(row).length() == cols*idLength){
                            stringNum = stack.get(row).substring(col*idLength, (col*idLength)+idLength);
                            if (stringNum.matches("-?\\d+")){
                                id = Integer.parseInt(stringNum);
                                tempMap.setTileID(row, col, id);

                            }
                        }
                    }
                }
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        return tempMap;
    }
}
