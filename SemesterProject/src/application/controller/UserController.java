package application.controller;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import application.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**This class acts as the controller for the UserHome view, which allows the user
 * to navigate to their LifeHubs and create new ones.
 * 
 * @author group 5 11-23-21
 *
 */

public class UserController {
	@FXML
	 private AnchorPane mainPane;
	
	@FXML
    private ChoiceBox<String> selection;

    @FXML
    private Label welcomeLabel;
	
	ArrayList<UserHubRecord> userHubRecords;
	ArrayList<HubEvent> events;
	ArrayList<Contact> contacts;
	
	/**This method accepts an ArrayList of UserHubRecords, performs a database query
	 * for all LifeHubs belonging to the User, creates a new UserHubRecord for each,
	 * adds it to the ArrayList and returns the updated ArrayList.
	 * 
	 * @param userHubRecords- the initial ArrayList of UserHubRecords
	 * @return- the updated ArrayList of UserHubRecords
	 */
	public ArrayList<UserHubRecord> getUserLifeHubs(ArrayList<UserHubRecord> userHubRecords) {
	
	String user_lifehub_query = "SELECT * FROM lifehub.lifehub WHERE user_id = ?";
		    	    
		try {
		    Connection conn = DatabaseConnection.getConnection();
		    
			PreparedStatement ps = conn.prepareStatement(user_lifehub_query);
			ps.setInt(1, User.getUserID());
			ResultSet rs = ps.executeQuery();	
						
				//false means an empty result set
				if(rs.next() != false) {

					do{
						userHubRecords.add(new UserHubRecord(rs.getString("life_hub_name"),rs.getInt("event_type")));
					}while(rs.next());
					
					//close connection
					//closeConnection(conn);
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
		       System.out.println("Error: DB connection failed in controller.");
		          	e.printStackTrace();
		    }
		return userHubRecords;
	}
	
	/**This method accepts a String of the Contact's name, performs a database query for all PhoneNumbers 
	 * which belong the this User and Contact name, adds them to an ArrayList of 
	 * PhoneNumbers, and returns this ArrayList.
	 * 
	 * @param contact- the name of the contact to be searched for.
	 * @return- the ArrayList of PhoneNumbers 
	 */
	public ArrayList<PhoneNumber> getUserContactNumbers(String contact){//ArrayList<PhoneNumber> userNumbers) {
	ArrayList<PhoneNumber> phoneArrayListOfContact = new ArrayList<PhoneNumber>();
	PhoneNumber phoneObject;
	
	String user_phone_contacts_query = "SELECT * FROM lifehub.phonenumber WHERE user_id = ? and contact_name = ?";//and CONTACT_NAME == contact
    	    
	try {
	    Connection conn = DatabaseConnection.getConnection();
	    
		PreparedStatement ps = conn.prepareStatement(user_phone_contacts_query);
		ps.setInt(1, User.getUserID());
		ps.setString(2, contact);
		ResultSet rs = ps.executeQuery();	
					
			//false means an empty result set
			if(rs.next() != false) {
				System.out.println("hit phone");

				do{
					//add resultset to arrayList, return ArrayList
					System.out.println("contacts for user: "+rs.getInt("user_id")+" "+rs.getString("contact_name")+
							" "+rs.getString("contact_phone"));
					
					String number = rs.getString("contact_phone");
					String type = rs.getString("contact_phone_type");
					phoneObject = new PhoneNumber(number, type);
					phoneArrayListOfContact.add(phoneObject);
								
				}while(rs.next());				

				//close connection
				//closeConnection(conn);
				try {
					conn.close();
				}catch (SQLException se){
					System.out.println("Error closing SQL connection.");
				}				
	        }	        	
	    }catch(SQLSyntaxErrorException see) {
	       System.out.println("Error: DB syntax is incorrect while getting user's phone contacts.");
	          	//see.printStackTrace();
	    }catch(Exception e) {
	       System.out.println("Error: DB connection failed in controller while getting user's phone contacts.");
	          	e.printStackTrace();
	    }
	return phoneArrayListOfContact;
}


	/**This method accepts a String of the Contact's name, performs a database query for all email
	 * addresses which belong the this User and Contact name, adds them to an ArrayList of 
	 * EmailAddresses, and returns this ArrayList.
	 * 
	 * @param contact- the name of the contact to be searched for.
	 * @return- the ArrayList of EmailAddresses 
	 */
	public ArrayList<EmailAddress> getUserEmailAddresses(String contact){//ArrayList<PhoneNumber> userNumbers) {
	ArrayList<EmailAddress> emailAddressArrayList = new ArrayList<EmailAddress>();
	EmailAddress emailAddress;
	
	String user_email_contacts_query = "SELECT * FROM lifehub.emailaddress WHERE user_id = ? and email_contact = ?";//and CONTACT_NAME == contact
    	    
	try {
	    Connection conn = DatabaseConnection.getConnection();
	    
		PreparedStatement ps = conn.prepareStatement(user_email_contacts_query);
		ps.setInt(1, User.getUserID());
		ps.setString(2, contact);
		ResultSet rs = ps.executeQuery();	
					
			//false means an empty result set
			if(rs.next() != false) {
				System.out.println("hit email");

				do{
					//add resultset to arrayList, return ArrayList
					System.out.println("email contacts for user: "+rs.getInt("user_id")+" "+rs.getString("email_contact")+
							" "+rs.getString("email_detail"));
					
					String email = rs.getString("email_detail");
					String type = rs.getString("email_type");
					emailAddress = new EmailAddress(email, type);
					emailAddressArrayList.add(emailAddress);					
					
				}while(rs.next());				

				//close connection
				//closeConnection(conn);
				try {
					conn.close();
				}catch (SQLException se){
					System.out.println("Error closing SQL connection.");
				}				
	        }	        	
	    }catch(SQLSyntaxErrorException see) {
	       System.out.println("Error: DB syntax is incorrect while getting user's email contacts.");
	          	//see.printStackTrace();
	    }catch(Exception e) {
	       System.out.println("Error: DB connection failed in controller while getting user's email contacts.");
	          	e.printStackTrace();
	    }
	return emailAddressArrayList;
}

	/**This method accepts an ArrayList of HubEvents, performs a database query to
	 * pull in all user HubEvents, adds them to the ArrayList, and returns the ArrayList.
	 *
	 * @param userHubEvents- the initial ArrayList of HubEvents
	 * @return- the updated ArrayList of HubEvents
	 */
	public ArrayList<HubEvent> getUserHubEvents(ArrayList<HubEvent> userHubEvents) {
	ArrayList <Contact> attendees = new ArrayList<Contact>();
	String user_hub_events_query = "SELECT * FROM lifehub.hubevent2 WHERE user_id = ?";
	    	    
	try {
	    Connection conn = DatabaseConnection.getConnection();
	    
		PreparedStatement ps = conn.prepareStatement(user_hub_events_query);
		ps.setInt(1, User.getUserID());
		ResultSet rs = ps.executeQuery();	
					
			//false means an empty result set
			if(rs.next() != false) {
					
				do{
					
					int event_id = rs.getInt("event_id");
					int user_id = rs.getInt("user_id");
					int event_type = rs.getInt("event_type");
					String event_recurring = rs.getString("event_recurring");
					String event_hub = rs.getString("event_hub");
					String event_name = rs.getString("event_name");
					String event_location = rs.getString("event_location");
					//start date of HubEvent
					int event_start_mm = Integer.parseInt(rs.getString("event_start_mm"));
					int event_start_dd = Integer.parseInt(rs.getString("event_start_dd"));
					int event_start_yr = Integer.parseInt(rs.getString("event_start_yr"));
					int event_start_hr = Integer.parseInt(rs.getString("event_start_hr"));
					int event_start_min = Integer.parseInt(rs.getString("event_start_min"));
					//end date of HubEvent
					int event_end_mm = Integer.parseInt(rs.getString("event_end_mm"));
					int event_end_dd = Integer.parseInt(rs.getString("event_end_dd"));
					int event_end_yr = Integer.parseInt(rs.getString("event_end_yr"));
					int event_end_hr = Integer.parseInt(rs.getString("event_end_hr"));
					int event_end_min = Integer.parseInt(rs.getString("event_end_min"));
					System.out.println("Test printing the logged-in users events: ");
					System.out.println(event_id+" "+user_id+" "+event_type+" "+event_recurring+" "+event_hub+" "+event_name
							+" "+event_location+" "+event_start_mm+" "+event_start_dd+" "+event_start_yr+" "+event_start_hr
							+" "+event_start_min+" "+event_end_mm+" "+event_end_dd+" "+event_end_yr+" "+event_end_hr+
							" "+event_end_min);
					Date start_date = new Date(event_start_yr, event_start_mm, event_start_dd);
					Date end_date = new Date(event_end_yr, event_end_mm, event_end_dd);
					HubEvent hubEventObject = new HubEvent(event_id, user_id, event_type, start_date,event_hub, 
							event_location,attendees,event_name,end_date);
					userHubEvents.add(hubEventObject);
				}while(rs.next());
				
				//closeConnection(conn);
				try {
					conn.close();
				}catch (SQLException se){
					System.out.println("Error closing SQL connection.");
				}				
	        }	        	
	    }catch(SQLSyntaxErrorException see) {
	       System.out.println("Error: DB syntax is incorrect while getting user's contacts.");
	          	//see.printStackTrace();
	    }catch(Exception e) {
	       System.out.println("Error: DB connection failed in controller while getting user's contacts.");
	          	e.printStackTrace();
	    }
	return userHubEvents;
}

	/**This method accepts an ArrayList of Contacts, performs a database query to
	 * pull in all user Contacts, adds them to the ArrayList, and returns the ArrayList.
	 * 
	 * @param contact- the initial ArrayList of Contacts
	 * @return- the updated ArrayList of Contacts
	 */
	public ArrayList<Contact> getUserContacts(ArrayList<Contact> contacts) {
	
		String user_contacts_query = "SELECT * FROM lifehub.contact WHERE user_id = ?";
		    	    
		try {
		    Connection conn = DatabaseConnection.getConnection();
		    
			PreparedStatement ps = conn.prepareStatement(user_contacts_query);
			ps.setInt(1, User.getUserID());
			ResultSet rs = ps.executeQuery();	
						
				//false means an empty result set
				if(rs.next() != false) {
						//gets only user contact names
					do{
						System.out.println("contacts for user: "+rs.getInt("user_id")+" "+rs.getString("contact_name"));
						//userHubRecords.add(new UserHubRecord(rs.getString("life_hub_name"),rs.getInt("event_type")));
						
						 //create a contact for the user with an AL for emails and an AL for phoneNumbers
						Contact newContact = new Contact(rs.getString("contact_name"),new ArrayList<PhoneNumber>(),new ArrayList<EmailAddress>());
						contacts.add(newContact);
						
						User.setUserContacts(contacts);
						
						
					}while(rs.next());
					
					//
					//get all phone numbers for logged-in user
					ArrayList<PhoneNumber> contactNumbers = new ArrayList<PhoneNumber>();
					ArrayList<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();
					
					
					for (int i = 0; i < User.getUserContacts().size(); i++)
					{
						//returns a arrayList of PhoneNumbers
						contactNumbers = getUserContactNumbers(User.getUserContacts().get(i).getName());
						emailAddresses = getUserEmailAddresses(User.getUserContacts().get(i).getName());
						User.getUserContacts().get(i).setPhoneList(contactNumbers);
						User.getUserContacts().get(i).setEmailsList(emailAddresses);
					}
					
					//System.out.println("Test printing size of contactNumbers: "+contactNumbers.size());
					//close connection
					//closeConnection(conn);
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
					}				
		        }	        	
		    }catch(SQLSyntaxErrorException see) {
		       System.out.println("Error: DB syntax is incorrect while getting user's contacts.");
		          	//see.printStackTrace();
		    }catch(Exception e) {
		       System.out.println("Error: DB connection failed in controller while getting user's contacts.");
		          	e.printStackTrace();
		    }
		return contacts;
	}

