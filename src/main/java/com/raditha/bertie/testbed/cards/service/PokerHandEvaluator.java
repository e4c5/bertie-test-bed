package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.annotation.Transactional;
import com.raditha.bertie.testbed.cards.model.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Evaluates poker hands. Uses @Transactional annotation.
 * Contains duplicate code with BlackjackHandEvaluator that should be extractable
 * since both use the same @Transactional annotation.
 */
public class PokerHandEvaluator {

    /**
     * Calculates the total value of cards in hand.
     * DUPLICATE CODE BLOCK - same as BlackjackHandEvaluator.calculateHandValue
     */
    @Transactional
    public int calculateHandValue(List<Card> hand) {
        int total = 0;
        for (Card card : hand) {
            int cardValue = card.getValue();
            if (cardValue > 10) {
                cardValue = 10;
            }
            total += cardValue;
        }
        return total;
    }

    /**
     * Sorts cards by rank.
     * DUPLICATE CODE BLOCK - same as BlackjackHandEvaluator.sortByRank
     */
    @Transactional
    public List<Card> sortByRank(List<Card> hand) {
        List<Card> sorted = new ArrayList<>(hand);
        sorted.sort(Comparator.comparingInt(Card::getValue));
        return sorted;
    }

    /**
     * Counts cards by suit.
     * DUPLICATE CODE BLOCK - same as BlackjackHandEvaluator.countBySuit
     */
    @Transactional
    public Map<Card.Suit, Long> countBySuit(List<Card> hand) {
        return hand.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));
    }

    /**
     * Checks if hand has a flush (5 cards of same suit).
     */
    @Transactional
    public boolean hasFlush(List<Card> hand) {
        Map<Card.Suit, Long> suitCounts = countBySuit(hand);
        return suitCounts.values().stream().anyMatch(count -> count >= 5);
    }

    /**
     * Checks if hand has a pair.
     */
    @Transactional
    public boolean hasPair(List<Card> hand) {
        Map<Card.Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.values().stream().anyMatch(count -> count >= 2);
    }
}
