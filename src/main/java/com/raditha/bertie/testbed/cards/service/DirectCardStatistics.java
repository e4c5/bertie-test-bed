package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.model.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Card statistics service without caching. Does NOT use @Cacheable annotation.
 * Contains duplicate code with CachedCardStatistics, but annotation mismatch
 * should trigger SafetyValidator warning (INCOMPATIBLE annotations).
 */
public class DirectCardStatistics {

    /**
     * Counts face cards in hand.
     * DUPLICATE CODE BLOCK - same as CachedCardStatistics.countFaceCards
     * BUT does NOT have @Cacheable annotation while CachedCardStatistics does.
     */
    public int countFaceCards(List<Card> hand) {
        int count = 0;
        for (Card card : hand) {
            if (card.isFaceCard()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts red cards in hand.
     * DUPLICATE CODE BLOCK - same as CachedCardStatistics.countRedCards
     * BUT does NOT have @Cacheable annotation while CachedCardStatistics does.
     */
    public int countRedCards(List<Card> hand) {
        int count = 0;
        for (Card card : hand) {
            if (card.isRed()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Groups cards by suit.
     * DUPLICATE CODE BLOCK - same as CachedCardStatistics.groupBySuit
     * BUT does NOT have @Cacheable annotation while CachedCardStatistics does.
     */
    public Map<Card.Suit, Integer> groupBySuit(List<Card> hand) {
        Map<Card.Suit, Integer> groups = new HashMap<>();
        for (Card card : hand) {
            Card.Suit suit = card.getSuit();
            groups.put(suit, groups.getOrDefault(suit, 0) + 1);
        }
        return groups;
    }

    /**
     * Finds highest value card.
     * DUPLICATE CODE BLOCK - same as CachedCardStatistics.findHighestCard
     */
    public Card findHighestCard(List<Card> hand) {
        Card highest = null;
        int highestValue = -1;
        for (Card card : hand) {
            if (card.getValue() > highestValue) {
                highestValue = card.getValue();
                highest = card;
            }
        }
        return highest;
    }

    /**
     * Calculates average card value.
     */
    public double calculateAverageValue(List<Card> hand) {
        if (hand.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }
        return (double) total / hand.size();
    }
}
