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
		path.insert(path.length() - 4, "_flat");
		File outF = new File(path.toString());
		
		try {
			BufferedReader inStream = new BufferedReader(new FileReader(inF));
			BufferedWriter outStream = new BufferedWriter(new FileWriter(outF));
			try {
				System.out.println("Fixing " + inF.getName());
				
				for(int i = 0; i < 8; i++)
					{
					String garbage = inStream.readLine();
					}

				while(inStream.ready()) {
					String temp = inStream.readLine();
					
					/* BEGIN FIXING */
					if(temp.trim().length() == 0 || temp.startsWith("//") || temp.startsWith("%O") || temp.startsWith("%S"))
						;
					else if(temp.startsWith("%F"))
					{
						outStream.write(temp.substring(4));
						outStream.newLine();
					}
					else if(temp.startsWith("%N"))
					{
						outStream.write(temp.substring(3));
					}
					else
					{
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
