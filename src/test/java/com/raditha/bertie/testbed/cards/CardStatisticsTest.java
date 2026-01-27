package com.raditha.bertie.testbed.cards;

import com.raditha.bertie.testbed.cards.model.Card;
import com.raditha.bertie.testbed.cards.service.CachedCardStatistics;
import com.raditha.bertie.testbed.cards.service.DirectCardStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardStatisticsTest {

    private CachedCardStatistics cachedStats;
    private DirectCardStatistics directStats;
    private List<Card> testHand;

    @BeforeEach
    void setUp() {
        cachedStats = new CachedCardStatistics();
        directStats = new DirectCardStatistics();
        testHand = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                new Card(Card.Suit.SPADES, Card.Rank.TWO),
                new Card(Card.Suit.HEARTS, Card.Rank.SEVEN)
        );
    }

    @Test
    void testCachedCountFaceCards() {
        int count = cachedStats.countFaceCards(testHand);
        assertEquals(2, count); // King and Jack
    }

    @Test
    void testDirectCountFaceCards() {
        int count = directStats.countFaceCards(testHand);
        assertEquals(2, count); // King and Jack
    }

    @Test
    void testCachedCountRedCards() {
        int count = cachedStats.countRedCards(testHand);
        assertEquals(3, count); // Hearts King, Diamonds Jack, Hearts Seven
    }

    @Test
    void testDirectCountRedCards() {
        int count = directStats.countRedCards(testHand);
        assertEquals(3, count); // Hearts King, Diamonds Jack, Hearts Seven
    }

    @Test
    void testCachedCalculateTotal() {
        int total = cachedStats.calculateTotal(testHand);
        assertEquals(38, total); // 13 + 5 + 11 + 2 + 7
    }

    @Test
    void testDirectCalculateTotal() {
        int total = directStats.calculateTotal(testHand);
        assertEquals(38, total); // 13 + 5 + 11 + 2 + 7
    }

    @Test
    void testCachedFindHighest() {
        Card highest = cachedStats.findHighest(testHand);
        assertNotNull(highest);
        assertEquals(Card.Rank.KING, highest.getRank());
    }

    @Test
    void testDirectFindHighest() {
        Card highest = directStats.findHighest(testHand);
        assertNotNull(highest);
        assertEquals(Card.Rank.KING, highest.getRank());
    }

    @Test
    void testCachedAndDirectProduceSameResults() {
        assertEquals(cachedStats.countFaceCards(testHand), directStats.countFaceCards(testHand));
        assertEquals(cachedStats.countRedCards(testHand), directStats.countRedCards(testHand));
        assertEquals(cachedStats.calculateTotal(testHand), directStats.calculateTotal(testHand));
        assertEquals(cachedStats.findHighest(testHand).getRank(), directStats.findHighest(testHand).getRank());
    }

    @Test
    void testEmptyHand() {
        List<Card> emptyHand = Collections.emptyList();
        assertEquals(0, cachedStats.countFaceCards(emptyHand));
        assertEquals(0, directStats.countFaceCards(emptyHand));
        assertEquals(0, cachedStats.countRedCards(emptyHand));
        assertEquals(0, directStats.countRedCards(emptyHand));
        assertEquals(0, cachedStats.calculateTotal(emptyHand));
        assertEquals(0, directStats.calculateTotal(emptyHand));
        assertNull(cachedStats.findHighest(emptyHand));
        assertNull(directStats.findHighest(emptyHand));
    }

    @Test
    void testAllFaceCards() {
        List<Card> allFaces = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.JACK),
                new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN),
                new Card(Card.Suit.CLUBS, Card.Rank.KING)
        );
        assertEquals(3, cachedStats.countFaceCards(allFaces));
        assertEquals(3, directStats.countFaceCards(allFaces));
    }

    @Test
    void testAllRedCards() {
        List<Card> allRed = Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.ACE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.TWO),
                new Card(Card.Suit.HEARTS, Card.Rank.THREE)
        );
        assertEquals(3, cachedStats.countRedCards(allRed));
        assertEquals(3, directStats.countRedCards(allRed));
    }
}
