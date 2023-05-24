import java.awt.*;
import java.util.ArrayList;

public class Road {

    Tile start_tile;
    Tile end_tile;

    ArrayList<Moving_unit> caravans = new ArrayList<>();

    Road(Tile start_tile, Tile end_tile){
        this.start_tile = start_tile;
        this.end_tile = end_tile;
    }

    void start_moving_units1(){
        int number_of_units = (int) this.start_tile.current_capacity;

        if (number_of_units > 0) {
            this.start_tile.current_capacity = 0;

            Moving_unit caravan = new Moving_unit(this.start_tile.x, this.start_tile.y, number_of_units, 1);
            this.caravans.add(caravan);
        }
    }

    void start_moving_units2(){
        int number_of_units = (int) this.start_tile.current_capacity;

        if (number_of_units > 0) {
            this.start_tile.current_capacity = 0;

            Moving_unit caravan = new Moving_unit(this.start_tile.x, this.start_tile.y, number_of_units, 2);
            this.caravans.add(caravan);
        }
    }

    void start_moving_units3(){
        int number_of_units = (int) this.start_tile.current_capacity;

        if (number_of_units > 0) {
            this.start_tile.current_capacity = 0;

            Moving_unit caravan = new Moving_unit(this.start_tile.x, this.start_tile.y, number_of_units, 3);
            this.caravans.add(caravan);
        }
    }

    void start_moving_units4(){
        int number_of_units = (int) this.start_tile.current_capacity;

        if (number_of_units > 0) {
            this.start_tile.current_capacity = 0;

            Moving_unit caravan = new Moving_unit(this.start_tile.x, this.start_tile.y, number_of_units, 4);
            this.caravans.add(caravan);
        }
    }

    void start_moving_units5(){
        int number_of_units = (int) this.start_tile.current_capacity;

        if (number_of_units > 0) {
            this.start_tile.current_capacity = 0;

            Moving_unit caravan = new Moving_unit(this.start_tile.x, this.start_tile.y, number_of_units, 5);
            this.caravans.add(caravan);
        }
    }

    void paint(Graphics g){
        for (Moving_unit caravan : caravans) {
            caravan.paint(g);
        }
    }

    void update(Graphics g, int timeDelta){
        double delta_x = timeDelta * 0.0005 * (this.end_tile.x - this.start_tile.x);
        double delta_y = timeDelta * 0.0005 * (this.end_tile.y - this.start_tile.y);
        ArrayList<Moving_unit> new_caravans = new ArrayList<Moving_unit>();
        for (Moving_unit caravan : caravans){
            caravan.update(delta_x, delta_y);
            int a1 = this.start_tile.x;
            int b1 = this.end_tile.x;
            int a2 = this.start_tile.y;
            int b2 = this.end_tile.y;
            int x_min = Math.min(a1, b1);
            int x_max = Math.max(a1, b1);
            int y_min = Math.min(a2, b2);
            int y_max = Math.max(a2, b2);
            if (!(((x_min <= caravan.x) && (x_max >= caravan.x)) && ((y_min <= caravan.y) && (y_max >= caravan.y)))){
                if (this.end_tile.type == caravan.type) {
                    this.end_tile.current_capacity += caravan.number_of_units;
                    if (this.end_tile.current_capacity > this.end_tile.capacity) {
                        this.end_tile.current_capacity = this.end_tile.capacity;
                    }
                }
                else if (this.end_tile.type == 0) {
                    this.end_tile.type = caravan.type;
                    this.end_tile.current_capacity += caravan.number_of_units;
                    if (this.end_tile.current_capacity > this.end_tile.capacity) {
                        this.end_tile.current_capacity = this.end_tile.capacity;
                    }
                }
                else {
                    int k = (int) (this.end_tile.current_capacity - caravan.number_of_units);
                    if (k >= 0){
                        this.end_tile.current_capacity -= caravan.number_of_units;
                    }
                    else{
                        this.end_tile.type = caravan.type;
                        this.end_tile.current_capacity = -1 * k;
                    }
                    if (this.end_tile.current_capacity > this.end_tile.capacity) {
                        this.end_tile.current_capacity = this.end_tile.capacity;
                    }

                }
                caravan.delete(g);
            }
            else {new_caravans.add(caravan);}
        }
        this.caravans = new_caravans;
    }
}