import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.InetSocketAddress;
/**
 * Class to launch the UI of the chord, which offer the interface to create a ring, join an existing ring,
 * query the location (which node it saved in) of a key, and leave the ring.
 * Author: Mengdi, Ying, Yuxia, Silin
 */
public class ChordForm extends javax.swing.JFrame {

    public ChordForm() {
        initComponents();
    }

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
        tPrint = new javax.swing.JTextArea();
        t_predecessor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tFinger = new javax.swing.JTable();
        bExit = new javax.swing.JButton();
        bCreate = new javax.swing.JButton();

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

        jLabel1.setFont(new java.awt.Font("Waree", 0, 15));
        jLabel1.setText("IP:");

        jLabel2.setFont(new java.awt.Font("Waree", 0, 15));
        jLabel2.setText("Port:");

        bLeave.setFont(new java.awt.Font("Waree", 0, 18));
        bLeave.setText("Leave");
        bLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLeaveActionPerformed(evt);
            }
        });

        bJoin.setFont(new java.awt.Font("Waree", 0, 18));
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

        jLabel3.setFont(new java.awt.Font("Waree", 0, 15));
        jLabel3.setText("Key:");

        bQuery.setFont(new java.awt.Font("Waree", 0, 18));
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

        jLabel4.setFont(new java.awt.Font("Waree", 0, 15));
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
        tFinger.getColumnModel().getColumn(1).setWidth(200);
        jScrollPane2.setViewportView(tFinger);

        bExit.setFont(new java.awt.Font("Waree", 0, 18));
        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        bCreate.setFont(new java.awt.Font("Waree", 0, 18));
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
    }

    private void bLeaveActionPerformed(java.awt.event.ActionEvent evt) {
        node.stopAllThreads();
        node = null;

        alertMessage = "Leave the ring successfully!";
        JOptionPane.showMessageDialog(null, "Leave the ring successfully!");
        System.exit(0);
    }

    private void bJoinActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void textIpActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void textPortActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void bQueryActionPerformed(java.awt.event.ActionEvent evt) {
        // call the query function and present the result.
        tPrint.setText("Query Result: " + query());
    }

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void bCreateActionPerformed(java.awt.event.ActionEvent evt) {
        // get local machine's address and use itself as the contact node to create the ring.
        contact = node.getAddress();
        boolean successful_join = node.join(contact);
        if (!successful_join) { // fail to join contact node
            alertMessage = "Cannot Create the ring, please check your machine's address.";
        } else { // if join successfully, periodically refresh the table list to show the updated finger table
            alertMessage = "Ring created successfully!";
            updateFingerTable();
        }
        JOptionPane.showMessageDialog(null, alertMessage);
    }

    private void bJoinMouseClicked(java.awt.event.MouseEvent evt) {
        // Join the node using the address of a contact node
        String ip = textIp.getText();
        String port = textPort.getText();
        String contactNode = ip + ":" + port;

        boolean successful_join = node.join(Util.createSocketAddress(contactNode));
        if (!successful_join) {
            alertMessage = "Cannot connect with node you are trying to contact. Now exit.";
        } else{
            alertMessage = "Joining the Chord ring successfully!";
            updateFingerTable();
        }
        JOptionPane.showMessageDialog(null, alertMessage);
    }

    // @param args the command line arguments
    public static void main(String args[]) {
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChordForm().setVisible(true);
            }
        });
    }

    public void updateFingerTable() {
        Thread thread = new Thread(() -> {
            while(node != null){
                node.printDataStructure();
                int[] ithStarts = node.getIthStarts();
                InetSocketAddress[] fingers = node.getFingers();
                String[] IDs = node.getIDs();
                Object[][] fingerTable = new String[6][3];
                for (int i = 0; i < 6; i++) {
                    fingerTable[i][0] = String.valueOf(ithStarts[i]);
                    fingerTable[i][1] = (fingers[i] == null)? "Null" : fingers[i].getAddress().toString().substring(1)
                            + ":" + fingers[i].getPort();
                    fingerTable[i][2] = IDs[i];
                }
                String[] columnNames = {"Start", "IP : Port", "Node ID"};
                DefaultTableModel model = new DefaultTableModel(fingerTable, columnNames);
                tFinger.setModel(model);
                node.printNeighbors();
                String pred = node.getPredecessorText();
                t_predecessor.setText(pred);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    //Query function to search for the location of a given key
    private String query() {
        InetSocketAddress localAddress = node.getAddress();
        if (localAddress == null) {
            alertMessage = "You are not in the ring. Please create or join an existing ring in order to do the query";
            JOptionPane.showMessageDialog(null, alertMessage);
            return "";
        }
        // Check if the machine is still alive in the ring
        String response = Util.sendRequest(localAddress, "KEEP");
        if (response == null || !response.equals("ALIVE"))  {
            alertMessage = "You are not alive anymore, please join the ring again in order to do the query.";
            JOptionPane.showMessageDialog(null, alertMessage);
            return  "";
        }

        // Local machine is one the ring. Now check if the system is stable
        boolean pred = false;
        boolean succ = false;
        InetSocketAddress pred_addr = Util.requestAddress(localAddress, "YOURPRE");
        InetSocketAddress succ_addr = Util.requestAddress(localAddress, "YOURSUCC");
        if (pred_addr == null || succ_addr == null) {
            alertMessage = "Local machine is disconnected from the ring.";
            JOptionPane.showMessageDialog(null, alertMessage);
            return  "";
        }
        if (pred_addr.equals(localAddress))
            pred = true;
        if (succ_addr.equals(localAddress))
            succ = true;

        // we suppose the system is stable if (1) this node has both valid
        // predecessor and successor or (2) none of them
        while (pred^succ) {
            System.out.println("Waiting for the system to be stable...");
            pred_addr = Util.requestAddress(localAddress, "YOURPRE");
            succ_addr = Util.requestAddress(localAddress, "YOURSUCC");
            if (pred_addr == null || succ_addr == null) {
                alertMessage = "Local machine is disconnected from the ring.";
                JOptionPane.showMessageDialog(null, alertMessage);
                return  "";
            }
            pred = pred_addr.equals(localAddress) ? true : false;
            succ = succ_addr.equals(localAddress) ? true : false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        String command = textKey.getText();
        int hash = Util.hashString(command);
        InetSocketAddress result = Util.requestAddress(localAddress, "FINDSUCC_"+hash);

        // if fail to send request, local node is disconnected, exit
        if (result == null) {
            alertMessage = "The node your are contacting is disconnected.";
            JOptionPane.showMessageDialog(null, alertMessage);
            return  "";
        }
        // return your response and print it in the panel
        return "\nHash value of key: "+ hash + "\nIP: " + result.getAddress().toString().substring(1)+"\nPort: " +
                result.getPort()+"\nNode ID: " + Util.hexIdAndPosition(result );
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

    private static Util util = new Util();
    private static Node node = new Node (Util.createSocketAddress("172.26.24.225" + ":" + "8000"));
    private static InetSocketAddress contact;
    private String alertMessage =  "";
}
