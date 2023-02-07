import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server_main {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);  // port 할당?
        }catch (IOException e){
            System.out.println("해당 포트가 열려있습니다.");
        }

        while(true){

            try {
                System.out.println(getTime() + "연결 요청을 기다리는 중입니다 ");

                Socket socket = serverSocket.accept();
                System.out.println(getTime() + socket.getInetAddress());

                InputStream in = socket.getInputStream();
                DataInputStream dis = new DataInputStream(in);
                System.out.println("받은 메세지 : " + dis.readUTF());
                System.out.println("연결을 종료합니다. ");

                /*
                    1. client에서 BufferedReader로 받아보자
                    2. readUTF() 만약 quit이라는 단어가 들어온다면 socket 종료해버리기
                 */
                
                dis.close();
                socket.close();
                System.out.println("연결을 종료되었습니다. ");

            } catch (IOException e) {
                e.printStackTrace();
                     }
                  }
            }

    // 현재 시간을 문자열로 반환하는 함수
    private static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh.mm.ss]");
        return f.format(new Date());
    }
}