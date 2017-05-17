package com.excilys.cdb.persistence;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CompanyDaoTest extends DBTestCase {
	CompanyDao companyDao;
	IDatabaseConnection dbUnitConnection;
	IDatabaseTester dbTester;
	IDataSet dataset;
	
	@Before
	public void setUp() {
		companyDao = CompanyDaoImpl.INSTANCE;
		
		String url = "jdbc:mysql://localhost:3306/computer-database-test?serverTimezone=Europe/Paris";
		String user = "admintest";
		String pwd = "qwerty1234";
				
		try {
			dataset = getDataSet();
			System.out.println(dataset.getTable("company"));
		} catch (Exception e) {
			fail("Unable to get xml file"+e.getMessage());
		}
		
		
		try {
			dbTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver", url, user, pwd);
			
			dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
			dbTester.setDataSet(dataset);
			dbTester.onSetup();
		} catch (Exception e) {
			fail("Connection issue: " + e);
			e.printStackTrace();
		}

	}
	
	@After
	public void tearDown() {
		try {
			dbUnitConnection.close();
		} catch (SQLException e) {
			fail("dbUnit close connection failed");
		}
		
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		
		return new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset.xml"));
	}
	
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

	@Override
    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.NONE;
    }

}
