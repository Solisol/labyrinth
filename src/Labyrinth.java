import java.awt.*;

public class Labyrinth extends Frame {

    public Labyrinth() {
    }

    public void paint(Graphics g)
    {
        g.drawRect(0,0,100,100);
        g.setColor(Color.cyan);
        g.fillRect(0,0,100,100);
        g.setColor(Color.black);
        g.drawString("Hello Sabapathy",60,60);
        g.drawOval(100,100,25,25);
        g.setColor(Color.red);
        g.fillOval(100,100,15,15);
    }
}
