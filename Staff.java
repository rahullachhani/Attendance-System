import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;

public class Staff extends JPanel implements ActionListener,ItemListener {
		
	
	
	JLabel user,password,text,reg,date,subjects,hi;
	JTextField juser;
	JPasswordField jpassword;
	JToggleButton login,cancel,submit_staff,cancel_staff,signout_staff;
	JComboBox day,month,year,jsubjects;
	JCheckBox[] ids;
	JPanel p,p1;
	
	String dd,yy,att_date,att_subject,name_staff,retsub;
	String[] subjects_taken;
	int mm;
	boolean count=false;
	Vector id_staff=new Vector();
	 
	 Connection con;
	 Statement st;
	 ResultSet rs;
	
	 
	 Staff()
	{
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    con=DriverManager.getConnection("jdbc:odbc:rahul");
		}
		catch(Exception e){System.out.println(e);}
		
		
		setVisible(true);
		setSize(1000,1000);
		setLayout(null);
		
		user=new JLabel("Username:");
		user.setBounds(300,100,150,25);
		user.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(user);
		
		juser=new JTextField(30);
		juser.setBounds(415,102,200,25);
		add(juser);
		
		password=new JLabel("Password:");
		password.setBounds(300,160,150,25);
		password.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(password);
		
		jpassword=new JPasswordField(30);
		jpassword.setBounds(415,163,200,25);
		add(jpassword);
		
		login=new JToggleButton("Login");
		login.setBounds(350,230,100,25);
		add(login);
		login.addActionListener(this);
		