	/**This method performs a database query to pull all Tasks for all hubs and classes
	 * for the current user and returns nothing. 
	 *
	 * @param tasks- The ArrayList of Tasks to be added to for a specific Hub or SchoolClass (deprecated)
	 */
	public void getUserTasksSchool(ArrayList<Task> tasks) {
	
	String user_tasks_query = "SELECT * FROM lifehub.task WHERE user_id = ? AND event_hub = ? AND event_name = ?";    	    
	try {
	    Connection conn = DatabaseConnection.getConnection();    
	
		if(User.getUserHubs()!=null)	
		{
			//loop thru logged-in user's hubs
			for (LifeHub hub: User.getUserHubs())
			{	
				/////Move type logic here
				if (hub.getEventType()!=2) {
				if(hub.getClasses()!=null) 
				{
					
				for (SchoolClass sClass: hub.getClasses()) {
					//Address duplicate class names
					System.out.println("School or personal category-1 or 3 EventHub of user");
					PreparedStatement ps = conn.prepareStatement(user_tasks_query);
					ps.setInt(1, User.getUserID());
					ps.setString(2, hub.getHubName());
					ps.setString(3, sClass.getClassName());
					ResultSet rs = ps.executeQuery();
					//false means an empty result set
					if(rs.next() != false) {
						//gets only user contact names
						do{
							sClass.getAssignments().add(new Task(rs.getString("text")));
							
						}while(rs.next());
			        }
				}
				}
				}
				else{//loop through hubs

						System.out.println("Business category-2 EventHub of user");
						PreparedStatement ps = conn.prepareStatement(user_tasks_query);
						ps.setInt(1, User.getUserID());
						ps.setString(2, hub.getHubName());
						ps.setString(3, hub.getHubName());
						
						ResultSet rs = ps.executeQuery();
						//false means an empty result set
						if(rs.next() != false) {
							//gets only user contact names
							do{
								hub.getTasks().add(new Task(rs.getString("text")));
								
							}while(rs.next());
				        }
					}		
				
		}
			
	
			try {
				conn.close();
			}catch (SQLException se){
				System.out.println("Error closing SQL connection.");
			}	
		}	

	    }catch(SQLSyntaxErrorException see) {
	       System.out.println("Error: DB syntax is incorrect while getting user's tasks.");
	          	//see.printStackTrace();
	    }catch(Exception e) {
	       System.out.println("Error: DB connection failed in controller while getting user's tasks.");
	          	e.printStackTrace();
	    }
	}

