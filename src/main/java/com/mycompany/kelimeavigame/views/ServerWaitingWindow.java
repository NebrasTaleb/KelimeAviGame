package com.mycompany.kelimeavigame.views;

import javax.swing.DefaultListModel;
import utils.Game;
import utils.GameInfoWindow;
import utils.GameUtils;

public class ServerWaitingWindow extends javax.swing.JFrame {

    private static DefaultListModel<String> playersListModel;

    public ServerWaitingWindow() {
        initComponents();
        playersListModel = new DefaultListModel();
        playersList.setModel(playersListModel);
        setLocationRelativeTo(null);
    }

    public static void addNewPlayerToList(String playerNickname) {
        String playerJoiningOrder = Integer.toString(playersListModel.size() + 1);
        playersListModel.addElement(playerJoiningOrder + "- " + playerNickname);
    }

    public static void removePlayerFromList(String playerNickname) {
        // if the user who disjoined is the last one in the list, then remove last element form the list
        String lastElement = playersListModel.get(playersListModel.size() - 1);
        if (lastElement.contains(playerNickname)) {
            playersListModel.remove(playersListModel.size() - 1);
        } else {
            // the disjoined player is not the last one in the list (nor the first because that is the game creator)
            // get the index of the disjoined player
            int disjoinedPlayerIndex = 1; // 1 will change. it is just a value for initialization
            for (int index = 0; index < playersListModel.size(); index++) {
                if (playersListModel.get(index).contains(playerNickname)) {
                    disjoinedPlayerIndex = index;
                    break;
                }
            }
            // save the current content (before removing any element) of the list 
            DefaultListModel<String> tempList = new DefaultListModel();
            GameUtils.copyListToListExceptRemovedItem(Game.connectedPlayersNicknames, tempList, disjoinedPlayerIndex);
            // remove all elements
            playersListModel.removeAllElements();
            int playerJoiningOrder = 0;
            for (int index = 0; index < tempList.size(); index++) {
                playerJoiningOrder++;
                playersListModel.addElement(playerJoiningOrder + "- " + tempList.get(index));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playersList = new javax.swing.JList<>();
        startGameButton = new javax.swing.JButton();
        gameInfoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Players List");

        playersList.setVisibleRowCount(10);
        jScrollPane1.setViewportView(playersList);

        startGameButton.setText("START GAME");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        gameInfoButton.setText("Game Info");
        gameInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameInfoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(gameInfoButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(startGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(gameInfoButton)))
                .addGap(18, 18, 18)
                .addComponent(startGameButton)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
            
    }//GEN-LAST:event_startGameButtonActionPerformed

    private void gameInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameInfoButtonActionPerformed
        GameInfoWindow gameInfoWindowWindow = new GameInfoWindow("Server");
        gameInfoWindowWindow.setVisible(true);
    }//GEN-LAST:event_gameInfoButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gameInfoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> playersList;
    private javax.swing.JButton startGameButton;
    // End of variables declaration//GEN-END:variables
}
