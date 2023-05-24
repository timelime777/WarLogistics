import java.awt.*;

public class Tile {
    int type;
    double capacity;
    double current_capacity;
    int x;
    int y;
    int size;

    Tile(int type, double capacity, int x, int y, int size) {
        this.type = type;
        this.capacity = capacity;
        this.x = x;
        this.y = y;
        this.size = size;
        this.current_capacity = 0;
    }

    void paint(Graphics g) {
        int x0 = this.x - (int) (this.size * (1 + this.current_capacity * 2 / this.capacity));
        int y0 = this.y - (int) (this.size * (1 + this.current_capacity * 2 / this.capacity));
        Color color_main = new Color(0, 108, 239);
        Color[] colors = {Color.white, color_main, Color.red, Color.green, Color.magenta, Color.orange};
        g.setColor(colors[this.type]);
        g.fillRect(x0, y0, (int) (2 * this.size * (1 + this.current_capacity * 2/ this.capacity)), (int) (2 * this.size * (1 + this.current_capacity * 2 / this.capacity)));
        g.setColor(Color.black);
        g.drawRect(x0, y0, (int) (2 * this.size * (1 + this.current_capacity * 2/ this.capacity)), (int) (2 * this.size * (1 + this.current_capacity * 2 / this.capacity)));
        g.drawString(Integer.toString((int) this.current_capacity), this.x, this.y);

    }

    void update(int timeDelta) {///TODO upgrade
        if (this.type == 1) {
            this.current_capacity += 0.001 * timeDelta;
            if (this.current_capacity > this.capacity) {
                this.current_capacity = this.capacity;
            }
            ///TODO Upgrade
        }

        if (this.type == 2) {
            this.current_capacity += 0.001 * timeDelta;
            if (this.current_capacity > this.capacity) {
                this.current_capacity = this.capacity;
            }
            ///TODO Upgrade
        }

        if (this.type == 3) {
            this.current_capacity += 0.001 * timeDelta;
            if (this.current_capacity > this.capacity) {
                this.current_capacity = this.capacity;
            }
            ///TODO Upgrade
        }

        if (this.type == 4) {
            this.current_capacity += 0.001 * timeDelta;
            if (this.current_capacity > this.capacity) {
                this.current_capacity = this.capacity;
            }
            ///TODO Upgrade
        }

        if (this.type == 5) {
            this.current_capacity += 0.001 * timeDelta;
            if (this.current_capacity > this.capacity) {
                this.current_capacity = this.capacity;
            }
            ///TODO Upgrade
        }

        if (this.type == 0) {
            ///TODO
        }
    }
}