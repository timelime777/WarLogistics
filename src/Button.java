import java.awt.*;

public class Button {
    int x;
    int y;
    int width;
    int height;

    int double_font = 0;

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

    void double_font(){
        this.double_font += 1;
        this.double_font = double_font % 2;
    }

    void change_color(Color c){this.color = c;}

    void paint(Graphics g){
        if (this.double_font == 0){
            g.setColor(this.color);
            g.fillRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.width / 10, this.height / 5);
            g.setColor(Color.black);
            g.drawRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.width / 10, this.height / 5);
            g.drawString(this.string, this.x - 22, this.y);}
        else{
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
            g.setFont(newFont);
            g.setColor(this.color);
            g.fillRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.width / 10, this.height / 5);
            g.setColor(Color.black);
            g.drawRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.width / 10, this.height / 5);
            g.drawString(this.string, this.x - 56, this.y);
            g.setFont(currentFont);
        }
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