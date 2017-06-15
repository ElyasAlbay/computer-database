package com.excilys.cdb.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.cdb.config.PersistenceConfig;
import com.excilys.ebi.spring.dbunit.test.DataSetTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
					DataSetTestExecutionListener.class })
//@DataSet(value = "/dataset.xml")
public class CompanyDaoTest {
	@Autowired
	CompanyDao companyDao;

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetAll() {
//		Page<Company> companyPage = new Page<>();
//		companyDao.getAll(companyPage);
//		assertNotNull(companyPage.getElementList());
	}
}
