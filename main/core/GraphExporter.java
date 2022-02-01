package main.core;

import java.io.*;

/**
 * Simple class for Exporting an interal Graph Structure to an ".dot" File.
 */
public class GraphExporter {
  /**
   * Nam e of the directory of the output
   */
  public static String outputDir = "dot";

  /**
   * Exports an Graph to a Dot File.
   * @param g Graph to export.
   * @param fileName name of the exported dotfile.
   */
  public static void exportGraphToDOT(Graph g, String fileName) {

    StringBuilder file = new StringBuilder();

    // First row gets graph row
    file.append("digraph g {\n");

    for (Edge e : g.EdgeList) {

      file.append(e.a.NodeIndex + " -> " + e.b.NodeIndex + "\n");
    }
    // Last row closing graph
    file.append("}\n");

    try {
      //Writing  dot file.
      File dotFile = new File(outputDir + "//" + fileName);
      FileWriter myWriter = new FileWriter(dotFile);
      myWriter.write(file.toString());
      myWriter.close();
      //Exec dot programm to generate graph as an image.
      convertDotToImage(dotFile.getAbsolutePath());
    } catch (IOException e) {

      System.out.println(e.toString());
    }

  }
  /**
   * Converts a Dotfile to an Image (SVG).
   * @param filePath Path to the dotfile.
   */
  public static void convertDotToImage(String filePath) {
    //Creating shell command
    String cmd = "cmd /c dot -Tsvg " + filePath + " > " + filePath.substring(0, filePath.length() - 4) + ".svg";

    try {
      //execute shell command
      Runtime.getRuntime().exec(cmd);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
