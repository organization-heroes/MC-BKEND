package com.cts.mcbkend.aggregatorservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;
import com.cts.mcbkend.aggregatorservice.rest.common.AggregatorCommonExceptionHandlingController;
import com.cts.mcbkend.aggregatorservice.rest.controller.AggregatorController;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.service.AggregatorService;
import com.netflix.ribbon.RequestTemplate.RequestBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:bootstrap-test.yml")
@WebMvcTest(value = AggregatorController.class)
public class AggregatorControllerTest { 
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AggregatorService aggregatorService;
	
	List<UserLoanDto> mockUserLoanResponse = new ArrayList<UserLoanDto>();
	
	
	String sampleResponse = "{\"payload\": [{\n" + 
			"      \"userId\": 2,\n" + 
			"      \"fName\": \"fName2\",\n" + 
			"      \"lName\": \"lName2\",\n" + 
			"      \"userName\": \"uName2\",\n" + 
			"      \"password\": null,\n" + 
			"      \"role\": \"Customer\",\n" + 
			"      \"address\": \"2 M.C Path\",\n" + 
			"      \"state\": \"WB\",\n" + 
			"      \"country\": \"India\",\n" + 
			"      \"email\": \"fn2@email.com\",\n" + 
			"      \"pan\": \"YWPTH91821\",\n" + 
			"      \"contacNo\": \"9081726353\",\n" + 
			"      \"dob\": \"17/06/2001\",\n" + 
			"      \"ssn\": \"ssn2\",\n" + 
			"      \"authToken\": null,\n" + 
			"      \"loanDocumentList\": []\n" + 
			"    }],\n" + 
			"  \"status\": true,\n" + 
			"  \"error\": null\n" + 
			"}";
	@Test
	public void retrieveAllCustomerLoanInfo() throws Exception {

		mockUserLoanResponse.add(new UserLoanDto((long) 2, "fName2","lName2", "uName2", null, "Customer", "2 M.C Path", "WB", "India"
				, "fn2@email.com", "YWPTH91821", "9081726353", "17/06/2001", "ssn2", null, null));
		ResponseEvent<List<UserLoanDto>> respEvent = new ResponseEvent<List<UserLoanDto>>(mockUserLoanResponse);
		Mockito.when(
				aggregatorService.getAllUserLoanList(Mockito.anyString())).thenReturn(mockUserLoanResponse);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/aggreagator/v1.0/get-all-user-loans").accept(
				MediaType.APPLICATION_JSON);

		mockMvc.perform(get("/v1.0/get-all-user-loans")
                .content(sampleResponse)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
		
		//MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		verify(aggregatorService, times(0)).getAllUserLoanList((any(String.class)));
		//System.out.println("------------"+result.getResponse());
		String expected = "{payload:[{userId: 2, fName: fName2, lName: lName2, userName: uName2, password: null, role: Customer, address: 2 M.C Path, state: WB, "
				+ "country: India, email: fn2@email.com, pan: YWPTH91821, contacNo: 9081726353, dob: 17/06/2001, "
				+ "ssn: ssn2, authToken: null, loanDocumentList: []}], status: true, error: null}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		//JSONAssert.assertEquals(expected, result.getResponse()
			//	.getContentAsString(), false);
	}
}
