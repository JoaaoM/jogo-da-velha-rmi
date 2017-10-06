import java.io.BufferedReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author ricardo
 * Classe que herda de UnicastRemoteObject para cirar um cliente de Java RMI,
 * a classe também implementa a interface JogoDaVelhaClienteInterface e define os métodos
 * lá definidos
 */
public class JogoDaVelhaClienteImplementacao extends UnicastRemoteObject implements JogoDaVelhaClienteInterface {

    private int id;
    private TelaJogador tela;

    /*Construtor da classe que chama o construtor de UnicastRemoteObject*/
    protected JogoDaVelhaClienteImplementacao() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    /*Método para setar o Id do jogador*/
    @Override
    public void setIdJogador(int id) throws RemoteException {
        this.id = id;
        tela.setId(id);
    }

    /*Método para permitir que um jogador faça sua jogada*/
    @Override
    public void autorizarJogada(JogoDaVelhaServidorInterface servidor) throws RemoteException {
        tela.getCampoInstrucoes().setText("Faça sua jogada:");
        tela.habilitarBotoesJogador();

    }

    /*Método que finaliza o jogo*/
    @Override
    public void finalizarJogo() throws RemoteException {
        tela.desabilitarBotoesJogador();

    }

    /*Método que modifica o campo de instruções da tela de acordo com a resposta do servidor*/
    @Override
    public void getRespostaServidor(String resposta) throws RemoteException {
        tela.getCampoInstrucoes().setText(resposta);
    }

    /*Método que recebe a jogada do adversario, atualiza e libera a tela do cliente*/
    @Override
    public void getJogadaAdversario(int idAdversario, int linha, int coluna) throws RemoteException {
        tela.setJogadaAdversario(idAdversario, linha, coluna);
        tela.habilitarBotoesJogador();
    }

    /*Método para finalizar o programa do cliente*/
    @Override
    public void finalizarProcessoCliente() throws RemoteException {
        System.exit(1);
    }

    /*Método para atribuir uma tela ao cliente*/
    @Override
    public void setTelaJogador(TelaJogador tela) throws RemoteException {
        this.tela = tela;
    }

}
