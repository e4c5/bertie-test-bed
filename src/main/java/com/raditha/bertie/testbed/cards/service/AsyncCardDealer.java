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

    private final Deck deck;

    public AsyncCardDealer() {
        this.deck = new Deck();
    }

    /**
     * Deals cards to players.
     * DUPLICATE CODE BLOCK - same as SyncCardDealer.dealToPlayers
     * BUT has @Async annotation while SyncCardDealer does not.
     */
    @Async
    public List<List<Card>> dealToPlayers(int playerCount, int cardsPerPlayer) {
        List<List<Card>> hands = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            List<Card> hand = new ArrayList<>();
            for (int j = 0; j < cardsPerPlayer; j++) {
                Card card = deck.draw();
                if (card != null) {
                    hand.add(card);
                }
            }
            hands.add(hand);
        }
        return hands;
    }

    /**
     * Shuffles and deals a fresh hand.
     * DUPLICATE CODE BLOCK - same as SyncCardDealer.shuffleAndDeal
     * BUT has @Async annotation while SyncCardDealer does not.
     */
    @Async
    public List<Card> shuffleAndDeal(int cardCount) {
        deck.reset();
        deck.shuffle();
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < cardCount; i++) {
            Card card = deck.draw();
            if (card != null) {
                hand.add(card);
            }
        }
        return hand;
    }

    /**
     * Calculates total deck value remaining.
     * DUPLICATE CODE BLOCK - same as SyncCardDealer.calculateRemainingValue
     */
    @Async
    public int calculateRemainingValue() {
        int total = 0;
        Deck tempDeck = new Deck();
        while (!tempDeck.isEmpty()) {
            Card card = tempDeck.draw();
            if (card != null) {
                total += card.getValue();
            }
        }
        return total;
    }
}
