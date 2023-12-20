package receiverPacket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReceiverManager {
	
	public static void receiveFile(String konum,int port) {
		
		try {
			
			System.out.println("Sunucu baþlatýldý. Baðlantý bekleniyor...");
		    ServerSocket serverSocket = new ServerSocket(port);
		    int clientCount = 0; 
		    
		    while (true) {
		    	// istemci baðlantýýný kabul et
		    	Socket clientSocket = serverSocket.accept();
		    	System.out.println("Baðlantý kabul edildi.");
		    	
		    	// istemciden gelen dosyayý iþlemek için yeni bir Thread oluþtur
		    	Thread clientHandler = new Thread(() -> {
		    		
		    		try {
		    			
		    			// Gelen dosyayý okumak için InputStream oluþtur
		                InputStream inputStream = clientSocket.getInputStream();
	
		                // Dosyayý kaydedilecek konumu belirle
		                String kayitKonumu = konum;     
	
		                // Dosya yolunu oluþtur
		                Path dosyaYolu = Paths.get(kayitKonumu, "gelendosya.txt");
	
		                // Dosyayý kaydetmek için FileOutputStream oluþtur
		                FileOutputStream fileOutputStream = new FileOutputStream(dosyaYolu.toString());
		                System.out.println(dosyaYolu);
		                
		                // Dosyayý oku ve kaydet
		                byte[] buffer = new byte[4096];
		                int bytesRead;
		                
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                	fileOutputStream.write(buffer, 0, bytesRead);
		                	}
		                
		                System.out.println("Dosya \"" + dosyaYolu + "\" konumuna kaydedildi. Baðlantýyý kapatýlýyor...");
		                // Kaynaklarý temizle
		                
		                fileOutputStream.close();
		                inputStream.close();
		                clientSocket.close();
		                
		                System.out.println("Baðlantý kapatýldý.");
		                System.exit(0);
		                
		                } catch (Exception e) {
		                      e.printStackTrace();
		                      }
		    		});
		    	// istemci iþleme Thread'ini baþlat
		    	clientHandler.start();
		    	}
		    } catch (Exception e) {
		          e.printStackTrace();
		          }
		}
}