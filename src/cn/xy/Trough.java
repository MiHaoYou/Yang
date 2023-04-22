package cn.xy;

import javax.swing.*;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;


public class Trough {

    private static List<Card> cards = new ArrayList<>();
    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


    public void deleteByCardName(String name){
        Iterator<Card> it = cards.iterator();
        while (it.hasNext()){
            Card card = it.next();
            if (card.getName().equals(name)){
                card.getParent().remove(card);
                it.remove();
            }
        }
    }

    public void addCard(Card card){
        cards.add(card);

        cards.sort(Comparator.comparing(Card::getName));

        Map<String,List<Card>> map =
                cards.stream().collect(Collectors.groupingBy(Card::getName));
        Set<String> keys = map.keySet();
        for (String key : keys){
            List<Card> cards = map.get(key);
            if (cards.size() == 3){
                deleteByCardName(key);
                break;
            }
        }
        paint();
        checkGameOver();
    }

    public void paint(){
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int x = 10 + i*card.getWidth()+10;
            card.setBounds(x,600,59,66);
        }
    }

    public void checkGameOver(){
        if (cards.size()>=7){
            JOptionPane.showMessageDialog(null,"游戏结束");
            System.exit(0);
        }else if(cards.size() == 0){
            YangMain.checkWin();
        }
    }
}
