package com.suhaib.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.suhaib.db.DataSorce;

/**
 * Application Lifecycle Listener implementation class StartUpListener
 *
 */
@WebListener
public class StartUpListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public StartUpListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// arg0.getServletContext().getResourceAsStream("web-inf/resourse/dbInfo.properties")
		// Properties properties = new Properties();
		// try {
		// properties.load(arg0.getServletContext().getResourceAsStream("/WEB-INF/resoursedbInfo.properties"));
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		DataSorce.createMongoClient("localhost", "27017");
	}

}
