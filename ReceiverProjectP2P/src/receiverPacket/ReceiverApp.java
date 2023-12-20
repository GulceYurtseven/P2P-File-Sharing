package receiverPacket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ReceiverApp {

    private JButton sendButton;
    public ReceiverApp() {
        Dosya d = new Dosya();
        JFrame frame = new JFrame("Klas�r Se�me Aray�z�");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton selectFolderButton = new JButton("Klas�r Se�");
        JTextField folderPathField = new JTextField(20);
        JLabel selectedFolderLabel = new JLabel("Se�ilen Klas�r: ");

        JLabel infoLabel = new JLabel("Dosyay� kaydetmek istedi�iniz klas�r� se�in");
        sendButton = new JButton("G�nder");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReceiverManager.receiveFile(folderPathField.getText(), 43488); //43488
            }
        });

        selectFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = folderChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = folderChooser.getSelectedFile();
                    String folderPath = selectedFolder.getAbsolutePath();

                    folderPathField.setText(folderPath);
                    selectedFolderLabel.setText("Se�ilen Klas�r: " + folderPath);
                    d.setLink(folderPath);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(infoLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(selectFolderButton);
        panel.add(folderPathField);
        panel.add(selectedFolderLabel);
        panel.add(sendButton); 

        frame.getContentPane().add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReceiverApp());
    }
}