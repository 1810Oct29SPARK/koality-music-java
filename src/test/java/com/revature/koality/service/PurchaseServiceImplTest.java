package com.revature.koality.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.koality.dao.CustomerDAOImpl;

public class PurchaseServiceImplTest {

	@Mock
	CustomerDAOImpl customerDAOMock;

	@InjectMocks
	private PurchaseService ps;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPurchaseTrack() {

		int customerId = 1;

		int trackId = 21;

		customerDAOMock = mock(CustomerDAOImpl.class);

		ps = new PurchaseServiceImpl(customerDAOMock);

		when(customerDAOMock.purchaseTrack(customerId, trackId)).thenReturn(true);

		boolean actual = ps.purchaseTrack(customerId, trackId);

		assertTrue(actual);

	}

	@Test
	public void testInvalidPurchaseTrack() {

		int customerId = 1;

		int trackId = 21;

		customerDAOMock = mock(CustomerDAOImpl.class);

		ps = new PurchaseServiceImpl(customerDAOMock);

		when(customerDAOMock.purchaseTrack(customerId, trackId)).thenReturn(true);

		boolean actual = ps.purchaseTrack(customerId, 0);

		assertFalse(actual);

	}

	@Test
	public void testPurchaseAlbum() {

		int customerId = 1;

		int albumId = 21;

		customerDAOMock = mock(CustomerDAOImpl.class);

		ps = new PurchaseServiceImpl(customerDAOMock);

		when(customerDAOMock.purchaseAlbum(customerId, albumId)).thenReturn(true);

		boolean actual = ps.purchaseAlbum(customerId, albumId);

		assertTrue(actual);

	}

	@Test
	public void testInvalidPurchaseAlbum() {

		int customerId = 1;

		int albumId = 21;

		customerDAOMock = mock(CustomerDAOImpl.class);

		ps = new PurchaseServiceImpl(customerDAOMock);

		when(customerDAOMock.purchaseAlbum(customerId, albumId)).thenReturn(true);

		boolean actual = ps.purchaseAlbum(customerId, 0);

		assertFalse(actual);

	}

}
