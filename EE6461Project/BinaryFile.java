package EE6461Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFile {
	
	private int numofZeros; // the number of padding zeros at the end of VLCcodes to make its length multiple of 8
	private int numofBytes; // the bytes of the binary file
	
	public void setNumofZeros(int noz){
		this.numofZeros = noz;
	}
	public int getNumofZeros(){
		return this.numofZeros;
	}
	public void setNumofBytes(int nob){
		this.numofZeros = nob;
	}
	public int getNumofBytes(){
		return this.numofBytes;
	}
	
	// write a binary file
	public int writeBinFile(String VLCcodes){
		int n = 0; // bytes
		try {
			if ((VLCcodes.length() % 8) == 0) {
				n = VLCcodes.length() / 8;
			} else {
				n = (VLCcodes.length() / 8) + 1;
				this.numofZeros = 8 - (VLCcodes.length() % 8);
				for (int i = 0; i < this.numofZeros; i++) {
					VLCcodes += "0";
				}
			}

			byte buffer[] = new byte[n];
			String tempByte = new String();
			// binary string to bytes
			for (int i = 0; i < n - 1; i++) {
				tempByte = VLCcodes.substring(0, 8);
				buffer[i] = (byte) Integer.parseInt(tempByte, 2);
				VLCcodes = VLCcodes.substring(8);
			}
			buffer[n - 1] = (byte) Integer.parseInt(VLCcodes, 2);
			
			// create the file output stream object
			FileOutputStream os = new FileOutputStream("/Users/sundeqing/Downloads/week12.bin");
			// write into the output stream
			os.write(buffer, 0, n);
			// close the output stream
			os.close();
			System.out.println("compression ratio=" + (n * 100.0 / 65536) + "%");

		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (Exception e) {
			System.out.println(e);
		}
		return n;
	}
	
	// read a binary file
	public String readBinFile(int n){
		String VLCcodes = "";
		try {
			// create the file input stream object
			FileInputStream is = new FileInputStream("/Users/sundeqing/Downloads/week12.bin");
			// n is the number of reading bytes			
			byte buf[] = new byte[n];
			
			// read the input stream
			int len = is.read(buf);
			this.numofBytes = len;
			System.out.println("len="+len);

			// bytes to binary string
			for (int i = 0; i < len; i++) {
				int number = buf[i];
				if (number < 0) {
					number += 256;
				}
				String str = Integer.toBinaryString(number);
			
				String zeros = "";
				for (int j = 0; j < (8 - str.length()); j++) {
					zeros += "0";
				}
				str = zeros + str;
				// remove padding zeros 
				if (i == (len - 1)) {
					str = str.substring(0, (8 - this.numofZeros));
				}

				VLCcodes += str;
			}

			// close the input stream
			is.close();
			return VLCcodes;
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
//	public static void main(String[] args){
//		String codes = "10110111010101101010";
//		BinaryFile test = new BinaryFile();
//		test.writeBinFile(codes);
//		String result = test.readBinFile();
//		System.out.println(result);
//		System.out.println(test.getNumofBytes()*1.0/100);
//	}

}
