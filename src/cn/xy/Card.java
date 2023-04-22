package cn.xy;

import cn.xy.tool.MapTool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends Component {
    private String name;
    private boolean isGray;
    private Image image;
    private Image grayImage;

    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    private Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Card(String name){
        this.name = name;
        this.image = Toolkit.getDefaultToolkit().getImage("res/"+name+".png");
        this.grayImage = Toolkit.getDefaultToolkit().getImage("res/"+name+"_灰.png");
        this.width = 59;
        this.height = 66;
        this.x = 0;
        this.y = 0;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点一下");
                Card card = (Card) e.getSource();

                if (card.isGray()){

                    return;
                }else {

                    if (cell == null){
                        card.getParent().remove(card);
                        return;
                    }
                    cell.setPopulated(false);
                    cell.setCard(card);

                    YangMain.addCardToTrough(card);


                    MapTool.checkAllCardsCoveredAndSetCardGary();


                    MouseListener[] mouseListeners = card.getMouseListeners();
                    for(MouseListener listener : mouseListeners){
                        card.removeMouseListener(listener);
                    }
                }
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        if (this.isGray()) {
            g.drawImage(this.grayImage, x, y, null);
        } else {
            g.drawImage(this.image, x, y, null);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public boolean isGray() {
        return isGray;
    }

    public void setGray(boolean gray) {
        isGray = gray;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getGrayImage() {
        return grayImage;
    }

    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }
}
