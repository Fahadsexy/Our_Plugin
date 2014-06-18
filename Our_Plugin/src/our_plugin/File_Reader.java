package our_plugin;

import java.io.IOException;
import java.util.ArrayList;

public class File_Reader {

	public ArrayList<SCC> SCCCLONES;
	
	public File_Reader() {
		SCCCLONES=new ArrayList<>();
	}

	public void run() throws IOException {
		// TODO Auto-generated method stub
//		String path = "\\CloneMiner\\output\\";
		Parser.refresh();
		String Files[] = { "CombinedTokens.txt" };
		for (int i = 0; i < Files.length; i++) {
//			String temp_path = path + Files[i];
//			switch (Files[i]) {
//			case "Clones.txt":

//				break;
//			case "ClonesByFile.txt":
//				ClonesbyFile.parser(temp_path);
//				break;
//			case "CombinedTokens.txt":
//				CombinedTokens.parser("\\CloneMiner\\output\\CombinedTokens.txt");
				Parser.parseClones(Directories.CLONESFILE);
				Parser.parseCombinedTokens(Directories.COMBINEDTOKENSFILE);
//				display(SCCCLONES);
//			}
		}
	}
	
	private static void display(ArrayList<SCC> SCCinstances) {
		for (int i = 0; i < SCCinstances.size(); i++) {
			for (int j = 0; j < SCCinstances.get(i).getclones().size(); j++) {
				for (int k = 0; k < SCCinstances.get(i).getclones().get(j).gettokens().size(); k++) {
					System.out.print(SCCinstances.get(i).getclones().get(j).gettokens().get(k) + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public ArrayList<SCC> getClones()
	{
		return SCCCLONES;
	}
	
	public void addSCC(SCC temp)
	{
		SCCCLONES.add(temp);
	}
	
	public VCL_Template genTemplate(int SCCID)
	{
		VCL_Template vcl = null;
		for(SCC SC : SCCCLONES)
		{
			if(SC.getSCCID() == SCCID)
			{
//				System.out.println("About to generate VCL Templates for SCCID: " + SCCID);
				vcl = new VCL_Template(SCCID, SC);
				return vcl;
			}
		}
		System.out.println("Could not find specified SCCID!");
		return null;
	}
}
