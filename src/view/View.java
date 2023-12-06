package view;

/**
 * This is the older view class to display output in command line.
 */
public interface View {
  /**
   * This displays the error encountered while working on image.
   *
   * @param s takes the string input to display it as output.
   * @return string to display.
   */
  String display(String s);
}
