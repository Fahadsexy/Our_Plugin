package our_plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import our_plugin.handlers.SampleHandler;

public class Parser {

	public Parser()
	{
		
	}
	
	public static void parseCombinedTokens(String temp_path)
	{
		temp_path = Activator.getAbsolutePath(temp_path);
		FileReader frdr = null;
		BufferedReader reader = null;
		try {
			frdr = new FileReader(temp_path);
			reader = new BufferedReader(frdr);
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		try {
			String line = null;
			line = reader.readLine();
			//ArrayList<ArrayList<String>> tokens = new ArrayList<>();
		//	String temp = "";
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\s+");
				if (isNumber(parts[2]) && isNumber(parts[3])
						&& isNumber(parts[4])) {
					SCCInstanceCustom instance = getSCCInstance(Integer.parseInt(parts[2]),
							Integer.parseInt(parts[3]));
					if (instance != null) {
						if (parts[9].equals("STARTMETHOD")) {
							continue;
						}
						if (parts[9].equals("ENDMETHOD")) {
							continue;
						}
						instance.addToken(parts[9]);
					}
				}
			}
//			SCCInstanceCustom.setClone_List(tokens);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			reader.close();
		}
	}
/*	
	private static void show(ArrayList<ArrayList<String>> tokens) {
		for (int i = 0; i < tokens.size(); i++) {
			System.out.println(tokens.size());
			System.out.println(tokens.get(i).size());
		}

	}
*/
	private static SCCInstanceCustom getSCCInstance(int parseInt, int parseInt2)
			throws IOException {
				for(int i=0;i<SampleHandler.f.getClones().size();i++)
				{
					for(int j=0;j<SampleHandler.f.getClones().get(i).getclones().size();j++)
					{
						if(SampleHandler.f.getClones().get(i).getclones().get(j).getFile_number()==parseInt && SampleHandler.f.getClones().get(i).getclones().get(j).getStart_line()<=parseInt2 && SampleHandler.f.getClones().get(i).getclones().get(j).getEnd_line() >=parseInt2)
						{
							return SampleHandler.f.getClones().get(i).getclones().get(j);
						}
					}
				}
				return null;
	}

