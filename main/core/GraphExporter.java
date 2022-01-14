package main.core;

import java.io.*;
public class GraphExporter {

    public static String outputDir = "dot";

    public static  void exportGraphToDOT(Graph g,String fileName) {

        StringBuilder file = new StringBuilder();

        //First row gets graph row 
        file.append("graph g {\n");

        for(Edge e : g.EdgeList) {

            file.append(e.a.NodeIndex+" -- "+e.b.NodeIndex+"\n");
        }
        //Last row closing graph
        file.append("}\n");



        try {
            File dotFile = new File(outputDir+"//"+fileName);
            FileWriter myWriter = new FileWriter(dotFile);
            myWriter.write(file.toString());
            myWriter.close();
            convertDotToImage(dotFile.getAbsolutePath());
          } catch (IOException e) {

            System.out.println(e.toString());
          }


    }

    public static void convertDotToImage(String filePath) {

        String cmd = "cmd /c dot -Tsvg "+filePath+" > "+filePath.substring(0,filePath.length()-4)+".svg";

		try {
			 Runtime.getRuntime().exec(cmd);			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
