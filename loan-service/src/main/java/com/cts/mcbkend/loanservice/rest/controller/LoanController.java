package com.cts.mcbkend.loanservice.rest.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.loanservice.model.LoanDto;
import com.cts.mcbkend.loanservice.rest.common.LoanCommonExceptionHandlingController;
import com.cts.mcbkend.loanservice.rest.event.ResponseEvent;
import com.cts.mcbkend.loanservice.rest.exception.LoanRestException;
import com.cts.mcbkend.loanservice.service.LoanService;

@RestController("loanController")
@RequestMapping("/loan")
public class LoanController extends LoanCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
	
	@Value(value = "${custom.instance.id}")
	private String customInstanceId;

	@Autowired
	private LoanService loanService;
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-loan/loanNumber/{loanNum}", "/v1.1/get-loan/loanNumber/{loanNum}"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<LoanDto>> getLoanByLoanNum(@PathVariable("loanNum") String loanNum) throws Exception {
		LOGGER.info(customInstanceId + "=====> Looking for loan by loan number {} ", loanNum);
		if(StringUtils.isEmpty(loanNum)  ) {
			LoanRestException userRestException = new LoanRestException();
			userRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			userRestException.setErrorMessage("Loan inforamtion is missing !!");
			throw userRestException;
		}
		LoanDto loanDto = null;
		loanDto=loanService.findByLoanNum(loanNum);
		if(loanDto==null) {
			LoanRestException loanRestException = new LoanRestException();
			loanRestException.setErrorCode(HttpStatus.NO_CONTENT);
			loanRestException.setErrorMessage("No such loan available for this loan number!");
			throw loanRestException;
		}
		return new ResponseEntity<ResponseEvent<LoanDto>>(ResponseEvent.response(loanDto), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-loan-records", "/v1.1/get-loan-records"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<LoanDto>>> findAllAvailableLoans() throws Exception {
		LOGGER.info(customInstanceId + "=====> Get all available loans");
		List<LoanDto> loanDtoList = null;
		loanDtoList=loanService.fetchAllLoans();
		if(loanDtoList==null || loanDtoList.isEmpty() || loanDtoList.size()<1) {
			LoanRestException loanRestException = new LoanRestException();
			loanRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			loanRestException.setErrorMessage("No loan is avilable at this moment!");
			throw loanRestException;
		}
		return new ResponseEntity<ResponseEvent<List<LoanDto>>>(ResponseEvent.response(loanDtoList), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/create-loan"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<LoanDto>> createNewLoan(@RequestBody LoanDto loanDto) throws Exception {
		LOGGER.info(customInstanceId + "=====> Creating the loan");
		if(StringUtils.isEmpty(loanDto.getLoanDesc()) || StringUtils.isEmpty(loanDto.getLoanNum()) 
				|| StringUtils.isEmpty(loanDto.getLoanStatus()) || StringUtils.isEmpty(loanDto.getLoanType())
				|| StringUtils.isEmpty(loanDto.getUserId())) {
			LoanRestException loanRestException = new LoanRestException();
			loanRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			loanRestException.setErrorMessage("Loan inforamtion missing !!");
			throw loanRestException;
		}
		LoanDto addedLoanDto = null;
		addedLoanDto = loanService.createLoan(loanDto);
		if(addedLoanDto.getId()==null || addedLoanDto.getId()<1) {
			LoanRestException loanRestException = new LoanRestException();
			loanRestException.setErrorCode(HttpStatus.NOT_FOUND);
			loanRestException.setErrorMessage("Loan creation failure");
			throw loanRestException;
		}
		return new ResponseEntity<ResponseEvent<LoanDto>>(ResponseEvent.response(addedLoanDto), HttpStatus.CREATED);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/modify-loan/loanNumber/{loanNum}"},method= RequestMethod.PUT, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<LoanDto>> updateLoan(@PathVariable("loanNum") String loanNum, @RequestBody LoanDto loanDto) throws Exception {
		LOGGER.info(customInstanceId + "=====> udating the loan by loan number {}", loanNum);
		if(StringUtils.isEmpty(loanDto.getLoanDesc()) || StringUtils.isEmpty(loanDto.getLoanNum()) 
				|| StringUtils.isEmpty(loanDto.getLoanStatus()) || StringUtils.isEmpty(loanDto.getLoanType())
				|| StringUtils.isEmpty(loanDto.getUserId()) || StringUtils.isEmpty(loanDto.getVersion())) {
			LoanRestException loanRestException = new LoanRestException();
			loanRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			loanRestException.setErrorMessage("Loan inforamtion missing !!");
			throw loanRestException;
		}
		LoanDto addedLoanDto = null;
		addedLoanDto = loanService.updateLoan(loanNum, loanDto);
		if(addedLoanDto.getId()==null || addedLoanDto.getId()<1) {
			LoanRestException loanRestException = new LoanRestException();
			loanRestException.setErrorCode(HttpStatus.CONFLICT);
			loanRestException.setErrorMessage("Loan update failed available!");
			throw loanRestException;
		}
		return new ResponseEntity<ResponseEvent<LoanDto>>(ResponseEvent.response(addedLoanDto), HttpStatus.ACCEPTED);
		
	}
	
}
