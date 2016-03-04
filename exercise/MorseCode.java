package exercise;
/*
Assignment 3
 Write a computer program that allows you to enter a string of up to 255 
characters and which encodes it in Morse Code.  The output should be a file 
called MorseOut.txt which only contains dots '.'  dashes '-' and spaces.
Assignment 4
 Write a decoding program to decode a  file MorseIn.txt  in the encoded format 
from the previous assignment.
Exploration  
 
You can encode the morse code as an audio signal by using a tone say 3000Hz for a 100ms period followed
by a  100ms quite period for a dot and the tone for a 300ms period followed by a  100ms quite period as the dash.
Each character space can be a  100ms quite period, the word space as 300ms quite and the sentence space as 500ms
quite.
 
1)    Try adding a sound out to your encoding program to send the information to the computer speakers.  (Difficulty level: Medium)
 
2)    Try sampling and decoding the audio more code you generated.  ( Difficulty level:  High )
 */


import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

/**
 *
    * @author Deqing Sun
 */
public class MorseCode {
    
    @SuppressWarnings("serial")
	private static final HashMap<String, String> dictionary = new HashMap<String, String>(){
        {
        put("A" , ".-"); put("N", "-."); put("0", "-----");
        put("B" , "-..."); put("O", "---"); put("1", ".----");
        put("C" , "-.-."); put("P", ".--."); put("2", "..---");
        put("D" , "-..");  put("Q", "--.-"); put("3", "...--");
        put("E" , ".");  put("R", ".-."); put("4", "....-");
        put("F" , "..-."); put("S", "..."); put("5", ".....");
        put("G" , "--.");  put("T", "-"); put("6", "-....");
        put("H" , "...."); put("U", "..-"); put("7", "--...");
        put("I" , ".."); put("V", "...-"); put("8", "---..");
        put("J" , ".---"); put("W", ".--"); put("9", "----.");
        put("K" , "-.-");  put("X", "-..-");
        put("L" , ".-.."); put("Y", "-.--");
        put("M" , "--");  put("Z", "--..");
        }
    };   
    
    public String encode(String inputString){
        String result = new String();        
        String input = inputString.toUpperCase();
        if(input.length() < 256){
            for(int i=0;i<input.length();i++){
                String subStr = input.substring(i, i+1);
                
                if(dictionary.containsKey(subStr)){
                    result += dictionary.get(subStr) + " ";
                }
                else if(subStr.equals(" ")){
                    result += "  ";
                }
                else if(subStr.equals(".")){
                    result += "   ";
                }
            }            
        }
        else{
            System.out.println("The length of input string should be no more than 255!");            
        }
        return result;
    }
    
    public String decode(String input){
        String result = new String();
        String[] sentences = input.split("\\s{3}");
        for(String s:sentences){
        	String[] words = s.split("\\s{2}");
    		for(String w:words){
    			String[] characters = w.split("\\s");
    			for(String c:characters){
    				for(@SuppressWarnings("rawtypes") Map.Entry entry:dictionary.entrySet()){
    					if(c.equals(entry.getValue())){
    						result += entry.getKey();
    					}
    						
    				}
    			}
    			result += " ";
    		}
    		result += ".";
        }
        
		return result;
    }
    
    // the sound of a dot
    private void dotTone() throws LineUnavailableException, InterruptedException{
    	SoundUtils dot = new SoundUtils();
    	dot.tone(3000, 100);
    	Thread.sleep(100);
    }
    
    // the sound of a dash
    private void dashTone() throws LineUnavailableException, InterruptedException{
    	SoundUtils dash = new SoundUtils();
    	dash.tone(3000, 300);
    	Thread.sleep(100);
    }
    
    public void soundOut(String encode) throws InterruptedException, LineUnavailableException{
    	String[] sentences = encode.split("\\s{3}");
    	for(String s:sentences){
    		String[] words = s.split("\\s{2}");
    		for(String w:words){
    			String[] characters = w.split("\\s");
    			for(String c:characters){
    				for(int i=0; i<c.length(); i++){
    					String oneSymbol = c.substring(i, i+1);
    					if(oneSymbol.equals(".")){
        					dotTone();
        				}
        				else if(oneSymbol.equals("-")){
        					dashTone();
        				}
        				Thread.sleep(100);
    				}
    				
    			}
    			Thread.sleep(300);
    		}
    		Thread.sleep(500);
    	}
    }
    
    // inner class of sound
	class SoundUtils {

		public float SAMPLE_RATE = 8000f;

		public void tone(int hz, int msecs) throws LineUnavailableException {
			tone(hz, msecs, 1.0);
		}

		public void tone(int hz, int msecs, double vol) throws LineUnavailableException {
			byte[] buf = new byte[1];
			AudioFormat af = new AudioFormat(SAMPLE_RATE, // sampleRate
					8, // sampleSizeInBits
					1, // channels
					true, // signed
					false); // bigEndian
			SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			for (int i = 0; i < msecs * 8; i++) {
				double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
				sdl.write(buf, 0, 1);
			}
			sdl.drain();
			sdl.stop();
			sdl.close();
		}
	}

    /**
     * @param args the command line arguments
     * @throws LineUnavailableException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException, LineUnavailableException {
        // TODO code application logic here
        String encode1 = "THE ACE OF SPADES.";
        MorseCode test1 = new MorseCode();
        String result1 = test1.encode(encode1);
        System.out.println(result1); 
        String encode2 = "WHERE ARE WE TO GO FROM HERE IN TIME.";
        String encodeResult = test1.encode(encode2);
        System.out.println(encodeResult);
        test1.soundOut(encodeResult);
		String decodeString = ".. ..-.  -.-- --- ..-  .-.. .. -.- .  - ---  --. .- -- -... .-.. .   ..  - . .-.. .-..  -.-- --- ..-  ..  .- --  -.-- --- ..- .-.  -- .- -.    .-- .. -.  ... --- -- .  .-.. --- ... .  ... --- -- . ";
		System.out.println(test1.decode(decodeString));
    }
    
}
