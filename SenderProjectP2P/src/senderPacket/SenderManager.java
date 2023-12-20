package senderPacket;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
//GÖNDEREN
public class SenderManager {
	//dosya gönderme kýsmý
	private static String[] targetIPs = {"10.7.60.166","10.7.56.39"};
		
	public static void sender(int port, String dosyaPath) {
		
	    try {
	        System.out.println("111111");
	        
	        // Sunucuya baðlan
	        for (String targetIP : targetIPs) {
	        	//socket açma
	        	Socket socket = new Socket(targetIP, port);
		       	
		        // Dosyayý okumak için FileInputStream oluþtur
		        FileInputStream fileInputStream = new FileInputStream(dosyaPath);
	
		        // Dosyayý göndermek için OutputStream oluþtur
		        OutputStream outputStream = socket.getOutputStream();
		        System.out.println("222222");
	
		        // Dosyayý oku ve gönder
		        byte[] buffer = new byte[4096];
		        /*çoðu iþletim sistemi ve dosya sistemleri de veriyi bellekte 
		         * bloklar halinde taþýr ve bu bloklar genellikle 4096 bayt 
		         * veya bir katlarý olarak düzenlenir. Bu, dosya okuma ve 
		         * yazma iþlemlerini optimize etmeye yardýmcý olabilir.*/
		        
		        int bytesRead; 
		        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
		            outputStream.write(buffer, 0, bytesRead);
		        }
	
		        // Kaynaklarý temizle
		        fileInputStream.close();
		        outputStream.close();
		        socket.close();
	        }

	        System.out.println("Dosya gönderildi. Baðlantý kapatýlýyor...");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("333333");
	    }
	}

}

