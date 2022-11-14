package video;

import cmd_prompt.CMD;

public class Merger {
	
	public void mergeAudioAndVideo(String inputVideo, String inputAudio, String outputPath) {
		String command = "ffmpeg -i "+inputVideo+" -i "+inputAudio+" -c:v copy -c:a aac -map 0:v:0 -map 1:a:0 "+outputPath;
		String COMMANDS[] = {
				"cd /",
                "cd program files",
                "cd ffmpeg/bin",
                command
		};
		new CMD().runCommand(COMMANDS);
	}
	
	public void mergePhotoAndAudio(String inputPhoto, String inputAudio, String outputPath) {
		mergeAudioAndVideo(inputPhoto, inputAudio, outputPath);
	}
	
	public void mergeVideos(String path) {
		FFiles.copy("C:\\Program Files\\FFMPEG\\bin\\ffmpeg.exe");
		FFiles.paste(path);
		String command = "ffmpeg -f concat -i titles.txt -c copy output.mp4";
		String COMMANDS[] = {
				"cd /",
				path.substring(0, path.indexOf("/")),
				"cd " + path.substring(path.indexOf("//")+1, path.length()),
				command
		};
		new CMD().runCommand(COMMANDS);
		FFiles.delete(path+"\\ffmpeg.exe");
	}
	
	
	public static void main(String[] args) {
//		new Merger().mergeAudioAndVideo("F://sample.mp4", "F://ai.mp3", "F://output.mp4");
//		new Merger().mergeVideos("F://Bukhari//0001");
		
		new Merger().mergePhotoAndAudio("F://pic.png", "F://ai.mp3", "F://fff.mp4");
	}
}