	private static boolean isNumber(String string) {
		try {
			Double.parseDouble(string);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true && string.length() > 0;
	}
	
	public static void parseClones(String path)
	{
		path = Activator.getAbsolutePath(path);
		FileReader frdr = null;
		BufferedReader reader = null;
		refresh();
		int count=0;
		try {
			frdr = new FileReader(path);
			reader = new BufferedReader(frdr);
		} catch (FileNotFoundException e) {
		}
		try {
			String line = "";
			SCC tempscc = null;
			SCCInstanceCustom tempclone=null;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("[; : . -]");
				switch (parts.length) {
				case 0:
					break;
				case 3:
					tempscc=new SCC();
					tempscc.setSCCID(Integer.parseInt(parts[0]));
					tempscc.setLength_in_tokens(Integer.parseInt(parts[1]));
					tempscc.setClone_count(Integer.parseInt(parts[2]));
					count=0;
					break;
				case 5:
					count++;
					tempclone=new SCCInstanceCustom();
					tempclone.setSCCID(tempscc.getSCCID());
					tempclone.setFile_number(Integer.parseInt(parts[0]));
					tempclone.setStart_line(Integer.parseInt(parts[1]));
					tempclone.setStart_col(Integer.parseInt(parts[2]));
					tempclone.setEnd_line(Integer.parseInt(parts[3]));
					tempclone.setEnd_col(Integer.parseInt(parts[4]));
					tempclone.setCode(getCodeSegment(getFile(tempclone.getFile_number(),tempclone), tempclone.getStart_line(),
							tempclone.getEnd_line()));
					tempclone.setDir_id(getDir(tempclone.getFile_number()));
					tempclone.setFilename(getDirName(tempclone.getFile_number()));
					Writer.writer(tempclone);
					tempscc.addInstance(tempclone);
					if(count==tempscc.getClone_count())
					{
						SampleHandler.f.addSCC(tempscc);
						CloneDB.insertSCC(tempscc);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*private static void update(SCCInstanceCustom temp) {
		int id = temp.getSCCID();
		int fn = temp.getFile_number();
		ArrayList<String> temp3 = new ArrayList<>();
		String temp2 = (Clone_List.get(id).get(fn));
		String[] parts = temp2.split("\\s+");
		for (int i = 0; i < parts.length; i++) {
			temp3.add(parts[i]);
		}
		temp.setClones(temp3);
		clone_instances.add(temp);
		SCC.setTokenized(clone_instances);

	}
	
	private static void display(ArrayList<SCCInstanceCustom> clone_instances2) {
		for (int i = 0; i < clone_instances2.size(); i++) {
			for (int j = 0; j < clone_instances2.get(i).getClones().size(); j++) {
				System.out.print(clone_instances2.get(i).getClones().get(j));
			}
			System.out.println();
		}
	}*/
	
	static void refresh() {
		for (int i = 0; i < 115; i++) {
			String path = Activator.getAbsolutePath(".\\My Output\\");
			File f = new File(path + i + ".txt");
			f.delete();
		}

	}
	
	public static String getDirName(int file_number2) {
		int dirnum = getDir(file_number2);
		FileReader frdr = null;
		BufferedReader reader = null;
		try {
			String path = Directories.DIRSINFOFILE;
			path = Activator.getAbsolutePath(path);
			frdr = new FileReader(path);
			reader = new BufferedReader(frdr);
		} catch (FileNotFoundException e) {
		}
		try {
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split(",");
				if (Integer.parseInt(parts[0]) == dirnum) {
					return parts[1];
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
		}
		try {
			reader.close();
		} catch (IOException e) {
		}
		return "Directory name not found";
	}
	
	public static int getDir(int file_number2) {
		FileReader frdr = null;
		BufferedReader reader = null;
		try {
			String path = Directories.FILESINFOFILE;
			path = Activator.getAbsolutePath(path);
			frdr = new FileReader(path);
			reader = new BufferedReader(frdr);
		} catch (FileNotFoundException e) {
		}
		try {
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split(",");
				if (Integer.parseInt(parts[0]) == file_number2) {
					return Integer.parseInt(parts[1]);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
		}
		try {
			reader.close();
		} catch (IOException e) {
		}
		return 0;
	}
	
	public	static String getFile(int file_number2,SCCInstanceCustom temp) {
		String name = null;
		temp.setFile_number(0);
		FileReader frdr = null;
		BufferedReader reader = null;
		try {
			String path = Directories.INPUTFILE;
			path = Activator.getAbsolutePath(path);
			frdr = new FileReader(path);
			reader = new BufferedReader(frdr);
		} catch (FileNotFoundException e) {
		}
		try {
			String line = reader.readLine();
			while (line != null) {
				if (temp.getFile_number()== file_number2) {
					name = line;
					// System.out.println(name);
					reader.close();
					return name;
				}
				temp.setFile_number(temp.getFile_number()+1);
				line = reader.readLine();
			}
		} catch (Exception e) {
		}
		try {
			reader.close();
		} catch (IOException e) {
		}
		System.out.println("File not found");
		return "File not found";
	}
	
	private static String getCodeSegment(String path, int start_line,
			int end_line) throws IOException {
		File check = new File(path);
		FileReader frdr = null;
		BufferedReader buff = null;
		String data = null;
		if (check.exists()) {
			frdr = new FileReader(path);
			buff = new BufferedReader(frdr);
		} else if (!check.exists()) {
			String path2 = Directories.CHECKFILE;
			path2 = Activator.getAbsolutePath(path2);
			frdr = new FileReader(path2);
			buff = new BufferedReader(frdr);
			System.out.println("File not found: " + path2);
		}
		int line_number = 0;
		try {
			String line = null;
			while ((line = buff.readLine()) != null) {
				line_number++;
				if (line_number >= start_line && line_number <= end_line) {
					data += (line + "\n");
				}
			}
		} catch (Exception e) {
		} finally {
			frdr.close();
			buff.close();

		}
		return data;
	}

}
