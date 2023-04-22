package cn.xy;

import cn.xy.tool.MapTool;
import cn.xy.tool.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.TimerTask;

public class YangMain extends JFrame{

    public static Map map = MapTool.createMap(3);
    private static Trough trough = new Trough();

    public YangMain() {

        init();


        buildMap();

        MapTool.checkAllCardsCoveredAndSetCardGary();


        Image bg = Toolkit.getDefaultToolkit().getImage("res/背景.jpg");
        Component bgt = new Component() {
            @Override
            public void paint(Graphics g) {
                g.drawImage(bg,0,0, 460, 753,null);
            }
        };
        bgt.setBounds(0,0,460,753);
        this.getContentPane().add(bgt);


        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 40,40 );

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MusicPlayer().beginPlay();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
    
    public void buildLayer(Layer layer){

        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                if (!cell.isPopulated()){
                    continue;
                }
                Card card = cell.getCard();

                int x = col * 59 + layer.getOffsetX();
                int y = row * 66 + layer.getOffsetY();
                card.setBounds(x,y,59,66);
                this.getContentPane().add(card);
            }
        }
    }

    public static void addCardToTrough(Card card){
        trough.addCard(card);
    }

    public void buildMap(){
        List<Layer> list = map.getLayers();
        for (int i = 0; i < list.size(); i++) {
            buildLayer(list.get(i));
        }
    }


    public static void checkWin(){
        List<Layer> layers = map.getLayers();
        System.out.println("卡牌剩余情况：");
        int sum = 0;
        for (Layer layer : layers){
            System.out.println(layer.getPopulatedCellNums() + "/" + layer.getSize() + " ");
            sum += layer.getPopulatedCellNums();
        }
        System.out.println("\n共剩余卡牌数量："+sum+"\n");

        if (sum == 0){
            JOptionPane.showMessageDialog(null,"加入果群成功！！");
            System.exit(0);
        }
    }


    public void init(){
        this.setTitle("果了个果");
        this.setSize(480, 800);
        this.setResizable(false);

        this.setLayout(null);
        this.setBounds(0, 0, 480, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new YangMain();
    }
}
