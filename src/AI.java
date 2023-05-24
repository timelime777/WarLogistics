import java.util.ArrayList;

public class AI {
    int player_num = -1;
    GridGraph graph;

    int time_penalty;

    int timeDelta_passed;

    AI (GridGraph graph, int player_num, int time_penalty){
        this.player_num = player_num;
        this.graph = graph;
        this.time_penalty = time_penalty;
    }

    void update(int timeDelta){
        this.timeDelta_passed += timeDelta;
        if (this.timeDelta_passed >= time_penalty){
            this.timeDelta_passed = 0;
            this.move_units();
        }
    }

    void move_units(){
        ArrayList<Integer> my_tiles = new ArrayList<>();
        for (int i = 0; i < this.graph.tiles.length; i = i + 1){
            if (this.graph.tiles[i].type == this.player_num){
                my_tiles.add(i);
            }
        }

        for (int tile_num: my_tiles){
            int[] tile_roads = this.graph.roads[tile_num];
            int tile_number = 0;
            int state_of_colonization = 0;
            int check_for_colonization_tiles = 0;
            for (int end_tile_state: tile_roads) {
                if (end_tile_state == 1) {
                    if (this.graph.tiles[check_for_colonization_tiles].type == 0) {
                        state_of_colonization = 1;
                    }
                }
                check_for_colonization_tiles += 1;
            }
            for (int end_tile_state: tile_roads) {
                if (end_tile_state == 1) {
                    if (this.graph.tiles[tile_number].type == 0) {
                        if (this.player_num == 2){
                            this.graph.move_units2(tile_num, tile_number);}
                        else if (this.player_num == 3) {
                            this.graph.move_units3(tile_num, tile_number);
                        } else if (this.player_num == 4) {
                            this.graph.move_units4(tile_num, tile_number);
                        } else if (this.player_num == 5) {
                            this.graph.move_units5(tile_num, tile_number);
                        }
                    } else if ((this.graph.tiles[tile_number].type != this.player_num) && (state_of_colonization != 1)) {
                        if (this.graph.tiles[tile_num].current_capacity > this.graph.tiles[tile_number].current_capacity) {
                            if (this.player_num == 2) {
                                this.graph.move_units2(tile_num, tile_number);}
                            else if (this.player_num == 3) {
                                this.graph.move_units3(tile_num, tile_number);
                            } else if (this.player_num == 4) {
                                this.graph.move_units4(tile_num, tile_number);
                            } else if (this.player_num == 5) {
                                this.graph.move_units5(tile_num, tile_number);
                            }
                        }
                    }

                }
                tile_number += 1;
            }
        }
    }
}
