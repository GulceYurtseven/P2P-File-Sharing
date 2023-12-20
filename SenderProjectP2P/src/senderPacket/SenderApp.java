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
    	
        setTitle("Dosya G�nderme Uygulamas�");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        dosyaYoluText = new JTextField();
        dosyaYoluText.setEditable(false);
        
        //ekran� t�klay�nca linki siliyor
        dosyaYoluText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dosyaYoluText.setTransferHandler(null);
                dosyaYoluText.setText("");
                dosyaYolu = null;
            }
        });

        panel.add(dosyaYoluText, BorderLayout.NORTH);



        JButton gonderButton = new JButton("G�nder");
        gonderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dosyaYolu != null && !dosyaYolu.isEmpty()) {
                    // G�nder butonuna bas�ld���nda SenderManager s�n�f�n� �a��r
                    SenderManager.sender(43488, dosyaYolu);
                    showFileSentMessage();
                } else {
                    JOptionPane.showMessageDialog(SenderApp.this, "L�tfen bir dosya se�in.", "Uyar�", JOptionPane.WARNING_MESSAGE);
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

                    // Se�ilen dosyan�n yoluyla dosya s�n�f�ndaki setLink fonksiyonunu �a��r

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
        JOptionPane.showMessageDialog(this, "Dosya g�nderildi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SenderApp());
    }
}