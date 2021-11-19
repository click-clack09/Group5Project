package application.model;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
	//holds all data
	//userID
	//password
	//list of contacts
	private static URL lastHub;
	private static String userName;
	private static int userID;
	private static LifeHub currentHub;
	private static ArrayList<LifeHub> userHubs;
	
	//This is not SOLID compliant. Use index in ed class instead?
	private static String currentClass;
	
	private static ArrayList<Contact> userContacts;
	private static ArrayList<HubEvent> userEvents;
	private static ArrayList<SchoolClass> classes;
	private String userLoggedInName;
	private String password;
		
	public User(String user, String password) {
		this.userLoggedInName = user;
		this.password = password;
	}
	
	//QC with Richard
	public static void closeConnection(Connection connection) {
		
		try {
			if(!connection.isClosed()) {
					System.out.println("Closing DB connection.");
					connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Error closing DB: "+e.getMessage());
		}
	}
	
	public static Boolean validate(String userName, String password)
	{  	
		boolean flag = false;
		Connection conn;
		//Using custom DatabaseConnection class with static method
 		String login_query = "SELECT * FROM lifeHub.User WHERE username = ? AND password = ?";
		try {
		    conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(login_query);
			ps.setString(1,userName);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			//false means an empty result set
			if(rs.next() != false) {

				System.out.println("User login sucessful for user: "+rs.getString("username")+" user id: "
							+rs.getInt("user_id")); 
				flag = true;
					
				//Set the logged in users static vars 
				User.setUserName(rs.getString("username"));
				User.setUserID(rs.getInt("user_id"));
				System.out.println("calling User.getUserName: "+User.getUserName());
				System.out.println("calling User.getUserID: "+User.getUserID());

				try {
					conn.close();
				}catch (SQLException se){

					System.out.println("Error closing SQL connection.");
				}
					return flag;
		    }
  	
		}catch(SQLSyntaxErrorException see) {
		    System.out.println("Error: DB syntax is incorrect.");
		          	//see.printStackTrace();
		}catch(Exception e) {
		    System.out.println("Error: DB connection failed.");
		          	//e.printStackTrace();
		}
		System.out.println("Error: Incorrect credentials.");
		return flag;
	}

	/**
	 * @return the lastHub
	 */
	public static URL getLastHub() {
		return lastHub;
	}

	/**
	 * @return the userName
	 */
	public static String getUserName() {
		return userName;
	}

	/**
	 * @return the userID
	 */
	public static int getUserID() {
		return userID;
	}

	/**
	 * @return the currentHub
	 */
	public static LifeHub getCurrentHub() {
		return currentHub;
	}

	/**
	 * @return the userHubs
	 */
	public static ArrayList<LifeHub> getUserHubs() {
		return userHubs;
	}

	/**
	 * @return the currentClass
	 */
	public static String getCurrentClass() {
		return currentClass;
	}

	/**
	 * @return the userContacts
	 */
	public static ArrayList<Contact> getUserContacts() {
		return userContacts;
	}

	/**
	 * @return the userEvents
	 */
	public static ArrayList<HubEvent> getUserEvents() {
		return userEvents;
	}

	/**
	 * @return the classes
	 */
	public static ArrayList<SchoolClass> getClasses() {
		return classes;
	}

	/**
	 * @param lastHub the lastHub to set
	 */
	public static void setLastHub(URL lastHub) {
		User.lastHub = lastHub;
	}

	/**
	 * @param userName the userName to set
	 */
	public static void setUserName(String userName) {
		User.userName = userName;
	}

	/**
	 * @param userID the userID to set
	 */
	public static void setUserID(int userID) {
		User.userID = userID;
	}

	/**
	 * @param currentHub the currentHub to set
	 */
	public static void setCurrentHub(LifeHub currentHub) {
		User.currentHub = currentHub;
	}

	/**
	 * @param userHubs the userHubs to set
	 */
	public static void setUserHubs(ArrayList<LifeHub> userHubs) {
		User.userHubs = userHubs;
	}

	/**
	 * @param currentClass the currentClass to set
	 */
	public static void setCurrentClass(String currentClass) {
		User.currentClass = currentClass;
	}

	/**
	 * @param userContacts the userContacts to set
	 */
	public static void setUserContacts(ArrayList<Contact> userContacts) {
		User.userContacts = userContacts;
	}

	/**
	 * @param userEvents the userEvents to set
	 */
	public static void setUserEvents(ArrayList<HubEvent> userEvents) {
		User.userEvents = userEvents;
	}

	/**
	 * @param classes the classes to set
	 */
	public static void setClasses(ArrayList<SchoolClass> classes) {
		User.classes = classes;
	}
	//////////////////////////////////////////////////////////////////////////////
	//These will be used to send things back to the DB
		public static void addEvent() {
			
		}

		
		public static boolean checkForContactInTable(Contact contact) {
			boolean flag = false;//free and clear to add contact
		    String check_for_existing_contact = "SELECT * FROM lifeHub.Contact WHERE user_id = ? AND contact_name = ?";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(check_for_existing_contact);
				ps.setInt(1, User.getUserID());
				ps.setString(2, contact.getName());
				ResultSet rs = ps.executeQuery();					
					//false means an empty result set
					if(rs.next() != false) {
						System.out.println("contact.getName() EXISTS in Contact Table. Return TRUE");
						flag = true;//don't add contact, already exists
			        }
		
					//close connection
					//closeConnection(conn);
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while while checking for existing contact name in Contact Table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed while checking for existing contact name in Contact Table.");
			          	e.printStackTrace();
			    }
			return flag;
		}

		public static void executeInsertIntoContactTable(Contact contact) {
			String insert_into_contact_table = "INSERT INTO lifeHub.Contact (user_id,contact_name) "+"VALUES(?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_contact_table);
				ps.setInt(1, User.getUserID());
				ps.setString(2, contact.getName());
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert of new contact!!!");
				}
				else {
					System.out.println("Failed insert of new contact!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting contact name in Contact Table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting contact name in Contact Table");
			          	e.printStackTrace();
			    }
			
		}

		public static void executeInsertIntoPhoneNumberTable(String contact_name, String phone_number, String type) {
			System.out.println("Inserting phone number into table: "+contact_name+" "+phone_number+" "+
		    type);
			
			String insert_into_phone_number_table = "INSERT INTO lifeHub.PhoneNumber (phone_num_id, user_id,contact_name,contact_phone,contact_phone_type) "+"VALUES(?,?,?,?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_phone_number_table);
				ps.setInt(1, 0);
				ps.setInt(2, User.getUserID());
				ps.setString(3, contact_name);
				ps.setString(4, phone_number);
				ps.setString(5, type);
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert into PhoneNumber table!!!");
				}
				else {
					System.out.println("Failed insert into PhoneNumber table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting into PhoneNumber table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting into PhoneNumber table");
			          	e.printStackTrace();
			    }
			
		}

		public static void executeInsertIntoEmailAddressTable(String contact_name, String email, String type) {
			System.out.println("Inserting email into table: "+contact_name+" "+email+" "+
				    type);
			String insert_into_email_address_table = "INSERT INTO lifeHub.EmailAddress (email_id,user_id,email_contact,email_detail,email_type) "+"VALUES(?,?,?,?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_email_address_table);
				ps.setInt(1, 0);
				ps.setInt(2, User.getUserID());
				ps.setString(3, contact_name);
				ps.setString(4, email);
				ps.setString(5, type);
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert into EmailAddress table!!!");
				}
				else {
					System.out.println("Failed insert into EmailAddress table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting into EmailAddress table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting contact name into EmailAddress table");
			          	e.printStackTrace();
			    }
		}
		public static boolean addContact(Contact contact) {
		//"INSERT INTO Employees (id, first, last, age) " +
	            //"VALUES(200,'Zia', 'Ali', 30)";
	  
			boolean result;

			//userID;//UserID Int
			
			//push userID and contact name up to Contact Table
			//userID; //name of user's current hub
			//if (contact table doesn't contain contact.getName())

			result = User.checkForContactInTable(contact);
			
			if(!result) {
				User.executeInsertIntoContactTable(contact);
				
				if (contact.getPhoneList()!=null) {
				
					for (int i = 0; i < contact.getPhoneList().size(); i++)
					{		
						User.executeInsertIntoPhoneNumberTable(contact.getName(), contact.getPhoneList().get(i).getNumber(),
								contact.getPhoneList().get(i).getType());
						//push to phone table as userID + contact_name [contact.getName()] + 
						//contact_phone [contact.getPhoneList(i).getNumber]+ contact_type. [contact.getPhoneList(i).getType];
					}
				}
				

				if (contact.getEmailsList()!=null) {
					for (int i=0; i< contact.getEmailsList().size(); i++)
					{
						//push to email table as userID + contact_name [contact.getName()]+ 
						//contact_email [contact.getEmailsList(i).getAddress]+ email_type [contact.getEmailsList(i).getType];
						User.executeInsertIntoEmailAddressTable(contact.getName(), contact.getEmailsList().get(i).getAddress(),
								contact.getEmailsList().get(i).getType());				
					}
				}
			}
			return !result;
		}

		//eventID,eventType,hubName,eventName,imgPath,text
		public static void executeInsertIntoTaskTable(int eventID, int eventType, String hubName, String eventName, String imgPath,
				String text) {
			System.out.println("Inserting task into Task table: "+hubName+" "+eventName+" "+text);
			String insert_into_task_table = "INSERT INTO lifeHub.Task (task_id,user_id,event_id,event_type,event_hub,event_name,img_path,text) "+"VALUES(?,?,?,?,?,?,?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_task_table);
				ps.setInt(1, 0);
				ps.setInt(2, User.getUserID());
				ps.setInt(3, eventID);
				ps.setInt(4, eventType);
				ps.setString(5, hubName);
				ps.setString(6, eventName);
				ps.setString(7, imgPath);
				ps.setString(8, text);
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert into Task table!!!");
				}
				else {
					System.out.println("Failed insert into Task table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting into Task table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting into Task table");
			          	e.printStackTrace();
			    }
		}
		public static void addTask(Task task, String className) {
			//need user_id event_id=99 event_type event_hub event_name img_path text
			User.getUserID(); //user_id
			int eventID = 99;
			int eventType = User.getCurrentHub().getEventType(); 
			//String eventTypeString = "";
			String hubName = User.getCurrentHub().getHubName();
			String eventName;
			String imgPath = "";
			String text = task.getText();
			
			if (User.getCurrentHub().getEventType()==2)//this means it's a Business tasks link to Hub
			{
				eventName=hubName;
				//send to hub copy ot class String imgPath = null, String text task.getText()		
			}
			else
			{
				//send to hub and class
				eventName = className;
			}
			//do insert here
			User.executeInsertIntoTaskTable(eventID,eventType,hubName,eventName,imgPath,text);
			
		}
		//add deletes based on these adds
		//QC with Richard
		
		public static void executeDeleteFromSchoolClassTable(int userID, String hubName, String schoolClassName, String schoolClassLocation, String schoolClassProfessor) {
			System.out.println("Deleting from SchoolClass table: "+hubName+" "+schoolClassName+" "+schoolClassProfessor);
			String delete_from_school_class_table = "DELETE FROM lifeHub.SchoolClass WHERE user_id = ? AND hub_name = ? AND school_class_name = ?"
					+ " AND school_class_location = ? AND school_class_professor = ?";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(delete_from_school_class_table);
				ps.setInt(1, userID);
				ps.setString(2, hubName);
				ps.setString(3, schoolClassName);
				ps.setString(4, schoolClassLocation);
				ps.setString(5, schoolClassProfessor);
		
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful Deletion from SchoolClass table!!!");
				}
				else {
					System.out.println("Failed Deletion SchoolClass table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while deleting from School Class table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed while deleting from School Class table");
			          	e.printStackTrace();
			    }
		}
		public static void deleteClass(SchoolClass thisClass) {
			//userID hubName schoolClassName schoolClassLocation schoolClassProfessor
			int userID = User.getUserID();
			String hubName = User.getCurrentHub().getHubName();
			String schoolClassName = thisClass.getClassName();
			String schoolClassLocation = thisClass.getLocation();
			String schoolClassProfessor = thisClass.getProfessor();
			User.executeDeleteFromSchoolClassTable(userID,hubName,schoolClassName,schoolClassLocation,schoolClassProfessor);	
		}

		//userID,hubName,schoolClassName,schoolClassLocation,schoolClassProfessor
		public static void executeInsertIntoSchoolClassTable(int userID, String hubName, String schoolClassName, String schoolClassLocation, String schoolClassProfessor) {
			System.out.println("Inserting task into SchoolClass table: "+hubName+" "+schoolClassName+" "+schoolClassProfessor);
			String insert_into_task_table = "INSERT INTO lifeHub.SchoolClass (school_class_id,user_id,hub_name,school_class_name,school_class_location,school_class_professor) "+"VALUES(?,?,?,?,?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_task_table);
				ps.setInt(1, 0);
				ps.setInt(2, User.getUserID());
				ps.setString(3, hubName);
				ps.setString(4, schoolClassName);
				ps.setString(5, schoolClassLocation);
				ps.setString(6, schoolClassProfessor);
		
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert into SchoolClass table!!!");
				}
				else {
					System.out.println("Failed insert into SchoolClass table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting into School Class table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting into School Class table");
			          	e.printStackTrace();
			    }
		}
		public static void addClass(SchoolClass thisClass) {
			//userID hubName schoolClassName schoolClassLocation schoolClassProfessor
			int userID = User.getUserID();
			String hubName = User.getCurrentHub().getHubName();
			String schoolClassName = thisClass.getClassName();
			String schoolClassLocation = thisClass.getLocation();
			String schoolClassProfessor = thisClass.getProfessor();
			User.executeInsertIntoSchoolClassTable(userID,hubName,schoolClassName,schoolClassLocation,schoolClassProfessor);	
		}

		public static void executeInsertIntoLifeHubTable(int userID, int eventType, String lifeHubName) {
			System.out.println("Inserting Life Hub Table: "+userID+" "+eventType+" "+lifeHubName);
			String insert_into_task_table = "INSERT INTO lifeHub.LifeHub (life_hub_id,user_id,event_type,life_hub_name) "+"VALUES(?,?,?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_task_table);
				ps.setInt(1, 0);
				ps.setInt(2, userID);
				ps.setInt(3, eventType);
				ps.setString(4, lifeHubName);
		
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert into LifeHub table!!!");
				}
				else {
					System.out.println("Failed insert into LifeHub table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting into LifeHub table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting into LifeHub table");
			          	e.printStackTrace();
			    }
		}

		public static void addHub(LifeHub hub) {
			int userID = User.getUserID();
			int eventType = hub.getEventType();
			String lifeHubName = hub.getHubName();
			User.executeInsertIntoLifeHubTable(userID,eventType,lifeHubName);	
		}

		public static void executeInsertIntoArchivedNoteTable(int userID, int eventType, String noteLifeHubName,
			String noteSchoolClassName, String noteImagePath, String noteText) {
			System.out.println("Inserting into Archived Note Table: "+userID+" "+eventType+" "+noteText);
			String insert_into_task_table = "INSERT INTO lifeHub.archivednotes (archived_notes_id,user_id,event_type,life_hub_name,school_class,img_path,text) "+"VALUES(?,?,?,?,?,?,?)";
			try {
			    Connection conn = DatabaseConnection.getConnection();	    
				PreparedStatement ps = conn.prepareStatement(insert_into_task_table);
				ps.setInt(1, 0);
				ps.setInt(2, userID);
				ps.setInt(3, eventType);
				ps.setString(4, noteLifeHubName);
				if (noteSchoolClassName==null)
					noteSchoolClassName="N/A";
				ps.setString(5, noteSchoolClassName);
				ps.setString(6, noteImagePath);
				ps.setString(7, noteText);
		
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert into Archived Note Table!!!");
				}
				else {
					System.out.println("Failed insert into Archived Note Table!");
				}
		
					//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}	
					
			    }catch(SQLSyntaxErrorException see) {
			       System.out.println("Error: DB syntax is incorrect while inserting into Archived Note Table");
			          	//see.printStackTrace();
			    }catch(Exception e) {
			       System.out.println("Error: DB connection failed inserting into Archived Note Table");
			          	e.printStackTrace();
			    }
			
		}

		//QC WITH RICHARD!!    /////HOLD******************************************************* 
		public static void addArchivedNote(Note note) {
			//image path to null
			//need event_type, life_hub_name, school_class, img_path, text
			int userID = User.getUserID();
			int eventType = User.getCurrentHub().getEventType();
			String noteImagePath = "";
			String noteText = note.getText();
			String noteLifeHubName = User.getCurrentHub().getHubName();
			String noteSchoolClassName;
			if (User.getCurrentHub().getEventType()==2)
				noteSchoolClassName = User.getCurrentHub().getHubName();
			else
				noteSchoolClassName = User.getCurrentClass();
			
			//String
			
			System.out.println(userID+" "+eventType+" "+noteLifeHubName+" "+noteSchoolClassName+" "+noteImagePath+" "+noteText);
			User.executeInsertIntoArchivedNoteTable(userID,eventType,noteLifeHubName,
					noteSchoolClassName,noteImagePath,noteText);
		}
		//QC WITH RICHARD!!/////HOLD*******************************************************
		public static void addHubEvent(HubEvent hubEvent) {	
			String lifeHubName = hubEvent.getHubName();
			int event_id = hubEvent.getEventID();
			int user_id = User.getUserID();
			int event_type = hubEvent.getEventType();
			String event_recurring = "FALSE";
			String event_hub = hubEvent.getHubName();//CODE CAMP 
			String event_name = hubEvent.getEventName();
			String event_location = hubEvent.getLocation();
			//start date of HubEvent
			Date startDate = hubEvent.getStartDate();
			Date endDate = hubEvent.getEndDate();
			
			int event_start_mm = startDate.getMonth();
			int event_start_dd = startDate.getDay();
			int event_start_yr = startDate.getYear();
			int event_start_hr = startDate.getHour();
			int event_start_min = startDate.getMinute();
			//end date of HubEvent
			int event_end_mm = endDate.getMonth();
			int event_end_yr = endDate.getYear();
			int event_end_hr = endDate.getHour();
			int event_end_min = endDate.getMinute();
			//User.executeInsertIntoHubEventTable();
		}
		
		public void executeInsertNewUserIntoUserTable() {
			//check username, for dupes after create button is selected
		
		//"INSERT INTO lifeHub.EmailAddress (email_id,user_id,email_contact,email_detail,email_type) "+"VALUES(?,?,?,?,?)";
		    String new_user_insert_query = "INSERT INTO lifeHub.User (user_id,username,password) "+"VALUES(?,?,?)";;
		
		    try {
		    	Connection conn = DatabaseConnection.getConnection();	
				PreparedStatement ps = conn.prepareStatement(new_user_insert_query);
				ps.setInt(1, 0);
				ps.setString(2,this.userLoggedInName);
				ps.setString(3,this.password);
		
				int result = ps.executeUpdate();					
				if(result > 0){
					System.out.println("Successful insert of new user into User table!!!");
				}
				else {
					System.out.println("Failed insert of new user into User table!");
				}
				
				try {
					conn.close();
				}catch (SQLException se){
					System.out.println("Error closing SQL connection.");
				}		        	
		    }catch(SQLSyntaxErrorException see) {
		       System.out.println("Error: DB syntax is incorrect.");
		          	//see.printStackTrace();
		    }catch(Exception e) {
		       System.out.println("Error: DB connection failed.");
		          	//e.printStackTrace();
		    }		
		}
		
		public boolean checkForNewUserInDatabase() {
			//check username, for dupes after create button is selected
			//return true if available
			//make db call here with this.user, not username
			boolean flag = true;
		    String check_for_new_user_duplicate_query = "SELECT * FROM lifeHub.User WHERE username = ?";
		
		    try {
		    	Connection conn = DatabaseConnection.getConnection();	
				PreparedStatement ps = conn.prepareStatement(check_for_new_user_duplicate_query);
				ps.setString(1,this.userLoggedInName);
		
				ResultSet rs = ps.executeQuery();
				
				//false means an empty result set
				if(rs.next() != false) {
					System.out.println("Username already exists while creating a new user: "+rs.getString("username"));
					flag = false;
					
										
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}		
		        }	        	
		    }catch(SQLSyntaxErrorException see) {
		       System.out.println("Error: DB syntax is incorrect.");
		          	//see.printStackTrace();
		    }catch(Exception e) {
		       System.out.println("Error: DB connection failed.");
		          	//e.printStackTrace();
		    }
			return flag;
		}

		public boolean addUser() {
			boolean wentThrough = false;
			//cfnuidb, if true, insert into User table
			if (checkForNewUserInDatabase()) 
			{
				//insert in db
				this.executeInsertNewUserIntoUserTable();
				wentThrough = true;
			}
			return wentThrough;		
		}
	
}
