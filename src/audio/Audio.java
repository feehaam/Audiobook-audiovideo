package audio;

import video.FFiles;

public class Audio {
	public static String modify(String text) {
		text = removeSuraRef(text);
		log("      Complete: Sura reference removal");
		text = addFullForms(text);
		log("      Complete: Adding full forms");
		text = removeWhiteSpaces(text);
		log("      Complete: Whitespace removal");
		text = removeSigns(text);
		log("      Complete: Sign removal");
		text = addBreath(text);
		log("      Complete: Stops/breaths addition");

		FFiles.createAndWrite("D://moded.txt", text);
		log("      Modified text: "+text.replaceAll("\n", " "));
		return text;
	}
	
	private static String removeWhiteSpaces(String text) {
		while(text.contains("  "))
			text = text.replace("  ", " ");
		return text;
	}
	
	private static String removeSuraRef(String text) {
		while(text.contains("(সূরা")) {
			int beg = text.indexOf("(সূরা"), end = beg;
			for(int i=beg; i<text.length(); i++) {
				if(text.charAt(i) == ')' || i==text.length()) {
					end = i;
					break;
				}
			}
			String sub;
			try {
				 sub = text.substring(beg, end+1);
			}
			catch(Exception e) {
				 sub = text.substring(beg, end);
			}
			text = text.replace(sub,"");
		}
		while(text.contains("(সুরা")) {
			int beg = text.indexOf("(সুরা"), end = beg;
			for(int i=beg; i<text.length(); i++) {
				if(text.charAt(i) == ')' || i==text.length()) {
					end = i;
					break;
				}
			}
			String sub;
			try {
				 sub = text.substring(beg, end+1);
			}
			catch(Exception e) {
				 sub = text.substring(beg, end);
			}
			text = text.replace(sub,"");
		}
		return text;
	}
	
	private static String addFullForms(String text) {
		
		//সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম
		while(text.contains("(স)")) {
			text = text.replace("(স)", "সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম ");
		}
		while(text.contains("(স.)")) {
			text = text.replace("(স.)", "সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম ");
		}
		while(text.contains("(সঃ)")) {
			text = text.replace("(সঃ)", "সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম ");
		}
		while(text.contains("(সা)")) {
			text = text.replace("(সা)", "সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম ");
		}
		while(text.contains("(সা.)")) {
			text = text.replace("(সা.)", "সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম ");
		}
		
		//আলাইহিস সালাম
		while(text.contains("(আ)")) {
			text = text.replace("(আ)", "আলাইহিস সালাম ");
		}
		while(text.contains("(আঃ)")) {
			text = text.replace("(আঃ)", "আলাইহিস সালাম ");
		}
		while(text.contains("(আ.)")) {
			text = text.replace("(আ.)", "আলাইহিস সালাম ");
		}

		
		//রাদিআল্লাহু আনহু 
		while(text.contains("(র)")) {
			text = text.replace("(র)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(র.)")) {
			text = text.replace("(র.)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রা)")) {
			text = text.replace("(রা)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রাঃ)")) {
			text = text.replace("(রাঃ)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রা.)")) {
			text = text.replace("(রা.)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রাযি)")) {
			text = text.replace("(রাযি)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রাযি.)")) {
			text = text.replace("(রাযি.)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রাযিঃ)")) {
			text = text.replace("(রাযিঃ)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রাদি.)")) {
			text = text.replace("(রাদি.)", "রাদিআল্লাহু আনহু ");
		}
		while(text.contains("(রাদিঃ)")) {
			text = text.replace("(রাদিঃ)", "রাদিআল্লাহু আনহু ");
		}
		
		//রহমাতুল্লাহি আলাইহি
		while(text.contains("(রঃ)")) {
			text = text.replace("(রঃ)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রহ)")) {
			text = text.replace("(রহ)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রহ.)")) {
			text = text.replace("(রহ.)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রাহ)")) {
			text = text.replace("(রাহ)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রাহ.)")) {
			text = text.replace("(রাহ.)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রাহঃ)")) {
			text = text.replace("(রাহঃ)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রাহ্‌)")) {
			text = text.replace("(রাহ্‌)", "রহমাতুল্লাহি আলাইহি ");
		}
		while(text.contains("(রহঃ)")) {
			text = text.replace("(রহঃ)", "রহমাতুল্লাহি আলাইহি ");
		}

		return text;
	}
	
	private static String removeSigns(String text) {
		while(text.contains("ঃ")) {
			text = text.replace("ঃ", "");
		}
		while(text.contains("‘")) {
			text = text.replace("‘", "");
		}
		while(text.contains("’")) {
			text = text.replace("’", "");
		}
		while(text.contains("'")) {
			text = text.replaceAll("'", "");
		}
		while(text.contains("\"")) {
			text = text.replaceAll("\"", "");
		}
		while(text.contains("-")) {
			text = text.replaceAll("-", " ");
		}
		while(text.contains("\n\n")) {
			text = text.replaceAll("\n\n", "\n");
		}
		while(text.contains("\n")) {
			text = text.replaceAll("\n", ".");
		}
		while(text.contains("।")) {
			text = text.replace("।", "..");
		}
		
		text += ".";
		return text;
	}
	
	private static String addBreath(String text) {
		if(text.contains("বলেন"))
			text = text.replaceAll("বলেন", "বলেন,");
		if(text.contains("বলেছেন"))
			text = text.replaceAll("বলেছেন", "বলেছেন,");
		if(text.contains("বর্ণিত"))
			text = text.replaceAll("বর্ণিত", "বর্ণিত,");
		if(text.contains("বর্ণীত"))
			text = text.replaceAll("বর্ণীত", "বর্ণীত,");
		if(text.contains("বর্ণনা করেন"))
			text = text.replaceAll("বর্ণনা করেন", "বর্ণনা করেন,");
		if(text.contains("শুনেছি"))
			text = text.replaceAll("শুনেছি", "শুনেছি,");
		if(text.contains("আর"))
			text = text.replaceAll("আর", "আর,");
		if(text.contains(" অথবা"))
			text = text.replaceAll("ব অথবা", ", অথবা");
		if(text.contains(" তবে"))
			text = text.replaceAll(" তবে", ", তবে");
		if(text.contains("জন্যে,"))
			text = text.replaceAll("জন্যে,", "জন্যে");
		text = text.replaceAll("নিয়্যাত", "নিয়ত");
		text = text.replaceAll("নিয়ত", "নিয়ৎ");
		text = text.replaceAll("হাজ্জ", "হজ্‌");
		text = text.replaceAll("সহীহ", "ছহিহ্‌");
		text = text.replaceAll("হাদীস", "হাদিছ");
		
		return text;
	}
	
	
	static void log(String s) {
		System.out.println(s);
		FFiles.addTo("F://Bukhari//log.txt", s+"\n");
	}
}
