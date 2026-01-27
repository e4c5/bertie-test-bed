package com.raditha.bertie.testbed.cards.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of playing cards.
 */
public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public List<Card> drawMultiple(int count) {
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < count && !cards.isEmpty(); i++) {
            drawn.add(draw());
        }
        return drawn;
    }

    public int remaining() {
        return cards.size();
    }

    public void reset() {
        initialize();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
