package utils;

import CommunicationModels.Message;
import com.mycompany.kelimeavigame.models.Rules;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GameInfoWindow extends javax.swing.JFrame {

    private String PlayerType; // this variable will indicate which player type wants to see the game information (server or client)

    public GameInfoWindow(String PlayerType) {
        this.PlayerType = PlayerType;
        initComponents();
        if (PlayerType.equals("Server")) {
            initFieldsWithLocalGameInfo();
        } else {
            // i.e PlayerType == "Client"
            fetchGameInfoAndInitFields();
        }

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void initFieldsWithLocalGameInfo() {
        String widthAndHeight = Integer.toString(Game.gameRules.gameWidth);
        widthAndHeight += " X ";
        widthAndHeight += Integer.toString(Game.gameRules.gameHeight);
        widthHeightEditText.setText(widthAndHeight);
        numOfBlocksEditText.setText(Integer.toString(Game.gameRules.numOfBlocks));
        winningPointsEditText.setText(Integer.toString(Game.gameRules.winningPoints));
        numOfGamesEditText.setText(Integer.toString(Game.gameRules.numOfGames));
        numOf2XBlocksEditText.setText(Integer.toString(Game.gameRules.numOf2X));
        numOf3XBlocksEditText.setText(Integer.toString(Game.gameRules.numOf3X));
    }

    private void fetchGameInfoAndInitFields() {
        try {
            Game.client.objectSender.writeObject(new Message("gameInfo", Game.client.nickname));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        widthHeightEditText = new javax.swing.JTextField();
        winningPointsEditText = new javax.swing.JTextField();
        numOfBlocksEditText = new javax.swing.JTextField();
        numOfGamesEditText = new javax.swing.JTextField();
        numOf2XBlocksEditText = new javax.swing.JTextField();
        numOf3XBlocksEditText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Game Width and Height :");

        jLabel2.setText("Number of Blocks :");

        jLabel3.setText("Winning Points :");

        jLabel4.setText("Number of Games :");

        jLabel5.setText("Number of 2X Blocks :");

        jLabel6.setText("Number of 3X Blocks :");

        widthHeightEditText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        widthHeightEditText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        widthHeightEditText.setEnabled(false);

        winningPointsEditText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        winningPointsEditText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        winningPointsEditText.setEnabled(false);

        numOfBlocksEditText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOfBlocksEditText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        numOfBlocksEditText.setEnabled(false);

        numOfGamesEditText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOfGamesEditText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        numOfGamesEditText.setEnabled(false);

        numOf2XBlocksEditText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOf2XBlocksEditText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        numOf2XBlocksEditText.setEnabled(false);

        numOf3XBlocksEditText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOf3XBlocksEditText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        numOf3XBlocksEditText.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(widthHeightEditText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numOfBlocksEditText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numOf3XBlocksEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(winningPointsEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numOfGamesEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numOf2XBlocksEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(widthHeightEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numOfBlocksEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(winningPointsEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(numOfGamesEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(numOf2XBlocksEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(numOf3XBlocksEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField numOf2XBlocksEditText;
    private javax.swing.JTextField numOf3XBlocksEditText;
    private javax.swing.JTextField numOfBlocksEditText;
    private javax.swing.JTextField numOfGamesEditText;
    private javax.swing.JTextField widthHeightEditText;
    private javax.swing.JTextField winningPointsEditText;
    // End of variables declaration//GEN-END:variables
}
