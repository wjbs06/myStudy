package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ChatServerReadHandler extends Thread{
    private Socket socket;
    private String line;   //서버로부터 읽어온 문자열 저장   

    public ChatServerReadHandler() {
        System.out.println("프로그램이 초기화 되지 않았습니다.");
    }
    
    public ChatServerReadHandler(Socket socket) {
        this.socket = socket;  
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                // 네트웍으로 전송 받음
                line = reader.readLine();
                
                if (line == null){ 
                    break;
                }
                // 화면 출력
                System.out.println("\n" + line);
                System.out.print("▶ ");
            }

        } catch(IOException ignored) {
        } finally {
            try {
                socket.close();
            } catch(IOException ignored) {}
        }
    }

}