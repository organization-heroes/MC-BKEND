package com.cts.mcbkend.loanservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.mcbkend.loanservice.entity.LoanEntity;
import com.cts.mcbkend.loanservice.model.LoanDto;
import com.cts.mcbkend.loanservice.repository.LoanRepository;
import com.cts.mcbkend.loanservice.rest.exception.LoanRestException;
import com.cts.mcbkend.loanservice.service.LoanService;
import com.cts.mcbkend.loanservice.util.Constants;
import com.sun.el.parser.ParseException;

@Service("loanServiceImpl")
@Transactional
public class LoanServiceImpl implements LoanService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private LoanRepository loanRepository; 
	
	@Override
	public List<LoanDto> findByUserId(Long userId) {
		List<LoanDto> loanDtoList = null;
		List<LoanEntity> loanEntityList = loanRepository.findByUserId(userId);
		if(loanEntityList!=null) {
			loanDtoList = loanEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return loanDtoList;
	}

	@Override
	public List<LoanDto> fetchAllLoans() {
		List<LoanDto> loanDtoList = null;
		List<LoanEntity> loanEntityList = loanRepository.findAll();
		if(loanEntityList!=null) {
			loanDtoList = loanEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return loanDtoList;
	}
	
	@Override
	public LoanDto findByLoanNum(String loanNum) {
		LoanDto loanDto = null;
		LoanEntity loanEntity = loanRepository.findByLoanNum(loanNum);
		if(loanEntity!=null) {
			loanDto=this.convertToDto(loanEntity);
		}
		return loanDto;
	}
	
	@Override
	public List<LoanDto> findByLoanNumIn(List<String> loanNumbers) {
		List<LoanDto> loanDtoList = null;
		List<LoanEntity> loanEntityList = loanRepository.findByLoanNumIn(loanNumbers);
		if(loanEntityList!=null) {
			loanDtoList = loanEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return loanDtoList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public LoanDto updateLoan(String loanNum, LoanDto loanDto) throws Exception{
		LoanDto registerLoanDto = null;
		LoanEntity registerLoanEntity = null;
		LoanEntity loanEntityObj = null;
		loanEntityObj = loanRepository.findById(loanDto.getId());
			if(loanEntityObj!=null && loanEntityObj.getLoanNum()==loanNum &&(loanEntityObj.getLockId()+1 == loanDto.getLockId())) {
				loanEntityObj.setLoanDesc(loanDto.getLoanDesc());
				loanEntityObj.setLoanStatus(loanDto.getLoanStatus());
				loanEntityObj.setLoanType(loanDto.getLoanType());
				loanEntityObj.setLockId(loanDto.getLockId());
				loanEntityObj.setVersion(loanDto.getVersion());
				loanEntityObj.setUserId(loanDto.getUserId());
				registerLoanEntity = loanRepository.saveAndFlush(loanEntityObj);
				registerLoanDto = convertToDto(registerLoanEntity);
			} else  {
				LoanRestException loanRestException = new LoanRestException();
				loanRestException.setErrorCode(HttpStatus.CONFLICT);
				loanRestException.setErrorMessage("Please takes latest version of the loan");
				throw loanRestException;
			}
		return registerLoanDto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public LoanDto createLoan(LoanDto loanDto) throws Exception{
		LoanDto registerLoanDto = null;
		LoanEntity registerLoanEntity = null;
		if(loanDto!=null) {
			LoanEntity loanEntity = convertToEntity(loanDto);
			loanEntity = updateDefaultValue(loanEntity);
			registerLoanEntity = loanRepository.saveAndFlush(loanEntity);
			if(registerLoanEntity!=null) {
				registerLoanDto = convertToDto(registerLoanEntity);
			}
		}
		return registerLoanDto;
	}
	
	private LoanEntity updateDefaultValue(LoanEntity loanEntity) {
		loanEntity.setLoanStatus(Constants.LOAN_CREATION_STATUS);
		loanEntity.setVersion(Constants.INITIAL_VERSION);
		loanEntity.setLockId(Constants.DEFAULT_LOCK_ID);
		return loanEntity;
	}
	
	
	
	private LoanDto convertToDto(LoanEntity loanEntity) {
		LoanDto loanDto = modelMapper.map(loanEntity, LoanDto.class);
	    return loanDto;
	}
	
	private LoanEntity convertToEntity(LoanDto loanDto) throws ParseException {
		LoanEntity loanEntity = modelMapper.map(loanDto, LoanEntity.class);
	    return loanEntity;
	}

	
}