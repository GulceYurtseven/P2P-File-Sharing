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
			
			System.out.println("Sunucu ba�lat�ld�. Ba�lant� bekleniyor...");
		    ServerSocket serverSocket = new ServerSocket(port);
		    int clientCount = 0; 
		    
		    while (true) {
		    	// istemci ba�lant��n� kabul et
		    	Socket clientSocket = serverSocket.accept();
		    	System.out.println("Ba�lant� kabul edildi.");
		    	
		    	// istemciden gelen dosyay� i�lemek i�in yeni bir Thread olu�tur
		    	Thread clientHandler = new Thread(() -> {
		    		
		    		try {
		    			
		    			// Gelen dosyay� okumak i�in InputStream olu�tur
		                InputStream inputStream = clientSocket.getInputStream();
	
		                // Dosyay� kaydedilecek konumu belirle
		                String kayitKonumu = konum;     
	
		                // Dosya yolunu olu�tur
		                Path dosyaYolu = Paths.get(kayitKonumu, "gelendosya.txt");
	
		                // Dosyay� kaydetmek i�in FileOutputStream olu�tur
		                FileOutputStream fileOutputStream = new FileOutputStream(dosyaYolu.toString());
		                System.out.println(dosyaYolu);
		                
		                // Dosyay� oku ve kaydet
		                byte[] buffer = new byte[4096];
		                int bytesRead;
		                
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                	fileOutputStream.write(buffer, 0, bytesRead);
		                	}
		                
		                System.out.println("Dosya \"" + dosyaYolu + "\" konumuna kaydedildi. Ba�lant�y� kapat�l�yor...");
		                // Kaynaklar� temizle
		                
		                fileOutputStream.close();
		                inputStream.close();
		                clientSocket.close();
		                
		                System.out.println("Ba�lant� kapat�ld�.");
		                System.exit(0);
		                
		                } catch (Exception e) {
		                      e.printStackTrace();
		                      }
		    		});
		    	// istemci i�leme Thread'ini ba�lat
		    	clientHandler.start();
		    	}
		    } catch (Exception e) {
		          e.printStackTrace();
		          }
		}
}