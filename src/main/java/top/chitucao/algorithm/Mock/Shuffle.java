package top.chitucao.algorithm.Mock;

import java.util.Random;

/**
 * @author chitucao
 * @since 2022/8/15 13:11
 * 洗牌
 */
public class Shuffle {

    class Card {

        String suit;
        String num;

        public Card(String suit, String num) {
            this.suit = suit;
            this.num = num;
        }

        @Override
        public String toString() {
            return suit + num;
        }
    }

    Card[] cards;

    public Shuffle() {
        String[] suit = {"方块", "梅花", "红桃", "黑桃"};
        String[] num = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        cards = new Card[52];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(suit[i], num[j]);
                cards[i * 13 + j] = card;
                System.out.print(card);
                if(j == 12){
                    System.out.println();
                }
            }
        }
    }

    // 洗牌
    public void shuffle() {
        Random r = new Random();
        for (int i = 0; i < 52; i++) {
            swap(cards, i, r.nextInt(52));
        }
    }

    // 发牌
    public void deal() {
        for (int i = 0; i < 52; i++) {
            if (i % 13 == 0) {
                System.out.println();
            }
            System.out.print(cards[i]);
        }
    }


    private void swap(Card[] cards, int p, int q) {
        Card temp = cards[p];
        cards[p] = cards[q];
        cards[q] = temp;
    }

    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle();
        shuffle.shuffle();
        shuffle.deal();
    }

}
