import java.awt.*;
import javax.swing.*;
import javax.swing.text.StyledEditorKit.BoldAction;
import java.awt.event.*;
import java.sql.*;

public class Register_Form extends JPanel implements ActionListener{
	
	 boolean count=true,count1=true;
	 int temp;
	 JTextField jid;
	 JCheckBox sub[]=new JCheckBox[10];
	 JRadioButton staff;
	 JRadioButton student;
	 JTextField jname;
	 JTextField juser;
	 JPasswordField jpassword;
	 JToggleButton submit,reset;
	 JLabel finalstatement;
	 String [] arr=new String[6];
	
	
	 Connection con;
	 PreparedStatement st;
	 Statement st1;
	 
	public Register_Form(){
		
		try
		{
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con=DriverManager.getConnection("jdbc:odbc:rahul");
		
		}
		catch(Exception e){System.out.println(e);}
		
		
		setVisible(true);
		setSize(500,500);
		setLayout(null);
		
		JLabel registration =new JLabel("<html><font color='blue'><marquee>Registration</marquee></font></html>");
		registration.setBounds(250, 10, 250, 50);
		registration.setFont(new Font(Font.SERIF,Font.BOLD|Font.ITALIC,30));
		add(registration);
		
		JLabel name=new JLabel("Name:");
		name.setBounds(180,70,100,50);
		name.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(name);
		
		jname=new JTextField(30);
		jname.setBounds(247,85,250,25);
		add(jname);
		
		JLabel type=new JLabel("Type:");
		type.setBounds(176,130,100,50);
		type.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(type);
		
		ButtonGroup buttongroup=new ButtonGroup();
		 staff=new JRadioButton("Staff",false);
		 student=new JRadioButton("Student",false);
		buttongroup.add(staff);
		buttongroup.add(student);
		staff.setBounds(247,135,150,50);
		staff.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		student.setBounds(247,170,150,50);
		student.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(staff);
		add(student);
		
		staff.addActionListener(this);
		student.addActionListener(this);
		
		JLabel id=new JLabel("ID  :");
		id.setBounds(180,230,100,50);
		id.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(id);
		
		 jid=new JTextField(30);
		jid.setBounds(247,245,250,25);
		add(jid);
		
		JLabel subjects=new JLabel("Subjects:");
		subjects.setBounds(180,290,150,50);
		subjects.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(subjects);
	
		
		sub[0]=new JCheckBox("MATHS");
		sub[0].setBounds(300,305,150,25);
		add(sub[0]);
		
		sub[1]=new JCheckBox("CP-2");
		sub[1].setBounds(300,330,200,25);
		add(sub[1]);
		
		sub[2]=new JCheckBox("DSA");
		sub[2].setBounds(300,355,220,25);
		add(sub[2]);
		
		sub[3]=new JCheckBox("OS");
		sub[3].setBounds(300,380,200,25);
		add(sub[3]);
		
		sub[4]=new JCheckBox("ITC");
		sub[4].setBounds(300,405,200,25);
		add(sub[4]);
		
		sub[5]=new JCheckBox("EVS");
		sub[5].setBounds(530,305,200,25);
		add(sub[5]);
		
		sub[6]=new JCheckBox("CPLAB");
		sub[6].setBounds(530,330,200,25);
		add(sub[6]);
		
		sub[7]=new JCheckBox("OSLAB");
		sub[7].setBounds(530,355,200,25);
		add(sub[7]);
		
		sub[8]=new JCheckBox("ITCLAB");
		sub[8].setBounds(530,380,230,25);
		add(sub[8]);
		
		sub[9]=new JCheckBox("IEML");
		sub[9].setBounds(530,405,200,25);
		add(sub[9]);
		
		JLabel user=new JLabel("Username:");
		user.setBounds(180,450,150,25);
		user.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(user);
		
		 juser=new JTextField(30);
		juser.setBounds(295,455,250,25);
		add(juser);
		
		JLabel password=new JLabel("Password:");
		password.setBounds(180,510,150,25);
		password.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		add(password);
		
		 jpassword=new JPasswordField(30);
		jpassword.setBounds(295,512,250,25);
		add(jpassword);
		
		submit=new JToggleButton("Submit");
		submit.setBounds(220,580,80,30);
		add(submit);
		submit.addActionListener(this);
		
		 reset=new JToggleButton("Reset");
		reset.setBounds(320,580,80,30);
		add(reset);
		reset.addActionListener(this);
		
		
		
	}//end of constructor
	
