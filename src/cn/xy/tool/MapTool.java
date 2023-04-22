package cn.xy.tool;

import cn.xy.Card;
import cn.xy.Cell;
import cn.xy.Layer;
import cn.xy.Map;
import cn.xy.YangMain;

import java.awt.*;
import java.util.List;


public class MapTool {

    public static Map createMap(Integer layerHeight){
        Map map = new Map();
        map.setLayerHeight(layerHeight);

        Layer layer1 = LayerTool.createLayer(5,6);
        Layer layer2 = LayerTool.createLayer(6,6);
        Layer layer3 = LayerTool.createLayer(6,7);



        layer3.setPrev(layer2);
        layer2.setPrev(layer1);
        layer1.setPrev(null);

        layer1.setOffsetX(59);
        layer1.setOffsetY(99);

        layer2.setOffsetX(30);
        layer2.setOffsetY(66);

        layer3.setOffsetX(30);
        layer3.setOffsetY(99);

        map.getLayers().add(layer1);
        map.getLayers().add(layer2);
        map.getLayers().add(layer3);
        return map;
    }


    public static boolean checkCardCoveredByLayer(Card card,Layer layer){
        Cell[][] cells = layer.getCells();

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                Cell cell = cells[row][col];
                if (cell.isPopulated()){

                    Rectangle temp = cell.getCard().getBounds();
                    Rectangle rect = card.getBounds();
                    if (rect.intersects(temp)){

                        return true;
                    }
                }
            }
        }

        if (layer.getPrev()!=null){
            return checkCardCoveredByLayer(card,layer.getPrev());
        }else {
            return false;
        }
    }


    public static void checkAllCardsCoveredAndSetCardGary(){
        List<Layer> layers = YangMain.map.getLayers();

        for (int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            Cell[][] cells = layer.getCells();

            for (int row = 0; row < cells.length; row++) {
                for (int col = 0; col < cells[row].length; col++) {
                    Cell cell = cells[row][col];
                    if (cell.isPopulated()){
                        Card card = cell.getCard();
                        boolean result = MapTool.checkCardCoveredByLayer(card,layer.getPrev());
                        card.setGray(result);
                    }
                }
            }
        }
    }
}
