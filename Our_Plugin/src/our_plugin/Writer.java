package our_plugin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	public static void writer(SCCInstanceCustom temp) {
		try {
			String path = "\\My Output\\";
			path = Activator.getAbsolutePath(path);
			FileWriter name = new FileWriter(path + temp.getSCCID() + ".txt", true);
			BufferedWriter out = new BufferedWriter(name);
			out.write("SCCID is " + temp.getSCCID() + "\n");
			//out.write("Length in tokens is " + temp.getLength_in_tokens() + "\n");
			//out.write("The number of clones found is " + temp.getClone_count() + "\n");
			out.write("File Number: " + temp.getFile_number() + "\n");
			out.write("File Name: " + Parser.getFile(temp.getFile_number(),temp) + "\n");
			out.write("Directory ID: " + Parser.getDir(temp.getFile_number()) + "\n");
			out.write("Directory Name: " + Parser.getDirName(temp.getFile_number()) + "\n");
			out.write("Starting line is " + temp.getStart_line() + "\n");
			out.write("Starting Column: " + temp.getStart_col() + "\n");
			out.write("Ending line is " + temp.getEnd_line() + "\n");
			out.write("Ending Column: " + temp.getEnd_col() + "\n");
			out.write("**************************CODE STARTS HERE*************************************\n");
			out.write(temp.getCode() + "\n");
			out.write("*************************CODE ENDS HERE****************************************\n");
			out.write("\n");
			out.write("\n");
			out.write("\n");
			out.close();
		} catch (IOException ioe) {
			System.out.println("file writer error");
		}
	}
}
