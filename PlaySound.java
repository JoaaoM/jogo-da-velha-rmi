import java.io.*;
import javax.sound.sampled.*;

	
public class PlaySound {

	static File file;
    static AudioInputStream stream;
    static AudioFormat format;
    static DataLine.Info info;
    static Clip clip;
	
	static void tocaMusica(Boolean vencedor) {
		if(vencedor)
			file = new File("musicas/smw_coin.wav");
		else
			file = new File("musicas/smw_blargg_no_echo.wav");
		try {
		    stream = AudioSystem.getAudioInputStream(file);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {}
	}
}
