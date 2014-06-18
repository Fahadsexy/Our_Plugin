package our_plugin;

import java.util.ArrayList;

public class Templater {
	private static Templater worker;
	String name;
	private Templater()
	{
		name = "templater";
	}
	
	public static Templater getTemplater()
	{
		if(worker==null)
		{
			worker = new Templater();
		}
		return worker;
	}
	
	public void start(ArrayList<SCC> data, VCL_Template vcl_Template, int SCCID)
	{
		ArrayList<String> tokens = new ArrayList<>();
		for(int j=vcl_Template.DynamicTokens.size()-1;j>-1;j--)
		{
			tokens = vcl_Template.DynamicTokens.get(j).tokenValues;		
			System.out.println("about to get code to match");
			for(int i=0;i<data.get(SCCID).getclones().size();i++)
			{
				String code = data.get(SCCID).getclones().get(i).getCode();
				data.get(SCCID).getclones().get(i).setCode(matcher(code,tokens));
			}
		}
		
	}

	private String matcher(String code, ArrayList<String> tokens) {
		System.out.println("Matching starting....");
		for(int i=0;i<tokens.size();i++)
		{
			System.out.println("Data acquired....");
			if(code.toLowerCase().contains(tokens.get(i).toLowerCase()))
			{
				System.out.println(code.substring(code.indexOf(tokens.get(i)) , code.indexOf(tokens.get(i)) + tokens.get(i).length()));
				code = code.replace(code.substring(code.indexOf(tokens.get(i)) , code.indexOf(tokens.get(i)) + tokens.get(i).length()), "BLANKTEXT");
				
				System.out.println(code);
				return code;
			}
		}
		return code;
		
	}

}
