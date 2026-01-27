package com.raditha.bertie.testbed.cards;

import com.raditha.bertie.testbed.cards.model.Card;
import com.raditha.bertie.testbed.cards.service.PokerHandEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandEvaluatorTest {

    private PokerHandEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new PokerHandEvaluator();
    }

    @Test
    void testCalculateHandValue() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
                new Card(Card.Suit.CLUBS, Card.Rank.SEVEN)
        );
        int value = evaluator.calculateHandValue(hand);
        assertEquals(15, value); // 5 + 3 + 7 = 15
    }

    @Test
    void testCalculateHandValueWithFaceCards() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN),
                new Card(Card.Suit.CLUBS, Card.Rank.JACK)
        );
        int value = evaluator.calculateHandValue(hand);
        assertEquals(30, value); // Face cards capped at 10 each
    }

    @Test
    void testCountFaceCards() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE),
                new Card(Card.Suit.CLUBS, Card.Rank.JACK),
                new Card(Card.Suit.SPADES, Card.Rank.TWO)
        );
        int count = evaluator.countFaceCards(hand);
        assertEquals(2, count);
    }

    @Test
    void testCountFaceCardsNone() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.THREE)
        );
        int count = evaluator.countFaceCards(hand);
        assertEquals(0, count);
    }

    @Test
    void testFindRedCards() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                new Card(Card.Suit.SPADES, Card.Rank.TWO)
        );
        List<Card> redCards = evaluator.findRedCards(hand);
        assertEquals(2, redCards.size());
        assertTrue(redCards.stream().allMatch(Card::isRed));
    }

    @Test
    void testHasPairTrue() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.CLUBS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)
        );
        assertTrue(evaluator.hasPair(hand));
    }

    @Test
    void testHasPairFalse() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.CLUBS, Card.Rank.QUEEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)
        );
        assertFalse(evaluator.hasPair(hand));
    }
}
