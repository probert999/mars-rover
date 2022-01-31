package com.probert999.marsrover.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuadPlateauMap implements PlateauMapInterface {

  private BufferedImage roverImage = null;
  private BufferedImage roverImageN = null;
  private BufferedImage roverImageE = null;
  private BufferedImage roverImageS = null;
  private BufferedImage roverImageW = null;
  private BufferedImage gridImage = null;
  private final JFrame mapFrame;
  private List<RoverDetails> rovers = null;
  private int roverImageHeight;
  private int roverImageWidth;
  private final int xMaximum;
  private final int yMaximum;
  private final GridMap gridMap;

  public QuadPlateauMap(String name, int xMaximum, int yMaximum) {

    try {
      roverImage = ImageIO.read(new File("img\\rover.png"));
      roverImageN = ImageIO.read(new File("img\\rover-n.png"));
      roverImageE = ImageIO.read(new File("img\\rover-e.png"));
      roverImageS = ImageIO.read(new File("img\\rover-s.png"));
      roverImageW = ImageIO.read(new File("img\\rover-w.png"));
      roverImageHeight = roverImage.getHeight();
      roverImageWidth = roverImage.getWidth();

      gridImage = ImageIO.read(new File("img\\mars.png"));
    } catch (IOException e) {
      System.out.println("Image files not found");
    }

    mapFrame = new JFrame();
    mapFrame.setSize(680, 680);
    mapFrame.setTitle(name);

    this.xMaximum = xMaximum;
    this.yMaximum = yMaximum;

    gridMap = new GridMap();
    mapFrame.add(gridMap);

    mapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public boolean isMapVisible()
  {
      return mapFrame.isVisible();
  }

  public void show(List<RoverDetails> listOfRovers) {
    mapFrame.setVisible(true);
    this.rovers = listOfRovers;
    gridMap.removeAll();
    gridMap.revalidate();
    gridMap.repaint();
    gridMap.transferFocusBackward();
  }

  public void hide(boolean finish) {
      if (finish) {
          mapFrame.dispose();
      } else {
      mapFrame.setVisible(false);
      }
  }

  private class GridMap extends JComponent {

    private final int imageOffset = 20;
    private int xGridOffset = 20;
    private int yGridOffset = 20;
    private final static int MAX_GRID_SIZE = 20;

    private boolean drawGrid = false;
    private int xGridSize;
    private int yGridSize;
    private final int MAP_SIZE = 600;
    private final Font gridFont;

    public GridMap() {
      this.xGridSize = xMaximum;
      this.yGridSize = yMaximum;

      gridFont = new Font("Courier New", Font.BOLD, 12);

      // If grid is being shown, calculate size of squares and offset on image
      if (xMaximum <= MAX_GRID_SIZE && yMaximum <= MAX_GRID_SIZE) {
        this.xGridSize = MAP_SIZE / (xMaximum + 1);
        this.yGridSize = MAP_SIZE / (yMaximum + 1);

        this.xGridOffset = imageOffset + (MAP_SIZE - (xGridSize * (xMaximum + 1))) / 2;
        this.yGridOffset = imageOffset + (MAP_SIZE - (yGridSize * (yMaximum + 1))) / 2;
        this.drawGrid = true;
      }
    }

      protected void paintComponent(Graphics g) {
          g.setFont(gridFont);
          drawGrid(g);
          drawRovers(g);
      }

      public void drawRovers(Graphics g) {
          int gridXPosition;
          int gridYPosition;
          for (RoverDetails rover : rovers) {
              int roverXPosition = rover.getXPosition();
              int roverYPosition = rover.getYPosition();
              if (drawGrid) {
                  gridXPosition = (xGridSize * (roverXPosition + 1))
                                  - (xGridSize / 2) + xGridOffset - (roverImageWidth / 2);

                  gridYPosition = (yGridSize * (yMaximum - roverYPosition))
                                  + (yGridSize / 2) + yGridOffset - (roverImageHeight / 2);
              } else {
                  gridXPosition = (int)
                                  ((((double) MAP_SIZE - (double) roverImageWidth)
                                          / (double) xMaximum) * (double) roverXPosition)
                                    + xGridOffset;
                  gridYPosition = (int)
                                  ((double)(MAP_SIZE - roverImageHeight) -
                                  ((double)(MAP_SIZE - roverImageHeight) / (double)yMaximum) * (double) roverYPosition)
                                   + yGridOffset;
              }

              switch (rover.getHeading())
              {
                  case 'N' -> roverImage = roverImageN;
                  case 'E' -> roverImage = roverImageE;
                  case 'S' -> roverImage = roverImageS;
                  case 'W' -> roverImage = roverImageW;
              }
              g.drawImage(roverImage, gridXPosition, gridYPosition, this);
              String roverDesc = rover.getRoverName();
              int nameOffset = (g.getFontMetrics().stringWidth(roverDesc) - roverImageWidth) / 2;
              g.drawString(roverDesc, gridXPosition - nameOffset, gridYPosition+roverImageHeight + 15);
          }
      }

      public void drawGrid(Graphics g) {
          setOpaque(false);
          g.drawImage(gridImage, imageOffset, imageOffset, this);

          Graphics2D g2d = (Graphics2D) g;
          if (drawGrid) {
              g2d.setColor(Color.RED);
              for (int x = 0; x <= xMaximum; x ++)
                  for (int y = 0; y <= yMaximum; y ++)
                      g.drawRect((x * xGridSize) + xGridOffset, (y*yGridSize) + yGridOffset, xGridSize, yGridSize);
          }

          // Display grid co-ordinates
          g.setColor(Color.BLACK);
          g.drawString("0,0", imageOffset, MAP_SIZE + imageOffset + 15);
          g.drawString(xMaximum + ",0", MAP_SIZE - imageOffset, MAP_SIZE + imageOffset +  15);
          g.drawString("0," + yMaximum, imageOffset, imageOffset - 5);
          g.drawString(yMaximum + "," + xMaximum, MAP_SIZE - imageOffset, imageOffset - 5);
      }
  }
}