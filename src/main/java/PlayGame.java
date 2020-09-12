import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PlayGame extends JPanel implements KeyListener, ActionListener {
    private boolean play= false;
    private int score =0;
    private int bricks= 27;
    private Timer timer;
    private int delay= 8;
    private int barX= 310;
    private int ballX= 120;
    private int ballY= 350;
    private int ballXDierction= -1;
    private int ballYDirection= -2;
    private BrickGenerator brickGenerator;


    public PlayGame() {
        brickGenerator= new BrickGenerator(6,9);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer= new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics graphics){
        //Setting BackGround
        graphics.setColor(Color.BLACK);
        graphics.fillRect(1,1,692,592);

        //Creating Map for Bricks
        brickGenerator.draw((Graphics2D) graphics);

        //Display Scores
        graphics.setColor(Color.ORANGE);
        graphics.setFont(new Font("serif",Font.BOLD, 25));
        graphics.drawString("Score: "+score,590,30);

        // Creating Borders
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0,0,3,592);
        graphics.fillRect(0,0,692,3);
        graphics.fillRect(691,0,3,592);

        //Creating Paddle
        graphics.setColor(Color.BLUE);
        graphics.fillRect(barX,550,100,8);

        //Creating the Ball
        graphics.setColor(Color.CYAN);
        graphics.fillOval(ballX,ballY,20,20);

        //If one finishes the game

        if(bricks<=0){
            play=false;
            ballYDirection=0;
            ballXDierction=0;
            graphics.setColor(Color.YELLOW);
            graphics.setFont(new Font("serif",Font.BOLD, 25));
            graphics.drawString("Congratulations!! You Won",260,300);

            graphics.setFont(new Font("serif",Font.BOLD, 20));
            graphics.drawString("Press Enter to Restart",260,350);
        }

        if(ballY>570){
            play=false;
            ballYDirection=0;
            ballXDierction=0;
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("serif",Font.BOLD, 25));
            graphics.drawString("Game Over!! Score: "+score,190,300);

            graphics.setFont(new Font("serif",Font.BOLD, 20));
            graphics.drawString("Press Enter to Restart",210,350);
        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(barX,550,100,8)))
                ballYDirection=-ballYDirection;

            A: for(int i=0;i<brickGenerator.bricks.length;i++){
                for(int j=0;j<brickGenerator.bricks[0].length;j++){
                    if(brickGenerator.bricks[i][j]>0){
                        int brickX= j*brickGenerator.brickWidth +80;
                        int brickY= i*brickGenerator.brickHeight +50;
                        int brickWidth= brickGenerator.brickWidth;
                        int brickHeight= brickGenerator.brickHeight;

                        Rectangle rectangle= new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRectangle= new Rectangle(ballX,ballY,20,20);
                        Rectangle brickRect= rectangle;

                        //check if ball inetrsects the brick
                        if(ballRectangle.intersects(brickRect)){
                            brickGenerator.setBrickValue(0,i,j);
                            bricks--;
                            score +=5;
                            if(ballX+19<=brickRect.x || ballX+1>=brickRect.x+brickRect.width){
                                ballXDierction= -ballXDierction;
                            }
                            else
                                ballYDirection=-ballYDirection;
                            break A;
                        }
                    }
                }
            }

            ballX+=ballXDierction;
            ballY+=ballYDirection;
            if(ballX<0){
                ballXDierction=-ballXDierction;
            }
            if(ballY<0)
                ballYDirection=-ballYDirection;
            if(ballX>670){
                ballXDierction=-ballXDierction;
            }

        }
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(barX>=600)
                barX=600;
            else
                moveRight();
        }

        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            if(barX<=10)
                barX=10;
            else
                moveLeft();
        }

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                ballX=120;
                ballY= 350;
                ballXDierction=-1;
                ballYDirection=-2;
                barX= 310;
                score=0;
                bricks=21;
                brickGenerator= new BrickGenerator(3,7);
                repaint();
            }
        }

    }

    private void moveLeft() {
        play=true;
        barX-=20;
    }

    private void moveRight() {
        play=true;
        barX+=20;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
