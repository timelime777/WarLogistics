import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyFrame extends JFrame implements MouseListener, MouseMotionListener {

    GridGraph grid;
    MyPanel panel;
    MyFrame(){

        this.setVisible(true);
        this.setTitle("WarLogistics");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        int w = this.getWidth();
        int h = this.getHeight();

        this.panel = new MyPanel(w, h);
        this.panel.set_to_base();
        this.add(this.panel);

        this.panel.addMouseListener(this);
        this.panel.addMouseMotionListener(this);
    }

    void update(){
        this.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (this.panel.is_there_arrow_out_there == 0){
            this.panel.create_arrow(x, y);
        }
        else {
            this.panel.delete_arrow(x, y);
        }
        this.panel.last_click_x = x;
        this.panel.last_click_y = y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.panel.is_there_arrow_out_there == 1) {
            int x = e.getX();
            int y = e.getY();
            this.panel.change_arrow(x , y);
        }
    }
}