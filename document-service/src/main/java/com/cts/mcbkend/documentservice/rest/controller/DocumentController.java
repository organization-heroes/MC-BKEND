package com.cts.mcbkend.documentservice.rest.controller;

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

import com.cts.mcbkend.documentservice.model.DocumentDto;
import com.cts.mcbkend.documentservice.rest.common.DocumentCommonExceptionHandlingController;
import com.cts.mcbkend.documentservice.rest.event.ResponseEvent;
import com.cts.mcbkend.documentservice.rest.exception.DocumentRestException;
import com.cts.mcbkend.documentservice.service.DocumentService;

@RestController("documentController")
@RequestMapping("/document")
public class DocumentController extends DocumentCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	@Value(value = "${custom.instance.id}")
	private String customInstanceId;
	
	@Autowired
	private DocumentService documentService;
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-document/loanNumber/{loanNum}/docId/{docId}", "/v1.1/get-document/loanNumber/{loanNum}/docId/{docId}"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<DocumentDto>> getDocumentByLoanNumAndDocId(@PathVariable("loanNum") String loanNum, @PathVariable("docId") String docId) throws Exception { 
		LOGGER.info(customInstanceId + "=====> Looking for document by loan number {} and docId {}", loanNum, docId);
		if(StringUtils.isEmpty(loanNum) || StringUtils.isEmpty(docId) ) {
			DocumentRestException userRestException = new DocumentRestException();
			userRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			userRestException.setErrorMessage("Document inforamtion missing !!");
			throw userRestException;
		}
		DocumentDto documentDto = null;
		documentDto=documentService.findByLoanNumAndDocId(loanNum, docId);
		if(documentDto==null) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.NO_CONTENT);
			documentRestException.setErrorMessage("No such loan document available for this user!");
			throw documentRestException;
		}
		return new ResponseEntity<ResponseEvent<DocumentDto>>(ResponseEvent.response(documentDto), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-document-list", "/v1.1/get-document-list"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<DocumentDto>>> findAllAvailableDocuments() throws Exception {
		LOGGER.info(customInstanceId + "=====> Get all available documents");
		List<DocumentDto> documentDtoList = null;
		documentDtoList=documentService.findByApprvlStatusIsAvalilable();
		if(documentDtoList==null || documentDtoList.isEmpty() || documentDtoList.size()<1) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			documentRestException.setErrorMessage("No user has uploaded document yet!");
			throw documentRestException;
		}
		return new ResponseEntity<ResponseEvent<List<DocumentDto>>>(ResponseEvent.response(documentDtoList), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/add-document"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<DocumentDto>> addLoanDocument(@RequestBody DocumentDto documentDto) throws Exception {
		LOGGER.info(customInstanceId + "=====> Adding document to the loan");
		if(StringUtils.isEmpty(documentDto.getDocDesc()) || StringUtils.isEmpty(documentDto.getDocId()) 
				|| StringUtils.isEmpty(documentDto.getDocLocation()) || StringUtils.isEmpty(documentDto.getDocTitle()) || StringUtils.isEmpty(documentDto.getLoanNum())
				|| StringUtils.isEmpty(documentDto.getUserId())) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			documentRestException.setErrorMessage("Document inforamtion missing !!");
			throw documentRestException;
		}
		DocumentDto addedDocumentDto = null;
		addedDocumentDto = documentService.addLoanDocument(documentDto);
		if(addedDocumentDto.getId()==null || addedDocumentDto.getId()<1) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.NOT_FOUND);
			documentRestException.setErrorMessage("No documents available!");
			throw documentRestException;
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
		LOGGER.info(customInstanceId + "=====> udating the loan by document index {}", documentIndex);
		if(StringUtils.isEmpty(documentDto.getApprvlStatus()) || StringUtils.isEmpty(documentDto.getDocDesc()) || StringUtils.isEmpty(documentDto.getDocId()) 
				|| StringUtils.isEmpty(documentDto.getDocLocation()) || StringUtils.isEmpty(documentDto.getDocTitle()) || StringUtils.isEmpty(documentDto.getLoanNum())
				|| StringUtils.isEmpty(documentDto.getUserId())) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			documentRestException.setErrorMessage("Document inforamtion missing !!");
			throw documentRestException;
		}
		DocumentDto addedDocumentDto = null;
		addedDocumentDto = documentService.updateLoanDocument(documentIndex, documentDto);
		if(addedDocumentDto.getId()==null || addedDocumentDto.getId()<1) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.CONFLICT);
			documentRestException.setErrorMessage("No documents available!");
			throw documentRestException;
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
		LOGGER.info(customInstanceId + "=====> deleting of document by document index {}", documentIndex);
		if(StringUtils.isEmpty(documentDto.getApprvlStatus()) || StringUtils.isEmpty(documentDto.getDocDesc()) || StringUtils.isEmpty(documentDto.getDocId()) 
				|| StringUtils.isEmpty(documentDto.getDocLocation()) || StringUtils.isEmpty(documentDto.getDocTitle()) || StringUtils.isEmpty(documentDto.getLoanNum())
				|| StringUtils.isEmpty(documentDto.getUserId())) {
			DocumentRestException documentRestException = new DocumentRestException();
			documentRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			documentRestException.setErrorMessage("Document inforamtion missing !!");
			throw documentRestException;
		}
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(documentService.deleteByLoanNumAndDocId(documentIndex, documentDto)), HttpStatus.ACCEPTED);
		
	}
}
