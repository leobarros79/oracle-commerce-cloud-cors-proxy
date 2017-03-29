package main.java.com.oracle.proxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.oracle.utils.CommonConstants;

public class GetNewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetNewOrders() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doLogin();
	}

	private String doLogin() {
		try {
			URL url = new URL(CommonConstants.LOGIN_URL);
			try {
				HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
				//add request reader
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				con.setRequestProperty("Authorization", CommonConstants.LOGIN_AUTHORIZATION);
				con.setDoOutput(true);

				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(CommonConstants.LOGIN_PARAMETER);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();

				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + CommonConstants.LOGIN_PARAMETER);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				return response.toString();

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} 

	}
}    

