import java.awt.*;
import java.applet.Applet;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Clock extends Applet implements ActionListener{

    TextField tunnid = new TextField("00");
    TextField minutid = new TextField("00");
    Button button = new Button("Set Alarm!");

    int aminut = 0;
    int atund = 0;
    boolean aratus = false;
    Font me = new Font ("Courier New", Font.BOLD, 40);
    Clock(){
        this.add(tunnid);
        this.add(minutid);
        this.add(button);
        button.addActionListener(this);
        Timer t = new Timer(1000, this);
        t.start();
    }

    public void paint(Graphics g){

        Dimension as = this.getSize();

        g.setColor(Color.white);

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));

        int ky = as.height/2;
        int kx = as.width/2;

        int raadius = 3*Math.min(kx, ky)/4;

        g.drawOval(kx-raadius, ky-raadius, 2*raadius, 2*raadius);
        //kriipsud kellal
        for(int i=0; i<60; i++){
            double nurk = Math.PI*i/30;
            int x1 = kx + (int)(raadius * Math.cos(nurk) * 9 / 10 * (i%5==0?0.9:1));
            int y1 = ky + (int)(raadius * Math.sin(nurk) * 9 / 10 * (i%5==0?0.9:1));
            int x2 = kx + (int)(raadius * Math.cos(nurk));
            int y2 = ky + (int)(raadius * Math.sin(nurk));

            g.drawLine(x1, y1, x2, y2);

        }
        //Seierid
        Calendar praegu = Calendar.getInstance();

        int tund = praegu.get(Calendar.HOUR);
        int minut = praegu.get(Calendar.MINUTE);
        int sekund = praegu.get(Calendar.SECOND);
        //Tund
        double nurk = Math.PI*tund/6 - Math.PI/2 + Math.PI*minut/360;
        int tx = kx + (int)(raadius * Math.cos(nurk)/2);
        int ty = ky + (int)(raadius * Math.sin(nurk)/2);
        g.drawLine(kx, ky, tx, ty);
        //Minut
        nurk = Math.PI*minut/30 - Math.PI/2 + Math.PI*sekund/1800;
        int mx = kx + (int)(raadius * Math.cos(nurk) * 2 / 3);
        int my = ky + (int)(raadius * Math.sin(nurk) * 2 / 3);
        g.drawLine(kx, ky, mx, my);
        //Sekund
        nurk = Math.PI*sekund/30 - Math.PI/2;
        int sx = kx + (int)(raadius * Math.cos(nurk) * 3 / 4);
        int sy = ky + (int)(raadius * Math.sin(nurk) * 3 / 4);
        g.drawLine(kx, ky, sx, sy);
        //Äratus
        if(aratus==true){

            nurk = Math.PI*atund/6 - Math.PI/2 + Math.PI*aminut/360;
            int ax = kx + (int)(raadius * Math.cos(nurk) * 2 / 3);
            int ay = ky + (int)(raadius * Math.sin(nurk) * 2 / 3);
            g.setColor(new Color(255, 0, 0));
            g.drawLine(kx,ky,ax,ay);

            if(tund==atund && minut == aminut){
                g.setColor(new Color(255, 253, 26));
                g.fillRect(0, 0, (int)as.width, (int)as.height);
                g.setColor(Color.black);
                g.setFont(me);
                g.drawString("Please, WAKE UP!", kx-30, ky);
            }
        }
    }

    public void actionPerformed(ActionEvent evt){
        try{

            int tund = Integer.parseInt(tunnid.getText());
            int minut = Integer.parseInt(minutid.getText());
            atund = tund;
            aminut = minut;

            if(evt.getActionCommand()==button.getLabel()){
                aratus=true;
            }

            repaint();

        }
        catch(Exception e){

        }

        repaint();

    }

    public static void main(String[] args){

        Frame f = new Frame("Kristian Andreas Jagor - IVSB11 - Project Clock");

        f.add(new Clock());

        f.setSize(500, 300);
        f.setLocation(100, 100);
        f.setBackground(new Color(64, 245, 118));

        f.setVisible(true);

    }
}
