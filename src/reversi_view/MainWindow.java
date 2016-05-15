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
        
        
        jButton2.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/res//white_large.jpg")));jButton2.setEnabled(false);
        jButton2.setDisabledIcon(new javax.swing.ImageIcon(
                getClass().getResource("/res//white_large.jpg")));
        
        
        jButton3.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/res//black_large.jpg")));jButton3.setEnabled(false);
        jButton3.setDisabledIcon(new javax.swing.ImageIcon(
                getClass().getResource("/res//black_large.jpg")));
        jLabel2.setText("2");
        jLabel3.setText("2");
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(200, 300));
        setMaximumSize(new java.awt.Dimension(1110, 685));
        setMinimumSize(new java.awt.Dimension(1110, 685));
        setPreferredSize(new java.awt.Dimension(1110, 685));
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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Turn :");

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Points", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 24))); // NOI18N
        jPanel3.setName("POINTS"); // NOI18N

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(128, 128, 128))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    
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
    
    public void updatePoints(int[] points){
        jLabel2.setText(String.valueOf(points[0]));
        jLabel3.setText(String.valueOf(points[1]));
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    private Action setClickedButton(JButton b) {
        clicked = b;
        return null;
    }
    public JButton getClickedButton(){
        return clicked;
    }
}
