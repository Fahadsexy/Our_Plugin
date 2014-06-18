package our_plugin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ClonesbyFile {

	private static int file_number;

	public static void parser(String path) {
		ArrayList<Integer> clones_by_file = new ArrayList<Integer>();
		file_number = 1;
		FileReader frdr = null;
		BufferedReader reader = null;
		try {
			frdr = new FileReader(path);
			reader = new BufferedReader(frdr);
		} catch (FileNotFoundException e) {
		}
		try {
			String line = reader.readLine();
			while (line != null) {
				file_number++;
				clones_by_file = new ArrayList<Integer>();
				String[] parts = line.split(",");
				if (parts.equals(""))
					continue;
				for (int i = 0; i < parts.length; i++) {
					if (!parts[i].equals("")) {
						clones_by_file.add(Integer.parseInt(parts[i]));
						// System.out.println(parts[i]);
					}
				}
				System.out.println();
				System.out.println("File Number: " + file_number);
				System.out.print("The clones found in it are: ");
				for (int i = 0; i < clones_by_file.size(); i++) {
					System.out.print(" " + clones_by_file.get(i) + " ");
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
		}
	}

	public static int getFile_number() {
		return file_number;
	}

	public static void setFile_number(int file_number) {
		ClonesbyFile.file_number = file_number;
	}
}
