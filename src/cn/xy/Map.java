package cn.xy;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private Integer layerHeight;

    private List<Layer> layers = new ArrayList<>();

    public Integer getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(Integer layerHeight) {
        this.layerHeight = layerHeight;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
