package our_plugin;

import java.util.ArrayList;

public class SCC {
	private ArrayList<SCCInstanceCustom> clone_instances;
	private int SCCID = 0, length_in_tokens = 0, clone_count = 0;

	public SCC()
	{
		clone_instances=new ArrayList<>();
	}
	
	
	public void addInstance(SCCInstanceCustom temp)
	{
		this.clone_instances.add(temp);
	}
	
	public void setclones(ArrayList<SCCInstanceCustom> Tokenized) {
		this.clone_instances = Tokenized;
	}
	
	public void setSCCID(int sCCID) {
		SCCID = sCCID;
	}
	
	public void setLength_in_tokens(int Length_in_tokens) {
		length_in_tokens = Length_in_tokens;
	}
	
	public void setClone_count(int Clone_count) {
		clone_count = Clone_count;
	}
	
	public int getSCCID() {
		return SCCID;
	}
	
	public int getLength_in_tokens() {
		return length_in_tokens;
	}
	
	public int getClone_count() {
		return clone_count;
	}
	
	public ArrayList<SCCInstanceCustom> getclones() {
		return clone_instances;
	}
}