	/**This method performs a database query to pull all SchoolClasses for all LifeHubs for 
	 * the current user and returns nothing. 
	 * 
	 */
	public void setUserSchools()
	{
		String user_school_classes_query = "SELECT * FROM lifehub.schoolclass WHERE user_id = ?";  
			    
		try {
			Connection conn = DatabaseConnection.getConnection();
	    	
			if(User.getUserHubs()!=null)	
			{

				System.out.println("reached schools");
				//loop thru logged-in user's hubs
				for (LifeHub hub: User.getUserHubs())
				{	
					//Address duplicate class names
					PreparedStatement ps = conn.prepareStatement(user_school_classes_query);
					ps.setInt(1, User.getUserID());
					ResultSet rs = ps.executeQuery();
					//false means an empty result set
					if(rs.next() != false) {
							//gets only user contact names
						do {
							String school_hub_name = rs.getString("hub_name");
							if(hub.getHubName().equals(school_hub_name)) {
									hub.getClasses().add(new SchoolClass(rs.getString("school_class_name"), rs.getString("school_class_professor"), 
									rs.getString("school_class_location"), new ArrayList<Task>(), new HubEvent(), new ArrayList<Note>()));
								}
							}while(rs.next());	
						}
				     }
				
				
				//close connection
					try {
						conn.close();
					}catch (SQLException se){
						System.out.println("Error closing SQL connection.");
				}	
			}	

	    }catch(SQLSyntaxErrorException see) {
	       System.out.println("Error: DB syntax is incorrect while getting user's classes");
	          	//see.printStackTrace();
	    }catch(Exception e) {
	       System.out.println("Error: DB connection failed in controller while getting user's classes.");
	          	e.printStackTrace();
	    }
	}
	
