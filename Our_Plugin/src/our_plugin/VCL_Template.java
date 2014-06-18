package our_plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VCL_Template {

	final String OUTPUT_PATH = "\\VCL_Output\\";
	int curSCCID = -1;
	int curFileID = 0;
	int curClassID = 0;
	int curMethodID = 0;
	int curVariableID = 0;
	ArrayList<ArrayList<String>> InstanceList;
	ArrayList<String> GenericTokens;
	ArrayList<DynamicToken> DynamicTokens;
	
	public VCL_Template(int SCCID, SCC SimpleClone)
	{
		InstanceList = new ArrayList();
		GenericTokens = new ArrayList();
		DynamicTokens = new ArrayList();
		curSCCID = SCCID;
		getTokensBySCC(SimpleClone, SCCID);
		matchTokens();
		generateTemplate();
	}

	public void matchTokens()
	{
		boolean firstPass = true;
		int tokenIndex = 0;

		// For all the instances of curSCCID //
//		System.out.println("Size: " + InstanceList.size());
		for(int i = 0; i < InstanceList.size(); i++)
		{
			// For the first pass, add all tokens to GenericTokens //
			if(firstPass)
			{
				for(String InstanceToken : InstanceList.get(i))
					GenericTokens.add(InstanceToken);
				firstPass = false;
			}
			else
			{
				tokenIndex = 0;
				for(String InstanceToken : InstanceList.get(i))
				{
					if(!GenericTokens.get(tokenIndex).equals(InstanceToken))	// If tokens differ
					{
						if(GenericTokens.get(tokenIndex).equals("9999")) // If you find 9999 at the index
						{
							checkIfExists(InstanceToken, tokenIndex);
						}
						else
						{
							DynamicToken tk = new DynamicToken();
							tk.tokenIndex = tokenIndex;
							tk.tokenValues.add(GenericTokens.get(tokenIndex));
							tk.tokenValues.add(InstanceToken);
							DynamicTokens.add(tk);
							
							GenericTokens.set(tokenIndex, "9999");
						}
					}
					tokenIndex++;
				}	
			}
		}

//		System.out.println("About to print dynamic tokens:- ");
//		for(DynamicToken tk : DynamicTokens)
//			tk.printTokens();
	}

	public void checkIfExists(String InstanceToken, int tokenIndex)
	{
		for(DynamicToken tk : DynamicTokens)
		{
			if(tk.tokenIndex == tokenIndex)
			{
				int matchFound = 0;
				for(String token : tk.getValues())
				{
					if(token.equals(InstanceToken))
					{
						matchFound++;
					}
				}
				if(matchFound == 0)
					tk.tokenValues.add(InstanceToken);
				return;
			}
		}
	}

	public void getTokensBySCC(SCC SimpleClone, int SCCID)
	{
		InstanceList.clear();
				
		// Temporary Hard Coding for SCCID 2 //
		ArrayList<SCCInstanceCustom> AllInstances = SimpleClone.getclones();
		
		for(SCCInstanceCustom Instance : AllInstances)
		{
			InstanceList.add(Instance.gettokens());
		}
		
		// InstanceList populated //
	}

	public void generateTemplate()
	{
		String path = Activator.getAbsolutePath(OUTPUT_PATH);
		curFileID = curSCCID;
		try {
			String SPC_PATH = curFileID + "_SPC.vcl";
			String VCL_PATH = curFileID + "_Frame.vcl";

			refresh(path+SPC_PATH, path+VCL_PATH);	// Delete files if they already exist //

			FileWriter spc_file = new FileWriter(path + SPC_PATH, true);
			FileWriter vcl_file = new FileWriter(path + VCL_PATH, true);
			BufferedWriter spc_output = new BufferedWriter(spc_file);
			BufferedWriter output = new BufferedWriter(vcl_file);

			// Read VariablesList //

			curVariableID = 0;
			ArrayList<String> VariableList = new ArrayList();
			for(int i = 0 ; i < DynamicTokens.size(); i++)
			{
				VariableList.add("variable_" + curVariableID);
				curVariableID++;
			}

			// ====== START OF SPC ===== //

			// Specify SPC details //
			spc_output.write("% SPC file for " + VCL_PATH + "\n\n");

			// The output class/method name //
			String methodNameVar = "method_" + curMethodID;
			String methodNameValue = "MyMethodValue";		// Can ask user to specify method name //
			spc_output.write("#set " + methodNameVar + " = " + "\"" +  methodNameValue + "\"" + "\n");

			// Set Variables //
			spc_output.write("% Here I will set the rest of the place-holder variables\n\n");
			int index = 0;
			for(String variable : VariableList)
			{
				// Set Variable Names and Values
				spc_output.write("#set " + variable + " = " + "\"" + DynamicTokens.get(index).tokenValues.get(0) + "\"" + "\n");
				index++;
			}

			// Adapt VCL File //
			spc_output.write("#adapt " + "\"" + VCL_PATH + "\"" + "\n\n");
			// Details about SPC here //
			spc_output.write("% You can specify options for this SPC File here\n\n");
			spc_output.write("#endadapt");

			// ====== END OF SPC ======= // 
			// ========================= //
			// ====== START OF VCL ===== //

			// Specify VCL output //
			//output.write("#output " + outputfileName + "\"Buffer.java\"");
			output.write("#output " + "?@" + methodNameVar + "?" + "\"Buffer.java\"" + "\n\n"); // Outputs: #output lolBuffer.java

			// Traversing VCLInputContainer Generic + Variable Lists //
			int varCounter = 0;
			for(String token: GenericTokens)
			{
				if(token.equals("9999"))
				{
					// Place-holder for variable //
					output.write("?@"+VariableList.get(varCounter)+"? ");
					varCounter++;
				}
				else
				{
					output.write(token + " ");	
				}	
			}

			output.close();
			spc_output.close();
			curFileID++;
			curClassID++;
			curMethodID++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ====== END OF VCL ===== //

	}

	private static void refresh(String SPC_path, String VCL_path) {
		File f1 = new File(SPC_path);
		File f2 = new File(VCL_path);
		f1.delete();
		f2.delete();
	}

}
