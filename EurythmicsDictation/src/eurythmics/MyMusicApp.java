package eurythmics;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jfugue.*;

public class MyMusicApp {
	
	public static String promptUser(String prompt) {
		Scanner sc = new Scanner(System.in);
		System.out.print(prompt);
		return sc.nextLine();
	}
	
	public static void run() {
		int tempo = Integer.parseInt(promptUser("Specify a tempo: "));
		int numMeasures = Integer.parseInt(promptUser("Number of measures: "));
		int beatsPerMeasure = Integer.parseInt(promptUser("Number of beats in a measure: "));
		Player player = new Player();
		String full = "T[" + tempo +"] ";
		for (int j = 0; j < beatsPerMeasure; j++) {
			full += "Cq ";
		}
		full += "| ";
		for (int i = 0; i < numMeasures; i++) {
			ArrayList<String> randomMeasure = MusicUtils.getRandomMeasure(beatsPerMeasure);
			String measure = "";
			for (String s : randomMeasure) {
				measure += (s + " ");
			}
			full += (measure + "| ");
		}
		player.play(full);
		String answer = promptUser("Are you finished? yes/no: ");
		while (!answer.contains("yes")) {
			player.play(full);
			answer = promptUser("Are you finished? yes/no: ");
		}
		System.out.println("What I played: ");
		StringTokenizer tokenizer = new StringTokenizer(full, "|");
		int currentMeasure = 0;
		while(tokenizer.hasMoreElements()) {
			StringTokenizer meas = new StringTokenizer(tokenizer.nextToken());
			System.out.print("Meausre " + currentMeasure + ": ");
			while (meas.hasMoreElements()) {
				String next = meas.nextToken();
				if (next.contains("*3:4")) next = "t";
				else if (next.contains("q.")) next = "q.";
				else if (next.contains("q")) next = "q";
				else if (next.contains("i")) next = "i";
				else if (next.contains("h")) next = "h";
				else if (next.contains("s")) next = "s";
				System.out.print(next + " ");
			}
			currentMeasure += 1;
			System.out.println("");
		}
	}
	
	public static void main(String [] args) {
		run();
	}
	
}
