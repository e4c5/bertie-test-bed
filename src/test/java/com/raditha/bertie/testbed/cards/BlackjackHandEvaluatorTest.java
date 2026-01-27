package com.raditha.bertie.testbed.cards;

import com.raditha.bertie.testbed.cards.model.Card;
import com.raditha.bertie.testbed.cards.service.BlackjackHandEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackHandEvaluatorTest {

    private BlackjackHandEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new BlackjackHandEvaluator();
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
    void testCalculateBlackjackValueSimple() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN)
        );
        int value = evaluator.calculateBlackjackValue(hand);
        assertEquals(12, value);
    }

    @Test
    void testCalculateBlackjackValueWithAceHigh() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.ACE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)
        );
        int value = evaluator.calculateBlackjackValue(hand);
        assertEquals(20, value); // Ace as 11
    }

    @Test
    void testCalculateBlackjackValueWithAceLow() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.ACE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.NINE),
                new Card(Card.Suit.CLUBS, Card.Rank.FIVE)
        );
        int value = evaluator.calculateBlackjackValue(hand);
        assertEquals(15, value); // Ace drops to 1 to avoid bust
    }

    @Test
    void testIsBlackjackTrue() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.ACE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.KING)
        );
        assertTrue(evaluator.isBlackjack(hand));
    }

    @Test
    void testIsBlackjackFalseWrongValue() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.KING)
        );
        assertFalse(evaluator.isBlackjack(hand));
    }

    @Test
    void testIsBlackjackFalseTooManyCards() {
        List<Card> hand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.SEVEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN),
                new Card(Card.Suit.CLUBS, Card.Rank.SEVEN)
        );
        assertFalse(evaluator.isBlackjack(hand)); // 21 but 3 cards
    }
}
