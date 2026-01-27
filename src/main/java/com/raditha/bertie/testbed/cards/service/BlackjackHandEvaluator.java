package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.annotation.Transactional;
import com.raditha.bertie.testbed.cards.model.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Evaluates blackjack hands. Uses @Transactional annotation.
 * Contains duplicate code with PokerHandEvaluator that should be extractable
 * since both use the same @Transactional annotation.
 */
public class BlackjackHandEvaluator {

    /**
     * Calculates the total value of cards in hand.
     * DUPLICATE CODE BLOCK - same as PokerHandEvaluator.calculateHandValue
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
     * DUPLICATE CODE BLOCK - same as PokerHandEvaluator.sortByRank
     */
    @Transactional
    public List<Card> sortByRank(List<Card> hand) {
        List<Card> sorted = new ArrayList<>(hand);
        sorted.sort(Comparator.comparingInt(Card::getValue));
        return sorted;
    }

    /**
     * Counts cards by suit.
     * DUPLICATE CODE BLOCK - same as PokerHandEvaluator.countBySuit
     */
    @Transactional
    public Map<Card.Suit, Long> countBySuit(List<Card> hand) {
        return hand.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));
    }

    /**
     * Calculates blackjack-specific hand value (Aces can be 1 or 11).
     */
    @Transactional
    public int calculateBlackjackValue(List<Card> hand) {
        int total = 0;
        int aces = 0;

        for (Card card : hand) {
            int value = card.getValue();
            if (value == 1) {
                aces++;
                value = 11;
            } else if (value > 10) {
                value = 10;
            }
            total += value;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    /**
     * Checks if hand is a blackjack (21 with 2 cards).
     */
    @Transactional
    public boolean isBlackjack(List<Card> hand) {
        return hand.size() == 2 && calculateBlackjackValue(hand) == 21;
    }

    /**
     * Checks if hand is busted (over 21).
     */
    @Transactional
    public boolean isBusted(List<Card> hand) {
        return calculateBlackjackValue(hand) > 21;
    }
}
