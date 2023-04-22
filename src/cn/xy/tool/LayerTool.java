package cn.xy.tool;

import cn.xy.Card;
import cn.xy.Cell;
import cn.xy.Layer;

import java.util.Random;

public class LayerTool {

    public static Layer createLayer(Integer rowNum,Integer colNum){
        Layer layer = null;

        try {
            layer = new Layer(rowNum, colNum);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }

        int size = layer.getCapacity() - 3*(new Random().nextInt(3)+1);
        layer.setSize(size);
        Card[] cards = CardTool.createCards(size);


        int index = 0;
        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                System.out.println(row+ "-" +col);

                Cell cell = new Cell();
                cell.setLayer(layer);
                if (index < size){
                    Card card = cards[index++];
                    cell.setPopulated(true);
                    cell.setCard(card);
                    card.setCell(cell);
                }else {
                    cell.setPopulated(false);
                }
                cells[row][col] = cell;
            }
        }
        return layer;
    }
}
