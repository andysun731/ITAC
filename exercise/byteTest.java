package exercise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class byteTest {
	public static void main(String[] args) {
		String a = "110010";
		byte m=(byte)Integer.parseInt(a,2);//将字符串转化成十进制整形，2表示原串表示的数是二进制
		System.out.println(m);
		String nn=Integer.toBinaryString(m);//将上面那个十进制数转化为二进制字符串形式
		System.out.println(nn);
		
//        try {   
//        System.out.print("输入要保存文件的内容：");  
//         
//        int n = 3;   
//        byte buffer[] = new byte[n];
//        buffer[0] = 6;
//        buffer[1] = 7;
//        buffer[2] = 8;
//        
//        // 创建文件输出流对象   
//        FileOutputStream os = new FileOutputStream("/Users/sundeqing/Downloads/BinaryFile.bin");   
//        // 写入输出流   
//        os.write(buffer, 0, n);   
//        // 关闭输出流   
//        os.close();   
//        System.out.println("n="+n);   
//        
//        } catch (IOException ioe) {   
//        System.out.println(ioe);   
//        } catch (Exception e) {   
//        System.out.println(e);   
//        }   
//		
//        try {   
//            // 创建文件输入流对象   
//            FileInputStream is = new FileInputStream("/Users/sundeqing/Downloads/WriteFile.bin");   
//            // 设定读取的字节数   
//            int n = 512;   
//            byte buf[] = new byte[n]; 
//            System.out.println("read the file");
//            // 读取输入流   
//            int len=is.read(buf);        //从流中读取内容
//            
//            //打印f文件的内容.
//            for(int i=0;i<len;i++){
//            	System.out.println(buf[i]);
//            }
//              
//            // 关闭输入流   
//            is.close();   
//            } catch (IOException ioe) {   
//            System.out.println(ioe);   
//            } catch (Exception e) {   
//            System.out.println(e);   
//            }   
        
	}

}
