import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ricardo
 * 
 * Interface que herda de Remote para definir metodos que serão utilizados por um Cliente Remoto
 */

public interface JogoDaVelhaClienteInterface extends Remote {

    public void autorizarJogada(JogoDaVelhaServidorInterface servidor) throws RemoteException;

    public void finalizarJogo() throws RemoteException;

    public void getRespostaServidor(String resposta) throws RemoteException;

    public void getJogadaAdversario(int idAdversario, int linha, int coluna) throws RemoteException;

    public void setIdJogador(int id) throws RemoteException;

    public void finalizarProcessoCliente() throws RemoteException;

    public void setTelaJogador(TelaJogador tela) throws RemoteException;
    
    public void resetaTela() throws RemoteException;
    
    public void setServidor(JogoDaVelhaServidorInterface server) throws RemoteException;
    
    public void criarTela(int id) throws RemoteException;
    
    public void setPlacar(String placar) throws RemoteException;
    
    public void tocaMusica(Boolean vencedor) throws RemoteException; 
}
