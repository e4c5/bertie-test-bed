package com.raditha.bertie.testbed.cards;

import com.raditha.bertie.testbed.cards.model.Card;
import com.raditha.bertie.testbed.cards.model.Deck;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CardModelTest {

    @Test
    void testCardCreation() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        assertEquals(Card.Suit.HEARTS, card.getSuit());
        assertEquals(Card.Rank.ACE, card.getRank());
        assertEquals(1, card.getValue());
    }

    @Test
    void testCardIsRed() {
        Card hearts = new Card(Card.Suit.HEARTS, Card.Rank.KING);
        Card diamonds = new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN);
        Card clubs = new Card(Card.Suit.CLUBS, Card.Rank.JACK);
        Card spades = new Card(Card.Suit.SPADES, Card.Rank.TEN);

        assertTrue(hearts.isRed());
        assertTrue(diamonds.isRed());
        assertFalse(clubs.isRed());
        assertFalse(spades.isRed());
    }

    @Test
    void testCardIsFaceCard() {
        Card jack = new Card(Card.Suit.HEARTS, Card.Rank.JACK);
        Card queen = new Card(Card.Suit.HEARTS, Card.Rank.QUEEN);
        Card king = new Card(Card.Suit.HEARTS, Card.Rank.KING);
        Card ten = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        Card ace = new Card(Card.Suit.HEARTS, Card.Rank.ACE);

        assertTrue(jack.isFaceCard());
        assertTrue(queen.isFaceCard());
        assertTrue(king.isFaceCard());
        assertFalse(ten.isFaceCard());
        assertFalse(ace.isFaceCard());
    }

    @Test
    void testDeckInitialization() {
        Deck deck = new Deck();
        assertEquals(52, deck.remaining());
        assertFalse(deck.isEmpty());
    }

    @Test
    void testDeckDraw() {
        Deck deck = new Deck();
        Card card = deck.draw();
        assertNotNull(card);
        assertEquals(51, deck.remaining());
    }

    @Test
    void testDeckDrawMultiple() {
        Deck deck = new Deck();
        List<Card> cards = deck.drawMultiple(5);
        assertEquals(5, cards.size());
        assertEquals(47, deck.remaining());
    }

    @Test
    void testDeckDrawAll() {
        Deck deck = new Deck();
        Set<String> uniqueCards = new HashSet<>();
        while (!deck.isEmpty()) {
            Card card = deck.draw();
            uniqueCards.add(card.toString());
        }
        assertEquals(52, uniqueCards.size());
        assertTrue(deck.isEmpty());
        assertNull(deck.draw());
    }

    @Test
    void testDeckReset() {
        Deck deck = new Deck();
        deck.drawMultiple(10);
        assertEquals(42, deck.remaining());
        deck.reset();
        assertEquals(52, deck.remaining());
    }

    @Test
    void testDeckShuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        assertEquals(52, deck.remaining());
    }
}
