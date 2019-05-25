import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

//$Id$

public class BookMovieTicket {
	public static class Movie{
		private String Poster;
		private String Title ;
		private String Type;
		private int Year;
		private String imdbID;
		public Movie(JSONObject movie)
		{
			try{
				Poster=movie.getString("Poster");
				Title=movie.getString("Title");
				Type=movie.getString("Type");
				Year=movie.getInt("Year");
				imdbID=movie.getString("imdbID");
			}
			catch(Exception e){
			}
		}
		public String getTitle(){
			return Title;
		}
	}
	public static class SortbyTitle implements Comparator<Movie> 
	{ 
		public int compare(Movie a, Movie b) 
	    { 
	        return a.getTitle().compareTo(b.getTitle());
	    } 
	} 
	public static void main(String[] args) {
		List<Movie> result= new ArrayList<Movie>();
		//Arrays.sort(result.toArray());
		Scanner reader = new Scanner(System.in);		
		String substr=reader.nextLine();
		List<Movie> currentResult= new ArrayList<Movie>();
		int total_pages=getMovieName(substr,1,currentResult);
		result.addAll(currentResult);
		for(int i=2;i<=total_pages;i++){
			getMovieName(substr,i,currentResult);
			result.addAll(currentResult);
		}
		Collections.sort(result, new SortbyTitle()); 
		for(Movie movie:result){
			System.out.println(movie.getTitle());
		}
	}
	private static int getMovieName(String substr, int pageNumber,List<Movie> currentResult){
		try{
			URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title="+substr+"&page="+pageNumber);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			content.toString();
			JSONObject result=new JSONObject(content.toString());
			JSONArray data=result.getJSONArray("data");
			for(int i=0;i<data.length();i++){
				JSONObject movieData=data.getJSONObject(i);
				currentResult.add(new Movie(movieData));
			}
			return result.getInt("total_pages");
		}
		catch(Exception e){
			
		}
		return 0;
	}
}
