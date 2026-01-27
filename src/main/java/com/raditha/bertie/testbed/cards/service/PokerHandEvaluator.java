package com.raditha.bertie.testbed.cards.service;

import com.raditha.bertie.testbed.cards.annotation.Transactional;
import com.raditha.bertie.testbed.cards.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Evaluates poker hands. Uses @Transactional annotation.
 * Contains duplicate code with BlackjackHandEvaluator that should be extractable
 * since both use the same @Transactional annotation.
 */
public class PokerHandEvaluator {

    /**
     * Calculates the total value of cards in hand.
     * DUPLICATE with BlackjackHandEvaluator.calculateHandValue
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
     * DUPLICATE with BlackjackHandEvaluator.countFaceCards
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
     * DUPLICATE with BlackjackHandEvaluator.findRedCards
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
     * Poker-specific: checks for pair.
     */
    @Transactional
    public boolean hasPair(List<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    return true;
                }
            }
        }
        return false;
    }
}
