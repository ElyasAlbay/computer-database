package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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

	private final static String LIST = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id ORDER BY ISNULL(%s), %s LIMIT ?, ?";
	private final static String GET_BY_ID = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.id=?";
	private final static String SEARCH_BY_NAME = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(%s), %s LIMIT ?, ?";
	private final static String CREATE = "INSERT INTO computer SET name=?, introduced=?, discontinued=?, company_id=?";
	private final static String UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private final static String DELETE = "DELETE FROM computer WHERE id=?";
	private final static String NUMBER_OF_ELEMENTS = "SELECT count(*) FROM computer";
	private final static String NB_SEARCH_ELEMENTS = "SELECT count(*) FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
	private final static String DELETE_COMPUTERS_BY_COMPANYID = "DELETE FROM computer WHERE company_id=?";

	/**
	 * Class constructor. Initiates connection to the database.
	 */
	public ComputerDaoImpl() {

	}

	@Override
	public Page<Computer> getAll(Page<Computer> computerPage) {
		LOG.info("getAll request.");
		
		String order;
		if (computerPage.getOrder().equals("computerName")) {
			order = "computer.name";
		} else if (computerPage.getOrder().equals("companyName")) {
			order = "company.name";
		} else {
			order = computerPage.getOrder();
		}
		
		String query = String.format(LIST, order, order);
		int pageOffset = (computerPage.getPageNumber() - 1) * computerPage.getPageSize();
		List<Computer> computerList = this.jdbcTemplate.query(query, new ComputerMapper(), pageOffset,
				computerPage.getPageSize());

		computerPage.setElementList(computerList);
		getNumberOfElements(computerPage);

		return computerPage;
	}

	@Override
	public Computer getById(int id) {
		LOG.info("getById request.");

		Computer computer = this.jdbcTemplate.queryForObject(GET_BY_ID, new Object[] { id }, new ComputerMapper());

		return computer;
	}

	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {
		LOG.info("searchByName request.");
		
		String order;
		if (computerPage.getOrder().equals("computerName")) {
			order = "computer.name";
		} else if (computerPage.getOrder().equals("companyName")) {
			order = "company.name";
		} else {
			order = computerPage.getOrder();
		}

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
		jdbcTemplate.update(new PreparedStatementCreator() {
			// Create prepared statement in which query variables are set
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, computer.getName());

				if (computer.getIntroduced() != null) {
					statement.setDate(2, Date.valueOf(computer.getIntroduced()));
				} else {
					statement.setNull(2, Types.TIMESTAMP);
				}
				if (computer.getDiscontinued() != null) {
					statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
				} else {
					statement.setNull(3, Types.TIMESTAMP);
				}
				if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
					statement.setLong(4, computer.getCompany().getId());
				} else {
					statement.setNull(4, Types.BIGINT);
				}
				return statement;
			}
		}, holder);

		computer.setId(holder.getKey().intValue());

		return computer;
	}

	@Override
	public Computer update(Computer computer) {
		LOG.info("update request.");

		jdbcTemplate.update(new PreparedStatementCreator() {
			// Create prepared statement in which query variables are set
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(UPDATE);
				statement.setInt(5, computer.getId());
				statement.setString(1, computer.getName());

				if (computer.getIntroduced() != null) {
					statement.setDate(2, Date.valueOf(computer.getIntroduced()));
				} else {
					statement.setNull(2, Types.TIMESTAMP);
				}
				if (computer.getDiscontinued() != null) {
					statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
				} else {
					statement.setNull(3, Types.TIMESTAMP);
				}
				if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
					statement.setLong(4, computer.getCompany().getId());
				} else {
					statement.setNull(4, Types.BIGINT);
				}
				return statement;
			}
		});

		return computer;
	}

	@Override
	public void delete(int id) {
		LOG.info("delete request.");

		this.jdbcTemplate.update(DELETE, id);
	}

	@Override
	public void deleteComputersByCompanyId(int companyId) {
		LOG.info("deleteComputerByCompanyId request.");

		this.jdbcTemplate.update(DELETE_COMPUTERS_BY_COMPANYID, companyId);
	}

	/**
	 * Gets number of computers in the database.
	 * 
	 * @return Count of computers.
	 */
	private void getNumberOfElements(Page<Computer> computerPage) {
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
}
