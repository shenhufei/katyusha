package socket;

;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Clients {
    public static void main(String[] args) throws IOException {
        byte [] bs = new byte[1024];
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9876));
        while(true){
            System.out.println("等待连接----");
            Socket accept = server.accept();
            System.out.println("连接成功----");
            System.out.println("start data----");
            int read = accept.getInputStream().read(bs);
            System.out.println("end data----"+read);
            String content = new String(bs);
            System.out.println(content);
        }
    }
}
