package EE6461Project;

public class Quantization {
	/**
	 * Quantize the array of pixels. 
	 * a:14->10, b:14->8, c:14->6, d:12->4,e:12->4, f:10->4, g:10->0 bits 
	 * a: /16, b: /64, c: /256, d: /256, e: /256,f: /64, g: 0
	 * 
	 * @param input
	 *            the array of pixels
	 */
	public static void quantize(int[][] input) {
		int height = input.length;
		int width = input[0].length;
		// quantize g area
		for (int gr = height / 2; gr < height; gr++) {
			for (int gc = width / 2; gc < width; gc++) {
				input[gr][gc] = 0;
			}
		}
		
		// quantize f area
		for (int fr = 0; fr < height / 2; fr++) {
			for (int fc = width / 2; fc < width; fc++) {
				input[fr][fc] = input[fr][fc] / 64;
			}
		}
		for (int fr = height / 2; fr < height; fr++) {
			for (int fc = 0; fc < width / 2; fc++) {
				input[fr][fc] = input[fr][fc] / 64;
			}
		}
		
		// quantize e area
		for (int er = height / 4; er < height / 2; er++) {
			for (int ec = width / 4; ec < width / 2; ec++) {
				input[er][ec] = input[er][ec] / 256;
			}
		}
		
		// quantize d area
		for (int dr = 0; dr < height / 4; dr++) {
			for (int dc = width / 4; dc < width / 2; dc++) {
				input[dr][dc] = input[dr][dc] / 256;
			}
		}
		for (int dr = height / 4; dr < height / 2; dr++) {
			for (int dc = 0; dc < width / 4; dc++) {
				input[dr][dc] = input[dr][dc] / 256;
			}
		}
		
		// quantize c area
		for (int cr = height / 8; cr < height / 4; cr++) {
			for (int cc = width / 8; cc < width / 4; cc++) {
				input[cr][cc] = input[cr][cc] / 256;
			}
		}
		
		// quantize b area
		for (int br = 0; br < height / 8; br++) {
			for (int bc = width / 8; bc < width / 4; bc++) {
				input[br][bc] = input[br][bc] / 64;
			}
		}
		for (int br = height / 8; br < height / 4; br++) {
			for (int bc = 0; bc < width / 8; bc++) {
				input[br][bc] = input[br][bc] / 64;
			}
		}
		
		// quantize a area
		for (int ar = 0; ar < height / 8; ar++) {
			for (int ac = 0; ac < width / 8; ac++) {
				input[ar][ac] = input[ar][ac] / 16;
			}
		}
	}
	
	/**
	 * Rescale the array of pixels. 
	 * a:14<-10, b:14<-8, c:14<-6, d:12<-4,e:12<-4, f:10<-4, g:10<-0 bits 
	 * a: *16, b: *64, c: *256, d: *256, e: *256,f: *64, g: *0
	 * 
	 * @param input
	 *            the array of quantized pixels
	 */
	public static void rescale(int[][] input) {
		int height = input.length;
		int width = input[0].length;
		
		// quantize f area
		for (int fr = 0; fr < height / 2; fr++) {
			for (int fc = width / 2; fc < width; fc++) {
				input[fr][fc] = input[fr][fc] * 64;
			}
		}
		for (int fr = height / 2; fr < height; fr++) {
			for (int fc = 0; fc < width / 2; fc++) {
				input[fr][fc] = input[fr][fc] * 64;
			}
		}
		
		// quantize e area
		for (int er = height / 4; er < height / 2; er++) {
			for (int ec = width / 4; ec < width / 2; ec++) {
				input[er][ec] = input[er][ec] * 256;
			}
		}
		
		// quantize d area
		for (int dr = 0; dr < height / 4; dr++) {
			for (int dc = width / 4; dc < width / 2; dc++) {
				input[dr][dc] = input[dr][dc] * 256;
			}
		}
		for (int dr = height / 4; dr < height / 2; dr++) {
			for (int dc = 0; dc < width / 4; dc++) {
				input[dr][dc] = input[dr][dc] * 256;
			}
		}
		
		// quantize c area
		for (int cr = height / 8; cr < height / 4; cr++) {
			for (int cc = width / 8; cc < width / 4; cc++) {
				input[cr][cc] = input[cr][cc] * 256;
			}
		}
		
		// quantize b area
		for (int br = 0; br < height / 8; br++) {
			for (int bc = width / 8; bc < width / 4; bc++) {
				input[br][bc] = input[br][bc] * 64;
			}
		}
		for (int br = height / 8; br < height / 4; br++) {
			for (int bc = 0; bc < width / 8; bc++) {
				input[br][bc] = input[br][bc] * 64;
			}
		}
		
		// quantize a area
		for (int ar = 0; ar < height / 8; ar++) {
			for (int ac = 0; ac < width / 8; ac++) {
				input[ar][ac] = input[ar][ac] * 16;
			}
		}
	}

}
