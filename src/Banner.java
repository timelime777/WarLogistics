import java.awt.*;

public class Banner {
    int x;
    int y;
    int width;
    int height;

    String string;


    Banner(int x, int y, String s){
        this.x = x;
        this.y = y;
        this.string = s;
    }

    void paint(Graphics g){
        g.setColor(Color.black);
        g.drawString(this.string, this.x - 22, this.y);

    }
}