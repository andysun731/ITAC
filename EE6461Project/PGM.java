package EE6461Project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A PGM file is a text file with the general format
 * 
 *   P2
 *   width  height  
 *   maxvalue
 *   (width * height values, separated by whitespace)
 *   
 */
public class PGM {
  // maximum greyscale value
  private static final int MAXVALUE = 255;

  /**
   * Write a PGM file from the given image.
   * @param imageArray
   *   the array of image pixels
   * @param filename
   *   name of the file to be written
   * @throws FileNotFoundException
   */
	public static void writeFile(int[][] imageArray, String filename) throws FileNotFoundException {
		PrintWriter pwrite = new PrintWriter(filename);
		int width = imageArray[0].length;
		int height = imageArray.length;

		// P2, width, height, and maxvalue
		pwrite.println("P2");
		pwrite.println(width + " " + height);
		pwrite.println(MAXVALUE);
		
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				int value = imageArray[i][j];
				pwrite.print(value + " ");
			}
			pwrite.println();
		}
		pwrite.close();
	}
  
  /**
   * Read a PGM file and returns the array of image pixels. 
   * @param filename
   * @return imageArray
   *   the array of image pixels
   * @throws FileNotFoundException
   */
	public static int[][] readFile(String filename) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(filename));
		scanner.next(); // pass P2
		int width = scanner.nextInt();
		int height = scanner.nextInt();
		int max = scanner.nextInt();

		int[][] imageArray = new int[height][width];

		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				// make sure it is not greater than 255
				int value = scanner.nextInt();
				value = (int) Math.round(((double) value) / max * MAXVALUE);
				imageArray[i][j] = value;
			}
		}
		return imageArray;
	}
  
  /**
   * Zero the areas f and g.
   * @param input
   *   the array of pixels
   */ 
  public static void setZeros(int[][] input){
		int n = input[0].length;
		for (int r = n / 2; r < n; r++) {
			for (int c = 0; c < n; c++) {
				input[r][c] = 0;
			}
		}
		for (int r = 0; r < n / 2; r++) {
			for (int c = n / 2; c < n; c++) {
				input[r][c] = 0;
			}
		}
  }
  
}