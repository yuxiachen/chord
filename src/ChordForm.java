/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.InetSocketAddress;
import java.net.InetAddress;

import java.net.UnknownHostException;
/**
 *
 * @author mengdi
 */
public class ChordForm extends javax.swing.JFrame {

    /**
     * Creates new form ChordForm
     */
    public ChordForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textIp = new javax.swing.JTextField();
        textPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bLeave = new javax.swing.JButton();
        bJoin = new javax.swing.JButton();
        textKey = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bQuery = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tPrint = new javax.swing.JTextArea("Query Result: ");
        t_predecessor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tFinger = new javax.swing.JTable();
        bExit = new javax.swing.JButton();
        bCreate = new javax.swing.JButton();

        result_queryOne = "";

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIpActionPerformed(evt);
            }
        });

        textPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPortActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Waree", 0, 15)); // NOI18N
        jLabel1.setText("IP:");

        jLabel2.setFont(new java.awt.Font("Waree", 0, 15)); // NOI18N
        jLabel2.setText("Port:");

        bLeave.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        bLeave.setText("Leave");
        bLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLeaveActionPerformed(evt);
            }
        });

        bJoin.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        bJoin.setText("Join");
        bJoin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bJoinMouseClicked(evt);
            }
        });
        bJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bJoinActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Waree", 0, 15)); // NOI18N
        jLabel3.setText("Key:");

        bQuery.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        bQuery.setText("Query");
        bQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQueryActionPerformed(evt);
            }
        });

        tPrint.setColumns(20);
        tPrint.setRows(5);
        tPrint.setFont(new Font("Serif",Font.PLAIN,20));
        jScrollPane1.setViewportView(tPrint);

        jLabel4.setFont(new java.awt.Font("Waree", 0, 15)); // NOI18N
        jLabel4.setText("Predecessor:");

        tFinger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Entry", "ID", "IP"
            }
        ));
        tFinger.setRowHeight(40);
        jScrollPane2.setViewportView(tFinger);

        bExit.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        bCreate.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        bCreate.setText("Create");
        bCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(232, 232, 232))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(textKey, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                            .addComponent(textIp))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(159, 159, 159)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(bLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(bQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel4)))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t_predecessor, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(232, 232, 232))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(textIp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLeave)
                    .addComponent(bJoin)
                    .addComponent(bCreate))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textKey, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(bQuery)
                    .addComponent(t_predecessor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bExit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLeaveActionPerformed
        // TODO add your handling code here:
        m_node.stopAllThreads();
        m_node = null;
        System.out.println("Leaving the ring...");
        JOptionPane.showMessageDialog(null, "Leave the ring successfully!");
    
    }//GEN-LAST:event_bLeaveActionPerformed

    private void bJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bJoinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bJoinActionPerformed

    private void textIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIpActionPerformed

    private void textPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPortActionPerformed

    private void bQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQueryActionPerformed
        // TODO add your handling code here:
        Query();
        tPrint.append(result_queryOne);
    }//GEN-LAST:event_bQueryActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bExitActionPerformed

    private void bCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateActionPerformed
        // TODO add your handling code here:
        m_helper = new Helper();

        // get local machine's ip
        String local_ip = "192.168.1.31";
        String port = "8888";

        // create node
        m_node = new Node (Helper.createSocketAddress(local_ip+":"+ port));
        m_contact = m_node.getAddress();
        // try to join ring from contact node
        boolean successful_join = m_node.join(m_contact);
        String alterMessage = "";
        // fail to join contact node
        if (!successful_join) {
            alterMessage = "Cannot Create the ring. Now exit.";
        } else {
            alterMessage = "Ring created successfully!";
            updateFingerTable();
        }
        JOptionPane.showMessageDialog(null, alterMessage);

    }//GEN-LAST:event_bCreateActionPerformed

    private void bJoinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bJoinMouseClicked
        // TODO add your handling code here:
        // get local machine's ip
        String local_ip = null;
        try {
            local_ip = InetAddress.getLocalHost().getHostAddress();

        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        m_node = new Node (Helper.createSocketAddress(local_ip+":"+"8000"));

        //get the contact address
        String ip = textIp.getText();
        String port = textPort.getText();
        String addr = ip + ":" + port;
        System.out.println(addr);

        // try to join ring from contact node
        boolean successful_join = m_node.join(Helper.createSocketAddress(addr));
        String alterMessage = "";

        if (!successful_join) {
            alterMessage = "Cannot connect with node you are trying to contact. Now exit.";
        }

        // print join info
        else{
            alterMessage = "Joining the Chord ring.";
        }
        JOptionPane.showMessageDialog(null, alterMessage);
    }//GEN-LAST:event_bJoinMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChordForm().setVisible(true);
            }
        });
    }

    public void updateFingerTable() {
    Thread thread = new Thread(){
        public void run(){
            while(m_node != null){
                m_node.printDataStructure();
                int[] ithStarts = m_node.getIthStarts();
                InetSocketAddress[] fingers = m_node.getFingers();
                String[] IDs = m_node.getIDs();
                Object[][] fingerTable = new String[6][3];
                for (int i = 0; i < 6; i++) {
                    fingerTable[i][0] = String.valueOf(ithStarts[i]);
                    fingerTable[i][1] = (fingers[i] == null)? "Null" : fingers[i].toString();
                    fingerTable[i][2] = IDs[i];
                }
                String[] columnNames = {"Start", "IP : Port", "ID & Position"};
                DefaultTableModel model = new DefaultTableModel(fingerTable, columnNames);
                tFinger.setModel(model);
                m_node.printNeighbors();
                String pred = m_node.getPredecessorText();
                t_predecessor.setText(pred);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    thread.start();
}

    private void Query() {
        InetSocketAddress localAddress = m_node.getAddress();
        if (localAddress == null) {
            System.out.println("Cannot find address you are trying to contact. Now exit.");
            System.exit(0);;
        }
        // successfully constructed socket address of the node we are
        // trying to contact, check if it's alive
        String response = Helper.sendRequest(localAddress, "KEEP");
        if (response == null || !response.equals("ALIVE"))  {
            System.out.println("\nCannot find node you are trying to contact. Now exit.\n");
            System.exit(0);
        }
        // it's alive, print connection info
        System.out.println("Connection to node "+localAddress.getAddress().toString()+", port " +
                localAddress.getPort()+", position "+Helper.hexIdAndPosition(localAddress)+".");
        // check if system is stable
        boolean pred = false;
        boolean succ = false;
        InetSocketAddress pred_addr = Helper.requestAddress(localAddress, "YOURPRE");
        InetSocketAddress succ_addr = Helper.requestAddress(localAddress, "YOURSUCC");
        if (pred_addr == null || succ_addr == null) {
            System.out.println("The node your are contacting is disconnected. Now exit.");
            System.exit(0);
        }
        if (pred_addr.equals(localAddress))
            pred = true;
        if (succ_addr.equals(localAddress))
            succ = true;

        // we suppose the system is stable if (1) this node has both valid
        // predecessor and successor or (2) none of them
        while (pred^succ) {
            System.out.println("Waiting for the system to be stable...");
            pred_addr = Helper.requestAddress(localAddress, "YOURPRE");
            succ_addr = Helper.requestAddress(localAddress, "YOURSUCC");
            if (pred_addr == null || succ_addr == null) {
                System.out.println("The node your are contacting is disconnected. Now exit.");
                System.exit(0);
            }
            if (pred_addr.equals(localAddress))
                pred = true;
            else
                pred = false;
            if (succ_addr.equals(localAddress))
                succ = true;
            else
                succ = false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        String command = textKey.getText();
        long hash = Helper.hashString(command);
        System.out.println("\nHash value is "+Long.toHexString(hash));
        InetSocketAddress result = Helper.requestAddress(localAddress, "FINDSUCC_"+hash);

        // if fail to send request, local node is disconnected, exit
        if (result == null) {
            System.out.println("The node your are contacting is disconnected. Now exit.");
            System.exit(0);
        }
        // print out response
        result_queryOne = "\nNode "+result.getAddress().toString()+"\nPort: " +
                result.getPort()+"\nPosition: " + Helper.hexIdAndPosition(result );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCreate;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bJoin;
    private javax.swing.JButton bLeave;
    private javax.swing.JButton bQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tFinger;
    private javax.swing.JTextArea tPrint;
    private javax.swing.JTextField t_predecessor;
    private javax.swing.JTextField textIp;
    private javax.swing.JTextField textKey;
    private javax.swing.JTextField textPort;
    // End of variables declaration//GEN-END:variables

    private static Node m_node;
    private static InetSocketAddress m_contact;
    private static Helper m_helper;

    private static String result_queryOne;
}
