package our_plugin;

import java.util.ArrayList;

public class DynamicToken {
	int tokenIndex;
	ArrayList<String> tokenValues;
	
	public DynamicToken()
	{
		tokenIndex = -1;
		tokenValues = new ArrayList();
	}
	
	public ArrayList<String> getValues()
	{
		return tokenValues;
	}
	
	public boolean checkifExists(String _token)
	{
		for(String token : tokenValues)
		{
			if(token.equals(_token))
				continue;
			else
				return true;
		}
		
		return false;
	}
	
	public void printTokens()
	{
		System.out.println("===== DynamicToken =====");
		for(String val : tokenValues)
			System.out.println(val);
		System.out.println("=================");
	}
	
}
