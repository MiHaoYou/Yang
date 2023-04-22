package cn.xy;


public class Layer {
    private Integer offsetX;
    private Integer offsetY;

    private Integer rowNum;
    private Integer colNum;

    private Integer capacity;

    private Integer size;

    private Layer prev;
    private Cell[][] cells = null;

    public Layer(Integer rowNum,Integer colNum) throws Exception {
        this.rowNum = rowNum;
        this.colNum = colNum;

        this.capacity = this.rowNum * this.colNum;
        if (this.capacity%3 != 0){
            throw new Exception("容量不是3的倍数");
        }

        this.cells = new Cell[this.rowNum][this.colNum];
        this.size = 0;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public void ShowCells(){
        for (int row = 0; row < cells.length ; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Card card = cells[row][col].getCard();
                if (card == null){
                    System.out.println("空");
                }else {
                    System.out.println(card.getName()+"-");
                }
            }
            System.out.println();
        }
    }

    public int getPopulatedCellNums(){
        int populateCellNums = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isPopulated()){
                    populateCellNums++;
                }
            }
        }
        return populateCellNums;
    }

    public Integer getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(Integer offsetX) {
        this.offsetX = offsetX;
    }

    public Integer getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Layer getPrev() {
        return prev;
    }

    public void setPrev(Layer prev) {
        this.prev = prev;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
