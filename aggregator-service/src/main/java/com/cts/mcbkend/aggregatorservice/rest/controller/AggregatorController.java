package com.cts.mcbkend.aggregatorservice.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.aggregatorservice.model.DocumentDto;
import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;
import com.cts.mcbkend.aggregatorservice.rest.common.AggregatorCommonExceptionHandlingController;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;
import com.cts.mcbkend.aggregatorservice.service.AggregatorService;

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
	@RequestMapping(value={"/v1.0/get-document/loanNumber/{loanNum}/docId/{docId}", "/v1.1/get-document/loanNumber/{loanNum}/docId/{docId}"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<DocumentDto>> getDocumentByLoanNumAndDocId(@PathVariable("loanNum") String loanNum, @PathVariable("docId") String docId) throws Exception { 
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER") +"=====> Looking for document by loan number {} and docId {}", loanNum, docId);
		if(StringUtils.isEmpty(loanNum) || StringUtils.isEmpty(docId) ) {
			AggregatorRestException userRestException = new AggregatorRestException();
			userRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			userRestException.setErrorMessage("Document inforamtion missing !!");
			throw userRestException;
		}
		DocumentDto documentDto = null;
		if(documentDto==null) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.NO_CONTENT);
			aggregatorRestException.setErrorMessage("No such loan document available for this user!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<DocumentDto>>(ResponseEvent.response(documentDto), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-all-user-loans", "/v1.1/get-all-user-loans"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<UserLoanDto>>> getAllUserLoanList() throws Exception {
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER") +"=====> Get all user loans with document details");
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
	@RequestMapping(value={"/v1.2/add-document"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<DocumentDto>> addLoanDocument(@RequestBody DocumentDto documentDto) throws Exception {
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER") +"=====> Adding document to the loan");
		if(StringUtils.isEmpty(documentDto.getDocDesc()) || StringUtils.isEmpty(documentDto.getDocId()) 
				|| StringUtils.isEmpty(documentDto.getDocLocation()) || StringUtils.isEmpty(documentDto.getDocTitle()) || StringUtils.isEmpty(documentDto.getLoanNum())
				|| StringUtils.isEmpty(documentDto.getUserId())) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			aggregatorRestException.setErrorMessage("Document inforamtion missing !!");
			throw aggregatorRestException;
		}
		DocumentDto addedDocumentDto = null;
		if(addedDocumentDto.getId()==null || addedDocumentDto.getId()<1) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.NOT_FOUND);
			aggregatorRestException.setErrorMessage("No documents available!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<DocumentDto>>(ResponseEvent.response(addedDocumentDto), HttpStatus.CREATED);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/modify-document/documentIndex/{documentIndex}"},method= RequestMethod.PUT, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<DocumentDto>> updateLoanDocument(@PathVariable("documentIndex") Long documentIndex, @RequestBody DocumentDto documentDto) throws Exception {
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER") +"=====> udating the loan by document index {}", documentIndex);
		if(StringUtils.isEmpty(documentDto.getApprvlStatus()) || StringUtils.isEmpty(documentDto.getDocDesc()) || StringUtils.isEmpty(documentDto.getDocId()) 
				|| StringUtils.isEmpty(documentDto.getDocLocation()) || StringUtils.isEmpty(documentDto.getDocTitle()) || StringUtils.isEmpty(documentDto.getLoanNum())
				|| StringUtils.isEmpty(documentDto.getUserId())) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			aggregatorRestException.setErrorMessage("Document inforamtion missing !!");
			throw aggregatorRestException;
		}
		DocumentDto addedDocumentDto = null;
		if(addedDocumentDto.getId()==null || addedDocumentDto.getId()<1) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.CONFLICT);
			aggregatorRestException.setErrorMessage("No documents available!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<DocumentDto>>(ResponseEvent.response(addedDocumentDto), HttpStatus.ACCEPTED);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/delete-document/documentIndex/{documentIndex}"},method= RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<String>> deleteLoanDocument(@PathVariable("documentIndex") Long documentIndex, @RequestBody DocumentDto documentDto) throws Exception {
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER") +"=====> deleting of document by document index {}", documentIndex);
		if(StringUtils.isEmpty(documentDto.getApprvlStatus()) || StringUtils.isEmpty(documentDto.getDocDesc()) || StringUtils.isEmpty(documentDto.getDocId()) 
				|| StringUtils.isEmpty(documentDto.getDocLocation()) || StringUtils.isEmpty(documentDto.getDocTitle()) || StringUtils.isEmpty(documentDto.getLoanNum())
				|| StringUtils.isEmpty(documentDto.getUserId())) {
			AggregatorRestException aggregatorRestException = new AggregatorRestException();
			aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			aggregatorRestException.setErrorMessage("Document inforamtion missing !!");
			throw aggregatorRestException;
		}
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(null), HttpStatus.ACCEPTED);
		
	}
}
