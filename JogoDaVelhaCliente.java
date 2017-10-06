import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author ricardo
 * 
 * Classe que inicia um jogador(cliente)
 */
public class JogoDaVelhaCliente {

	/*Configura as informações para iniciar o cliente de acordo com o serviço e dados do servidor*/
    private static String ip = "localhost";
    private static String porta = "1099";
    private static String nomeServico = "JogoDaVelha";

    private static JogoDaVelhaClienteInterface jogador;
    private static TelaJogador tela;

    public static void main(String args[]) {

    	/*Tenta localizar o serviço a partir do protocolo rmi e informações do servidor*/
        try {
            String url = "rmi://" + ip + ":" + porta + "/" + nomeServico;
            JogoDaVelhaServidorInterface server = (JogoDaVelhaServidorInterface) Naming.lookup(url);
            /*Cria um jogador e uma tela, define a qual servidor essa tela pertence e então atribui
             * a mesma ao cliente(jogador)*/
            jogador = new JogoDaVelhaClienteImplementacao();
            tela = new TelaJogador();
            tela.setServidor(server);
            jogador.setTelaJogador(tela);
            /*Loga o cliente no servidor*/
            server.logar(jogador);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /*Inicia a tela*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tela.setVisible(true);
            }
        });
    }

}
