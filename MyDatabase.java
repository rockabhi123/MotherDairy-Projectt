import java.sql.*;
class MyDatabase
{
  public static final Connection getCon()
  {
      Connection Con=null;
	  try
	  {  //Step-1 
	      Class.forName("com.mysql.cj.jdbc.Driver");
		  Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/motherdairy","root","12345678");
	  }
	  catch(Exception e)
	  {}
	  return Con;
  }
}