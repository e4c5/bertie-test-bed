package com.raditha.bertie.testbed.cards;

import com.raditha.bertie.testbed.cards.model.Card;
import com.raditha.bertie.testbed.cards.service.AsyncCardDealer;
import com.raditha.bertie.testbed.cards.service.SyncCardDealer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDealerTest {

    @Test
    void testAsyncDealCards() {
        AsyncCardDealer dealer = new AsyncCardDealer();
        List<Card> hand = dealer.dealCards(5);
        assertEquals(5, hand.size());
        assertNotNull(hand.get(0));
    }

    @Test
    void testSyncDealCards() {
        SyncCardDealer dealer = new SyncCardDealer();
        List<Card> hand = dealer.dealCards(5);
        assertEquals(5, hand.size());
        assertNotNull(hand.get(0));
    }

    @Test
    void testAsyncDealCardsMoreThanAvailable() {
        AsyncCardDealer dealer = new AsyncCardDealer();
        List<Card> hand = dealer.dealCards(60);
        assertEquals(52, hand.size()); // Only 52 cards in deck
    }

    @Test
    void testSyncDealCardsMoreThanAvailable() {
        SyncCardDealer dealer = new SyncCardDealer();
        List<Card> hand = dealer.dealCards(60);
        assertEquals(52, hand.size()); // Only 52 cards in deck
    }

    @Test
    void testAsyncResetDeck() {
        AsyncCardDealer dealer = new AsyncCardDealer();
        dealer.dealCards(10);
        assertDoesNotThrow(() -> dealer.resetDeck());
        List<Card> hand = dealer.dealCards(52);
        assertEquals(52, hand.size());
    }

    @Test
    void testSyncResetDeck() {
        SyncCardDealer dealer = new SyncCardDealer();
        dealer.dealCards(10);
        assertDoesNotThrow(() -> dealer.resetDeck());
        List<Card> hand = dealer.dealCards(52);
        assertEquals(52, hand.size());
    }

    @Test
    void testAsyncCountRedRemaining() {
        AsyncCardDealer dealer = new AsyncCardDealer();
        int redCount = dealer.countRedRemaining();
        assertEquals(26, redCount); // Half the deck is red
    }

    @Test
    void testSyncCountRedRemaining() {
        SyncCardDealer dealer = new SyncCardDealer();
        int redCount = dealer.countRedRemaining();
        assertEquals(26, redCount); // Half the deck is red
    }

    @Test
    void testAsyncAndSyncProduceSameResults() {
        AsyncCardDealer asyncDealer = new AsyncCardDealer();
        SyncCardDealer syncDealer = new SyncCardDealer();

        // Both should count same red cards
        assertEquals(asyncDealer.countRedRemaining(), syncDealer.countRedRemaining());
    }
}
