package audio;

import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Create {
	
	private SynthesiserV2 synthesizer = new SynthesiserV2();
	private InputStream inputStream;
	
	public Create(String text) {
		Thread thread = new Thread(() -> {
			try {
				inputStream = synthesizer.getMP3Data(text);

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		thread.setDaemon(false);
		thread.start();
	}
	
	public InputStream mp3() {
		while(inputStream == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return inputStream;
	}
	
	public void sound() {
		AdvancedPlayer player;
		try {
			player = new AdvancedPlayer(mp3());
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
