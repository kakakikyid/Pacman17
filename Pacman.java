import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pacman extends JFrame implements Runnable, KeyListener {
    private int angle = 0, arrow = KeyEvent.VK_RIGHT;
    // iterasi=1;
    Body pacman, food;

    public Pacman(){
        pacman = new Body(0, 30);
        food = new Body(60, 60);
        init();
    }

    private void init(){
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(600, 600));
        this.setTitle("Pacman");
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // public void run(){
    //     while(true){
    //         pacman.setX(pacman.getX() + 20);
    //         if(pacman.getX() >= getWidth()){
    //             pacman.setX(0);
    //         } else if (pacman.getX() <= 0){
    //             pacman.setX(getWidth());
    //         }
    //         repaint();
    //         try{
    //             Thread.sleep(100);
    //         } catch (Exception e) {
    //             // TODO: handle exception
    //         }
    //     }
    // }

public void run() {
    while (true){
        move();
        repaint();
        try{
            // if (iterasi>=3)
            // Thread.sleep(10);
            // else
            Thread.sleep(100);
        } catch (Exception e){
            System.out.println("Missing");
        }
    }
}

private void move(){
    switch (arrow){
        case KeyEvent.VK_LEFT: {
            pacman.setX(pacman.getX() - 30);
            angle = 240;
            break;
        }
        case KeyEvent.VK_RIGHT: {
            pacman.setX(pacman.getX() + 30);
            angle = 0;
            break;
        }
        case KeyEvent.VK_UP: {
            pacman.setY(pacman.getY() - 30);
            angle = 90;
            break;
        }
        case KeyEvent.VK_DOWN: {
            pacman.setY(pacman.getY() + 30);
            angle = 270;
            break;
        }
        }

        if (pacman.getX() >= getWidth())
            pacman.setX(0);
        else if (pacman.getY() >= getHeight())
            pacman.setY(30);
        else if (pacman.getY() <= 30)
            pacman.setY(getHeight());
        else if (pacman.getX() <= 0)
            pacman.setX(getWidth());
    }

    public boolean isEat(){
        if (pacman.getX() == food.getX() && pacman.getY() == food.getY()){
            int foodX = (int) (Math.random() * 19) + 1;
            int foodY = (int) (Math.random() * 19) + 1;
            food.setX(30 * foodX);
            food.setY(30 * foodY);
            // iterasi+=1;
            return true;
        }
        return false;
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

    public void paint(Graphics p){
        p.setColor(Color.GRAY);
        p.fillRect(getX(), getY(), getWidth(), getHeight());

        p.setColor(Color.BLUE);
        if (isEat()){
            p.fillArc(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), angle, 360);
        } else
        p.fillArc(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), angle, 315);

        p.setColor(Color.GREEN);
        p.fillRect(food.getX(), food.getY(), food.getWidth(), food.getHeight());
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        switch (key){
            case KeyEvent.VK_LEFT:
                arrow = KeyEvent.VK_LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                arrow = KeyEvent.VK_RIGHT;
                break;
            case KeyEvent.VK_UP:
                arrow = KeyEvent.VK_UP;
                break;
            case KeyEvent.VK_DOWN:
                arrow = KeyEvent.VK_DOWN;
                break;
        }
    }

}
