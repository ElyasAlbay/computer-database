package com.excilys.cdb.persistence;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=PersistenceConfig.class)
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//					DataSetTestExecutionListener.class })
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
