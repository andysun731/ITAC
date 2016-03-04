package EE6461Project;

/*
 * Harr Transform:
 * A=a+b
 * B=a-b
 */
public class HarrTransform {
	/**
	 * Transform one time.
	 * 
	 * @param input
	 *        the array of pixels
	 * @param n
	 *        the area of the array, which needs to be transformed
	 */
	public static void Transform(int[][] input) {
		int height = input.length;
		int width = input[0].length;
		int[] tempRow = new int[width];

		// transform every row
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c += 2) {
				tempRow[c / 2] = input[r][c] + input[r][c + 1];
				tempRow[c / 2 + width / 2] = input[r][c] - input[r][c + 1];
			}
			for (int i = 0; i < width; i++) {
				input[r][i] = tempRow[i];
			}

		}

		// transform every column
		int[] tempColumn = new int[height];
		for (int c = 0; c < width; c++) {
			for (int r = 0; r < height; r += 2) {
				tempColumn[r / 2] = input[r][c] + input[r + 1][c];
				tempColumn[r / 2 + height / 2] = input[r][c] - input[r + 1][c];
			}
			for (int i = 0; i < height; i++) {
				input[i][c] = tempColumn[i];
			}

		}

	}

	/**
	 * Inverse Transform one time.
	 * 
	 * @param input
	 *        the array of pixels
	 * @param n
	 *        the area of the array, which needs to be inverse transformed
	 */
	public static void InverseTransfor(int[][] input) {
		int height = input.length;
		int width = input[0].length;
		int[] tempColumn = new int[height];

		// inverse transform every column
		for (int c = 0; c < width; c++) {
			for (int r = 0; r < height / 2; r++) {
				tempColumn[r * 2] = (input[r][c] + input[r + height / 2][c]) / 2;
				tempColumn[r * 2 + 1] = (input[r][c] - input[r + height / 2][c]) / 2;
			}
			for (int i = 0; i < height; i++) {
				input[i][c] = tempColumn[i];
			}

		}

		// inverse transform every row
		int[] tempRow = new int[width];
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width / 2; c++) {
				tempRow[c * 2] = (input[r][c] + input[r][c + width / 2]) / 2;
				tempRow[c * 2 + 1] = (input[r][c] - input[r][c + width / 2]) / 2;
			}
			for (int i = 0; i < width; i++) {
				input[r][i] = tempRow[i];
			}

		}

	}

}
