package com.cts.mcbkend.documentservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mcbkend.documentservice.entity.DocumentEntity;
import com.cts.mcbkend.documentservice.model.DocumentDto;
import com.cts.mcbkend.documentservice.repository.DocumentRepository;
import com.cts.mcbkend.documentservice.service.DocumentService;
import com.cts.mcbkend.documentservice.util.Constants;
import com.sun.el.parser.ParseException;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private DocumentRepository documentRepository; 
	
	@Override
	public List<DocumentDto> findByUserId(Long userId) {
		List<DocumentDto> documentDtoList = null;
		List<DocumentEntity> documentEntityList = documentRepository.findByUserId(userId);
		if(documentEntityList!=null) {
			documentDtoList = documentEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return documentDtoList;
	}

	@Override
	public DocumentDto findByLoanNumAndDocId(String loanNum, String docId) {
		DocumentDto documentDtoObj = null;
		DocumentEntity documentEntity = documentRepository.findByLoanNumAndDocId(loanNum, docId);
		if(documentEntity!=null) {
			documentDtoObj = convertToDto(documentEntity);
		}
		return documentDtoObj;
	}

	@Override
	public List<DocumentDto> findByUserIdAndLoanNum(Long userId, String loanNum) {
		List<DocumentDto> documentDtoList = null;
		List<DocumentEntity> documentEntityList = documentRepository.findByUserIdAndLoanNum(userId, loanNum);
		if(documentEntityList!=null) {
			documentDtoList = documentEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return documentDtoList;
	}
	
	@Override
	public List<DocumentDto> findAlldocuments() {
		List<DocumentDto> documentDtoList = null;
		List<DocumentEntity> documentEntityList = documentRepository.findAll();
		if(documentEntityList!=null) {
			documentDtoList = documentEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return documentDtoList;
	}
	
	@Override
	public List<DocumentDto> findByLoanNum(String loanNum) {
		List<DocumentDto> documentDtoList = null;
		List<DocumentEntity> documentEntityList = documentRepository.findByLoanNum(loanNum);
		if(documentEntityList!=null) {
			documentDtoList = documentEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return documentDtoList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public String deleteByLoanNumAndDocId(Long documentIndex, DocumentDto documentDto) {
		long count = 0;
		long updateStatus = 0;
		String status = null;
		count = documentRepository.countByUserIdAndLoanNum(documentDto.getUserId(), documentDto.getLoanNum());
		if(count == 0) {
			status = "User/Loan is not valid";
		}else if(count > 1) {
			updateStatus = documentRepository.deleteByLoanNumAndDocId(documentDto.getLoanNum(), documentDto.getDocId());
			if(updateStatus>0) {
				status = "Document deleted successfully for loan number "+documentDto.getLoanNum();
			} else  {
				status = "Document deleted is not successful for loan number "+documentDto.getLoanNum();
			}
		}
		return status;
	}
	
	@Override
	public List<DocumentDto> findByApprvlStatusIsAvalilable() {
		List<DocumentDto> documentDtoList = null;
		List<DocumentEntity> documentEntityList = documentRepository.findByApprvlStatusNot(Constants.NOT_AVAILABLE);
		if(documentEntityList!=null) {
			documentDtoList = documentEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return documentDtoList;
	}

	@Override
	public String updateApprovalStatus(DocumentDto documentDto) {
		long count = 0;
		String status = null;
		count = documentRepository.updateApprovalStatus(documentDto.getApprvlStatus(),documentDto.getLoanNum(), documentDto.getDocId());
		if(count > 0) {
			status = "Document status updated successfullly for loan number "+documentDto.getLoanNum();
		} else {
			status = "Document status not updated successfullly for loan number "+documentDto.getLoanNum();
		}
		return status;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public DocumentDto updateLoanDocument(Long documentIndex, DocumentDto documentDto) throws Exception{
		DocumentDto registerDocumentDto = null;
		DocumentEntity registerDocumentEntity = null;
		DocumentEntity documentEntityObj = null;
		documentEntityObj = documentRepository.findById(documentIndex);
			if(documentEntityObj!=null) {
				documentEntityObj.setApprvlStatus(Constants.INITIATE_APPROVAL_STATUS);
				documentEntityObj.setDocDesc(documentDto.getDocDesc());
				documentEntityObj.setDocId(documentDto.getDocId());//Need to be changed by dynamically generated
				documentEntityObj.setDocLocation(documentDto.getDocLocation());
				documentEntityObj.setDocTitle(documentDto.getDocTitle());
				registerDocumentEntity = documentRepository.saveAndFlush(documentEntityObj);
				registerDocumentDto = convertToDto(registerDocumentEntity);
			}
		return registerDocumentDto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public DocumentDto addLoanDocument(DocumentDto documentDto) throws Exception{
		DocumentDto registerDocumentDto = null;
		DocumentEntity registerDocumentEntity = null;
		DocumentEntity documentEntity = convertToEntity(documentDto);
		registerDocumentEntity = documentRepository.saveAndFlush(documentEntity);
		
		if(registerDocumentEntity!=null) {
			registerDocumentDto = convertToDto(registerDocumentEntity);
		}
		return registerDocumentDto;
	}
	
	private DocumentDto convertToDto(DocumentEntity documentEntity) {
		DocumentDto documentDto = modelMapper.map(documentEntity, DocumentDto.class);
	    return documentDto;
	}
	
	private DocumentEntity convertToEntity(DocumentDto documentDto) throws ParseException {
		DocumentEntity documentEntity = modelMapper.map(documentDto, DocumentEntity.class);
	    return documentEntity;
	}

	
}