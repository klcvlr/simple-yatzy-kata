package org.codingdojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Yatzy {

    public static final int YATZY_SCORE = 50;
    public static final int SMALL_STRAIGHT_SCORE = 15;
    public static final int LARGE_STRAIGHT_SCORE = 20;
    private final Map<Integer, Integer> diceCount = new HashMap<>();

    public Yatzy(int die1, int die2, int die3, int die4, int die5) {
        int[] dice = {die1, die2, die3, die4, die5};

        for (int die : dice) {
            assertValidDieValue(die);
            diceCount.merge(die, 1, Integer::sum);
        }
    }

    public int yatzyScore() {
        return allDiceHaveTheSameValue() ? YATZY_SCORE : 0;
    }

    public int chanceScore() {
        return totalDiceSum();
    }

    public int onesScore() {
        return scoreForValue(1);
    }

    public int twosScore() {
        return scoreForValue(2);
    }

    public int threesScore() {
        return scoreForValue(3);
    }

    public int foursScore() {
        return scoreForValue(4);
    }

    public int fivesScore() {
        return scoreForValue(5);
    }

    public int sixesScore() {
        return scoreForValue(6);
    }

    public int pairScore() {
        return nOfAKindScore(2);
    }

    public int threeOfAKindScore() {
        return nOfAKindScore(3);
    }

    public int fourOfAKindScore() {
        return nOfAKindScore(4);
    }

    public int twoPairsScore() {
        List<Integer> pairs = diceCount.entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .toList();

        if (pairs.size() < 2) {
            return 0;
        }

        return pairs.stream().mapToInt(Integer::intValue).sum() * 2;
    }

    public int smallStraightScore() {
        return allDiceAreDifferent() && totalDiceSum() == SMALL_STRAIGHT_SCORE ? SMALL_STRAIGHT_SCORE : 0;
    }

    public int largeStraightScore() {
        return allDiceAreDifferent() && totalDiceSum() == LARGE_STRAIGHT_SCORE ? LARGE_STRAIGHT_SCORE : 0;
    }

    public int fullHouseScore() {
        return diceCount.containsValue(3) && diceCount.containsValue(2) ? totalDiceSum() : 0;
    }


    private int totalDiceSum() {
        return diceCount.entrySet().stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();
    }

    private int scoreForValue(int value) {
        return diceCount.getOrDefault(value, 0) * value;
    }

    /**
     * Calculates the score for N of a kind.
     * Finds the highest dice value that appears at least N times and returns the product of that value and N.
     * If no dice value meets the criteria, returns 0.
     *
     * @param n the number of same dice required to score
     * @return the score for N of a kind
     */
    private int nOfAKindScore(int n) {
        for (int diceValue = 6; diceValue > 0; diceValue--) {
            if (diceCount.getOrDefault(diceValue, 0) >= n) {
                return diceValue * n;
            }
        }
        return 0;
    }

    private boolean allDiceHaveTheSameValue() {
        return diceCount.size() == 1;
    }

    private boolean allDiceAreDifferent() {
        return diceCount.size() == 5;
    }

    private void assertValidDieValue(int die) {
        if (die < 1 || die > 6) {
            throw new IllegalArgumentException("dice values must be within 1 to 6");
        }
    }
}