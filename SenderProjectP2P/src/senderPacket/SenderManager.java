package senderPacket;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
//G�NDEREN
public class SenderManager {
	//dosya g�nderme k�sm�
	private static String[] targetIPs = {"10.7.60.166","10.7.56.39"};
		
	public static void sender(int port, String dosyaPath) {
		
	    try {
	        System.out.println("111111");
	        
	        // Sunucuya ba�lan
	        for (String targetIP : targetIPs) {
	        	//socket a�ma
	        	Socket socket = new Socket(targetIP, port);
		       	
		        // Dosyay� okumak i�in FileInputStream olu�tur
		        FileInputStream fileInputStream = new FileInputStream(dosyaPath);
	
		        // Dosyay� g�ndermek i�in OutputStream olu�tur
		        OutputStream outputStream = socket.getOutputStream();
		        System.out.println("222222");
	
		        // Dosyay� oku ve g�nder
		        byte[] buffer = new byte[4096];
		        /*�o�u i�letim sistemi ve dosya sistemleri de veriyi bellekte 
		         * bloklar halinde ta��r ve bu bloklar genellikle 4096 bayt 
		         * veya bir katlar� olarak d�zenlenir. Bu, dosya okuma ve 
		         * yazma i�lemlerini optimize etmeye yard�mc� olabilir.*/
		        
		        int bytesRead; 
		        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
		            outputStream.write(buffer, 0, bytesRead);
		        }
	
		        // Kaynaklar� temizle
		        fileInputStream.close();
		        outputStream.close();
		        socket.close();
	        }

	        System.out.println("Dosya g�nderildi. Ba�lant� kapat�l�yor...");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("333333");
	    }
	}

}

