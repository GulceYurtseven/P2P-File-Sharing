package senderPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.io.File;

public class SenderApp extends JFrame {

    private JTextField dosyaYoluText;
    private String dosyaYolu; 

    public SenderApp() {
    	
        setTitle("Dosya Gönderme Uygulamasý");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        dosyaYoluText = new JTextField();
        dosyaYoluText.setEditable(false);
        
        //ekraný týklayýnca linki siliyor
        dosyaYoluText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dosyaYoluText.setTransferHandler(null);
                dosyaYoluText.setText("");
                dosyaYolu = null;
            }
        });

        panel.add(dosyaYoluText, BorderLayout.NORTH);



        JButton gonderButton = new JButton("Gönder");
        gonderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dosyaYolu != null && !dosyaYolu.isEmpty()) {
                    // Gönder butonuna basýldýðýnda SenderManager sýnýfýný çaðýr
                    SenderManager.sender(43488, dosyaYolu);
                    showFileSentMessage();
                } else {
                    JOptionPane.showMessageDialog(SenderApp.this, "Lütfen bir dosya seçin.", "Uyarý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panel.add(gonderButton, BorderLayout.SOUTH);

        panel.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                try {
                    File dosya = ((java.util.List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor)).get(0);
                    dosyaYolu = dosya.getAbsolutePath();
                    dosyaYoluText.setText(dosyaYolu);
                    dosyaYoluText.setTransferHandler(this); // Reset the TransferHandler

                    // Seçilen dosyanýn yoluyla dosya sýnýfýndaki setLink fonksiyonunu çaðýr

                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void showFileSentMessage() {
        JOptionPane.showMessageDialog(this, "Dosya gönderildi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SenderApp());
    }
}