import java.io.*;
public class elpFixer {

	public static void main(String[] args) {
	
		int inputFiles = args.length;
		
		if(inputFiles == 0) {
			System.out.print("You must specify at least one file");
			System.exit(0);
		}
		
		for(int i = 0; i < inputFiles; i++) {
			fixer(args[i]);
		}

	}
	
	public static void fixer(String inName) {
		File inF = new File(inName);
		StringBuilder path = new StringBuilder(inF.getAbsolutePath());
		path.insert(path.length() - 4, "_fixed");
		File outF = new File(path.toString());
		
		try {
			BufferedReader inStream = new BufferedReader(new FileReader(inF));
			BufferedWriter outStream = new BufferedWriter(new FileWriter(outF));
			try {
				System.out.println("Fixing " + inF.getName());
				while(inStream.ready()) {
					String temp = inStream.readLine();
					
					/* BEGIN FIXING */
					if(temp.trim().length() == 0 || temp.startsWith("//") || temp.startsWith("%O"))
						;
					else {
						outStream.write(temp);
						outStream.newLine();
					}
				}
			}
			finally {
				inStream.close();
				outStream.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file could not be opened: " + inF.getPath());
		} catch (IOException e) {
			System.out.println("Output file could not be opened: " + outF.getPath());
		}
	}
}
