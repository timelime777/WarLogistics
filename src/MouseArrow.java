import java.awt.*;

public class MouseArrow {
    int start_point_x;
    int start_point_y;
    int end_point_x;
    int end_point_y;

    MouseArrow(int x1, int y1, int x2, int y2){
        this.start_point_x = x1;
        this.start_point_y = y1;
        this.end_point_x = x2;
        this.end_point_y = y2;
    }

    void paint(Graphics g){
        if (!((this.start_point_x == this.end_point_x) && (this.start_point_y == this.end_point_y))){
            Color arrow_color = new Color(0, 250, 212, 255);
            g.setColor(arrow_color);
            g.drawLine(this.start_point_x, this.start_point_y, this.end_point_x, this.end_point_y);
            g.drawRect(this.end_point_x - 2, this.end_point_y - 2, 4, 4);
            g.drawOval(this.start_point_x - 2, this.start_point_y - 2, 4, 4);
        }
    }
}
