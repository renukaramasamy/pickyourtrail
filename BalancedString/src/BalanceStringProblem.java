import java.io.InputStream;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONTokener;

//$Id$

public class BalanceStringProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			InputStream is = BalanceStringProblem.class.getResourceAsStream("input.json");
			if (is == null) {
	            System.out.println("Invalid input");
	        }
			JSONTokener tokener = new JSONTokener(is);
	        JSONArray inputJson = new JSONArray(tokener);
	        for(int i=0;i<inputJson.length();i++){
	        	String str=inputJson.getString(i);
	        	System.out.println("String "+(i+1) +":\t"+str +" ---> "+isBalanceString(str)); 
	        }
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("e");
		}
	}
	private static boolean isBalanceString(String str){
		Pattern p = Pattern.compile("a|c");
        Matcher m = p.matcher(str.toLowerCase());
        int ac=0;
        while(m.find()){
        	ac++;
        }
        p = Pattern.compile("b|d");
        m = p.matcher(str.toLowerCase());
        int bd=0;
        while(m.find()){
        	bd++;
        }
        if(ac %2 == 0 && bd % 2==0){
        	return true;
        }
        return false;
	}
}
