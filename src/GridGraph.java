import java.awt.*;
import java.util.ArrayList;

public class GridGraph {
    Tile[] tiles;
    int[][] roads;

    Road[][] new_real_roads;

    ArrayList<Road> real_roads = new ArrayList<>();

    GridGraph(Tile[] tiles, int[][] roads){
        this.tiles = tiles;
        this.roads = roads;
        this.new_real_roads = new Road[this.tiles.length][this.tiles.length];
        for (int i = 0; i < tiles.length; i = i + 1){
            for(int j = 0; j < tiles.length; j = j + 1){
                Road road = new Road(this.tiles[i], this.tiles[j]);
                if (roads[i][j] == 1){
                    real_roads.add(road);}
                this.new_real_roads[i][j] = road;
            }
        }
    }

    void paint(Graphics g){
        for (int i = 0; i < tiles.length; i = i + 1){
            for(int j = 0; j < tiles.length; j = j + 1){
                if (roads[i][j] == 1){
                    g.drawLine(tiles[i].x, tiles[i].y, tiles[j].x, tiles[j].y);}
            }
        }
        for (Tile tile : tiles) {
            tile.paint(g);
        }
        for (Road road: real_roads){
            road.paint(g);
        }
    }

    void update(Graphics g, int timeDelta){
        for (Tile tile : tiles){
            tile.update(timeDelta);
        }
        for (Road road : real_roads){
            road.update(g, timeDelta);
        }
    }

    void move_units1(int start_tile, int end_tile){
        if (this.roads[start_tile][end_tile] == 1){
            this.new_real_roads[start_tile][end_tile].start_moving_units1();
        }
    }

    void move_units2(int start_tile, int end_tile){
        if (this.roads[start_tile][end_tile] == 1){
            this.new_real_roads[start_tile][end_tile].start_moving_units2();
        }
    }

    void move_units3(int start_tile, int end_tile){
        if (this.roads[start_tile][end_tile] == 1){
            this.new_real_roads[start_tile][end_tile].start_moving_units3();
        }
    }

    void move_units4(int start_tile, int end_tile){
        if (this.roads[start_tile][end_tile] == 1){
            this.new_real_roads[start_tile][end_tile].start_moving_units4();
        }
    }

    void move_units5(int start_tile, int end_tile){
        if (this.roads[start_tile][end_tile] == 1){
            this.new_real_roads[start_tile][end_tile].start_moving_units5();
        }
    }

}