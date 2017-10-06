import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ricardo
 * 
 * Interface que herda de Remote para definir metodos/serviçoes que serão utilizados pelo servidor RMI
 */
public interface JogoDaVelhaServidorInterface extends Remote {

    public void logar(JogoDaVelhaClienteInterface cliente) throws RemoteException;

    public void jogar(int id, int linha, int coluna) throws RemoteException;

    public void deslogar(JogoDaVelhaClienteInterface cliente) throws RemoteException;

}
