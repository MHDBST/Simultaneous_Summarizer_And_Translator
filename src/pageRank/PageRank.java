package pageRank;

import java.util.HashMap;

public class PageRank {

	Graph graph;
	double[] ws;
	int size;
	HashMap<String, Double> edges;

	public PageRank(Graph graph) {

		this.graph = graph;
		size = graph.getNodes().size();
		edges = graph.getEdges();
		ws = new double[size];
		initialize();
		iterate();

	}

	public void initialize() {
		for (int i = 0; i < ws.length; i++) {
			ws[i] = Math.random();
		}
	}

	public void iterate() {

		double d = 0.85;
		while (true) {
			boolean end = true;
			for (int i = 0; i < size; i++) {
				double preWS = ws[i];
				double newWS = 1 - d;
				double sum = 0;
				for (int j = 0; j < size && j != i; j++) {
					double subSum = 0;
					for (int k = 0; k < size; k++) {
						if (k == j)
							continue;
						int f = Math.min(k, j);
						int l = Math.max(k, j);
						subSum += edges.get(l + "+" + f);
					}
					int f0 = Math.min(i, j);
					int l0 = Math.max(i, j);
					double up = edges.get(l0 + "+" + f0);
					if (up != 0)
						sum += ((double) up / subSum) * ws[j];
				}
				newWS += d * sum;
				ws[i] = newWS;
				if (Math.abs((double) preWS - newWS) > 0.00001)
					end = false;
			}
			if (end)
				break;
		}
		
//		Arrays.sort(ws);

	}
	public double[] getWS(){
		return ws;
	}
}
