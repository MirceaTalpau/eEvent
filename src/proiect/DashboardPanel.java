package proiect;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

public class DashboardPanel extends MyPanel {
    private JLabel[] boxLabel = new JLabel[3], boxTitle = new JLabel[3], boxInfo = new JLabel[3];

    public DashboardPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        int[] s = Utils.getStatistics();
        for(int i = 0; i < boxLabel.length; i++) {
            boxLabel[i] = new JLabel();
            boxLabel[i].setOpaque(true);
            boxLabel[i].setBorder(BorderFactory.createLineBorder(menuColor, 1, true));

            boxTitle[i] = new JLabel(s[i] + "");
            boxTitle[i].setFont(new Font("Monospaced", Font.BOLD, fontTitleSize));
            boxTitle[i].setForeground(textColor);
            boxTitle[i].setHorizontalAlignment(SwingConstants.CENTER);
            boxTitle[i].setVerticalAlignment(SwingConstants.CENTER);
            add(boxTitle[i]);

            boxInfo[i] = new JLabel();
            boxInfo[i].setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
            boxInfo[i].setForeground(textColor);
            boxInfo[i].setHorizontalAlignment(SwingConstants.CENTER);
            boxInfo[i].setVerticalAlignment(SwingConstants.CENTER);
            
            add(boxTitle[i]);
            add(boxInfo[i]);
            add(boxLabel[i]);
        }
        boxInfo[0].setText("Utilizatori inregistrati");
        boxInfo[1].setText("Evenimente active");
        boxInfo[2].setText("Bilete vandute");

    }

    @Override
    public void resizeUI() {
        int boxW = getBounds().width / 4;
        int boxH = boxW / 2;
        boxLabel[1].setBounds((getBounds().width - boxW) / 2, (getBounds().height - boxH) / 2, boxW, boxH);
        boxLabel[0].setBounds(boxLabel[1].getBounds().x - boxW - menuOffset * 3, boxLabel[1].getBounds().y, boxW, boxH);
        boxLabel[2].setBounds(boxLabel[1].getBounds().x + boxW + menuOffset * 3, boxLabel[1].getBounds().y, boxW, boxH);
        boxTitle[0].setBounds(boxLabel[0].getBounds().x, boxLabel[0].getBounds().y, boxW, boxH / 3);
        boxTitle[1].setBounds(boxLabel[1].getBounds().x, boxLabel[1].getBounds().y, boxW, boxH / 3);
        boxTitle[2].setBounds(boxLabel[2].getBounds().x, boxLabel[2].getBounds().y, boxW, boxH / 3);
        boxInfo[0].setBounds(boxTitle[0].getBounds().x, boxLabel[0].getBounds().y, boxW, boxH);
        boxInfo[1].setBounds(boxTitle[1].getBounds().x, boxLabel[1].getBounds().y, boxW, boxH);
        boxInfo[2].setBounds(boxTitle[2].getBounds().x, boxLabel[2].getBounds().y, boxW, boxH);
    }
}
