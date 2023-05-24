import javax.swing.JPanel;
import java.awt.*;

public class MyPanel extends JPanel {
    double Diff_mul = 1.2;
    int time = (int) System.currentTimeMillis();
    int start_state = 0;
    int edit_state = 0;
    int w;
    int h;
    int last_click_x = -1;
    int last_click_y = -1;
    int num_of_tiles = 6;
    int num_of_players = 1;
    int generator_state = 0;
    int spectator_state = 0;
    int difficulty = 0;
    int win_state = 0;
    int lose_state = 0;
    int arrow_start_tile = -1;
    int arrow_end_tile = -1;
    int base_time_penalty = 5003;

    int base_capacity = 40;

    int size = 40;
    int is_there_arrow_out_there = 0;

    int speed = 10; /// (от 1 до 20)

    double speed_modifier = 0.09;

    AI first_enemy;
    AI second_enemy;
    AI third_enemy;
    AI four_enemy;

    Button Start_Button;
    Button Edit_Button;
    Button Home_Button;
    Button WinScreen;
    Button LoseScreen;
    Button AddDiffBtn;
    Button RemoveDiffBtn;
    Button SpecBtn;
    Button AddTileBtn;
    Button RemoveTileBtn;
    Button AddPlayerBtn;
    Button RemovePlayerBtn;
    Button AddCapBtn;
    Button RemoveCapBtn;
    Button AddSizeBtn;
    Button RemoveSizeBtn;

    Button AddSpeedBtn;
    Button RemoveSpeedBtn;

    Button Tree;

    Button Sticker;

    Button Random;

    Button All;

    Banner Settings;

    Banner GenBanner;
    Banner SpeedBanner;
    Banner CapBanner;
    Banner PlayerBanner;
    Banner SizeBanner;
    Banner TileBanner;
    Banner DiffBanner;
    Banner SpecBanner;

    String spec_mod = "Откл.";
    MouseArrow current_arrow = new MouseArrow(0, 0 , 0, 0);;
    GridGraph grid;


    MyPanel(int w, int h){
        this.delete_grid();
        this.w = w;
        this.h = h;
        this.Start_Button = new Button(w / 2, h / 2 - 300, 600, 250, "Начать игру");
        this.Edit_Button = new Button(w / 2, h / 2 + 100, 600, 250, "Настройки");
        this.Home_Button = new Button(50, 50, 75, 50, " Назад");
        this.WinScreen = new Button(w / 2, h / 2, 600, 250, "ВЫ ПОБЕДИЛИ!");
        this.WinScreen.change_color(Color.green);
        this.LoseScreen = new Button(w / 2, h / 2, 600, 250, "ВЫ ПРОИГРАЛИ!");
        this.LoseScreen.change_color(Color.red);

        this.AddDiffBtn = new Button(w / 2 + - 100, h / 2 - 350, 50, 50, " Доб.");
        this.RemoveDiffBtn = new Button(w / 2, h / 2 - 350, 50, 50, " Ум.");
        this.DiffBanner = new Banner(w / 2 - 300, h / 2 - 350, "Сложность:" + Integer.toString(this.difficulty));

        this.AddTileBtn = new Button(w / 2 + - 100, h / 2 - 150, 50, 50, " Доб.");
        this.RemoveTileBtn = new Button(w / 2, h / 2 - 150, 50, 50, " Ум.");
        this.TileBanner = new Banner(w / 2 - 300, h / 2 - 150, "Кол-во городов: " + Integer.toString(this.num_of_tiles));

        this.AddPlayerBtn = new Button(w / 2 + - 100, h / 2 - 50, 50, 50, " Доб.");
        this.RemovePlayerBtn = new Button(w / 2, h / 2 - 50, 50, 50, " Ум.");
        this.PlayerBanner = new Banner(w / 2 - 300, h / 2 - 50, "Кол-во Ботов: " + Integer.toString(this.num_of_players));

        this.AddCapBtn = new Button(w / 2 + - 100, h / 2 + 50, 50, 50, " Доб.");
        this.RemoveCapBtn = new Button(w / 2, h / 2 + 50, 50, 50, " Ум.");
        this.CapBanner = new Banner(w / 2 - 300, h / 2 + 50, "Вместимость городов: " + Integer.toString(this.base_capacity));

        this.AddSizeBtn = new Button(w / 2 + - 100, h / 2 + 150, 50, 50, " Доб.");
        this.RemoveSizeBtn = new Button(w / 2, h / 2 + 150, 50, 50, " Ум.");
        this.SizeBanner = new Banner(w / 2 - 300, h / 2 + 150, "Размер городов: " + Integer.toString(this.size));

        this.AddSpeedBtn = new Button(w / 2 + - 100, h / 2 + 250, 50, 50, " Доб.");
        this.RemoveSpeedBtn = new Button(w / 2, h / 2 + 250, 50, 50, " Ум.");
        this.SpeedBanner = new Banner(w / 2 - 300, h / 2 + 250, "Скорость игры: " + Integer.toString(this.speed));

        this.GenBanner = new Banner(w / 2 - 300, h / 2 + 350, "Тип графа: ");
        this.Tree = new Button(w / 2 + - 200, h / 2 + 350, 50, 50, "Дерево");
        this.Random = new Button(w / 2 + - 100, h / 2 + 350, 50, 50, "Случ.");
        this.All = new Button(w / 2, h / 2 + 350, 50, 50, "Полный");

        this.Sticker = new Button(w / 2 - 100, h / 2 + 450, 50, 50, "");
        this.Sticker.change_color(Color.green);

        this.SpecBtn = new Button(w / 2 + - 100, h / 2 - 250, 50, 50, this.spec_mod);
        this.SpecBanner = new Banner(w / 2 - 300, h / 2 - 250, "Режим наблюдателя:");

        this.Settings = new Banner(w / 2 - 300, h / 2 - 450, "Настройки игры:" +
                " (Игра нестабильна при маленьких городах или их большом количестве или большой вместимости!)");
    }

