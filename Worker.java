package microServerWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;

class Worker implements Runnable {
	private Socket socket;
	private final static String ressourcesFolder = "res";

	Worker(Socket connexion) {
		this.socket = connexion;
	}

	public byte[] getContent(String request) {
		byte[] ans = null;
		// Split pour obtenir le chemin
		String filepath = "";
		String content = "";
		Hashtable<String, String> parameters = new Hashtable<String, String>();
		String[] parsedTokens = request.split("\n"); // par lignes
		parsedTokens = parsedTokens[0].split(" "); // par mots sur la 1ere ligne

		System.out.println("PARSEDTOKENS[0] : " + parsedTokens[0]);
		System.out.println("PARSEDTOKENS[1] : " + parsedTokens[1]);
		
		parsedTokens = parsedTokens[1].split("\\?");
		filepath = parsedTokens[0];
		parsedTokens = parsedTokens[1].split("&");
		
		for(String token : parsedTokens) {
			String[] parameter = token.split("=");
			parameters.put(parameter[0], parameter[1]);
		}
		
		System.out.println("FILEPATH : " + filepath);

		if (filepath.equals("/")) {
			System.out.println("Traitement de la requete en cours ..." + filepath);
			filepath = "/main.html";
		}

		if (filepath.endsWith(".html")) {
			content = getFileContent(filepath);
		}
		
		content = replaceParameters(content, parameters);

		ans = ("HTTP/1.1 200 OK\n Content-Length: " + content.length() + "\n Content-Type: text/html\n\n" + content)
				.getBytes();

		return ans;
	}
	
	private String replaceParameters(String content, Hashtable<String, String> parameters) {
		Iterator<String> i = parameters.keys().asIterator();
		
		System.out.println("Ouaaaais");

		while(i.hasNext()) {
			String key = i.next();
			System.out.println("$\\{" + key + "\\}");
			System.out.println("avant :\n" + content);
			content = content.replaceAll("\\$\\{" + key + "\\}", parameters.get(key));
			System.out.println("après :\n" + content);
		}
		
		return content;
	}

	private String getFileContent(String fileName) {
		File file = new File(ressourcesFolder + fileName);
		String res = "";
		try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
			String line = fileReader.readLine();
			while (line != null) {
				res += line;
				line = fileReader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Requested invalid path.");
			res = "<h1> Erreur 404 : La page demandee n'existe pas </h1>"
					+ "<h3> A la place, voici la date et l'heure d'aujourd'hui : </h3>"
					+ "<script language=\"javascript\">" + "var today = new Date(); document.write(today);"
					+ "</script>";

		}

		return res;
	}

	@Override
	public void run() {
		try {
			System.out.println("[" + Thread.currentThread().getName() + "] commence à travailler.");
			// Thread.sleep(10*1000);

			InputStream is = socket.getInputStream();
			byte bytes[] = new byte[1024];
			is.read(bytes);
			String request = new String(bytes);
			System.out.println("Le client demande : " + request);

			OutputStream os = socket.getOutputStream();
			byte content[] = getContent(request);

			os.write(content); // Lit les bytes
			os.flush();

			is.close();
			os.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion : " + e);
			e.printStackTrace();
		}

		System.out.println("[" + Thread.currentThread().getName() + "] a fini !");
		return;
	}
}
