package our_plugin;

/*import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;*/
import java.sql.*;

/*import java.io.FileInputStream;
 import java.util.Properties;
 import org.eclipse.core.runtime.FileLocator;
 import org.eclipse.core.runtime.IPath;
 */

public class CloneDB {
	public static Connection conn;

	public static Connection openConnection() {
		conn = null;
		try {
			String username = "root";
			String password = "";
			/*
			 * Properties properties = new Properties(); IPath path = new
			 * Path(FileLocator
			 * .toFileURL(myBundle.getEntry(Controller.DATABASE_CONFIG
			 * )).getPath()); String pathStr = path.toString(); FileInputStream
			 * filein = new FileInputStream(pathStr); properties.load(filein);
			 * username = properties.getProperty("username"); password =
			 * properties.getProperty("password");
			 */
			String url = "jdbc:mysql://localhost:3306/";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.err.println("Cannot connect to database server"
					+ e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) { /* ignore close errors */
			}
		}
	}

	public static void createDatabase() {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("drop schema if exists clonedatabase");
			stmt.execute("create schema clonedatabase");
			stmt.execute("use clonedatabase;");

			String scc_instance = "CREATE TABLE scc_instance ("
					+ "scc_instance_id INTEGER     NOT NULL,"
					+ "scc_id INTEGER     NOT NULL,"
					+ "fid INTEGER     NOT NULL,"
					+ "startline INTEGER     NOT NULL,"
					+ "startcol INTEGER     NOT NULL,"
					+ "endline INTEGER     NOT NULL,"
					+ "endcol INTEGER     NOT NULL,"
					+ "mid INTEGER     NOT NULL," + "did INTEGER     NOT NULL,"
					+ "length_in_tokens INTEGER NOT NULL,"
					+ "gid INTEGER     NOT NULL" + ")";
			stmt.addBatch(scc_instance);
			stmt.executeBatch();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void shutdown() {
		try {
			DriverManager.getConnection("jdbc:derby;shutdown=true;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearDatabase() {
		try {
			Statement st = conn.createStatement();
			st.execute("use clonedatabase;");

			/*
			 * String[] tableName = { "x", "fcc", "fcscrossdir_files",
			 * "fcc_instance", "fcs_crossdir", "fcs_indir", "fcscrossdir_dir",
			 * "fcscrossdir_fcc", "fcsindir_dir", "fcsindir_fcc", "file",
			 * "file_directory", "mcc", "mcc_instance", "mcc_scc",
			 * "mcs_crossfile", "mcscrossfile_methods", "mcscrossfile_file",
			 * "mcscrossfile_mcc", "method", "method_file", "scc", "fcc_scc",
			 * "scc_file", "scc_instance", "scc_method", "scscrossfile_scc",
			 * "scsinfile_scc", "scs_crossfile", "scs_crossmethod",
			 * "scs_infile", "scscrossfile_file", "scscrossmethod_method",
			 * "scscrossmethod_scc", "scsinfile_file", "fcc_dir", "mcc_file",
			 * "scsinfile_fragments", "fcsindir_files", "fcscrossgroup_files",
			 * "fcsingroup_files", "fcs_crossgroup", "fcs_ingroup",
			 * "fcscrossgroup_group", "fcscrossgroup_fcc", "fcsingroup_group",
			 * "fcsingroup_fcc", "fcc_group"};
			 */

			// for (int i = 0; i < tableName.length; i++) {
			st.addBatch("Delete from scc_instance");
			// }
			st.executeBatch();
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void insertSCC(SCC temp) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("use clonedatabase;");
			for(int i=0;i< temp.getclones().size();i++)
			{
				String query = "insert into scc_instance (scc_instance_id,scc_id,fid,startline,startcol,endline,endcol,mid,did,length_in_tokens,gid) values ('";
				query = query + temp.getclones().get(i).getSCCID() + "','" + -1 + "','"
						+ temp.getclones().get(i).getFile_number() + "','" + temp.getclones().get(i).getStart_line()
						+ "','" + temp.getclones().get(i).getStart_col() + "','" + temp.getclones().get(i).getEnd_line()
						+ "','" + temp.getclones().get(i).getEnd_col() + "','" + -1 + "','"
						+ temp.getclones().get(i).getDir_id() + "','"
						+ temp.getLength_in_tokens() + "','" + -1 + "')";
				stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Initiate() {
		openConnection();
		createDatabase();
	}

	/*
	 * public static void main(String[] args) { try{
	 * System.out.println("hahahahaa"); FileWriter name = new FileWriter(
	 * ".\\My Output\\test.txt", true); BufferedWriter out = new
	 * BufferedWriter(name); out.write("hrami"); out.close(); } catch
	 * (IOException ioe) { System.out.println("file writer error"); } }
	 */

}
