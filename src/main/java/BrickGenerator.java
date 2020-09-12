import java.awt.*;

public class BrickGenerator {
    public int[][] bricks;
    public int brickWidth;
    public int brickHeight;
    public BrickGenerator(int row, int col){
        bricks= new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                bricks[i][j]=1;
        }
        brickWidth = 540/col;
        brickHeight= 150/row;
    }

    public void draw(Graphics2D graphics2D){
        for(int i=0;i<bricks.length;i++){
            for(int j=0;j<bricks[0].length;j++){
                if(bricks[i][j]>0){
                    graphics2D.setColor(Color.GRAY);
                    graphics2D.fillRect(j* brickWidth +80,i*brickHeight+50, brickWidth,brickHeight);
                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(j* brickWidth +80,i*brickHeight+50, brickWidth,brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int val, int row, int column){
        bricks[row][column]= val;
    }
}
