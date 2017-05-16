package com.excilys.cdb.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyDaoTest extends DBTestCase {
	CompanyDao companyDao;
	IDatabaseConnection dbUnitConnection;
	IDataSet dataset;
	
	@Before
	public void setUp() {
		companyDao = CompanyDaoImpl.INSTANCE;
		Connection connection;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-test?zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "admintest", "qwerty1234");
			try {
				dbUnitConnection = new DatabaseConnection(connection);
//				DatabaseConfig dbConfig = dbUnitConnection.getConfig();
//				 dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
			} catch (DatabaseUnitException e) {
				fail("dbUnit connection failed");
			}
		} catch (SQLException e) {
			fail("Unable to connect to database, driver not found");
		}
		
		try {
			dataset = getDataSet();
		} catch (Exception e) {
			fail("Unable to get xml file"+e.getMessage());
		}
		
		try {
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		} catch (DatabaseUnitException | SQLException e) {
			if(e instanceof DatabaseUnitException) {
				fail("database unit exception");
			} else {
				fail("sql exception");
			}
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
		
		return new FlatXmlDataSetBuilder().setColumnSensing(true).build(new File("src/test/resources/dataset.xml"));
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
