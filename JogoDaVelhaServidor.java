import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ricardo
 * 
 * Classe que inicializa o servidor
 */
public class JogoDaVelhaServidor {
	
	/*Informações que serão utilizadas para definir o servidor*/
    private static String ip = "localhost";
    private static String porta = "1099";
    private static String nomeServico = "JogoDaVelha";

    public static void main(String args[]) {
        JogoDaVelhaServidorInterface server;

        /*Tenta pegar o registro RMI a partir da porta que o servidor escutara. Após isto,
         * o servidor é inicializado e então é feito um bind entre o serviço e o servidor*/
        try {
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(porta));
            registry.list();
            String url = "rmi://" + ip + ":" + porta + "/" + nomeServico;
            server = new JogoDaVelhaServidorImplementacao();
            Naming.rebind(url, server);
            //System.setProperty("java.rmi.server.hostname",ip);
        } catch (RemoteException e) {
            System.out.println("Remote Exception: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("URL mal formada: " + e.getMessage());
        }

    }

}
