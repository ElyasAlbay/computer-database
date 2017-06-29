package com.excilys.cdb.webapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.cdb.binding.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDto;
import com.excilys.cdb.model.util.Field;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.webapp.exceptions.RestExceptionHandler;

@Controller
@RequestMapping("/computer")
public class ComputerController {
	private static final Logger LOG = LoggerFactory.getLogger(ComputerController.class);

	@Autowired
	private ComputerService computerService;

	@GetMapping(value = "/get/{id}")
	@ResponseBody
	public ComputerDto getById(@PathVariable long id) {
		LOG.info("Get by id request.");

		if (id < 0)
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");

		ComputerDto computer = ComputerMapper.createDto(computerService.getById(id));

		if (computer == null)
			RestExceptionHandler.throwEntityNotFoundException("Computer not found in database.");

		return computer;
	}

	@GetMapping(value = "/page/{pageNumber}/{pageSize}/{order}")
	@ResponseBody
	public List<ComputerDto> getPageWithOrder(@PathVariable int pageNumber, @PathVariable int pageSize,
			@PathVariable String order) {
		LOG.info("Get page request with number, size and order.");

		if (pageNumber < 0 || pageSize < 0 || !Field.isValidOrder(order))
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");

		Page<Computer> computerPage = new Page<>();
		computerPage.setPageNumber(pageNumber);
		computerPage.setPageSize(pageSize);
		computerPage.setOrder(order);

		List<ComputerDto> computerList = ComputerMapper
				.createDtoList(computerService.getAll(computerPage).getElementList());

		return computerList;
	}

	@GetMapping(value = "/page/{pageNumber}/{pageSize}")
	@ResponseBody
	public List<ComputerDto> getPage(@PathVariable int pageNumber, @PathVariable int pageSize) {
		LOG.info("Get page request with number and size.");

		if (pageNumber < 0 || pageSize < 0)
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");

		Page<Computer> computerPage = new Page<>();
		computerPage.setPageNumber(pageNumber);
		computerPage.setPageSize(pageSize);

		List<ComputerDto> computerList = ComputerMapper
				.createDtoList(computerService.getAll(computerPage).getElementList());

		return computerList;
	}

	@GetMapping(value = "/page/{pageNumber}")
	@ResponseBody
	public List<ComputerDto> getDefaultPage(@PathVariable int pageNumber) {
		LOG.info("Get page request with number.");

		if (pageNumber < 0)
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");

		Page<Computer> computerPage = new Page<>();
		computerPage.setPageNumber(pageNumber);

		List<ComputerDto> computerList = ComputerMapper
				.createDtoList(computerService.getAll(computerPage).getElementList());

		return computerList;
	}

	@GetMapping(value = "/search/{name}")
	@ResponseBody
	public List<ComputerDto> getSearch(@PathVariable String name) {
		LOG.info("Search request.");

		List<ComputerDto> computerList = ComputerMapper
				.createDtoList(computerService.searchByName(new Page<Computer>(), name).getElementList());

		return computerList;
	}

	@PostMapping(value = "/add")
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody ComputerDto computerDto) {
		LOG.info("Add request.");

		Computer computer = ComputerMapper.createObject(computerDto);
		if (computer.getDiscontinued().isBefore(computer.getIntroduced())) {
			RestExceptionHandler.throwInvalidParameterException(
					"Date of introduction has to be anterior to date of discontinuation.");
		}
			
		computerService.create(computer);
		return new ResponseEntity<>("Add susccessful. Id="+computer.getId(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		LOG.info("Delete request.");

		if (id < 0)
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");
		if (computerService.delete(id) <= 0)
			RestExceptionHandler.throwEntityNotFoundException("Computer not found in database.");

		return new ResponseEntity<>("Delete susccessful.", HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<String> update(@Valid @RequestBody ComputerDto computerDto) {
		LOG.info("Update request.");

		Computer computer = ComputerMapper.createObject(computerDto);
		if (computer.getDiscontinued().isBefore(computer.getIntroduced())) {
			RestExceptionHandler.throwInvalidParameterException(
					"Date of introduction has to be anterior to date of discontinuation.");
		}

		computerService.update(computer);
		return new ResponseEntity<>("Update susccessful.", HttpStatus.OK);
	}

}
