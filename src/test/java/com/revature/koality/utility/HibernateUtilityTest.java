package com.revature.koality.utility;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class HibernateUtilityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		HibernateUtility.getMainSessionFactory();
		HibernateUtility.getTestSessionFactory();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testGetMainSessionFactory() {
		assertNotNull(HibernateUtility.getMainSessionFactory());
	}

	@Test
	public void testGetTestSessionFactory() {
		assertNotNull(HibernateUtility.getTestSessionFactory());
	}

}
