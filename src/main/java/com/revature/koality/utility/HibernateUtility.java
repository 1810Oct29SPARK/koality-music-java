package com.revature.koality.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

	private static SessionFactory mainSessionFactory;
	private static SessionFactory testSessionFactory;

	public static SessionFactory getMainSessionFactory() {

		if (HibernateUtility.mainSessionFactory == null) {
			Properties props = new Properties();
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			try (InputStream in = cl.getResourceAsStream("hibernate.connection.main.properties")) {
				props.load(in);
				Configuration config = new Configuration();
				config.configure("hibernate.cfg.xml");
				config.addProperties(props);
				HibernateUtility.mainSessionFactory = config.buildSessionFactory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return HibernateUtility.mainSessionFactory;

	}

	public static SessionFactory getTestSessionFactory() {

		if (HibernateUtility.testSessionFactory == null) {
			Properties props = new Properties();
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			try (InputStream in = cl.getResourceAsStream("hibernate.connection.test.properties")) {
				props.load(in);
				Configuration config = new Configuration();
				config.configure("hibernate.cfg.xml");
				config.addProperties(props);
				HibernateUtility.testSessionFactory = config.buildSessionFactory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return HibernateUtility.testSessionFactory;

	}
	
	public static void destroySessionFactories() {
		
		if (HibernateUtility.mainSessionFactory != null && !HibernateUtility.mainSessionFactory.isClosed()) {
			HibernateUtility.mainSessionFactory.close();
		}
		
		if (HibernateUtility.testSessionFactory != null && !HibernateUtility.testSessionFactory.isClosed()) {
			HibernateUtility.testSessionFactory.close();
		}

		HibernateUtility.mainSessionFactory = null;
		HibernateUtility.testSessionFactory = null;
		
	}

}
