package utb.fai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class App {

    public static void main(String[] args) {
            int port;
            int max_threads;
        if (args.length >= 2) {
                   port = Integer.parseInt(args[0]);
        max_threads = Integer.parseInt(args[1]);    

        }else {
            System.out.println("Using default parameters: port=12345, max_threads=10");
                   port = 12345;
         max_threads = 10;    

        }
        
  
        // Implement input parameter processing
        
        // Implementation of the main server loop
        

        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(max_threads);
     
       try( ServerSocket serverSocket = new ServerSocket(port)) {
        System.out.println("Server is listening on port " + serverSocket.getLocalPort())
        ;
        while (true) {
            Socket soc = serverSocket.accept();
            System.out.println("New client connected from " + soc.getInetAddress().getHostAddress() + ":" + soc.getPort());
            ClientThread clientThread = new ClientThread(soc);
           executorService.submit(clientThread);
            
        }
        
       } catch (IOException e) {
       e.printStackTrace();
       }finally {
        executorService.shutdownNow();
       }

    }
}
