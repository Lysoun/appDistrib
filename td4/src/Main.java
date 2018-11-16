package microServerWeb;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Main{
	private static final int port = 1237;
    public static int nbThreads = 5;
    private static ExecutorService pool = Executors.newFixedThreadPool(nbThreads);
    private static ServerSocket serverSocket; // Pour pouvoir fermer la socket
    
    public static void start() throws InterruptedException, ExecutionException{
	try{
	    serverSocket = new ServerSocket(port);
	    System.out.println("Démarrage du serveur...");
	    
	    while(true){
			System.out.println("En attente d'une nouvelle connexion...");    
			Socket socket = serverSocket.accept();
			System.out.println("Un client s'est connecté. Lecture du contenu...");
			pool.submit(new Worker(socket));
	    }
	} catch(Exception e){
	    System.out.println("Erreur lors de la connexion au serveur : " + e);
	    e.printStackTrace();
	}
    }                                                                         

    // Quand appeler cette methode ?
    // Idéalement, capter un flag correspondant à CTRL+C ou un prompt de l'utilisateur
    /*
    public static void stop(){ 
	System.out.println("Fermeture du pool.");	
	pool.shutdown();
	try{
	    serverSocket.close();
	    System.out.println("Serveur fermé.");
	} catch(Exception e){
	    System.out.println("Erreur lors de la fermeture du serveur : " + e);
	    e.printStackTrace();
	}
	
	System.out.println("Fin.");
	System.exit(0);
    }
    */
    
    public static void main(String[] args){
        try {
            start();
        }
        catch(Exception e){
	    System.out.println("Il semble que quelque chose se soit mal passé... : " + e);
        }
    }
}