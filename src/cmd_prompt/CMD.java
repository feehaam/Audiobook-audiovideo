package cmd_prompt;
import java.io.PrintWriter;
public class CMD {
//	public static void main(String[] args) {
//		new CMD().runCommand("F://sample.mp4", "F://ai.mp3");
//	}	
//	"ffmpeg -i "+inputVideo+" -i "+inputAudio+" -c:v copy -c:a aac -map 0:v:0 -map 1:a:0 F://output.mp4"
	
	public void runCommand(String COMMANDS[]) {
		String[] command = { "cmd", };
		    Process p;
			try {
				p = Runtime.getRuntime().exec(command);
			        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
		                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
		                PrintWriter stdin = new PrintWriter(p.getOutputStream());
		                for(String c: COMMANDS) {
		                	stdin.println(c);
		                }
		                stdin.close();
		                p.waitFor();
		    	} catch (Exception e) {
		 		e.printStackTrace();
			}
	}
}	