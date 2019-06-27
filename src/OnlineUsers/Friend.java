package OnlineUsers;

import Tools.RoundedCornerBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Friend extends JPanel {
    private JPanel information;
    private JLabel title;
    private JLabel artist;
    private JLabel onORof;
    public Friend(String tit , String artis , String onOro){
        super();
        Font font = new Font("serif" , Font.BOLD , 15);
        information = new JPanel();
        information.setBackground(Color.BLACK);
        information.setLayout(new BoxLayout(information , BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(300 ,100 ));
        information.setBorder(BorderFactory.createLineBorder(Color.gray , 1 , true));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.add(information,BorderLayout.CENTER);
        this.add(Box.createVerticalStrut(5) , BorderLayout.NORTH);
        this.add(Box.createHorizontalStrut(5) , BorderLayout.WEST);
        this.add(Box.createHorizontalStrut(5) , BorderLayout.EAST);
        this.add(Box.createVerticalStrut(5) , BorderLayout.SOUTH);
        title = new JLabel("  "+tit);
        title.setForeground(Color.white);
        title.setBackground(Color.BLACK);
        title.setFont(font);
        artist = new JLabel("  "+artis);
        artist.setForeground(Color.white);
        artist.setBackground(Color.BLACK);
        artist.setFont(font);
        onORof = new JLabel("  "+onOro);
        onORof.setForeground(Color.white);
        onORof.setBackground(Color.BLACK);
        onORof.setFont(font);

        information.add(title);
        information.add(Box.createVerticalStrut(8));
        information.add(artist);
        information.add(Box.createVerticalStrut(8));
        information.add(onORof);

        information.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                information.getParent().setBackground(Color.DARK_GRAY);
                information.setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                information.getParent().setBackground(Color.BLACK);
                information.setBackground(Color.BLACK);
            }
        });


    }

    public void setArtist(String artist) {
        this.artist.setText(artist);
        revalidate();
        repaint();
    }

    public void setOnORof(String onORof) {
        this.onORof.setText(onORof);
        revalidate();
        repaint();
    }

    public void setTitle(String title) {
        this.title.setText(title);
        revalidate();
        repaint();
    }
}