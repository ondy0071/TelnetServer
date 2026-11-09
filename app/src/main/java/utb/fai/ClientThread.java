package utb.fai;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientThread implements Runnable {

    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientThread(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;

    OutputStream out = clientSocket.getOutputStream();
    InputStream in = clientSocket.getInputStream();

    this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    this.writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
}

    @Override
    public void run() {

        String buffer;
        try {

            while ((buffer = this.reader.readLine()) != null) {
                System.out.println("Received: " + buffer);
                
                writer.write(buffer + "\r\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
