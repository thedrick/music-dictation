package eurythmics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MusicUtils {

	private static String [] validNotes = {"A", "B", "C", "D", "E", "F", "G"};
	private static String [][] validRhythms = {{"h"},
										{"q"},
										{"i", "i"},
										{"s","s","s","s"},
										{"i*3:4", "i*3:4", "i*3:4"},
										{"q.", "i"},
										{"i", "q."},
										{"i", "q", "i"},
										{"i", "s", "s"},
										{"s", "s", "i"},
										{"s", "i", "s"}};
	private static final int maxTempo = 160;
	private static final int minTempo = 80;
	
	public static ArrayList<String> getRandomMeasure(int numBeats) {
		ArrayList<String> measure = new ArrayList<String>();
		Random r = new Random();
		while (numBeats != 0) {
			String[] rhythm = validRhythms[r.nextInt(validRhythms.length)];
			double rhythmDurations = calculateRhythmDuration(rhythm);
			if ((double)numBeats - rhythmDurations < 0) {
				continue;
			}
			else {
				for (String beat : rhythm) {
					String pitch = validNotes[r.nextInt(validNotes.length)];
					measure.add(pitch+beat);
				}
				numBeats -= rhythmDurations;
			}
		}
		return measure;
	}
	
	public static double calculateRhythmDuration(String [] notes) {
		double finalValue = 0.0;
		HashMap<String, Double> durations = rhythmDuration();
		for (String note : notes) {
			finalValue += durations.get(note);
		}
		return finalValue;
	}
	
	public static HashMap<String, Double> rhythmDuration() {
		HashMap<String, Double> durations = new HashMap<String, Double>();
		durations.put("h", new Double(2.0));
		durations.put("q", new Double(1.0));
		durations.put("i", new Double(0.5));
		durations.put("i*3:4", new Double(1.0/3.0));
		durations.put("s", new Double(0.25));
		durations.put("q.", new Double(1.5));
		return durations;
	}
	
	public static String [] getValidNotes() {
		return validNotes;
	}
	
	public static void setValidNotes(String [] validNotes) {
		MusicUtils.validNotes = validNotes;
	}
	
	public static String [][] getValidRhythms() {
		return validRhythms;
	}
	
	public static void setValidRhythms(String [][] validRhythms) {
		MusicUtils.validRhythms = validRhythms;
	}
	
	public static int getMaxTempo() {
		return maxTempo;
	}
	
	public static int getMinTempo() {
		return minTempo;
	}
	
}
