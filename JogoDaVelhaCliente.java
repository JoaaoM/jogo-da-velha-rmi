import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author ricardo
 */
public class JogoDaVelhaCliente {

    private static String ip = "localhost";
    private static String porta = "1099";
    private static String nomeServico = "JogoDaVelha";

    private static JogoDaVelhaClienteInterface jogador;
    private static TelaJogador tela;

    public static void main(String args[]) {

        try {
            String url = "rmi://" + ip + ":" + porta + "/" + nomeServico;
            JogoDaVelhaServidorInterface server = (JogoDaVelhaServidorInterface) Naming.lookup(url);
            jogador = new JogoDaVelhaClienteImplementacao();
            tela = new TelaJogador();
            tela.setServidor(server);
            jogador.setTelaJogador(tela);
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tela.setVisible(true);
            }
        });
    }

}
