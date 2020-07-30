package com.cts.mcbkend.aggregatorservice.rest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;
import com.cts.mcbkend.aggregatorservice.rest.common.AggregatorCommonExceptionHandlingController;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;
import com.cts.mcbkend.aggregatorservice.service.AggregatorService;
import com.cts.mcbkend.aggregatorservice.util.Constants;

@RestController("aggregatorController")
@RequestMapping("/aggregator")
public class AggregatorController extends AggregatorCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AggregatorController.class);

	@Autowired
	private AggregatorService aggregatorService;
	
	@Autowired
	private HttpServletRequest request;
	
	
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-all-user-loans", "/v1.1/get-all-user-loans"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<UserLoanDto>>> getAllUserLoanList() throws Exception {
		LOGGER.info("sessionID: {} =====> Get all user loans with document details",request.getHeader(Constants.authHeader));
		List<UserLoanDto> userLoanDtoList = null;
		userLoanDtoList=aggregatorService.getAllUserLoanList(request.getHeader("AUTH_HEADER") );
		if(userLoanDtoList==null || userLoanDtoList.isEmpty() || userLoanDtoList.size()<1) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			aggregatorRestException.setErrorMessage("No user has registered yet!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<List<UserLoanDto>>>(ResponseEvent.response(userLoanDtoList), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/register-user"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<UserLoanDto>> createUserWithLoan(@RequestBody UserLoanDto userLoanDto) throws Exception {
		LOGGER.info("sessionID: {} =====> Adding user ",request.getHeader(Constants.authHeader));
		if(userLoanDto==null || StringUtils.isEmpty(userLoanDto.getfName()) || StringUtils.isEmpty(userLoanDto.getlName()) || StringUtils.isEmpty(userLoanDto.getContacNo()) 
				|| StringUtils.isEmpty(userLoanDto.getAddress()) || StringUtils.isEmpty(userLoanDto.getCountry()) || StringUtils.isEmpty(userLoanDto.getState())
				|| StringUtils.isEmpty(userLoanDto.getDob()) || StringUtils.isEmpty(userLoanDto.getEmail()) || StringUtils.isEmpty(userLoanDto.getPan()) 
				|| StringUtils.isEmpty(userLoanDto.getPassword())|| StringUtils.isEmpty(userLoanDto.getRole()) || StringUtils.isEmpty(userLoanDto.getSsn()) 
				|| StringUtils.isEmpty(userLoanDto.getUserName())) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			aggregatorRestException.setErrorMessage("User inforamtioninfromation is missing !!");
			throw aggregatorRestException;
		}
		if(userLoanDto.getRole().equalsIgnoreCase("Customer")) {
			if(userLoanDto.getLoanDocumentList()==null || userLoanDto.getLoanDocumentList().isEmpty() ||userLoanDto.getLoanDocumentList().size()<1||StringUtils.isEmpty(userLoanDto.getLoanDocumentList().get(0).getLoanDesc()) 
					|| StringUtils.isEmpty(userLoanDto.getLoanDocumentList().get(0).getLoanNum()) 
					|| StringUtils.isEmpty(userLoanDto.getLoanDocumentList().get(0).getLoanStatus()) 
					|| StringUtils.isEmpty(userLoanDto.getLoanDocumentList().get(0).getLoanType())) {
				AggregatorRestException aggregatorRestException = new AggregatorRestException();
				aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
				aggregatorRestException.setErrorMessage("User inforamtion or Loan infromation is missing !!");
				throw aggregatorRestException;
			}
		}
		UserLoanDto addedUserLoanDto = null;
		addedUserLoanDto = aggregatorService.createUserWithLoan(request.getHeader("AUTH_HEADER"), userLoanDto);
		if(addedUserLoanDto==null || addedUserLoanDto.getUserId()==null || !(addedUserLoanDto.getUserId()>0)) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.NOT_FOUND);
			aggregatorRestException.setErrorMessage("User registration fails!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<UserLoanDto>>(ResponseEvent.response(addedUserLoanDto), HttpStatus.CREATED);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/login-user"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<UserLoanDto>> loginUser(@RequestBody Map<String,String> body) throws Exception {
		LOGGER.info("sessionID: {} =====> Login user ",request.getHeader(Constants.authHeader));
		if(body==null || body.isEmpty() || StringUtils.isEmpty(body.get("username")) || StringUtils.isEmpty(body.get("password")) ) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			aggregatorRestException.setErrorMessage("User login information is missing !!");
			throw aggregatorRestException;
		}
		UserLoanDto loggedUserLoanDto = null;
		loggedUserLoanDto = aggregatorService.loginUser(body);
		if(loggedUserLoanDto==null || loggedUserLoanDto.getUserId()==null || !(loggedUserLoanDto.getUserId()>0)) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.NOT_FOUND);
			aggregatorRestException.setErrorMessage("User login fails!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<UserLoanDto>>(ResponseEvent.response(loggedUserLoanDto), HttpStatus.ACCEPTED);
		
	}
	
}
