package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.annotation.Async;
import com.raditha.bertie.testbed.cards.model.Card;
import com.raditha.bertie.testbed.cards.model.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * Asynchronous card dealer. Uses @Async annotation.
 * Contains duplicate code with SyncCardDealer, but annotation mismatch
 * should trigger SafetyValidator warning (INCOMPATIBLE annotations).
 */
public class AsyncCardDealer {

    private Deck deck;

    public AsyncCardDealer() {
        this.deck = new Deck();
    }

    /**
     * Deals cards to a single player.
     * DUPLICATE with SyncCardDealer.dealCards - but @Async mismatch!
     */
    @Async
    public List<Card> dealCards(int cardCount) {
        List<Card> hand = new ArrayList<>();
        int dealt = 0;
        while (dealt < cardCount) {
            Card card = deck.draw();
            if (card != null) {
                hand.add(card);
                dealt = dealt + 1;
            } else {
                break;
            }
        }
        System.out.println("Dealt " + dealt + " cards");
        return hand;
    }

    /**
     * Resets and shuffles the deck.
     * DUPLICATE with SyncCardDealer.resetDeck - but @Async mismatch!
     */
    @Async
    public void resetDeck() {
        deck = new Deck();
        deck.shuffle();
        int remaining = deck.remaining();
        System.out.println("Deck reset with " + remaining + " cards");
        System.out.println("Ready to deal");
    }

    /**
     * Counts remaining cards by color.
     * DUPLICATE with SyncCardDealer.countByColor - but @Async mismatch!
     */
    @Async
    public int countRedRemaining() {
        Deck tempDeck = new Deck();
        int redCount = 0;
        while (!tempDeck.isEmpty()) {
            Card card = tempDeck.draw();
            if (card != null && card.isRed()) {
                redCount = redCount + 1;
            }
        }
        System.out.println("Red cards: " + redCount);
        return redCount;
    }
}
