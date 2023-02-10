import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Data_main {

    public static void main(String[] args){
        // 1. Server프로그램에서 사용하는 소켓, Port를 통해 연결 요청이 오기를 대기
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(5555);
        }catch (IOException e){
            System.out.println("이미 존재하는 포트입니다.");
        }


        while (true){

            System.out.println("연결 요청을 기다리는 중입니다.");

            try {
                Socket socket2 = serverSocket.accept();

                System.out.println("접속자 IP : "+ socket2.getInetAddress());

                InputStream in = socket2.getInputStream();
                DataInputStream dis = new DataInputStream(in);

                // 7. readUTF()로 받은 메세지를 UTF-8 인코딩을 통해서 읽어들임
                String packet = dis.readUTF();
                //String name = cd.byteToString(cd.hexToByte(packet));
                System.out.println("[DB 출력] 받은 메세지 : "+packet);

                dis.close();
                socket2.close();
                System.out.println("연결을 종료했습니다.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
