package exercise;
import java.util.ArrayList;
     import java.util.HashMap;
     import java.util.Iterator;
     import java.util.LinkedHashMap;
     import java.util.List;
     import java.util.Map;
     
public class ShannonFano {
public Map compress(Map freq) {
		
		Map<String, String> result = new LinkedHashMap<String, String>();
		List<String> charList = new ArrayList<String>();
		List<Double> probabilities =new ArrayList<Double>();
		
		Iterator entries = freq.entrySet().iterator();
		while( entries.hasNext() ) {
			Map.Entry<String, Double> entry = (Map.Entry)entries.next();
			charList.add(entry.getKey());
			probabilities.add(entry.getValue());
		}
		
		addBit(result, charList, probabilities, true);
		
		return result;
	}

	private double sumProb(List<Double> prob){
		double sumResult = 0.0;
		for(Double p:prob){
			sumResult += p;
		}
		return sumResult;
	}
	
	private void addBit(Map<String, String> result, List<String> charList, List<Double> probabilities, boolean up) {
		String bit = "";
		if( !result.isEmpty() ) {
			bit = (up) ? "0" : "1";
		}
		
		for( String c : charList ) {
			String s = (result.get(c) == null) ? "" : result.get(c);
			result.put(c, s + bit);
		}
		
		if( charList.size() >= 2 ) {
			int separator = 0;
			for(int i=0;i<probabilities.size();i++){
				if(sumProb(probabilities.subList(0, i)) >= sumProb(probabilities.subList(i, probabilities.size()))){
					separator = i;
					break;
				}
			}
			
			List<String> upList = charList.subList(0, separator);
			List<Double> upproList = probabilities.subList(0, separator);
			addBit(result, upList, upproList, true);
			List<String> downList = charList.subList(separator, charList.size());
			List<Double> downproList = probabilities.subList(separator, probabilities.size());
			addBit(result, downList, downproList, false);
		}
	}
	
	public static void main(String[] args){
		ShannonFano encode = new ShannonFano();
		Map<String, Double> freq = new LinkedHashMap<String, Double>();
//		freq.put("s1", 0.5);
//		freq.put("s2", 0.2);
//		freq.put("s3", 0.1);
//		freq.put("s4", 0.1);
//		freq.put("s5", 0.1);
		freq.put("s1", 0.5);
		freq.put("s2", 0.1);
		freq.put("s3", 0.1);
		freq.put("s4", 0.1);
		freq.put("s5", 0.1);
		freq.put("s6", 0.05);
		freq.put("s7", 0.025);
		freq.put("s8", 0.025);
		Map<String, String> result = encode.compress(freq);
		Iterator entries = result.entrySet().iterator();
		while( entries.hasNext() ) {
			Map.Entry entry = (Map.Entry)entries.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
	}

}
