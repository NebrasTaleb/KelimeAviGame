package com.mycompany.kelimeavigame.views;

import CommunicationModels.Message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import utils.Game;
import utils.GameInfoWindow;

public class ClientWaitingWindow extends javax.swing.JFrame {

    private static DefaultListModel<String> playersListModel;

    public ClientWaitingWindow(List<String> currentlyConnectedPlayersNicknames) {
        initComponents();
        playersListModel = new DefaultListModel();
        playersList.setModel(playersListModel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // close ClientWaitingWindow 
        Game.clientLoginWindow.dispose();
        addNewPlayersToList(currentlyConnectedPlayersNicknames);
        // listen to window closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(Game.clientNickname + " closed the game");
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() {
                        try {
                            Game.client.objectSender.writeObject(new Message("exit", Game.client.nickname));
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
                // stop this client from receiving any responses from the server (and the finally block in run method of
                // ServerResponseHandler class will be executed so the client's socket will be closed)
                Game.client.serverResponseHandler.threadIsRunning = false;
                e.getWindow().dispose();
            }
        });
    }

    public void addNewPlayersToList(List<String> playersNicknames) {
        String playerJoiningOrder;
        for (String nickname : playersNicknames) {
            playerJoiningOrder = Integer.toString(playersListModel.size() + 1);
            playersListModel.addElement(playerJoiningOrder + "- " + nickname);
        }
    }

    public static void addNewPlayerToList(String playerNickname) {
        String playerJoiningOrder = Integer.toString(playersListModel.size() + 1);
        playersListModel.addElement(playerJoiningOrder + "- " + playerNickname);
    }

    public static void updatePlayersList(List<String> newListOfNicknames) {
        // first, remove all elements
        playersListModel.clear();
        String playerJoiningOrder;
        for (String nickname : newListOfNicknames) {
            playerJoiningOrder = Integer.toString(playersListModel.size() + 1);
            playersListModel.addElement(playerJoiningOrder + "- " + nickname);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        playersList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        gameInfoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playersList.setVisibleRowCount(10);
        jScrollPane1.setViewportView(playersList);

        jLabel1.setText("Players List");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gameInfoButton)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(gameInfoButton)))
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gameInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameInfoButtonActionPerformed
        GameInfoWindow gameInfoWindowWindow = new GameInfoWindow("Client");
        gameInfoWindowWindow.setVisible(true);
    }//GEN-LAST:event_gameInfoButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gameInfoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> playersList;
    // End of variables declaration//GEN-END:variables
}
