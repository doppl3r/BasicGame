package mapping;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import textures.SpriteSheet;

public class TileBuffer {
    private TileMap map;
    private int tileID;
    private double mainX;
    private double mainY;
    private double blockSize;
    private double t;
    private boolean grid;

    public TileBuffer(Context context, int blockSize){
        this.blockSize=blockSize;
        map = new TileMap(10,10); //generic numbers
        map.setMapList(MapFileReader.convertToTileMap(context));
        //map.defaultMap();
    }
    public void draw(Canvas canvas, int maxX, int maxY, SpriteSheet texture){
        //canvas.drawBitmap(texture.getBitmap(),0,0,null);
        int windowX = maxX;
        int windowY = maxY;
        int tempX;
        int tempY;
        //set parameters
        int minCol = -(int)(mainX/blockSize);
        int maxCol = (int)(minCol+((windowX+(blockSize*2))/blockSize));
        if (minCol < 0) minCol = 0;
        if (maxCol < 0) maxCol = 0;
        if (maxCol > map.getCols()) maxCol = map.getCols();
        if (minCol > maxCol) minCol = maxCol;

        int minRow = -(int)(mainY/blockSize);
        int maxRow = (int)(minRow+((windowY+(blockSize*2))/blockSize));
        if (minRow < 0) minRow = 0;
        if (maxRow < 0) maxRow = 0;
        if (maxRow > map.getRows()) maxRow = map.getRows();
        if (minRow > maxRow) minRow = maxRow;

        for (int row = minRow; row < maxRow; row++){
            for (int col = minCol; col < maxCol; col++){
                tempX = (int)(mainX+(col*blockSize));
                tempY = (int)(mainY+(row*blockSize));
                texture.animate(map.getTile(row,col).getID()-1);
                texture.update(tempX,tempY,(int)blockSize,(int)blockSize);
                texture.draw(canvas,windowX,windowY);
            }
        }
    }
    public void update(double mainX, double mainY, double blockSize, int blockID, double mod){
        this.mainX=mainX;
        this.mainY=mainY;
        this.blockSize=blockSize;
        this.tileID =blockID;
    }
    //mouse actions
    public boolean down(int x, int y, int buttonID){;
        boolean inBounds = false;
        //save the map before the next action! Very important!
        if (x-mainX > 0 && x-mainX < getMapPixelWidth() &&
            y-mainY > 0  && y-mainY < getMapPixelHeight()){
            t=map.saveMap();
            inBounds = true;
        }
        return inBounds;
    }
    public void move(int x, int y, int buttonID){

    }
    public void up(int x, int y, int buttonID){

    }
    public void hover(int x, int y){

    }
    public void floodFill(int x, int y, int oldVal, int newVal){
        int row = (int)((int)(y-mainY)/blockSize);
        int col = (int)((int)(x-mainX)/blockSize);
        if (x-mainX > 0 && x-mainX < getMapPixelWidth() &&
            y-mainY > 0  && y-mainY < getMapPixelHeight()){
            map.floodFill(row,col,oldVal,newVal);
        }
    }
    public void toggleGrid(){ grid = !grid; }
    public void addRow(){ t=map.saveMap(); map.addRow(); }
    public void removeRow(){ t=map.saveMap(); map.removeLastRow(); }
    public void addCol(){ t=map.saveMap(); map.addCol(); }
    public void removeCol(){ t=map.saveMap(); map.removeLastCol(); }
    public void setNewMap(int cols, int rows){ t=map.saveMap(); map.setNewMap(cols,rows); }
    public void setRows(int rows){ map.setRows(rows); }
    public void setCols(int cols){ map.setCols(cols); }
    public void setRowsAndCols(int rows, int cols, boolean clear){
        if (!clear) t=map.saveMap();
        setRows(rows);
        setCols(cols);
    }
    public void resetMap(){ t=map.saveMap(); map.resetMap(); }
    public void clearMap(){ t=map.saveMap(); map.clearMap(); }
    public boolean setTileID(int row, int col, int tileID){ return map.setTileID(row,col,tileID); }
    public void undo(){ map.undo(); }
    public void redo(){ map.redo(); }
    //getters
    public TileMap getMap(){ return map; }
    public int getMapPixelWidth(){ return (int)blockSize*map.getCols(); }
    public int getMapPixelHeight(){ return (int)blockSize*map.getRows(); }
    public int getBlockSize(){ return (int)blockSize; }
    public double getTime(){ return t; }
    public void setEnableHistory(boolean enableHistory){ map.setEnableHistory(enableHistory); }
    public boolean gridIsOn(){ return grid; }
    public void setMap(int[][] newMap){ map.setMap(newMap); }
    public void setMapList(TileMap map){
        this.map.setMapList(map);
    }
}
