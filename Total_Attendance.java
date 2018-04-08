import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class Total_Attendance extends JFrame {
	
	 Connection con;
	 Statement st,st1;
	 ResultSet rs,rt;
	 Object colHeads[]=new Object[4];
	 int tot;String sub;
	 Object[][] data=new Object[10][4];
	 
 public Total_Attendance(int id1){
	 
	 setSize(500,250);
	 setLocation(360,260);
	 setVisible(true);
	 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	 
	  try
		{
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  con=DriverManager.getConnection("jdbc:odbc:rahul");
		
		  colHeads[0]="Subjects";
		  colHeads[2]="NO. of days absent";
		  colHeads[1]="Total lectures";
		  colHeads[3]="Present percentage";


    st=con.createStatement();
    String r1="Select count(distinct date),subject from attendance group by subject order by subject asc";
    rs=st.executeQuery(r1);
    int r=0,c=0;
    while(rs.next())
    {
    	c=0;
    	sub=rs.getString(2);
    	tot=rs.getInt(1);
    	if(sub.indexOf("LAB")!=-1)
    	tot=(int)Math.ceil(tot/2.0);	
    	data[r][c]=sub;
    	data[r][++c]=tot;
    	r++;
    }
    for( r=0;r<10;r++)
    {
    	String r2="Select count(id) from attendance where id="+id1+" and subject='"+data[r][0]+"'";
    	rs=st.executeQuery(r2);
    	while(rs.next())
    	data[r][2]=rs.getInt(1);
    	data[r][3]=(float)(Integer.parseInt(data[r][1].toString())-Integer.parseInt(data[r][2].toString()))/(float)Integer.parseInt(data[r][1].toString())*100;
    }
		}
		catch(Exception e){System.out.println(e);}

    JTable table = new JTable(data, colHeads);

    
    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(table, v, h);

    
    add(jsp, BorderLayout.CENTER);
    add(new JToggleButton("Print"),BorderLayout.SOUTH);
  }
}

