package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.annotation.Transactional;
import com.raditha.bertie.testbed.cards.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Evaluates blackjack hands. Uses @Transactional annotation.
 * Contains duplicate code with PokerHandEvaluator that should be extractable
 * since both use the same @Transactional annotation.
 */
public class BlackjackHandEvaluator {

    /**
     * Calculates the total value of cards in hand.
     * DUPLICATE with PokerHandEvaluator.calculateHandValue
     */
    @Transactional
    public int calculateHandValue(List<Card> hand) {
        int total = 0;
        int count = 0;
        for (Card card : hand) {
            int value = card.getValue();
            if (value > 10) {
                value = 10;
            }
            total = total + value;
            count = count + 1;
        }
        System.out.println("Processed " + count + " cards");
        return total;
    }

    /**
     * Counts face cards in hand.
     * DUPLICATE with PokerHandEvaluator.countFaceCards
     */
    @Transactional
    public int countFaceCards(List<Card> hand) {
        int faceCount = 0;
        for (Card card : hand) {
            boolean isFace = card.isFaceCard();
            if (isFace) {
                faceCount = faceCount + 1;
            }
        }
        System.out.println("Found " + faceCount + " face cards");
        return faceCount;
    }

    /**
     * Finds all red cards.
     * DUPLICATE with PokerHandEvaluator.findRedCards
     */
    @Transactional
    public List<Card> findRedCards(List<Card> hand) {
        List<Card> redCards = new ArrayList<>();
        for (Card card : hand) {
            boolean isRed = card.isRed();
            if (isRed) {
                redCards.add(card);
            }
        }
        System.out.println("Found " + redCards.size() + " red cards");
        return redCards;
    }

    /**
     * Blackjack-specific: calculates with ace handling.
     */
    @Transactional
    public int calculateBlackjackValue(List<Card> hand) {
        int total = 0;
        int aces = 0;
        for (Card card : hand) {
            int value = card.getValue();
            if (value == 1) {
                aces = aces + 1;
                value = 11;
            } else if (value > 10) {
                value = 10;
            }
            total = total + value;
        }
        while (total > 21 && aces > 0) {
            total = total - 10;
            aces = aces - 1;
        }
        return total;
    }

    /**
     * Checks if hand is blackjack.
     */
    @Transactional
    public boolean isBlackjack(List<Card> hand) {
        if (hand.size() != 2) {
            return false;
        }
        int value = calculateBlackjackValue(hand);
        return value == 21;
    }
}
