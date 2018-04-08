import java.awt.*;
import javax.swing.*;

  
public class Attendance extends JFrame {

  public static void main(String[] args) {
	  
	  Attendance jd=new Attendance();
	 jd.setVisible(true);
	 jd.setSize(500,500);
	 
    JTabbedPane jtp = new JTabbedPane();
    
    jtp.addTab("Home", new homeimg());
    jtp.addTab("Staff", new Staff());
    jtp.addTab("Student", new Student());
    jtp.addTab("Register", new Register_Form());
    jtp.addTab("Developers", new developers());
    
    jd.add(jtp);
    jd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   
  }
 
}

	class homeimg extends JPanel{ 
		String msg = " ********ONLINE ATTENDANCE SYSTEM****** ";
	   
	    public void paintComponent(Graphics g) {
	    	
			  super.paintComponent(g);
			  
			  Image img1 = new ImageIcon("E:\\attendance_system\\vjti-mumbai.jpg").getImage(); 
			  g.drawImage(img1, 50,0, this);
			  
			  g.setColor(Color.red);
			  g.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,30));
			  
		    g.drawString(msg, 300, 100);
		    g.drawString("SYBTech(COMPS)",500,150);
		    
		    Image img = new ImageIcon("E:\\attendance_system\\home_img.jpg").getImage(); 
	       
	         if(img != null){
	            g.drawImage(img, 20,260, this);
	            
		  }
	    }
	}
	class developers extends JPanel{
		
		String head="Developed by:";
		String rahul="Rahul Lachhani        -       121070035";
		String aditya="Aditya Narayanan      -      121070033";
		String pratik="Pratik Sutar             -        121070034";
		String sagar="Sagarsing Gairwar      -     121070036";
		String contact="Contact us-   lachhani.rahul@gmail.com";
		public void paintComponent(Graphics g) {
			  super.paintComponent(g);
			  
			  g.setColor(Color.red);
			  g.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,35));
			  g.drawString(head, 300,80);
			  
			  g.setColor(Color.blue);
			  g.setFont(new Font("Arial",Font.ITALIC,20));
			  g.drawString(rahul, 320, 150);
			  g.drawString(pratik, 320, 200);
			  g.drawString(aditya, 320, 250);
			  g.drawString(sagar, 320, 300);
			  
			  g.setColor(Color.black);
			  g.setFont(new Font("Arial",Font.BOLD,20));
			  g.drawString(contact, 320, 400);
			  
			  Image img1 = new ImageIcon("E:\\attendance_system\\vjti-mumbai.jpg").getImage(); 
			  g.drawImage(img1, 50,0, this);
	}
	}		
		
	    
	   
	