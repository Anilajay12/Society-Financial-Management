# <p align="center">Society-Financial-Management</p><br><br>

# Software Requirements

  Spring Tool Suite 4 <br/><br>
  Mysql Database<br/><br>
  Any Browser(Chrome, Firefox,Internet Explorer,Microsoft Edge)<br><br>

# Technologies Used

  JSP<br><br>
  MySQL<br><br>
  Servlets<br><br>
  
# Description about Project

It is a Project Related to Society (or) Residence Financial Management.Which is useful for managing our residency maintenance bills and residency budget ( Spendings on Events conducted on Society/Residency )
  
  In this Project we have 3 types of users
  1. Client / Customer who uses the application
  2. Employee who manages the customers,events
  3. Admin who will manage employees,events and generates the reports for spendings 


# Sending Email Remainder

To send the email to users you have to change the following two property values and in your google account you have to change account settings and allow less secure apps
<br><br>
    final String emailid="your email id";	// xyz@gmail.com
		<br><br>final String password="your password";
    
    It is present in controller -> SendEmailController.java and Line number 82 

# Important

You have to change the username and password of database in <b>web.xml</b> according to your database login details   <br><b>Project -> WebContent -> WEB-INF -> web.xml</b> 
