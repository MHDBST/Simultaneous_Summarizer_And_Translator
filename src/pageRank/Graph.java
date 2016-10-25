package pageRank;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

	ArrayList<ArrayList<String>> nodes;

	HashMap<String, Double> edges;

	public Graph() {

		nodes = new ArrayList<ArrayList<String>>();
		edges = new HashMap<String, Double>();

	}

	public void addNode(ArrayList<String> s) {

		nodes.add(s);
		completeGraph(nodes.size(), s);
	}

	public void completeGraph(int index, ArrayList<String> source) {
		for (int i = 0; i < index - 1; i++) {
			int count = 0; // tedad kalamate moshtarak
			ArrayList<String> end = nodes.get(i);
			for (String s : source) {
				if (end.contains(s))
					count++;
			}
			double weight = 0;
			if (count != 0)
				weight = (double) count
						/ (Math.log(source.size()) + Math.log(end.size()));
//			System.out.println(count + "hrrere " + source.size() + " "
//					+ end.size());
			addEdge(index - 1, i, weight);
		}

	}

	public void addEdge(int i, int j, double weight) {
		edges.put(i + "+" + j, weight);
	}

	public ArrayList<ArrayList<String>> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<ArrayList<String>> nodes) {
		this.nodes = nodes;
	}

	public HashMap<String, Double> getEdges() {
		return edges;
	}

	public void setEdges(HashMap<String, Double> edges) {
		this.edges = edges;
	}

}
