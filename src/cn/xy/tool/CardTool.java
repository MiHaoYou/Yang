package cn.xy.tool;

import cn.xy.Card;

import java.util.Random;

public class CardTool {

    public static String[] cardNames = {"枇杷","柠檬","梨","椰子",
            "榴莲","橙子","洋葱","火龙果",
            "牛油果","苹果","草莓","荔枝",
            "蓝莓","西红柿","香蕉",};

    public static Random random = new Random();
    public static String generateRandowCardName(){
        int index = random.nextInt(cardNames.length);
        return cardNames[index];
    }


    public static Card[] createCards(Integer size){
        Card[] cards = new Card[size];


        for (int i = 0; i < cards.length; i=i+3) {
            String randomCardName = generateRandowCardName();

            Card card1 = new Card(randomCardName);
            Card card2 = new Card(randomCardName);
            Card card3 = new Card(randomCardName);
            cards[i] = card1;
            cards[i+1] = card2;
            cards[i+2] = card3;
        }

        System.out.println("打乱顺序~~~~~~~~~");
        for (int i = 0; i < cards.length; i++) {
            Card cardA = cards[i];
            int randomIndex = random.nextInt(cards.length);
            Card cardB = cards[randomIndex];

            Card temp = cardA;
            cards[i] = cardB;
            cards[randomIndex] = temp;
        }
        return cards;
    }
}
