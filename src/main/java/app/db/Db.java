package app.db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.tweak.HandleCallback;

public class Db {

	public Db() {
		
	}
	public void TestDB() {		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			
		}
		DBI dbi = new DBI("jdbc:hsqldb:mem:test","SA","");
		
		dbi.withHandle(new HandleCallback<Void>()
		{
		  public Void withHandle(Handle handle) throws Exception
		  {
		    handle.execute("create table silly (id int)");
		    return null;
		  }
		});
		
	}
}