	public void actionPerformed(ActionEvent ie)
	{
		if(ie.getActionCommand().equals("Staff"))
		{
			jid.setEnabled(false);
			for(int i=0;i<10;i++)
			{
				sub[i].setEnabled(true);
			}
			arr[0]="staff";
			arr[1]=null;
			count=true;
			temp=0;	
		}
		if(ie.getActionCommand().equals("Student"))
		{
			
			jid.setEnabled(true);
			arr[0]="student";
			count=false;
			arr[5]=null;
			for(int i=0;i<10;i++)
			{
				sub[i].setEnabled(false);
			}
			
		}
		if(ie.getActionCommand().equals("Reset"))
		{
			
			jname.setText("");
			jid.setText("");
			juser.setText("");
			jpassword.setText("");
			for(int i=0;i<10;i++)
			{
				sub[i].setSelected(false);
			}
			staff.setSelected(false);
			student.setSelected(false);
			reset.setSelected(false);
		}
		if(ie.getActionCommand().equals("Submit"))
		{
			count1=true;
			if(!count)
			{
			arr[1]=jid.getText();
			try
			{
			temp=Integer.parseInt(arr[1]);
			}
			catch(NumberFormatException e)
			{
				count1=false;
				JOptionPane.showMessageDialog(this,"Enter valid ID no and try again."
		                ,"ERROR", JOptionPane.ERROR_MESSAGE);
				submit.setSelected(false);
			}
			}
			arr[2]=jname.getText();
			if(arr[2].equals(""))
			{
				count1=false;
				JOptionPane.showMessageDialog(this,"Enter valid name and try again."
		                ,"ERROR", JOptionPane.ERROR_MESSAGE);
				submit.setSelected(false);
			}
			if(count)
			{
			arr[5]="";
			for(int i=0;i<10;i++){
				if(sub[i].isSelected())
					arr[5]=arr[5]+sub[i].getText()+",";
			}
			
			try
			{
			arr[5]=arr[5].substring(0,arr[5].length()-1);
			}
			catch(Exception e)
			{
				count1=false;
				JOptionPane.showMessageDialog(this,"Enter the subjects and try again."
		                ,"ERROR", JOptionPane.ERROR_MESSAGE);
				submit.setSelected(false);
			}
			}
			if(count1)
			{
				
			arr[3]=juser.getText();
			char[] p=jpassword.getPassword();
			arr[4]=new String(p);
			
			try {
					String q="Insert into register values(?,?,?,?,?,?)";
					st=con.prepareStatement(q);
					st.setString(1, arr[2]);
					st.setString(2, arr[0]);
					st.setString(3, arr[3]);
					st.setString(4, arr[4]);
					st.setInt(5, temp);
					st.setString(6, arr[5]);
					st.execute();	
				} 
				catch(SQLException e){
					count1=false;
					JOptionPane.showMessageDialog(this, "Form submission failed.Please try again."
			                ,"ERROR", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					submit.setSelected(false);
				}
			}
				jname.setText("");
				jid.setText("");
				juser.setText("");
				jpassword.setText("");
				for(int i=0;i<10;i++){
					sub[i].setSelected(false);
				}
				staff.setSelected(false);
				student.setSelected(false);
				if(count1){
				JOptionPane.showMessageDialog(this, "Your form is successfully submitted"
		                ,"Form Submitted", JOptionPane.PLAIN_MESSAGE);
				submit.setSelected(false);
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
	


