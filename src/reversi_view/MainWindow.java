package reversi_view;


import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import reversi_model.Board;
import reversi_model.Disc;
import reversi_controller.Reversi;

public class MainWindow extends javax.swing.JFrame {

    ArrayList<int[]> possibleWhite;
    ArrayList<int[]> possibleBlack;
    public JButton[][] buttonBoard;
    public boolean turnWhite;
    private final int N;
    private JButton clicked = null;
    public int initialI, initialJ;

    ImageIcon imageWhite;
    ImageIcon imageBlack;
    ImageIcon imageEmpty;

    public MainWindow(int N, Reversi controller) {
        possibleWhite = new ArrayList<>();
        possibleBlack = new ArrayList<>();
        buttonBoard = new JButton[N][N];
        turnWhite = true;
        this.N = N;
        initComponents();
        imageWhite = new javax.swing.ImageIcon(
                getClass().getResource("/res//white.jpg"));
        imageBlack = new javax.swing.ImageIcon(
                getClass().getResource("/res//black.jpg"));
        imageEmpty = new javax.swing.ImageIcon(
                getClass().getResource("/res//blank.jpg"));
        jPanel1.setLayout(new java.awt.GridLayout(N, N));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                JButton b = new JButton();
                b.setName((j) + " " + (N-1-i));
                b.setActionCommand((j) + " " + (N-1-i));
                b.setIcon(imageEmpty);
                b.setEnabled(false);
                b.addActionListener(controller);
                jPanel1.add(b);
                buttonBoard[j][(N-1-i)] = b;
            }
        }
        jButton1.setName("");
        jButton1.setActionCommand("");
        jButton1.setIcon(imageEmpty);
        jButton1.setEnabled(false);
        jButton1.setDisabledIcon(imageWhite);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(200, 300));
        setMaximumSize(new java.awt.Dimension(1200, 620));
        setMinimumSize(new java.awt.Dimension(1200, 620));
        setPreferredSize(new java.awt.Dimension(1200, 620));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(560, 560));
        jPanel1.setMinimumSize(new java.awt.Dimension(560, 560));
        jPanel1.setPreferredSize(new java.awt.Dimension(560, 560));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Turn :");

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(510, 510, 510))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public void updateBoard(List<Disc> discList){
        for(Disc d : discList){
            if(d.isWhite()){
                buttonBoard[d.column()][d.row()].setDisabledIcon(imageWhite);
            } else {
                buttonBoard[d.column()][d.row()].setDisabledIcon(imageBlack);
            }
        }
    }
    public void updatePlayer(boolean isWhite){
        if(isWhite){
            jButton1.setDisabledIcon(imageWhite);
        } else {         
            jButton1.setDisabledIcon(imageBlack);
        }
    }
    public void updatePossibleMoves(List<Disc> posssibleMoves){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttonBoard[i][j].setEnabled(false);
            }
        }
        for(Disc d : posssibleMoves){
            buttonBoard[d.column()][d.row()].setEnabled(true);
        }
    }

    private void changePlayer() {
        if (turnWhite) {
            System.out.println("White:");
            possibleWhite.stream().forEach((i) -> {
                buttonBoard[i[0]][i[1]].setEnabled(true);   
                System.out.print(i[0] + " " + i[1] + ", ");
                System.out.println();
            });
            System.out.println("Black:");
            possibleBlack.stream().forEach((i) -> {
                buttonBoard[i[0]][i[1]].setEnabled(false);
                System.out.print(i[0] + " " + i[1] + ", ");
                System.out.println();
            });
        } else {
            System.out.println("Black:");
            possibleBlack.stream().forEach((i) -> {
                buttonBoard[i[0]][i[1]].setEnabled(true);
                System.out.print(i[0] + " " + i[1] + ", ");
                System.out.println();
            });
            System.out.println("White:");
            possibleWhite.stream().forEach((i) -> {
                buttonBoard[i[0]][i[1]].setEnabled(false);
                System.out.print(i[0] + " " + i[1] + ", ");
                System.out.println();
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private Action setClickedButton(JButton b) {
        clicked = b;
        return null;
    }
    public JButton getClickedButton(){
        return clicked;
    }
}
