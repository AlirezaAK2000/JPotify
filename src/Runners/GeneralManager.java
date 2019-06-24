package Runners;

import Boxoffice.Boxoffice;
import Center.Center;
import Musicbox.MusicBox;
import OnlineUsers.OnlineUsers;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class GeneralManager extends JFrame {
    private Boxoffice boxoffice;
    private OnlineUsers onlineUsers;
    private Center center;
    private MusicBox musicBox;
    private Image img;
    public GeneralManager() throws IOException, InterruptedException {
        super();
        onlineUsers = new OnlineUsers();
        musicBox = new MusicBox();
        center = new Center(musicBox);
        boxoffice = new Boxoffice(center);
        JScrollPane j=new JScrollPane(boxoffice);
        j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane j1 = new JScrollPane(onlineUsers);
        j1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
        this.add(center , BorderLayout.CENTER);
        this.add(j1 , BorderLayout.EAST);
        this.add(j , BorderLayout.WEST);
        this.add(musicBox , BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension min = new Dimension(500, 500);
        this.setMinimumSize(min);
        this.setSize(800 , 800);
        this.setVisible(true);
    }

    public Boxoffice getBoxoffice() {
        return boxoffice;
    }

    public MusicBox getMusicBox() {
        return musicBox;
    }

    public OnlineUsers getOnlineUsers() {
        return onlineUsers;
    }
}