/*
package learn.picdoan;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.apache.tomcat.util.codec.binary.Base64;

public class DownloadPicFromURL {
	public static void main(String[] args) throws IOException {
       
        String path="d:/pic.jpg";
        downloadPicture(url,path);
    }
    //链接url下载图片
    private static void downloadPicture(String urlList,String path) throws IOException {
        URL url = null;
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            url = new URL(urlList);
             dataInputStream = new DataInputStream(url.openStream());
             
             String base64FromInputStream = getBase64FromInputStream(dataInputStream);
             TtpOcrService.vehicleLicenseBase64OCR(base64FromInputStream);
             
             
             fileOutputStream = new FileOutputStream(new File(path));
            */
/*ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());*//*

            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	dataInputStream.close();
            fileOutputStream.close();
		}
    }
    
    
    public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }


}
*/
