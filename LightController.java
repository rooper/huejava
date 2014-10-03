import java.io.IOException;
import java.io.InputStream;
import java.net.*;


public class LightController {
	String user = "loganrooper";
	String url = "http://10.181.25.128/api/" + user + "/";
	String charset = "UTF-8";
	
	/*
	 * Gets the state of all the lights.
	 */
	public String getLightsState() throws IOException{
		String query = "lights";
		return sendGet(query);
	}
	
	/**
	 * Sends a restful get.
	 * @return The restful response.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public String sendGet(String query) throws MalformedURLException, IOException {
		URLConnection connection = new URL(url + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();
		
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
		}
		
		String s = new String(output);
		return s.toString();
	}
	
	public static void main(String[] args) throws IOException {
		LightController lc = new LightController();
		System.out.println(lc.getLightsState());
	}
	
}
