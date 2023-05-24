import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelGenerator{
    int number_of_tiles;

    int base_capacity = 100;
    int base_size = 25;

    int number_of_players;

    int width;
    int height;

    Tile[] tiles;

    int[][] roads;

    LevelGenerator(int number_of_tiles, int number_of_players, int width, int height){
        this.width = width;
        this.height = height;
        this.number_of_tiles = number_of_tiles;
        this.number_of_players = number_of_players + 1;
        this.tiles = new Tile[this.number_of_tiles];
        this.roads = new int[this.number_of_tiles][this.number_of_tiles];
    }

    void change_base_capacity(int capacity){
        this.base_capacity = capacity;
    }

    void change_base_size(int size){
        this.base_size = size;
    }

    void generate_level_tiles(){
        int center_x = (int)(this.width / 2);
        int center_y = (int)(this.height / 2);

        int vector_x = (int)(((this.width / 2) - 200) / 1.4);
        int vector_y = (int)(((this.height / 2) - 120)/ 1.2);


        for (int i = 0; i < this.number_of_tiles; i = i + 1){
            int x_tile = (int)(center_x + vector_x * Math.cos((Math.PI * 2 / this.number_of_tiles) * i));
            int y_tile = (int)(center_y + vector_y * Math.sin((Math.PI * 2 / this.number_of_tiles) * i));
            Tile newTile = new Tile(0, this.base_capacity, x_tile, y_tile, this.base_size);
            tiles[i] = newTile;
        }

        for (int i = 1; i < number_of_players; i = i + 1){
            int tile_num = (int) (this.number_of_tiles * i / this.number_of_players);
            tiles[tile_num].type = 1 + i;
        }
    }

    void generate_arch_spiral_tiles(){
        int center_x = (int)(this.width / 2);
        int center_y = (int)(this.height / 2);

        int vector_x = (int)(((this.width / 2) - 200));
        int vector_y = (int)(((this.height / 2) - 120));

        double sin_a = Math.sin(Math.PI * 2 / this.number_of_tiles);
        double cos_a = Math.cos(Math.PI * 2 / this.number_of_tiles);
        double current_sin_a = Math.sin(Math.PI * 2 / this.number_of_tiles);
        double current_cos_a = Math.cos(Math.PI * 2 / this.number_of_tiles);

        for (int i = 0; i < this.number_of_tiles; i = i + 1){
            Tile newTile = new Tile(0, this.base_capacity, (int)(center_x + (current_cos_a * vector_x)),
                    (int)(center_y + (current_sin_a * vector_y)), this.base_size);
            tiles[i] = newTile;
            current_sin_a = sin_a * current_cos_a + cos_a * current_sin_a;
            current_cos_a = cos_a * current_cos_a - sin_a * current_sin_a;
        }

        for (int i = 1; i < number_of_players; i = i + 1){
            int tile_num = (int) (this.number_of_tiles * i / this.number_of_players);
            tiles[tile_num].type = 1 + i;
        }
    }

    void add_player(){
        this.tiles[0].type = 1;
    }

    void generate_all_roads(){
        for (int i = 0; i < this.number_of_tiles; i = i + 1){
            for (int j = 0; j < this.number_of_tiles; j = j + 1) {
                roads[i][j] = 1;
            }
        }

    }

    void generate_empty_graph(){
        for (int i = 0; i < this.number_of_tiles; i = i + 1){
            for (int j = 0; j < this.number_of_tiles; j = j + 1) {
                roads[i][j] = 0;
            }
        }
    }
    void generate_random_tree_roads(){
        ArrayList<Integer> queued_vertices = new ArrayList<>();

        queued_vertices.add(-1);

        for (int i = 1; i < this.number_of_tiles; i = i + 1){
            queued_vertices.add(i);
        }

        ArrayList<Integer> generated_vertices = new ArrayList<>();
        generated_vertices.add(0);

        for (int i = 1; i < this.number_of_tiles; i = i + 1){
            int queue_length = 0;
            for (int j = 0; j < this.number_of_tiles; j = j + 1){
                if (queued_vertices.get(j) != -1){
                    queue_length += 1;
                }

            }
            Integer[] current_queue = new Integer[queue_length];
            int current_index = 0;
            for (int j = 0; j < this.number_of_tiles; j = j + 1){
                if (queued_vertices.get(j) != -1){
                    current_queue[current_index] = queued_vertices.get(j);
                    current_index += 1;
                }
            }
            List<Integer> intList = Arrays.asList(current_queue);
            Collections.shuffle(intList);
            intList.toArray(current_queue);

            int new_tile = current_queue[0];
            queued_vertices.set(new_tile, -1);

            Integer[] generated_tiles = new Integer[generated_vertices.size()];
            for (int j = 0; j < generated_vertices.size(); j = j + 1){
                generated_tiles[j] = generated_vertices.get(j);
            }
            List<Integer> intList1 = Arrays.asList(generated_tiles);
            Collections.shuffle(intList1);
            intList1.toArray(generated_tiles);

            int previous_tile = generated_tiles[0];

            roads[new_tile][previous_tile] = 1;
            roads[previous_tile][new_tile] = 1;

            generated_vertices.add(new_tile);
        }
    }
    void generate_random_graph(){
        int k = 1 + (int)Math.sqrt((int)(this.number_of_tiles / 4));
        for (int i = 0; i < k; i = i + 1){
            this.generate_random_tree_roads();
        }
    }
}
