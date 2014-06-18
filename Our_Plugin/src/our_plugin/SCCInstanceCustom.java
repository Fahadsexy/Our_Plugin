package our_plugin;

import java.util.ArrayList;

public class SCCInstanceCustom {

	private int SCCID=0,
			file_number = 0, start_line = 0, start_col = 0, end_line = 0,
			end_col = 0, dir_id = 0;
	private String filename;
	private String code;
	private ArrayList<String> tokens;
	//private static ArrayList<SCCInstanceCustom> clone_instances = new ArrayList<>();
	//private static ArrayList<ArrayList<String>> Clone_List = new ArrayList<>();

	public SCCInstanceCustom()
	{
		SCCID=0;
		tokens=new ArrayList<>();
		file_number = 0;
		start_line = 0;
		start_col = 0;
		end_line = 0;
		end_col = 0;
		dir_id = 0;
	}
	public SCCInstanceCustom(int SCCID1, int file_number1, int start_line1,
			int start_col1, int end_line1, int end_col1, String filename1,
			int did) {
		SCCID = SCCID1;
		file_number = file_number1;
		start_line = start_line1;
		start_col = start_col1;
		end_line = end_line1;
		end_col = end_col1;
		filename = filename1;
		dir_id = did;
		tokens = new ArrayList<>();
	}
	
	public void setFile_number(int File_number) {
		file_number = File_number;
	}
	
	public void setStart_line(int Start_line) {
		start_line = Start_line;
	}
	
	public void setSCCID(int sCCID) {
		SCCID = sCCID;
	}

	public void setStart_col(int Start_col) {
		start_col = Start_col;
	}
	
	public void setEnd_line(int End_line) {
		end_line = End_line;
	}
	
	public void setEnd_col(int End_col) {
		end_col = End_col;
	}
	
	public void setFilename(String Filename) {
		filename = Filename;
	}
	
	public void setCode(String Code) {
		code = Code;
	}
	
	public void setDir_id(int dir)
	{
		dir_id=dir;
	}
	
	public void settokens(ArrayList<String> Clones) {
		tokens = Clones;
	}
	
	public int getSCCID() {
		return SCCID;
	}

	public int getFile_number() {
		return file_number;
	}

	public int getStart_line() {
		return start_line;
	}

	public int getStart_col() {
		return start_col;
	}

	public int getEnd_line() {
		return end_line;
	}

	public int getEnd_col() {
		return end_col;
	}

	public int getDir_id() {
		return dir_id;
	}

	public  String getFilename() {
		return filename;
	}

	public String getCode() {
		return code;
	}

	public ArrayList<String> gettokens() {
		return tokens;
	}
	
	public void addToken(String as)
	{
		this.tokens.add(as);
	}

	/*public static ArrayList<ArrayList<String>> getClone_List() {
		return Clone_List;
	}*/
}
