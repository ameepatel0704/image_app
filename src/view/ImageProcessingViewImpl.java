package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;


import controller.Features;
import model.EnhancedImageProcessingModel;
import model.Pixel;

/**
 * This class holds all the GUI implementation of view.
 */
public class ImageProcessingViewImpl extends JFrame implements ImageProcessingView {

  private final String objectName = "image";
  private final JPanel imagePanel;
  private final JPanel histogramPanel;
  private final JButton loadButton;
  private final JButton verticalFlipButton;
  private final JButton horizontalFlip;
  private final JButton greyScaleButton;
  private final JButton dither;
  private final JButton blur;
  private final JButton sharpen;
  private final JButton sepia;
  private final JButton brightness;
  private final JButton splitAndSave;
  private final JButton loadAndCombine;
  private final JButton saveButton;
  private final EnhancedImageProcessingModel model;
  int flag = 0;
  private JPanel editButtonPanel;
  private JButton editButton;
  private JLabel fileSaveDisplay;
  private JTextField input;
  private JTextField commandInput;
  private String command;


  /**
   * This is the construction of view implementation class.
   *
   * @param caption is the caption of the loaded image.
   * @param model   is the model of the enhanced image processing model.
   */
  public ImageProcessingViewImpl(String caption, EnhancedImageProcessingModel model) {
    super(caption);
    this.model = model;

    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //show an image with a scrollbar
    this.imagePanel = new JPanel();
    //a border around the panel with a caption
    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    this.imagePanel.setPreferredSize(new Dimension(400, 300));
    this.imagePanel.setVisible(true);
    mainPanel.add(imagePanel);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);


    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileSavePanel);
    this.saveButton = new JButton("Save a file");
    this.saveButton.setActionCommand("Save file");
    fileSavePanel.add(this.saveButton);
    this.loadButton = new JButton("Open a file");
    this.loadButton.setActionCommand("open file");
    fileSavePanel.add(this.loadButton);

    //commands panel
    JPanel commandsPanel = new JPanel();
    setPreferredSize(new Dimension(600, 400));
    setLocation(400, 400);
    //a border around the panel with a caption
    commandsPanel.setBorder(BorderFactory.createTitledBorder("Commands Panel"));
    commandsPanel.setLayout(new GridLayout(2, 0, 10, 10));
    mainPanel.add(commandsPanel);

    this.verticalFlipButton = new JButton("Vertical Flip");
    this.verticalFlipButton.setActionCommand("vertical flip");
    commandsPanel.add(this.verticalFlipButton);

    this.horizontalFlip = new JButton("Horizontal flip");
    this.horizontalFlip.setActionCommand("horizontal flip");
    commandsPanel.add(this.horizontalFlip);

    this.greyScaleButton = new JButton("Greyscale options");
    this.greyScaleButton.setActionCommand("grayscale option");
    commandsPanel.add(this.greyScaleButton);

    this.dither = new JButton("Dither");
    this.dither.setActionCommand("dither");
    commandsPanel.add(this.dither);

    this.blur = new JButton("Blur");
    this.blur.setActionCommand("filter blur");
    commandsPanel.add(this.blur);

    this.sharpen = new JButton("Sharpen");
    this.sharpen.setActionCommand("filter sharpen");
    commandsPanel.add(this.sharpen);

    this.sepia = new JButton("Sepia Tone");
    this.sepia.setActionCommand("color-transform sepia");
    commandsPanel.add(this.sepia);

    this.brightness = new JButton("Brightness");
    this.brightness.setActionCommand("brightness");
    commandsPanel.add(this.brightness);

    this.splitAndSave = new JButton("Split and save");
    this.splitAndSave.setActionCommand("split save");
    commandsPanel.add(this.splitAndSave);

    this.loadAndCombine = new JButton("Load and combine");
    this.loadAndCombine.setActionCommand("load combine");
    commandsPanel.add(this.loadAndCombine);


    mainPanel.add(commandsPanel);


    //show an image histogram with a scrollbar
    this.histogramPanel = new JPanel();
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(600, 400));
    setLocation(400, 400);
    //a border around the panel with a caption
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    this.histogramPanel.setLayout(new GridLayout(1, 0, 10, 10));
    this.histogramPanel.setVisible(true);

    //imagePanel.setMaximumSize(null);
    JScrollPane histogramScrollPane = new JScrollPane(histogramPanel);
    histogramScrollPane.setPreferredSize(new Dimension(1200, 800));
    mainPanel.add(histogramScrollPane);
    pack();
    setVisible(true);
  }


  @Override
  public void addFeatures(Features features) {
    this.loadButton.addActionListener(evt -> {
      features.loadImage(objectName);
    });
    this.saveButton.addActionListener(evt -> features.saveImage());
    this.verticalFlipButton.addActionListener(evt -> features.verticalFlip());
    this.horizontalFlip.addActionListener(evt -> features.horizontalFlip());
    this.greyScaleButton.addActionListener(evt -> {
      JFrame greyScaleOptions = new JFrame();
      String[] options = {"Intensity", "Value", "Luma", "Red", "Green", "Blue"};
      int input = JOptionPane.showOptionDialog(greyScaleOptions,
          "Choose a greyscale option:", "Greyscale options",
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
          null, options, options[0]);
      selectGreyScale(options[input], features);
    });
    this.dither.addActionListener(evt -> features.dither());
    this.blur.addActionListener(evt -> features.blur());
    this.sharpen.addActionListener(evt -> features.sharpen());
    this.sepia.addActionListener(evt -> features.sepiaTone());
    this.brightness.addActionListener(evt -> {
      JFrame brightnessPopUp = new JFrame("Pop-Up Window");
      String input = JOptionPane.showInputDialog(brightnessPopUp,
          "Enter brightness value:");
      int brightnessValue = input.isEmpty() ? 0 : Integer.parseInt(input);
      features.brightness(brightnessValue);
    });
    this.splitAndSave.addActionListener(evt -> {
      JFrame greyScaleOptions = new JFrame();
      String[] options = {"Red greyscale", "Green greyscale", "Blue greyscale"};
      int input = JOptionPane.showOptionDialog(greyScaleOptions,
          "Choose a greyscale option to save:", "Greyscale options",
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
          null, options, options[0]);
      splitAndSaveImages(options[input], features);
    });

    this.loadAndCombine.addActionListener(evt -> {
      JFrame greyScaleOptions = new JFrame();
      String[] options = {"Red greyscale", "Green greyscale", "Blue greyscale"};
      for (int i = 0; i < options.length; i++) {
        int input = JOptionPane.showOptionDialog(greyScaleOptions,
            "Choose a greyscale option to save:", "Greyscale options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[i]);
        loadAndCombineImages(options[input], features);
      }
      features.combineImage();
    });

  }

  private void selectGreyScale(String greyScaleOption, Features features) {
    switch (greyScaleOption) {
      case "Intensity":
        features.intensityGrayscale();
        break;
      case "Value":
        features.valueGrayscale();
        break;
      case "Luma":
        features.lumaGrayscale();
        break;
      case "Red":
        features.redGrayscale();
        break;
      case "Green":
        features.greenGrayscale();
        break;
      case "Blue":
        features.blueGrayscale();
        break;
      default:
        System.out.println("ERROR");
    }

  }

  private void splitAndSaveImages(String option, Features features) {
    switch (option) {
      case "Red greyscale":
        features.redGrayscale();
        features.saveImage();
        break;
      case "Green greyscale":
        features.greenGrayscale();
        features.saveImage();
        break;
      case "Blue greyscale":
        features.blueGrayscale();
        features.saveImage();
        break;
      default:
        System.out.println("Incorrect Command");
    }
  }

  private void loadAndCombineImages(String option, Features features) {
    switch (option) {
      case "Red greyscale":
        features.loadImage(objectName);
        features.redGrayscale();
        break;
      case "Green greyscale":
        features.loadImage(objectName);
        features.greenGrayscale();
        break;
      case "Blue greyscale":
        features.loadImage(objectName);
        features.blueGrayscale();
        break;
      default:
        System.out.println("Incorrect Command");
    }
  }

  @Override
  public String load() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "PNG,JPEG,BMP & PPM Images", "jpeg", "png", "ppm", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retValue = fchooser.showOpenDialog(ImageProcessingViewImpl.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public String save() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "PNG,JPEG,BMP & PPM Images", "jpeg", "png", "ppm", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retValue = fchooser.showSaveDialog(ImageProcessingViewImpl.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public String executeCommand() {
    return this.command + " " + this.commandInput.getText()
        + " " + this.input.getText();
  }

  @Override
  public void displayImage(String imageName) {
    try {
      this.imagePanel.removeAll(); // to remove any previous displaying image
      Pixel[][] image = model.getImage(imageName);
      BufferedImage newImage = new BufferedImage(image[0].length, image.length,
          BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          int red = image[i][j].get(0);
          int green = image[i][j].get(1);
          int blue = image[i][j].get(2);
          int rgb = (red << 16) | (green << 8) | blue;
          newImage.setRGB(j, i, rgb);
        }
      }
      JLabel picLabel = new JLabel(new ImageIcon(newImage));
      this.imagePanel.add(picLabel);
      this.imagePanel.revalidate();
      this.imagePanel.repaint();
      displayHistogram(imageName);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void displayHistogram(String imageName) {
    try {
      int[][] histogram = model.fetchHistogram(imageName);
      this.histogramPanel.removeAll();
      HistogramPanel histPanel = new HistogramPanel(histogram);
      this.histogramPanel.add(histPanel);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Override
  public void displayError(String error) {
    JOptionPane.showMessageDialog(ImageProcessingViewImpl.this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }
}
