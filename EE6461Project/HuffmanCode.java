package EE6461Project;
import java.util.*;
 
abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; // the frequency of this tree
    public HuffmanTree(int frequen) { frequency = frequen; }
 
    // compares on the frequency
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}
 
class HuffmanLeaf extends HuffmanTree {
    public final int value; // the pixel integer this leaf represents
 
    public HuffmanLeaf(int frequen, int valu) {
        super(frequen);
        value = valu;
    }
}
 
class HuffmanNode extends HuffmanTree {
    public final HuffmanTree leftSubtree, rightSubtree; // subtrees
 
    public HuffmanNode(HuffmanTree left, HuffmanTree right) {
        super(left.frequency + right.frequency);
        leftSubtree = left;
        rightSubtree = right;
    }
}
 
public class HuffmanCode {
    private Map<Integer, String> dictionary = new HashMap<Integer, String>();
	
	// input is a map of <pixel, frequency>
    private static HuffmanTree buildTree(Map<Integer, Integer> frequencies) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // create leaves
        for(Map.Entry<Integer,Integer> m:frequencies.entrySet())  
        {  
        	if (m.getValue() > 0)
                trees.offer(new HuffmanLeaf(m.getValue(), m.getKey()));
        }  
        
        assert trees.size() > 0;
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree tree1 = trees.poll();
            HuffmanTree tree2 = trees.poll();
 
            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(tree1, tree2));
        }
        return trees.poll();
    }
 
    private static void generateDictionary(HuffmanTree tree, StringBuffer prefix, Map<Integer, String> dictionary) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            // generate the dictionary (code is just the prefix)
            dictionary.put(leaf.value, prefix.toString());
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            generateDictionary(node.leftSubtree, prefix, dictionary);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            generateDictionary(node.rightSubtree, prefix, dictionary);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public void buildDictionary(int[][] inputPixel) {		
		Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
		// read each pixel and record the frequencies
		for (int[] row : inputPixel) {
			for (int column : row) {
				if (frequencies.containsKey(column)) {
					frequencies.put(column, frequencies.get(column) + 1);
				} else {
					frequencies.put(column, 1);
				}
			}
		}
 
        // build tree and dictionary
		HuffmanTree tree = buildTree(frequencies);
		
		generateDictionary(tree, new StringBuffer(), this.dictionary);
 
    }
    
    public Map<Integer, String> getDictionary(){
    	return this.dictionary;
    }
    
    // Huffman encode
    public String encodeVLC(int[][] inputPixel){
    	String result = new String();
    	for (int[] row : inputPixel) {
			for (int column : row) {
				if (this.dictionary.containsKey(column)) {
					result += this.dictionary.get(column);
				} 
			}
		}
    	return result;
    }
    
    // Huffman decode
    public int[][] decodeVLC(String codes, int height, int width){
    	int[][] pixel = new int[height][width];
    	int r=0, c=0;
    	int length = codes.length();
    	// generate the decode dictionary
    	Map<String, Integer> decodeDictionary = new HashMap<String, Integer>();
    	Iterator<Map.Entry<Integer, String>> entries = this.dictionary.entrySet().iterator();
    	while (entries.hasNext()) {
    		Map.Entry<Integer, String> entry = entries.next(); 
    		decodeDictionary.put(entry.getValue(), entry.getKey());
    	}
    	
    	// decode
		while (length > 0) {
			for (int i = 1; i <= length; i++) {
				String tempKey = codes.substring(0, i);
				if (decodeDictionary.containsKey(tempKey)) {
					pixel[r][c] = decodeDictionary.get(tempKey);
					if (c == (width - 1)) {
						r += 1;
						c = 0;
					} else {
						c += 1;
					}
					codes = codes.substring(i);
					length = codes.length();
					break;
				}
			}
		}
    	
    	return pixel;
    }
    
//    public static void main(String[] args){
//		int[][] input = { { 0, 0, 2, }, { 0, 0, 5 }, { 6, 0, 8 }, { 6, 0, 6 }, {0,0,6} };		
//		HuffmanCode test = new HuffmanCode();
//		test.buildDictionary(input);
//		Map<Integer, String> dictionary = test.getDictionary();
//		// print out results
//		System.out.println("SYMBOL\tHUFFMAN CODE");
//		for (Map.Entry<Integer, String> m : dictionary.entrySet()) {
//			System.out.println(m.getKey() + "\t" + m.getValue());
//		}
//		String encodeResult = test.encodeVLC(input, dictionary);
//		System.out.println(encodeResult);
//		
//		int height = input.length;
//		int width = input[0].length;
//		int[][] decode = test.decodeVLC(encodeResult, dictionary, height, width);
//		for(int[] r:decode){
//			for(int c:r){
//				System.out.print(c+" ");
//			}
//			System.out.println( );
//		}
//    }
}