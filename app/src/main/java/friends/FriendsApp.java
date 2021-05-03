package friends;

import java.io.*;
import java.util.*;

// Testing client for Friends
public class FriendsApp {

	public static void main (String[] args) {

		/* if ( args.length < 1 ) {
			System.out.println("Expecting graph text file as input");
			return;
		} */

		/* System.out.print("Enter the path of a graph text file: ");
		var scanner = new Scanner(System.in);
		String filename = scanner.nextLine(); */
		String filename = "./sampleGraph.txt";
		try {
			Graph g = new Graph(new Scanner(new File(filename)));

			// Update p1 and p2 to refer to people on Graph g
			// sam and sergei are from sample graph
			String p1 = "sam";
			String p2 = "sergei";
			ArrayList<String> shortestChain = Friends.shortestChain(g, p1, p2);

			var paths = Friends.allPaths(g);

			for (ArrayList<Integer> path : paths) {
				System.out.println(path);
			}

			// Testing Friends.shortestChain
			System.out.println("Shortest chain from " + p1 + " to " + p2);
			for ( String s : shortestChain ) {
				System.out.println(s);
			}

			System.out.println("Rutgers Cliques:");
			var cliques = Friends.cliques(g, "rutgers");
			for (var clique : cliques) {
				for (String student : clique) {
					System.out.println(student);
				}
				System.out.println("-------------");
			}

			// ADD test for Friends.cliques() here


			// ADD test for Friends.connectors() here
		} catch (FileNotFoundException e) {

			System.out.println(filename + " not found");
		}
	}
}
