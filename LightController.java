import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.*;

public class LightController {
	String user = "loganrooper";
	String url = "http://10.181.25.128/api/" + user + "/";
	String charset = "UTF-8";
		
	/*
	 * Gets the state of all the lights.
	 */
	public String getLightsState() {
		//per http://www.developers.meethue.com/documentation/getting-started
		String query = "lights";
		return sendGet(query);
	}
	
	public String[] getLightList() {
		String parseMe = getLightsState();
		//Parse state string as json object
		Object obj=JSONValue.parse(parseMe);
		JSONArray array=(JSONArray)obj;
		
		//Get list of lights
		System.out.println(array.toString());
		
		return new String[0];
	}
	
	/**
	 * Sends a restful get.
	 * @return The restful response.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public String sendGet(String query) {
		try {
			URLConnection connection = new URL(url + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			
			InputStream response = null;
			response = connection.getInputStream();
			byte[] output = new byte[0];
			while (response.available() > 0) {
				byte[] b = new byte[response.available()];
				response.read(b);
				byte[] outBuffer =  output;
				output = new byte[output.length + b.length];
				
				for (int i = 0; i < outBuffer.length; i++) {
					output[i] = outBuffer[i];
				}
				
				for (int j = 0; j < b.length; j++) {
					output[outBuffer.length + j] = b[j];
				}
			
			String s = new String(output);
			return s.toString();
			}
			
		} catch (Exception IOException) {
			System.out.println("Connection lost.");
			return null;
		}	
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		LightController lc = new LightController();
		System.out.println(lc.getLightsState());
		lc.getLightList();
	}
	
}
