package com.cts.mcbkend.aggregatorservice.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mcbkend.aggregatorservice.feign.DocumentService;
import com.cts.mcbkend.aggregatorservice.feign.LoanService;
import com.cts.mcbkend.aggregatorservice.feign.UserService;
import com.cts.mcbkend.aggregatorservice.model.DocumentDto;
import com.cts.mcbkend.aggregatorservice.model.LoanDocumentDto;
import com.cts.mcbkend.aggregatorservice.model.LoanDto;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.service.AggregatorService;

@Service("AggregatorService")
public class AggregatorServiceImpl implements AggregatorService {

	@Autowired
	private UserService userService;

	@Autowired
	private LoanService loanService;

	@Autowired
	private DocumentService documentService;

	@Override
	public List<UserLoanDto> getAllUserLoanList() throws Exception {
		
		List<UserLoanDto> userLoanDtoList = new ArrayList<UserLoanDto>();
		List<LoanDocumentDto> loanDocumentList= null;
		UserLoanDto userLoanDto = null;
		List<UserDto> userDtoList = null;
		List<LoanDto> loanDtoList = null;
		List<DocumentDto> documentDtoList = null;
		ResponseEvent<List<UserDto>> listUserDtoResponse = null;
		ResponseEvent<List<LoanDto>> listLoanDtoResponse = null;
		ResponseEvent<List<DocumentDto>> listDocumentDtoResponse = null;
		Set<Long> userIds = new HashSet<Long>();
		
		listUserDtoResponse = userService.getUserList();
		userDtoList = listUserDtoResponse.getPayload();
		userDtoList.stream().forEach((userDto) -> userIds.add(userDto.getId()));
		
		listLoanDtoResponse = loanService.getMultipleLoans(new ArrayList<Long>(userIds));
		loanDtoList = listLoanDtoResponse.getPayload();
		Map<Long, List<LoanDto>> userLoanMap = loanDtoList.stream().collect(Collectors.groupingBy(w -> w.getUserId()));
		
		listDocumentDtoResponse = documentService.getMultipleDocuments(new ArrayList<Long>(userIds));
		documentDtoList = listDocumentDtoResponse.getPayload();
		Map<String, List<DocumentDto>> loanDocumentMap = documentDtoList.stream().collect(Collectors.groupingBy(w -> w.getLoanNum()));
		
		for(UserDto eachUserDto: userDtoList) {
			userLoanDto = new UserLoanDto();
			userLoanDto.setUserId(eachUserDto.getId());
			userLoanDto.setAddress(eachUserDto.getAddress());
			userLoanDto.setContacNo(eachUserDto.getContacNo());
			userLoanDto.setCountry(userLoanDto.getCountry());
			userLoanDto.setDob(eachUserDto.getDob());
			userLoanDto.setEmail(eachUserDto.getEmail());
			userLoanDto.setfName(eachUserDto.getfName());
			userLoanDto.setlName(eachUserDto.getlName());
			userLoanDto.setPan(eachUserDto.getPan());
			userLoanDto.setRole(eachUserDto.getRole());
			userLoanDto.setSsn(eachUserDto.getSsn());
			userLoanDto.setState(eachUserDto.getState());
			userLoanDto.setUserName(eachUserDto.getUserName());
			List<LoanDto> loanDtoListObj = userLoanMap.get(eachUserDto.getId());
			loanDocumentList = new ArrayList<LoanDocumentDto>();
			for(LoanDto eachLoanDtoObj: loanDtoListObj) {
				LoanDocumentDto loanDocumentDto = new LoanDocumentDto();
				loanDocumentDto.setDocumentList(loanDocumentMap.get(eachLoanDtoObj.getLoanNum()));
				loanDocumentDto.setLoanDesc(eachLoanDtoObj.getLoanDesc());
				loanDocumentDto.setLoanId(eachLoanDtoObj.getId());
				loanDocumentDto.setLoanNum(eachLoanDtoObj.getLoanNum());
				loanDocumentDto.setLoanStatus(eachLoanDtoObj.getLoanNum());
				loanDocumentDto.setLoanType(eachLoanDtoObj.getLoanType());
				loanDocumentDto.setLockId(eachLoanDtoObj.getLockId());
				loanDocumentDto.setVersion(eachLoanDtoObj.getVersion()+1);
				loanDocumentList.add(loanDocumentDto);
			}
			userLoanDto.setLoanDocumentList(loanDocumentList);
			userLoanDtoList.add(userLoanDto);
		}
		return userLoanDtoList;
	}

}