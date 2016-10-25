package summarization;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

	final int individualCount = 1000;
	final double percentage = 0.5;
	final double mutationProb = 0.002;
	private int individualSize;
	private ArrayList<int[]> docsmanSum;
	private Individual[] inds;
	private float[] fitnesses;
	private int[] fitnessesIndex;
	private static Individual finalIndividual;
	private ArrayList<ArrayList<Data>> docsdatum;

	public GeneticAlgorithm(int individualSize, ArrayList<int[]> docsmanSum,
			ArrayList<ArrayList<Data>> docsdatum) {

		this.individualSize = individualSize;
		this.docsmanSum = docsmanSum;
		inds = new Individual[individualCount];
		this.docsdatum = docsdatum;

		initial();

		nextGeneration(1, inds);
	}

	public void initial() {

		for (int i = 0; i < individualCount; i++) {
			Individual indi = new Individual(individualSize, percentage/*
																		 * ,
																		 * manSum
																		 */);
			indi.randGenes();
			inds[i] = indi;
		}

	}

	public void nextGeneration(int index, Individual[] inds) {

		Individual[] newinds = new Individual[individualCount];
		// ///////baraye hame documentha

		for (int j = 0; j < docsdatum.size(); j++) {

			fitnesses = new float[individualCount];
			fitnessesIndex = new int[individualCount];
			for (int i = 0; i < individualCount; i++) {
				// inds[i].evaluate(datum);
				inds[i].evaluate(docsdatum.get(j), docsmanSum.get(j));
				fitnesses[i] += inds[i].getFitnessValue();

			}

		}
		for (int i = 0; i < individualCount; i++) {
			fitnessesIndex[i] = i;
			fitnesses[i] = (float) fitnesses[i] / docsdatum.size();

		}

		mySort(0, individualCount - 1, fitnesses, fitnessesIndex);

		for (int i = 0; i < 50; i++) {
			// bayad 50 taye akhare un beshan 50 taye avale in :D chon sortemun
			// bar ax moratab mikone
			newinds[i] = inds[fitnessesIndex[individualCount - 1 - i]];

		}
		float max950Inds = fitnesses[individualCount - 1 - 50];
		for (int i = 50; i < individualCount; i++) {
			// ///////////cross over ////////////
			int index1 = 0;
			int index2 = 0;
			double p1 = Math.random();
			double p2 = Math.random();
			double cumulativeProbability = 0.0;
			for (int f = 0; f < individualCount - 50; f++) {
				cumulativeProbability += (float) fitnesses[f] / max950Inds;
				if (p1 <= cumulativeProbability) {
					index1 = f;
					p1 = 50;
				}
				if (p2 <= cumulativeProbability) {
					index2 = f;
					p2 = 50;
				}
				if (p1 == 50 && p2 == 50)
					break;

			}

			// mahale cross over
			int crossOverI = new Random().nextInt(individualSize);
			newinds[i] = merge(inds[fitnessesIndex[index1]],
					inds[fitnessesIndex[index2]], crossOverI);

			// ///////mutation//////
			double m = Math.random();
			if (m < mutationProb)
				newinds[i].mutate();
			;

		}

		// chizi k azash balaie ro neveshtam
		// ////////////
		// double p = Math.random();
		// double cumulativeProbability = 0.0;
		// for (Item item : items) {
		// cumulativeProbability += item.probability();
		// if (p <= cumulativeProbability) {
		// return item;
		// }
		// }

		index++;
		// 101 bar anjam bede, bare 101 om tu inds moratab shode khurujimun
		// hast(vaznhaie monaseb k monasebtarinesh tu ins[akhari] hast)
		if (index != 101)
			nextGeneration(index, newinds);
		else {
			setFinalIndividual(inds[fitnessesIndex[individualCount - 1]]);
		}

	}

	public static Individual getFinalIndis() {
		return finalIndividual;
	}

	public void setFinalIndividual(Individual finalIndividual) {
		this.finalIndividual = finalIndividual;
	}

	// / sort A with respect to B
	public void mySort(int l, int r, float[] A, int[] B) {
		if (l > r)
			return;
		float x = A[r];
		int small = l;
		for (int i = l; i < r; i++) {
			if (x > A[i]) {
				float k = A[small];
				A[small] = A[i];
				A[i] = k;
				int d = B[small];
				B[small] = B[i];
				B[i] = d;
				small++;
			}
		}
		float k = A[small];
		A[small] = x;
		A[r] = k;
		int d = B[small];
		B[small] = B[r];
		B[r] = d;
		mySort(l, small - 1, A, B);
		mySort(small + 1, r, A, B);
	}

	public Individual merge(Individual i1, Individual i2, int crossOverI) {
		Individual res = new Individual(individualSize, percentage/* , manSum */);
		for (int i = 0; i < crossOverI; i++)
			res.setGene(i, i1.getGene(i));
		for (int i = crossOverI; i < individualSize; i++)
			res.setGene(i, i2.getGene(i));
		return res;
	}
}

class Individual {
	// public static final int SIZE = 500;
	private int size;
	private int[] genes;
	private float fitnessValue;
	private double percentage;

	// private int[] manSum;

	public Individual(int size, double percentage/* , int[] manSum */) {

		this.size = size;
		this.percentage = percentage;
		// this.manSum = manSum;
		genes = new int[size];
	}

	public float getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(float fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public int getGene(int index) {
		return genes[index];
	}

	public void setGene(int index, int gene) {
		this.genes[index] = gene;
	}

	public void randGenes() {
		Random rand = new Random();
		for (int i = 0; i < size; ++i) {
			this.setGene(i, rand.nextInt(2));
		}
	}

	public void mutate() {
		Random rand = new Random();
		int index = rand.nextInt(size);
		this.setGene(index, 1 - this.getGene(index)); // flip
	}

	private int[] getGenes() {
		return genes;
	}

	public void evaluate(ArrayList<Data> datum, int[] manSum) {

		Evaluate e = new Evaluate(this.getGenes(), percentage, manSum, datum);
		// System.out.println("length is " + manSum.length);
		setFitnessValue(e.calculate());

		// * int fitness = 0; for (int i = 0; i < size; ++i) { fitness +=
		// * this.getGene(i); } this.setFitnessValue(fitness);
		// *
		// * return fitness;

	}
}
