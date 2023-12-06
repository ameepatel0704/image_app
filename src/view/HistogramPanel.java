package view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * A class for displaying Histogram of the images.
 */
class HistogramPanel extends JPanel {
  private int[][] histogramData;

  /**
   * The constructor of the histogram panel.
   *
   * @param histogramData takes the 2d int array of data of the histogram of image.
   */
  public HistogramPanel(int[][] histogramData) {
    this.histogramData = histogramData;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    //    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int width = getWidth();
    int height = getHeight();

    int histogramWidth = width - 50;
    int histogramHeight = height - 50;
    int barWidth = histogramWidth / 256;


    g2d.setBackground(Color.WHITE);
    //g2d.fillRect(0, 0, width, height);

    int[] colors = {Color.RED.getRGB(), Color.GREEN.getRGB(), Color.BLUE.getRGB(),
        Color.DARK_GRAY.getRGB()};

    // Draw color boxes and labels
    int startX = getWidth() / 2 + 180;
    drawColorBoxAndLabel(g, "Red", Color.RED, startX, 15);
    drawColorBoxAndLabel(g, "Green", Color.GREEN, startX + 55, 15);
    drawColorBoxAndLabel(g, "Blue", Color.BLUE, startX + 110, 15);
    drawColorBoxAndLabel(g, "Intensity", Color.DARK_GRAY, startX + 165, 15);


    for (int k = 0; k < histogramData.length; k++) {
      int[] data = histogramData[k];
      int max = findMaxValue(data);
      double scaleFactor = (double) histogramHeight / max;

      // Draw x-axis
      g2d.setColor(Color.BLACK);
      g2d.drawLine(25, height - 25, width - 25, height - 25);
      g2d.drawString("Intensity", histogramWidth / 2 + 40, height - 2);

      // Draw y-axis
      g2d.drawLine(25, height - 25, 25, 25);
      g2d.drawString("Pixel value", 5, height - 2);


      Font font = new Font("Arial", Font.PLAIN, 12);
      g2d.setFont(font);
      for (int i = 0; i < 256; i += 50) {
        String label = String.valueOf(i);
        int labelWidth = g2d.getFontMetrics().stringWidth(label);
        int labelX = 25 + (histogramWidth * i / 255) - (labelWidth / 2);
        int labelY = height - 10;
        g2d.drawString(label, labelX, labelY);
      }

      // Draw Y-axis labels
      for (int i = 0; i < histogramHeight; i += histogramHeight / 10) {
        String label = String.valueOf(i);
        int labelWidth = g2d.getFontMetrics().stringWidth(label);
        int labelX = 5;
        int labelY = height - 25 - i;
        g2d.drawString(label, labelX, labelY);
      }

      g2d.setColor(new Color(colors[k]));
      for (int i = 1; i < data.length; i++) {
        int x1 = (int) (((double) (width) / (data.length - 1)) * (i - 1) + 25);
        int y1 = (height) - (int) (data[i - 1] * scaleFactor) - 25;
        int x2 = (int) (((double) (width) / (data.length - 1)) * i + 25);
        int y2 = (height) - (int) (data[i] * scaleFactor) - 25;
        g2d.drawLine(x1, y1, x2, y2);
      }
    }
  }

  private void drawColorBoxAndLabel(Graphics g, String label, Color color, int x, int y) {
    g.setColor(color);
    g.fillRect(x, y, 10, 10);
    g.setColor(Color.BLACK);
    g.drawString(label, x + 15, y + 10);
  }

  /**
   * The method to find the max value from the data.
   *
   * @param data takes int array to find the max value from the array.
   * @return maximum value from the data.
   */
  private int findMaxValue(int[] data) {
    int max = data[0];
    for (int i = 1; i < data.length; i++) {
      if (data[i] > max) {
        max = data[i];
      }
    }
    return max;
  }
}
