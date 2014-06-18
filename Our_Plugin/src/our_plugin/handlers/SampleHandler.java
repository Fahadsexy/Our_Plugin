package our_plugin.handlers;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import our_plugin.File_Reader;
import our_plugin.Initializer;
import our_plugin.Templater;
import our_plugin.VCL_Template;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public static File_Reader f;
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		new Initializer();
		f = new File_Reader();
		Templater worker = Templater.getTemplater();
		ArrayList<VCL_Template>  vcls = new ArrayList<>();
		try {
			f.run();
			
//			for(int i=0;i<f.SCCCLONES.size();i++)
//			{
				worker.start(f.SCCCLONES, f.genTemplate(2),2);
				//vcls.add(f.genTemplate(f.SCCCLONES.get(i).getSCCID()));
//			}
			//worker.start(f.SCCCLONES,vcls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
