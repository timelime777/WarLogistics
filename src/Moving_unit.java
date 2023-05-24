import java.awt.*;

public class Moving_unit {
    double x;
    double y;

    int type;

    int number_of_units;

    int size = 4;

    Moving_unit(double x, double y, int number_of_units, int type){
        this.type = type;
        this.x = x;
        this.y = y;
        this.number_of_units = number_of_units;
    }

    void paint(Graphics g){
        Color color_main = new Color(0, 108, 239);
        Color[] colors = {Color.white, color_main, Color.red, Color.green, Color.magenta, Color.orange};
        g.setColor(colors[type]);
        int unit_size = (this.number_of_units / 10);

        g.fillOval((int)(this.x - size) - unit_size, (int)(this.y - size) - unit_size, 2 * (size + this.number_of_units / 10), 2 * (size + this.number_of_units / 10));
        g.setColor(Color.white);
        g.drawOval((int)(this.x - size) - unit_size, (int)(this.y - size) - unit_size, 2 * (size + this.number_of_units / 10), 2 * (size + this.number_of_units / 10));
    }

    void update(double delta_x, double delta_y){
        this.x += delta_x;
        this.y += delta_y;
    }

    void delete(Graphics g){
        this.number_of_units = 0;
        this.size = 0;

    }
}