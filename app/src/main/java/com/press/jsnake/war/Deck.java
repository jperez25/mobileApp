package com.press.jsnake.war;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> cards;
    public Deck(){
        cards = new LinkedList<>();

    }
    public void fill() {
        cards = new LinkedList<>();
        for(int i = 2; i < 15; i++) {
            cards.add(new Card(i, Card.HEARTS));
            cards.add(new Card(i, Card.SPADES));
            cards.add(new Card(i, Card.DIAMONDS));
            cards.add(new Card(i, Card.CLUBS));
        }
    }
    public boolean isEmpty() {
        if (cards.size() <= 0) {
            return true;
        }
        return false;
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }
    public Card draw(){
        return cards.pop();
    }
    public int size() {
        return cards.size();
    }
    public void add(Card card) {
        cards.addLast(card);
    }
}