	/**This method performs a database query to pull all notes for all hubs and classes
	 * for the current user and returns nothing. 
	 * 
	 */
	public void setUserNotes()
	{
		String user_tasks_query = "SELECT * FROM lifehub.archivednotes WHERE user_id = ? AND life_hub_name = ? AND school_class = ?";    	    
		try {
		    Connection conn = DatabaseConnection.getConnection();    
		
			if(User.getUserHubs()!=null)	
			{
				//loop thru logged-in user's hubs
				for (LifeHub hub: User.getUserHubs())
				{	
					System.out.println("Hub name is: "+ hub.getHubName());
					/////Move type logic here
					if (hub.getEventType()==1) 
					{
						if(hub.getClasses()!=null) 
						{
							for (SchoolClass sClass: hub.getClasses()) {
								System.out.println("Class name is: "+ sClass.getClassName());
								//Address duplicate class names
								PreparedStatement ps = conn.prepareStatement(user_tasks_query);
								ps.setInt(1, User.getUserID());
								ps.setString(2, hub.getHubName());
								ps.setString(3, sClass.getClassName());
								ResultSet rs = ps.executeQuery();
								//false means an empty result set
								if(rs.next() != false) {
									//gets only user contact names
									do
									{
										System.out.println("note is: "+ rs.getString("text"));
										sClass.getNotes().add(new Note(rs.getString("text")));
										
									}while(rs.next());
						        }
							}
						}
					}
					else{
							if(hub.getClasses()!=null) 
							{
								System.out.println("From Else -------------Class name is: "+ hub.getHubName());
									//Address duplicate class names
								PreparedStatement ps = conn.prepareStatement(user_tasks_query);
								ps.setInt(1, User.getUserID());
								ps.setString(2, hub.getHubName());
								ps.setString(3, hub.getHubName());
								ResultSet rs = ps.executeQuery();
								//false means an empty result set
								if(rs.next() != false) {
									//gets only user contact names
									do
									{
										System.out.println("note is: "+ rs.getString("text"));
										hub.getNotes().add(new Note(rs.getString("text")));
										
									}while(rs.next());
						        }
							}
							if(hub.getClasses().size()==0)
								System.out.println("This hub has no classes");	
							
						}		
					
			}
				try {
					conn.close();
				}catch (SQLException se){
					System.out.println("Error closing SQL connection.");
				}	
			}	

		    }catch(SQLSyntaxErrorException see) {
		       System.out.println("Error: DB syntax is incorrect while getting user's tasks.");
		          	//see.printStackTrace();
		    }catch(Exception e) {
		       System.out.println("Error: DB connection failed in controller while getting user's tasks.");
		          	e.printStackTrace();
		    }
		
	}
	