		cancel=new JToggleButton("Cancel");
		cancel.setBounds(470,230,100,25);
		add(cancel);
		cancel.addActionListener(this);
	}
	public  void staff_form(String username)
	{
		text=new JLabel("<html><font color='red'>Enter the date and subject.</font><html>");
		text.setBounds(400,100,850,27);
		text.setFont(new Font(Font.SANS_SERIF,Font.BOLD|Font.ITALIC,15));
	
		
		date=new JLabel("Date:");
		date.setBounds(300,150,100,25);
		date.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(date);
		add(text);
		
		day=new JComboBox();
		day.addItem("");
		for(int i=1;i<32;i++)
		{
			day.addItem(i);
		}
		day.setBounds(400,153,50,25);
		add(day);
		day.addItemListener(this);
		
		
		String[] mon={"","January","February","March","April","May","June","July","August","September","October","November","December"};
		month=new JComboBox();
		for(int i=0;i<mon.length;i++)
		{
			month.addItem(mon[i]);
		}
		month.setBounds(470,153,100,25);
		add(month);
		month.addItemListener(this);
	

		year=new JComboBox();
		year.addItem("");
		for(int i=2013;i<2025;i++)
		{
			year.addItem(i);
		}
		year.setBounds(600,153,100,25);
		add(year);
		year.addItemListener(this);
		
		
		    subjects=new JLabel("Subjects:");
			subjects.setBounds(300,200,150,25);
			subjects.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
			add(subjects);
			
			String q="Select subjects from register where username='"+username+"'";
			try {
				st=con.createStatement();
				 rs = st.executeQuery(q);
				 if(rs.next())
				 {
					retsub=rs.getString(1); 
				 }
				} 
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			subjects_taken=retsub.split(",");
			 
			jsubjects=new JComboBox();
			jsubjects.addItem("");
			for(int i=0;i<subjects_taken.length;i++)
			{
				jsubjects.addItem(subjects_taken[i]);
			}
			jsubjects.setBounds(450,200,100,25);
			add(jsubjects);
			jsubjects.addItemListener(this);
			
			
			reg=new JLabel("<html><font color='red'>Mark the IDs which were absent.</font></html>");
			reg.setBounds(385,245,250,25);
			reg.setFont(new Font(Font.SANS_SERIF,Font.BOLD|Font.ITALIC,15));
			add(reg);
			
			p=new JPanel();
			p.setBounds(200,270,600,300);
			p.setLayout(new GridLayout(10,10));
			Vector v=new Vector();
			String r="select distinct id from register where type='student' order by id asc";
			try {
				rs=st.executeQuery(r);
				while(rs.next())
				{
					v.addElement(rs.getInt(1));
				}
				ids=new JCheckBox[v.size()];
				
				for(int i=0;i<v.size();i++)
				{
					
					ids[i]=new JCheckBox(v.elementAt(i).toString());
					p.add(ids[i]);
					ids[i].addItemListener(this);
					ids[i].setEnabled(false);
					
				}
				
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v.removeAllElements();
			add(p);
			
			p1=new JPanel();
			p1.setLayout(new GridLayout(1,3,10,10));
			p1.setBounds(350,570,300,25);
			
			submit_staff=new JToggleButton("Submit");
			submit_staff.addActionListener(this);
			p1.add(submit_staff);
			
			cancel_staff=new JToggleButton("Cancel");
			cancel_staff.addActionListener(this);
			p1.add(cancel_staff);
			
			signout_staff=new JToggleButton("Sign Out");
			signout_staff.addActionListener(this);
			p1.add(signout_staff);
			add(p1);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Login"))
		{
			
			String type;
			int id;
			String username=juser.getText();
			char[] p=jpassword.getPassword();
			String password1=new String(p);
			
			String q="Select type,id,name from register where  username = '" + username + "' and password = '" + password1 + "'";  
			try {
				st=con.createStatement();
				 rs = st.executeQuery(q);
				
				 if(rs.next())
				 {
					
					  
					 name_staff=rs.getString(3);
					  id=rs.getInt(2);
					  if(rs.getString(1).equals("staff"))
					  {
						  	juser.setText("");
							jpassword.setText("");
							login.setSelected(false);
							
							user.setVisible(false);
							password.setVisible(false);
							juser.setVisible(false);
							jpassword.setVisible(false);
							cancel.setVisible(false);
							login.setVisible(false);
						 
							staff_form(username);
					 }
					  else
					  {
						  JOptionPane.showMessageDialog(this, "You are not a staff.Login into student."
					                ,"ERROR", JOptionPane.ERROR_MESSAGE);
						  juser.setText("");
							jpassword.setText("");
							login.setSelected(false);
					  }
					 
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(this, "Authentication failed.Try again"
				                ,"ERROR", JOptionPane.ERROR_MESSAGE);
						juser.setText("");
						jpassword.setText("");
						login.setSelected(false);
				 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 JOptionPane.showMessageDialog(this, "Authentication failed.Try again"
			                ,"ERROR", JOptionPane.ERROR_MESSAGE);
					juser.setText("");
					jpassword.setText("");
					login.setSelected(false);
				}
			
		}
		if(ae.getSource()==cancel)
		{
			juser.setText("");
			jpassword.setText("");
			cancel.setSelected(false);
		}
		if(ae.getSource()==cancel_staff)
		{
			day.setSelectedItem("");
			month.setSelectedItem("");
			year.setSelectedItem("");
			jsubjects.setSelectedItem("");
			for(int i=0;i<ids.length;i++)
			{
				ids[i].setSelected(false);
			}
			cancel_staff.setSelected(false);
		}
		if(ae.getActionCommand().equals("Submit"))
		{
			for(int i=0;i<ids.length;i++)
			{
				if(ids[i].isSelected())
				{
					 id_staff.addElement(ids[i].getLabel());
					 
				}	
			}
			if(count)
			{
			String s="";
			
			if(id_staff.size()==0)
			{
				s="Insert into attendance (date,subject) values ('"+att_date+"','"+att_subject+"')";
				
				try {
					st.executeUpdate(s);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					count=false;
					JOptionPane.showMessageDialog(this, "Action failed.Please try again"
			                ,"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			for(int i=0;i<id_staff.size();i++){
				int id=Integer.parseInt(id_staff.elementAt(i).toString());
				s="Insert into attendance values('"+att_date+"','"+att_subject+"',"+id+")";
				try
				{
				st.executeUpdate(s);
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				count=false;
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Action failed.Please try again"
		                ,"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
			else
			{
				JOptionPane.showMessageDialog(this, "Action failed.Please try again."
		                ,"ERROR", JOptionPane.ERROR_MESSAGE);
			}
			if(count)
			JOptionPane.showMessageDialog(this, "Attendance saved successfully."
	                ,"Attendance marked", JOptionPane.PLAIN_MESSAGE);
			submit_staff.setSelected(false);
			id_staff.removeAllElements();
			
		}
		if(ae.getActionCommand().equals("Sign Out"))
		{
			text.setVisible(false);
			reg.setVisible(false);
			day.setVisible(false);
			date.setVisible(false);
			month.setVisible(false);
			year.setVisible(false);
			subjects.setVisible(false);
			jsubjects.setVisible(false);
			p.setVisible(false);
			p1.setVisible(false);
			 
			 password.setVisible(true);
			 user.setVisible(true);
			 juser.setVisible(true);
			 jpassword.setVisible(true);
			 cancel.setVisible(true);
			 login.setVisible(true);
		}
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==jsubjects)
		{
			att_subject=(String)jsubjects.getSelectedItem();
		
		}
		if(ie.getSource()==day)
		{
			dd=day.getSelectedItem().toString();
			
		}
		if(ie.getSource()==month)
		{
			mm=month.getSelectedIndex();
			
		}
		if(ie.getSource()==year)
		{
			yy=year.getSelectedItem().toString();
			
		}
		att_date=yy+"-"+mm+"-"+dd;
		
		if(jsubjects.getSelectedItem().equals("")||day.getSelectedItem().equals("")||month.getSelectedItem().equals("")||year.getSelectedItem().equals(""))
		{
			count=false;
			for(int i=0;i<ids.length;i++)
			{
				ids[i].setEnabled(false);
				
			}
		}
		else
		{
			count=true;
			for(int i=0;i<ids.length;i++)
			{
				ids[i].setEnabled(true);
			}
		}
				
	}
	public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        Image img = new ImageIcon("E:\\attendance_system\\vjti-mumbai.jpg").getImage();  
	        if(img != null){
	            g.drawImage(img,50,0, this);
	            
	        }
	    }
	}



