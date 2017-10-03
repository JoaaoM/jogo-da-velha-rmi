import java.io.BufferedReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author ricardo
 */
public class JogoDaVelhaClienteImplementacao extends UnicastRemoteObject implements JogoDaVelhaClienteInterface {

    private int id;
    private TelaJogador tela;

    protected JogoDaVelhaClienteImplementacao() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void setIdJogador(int id) throws RemoteException {
        this.id = id;
        tela.setId(id);
    }

    @Override
    public void autorizarJogada(JogoDaVelhaServidorInterface servidor) throws RemoteException {
        tela.getCampoInstrucoes().setText("Faça sua jogada:");
        tela.habilitarBotoesJogador();

    }

    @Override
    public void finalizarJogo() throws RemoteException {
        tela.desabilitarBotoesJogador();

    }

    @Override
    public void getRespostaServidor(String resposta) throws RemoteException {
        tela.getCampoInstrucoes().setText(resposta);
    }

    @Override
    public void getJogadaAdversario(int idAdversario, int linha, int coluna) throws RemoteException {
        tela.setJogadaAdversario(idAdversario, linha, coluna);
        tela.habilitarBotoesJogador();
    }

    @Override
    public void finalizarProcessoCliente() throws RemoteException {
        System.exit(1);
    }

    @Override
    public void setTelaJogador(TelaJogador tela) throws RemoteException {
        this.tela = tela;
    }

}