	/**This method calls methods to read all LifeHubs and their associated
	 * SchoolClasses, Tasks, HubEvents, Notes and Contacts, creates ArrayLists
	 * of each to be used for all subsequent references to these classes, and returns
	 * nothing. 
	 * 
	 * Note**To reduce database calls and increase performance, the choice was made to
	 * pull everything from the database for a specific user after they logged in. 
	 * 
	 */
	@FXML
		void initialize()
	{
		//start fresh new hub

		User.setLastHub(null);
    	User.setCurrentHub(null);
    	User.setCurrentClass(null);
    	
    	welcomeLabel.setText("Welcome "+User.getUserName());
    	
    	// step 1!! make call to the UserHubRecord model - target lifehub table
    	userHubRecords = getUserLifeHubs(new ArrayList<UserHubRecord>());
		
		//How will data come back from query? Each record read should be added to userHubNames
		
    	////Get all classes for each user and Hub

    	
    	//GIVE RICHARD EVERYTHING FROM EVENTS FOR THIS USER
    	events = new ArrayList<HubEvent>();
		
		events = getUserHubEvents(events);
		
		//Add ALL events
		User.setUserEvents(events);

		
		//DBQuery for all HubEvents and....Contacts - use PhoneNumber table
		//GIVE RICHARD EVERYTHING FROM PHONE NUMBER FOR THIS USER - phone number table and email tables, 2 array lists
		contacts = new ArrayList<Contact>();
		contacts = getUserContacts(contacts);	
		//Add ALL contacts
		User.setUserContacts(contacts);
		 //getUserEmailAddresses
		
	
		
		//RICHARD NEEDS ALL OF THE USER HUBS AND TYPES IN AN ARRAYLIST OF UserHubRecords. List is called UserHubRecords
		//Dynamically Added Hubs from LifeHub TABLE - for step 1)
		if(userHubRecords!=null)
		{
			User.setUserHubs(new ArrayList<LifeHub>());
			for (int i = 0; i < userHubRecords.size(); i++ )
			{
				User.getUserHubs().add(new LifeHub(userHubRecords.get(i).getHubName()));
				User.getUserHubs().get(i).setEventType(userHubRecords.get(i).getHubType());
			}		
		}
		
		//step 2!! - set schools, adds all classes to each hub
    	setUserSchools();
    	setUserNotes();
		/*CREATE ALL USER HUBS. THIS CREATES A COMPLETE LISTING TO PULL FROM LATER.
		 * Pull from Tasks, look at all for user and HubName. Add all, for each class, and hub, in a ArrayList associated with that hub and class
		 * Need Classes all records from SchoolClass*/
		//This is added so we can test the hub creating buttons. Should be reading from DB
		
		//call classFromDB method here
		
				//*********STEP 2*********/
		
		//1	FALL 2021	App Programming test		Bring notes - open book test
		//3	HOME	    Brother's wedding		    Get tuxedo from cleaners
		//2	CODE CAMP			                    bring coffee
		ArrayList<Task> arrayListOfTasks = new ArrayList<Task>();
		
		//now void - see 2) - setSchools
		getUserTasksSchool(arrayListOfTasks);
		//set user's business tasks
		//for the user, Create each unique hubs - each name (life, tasks, events , and notes during creating
				//tasks for type business only 
				//if 1 or 3 use this line ArrayList<Task> temp = new ArrayList<Task> and pass to constructor
		/*get DB tasks for current user
		 * for (thisLifeHub: UserLifeHubs)
		 * {		
		 * 
		 * 
		 * 
		 * if type 2
		 * 	get event hub and task
		 * 	add each of these tasks to the array list for that hub
		 * 	thisLifeHub.getTasks.add(new Task(text);
		 * 
		 * 
		 * 
		 * 
		 * else
		 * 	get event hub, class and task
		 * 	add each task to the array list for that class, and that class in in the hub arraylist of classes
		 * for (class in thisLifeHub)
		 * 	thisLifeHub.getclass.getTasks.add(new Task(text));
		 * }*/
		if (User.getUserHubs()==null)
			User.setUserHubs(new ArrayList<LifeHub>());
		
		//make a list of the names
		ArrayList<String> hubNames = new ArrayList<String>();
		for (UserHubRecord record:userHubRecords)
		{
			hubNames.add(record.getHubName());
		}
		
		//This will print all of the hubs
		selection.getItems().addAll(hubNames);
		
	}
	
