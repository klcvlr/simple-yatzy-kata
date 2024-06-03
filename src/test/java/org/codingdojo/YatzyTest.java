package org.codingdojo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YatzyTest {

    @ParameterizedTest
    @DisplayName("Chance: should return the sum of all the dices")
    @CsvSource({"2,3,4,5,1,15", "3,3,4,5,1,16"})
    public void testChance(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).chanceScore());
    }

    @ParameterizedTest
    @DisplayName("Yatzy: should score 50 if all dice are the same. scores 0 otherwise")
    @CsvSource({"4,4,4,4,4,50", "6,6,6,6,6,50", "6,6,6,6,3,0"})
    public void testYatzyScores(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).yatzyScore());
    }

    @ParameterizedTest
    @DisplayName("Ones: sums all the dices of value 1")
    @CsvSource({"1,2,3,4,5,1", "1,2,1,4,5,2", "6,2,2,4,5,0", "1,2,1,1,1,4"})
    public void testOnes(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).onesScore());
    }

    @ParameterizedTest
    @DisplayName("Twos: sums all the dices of value 2")
    @CsvSource({"1,2,3,2,6,4", "2,2,2,2,2,10"})
    public void testTwos(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).twosScore());
    }

    @ParameterizedTest
    @DisplayName("Threes: sums all the dices of value 3")
    @CsvSource({"1,2,3,2,3,6", "2,3,3,3,3,12"})
    public void testThrees(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).threesScore());
    }

    @ParameterizedTest
    @DisplayName("Fours: sums all the dices of value 4")
    @CsvSource({"4,4,4,5,5,12", "4,4,5,5,5,8", "4,5,5,5,5,4"})
    public void testFours(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).foursScore());
    }

    @ParameterizedTest
    @DisplayName("Fives: sums all the dices of value 5")
    @CsvSource({"4,4,4,5,5,10", "4,4,5,5,5,15", "4,5,5,5,5,20"})
    public void testFives(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).fivesScore());
    }

    @ParameterizedTest
    @DisplayName("Sixes: sums all the dices of value 6")
    @CsvSource({"4,4,4,5,5,0", "4,4,6,5,5,6", "6,5,6,6,5,18"})
    public void testSixes(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).sixesScore());
    }

    @ParameterizedTest
    @DisplayName("Pair: Scores the value of the highest pair. Scores 0 if no pair is found")
    @CsvSource({"3,4,3,5,6,6", "5,3,3,3,5,10", "5,3,6,6,5,12"})
    public void testPair(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).pairScore());
    }

    @ParameterizedTest
    @DisplayName("Two pairs: Score the value of the two pairs. Scores 0 if there are fewer than 2 pairs")
    @CsvSource({"3,3,5,4,5,16", "3,3,5,5,5,16"})
    public void testTwoPairs(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).twoPairsScore());
    }

    @ParameterizedTest
    @DisplayName("Three of a kind: sums dice that are 3 of a kind")
    @CsvSource({"3,3,3,4,5,9", "5,3,5,4,5,15", "3,3,3,3,5,9"})
    public void testThreeOfAKind(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).threeOfAKindScore());
    }

    @ParameterizedTest
    @DisplayName("Four of a kind: sums dice that are 4 of a kind")
    @CsvSource({"3,3,3,3,5,12", "5,5,5,4,5,20", "3,3,3,3,3,12"})
    public void testFourOfAKind(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).fourOfAKindScore());
    }

    @ParameterizedTest
    @DisplayName("Small straight: scores 15")
    @CsvSource({"1,2,3,4,5,15", "2,3,4,5,1,15", "1,2,2,4,5,0"})
    public void testSmallStraight(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).smallStraightScore());
    }

    @ParameterizedTest
    @DisplayName("Large straight: scores 20")
    @CsvSource({"6,2,3,4,5,20", "2,3,4,5,6,20", "1,2,2,4,5,0"})
    public void testLargeStraight(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).largeStraightScore());
    }

    @ParameterizedTest
    @DisplayName("Full house")
    @CsvSource({"6,2,2,2,6,18", "2,3,4,5,6,0"})
    public void testFullHouse(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, new Yatzy(d1, d2, d3, d4, d5).fullHouseScore());
    }

    @ParameterizedTest
    @DisplayName("Throw IllegalArgumentException when a die value is invalid")
    @CsvSource({"1,2,3,4,7", "1,2,0,4,5", "2,6,-5,2,2"})
    void testInvalidDieValue(int d1, int d2, int d3, int d4, int d5) {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Yatzy(d1, d2, d3, d4, d5));
        assertEquals("dice values must be within 1 to 6", exception.getMessage());
    }
}