    @Override
    public void paintComponent(Graphics g){
        int currentTime = (int) System.currentTimeMillis();
        if (this.win_state == 1 && this.spectator_state != 1) {
            this.WinScreen.paint(g);
            this.WinScreen.onMouseHit(this.last_click_x, this.last_click_y);
            if (this.WinScreen.hit_state == 1) {
                this.WinScreen.hit_state = 0;
                this.start_state = 0;
                this.edit_state = 0;
                this.lose_state = 0;
                this.win_state = 0;
            }
        } else if (this.lose_state == 1 && this.spectator_state != 1) {
            this.LoseScreen.paint(g);
            this.LoseScreen.onMouseHit(this.last_click_x, this.last_click_y);
            if (this.LoseScreen.hit_state == 1) {
                this.LoseScreen.hit_state = 0;
                this.start_state = 0;
                this.edit_state = 0;
                this.lose_state = 0;
                this.win_state = 0;
            }
        }
        else {
            if (this.start_state != 0 && this.edit_state == 0) {
                this.Home_Button.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.Home_Button.hit_state == 1) {
                    this.Home_Button.hit_state = 0;
                    this.start_state = 0;
                    this.edit_state = 0;
                    this.delete_grid();
                }
                this.grid.update(g, (int)((currentTime - this.time) / (1 + (10 - this.speed) * this.speed_modifier)));
                this.first_enemy.update((int)((currentTime - this.time) / (1 + (10 - this.speed) * this.speed_modifier)));
                this.second_enemy.update((int)((currentTime - this.time) / (1 + (10 - this.speed) * this.speed_modifier)));
                this.third_enemy.update((int)((currentTime - this.time) / (1 + (10 - this.speed) * this.speed_modifier)));
                this.four_enemy.update((int)((currentTime - this.time) / (1 + (10 - this.speed) * this.speed_modifier)));
                this.grid.paint(g);
                this.current_arrow.paint(g);
                this.Home_Button.paint(g);
                int win = 1;
                int lose = 1;
                for (Tile tile : this.grid.tiles) {
                    if (tile.type != 1) {
                        win = 0;
                    }
                    if (tile.type == 1) {
                        lose = 0;
                    }
                }
                if (win == 1) {
                    this.win_state = 1;
                } else if (lose == 1) {
                    this.lose_state = 1;
                }
            } else if (this.edit_state == 1) {
                this.Home_Button.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.Home_Button.hit_state == 1) {
                    this.Home_Button.hit_state = 0;
                    this.start_state = 0;
                    this.edit_state = 0;
                    this.delete_grid();
                }
                this.AddDiffBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.AddDiffBtn.hit_state == 1) {
                    this.AddDiffBtn.hit_state = 0;
                    if (this.difficulty <= 9){
                        this.difficulty += 1;}
                }
                this.RemoveDiffBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.RemoveDiffBtn.hit_state == 1) {
                    this.RemoveDiffBtn.hit_state = 0;
                    if (this.difficulty >= -9){
                        this.difficulty -= 1;}
                }
                this.AddTileBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.AddTileBtn.hit_state == 1) {
                    this.AddTileBtn.hit_state = 0;
                    if (this.num_of_tiles <= 49){
                        this.num_of_tiles += 1;}
                }
                this.RemoveTileBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.RemoveTileBtn.hit_state == 1) {
                    this.RemoveTileBtn.hit_state = 0;
                    if (this.num_of_tiles >= this.num_of_players + 2){
                        this.num_of_tiles -= 1;}
                }
                this.AddPlayerBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.AddPlayerBtn.hit_state == 1) {
                    this.AddPlayerBtn.hit_state = 0;
                    if (this.num_of_players + 1 <= Math.min(4, this.num_of_tiles - 1)){
                        this.num_of_players += 1;}
                }
                this.RemovePlayerBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.RemovePlayerBtn.hit_state == 1) {
                    this.RemovePlayerBtn.hit_state = 0;
                    if (this.num_of_players - 1 >= 0){
                        this.num_of_players -= 1;}
                }

                this.AddCapBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.AddCapBtn.hit_state == 1) {
                    this.AddCapBtn.hit_state = 0;
                    if (this.base_capacity + 1 <= 100){
                        this.base_capacity += 1;}
                }
                this.RemoveCapBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.RemoveCapBtn.hit_state == 1) {
                    this.RemoveCapBtn.hit_state = 0;
                    if (this.base_capacity >= 3){
                        this.base_capacity -= 1;}
                }

                this.AddSizeBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.AddSizeBtn.hit_state == 1) {
                    this.AddSizeBtn.hit_state = 0;
                    if (this.size + 1 <= 60){
                        this.size += 1;}
                }
                this.RemoveSizeBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.RemoveSizeBtn.hit_state == 1) {
                    this.RemoveSizeBtn.hit_state = 0;
                    if (this.size >= 16){
                        this.size -= 1;}
                }

                this.AddSpeedBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.AddSpeedBtn.hit_state == 1) {
                    this.AddSpeedBtn.hit_state = 0;
                    if (this.speed + 1 <= 20){
                        this.speed += 1;}
                }
                this.RemoveSpeedBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.RemoveSpeedBtn.hit_state == 1) {
                    this.RemoveSpeedBtn.hit_state = 0;
                    if (this.speed >= 2){
                        this.speed -= 1;}
                }

                this.SpecBtn.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.SpecBtn.hit_state == 1) {
                    this.SpecBtn.hit_state = 0;
                    this.spectator_state = (this.spectator_state + 1) % 2;
                    if (this.spectator_state == 1){
                        this.spec_mod = "Вкл.";
                    }
                    else{
                        this.spec_mod = "Откл";
                    }
                    this.SpecBtn = new Button(w / 2 + - 100, h / 2 - 250, 50, 50, this.spec_mod);
                }

                this.Tree.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.Tree.hit_state == 1) {
                    this.Tree.hit_state = 0;
                    this.generator_state = -1;
                    this.Sticker = new Button(w / 2 - 200, h / 2 + 450, 50, 50, "");
                    this.Sticker.change_color(Color.green);
                }

                this.Random.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.Random.hit_state == 1) {
                    this.Random.hit_state = 0;
                    this.generator_state = 0;
                    this.Sticker = new Button(w / 2 - 100, h / 2 + 450, 50, 50, "");
                    this.Sticker.change_color(Color.green);
                }

                this.All.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.All.hit_state == 1) {
                    this.All.hit_state = 0;
                    this.generator_state = 1;
                    this.Sticker = new Button(w / 2, h / 2 + 450, 50, 50, "");
                    this.Sticker.change_color(Color.green);
                }

                this.DiffBanner = new Banner(w / 2 - 300, h / 2 - 350, "Сложность:" + Integer.toString(this.difficulty));
                this.TileBanner = new Banner(w / 2 - 300, h / 2 - 150, "Кол-во городов: " + Integer.toString(this.num_of_tiles));
                this.PlayerBanner = new Banner(w / 2 - 300, h / 2 - 50, "Кол-во Ботов: " + Integer.toString(this.num_of_players));
                this.CapBanner = new Banner(w / 2 - 300, h / 2 + 50, "Вместимость городов: " + Integer.toString(this.base_capacity));
                this.SizeBanner = new Banner(w / 2 - 300, h / 2 + 150, "Размер городов: " + Integer.toString(this.size));
                this.SpeedBanner = new Banner(w / 2 - 300, h / 2 + 250, "Скорость игры: " + Integer.toString(this.speed));

                this.AddTileBtn.paint(g);
                this.RemoveTileBtn.paint(g);
                this.TileBanner.paint(g);

                this.Home_Button.paint(g);

                this.DiffBanner.paint(g);
                this.AddDiffBtn.paint(g);
                this.RemoveDiffBtn.paint(g);

                this.SpecBanner.paint(g);
                this.SpecBtn.paint(g);

                this.AddPlayerBtn.paint(g);
                this.RemovePlayerBtn.paint(g);
                this.PlayerBanner.paint(g);

                this.CapBanner.paint(g);
                this.AddCapBtn.paint(g);
                this.RemoveCapBtn.paint(g);

                this.AddSizeBtn.paint(g);
                this.RemoveSizeBtn.paint(g);
                this.SizeBanner.paint(g);

                this.AddSpeedBtn.paint(g);
                this.RemoveSpeedBtn.paint(g);
                this.SpeedBanner.paint(g);

                this.GenBanner.paint(g);
                this.All.paint(g);
                this.Random.paint(g);
                this.Tree.paint(g);
                this.Sticker.paint(g);
                this.Settings.paint(g);

            } else {
                this.Start_Button.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.Start_Button.hit_state == 1) {
                    this.Start_Button.hit_state = 0;
                    this.start_state = 1;
                    this.create_grid();
                }
                this.Edit_Button.onMouseHit(this.last_click_x, this.last_click_y);
                if (this.Edit_Button.hit_state == 1) {
                    this.Edit_Button.hit_state = 0;
                    this.edit_state = 1;
                }
                this.Start_Button.paint(g);
                this.Edit_Button.paint(g);
            }
        }
        this.time = currentTime;
        this.last_click_x = -1;
        this.last_click_y = -1;
    }

    void set_to_base(){
        this.start_state = 0;
        this.edit_state = 0;
        this.last_click_x = -1;
        this.last_click_y = -1;
        this.num_of_tiles = 6;
        this.num_of_players = 1;
        this.generator_state = 0;
        this.spectator_state = 0;
        this.difficulty = 0;
        this.win_state = 0;
        this.lose_state = 0;
        this.arrow_start_tile = -1;
        this.arrow_end_tile = -1;
        this.base_time_penalty = 5003;
        this.base_capacity = 40;
        this.size = 40;
        this.is_there_arrow_out_there = 0;
        this.speed = 10;
    }

    void setWidth(int w){
        this.w = w;
    }

    void setHeight(int h){
        this.h = h;
    }

    void create_grid(){
        LevelGenerator generator = new LevelGenerator(this.num_of_tiles, this.num_of_players, w, h);
        generator.change_base_size(this.size);
        generator.change_base_capacity(this.base_capacity);
        generator.generate_level_tiles();
        if (this.generator_state == 0){
            generator.generate_random_graph();
        } else if (this.generator_state == -1){
            generator.generate_random_tree_roads();
        } else if (this.generator_state == 1){
            generator.generate_all_roads();
        }

        if (this.spectator_state == 0){
            generator.add_player();}

        int[][] roads = generator.roads;
        Tile[] tiles = generator.tiles;
        this.grid = new GridGraph(tiles, roads);

        this.first_enemy = new AI(this.grid, 2, (int)(this.base_time_penalty / (Math.pow(this.Diff_mul, this.difficulty))));
        this.second_enemy = new AI(this.grid, 3, (int)(this.base_time_penalty / (Math.pow(this.Diff_mul, this.difficulty))) + 71);
        this.third_enemy = new AI(this.grid, 4, (int)(this.base_time_penalty / (Math.pow(this.Diff_mul, this.difficulty))) + 141);
        this.four_enemy = new AI(this.grid, 5, (int)(this.base_time_penalty / (Math.pow(this.Diff_mul, this.difficulty))) + 211);
    }

    void delete_grid(){
        Tile tile1 = new Tile(1,0, 0, 0, 0);
        Tile tile2 = new Tile(2,0, 0, 0, 0);
        Tile[] arr = {tile1, tile2};
        int[][] arr2 = {{0, 0}, {0, 0}};
        this.grid = new GridGraph(arr, arr2);
    }

    void create_arrow(int x, int y){
        int state = 0;
        int current_tile = 0;
        int tile_num = -1;
        for (Tile tile: grid.tiles){
            int current_tile_x0 = tile.x - (int) (tile.size * (1 + tile.current_capacity * 2 / tile.capacity));
            int current_tile_x1 = current_tile_x0 + (int) (2 * tile.size * (1 + tile.current_capacity * 2/ tile.capacity));
            int current_tile_y0 = tile.y - (int) (tile.size * (1 + tile.current_capacity * 2 / tile.capacity));
            int current_tile_y1 = current_tile_y0 + (int) (2 * tile.size * (1 + tile.current_capacity * 2 / tile.capacity));
            if (((x >= current_tile_x0) && (x <= current_tile_x1)) && ((y >= current_tile_y0) && (y <= current_tile_y1))){
                tile_num = current_tile;
                state = 1;
            }
            current_tile += 1;
        }
        if (state == 1){
            if (this.grid.tiles[tile_num].type == 1){
                int x1 = this.grid.tiles[tile_num].x;
                int y1 = this.grid.tiles[tile_num].y;

                this.current_arrow = new MouseArrow(x1, y1, x1, y1);
                this.is_there_arrow_out_there = 1;
                this.arrow_start_tile = tile_num;}}
    }

    void delete_arrow(int x, int y){
        int state = 0;
        int current_tile = 0;
        int tile_num = -1;
        for (Tile tile: grid.tiles){
            int current_tile_x0 = tile.x - (int) (tile.size * (1 + tile.current_capacity * 2 / tile.capacity));
            int current_tile_x1 = current_tile_x0 + (int) (2 * tile.size * (1 + tile.current_capacity * 2/ tile.capacity));
            int current_tile_y0 = tile.y - (int) (tile.size * (1 + tile.current_capacity * 2 / tile.capacity));
            int current_tile_y1 = current_tile_y0 + (int) (2 * tile.size * (1 + tile.current_capacity * 2 / tile.capacity));
            if (((x >= current_tile_x0) && (x <= current_tile_x1)) && ((y >= current_tile_y0) && (y <= current_tile_y1))){
                tile_num = current_tile;
                state = 1;
            }
            current_tile += 1;
        }
        if (state == 0){
            this.current_arrow = new MouseArrow(0, 0 , 0, 0);
            this.is_there_arrow_out_there = 0;}
        else{
            this.arrow_end_tile = tile_num;
            if (this.grid.tiles[this.arrow_start_tile].type == 1){
                this.grid.move_units1(this.arrow_start_tile, this.arrow_end_tile);}
            this.current_arrow = new MouseArrow(0, 0 , 0, 0);
            this.is_there_arrow_out_there = 0;
            this.arrow_start_tile = -1;
            this.arrow_end_tile = -1;
        }
    }

    void change_arrow(int x, int y){
        if (this.is_there_arrow_out_there == 1){
            int c_x = this.current_arrow.start_point_x;
            int c_y = this.current_arrow.start_point_y;
            this.current_arrow = new MouseArrow(c_x, c_y, x, y);}
    }
}