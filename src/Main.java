import java.util.Scanner;

import audio.*;
import video.Merger;

public class Main {

	static Double totalTime = 0.0;
	static int totalChars = 0;
	
	public static void main(String[] strings) {
		Bukhari();
	}
	
	static void Bukhari() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Number of hadis: ");
		int H = sc.nextInt();
		System.out.print("1. Create Audios\n2. Create videos\nEnter your choice: ");
		int choice = sc.nextInt();
		FFiles.changeData("F://Bukhari//log.txt", "");
		if(choice == 1)
		for(int i=1; i<=H; i++) {
			String HN = i+"";
			while(HN.length() < 4) HN = "0"+HN;
			createAndMergeStep1("F://Bukhari//"+HN);
		}
		//STEP 2: create screenshots!
		if(choice == 2)
		for(int i=1; i<=H; i++) {
			String HN = i+"";
			while(HN.length() < 4) HN = "0"+HN;
			createAndMergeStep3("F://Bukhari//"+HN);
		}
		
		log("Hadis prepared: "+H);
		if(choice == 1) log("Total letters proceeded: "+totalChars);
		log("Total time taken: "+String.format("%.2fs", totalTime));
		if(choice == 1) log("Average letters per hadis: "+totalChars/H);
		log("Average time per hadis: "+String.format("%.2fs", totalTime/H));
		log("===============================================================");
	}
	
	static void log(String s) {
		System.out.println(s);
		FFiles.addTo("F://Bukhari//log.txt", s+"\n");
	}
	
	static void createAndMergeStep1(String path) {
		long timeStart = System.currentTimeMillis();
		log("PROCESSING: "+path.substring(path.indexOf("B"), path.length()));
		makeFolders(path);
		log("   Complete: Forlders creation");
		String text = FFiles.read(path+"//text.txt") + getVoiceTitle(path);
		int size = text.length();
		totalChars += size;
		int sub = 1;
		while(text.length() > 500) {
			for(int i=500; i>0; i--) {
				if(text.charAt(i) == ',' || text.charAt(i) == '।') {
					String toProcess = text.substring(0, i+1);
					text = text.substring(i+1, text.length());
					String audioOutput = path+"//audios//audio"+sub+".mp3";
					String textOutput = path+"//texts//text"+sub+".txt";
					createAudio(toProcess, audioOutput);
					log("  Complete: "+audioOutput);
					createText(toProcess, textOutput);
					log("  Complete: "+textOutput);
					sub++;
					break;
				}
				if(i==5) {
					for(int j=500; j>=0; j--) {
						if(text.charAt(j) == ' ') {
							text = text.substring(0, j) + "," + text.substring(j, text.length());
							i = 500;
							break;
						}
					}
				}
			}
		}
		createAudio(text, path+"//audios//audio"+sub+".mp3");
		log("  Complete: "+path+"//audios//audio"+sub+".mp3");
		createText(text, path+"//texts//text"+sub+".txt");
		log("  Complete: "+path+"//texts//text"+sub+".txt");
		FFiles.createAndWrite(path+"//subfiles.txt", ""+(sub));
		log("Text size: "+size+" | Sub files: "+sub);
		Double time = (double)(System.currentTimeMillis() - timeStart);
		time /= 1000;
		totalTime += time;
		log("Time taken: "+String.format("%.2fs", time));
		log("--------------------------------DONE----------------------------------------\n");
	}
	
	static String getVoiceTitle(String path) {
		String no = String.valueOf(Integer.parseInt(path.substring(path.length()-4, path.length())));
		String banglaNumerics[] = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
		String banglaNo = "";
		for(int i=0; i<no.length(); i++) {
			banglaNo += banglaNumerics[no.charAt(i)-'0'];
		}
		return " (সহীহ বুখারী, হাদীস নং "+banglaNo+")";
	}
	
	static void createAndMergeStep3(String path) {
		log("PROCESSING VIDEO FOR "+path);
		int sub = Integer.parseInt(FFiles.read(path+"//subfiles.txt"));
		String titlesText = "";
		for(int i=1; i<=sub; i++) {
			String photo = path+"//photos//photo"+i+".png";
			String audio = path+"//audios//audio"+i+".mp3";
			String videoOutput = path+"//videos//video"+i+".mp4";
			createVideo(photo, audio, videoOutput);
			log("   Complete: Creating video "+sub);
			titlesText += "file video"+i+".mp4\n";
		}
		FFiles.createAndWrite(path+"//videos//titles.txt", titlesText);
		new Merger().mergeVideos(path+"//videos");
		FFiles.cut(path+"//videos//output.mp4");
		String title = getTitle(path);
		FFiles.paste(path+"//output.mp4");
		FFiles.delete(path+"//videos//titles.txt");
		FFiles.createAndWrite(path+"//title.txt", title);
	}
	
	static String getTitle(String path) {
		String no = String.valueOf(Integer.parseInt(path.substring(path.length()-4, path.length())));
		String banglaNumerics[] = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
		String banglaNo = "";
		for(int i=0; i<no.length(); i++) {
			banglaNo += banglaNumerics[no.charAt(i)-'0'];
		}
		return "সহীহ বুখারী শরীফ | হাদীস নং "+banglaNo;
	}

	static void makeFolders(String path) {
		FFiles.createFolder(path+"//audios");
		FFiles.createFolder(path+"//photos");
		FFiles.createFolder(path+"//videos");
		FFiles.createFolder(path+"//texts");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
	}
	
	static void createAudio(String input, String output) {
		if(FFiles.ifExists(output)) FFiles.delete(output);
		input = Audio.modify(input);
		Create audio = new Create(input);
		new SaveFile().copyInputStreamToFile(audio.mp3(), output);
	}
	
	static void createText(String text, String path) {
		if(FFiles.ifExists(path)) FFiles.delete(path);
		FFiles.createAndWrite(path, text);
	}
	
	static void createVideo(String photo, String audio, String output) {
		if(FFiles.ifExists(output)) FFiles.delete(output);
		Merger m = new Merger();
		m.mergePhotoAndAudio(photo, audio, output);
	}
}
