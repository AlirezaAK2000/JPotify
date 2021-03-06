package Center;
import Tools.OvalBorder;
import Tools.ProButton;
import Tools.RoundedCornerBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SearchBox extends JPanel {
    private JTextField searcher;
    private BufferedImage originalImage;
    private BufferedImage scaledImage;
    private JLabel usernameLabel;
    public SearchBox(String username) throws IOException {
        searcher = new JTextField("Search");
        searcher.setBackground(Color.WHITE);
        searcher.setPreferredSize(new Dimension(250, 30));
        searcher.setEditable(true);

        usernameLabel = new JLabel(username);
        usernameLabel.setOpaque(false);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("serif" , Font.BOLD , 20));

        originalImage = ImageIO.read(new File("backgrounds\\left2.jpg"));
        this.setLayout(new GridLayout(1 , 3));
        this.setBackground(Color.darkGray);
        this.add(searcher);
        this.add(Box.createHorizontalStrut(0));
        this.add(usernameLabel);

    }

    public void paintComponent(Graphics g) {
        double widthScaleFactor = getWidth() / (double) originalImage.getWidth();
        double heightScaleFactor = getHeight() / (double) originalImage.getHeight();

        AffineTransform at = new AffineTransform();
        at.scale(widthScaleFactor, heightScaleFactor);

        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        scaledImage = scaleOp.filter(originalImage, null);


        g.drawImage(scaledImage, 0, 0, null);
        addComponentListener(new ResizerListener());
    }


    private class ResizerListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            double widthScaleFactor = getWidth() / (double) originalImage.getWidth();
            double heightScaleFactor = getHeight() / (double) originalImage.getHeight();

            AffineTransform at = new AffineTransform();
            at.scale(widthScaleFactor, heightScaleFactor);

            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            scaledImage = scaleOp.filter(originalImage, null);

            repaint();
        }



    }

    public JTextField getSearcher() {
        return searcher;
    }
}