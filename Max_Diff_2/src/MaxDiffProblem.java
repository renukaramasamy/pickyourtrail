import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

//$Id$

public class MaxDiffProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++)
		{
			a[i]=reader.nextInt();
		}
		System.out.println(maxDifference(a));*/
		
		try{
			InputStream is = MaxDiffProblem.class.getResourceAsStream("input.json");
			if (is == null) {
	            System.out.println("Invalid input");
	        }
			JSONTokener tokener = new JSONTokener(is);
	        JSONArray inputJson = new JSONArray(tokener);
	        int numInputs=inputJson.length();
	        for(int i=0;i<numInputs ;i++ ){
	        	JSONObject obj=(JSONObject) inputJson.get(i);
	        	int n=obj.getInt("length");
	        	JSONArray json_a= obj.getJSONArray("array");
	        	System.out.println("Array "+ (i+1) + " :"+json_a.toString());
	        	int a[]=new int[n];
	        	for(int j=0;j<n;j++){
	        		a[j]=json_a.getInt(j);
	        	}
	        	System.out.println(maxDifference(a));
	        }
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("e");
		}
	}
	private static int maxDifference(int[] a){
		int n=a.length;
		int[] minArray=new int [n];
		int[] maxArray=new int [n];
		minArray[0]=a[0];
		maxArray[n-1]=a[n-1];
		for(int i=1;i<n;i++)
		{
			int minValue=minArray[i-1] < a[i] ? minArray[i-1] : a[i];
			minArray[i]=minValue;
			int maxValue=maxArray[n-i] > a[n-1-i] ? maxArray[n-i] : a[n-1-i] ;
			maxArray[n-1-i]=maxValue;
		}
		int maxdiff=-1;
		for(int i=0;i<n;i++)
		{
			int curDiff=maxArray[i] - minArray[i];
			maxdiff= curDiff > maxdiff ? curDiff:maxdiff;
		}
		if(maxdiff == 0)
			return -1;
		return maxdiff;
	}

}
