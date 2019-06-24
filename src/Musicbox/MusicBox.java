package Musicbox;

import Center.Center;
import Logic.Song;
import Music.SongPanel;
import Music.SongPanels;
import Tools.Integer;
import Tools.ProButton;
import Tools.ProSlider;
import Tools.SliderDemoSkin;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MusicBox extends JPanel  {
    private ProButton playb;
    private ImageIcon playI;
    private ImageIcon stopI;
    private ProButton nextb;
    private ProButton backb;
    private ProButton unrepeat;
    private ImageIcon unrepeatI;
    private ProButton shuffle;
    private ImageIcon repeat;
    private SliderDemoSkin volumeSet;
    private SliderDemoSkin songSetter;
    private ProButton muter;
    private ProSlider volume;
    private ImageIcon mute;
    private ImageIcon volumeOn;
    private JPanel center;
    private JPanel auxPanel;
    private JPanel songInformation;
    private JLabel songName;
    private JLabel artist;
    private ImageIcon nextbIc;
    private ImageIcon backbIc;
    private ImageIcon shuffleIc;
    private ImageIcon unShuffle;
    private SongPanels songPanels;
    private SongPanel songPanel;
    private Thread playThread;
    private Thread moveSliderThread;
    private boolean move;
    public MusicBox() throws IOException {
        super();
        playThread=new Thread();
        move=false;
        center = new JPanel();
        auxPanel = new JPanel();
        center.setLayout(new FlowLayout(1,50,20));
        center.setBackground(Color.WHITE);
        stopI =new ImageIcon();
        stopI.setImage(ImageIO.read(new File("Icons\\stop.png")));
        nextbIc = new ImageIcon();
        nextbIc.setImage(ImageIO.read(new File("Icons\\nextt.png")));
        nextb = new ProButton (nextbIc);
        backbIc= new ImageIcon();
        backbIc.setImage(ImageIO.read(new File("Icons\\back.png")));
        backb = new  ProButton (backbIc);
        playI = new ImageIcon();
        playI.setImage(ImageIO.read(new File("Icons\\play.png")));
        playb = new ProButton (playI);
        unrepeatI =  new ImageIcon();
        unrepeatI.setImage(ImageIO.read(new File("Icons\\unrepeat.png")));
        unrepeat = new ProButton (unrepeatI);
        shuffleIc = new ImageIcon();
        shuffleIc.setImage(ImageIO.read(new File("Icons\\shuffle.png")));
        unShuffle = new ImageIcon();
        unShuffle.setImage(ImageIO.read(new File("Icons\\unshuffle.png")));
        shuffle = new ProButton(shuffleIc);
        shuffle.addActionListener(new Mousehandler());
        repeat =  new ImageIcon();
        repeat.setImage(ImageIO.read(new File("Icons\\repeat.png")));
        volumeOn = new ImageIcon();
        volumeOn.setImage(ImageIO.read(new File("Icons\\sound.png")));
        mute = new ImageIcon();
        mute.setImage(ImageIO.read(new File("Icons\\mute.png")));
        muter = new ProButton(volumeOn);
        muter.addActionListener(new Mousehandler());


        playb.setPreferredSize(new Dimension(28,28));
        nextb.setPreferredSize(new Dimension(28,28));
        backb.setPreferredSize(new Dimension(28,28));
        shuffle.setPreferredSize(new Dimension(28,28));
        unrepeat.setPreferredSize(new Dimension(28,28));
        muter.setPreferredSize(new Dimension(28,28));

        songInformation = new JPanel();
        songInformation.setLayout(new BoxLayout(songInformation, BoxLayout.Y_AXIS));
        songName = new JLabel();

        volumeSet = new SliderDemoSkin();
        volumeSet.remove(volumeSet.getSlider());
        volumeSet.setLayout(new FlowLayout());
        volumeSet.add(muter , FlowLayout.LEFT);
        volumeSet.add(volumeSet.getSlider() , FlowLayout.CENTER);

        volumeSet.getSlider().setPreferredSize(new Dimension(100 , 15));
        volumeSet.getSlider().setValue(50);


        songSetter = new SliderDemoSkin();
        songSetter.getSlider().setPreferredSize(new Dimension(600 , 15));
        songSetter.setPreferredSize(new Dimension(600 , 10));
        auxPanel.add(songSetter.getSlider());
        auxPanel.setBackground(new Color(245,245,245));
        songSetter.getSlider().setValue(0);
        this.setLayout(new BorderLayout());
        center.add(shuffle, FlowLayout.LEFT );
        center.add(unrepeat, FlowLayout.CENTER);
        center.add( nextb , FlowLayout.LEFT,1);
        center.add( playb, FlowLayout.CENTER, 1);
        center.add(backb , FlowLayout.CENTER , 1);
        this.add(new JLabel(""),BorderLayout.WEST);
        this.add(center ,BorderLayout.CENTER);
        this.add(volumeSet, BorderLayout.EAST);
        this.add(auxPanel , BorderLayout.NORTH);



        playb.addActionListener(new Mousehandler());
        unrepeat.addActionListener(new Mousehandler());
        moveSliderThread=new Thread(new MoveSlider(getSlider().getPosition(),getSlider()));
        moveSliderThread.start();


    }
    private class Mousehandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==unrepeat){
                if (unrepeat.getIcon().equals(unrepeatI))
                    unrepeat.setIcon(repeat);
                else
                    unrepeat.setIcon(unrepeatI);
            }
            if (e.getSource() == playb){
                if(playb.getIcon().equals(stopI))
                    playb.setIcon(playI);
                else
                    playb.setIcon(stopI);
            }
            if (e.getSource()==muter){
                if(muter.getIcon().equals(mute))
                    muter.setIcon(volumeOn);
                else
                    muter.setIcon(mute);
            }
            if (e.getSource() == shuffle){
                if(shuffle.getIcon().equals(unShuffle))
                    shuffle.setIcon(shuffleIc);
                else
                    shuffle.setIcon(unShuffle);
            }
        }
    }

    public JPanel getSongInformation() {
        return songInformation;
    }

    public void setSongPanels(SongPanels songPanels){
        this.songPanels=songPanels;
    }

    public void setSongPanel(SongPanel songPanel) {
        this.songPanel = songPanel;
        playThread=this.songPanel.getSong().getPlayTheread();
    }

    public SongPanel getSongPanel() {
        return songPanel;
    }

    public SongPanels getSongPanels() {
        return songPanels;
    }

    public Thread getPlayThread() {
        return playThread;
    }

    public  ProSlider getSlider(){
        return songSetter.getSlider();
    }
    public ProButton getPlayb(){
        return playb;
    }

    public ProButton getBackb() {
        return backb;
    }

    public ProButton getNextb() {
        return nextb;
    }
    class MoveSlider implements Runnable{
        private Integer position;
        private ProSlider slider;

        public MoveSlider(Integer position, ProSlider slider) {
            this.position = position;
            this.slider = slider;
        }

        @Override
        public void run() {
            Robot r= null;
            try {
                r = new Robot();
                for (position.setValue(0); position.getValue() <slider.getMaximum() ; ) {
                    r.delay(1);
                    if(move){
                        slider.setValue(position.getValue());


                    }
                    position.setValue(position.getValue()+1);
                    SwingUtilities.updateComponentTreeUI(slider);
                }
            } catch (AWTException e) {
                e.printStackTrace();
            }

        }
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
