import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.NoSuchElementException;


/**
 * Class that is capable of reading in a graph file from disk.
 * Graph files are line based. Node names have type String and edge weights have
 * type Double. Fields on the line are separated by ':' and there is no extra white space.
 */
public class DiGraphReader implements IGraphReader {
    // Fields needed for the Graph Reader should be added here
    IGraph<String, Double> graph;

    /**
     * Creates a new graph reader instance
     */
    public DiGraphReader() {
        // Constructor doesn't need to do anything bc things initialize when the file is read in
    }

    /**
     * Reads in a file and instantiates the graph
     * @param filename the file to read
     * @return the instantiated graph
     */
    public IGraph<String,Double> read(String filename) throws FileNotFoundException, IOException, NoSuchElementException {
        // Open the file
        int rows=0;        // how many lines in the file
        int colons=0;
        String line;
        BufferedReader br=new BufferedReader(new FileReader(filename));
        while((line = br.readLine())!=null) {
          for(int c=0; c<line.length(); c++) {     //goes through characters in line and checks that there are two colons (so three arguments) before incrementing rows
              if (line.charAt(c) == ':'){
                colons++;
              }
            }
            if (colons % 2 == 0) { rows++; }
        }
        //System.out.println(rows);  working!

        graph = (IGraph<String, Double>)new Graph<String, Double>();

        Scanner scan = new Scanner(new File(filename));
        scan.useDelimiter(":|\n");

        for(int i=0; i<rows; i++) {

              String node1 = scan.next();
              //System.out.println(node1); check works

              String node2 = scan.next();
              //System.out.println(node2); check works

              String weigh = scan.next();
              Double wei = Double.parseDouble(weigh);
              //System.out.println(wei); check works

              INode<String> nOne = graph.addNode(node1);
              INode<String> nTwo = graph.addNode(node2);
              graph.addEdge(nOne,nTwo,wei);
        }
        // Return the graph instance
        return graph;
    }

    /**
     * Simple main method to open and process a file
     */
    public static void main(String[] argv) throws Exception {
        // This code should work without modification once your reader code is working
        IGraphReader r = new DiGraphReader();
        IGraph<String,Double> g = r.read("graphfile.cs2");
        IEdge<String,Double>[] edges = g.getEdgeSet();
        for(int i=0; i<edges.length; i++) {
            System.out.println(edges[i].getSource().getValue()+" -> "+edges[i].getDestination().getValue()+"  w: "+edges[i].getWeight());
        }
    }
}
