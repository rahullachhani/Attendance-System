import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;



 class Student extends JPanel implements ActionListener,ItemListener
 {
		
	
	boolean count;
	JLabel user,password,date,days[]=new JLabel[5],times[]=new JLabel[7],selection_date[]=new JLabel[5] ;
	JTextField juser;
	JPasswordField jpassword;
	JToggleButton login,cancel,subject[][]=new JToggleButton[7][5],submit,total,signout;
	JComboBox day,month,year;
	String name_stud,username,password1,type,dd,yy,stud_date;
	int id,mm;
	 JPanel tt;
	 
	 String[] selections=new String[5];
	
	 JPanel p1;JToggleButton submit_staff,cancel_staff;
	 JPanel p2;
	 Connection con;
	 Statement st,st1;
	 ResultSet rs,rt;
	
		 String[][] timetable={{"OSLAB","EVS","MATHS","MATHS","ITC"},{"OSLAB","CP-2","DSA","MATHS","OS"},{"OSLAB","CP-2","EVS","CP-2","DSA"},{"OS","OS","OS","CP-2","DSA"},{"EVS","CPLAB","IAI","IEML","ITCLAB"},{"ITC","CPLAB","IAI","IEML","ITCLAB"},{"ITC","CPLAB","MATHS","IEML","ITCLAB"}};
		 String[][] timetable1={{"ITCLAB","EVS","MATHS","MATHS","ITC"},{"ITCLAB","CP-2","DSA","MATHS","OS"},{"ITCLAB","CP-2","EVS","CP-2","DSA"},{"OS","OS","OS","CP-2","DSA"},{"EVS","IEML","IAI","CPLAB","OSLAB"},{"ITC","IEML","IAI","CPLAB","OSLAB"},{"ITC","IEML","MATHS","CPLAB","OSLAB"}};
	
	 
	 Student()
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
	 public void student_form()
	 {
		 setSize(700,700);
		 
		 	date=new JLabel("Date:");
			date.setBounds(400,100,100,25);
			date.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
			add(date);
			
			
			day=new JComboBox();
			day.addItem("");
			for(int i=1;i<32;i++)
			{
				day.addItem(i);
			}
			day.setBounds(500,103,50,25);
			add(day);
			day.addItemListener(this);
			
			
			String[] mon={"","January","February","March","April","May","June","July","August","September","October","November","December"};
			month=new JComboBox();
			for(int i=0;i<mon.length;i++)
			{
				month.addItem(mon[i]);
			}
			month.setBounds(570,103,100,25);
			add(month);
			month.addItemListener(this);
		

			
			year=new JComboBox();
			year.addItem("");
			for(int i=2013;i<2025;i++)
			{
				year.addItem(i);
			}
			year.setBounds(700,103,100,25);
			add(year);
			year.addItemListener(this);
		 
			submit=new JToggleButton("Submit");
			submit.setBounds(830,103,100,25);
			add(submit);
			submit.addActionListener(this);
		 
		 
			Border b=BorderFactory.createLineBorder(Color.blue,1);
			
			p1=new JPanel();
			p1.setLayout(new GridLayout(9,6));
			p1.setBounds(330,180,700,380);
			
			JLabel time2=new JLabel("Date",JLabel.CENTER);
			time2.setBorder(b);
			p1.add(time2);
			
			for(int i=0;i<5;i++)
			{
				selection_date[i]=new JLabel("",JLabel.CENTER);
				selection_date[i].setBorder(b);
				p1.add(selection_date[i]);
			}
			
			JLabel time1=new JLabel("Time/Day",JLabel.CENTER);
			time1.setBorder(b);
			p1.add(time1);
			
			String[] time={"9-10","10-11","11:15-12:15","12:15-1:15","2-3","3-4","4-5"};
			String[] days_of_week={"Monday","Tuesday","Wednesday","Thursday","Friday"};
			
			for(int i=0;i<5;i++)
			{
				days[i]=new JLabel(days_of_week[i],JLabel.CENTER);
				days[i].setBorder(b);
				p1.add(days[i]);
			}
			
			for(int i=0;i<7;i++)
			{
				times[i]=new JLabel(time[i],JLabel.CENTER);
				times[i].setBorder(b);
				p1.add(times[i]);
				
			for(int j=0;j<5;j++)
				{
					if(id>121070032)
						subject[i][j]=new JToggleButton(timetable[i][j]);
					else
						subject[i][j]=new JToggleButton(timetable1[i][j]);
					
					subject[i][j].setEnabled(true);
					subject[i][j].addActionListener(this);
					p1.add(subject[i][j]);
				}
			}
			
			add(p1);
			
			p2=new JPanel();
			p2.setBounds(330,600,700,50);

			JLabel l1=new JLabel("PRESENT");
			JToggleButton red=new JToggleButton();
			red.setBackground(Color.red);
			JToggleButton green=new JToggleButton();
			green.setBackground(Color.green);
			JToggleButton blue=new JToggleButton();
			
			
			JLabel l2=new JLabel("ABSENT");
			JLabel l3=new JLabel("NO ENTRY",JLabel.CENTER);
			p2.add(green);
			p2.add(l1);
			p2.add(red);
			p2.add(l2);
			p2.add(blue);
			total=new JToggleButton("CAL_TOTAL_ATTENDANCE");
			total.addActionListener(this);
			p2.add(l3);
			p2.add(total);
			signout=new JToggleButton("Sign Out");
			signout.addActionListener(this);
			p2.add(signout);
			add(p2);
			
		 
	 }
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Sign Out"))
		{
			
			day.setVisible(false);
			date.setVisible(false);
			month.setVisible(false);
			year.setVisible(false);
			submit.setVisible(false);
			p1.setVisible(false);
			p2.setVisible(false);	
			
			 password.setVisible(true);
			 user.setVisible(true);
			 juser.setVisible(true);
			 jpassword.setVisible(true);
		     cancel.setVisible(true);
		     login.setVisible(true);
		}
	
		if(ae.getActionCommand().equals("Login"))
		{
			
			
			username=juser.getText();
			char[] p=jpassword.getPassword();
			 password1=new String(p);
			 
			String q="Select type,id,name from register where  username = '" + username + "' and password = '" + password1 + "'";  
			try {
				st=con.createStatement();
				 rs = st.executeQuery(q);
				 if(rs.next())
				 {
					
					  name_stud=rs.getString(3);
					  id=rs.getInt(2);
					  if(rs.getString(1).equals("student"))
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
						 
						    
						  student_form();
						 
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(this, "You are not a student.Login into staff."
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
		if(ae.getActionCommand().equals("Submit"))
		{
			if(!(day.getSelectedItem().equals(""))&&!(month.getSelectedItem().equals(""))&&!(year.getSelectedItem().equals("")))
			{
				
				 for(int j=0;j<5;j++)
			        {
					 for(int i=0;i<7;i++)
						{
							
								subject[i][j].setBackground(null);
							
						}
			        }
				 
				stud_date=yy+"-"+mm+"-"+dd;
				
		        Calendar calender = Calendar.getInstance();
		        int m=mm;
		        
		        calender.set(Integer.parseInt(yy),--m,Integer.parseInt(dd));
		        System.out.println(calender.getTime());
		        int week=calender.get(Calendar.DAY_OF_WEEK);
		        System.out.println(week);
		     
		        for(int i=0;i<5;i++)
		        {
		        	selections[i]=null;
		        }
		        switch(week)
		        {
		        case 1:
		        	JOptionPane.showMessageDialog(this, "Sorry,It's Sunday."
			                ,"ERROR", JOptionPane.ERROR_MESSAGE);
		        	day.setSelectedItem("");
		        	month.setSelectedItem("");
		        	year.setSelectedItem("");
		        	break;
		        	
		        case 2:
		        	selections[0]=stud_date;
		        	for(int i=1;i<5;i++){
		        	 calender.add(Calendar.DAY_OF_WEEK, +1);
		        	 int selectedyear=calender.get(Calendar.YEAR);
		        	 int selectedmonth=calender.get(Calendar.MONTH);
		        	 int selectedday=calender.get(Calendar.DAY_OF_MONTH);
		        	 selections[i]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
		        	 System.out.println(selections[i]);
		        	}
		        	 break;
		        case 3:
		        	 calender.add(Calendar.DAY_OF_WEEK, -1);
		        	 int selectedyear=calender.get(Calendar.YEAR);
		        	 int selectedmonth=calender.get(Calendar.MONTH);
		        	 int selectedday=calender.get(Calendar.DAY_OF_MONTH);
		        	 selections[0]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
		        	 for(int i=1;i<5;i++){
			        	 calender.add(Calendar.DAY_OF_WEEK, +1);
			        	  selectedyear=calender.get(Calendar.YEAR);
			        	  selectedmonth=calender.get(Calendar.MONTH);
			        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
			        	 selections[i]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
			        	 System.out.println(selections[i]);
			        	}
		        	 break;
		        	 
		        case 4:
		        	calender.add(Calendar.DAY_OF_WEEK, -2);
		        	  selectedyear=calender.get(Calendar.YEAR);
		        	  selectedmonth=calender.get(Calendar.MONTH);
		        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
		        	 selections[0]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
		        	 for(int i=1;i<5;i++){
			        	 calender.add(Calendar.DAY_OF_WEEK, +1);
			        	  selectedyear=calender.get(Calendar.YEAR);
			        	  selectedmonth=calender.get(Calendar.MONTH);
			        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
			        	 selections[i]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
			        	 System.out.println(selections[i]);
			        	}
		        	 break;
		        case 5:
		        	calender.add(Calendar.DAY_OF_WEEK, -3);
		        	 System.out.println("selected="+calender.getTime());
		        	 selectedyear=calender.get(Calendar.YEAR);
		        	  selectedmonth=calender.get(Calendar.MONTH);
		        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
		        	 selections[0]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
		        	 for(int i=1;i<5;i++){
			        	 calender.add(Calendar.DAY_OF_WEEK, +1);
			        	 System.out.println(calender.getTime());
			        	  selectedyear=calender.get(Calendar.YEAR);
			        	  selectedmonth=calender.get(Calendar.MONTH);
			        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
			        	  
			        	 selections[i]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
			        	 System.out.println(selections[i]);
			        	}
		        	 break;
		        	 
		        case 6:
		        	calender.add(Calendar.DAY_OF_WEEK, -4);
		        	  selectedyear=calender.get(Calendar.YEAR);
		        	  selectedmonth=calender.get(Calendar.MONTH);
		        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
		        	 selections[0]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
		        	 for(int i=1;i<5;i++){
			        	 calender.add(Calendar.DAY_OF_WEEK, +1);
			        	  selectedyear=calender.get(Calendar.YEAR);
			        	  selectedmonth=calender.get(Calendar.MONTH);
			        	  selectedday=calender.get(Calendar.DAY_OF_MONTH);
			        	 selections[i]=(selectedyear+"-"+(++selectedmonth)+"-"+selectedday);
			        	 System.out.println(selections[i]);
			        	}
		        	 break;
		        	 
		        case 7:
		        	JOptionPane.showMessageDialog(this, "Sorry,It's Saturday."
			                ,"ERROR", JOptionPane.ERROR_MESSAGE);
		        	day.setSelectedItem("");
		        	month.setSelectedItem("");
		        	year.setSelectedItem("");
		        	break;
		        	 
		        }
		        for(int i=0;i<5;i++)
				{
		        	selection_date[i].setBackground(Color.pink);
					selection_date[i].setText(selections[i]);
					
					
				}
		        try
		        {
					st1=con.createStatement();
				} 
		        catch (SQLException e2)
		        {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        for(int j=0;j<5;j++)
		        {
				String r="Select subject from attendance where date='"+selections[j]+"'";
				System.out.println(r);
				try {
					rt=st.executeQuery(r);
					while(rt.next())
					{
						String s=rt.getString(1);
						
						for(int i=0;i<7;i++)
						{
							if(subject[i][j].getLabel().equals(s))
							{
								subject[i][j].setBackground(Color.green);
								subject[i][j].setForeground(Color.black);
							}
						}
					}
					rt.close();
					
				}
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				r="Select subject,date,id from attendance where id="+id+" and date='"+selections[j]+"'";
				
				
				try {
					
					rs=st1.executeQuery(r);
					
					while(rs.next())
					{
						String s=(rs.getString(1));
						
						for(int i=0;i<7;i++)
						{
							if(subject[i][j].getLabel().equals(s))
							{
								subject[i][j].setBackground(Color.red);
								subject[i][j].setForeground(Color.black);
							}
						}
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 
			}	 
				submit.setSelected(false);
		}
		if(ae.getSource()==total)
		{
			
			new Total_Attendance(id);
			total.setSelected(false);
			
		}
		for(int j=0;j<5;j++)
			for(int i=0;i<7;i++)
			{
				if(ae.getSource()==subject[i][j])
					subject[i][j].setSelected(false);
			}
		
	}
	public void itemStateChanged(ItemEvent ie)
	{
		
		if(ie.getSource()==day)
		{
			dd=day.getSelectedItem().toString();
			
		}
		else 
		if(ie.getSource()==month)
		{
			mm=month.getSelectedIndex();
			
		}
		else
		if(ie.getSource()==year)
		{
			yy=year.getSelectedItem().toString();
			
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
 
 	
