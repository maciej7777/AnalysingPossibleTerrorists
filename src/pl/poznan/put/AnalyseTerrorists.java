package pl.poznan.put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Maciej Uniejewski on 2017-03-17.
 */
public class AnalyseTerrorists {

    private AnalyseTerrorists() {

    }

    private static float getRandomNumber() {
        return (float) ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE) / Integer.MAX_VALUE;
    }

    private static int getRandomHotel(int hotels) {
        return ThreadLocalRandom.current().nextInt(hotels);
    }

    private static void initMeetings(int n, ArrayList<HashMap<Integer, Integer>> meetings) {
        for (int i = 0; i < n; i++) {
            meetings.add(new HashMap<>());
        }
    }

    private static ArrayList<ArrayList<Integer>> initHotels(int h) {

        ArrayList<ArrayList<Integer>> hotels = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            hotels.add(new ArrayList<>());
        }

        return hotels;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();

        int n = Integer.parseInt(args[0]);
        float p = Float.parseFloat(args[1]);
        int h = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);

        ArrayList<HashMap<Integer, Integer>> meetings = new ArrayList<>();

        initMeetings(n, meetings);

        //For each day
        for (int i = 0; i < d; i++) {

            ArrayList<ArrayList<Integer>> hotels = initHotels(h);

            //Simulate each person behavior
            for (int j = 0; j < n; j++) {
                if (getRandomNumber() < p) {
                    int chosenHotel = getRandomHotel(h);

                    System.out.println("[Day " + (i + 1) + "]Person " + j + " have chosen hotel number " + chosenHotel + ".");
                    hotels.get(chosenHotel).add(j);
                }
            }

            for (int hh = 0; hh < h; hh++) {
                ArrayList<Integer> peopleList = hotels.get(hh);
                if (peopleList != null) {
                    for (Integer person1 : peopleList) {
                        peopleList.stream().filter(person2 -> person1.intValue() != person2.intValue()).forEach(person2 -> {
                            Integer numberOfMeets = meetings.get(person1).get(person2);
                            if (numberOfMeets == null) {
                                meetings.get(person1).put(person2, 1);
                            } else {
                                meetings.get(person1).put(person2, numberOfMeets + 1);
                            }
                        });
                    }
                }
            }
        }

        //Collect data after simulation
        int suspectedPair = 0;
        int suspectedPeople = 0;
        int max = 0;

        //Suspected
        for (int person = 0; person < n; person++) {
            boolean ifSuspected = false;
            for (Map.Entry<Integer, Integer> m : meetings.get(person).entrySet()) {
                if (m.getValue() > 1) {
                    if (m.getValue() > max) {
                        max = m.getValue();
                    }
                    suspectedPair++;
                    ifSuspected = true;
                }
            }
            if (ifSuspected) {
                suspectedPeople++;
            }
        }

        HashMap<Integer, Integer> histogram = new HashMap<>();
        for (int i = 0; i < max + 1; i++) {
            histogram.put(i, 0);
        }

        for (int person = 0; person < n; person++) {
            for (Map.Entry<Integer, Integer> m : meetings.get(person).entrySet()) {
                if (m.getKey() > person) {
                    Integer wystapienia = histogram.get(m.getValue());

                    histogram.put(m.getValue(), wystapienia + 1);
                }
            }
        }

        System.out.println("Suspected pairs: " + suspectedPair / 2);
        System.out.println("Suspected people: " + suspectedPeople);
        System.out.println("Histogram: " + histogram);

        long elapsedTime = System.nanoTime() - start;

        System.out.println("Duration: " + elapsedTime/1000000000.0 + "s");
    }
}