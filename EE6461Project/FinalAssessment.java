package EE6461Project;

import java.io.FileNotFoundException;

public class FinalAssessment {
	/**
	 * Read in a PGM file.
	 * Run the 3 transform iterations.
	 * Apply the quantization levels.
	 * VLC encode the samples.
	 * Write the codes to a binary file.
	 * @throws FileNotFoundException 
	 */
	public static void program1(int[] parameters, HuffmanCode huffman, BinaryFile binFile) throws FileNotFoundException {
		int[][] pixel = PGM.readFile("/Users/sundeqing/Downloads/week12.pgm");
		parameters[0] = pixel.length;
		parameters[1] = pixel[0].length;
		System.out.println("height="+parameters[0]+" width="+parameters[1]);
		HarrTransform.Transform(pixel);
		HarrTransform.Transform(pixel);
		HarrTransform.Transform(pixel);
		PGM.writeFile(pixel, "/Users/sundeqing/Downloads/aftertransform.pgm");

		Quantization.quantize(pixel);
		huffman.buildDictionary(pixel);
		PGM.writeFile(pixel, "/Users/sundeqing/Downloads/afterquantization.pgm");
		
		String VLCcodes = huffman.encodeVLC(pixel);
		parameters[2] = binFile.writeBinFile(VLCcodes);
	}
	
	/**
	 * Read the codes binary file.
	 * VLC decode the samples.
	 * Rescale the samples.
	 * Run the 3 inverse transform iterations.
	 * Write out a PGM file.
	 * @throws FileNotFoundException 
	 */
	public static void program2(int[] parameters, HuffmanCode huffman, BinaryFile binFile) throws FileNotFoundException {
		
		int height = parameters[0], width = parameters[1], bytes = parameters[2];
		String VLCcodes = binFile.readBinFile(bytes);
		int[][] pixel = new int[height][width];
		pixel = huffman.decodeVLC(VLCcodes, height, width);
		Quantization.rescale(pixel);
		HarrTransform.InverseTransfor(pixel);
		HarrTransform.InverseTransfor(pixel);
		HarrTransform.InverseTransfor(pixel);
		PGM.writeFile(pixel, "/Users/sundeqing/Downloads/week12rr.pgm");
	}

	public static void main(String[] args) throws FileNotFoundException {

		int[] parameters = new int[3]; // parameters[0] is height, parameters[1] is width, parameters[2] is bytes
		HuffmanCode huffman = new HuffmanCode();
		BinaryFile binFile = new BinaryFile();
		program1(parameters, huffman, binFile);
		program2(parameters, huffman, binFile);

	}

}
