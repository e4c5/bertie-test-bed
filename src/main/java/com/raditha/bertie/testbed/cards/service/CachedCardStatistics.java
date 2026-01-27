package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.annotation.Cacheable;
import com.raditha.bertie.testbed.cards.model.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Card statistics service with caching. Uses @Cacheable annotation.
 * Contains duplicate code with DirectCardStatistics, but annotation mismatch
 * should trigger SafetyValidator warning (INCOMPATIBLE annotations).
 */
public class CachedCardStatistics {

    /**
     * Counts face cards in hand.
     * DUPLICATE CODE BLOCK - same as DirectCardStatistics.countFaceCards
     * BUT has @Cacheable annotation while DirectCardStatistics does not.
     */
    @Cacheable("faceCardCount")
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
     * DUPLICATE CODE BLOCK - same as DirectCardStatistics.countRedCards
     * BUT has @Cacheable annotation while DirectCardStatistics does not.
     */
    @Cacheable("redCardCount")
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
     * DUPLICATE CODE BLOCK - same as DirectCardStatistics.groupBySuit
     * BUT has @Cacheable annotation while DirectCardStatistics does not.
     */
    @Cacheable("cardsBySuit")
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
     * DUPLICATE CODE BLOCK - same as DirectCardStatistics.findHighestCard
     */
    @Cacheable("highestCard")
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
}