	/**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
	@FXML
    void addHub(ActionEvent event) {
    	System.out.println("testHub");
    }
	
	/**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
	@FXML
    void changeTheme(ActionEvent event) {
    	System.out.println("testChangeTheme");
    }
	
	/**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
	 @FXML
	    void tutorial(ActionEvent event) {
		 System.out.println("testTutorial");
	    }
	 
	 /**Top menu item. Unused at this time.
	  * 
	  * @param event- the triggering event
	  */
	 @FXML
	 void about(ActionEvent event) {
		 System.out.println("testAbout");
	 }
	
	 /**Top menu item. Unused at this time.
	  * 
	  * @param event- the triggering event
	  */
    @FXML
    void logout(ActionEvent event) {
    	System.out.println("testLogout");
    }
	   
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */    
    @FXML
    void close(ActionEvent event) {
    	System.out.println("testClose");
    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void deleteHub(ActionEvent event) {
    	System.out.println("testDelete");
    }

    /**This method accepts an ActionEvent, navigates to the selected LifeHub
     * from the name supplied by the user, navigates to the correct Hub view,
     * and returns nothing.
     * 
     * @param event- the ActionEvent which triggered this method
     */
    @FXML
    void goTo(ActionEvent event) {
    	String userChoice = selection.getSelectionModel().getSelectedItem().toString();
    	//Make sure this makes sense. 
    	//Makes new hub with name only.
    	
    	
    	//Logic below will determine the FXML doc to call
    	//Initialize hubType to sentinel value
    	int hubType = -1;
		int count = User.getUserHubs().size();
		Boolean find = true;
		
		//FIND THE RIGHT HUB AND SET THE HUB
		
		while (find)
		{
			if (count > 0)
			{
				//descending search for hubEntry 
				hubType=User.getUserHubs().get(--count).compareTo(userChoice);
				//if valid hubType is returned, search is complete
				if (hubType > 0)
					find = false;
			}
			//Ends search when all values have been checked
			else
				find = false;

		}
		User.setCurrentHub(User.getUserHubs().get(count));
		
		String hubView = "";
		
		ArrayList<Task> tasks = new ArrayList<Task>();
		ArrayList<Note> notes = new ArrayList<Note>();
		if(User.getCurrentHub().getClasses()!=null)
		User.setClasses(User.getCurrentHub().getClasses());
		//this switch will get select the appropriate FXML doc (1 school, 3 personal, or 2 business)
		switch (hubType)
		{
			case 1:
				hubView="EducationHome";
				break;
			case 2:
				hubView="BusinessHome";
				break;
			case 3:
				hubView="PersonalHome";
				break;
			default:
				break;
		}		
		//Create current Hub with data from on DB query
		//User.setCurrentHub(new LifeHub(userChoice,hubType, events, tasks,notes));
	
    	try
    	{
    		
    		URL url = new File("src/application/view/"+hubView+".fxml").toURI().toURL();
    		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());
    		//this will instantiate the LifeHub object based on the DB query
        	//pull all tasks, events and notes associated with the User.getCurrentHub() String
    		
        	mainPane = FXMLLoader.load(url);
        	Scene scene = new Scene(mainPane);// pane you are GOING TO show
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
    	}
    	catch (IOException e)
    	{
    		//System.out.println("Error at 725");
    	}
    }

