package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.model.Card;

import java.util.List;

/**
 * Card statistics service without caching. Does NOT use @Cacheable annotation.
 * Contains duplicate code with CachedCardStatistics, but annotation mismatch
 * should trigger SafetyValidator warning (INCOMPATIBLE annotations).
 */
public class DirectCardStatistics {

    /**
     * Counts face cards in hand.
     * DUPLICATE with CachedCardStatistics.countFaceCards - but NO @Cacheable here!
     */
    public int countFaceCards(List<Card> hand) {
        int count = 0;
        for (Card card : hand) {
            if (card.isFaceCard()) {
                count = count + 1;
            }
        }
        System.out.println("Face card count: " + count);
        return count;
    }

    /**
     * Counts red cards in hand.
     * DUPLICATE with CachedCardStatistics.countRedCards - but NO @Cacheable here!
     */
    public int countRedCards(List<Card> hand) {
        int count = 0;
        for (Card card : hand) {
            if (card.isRed()) {
                count = count + 1;
            }
        }
        System.out.println("Red card count: " + count);
        return count;
    }

    /**
     * Calculates total hand value.
     * DUPLICATE with CachedCardStatistics.calculateTotal - but NO @Cacheable here!
     */
    public int calculateTotal(List<Card> hand) {
        int total = 0;
        for (Card card : hand) {
            int value = card.getValue();
            total = total + value;
        }
        System.out.println("Total value: " + total);
        return total;
    }

    /**
     * Finds the highest card.
     * DUPLICATE with CachedCardStatistics.findHighest - but NO @Cacheable here!
     */
    public Card findHighest(List<Card> hand) {
        Card highest = null;
        int maxValue = 0;
        for (Card card : hand) {
            int value = card.getValue();
            if (value > maxValue) {
                maxValue = value;
                highest = card;
            }
        }
        System.out.println("Highest card value: " + maxValue);
        return highest;
    }
}
