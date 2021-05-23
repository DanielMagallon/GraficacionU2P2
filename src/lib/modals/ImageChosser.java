package lib.modals;

import lib.staticlass.AppProperties;
import main.Run;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ImageChosser extends JDialog
{
    public ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
    private JPanel panelImages;

    private BufferedImage bufferedImage;

    public ImageChosser(JFrame frame) {
        super(frame,true);
        getContentPane().setLayout(new BorderLayout());
        panelImages = new JPanel();
        getContentPane().add(new JScrollPane(panelImages));
        panelImages.setPreferredSize(new Dimension(600,2000));

        JPanel btns = new JPanel(new FlowLayout());
        getContentPane().add(btns,"South");
        JButton btnApply = new JButton("Aplicar (doble click imagen)");
        btnApply.addActionListener(a->{
            this.dispose();
        });
        JButton btnRef = new JButton("Refrescar");
        btnRef.addActionListener(a->refresh());
        btns.add(btnApply);
        btns.add(btnRef);

        setSize(700,700);

        setLocationRelativeTo(null);
    }

    private void run() {
        panelImages.setLayout(null);
        int x=10,y=10;
        int ind=0;
        for(BufferedImage img : bufferedImages)
        {
            ImageIcon i = new ImageIcon(img.getScaledInstance(200,200,Image.SCALE_REPLICATE));
            JLabel lbl = new JLabel(i);
            lbl.setName(ind+"");
            ind++;
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    JLabel l = (JLabel) mouseEvent.getComponent();
                    int index = Integer.parseInt(l.getName());
                    bufferedImage = bufferedImages.get(index);

                    if(mouseEvent.getClickCount()>=2) {
                        ImageChosser.this.dispose();
                    }
                }
            });
            lbl.setBounds(x,y,200,200);
            x+=220;

            if(x>=240)
            {
                x=10;
                y+=220;
            }

            lbl.setCursor(AppProperties.handCursor);
            panelImages.add(lbl);
        }

        panelImages.validate();
        panelImages.repaint();
    }

    public void refresh(){
        bufferedImages.clear();
        panelImages.removeAll();

        for(File file : Objects.requireNonNull(Run.root.listFiles
                (
                        file -> {
                            for(String ext : Run.validExt)
                                if(file.getName().endsWith(ext))
                                    return true;
                            return false;
                        }
                )))
        {
            try {
                BufferedImage bf = ImageIO.read(file);

                bufferedImages.add(bf);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        if(bufferedImages.isEmpty()){
            panelImages.setLayout(new FlowLayout());
            first=false;
            JButton btn = new JButton("Aun no hay imagenes, presione aqui para importar");
            btn.addActionListener(a->Run.importImage());
            panelImages.add(btn);
            return;
        }

        new Thread(this::run).start();
        first=true;

    }

    private boolean first;

    public BufferedImage choose()
    {

        if(!first){
            refresh();
        }

        setVisible(true);
        return bufferedImage;
    }
}
