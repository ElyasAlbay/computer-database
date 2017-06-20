package com.excilys.cdb.persistence;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.utility.mapper.ComputerMapper;

/**
 * Implementation of ComputerDao, sends requests to the database and gets an
 * instance of Computer from the corresponding Mapper.
 * 
 * @author Elyas Albay
 *
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {
	private static final Logger LOG = LoggerFactory.getLogger(ComputerDaoImpl.class);	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final static String LIST = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id ORDER BY ISNULL(%s), %s LIMIT ?, ?";
	private final static String GET_BY_ID = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.id=?";
	private final static String SEARCH_BY_NAME = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(%s), %s LIMIT ?, ?";
	private final static String CREATE = "INSERT INTO computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:companyId";
	private final static String UPDATE = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:companyId WHERE id=:id";
	private final static String DELETE = "DELETE FROM computer WHERE id=?";
	private final static String NUMBER_OF_ELEMENTS = "SELECT count(*) FROM computer";
	private final static String NB_SEARCH_ELEMENTS = "SELECT count(*) FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
	private final static String DELETE_COMPUTERS_BY_COMPANYID = "DELETE FROM computer WHERE company_id=?";

	@Override
	public Page<Computer> getAll(Page<Computer> computerPage) {
		LOG.info("getAll request.");

		 String order = computerPage.getOrder().equals("name") ?
		 "computer.name" : computerPage.getOrder();
		 String query = String.format(LIST, order, order);
		 int pageOffset = (computerPage.getPageNumber() - 1) *
		 computerPage.getPageSize();
		 List<Computer> computerList = this.jdbcTemplate.query(query, new
		 ComputerMapper(), pageOffset,
		 computerPage.getPageSize());

		computerPage.setElementList(computerList);
		getNumberOfElements(computerPage);

		return computerPage;
	}

	@Override
	public Computer getById(long id) {
		LOG.info("getById request.");

		Computer computer = null;
		List<Computer> computerList = this.jdbcTemplate.query(GET_BY_ID, new ComputerMapper(), id);

		if (!computerList.isEmpty()) {
			computer = computerList.get(0);
		}

		return computer;
	}

	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {
		LOG.info("searchByName request.");

		String order = computerPage.getOrder().equals("name") ? "computer.name" : computerPage.getOrder();
		String query = String.format(SEARCH_BY_NAME, order, order);
		int pageOffset = (computerPage.getPageNumber() - 1) * computerPage.getPageSize();
		List<Computer> computerList = this.jdbcTemplate.query(query, new ComputerMapper(), name + '%', name + '%',
				pageOffset, computerPage.getPageSize());

		computerPage.setElementList(computerList);
		getNbSearchElements(computerPage, name);

		return computerPage;
	}

	@Override
	public Computer create(Computer computer) {
		LOG.info("create request.");

		KeyHolder holder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(CREATE, initParameters(computer), holder);
		computer.setId(holder.getKey().longValue());

		return computer;
	}

	@Override
	public Computer update(Computer computer) {
		LOG.info("update request.");

		namedParameterJdbcTemplate.update(UPDATE, initParameters(computer).addValue("id", computer.getId()));

		return computer;
	}

	@Override
	public void delete(long id) {
		LOG.info("delete request.");

		this.jdbcTemplate.update(DELETE, id);
	}

	@Override
	public void deleteComputersByCompanyId(long companyId) {
		LOG.info("deleteComputerByCompanyId request.");

		this.jdbcTemplate.update(DELETE_COMPUTERS_BY_COMPANYID, companyId);
	}
	
	@Override
	public void getNumberOfElements(Page<Computer> computerPage) {
		LOG.info("getNumberOfElements request.");

		computerPage.setNumberOfElements(this.jdbcTemplate.queryForObject(NUMBER_OF_ELEMENTS, Integer.class));

		// Set number of pages and rounds to the upper integer if the division
		// has a remainder
		int numberOfPages = computerPage.getNumberOfElements() / computerPage.getPageSize();

		if ((computerPage.getNumberOfElements() % computerPage.getPageSize()) != 0) {
			computerPage.setNumberOfPages(numberOfPages + 1);
		} else {
			computerPage.setNumberOfPages(numberOfPages);
		}
	}

	/**
	 * Gets number of computers in search query.
	 * 
	 * @return Count of computers.
	 */
	private void getNbSearchElements(Page<Computer> computerPage, String name) {
		LOG.info("getNbSearchElements request.");

		computerPage.setNumberOfElements(
				this.jdbcTemplate.queryForObject(NB_SEARCH_ELEMENTS, Integer.class, name + '%', name + '%'));

		// set number of elements in search query and rounds to the upper
		// integer if the division has a remainder
		int numberOfPages = computerPage.getNumberOfElements() / computerPage.getPageSize();

		if ((computerPage.getNumberOfElements() % computerPage.getPageSize()) != 0) {
			computerPage.setNumberOfPages(numberOfPages + 1);
		} else {
			computerPage.setNumberOfPages(numberOfPages);
		}
	}

	/**
	 * 
	 * @param computer
	 *            Computer which value to add in map.
	 * @return Map of parameters.
	 */
	private MapSqlParameterSource initParameters(Computer computer) {
		LOG.info("Init MapSqlParameterSource.");

		MapSqlParameterSource paramsMap = new MapSqlParameterSource();

		paramsMap.addValue("name", computer.getName());
		paramsMap.addValue("introduced", computer.getIntroduced());
		paramsMap.addValue("discontinued", computer.getDiscontinued());
		if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
			paramsMap.addValue("companyId", computer.getCompany().getId());
		} else {
			paramsMap.addValue("companyId", null);
		}

		return paramsMap;
	}
}
