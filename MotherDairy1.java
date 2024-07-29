import java.util.*;
import java.sql.*;
class MotherDairy1
{
	public static void main(String st[])
	{
		   Scanner sc=new Scanner(System.in); 
		   int choice=420;
		   
		   while(!(choice==0))
		   {
			   System.out.println("Press 1 for Add Product");
			   System.out.println("Press 2 for Find Product");
			   System.out.println("Press 3 for Sale Product");
			   System.out.println("Press 4 for Read All Product");
			   System.out.println("Press 0 for Exit");
			   
			   choice = sc.nextInt();
			   
			   if(choice==1)
			   {
				   insertProduct();
			   }
			   else if(choice==2)
			   {
				   findProduct();
			   }
			   else if(choice==3)
			   {
				   saleProduct();
			   }
  
		   }
	}
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  INSERT PRODUCT START CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public static void insertProduct()
	{
		try
		{
	    Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter Product Name:- ");
		String name=sc.nextLine();
		System.out.print("Enter Product Price:- ");
		int price=sc.nextInt();
		System.out.print("Enter Product Quantity:- ");
		int quantity=sc.nextInt();
		
		
		Connection con=MyDatabase.getCon();
		
		String sql="insert into product(name,quantity,price)"+"values('"+name+"',"+quantity+","+price+");";
		
		Statement stmt=con.createStatement();
		
		int i=stmt.executeUpdate(sql);
		
		if(i>0)
		{
			System.out.println("Data Saved");
		}
		else
		{
			System.out.println("Query Prblm");
		}
		
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
 //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  INSERT PRODUCT END CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	
	
 //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  FIND PRODUCT START CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public static void findProduct()
	{
		
		try
		{
			
			Scanner sc=new Scanner(System.in);
			System.out.print("Please Enter Product Name:- ");
			String name=sc.nextLine();
			//int id = sc.nextInt();

             Connection con=MyDatabase.getCon();      
            
			//Create Statements..
			Statement stmt=con.createStatement();  

			//Get All Record into 
			ResultSet rs=stmt.executeQuery("select * from product where name="+"'"+name+"'");  
                  
            System.out.println("ID\tNAME\tPRICE\tQUANTITY");
			
			while(rs.next()) 
			{				
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(4)+"\t"+rs.getInt(3)); 
			}
			
			con.close();

			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		
	}

 //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  FIND PRODUCT END CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  SALE PRODUCT START CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public static void saleProduct()
	{
            try
			{
				Scanner sc=new Scanner(System.in);
				System.out.print("Please Enter Product Name: ");
				String name = sc.nextLine();
				
				System.out.print("Please Enter Product Quantity: ");
				int qty = sc.nextInt();
				
				  Connection con=MyDatabase.getCon();      
            
			//Create Statements..
			Statement stmt=con.createStatement();  

			//Get All Record into 
			ResultSet rs=stmt.executeQuery("select * from product where name="+"'"+name+"'");  
                  
		    //Get Actual Price And Quantity
				  int p=0,q=0;
			while(rs.next()) 
			{		
            q=rs.getInt(3); 		
			p=rs.getInt(4);
			}
			
			
			//Generate Bill
			
			System.out.println("SN\tNAME\tQUANTITY\tPRICE\t\tTOTAL");
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			System.out.println("1"+"\t"+name+"\t"+qty+"\t"+p+"\t\t"+(qty*p));
			
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			
			//Update Product into Table
			
			int res=q-qty;
			
			String sql="update product set quantity="+res+" where name="+"'"+name+"'";
		
		PreparedStatement p1=con.prepareStatement(sql);
		
		p1.execute();
			
			
			
			con.close();

				
				
				
				
				
			}
			catch(Exception e)
			{}
	}	
	
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  SALE PRODUCT END CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	
	
	
}