    /**This method accepts an ActionEvent, displays a TextInputDialog, creates a new Business LifeHub
     * from the name supplied by the user, adds it to the ArrayList of the user's LifeHubs and 
     * the database, navigates to the new hub view, and returns nothing.
     * 
     * @param event- the ActionEvent which triggered this method
     */
    @FXML
    void newBusiness(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
    	String hubName = "";
    	boolean validInput;
    	
    	do
    	{
            textDialog.getEditor().clear();
            textDialog.setTitle("New Business Hub");
            textDialog.setHeaderText("Please enter the new hub name");
            textDialog.setContentText("Hub name:");
            textDialog.showAndWait();
            hubName = textDialog.getResult();
            validInput = validateInput(hubName);
    	}while(!validInput);
            //make new hub
           //pull next index from database
            //index will be used to populate all events, lists, etc on the page
            //any calendar visible on the hub page will only be from this hub
            //when switching to calendar view, ALL of the users events be added to the calendar
    		
    		
    		//make new hub
            LifeHub currentHub = new LifeHub(hubName,2, new ArrayList<HubEvent>(), new ArrayList<Task>(), new ArrayList<Note>());
            
            //Add to DB
            User.addHub(currentHub);
            //Add to UserHubs
            User.getUserHubs().add(currentHub);
            
        		User.setClasses(new ArrayList<SchoolClass>());
            User.setCurrentHub(currentHub);
            try
        	{
        		URL url = new File("src/application/view/BusinessHome.fxml").toURI().toURL();
        		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());        		
        		mainPane = FXMLLoader.load(url);
            	//use FXMLloader to pass all user data to next controller
            	Scene scene = new Scene(mainPane);// pane you are GOING TO show
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
                window.setScene(scene);
                window.show();
        	}
        	catch (IOException e)
        	{
        		//popup error window
        	}   
    }

    /**This method accepts an ActionEvent, displays a TextInputDialog, creates a new Education LifeHub
     * from the name supplied by the user, adds it to the ArrayList of the user's LifeHubs and 
     * the database, navigates to the new hub view, and returns nothing.
     * 
     * @param event- the ActionEvent which triggered this method
     */
    @FXML
    void newEducation(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
   	 String hubName = "";
   	 boolean validInput;
       //{
		   //pull next index from database
	       //index will be used to populate all events, lists, etc on the page
           //any calendar visible on the hub page will only be from this hub
	       //when switching to calendar view, ALL of the users events be added to the calendar
		   do
		   {
	           textDialog.getEditor().clear();
	           textDialog.setTitle("New Education Hub");
	           textDialog.setHeaderText("Please enter the new hub name");
	           textDialog.setContentText("Hub name:");
	           textDialog.showAndWait();
	           hubName = textDialog.getResult();
	           validInput = validateInput(hubName);
	    	}while(!validInput);
         //make new hub
           LifeHub currentHub = new LifeHub(hubName,1, new ArrayList<HubEvent>(), new ArrayList<Task>(), new ArrayList<Note>());
           
           //Add to DB
           User.addHub(currentHub);
           //Add to UserHubs
           User.getUserHubs().add(currentHub);
           
       		User.setClasses(new ArrayList<SchoolClass>());
           User.setCurrentHub(currentHub);
           try
       	{
       		URL url = new File("src/application/view/EducationHome.fxml").toURI().toURL();
       		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());
       		//User.setCurrentHub(hubName);
       		//use FXMLloader to pass all user data to next controller
           	mainPane = FXMLLoader.load(url);
           	   Scene scene = new Scene(mainPane);// pane you are GOING TO show
               Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
               window.setScene(scene);
               window.show();
       	}
       	catch (IOException e)
       	{
       		//popup error window
       	} 
     }

    /**This method accepts an ActionEvent, displays a TextInputDialog, creates a new Personal LifeHub
     * from the name supplied by the user, adds it to the ArrayList of the user's LifeHubs and 
     * the database, navigates to the new hub view, and returns nothing.
     * 
     * @param event- the ActionEvent which triggered this method
     */
    @FXML
    void newPersonal(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
   	 String hubName = "";
   	 boolean validInput;
       //{
   	 		//pull next index from database
   	 		//index will be used to populate all events, lists, etc on the page
   	 		//any calendar visible on the hub page will only be from this hub
   	 		//when switching to calendar view, ALL of the users events be added to the calendar
   	 		do
   	 		{
   	 			textDialog.getEditor().clear();
 				textDialog.setTitle("New Personal Hub");
	           textDialog.setHeaderText("Please enter the new hub name");
	           textDialog.setContentText("Hub name:");
	           textDialog.showAndWait();
	           hubName = textDialog.getResult();
	           validInput = validateInput(hubName);
   	    	}while(!validInput);
         //make new hub
           LifeHub currentHub = new LifeHub(hubName,3, new ArrayList<HubEvent>(), new ArrayList<Task>(), new ArrayList<Note>());
           User.addHub(currentHub);
           //Add to DB
           
           //Add to UserHubs
           User.getUserHubs().add(currentHub);
           
       		User.setClasses(new ArrayList<SchoolClass>());
           User.setCurrentHub(currentHub);

           try
       	{
       		URL url = new File("src/application/view/PersonalHome.fxml").toURI().toURL();
       		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());
       		//Make a New Hub, add it to the users arrayList, send it to DB
       		
	       	//use FXMLloader to pass all user data to next controller
           	mainPane = FXMLLoader.load(url);
           	Scene scene = new Scene(mainPane);// pane you are GOING TO show
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
       	}
       	catch (IOException e)
       	{
       		//popup error window
       	} 
           
    }
    
    /**This method accepts an ActionEvent, changes the scene the Login view, and returns
     * nothing.
     * 
     * @param event- the ActionEvent which triggers this method
     */
    @FXML
    void logOutButton(ActionEvent event) {
    	try
       	{
    		URL url = new File("src/application/view/Login.fxml").toURI().toURL();
       		User.setLastHub(new File("src/application/view/Login.fxml").toURI().toURL());
           	mainPane = FXMLLoader.load(url);
           	   Scene scene = new Scene(mainPane);
               Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
               window.setScene(scene);
               window.show();
       	}
       	catch (IOException e)
       	{
       		//popup error window
       	} 
    }
    
    /**This method accepts a String, returns true if the String is not empty
     * and not null, otherwise returns false.
     * 
     * @param input- The String being validated.
     * @return- the Boolean value determined by the String being null or empty.
     */
    public boolean validateInput(String input)
    {
    	if(input != null)
    	{
    		if (!input.equals(""))
    			return true;
    	}
    	return false;
    }

    

}
