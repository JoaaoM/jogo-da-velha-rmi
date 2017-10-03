import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class JogoDaVelhaServidorImplementacao extends UnicastRemoteObject implements JogoDaVelhaServidorInterface {

    private int tabuleiro[][];
    private int numeroJogada;
    private Map<Integer, JogoDaVelhaClienteInterface> jogadores;
    private int idJogador;

    private static final long serialVersionUID = 1L;

    protected JogoDaVelhaServidorImplementacao() throws RemoteException {
        super();
        jogadores = new HashMap<Integer, JogoDaVelhaClienteInterface>();
        idJogador = 0;

        tabuleiro = new int[3][3];
        numeroJogada = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = 9;
            }
        }
    }

    @Override
    public synchronized void logar(JogoDaVelhaClienteInterface cliente) throws RemoteException {
        if (idJogador < 2) {
            jogadores.put(idJogador, cliente);
            System.out.println("Server: jogador " + idJogador + " logado com sucesso.");
            cliente.setIdJogador(idJogador);
            cliente.getRespostaServidor("Logado com sucesso.");
            idJogador++;
            if (idJogador < 2) {
                cliente.getRespostaServidor("Aguardando o próximo jogador...");
            } else {
                for (int i = 0; i < 2; i++) {
                    jogadores.get(i).getRespostaServidor("Aguarde a jogada do outro jogador!");
                }
                iniciarPartida();
            }
        } else {
            cliente.getRespostaServidor("Impossível logar: O servidor está lotado. ");
            cliente.finalizarProcessoCliente();
        }

    }

    @Override
    public void jogar(int id, int linha, int coluna) throws RemoteException {
        this.tabuleiro[linha][coluna] = id;
        numeroJogada++;
        if (numeroJogada >= 5) {
            boolean isVencedor = processarVencedor(id);
            if (isVencedor) {               
                jogadores.get(id).getRespostaServidor("Voce venceu!");
                jogadores.get(id).finalizarJogo();
                jogadores.get(Math.abs(1 - id)).getJogadaAdversario(id, linha, coluna);
                jogadores.get(Math.abs(1 - id)).getRespostaServidor("Voce perdeu!");
                jogadores.get(Math.abs(1 - id)).finalizarJogo();
            } else {
                if (numeroJogada == 9) {
                    jogadores.get(id).getRespostaServidor("Deu Velha!");
                    jogadores.get(Math.abs(1 - id)).getJogadaAdversario(id, linha, coluna);
                    jogadores.get(Math.abs(1 - id)).getRespostaServidor("Deu vellha!");
                } else {               
                    jogadores.get(id).getRespostaServidor("Aguarde a jogada do outro jogador!");
                    jogadores.get(Math.abs(1 - id)).getJogadaAdversario(id, linha, coluna);
                    jogadores.get(Math.abs(1 - id)).autorizarJogada(this);
                }
            }
        } else {        
            jogadores.get(id).getRespostaServidor("Aguarde a jogada do outro jogador!");
            jogadores.get(Math.abs(1 - id)).getJogadaAdversario(id, linha, coluna);
            jogadores.get(Math.abs(1 - id)).autorizarJogada(this);
        }
    }

    @Override
    public synchronized void deslogar(JogoDaVelhaClienteInterface cliente) throws RemoteException {
        // TODO Auto-generated method stub

    }

    private void sortearPrimeiroJogador() {
        if (Math.random() < 0.5) {
            try {
                jogadores.get(0).autorizarJogada(this);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                jogadores.get(1).autorizarJogada(this);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void iniciarPartida() {
        sortearPrimeiroJogador();
    }

    private boolean processarVencedor(int idJogador) {
        boolean resultado = false;

        // Verifica linhas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] != idJogador) {
                    break;
                }
                if (j == 2) {
                    resultado = true;
                    System.out.println("Linhas Verificadas resultado = " + resultado);
                    return true;
                }
            }
            if (resultado) {
                break;
            }
        }
        System.out.println("Linhas Verificadas resultado = " + resultado);
        // Verifica colunas
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (tabuleiro[i][j] != idJogador) {
                    break;
                }
                if (i == 2) {
                    resultado = true;
                    System.out.println("Colunas Verificadas resultado = " + resultado);
                    return true;
                }
            }
            if (resultado) {
                break;
            }
        }
        System.out.println("Colunas Verificadas resultado = " + resultado);
        // Verifica diagonal principal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][i] != idJogador) {
                break;
            }
            if (i == 2) {
                resultado = true;
                System.out.println("Diagonal Principal Verificada resultado = " + resultado);
                return true;
            }
        }
        System.out.println("Diagonal Principal Verificada resultado = " + resultado);

        // Verifica diagonal secundaria
        for (int i = 2, j = 0; i >= 0; i--, j++) {
            if (tabuleiro[i][j] != idJogador) {
                break;
            }
            if (j == 2) {
                resultado = true;
                System.out.println("Diagonal Secundaria Verificada resultado = " + resultado);
                return true;
            }
        }
        System.out.println("Diagonal Secundaria Verificada resultado = " + resultado);
        System.out.println("__________________________________________________");
        return resultado;
    }
}
