import java.awt.*;

public class Button {
    int x;
    int y;
    int width;
    int height;

    String string;

    Color color = Color.white;

    int hit_state = 0;

    Button(int x, int y, int width, int height, String s){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.string = s;
    }

    void change_color(Color c){this.color = c;}

    void paint(Graphics g){
        g.setColor(this.color);
        g.fillRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.width / 10, this.height / 5);
        g.setColor(Color.black);
        g.drawRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.width / 10, this.height / 5);
        g.drawString(this.string, this.x - 22, this.y);

    }

    void onMouseHit(int mouseX, int mouseY){
        if ((mouseX <= this.x + this.width / 2)&&(mouseX >= this.x - this.width / 2)&&
                (mouseY <= this.y + this.height / 2)&&(mouseY >= this.y - this.height / 2)){
            this.hit_state = 1;
        }
    }

    int button_was_hit(int state){
        if (state == 1){
            return 1;
        }

        else{
            return 0;
        }
    }
}