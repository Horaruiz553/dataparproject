package py.com.local.datapar.util;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jboss.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import py.com.local.datapar.core.model.Usuario;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class HttpsClientActive {

	public void conectToAdministration(String cedula, int det)
			throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting Host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting Host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		/* End of the fix */

		// URL url = new URL("https://181.94.245.209:8444/ConsultaAlumnoMora.php");

		URL url;
		URLConnection urlConn = null;
		HttpURLConnection htcon = null;
		InputStream is = null;
		StringBuffer sb = new StringBuffer();
		String authStr = "Servicio:servicio123";

		String authStringEnc = Base64.encodeBytes(authStr.getBytes());

		// String authStringEnc = new
		// String(Base64Encoder.encode(authString.getBytes()));
		try {
			url = new URL("https://181.94.245.209:8444/ConsultaAlumnoMora.php");
			urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestProperty("Authorization", "Basic " + authStringEnc);
			// urlConn.setRequestMethod("GET");

			urlConn.setRequestProperty("user-agent", "Mozilla/5.0");
			urlConn.setRequestProperty("Content-Type", "application/json");

			String input = "[{\"cedula\": " + cedula + "}]";

			htcon = (HttpURLConnection) urlConn;

			htcon.setDoInput(true);
			htcon.setRequestMethod("GET");
			htcon.setDoOutput(true);
			htcon.setRequestProperty("Authorization", "Basic " + authStringEnc);
			// urlConn.setRequestMethod("GET");

			htcon.setRequestProperty("user-agent", "Mozilla/5.0");
			htcon.setRequestProperty("Content-Type", "application/json");

			OutputStream os = htcon.getOutputStream();
			os.write(input.getBytes("UTF-8"));
			os.close();

			/*
			 * is = htcon.getInputStream(); InputStreamReader isr = new
			 * InputStreamReader(is);
			 * 
			 * 
			 * 
			 * int numCharsRead; char[] charArray = new char[1024];
			 * 
			 * while ((numCharsRead = isr.read(charArray)) > 0) { sb.append(charArray, 0,
			 * numCharsRead); }
			 */
			InputStream in = new BufferedInputStream(htcon.getInputStream());
			String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
			Gson gson = new Gson();
			JsonParser jsonParser = new JsonParser();
			try {
				JsonArray jsonArray = (JsonArray) jsonParser.parse(result);
				JsonObject obj = (JsonObject) jsonParser.parse(jsonArray.get(0).toString());
				String cantCuota = obj.get("cuotas_pendientes").toString().replaceAll("\"", "");
				if (Integer.parseInt(cantCuota) >= 1) {
					
				}else {
					//activarUsuario(cedula);
					System.out.println(
							det + " ) DATA ==>> " + cedula + " ==>> " + Integer.parseInt(cantCuota));
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
			}

			in.close();
			htcon.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void activarUsuario(String cedula) {
		DBConnection dBConnection = new DBConnection();
		try {
			try {
				Statement statement = dBConnection.getConnection().createStatement();
				String sql = "UPDATE mo_user SET suspended=0 WHERE username='" + cedula + "'";
				//System.out.println(sql);
				int i = statement.executeUpdate(sql);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
			dBConnection.getConnection().close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getLocalizedMessage());
			System.out.println("ERROR: " + e.fillInStackTrace());
		} finally {

		}
	}

}
