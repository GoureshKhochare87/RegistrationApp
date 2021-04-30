package com.registration.RegistrationApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.RegistrationApp.Dto.ResultModel;
import com.registration.RegistrationApp.Entity.Users;
import com.registration.RegistrationApp.Service.UserService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationAppApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	
	@Autowired private WebApplicationContext webApplicationContext;
	  
	@BeforeAll public void setup() {
	mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
	 
	
	@Test
	public void createUserTest() throws Exception {
		Users mockUser = new Users();
		mockUser.setId(1);
		mockUser.setUserId("Neosoft_1");
		mockUser.setName("Gouresh");
		mockUser.setSurname("Khochare");
		mockUser.setEmail("gouresh@gmail.com");
		mockUser.setDob(Date.valueOf("2020-02-02"));
		mockUser.setCity("Thane");
		mockUser.setRole("Developer");
		mockUser.setPassword("gouresh123");
		mockUser.setContactNumber("9090909090");
		mockUser.setDesignation("Java");
		mockUser.setPinCode(400606);
		mockUser.setJoiningDate(Date.valueOf("2020-02-02"));

		List<String> stringList = new ArrayList<String>();
		stringList.add("Success");

		ResultModel resultModel = new ResultModel();
		resultModel.setMessage("Success");
		resultModel.setData(stringList);

		String inputInJson = this.mapToJson(resultModel);

		String Url = "/user/saveUpdateUser";

		Mockito.when(userService.saveUser(Mockito.any(Users.class))).thenReturn(stringList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Url).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	//add
	@Test
	public void UpdateUser() throws Exception {
		Users mockUser=new Users();
		
		mockUser.setUserId("NeoSoft_2");
		mockUser.setId(2);
		mockUser.setName("Rohan");
		mockUser.setSurname("Patil");
		mockUser.setEmail("rohan@gmail.com");
		mockUser.setDob(Date.valueOf("2020-02-02"));
		mockUser.setCity("Thane");
		mockUser.setContactNumber("8080808080");
		mockUser.setDesignation("Java");
		mockUser.setPinCode(400606);
		mockUser.setJoiningDate(Date.valueOf("2020-02-02"));

		List<String> stringList = new ArrayList<String>();
		stringList.add("Success");

		ResultModel resultModel = new ResultModel();
		resultModel.setMessage("Success");
		resultModel.setData(stringList);

		String inputInJson = this.mapToJson(resultModel);

		String Url = "/user/saveUpdateUser";

		Mockito.when(userService.saveUser(Mockito.any(Users.class))).thenReturn(stringList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Url).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	
	@Test
	public void getAllUsersTest() throws Exception {
		
		Users mock1=new Users();
		
		mock1.setId(2);//change
		mock1.setName("Rohan");
		mock1.setSurname("Patil");
		mock1.setUserId("Neosoft_2");//change
		mock1.setPassword("rohan123");
		mock1.setEmail("rohan@gmail.com");
		mock1.setDob(Date.valueOf("2020-02-02"));
		mock1.setCity("Thane");
		mock1.setContactNumber("8080808080");
		mock1.setPinCode(400606);
		mock1.setDesignation("Java");
		mock1.setRole("Developer");
		mock1.setJoiningDate(Date.valueOf("2020-02-02"));
		
		//change
		Users mock2=new Users();
		
		mock2.setId(3);
		mock2.setUserId("Neosoft_3");
		mock2.setName("Popaye");
		mock2.setSurname("Patil");
		mock2.setUserId("Neosoft_3");
		mock2.setPassword("popaye123");
		mock2.setEmail("popaye@gmail.com");
		mock2.setDob(Date.valueOf("2020-02-02"));
		mock2.setCity("Pune");
		mock2.setContactNumber("7070707070");
		mock2.setPinCode(400606);
		mock2.setDesignation("Angular");
		mock2.setRole("Developer");
		mock2.setJoiningDate(Date.valueOf("2020-02-02"));
		
		List<Users> userList=new ArrayList<Users>();
		ResultModel resultModel = new ResultModel();
		userList.add(mock1);
		userList.add(mock2);
		resultModel.setData(userList);
		resultModel.setMessage("Success");
		
		String Url= "/user/getAllUsers";
		
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	

	@Test
	public void getUserByUserId() throws Exception{
		
		Users mock1=new Users();
		
		mock1.setId(1);
		mock1.setName("Gouresh");
		mock1.setSurname("Khochare");
		mock1.setUserId("Neosoft_1");
		mock1.setPassword("gouresh123");
		mock1.setEmail("gouresh@gmail.com");
		mock1.setDob(Date.valueOf("2020-02-02"));
		mock1.setCity("Thane");
		mock1.setContactNumber("9090909090");
		mock1.setPinCode(400606);
		mock1.setDesignation("Java");
		mock1.setRole("Developer");
		mock1.setJoiningDate(Date.valueOf("2020-02-02"));
		
		ResultModel resultModel=new ResultModel();
		resultModel.setData(mock1);
		resultModel.setMessage("Success");
		
		String Url="/user/getByUserId/Neosoft_1";
		
		Mockito.when(userService.getById(Mockito.anyString())).thenReturn(mock1);
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		
	}
	
	@Test
	public void searchByName() throws Exception{
		
		Users mock1=new Users();
		
		mock1.setId(1);
		mock1.setName("Gouresh");
		mock1.setSurname("Khochare");
		mock1.setUserId("Neosoft_1");
		mock1.setPassword("gouresh123");
		mock1.setEmail("gouresh@gmail.com");
		mock1.setDob(Date.valueOf("2020-02-02"));
		mock1.setCity("Thane");
		mock1.setContactNumber("9090909090");
		mock1.setPinCode(400606);
		mock1.setDesignation("Java");
		mock1.setRole("Developer");
		mock1.setJoiningDate(Date.valueOf("2020-02-02"));
		
		ResultModel resultModel=new ResultModel();
		resultModel.setData(mock1);
		resultModel.setMessage("Success");
		
		String Url="/user/getByName/Gouresh";
		
		Mockito.when(userService.getByName(Mockito.anyString())).thenReturn(mock1);
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		
	}

	@Test
	public void deleteUserByIdTest() throws Exception {
		
		ResultModel resultModel=new ResultModel();
		resultModel.setMessage("Success");
		
		String Url="/user/deleteUserById/7";
		
		Mockito.when(userService.deleteUser(Mockito.anyInt())).thenReturn("Success");
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.delete(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
		
		private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);//add
		final DateFormat df=new SimpleDateFormat("yyyy-MM-dd");//add
		objectMapper.setDateFormat(df);//add
		return objectMapper.writeValueAsString(object);
	}
	/*
	 * //add
	 * 
	 * @Test public void getUserByUserId() throws Exception{ //change Users
	 * mock1=new Users();
	 * 
	 * mock1.setId(8); mock1.setName("Poonam"); mock1.setSurname("Patil");
	 * mock1.setUserId("Neosoft_8"); mock1.setPassword("poonam123");
	 * mock1.setEmail("poonam@gmail.com"); mock1.setDob(null);
	 * mock1.setCity("Pune"); mock1.setContactNumber("987654321");
	 * mock1.setPinCode(425001); mock1.setDesignation("Java Developer");
	 * mock1.setRole("Developer"); mock1.setJoiningDate(null);
	 * 
	 * ResultModel resultModel=new ResultModel(); resultModel.setData(mock1);
	 * resultModel.setMessage("Success");
	 * 
	 * //String Url="/user/getByUserId/Neosoft_1";//original String
	 * Url="/user/getByUserId/Neosoft_8"; //String
	 * Url="/users/getByUserId?userId=Neosoft_8";
	 * 
	 * Mockito.when(userService.getById(Mockito.anyString())).thenReturn(mock1);
	 * 
	 * RequestBuilder requestBuilder=
	 * MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * MockHttpServletResponse response= result.getResponse();
	 * 
	 * String outputInJson=result.getResponse().getContentAsString();
	 * 
	 * String expectedJson=this.mapToJson(resultModel);
	 * 
	 * assertThat(outputInJson).isEqualTo(expectedJson);
	 * 
	 * assertEquals(HttpStatus.OK.value(), response.getStatus());
	 * 
	 * 
	 * }
	 
	 
	 
	 
	 * //add
	 * 
	 * @Test public void getUserByNameSurnamePincode() throws Exception{ //change
	 * Users mock1=new Users();
	 * 
	 * mock1.setId(2); mock1.setName("Prem"); mock1.setSurname("Barhate");
	 * mock1.setUserId("Neosoft_2"); mock1.setPassword("prem123");
	 * mock1.setEmail("prem@gmail.com"); mock1.setDob(null); mock1.setCity("Pune");
	 * mock1.setContactNumber("987654321"); mock1.setPinCode(423005);
	 * mock1.setDesignation("Java Developer"); mock1.setRole("Developer");
	 * mock1.setJoiningDate(null);
	 * 
	 * List<Users> usersList =new ArrayList<Users>(); usersList.add(mock1);
	 * 
	 * ResultModel resultModel=new ResultModel(); resultModel.setData(usersList);
	 * resultModel.setMessage("Success");
	 * 
	 * String Url=
	 * "/users/getByNameAndSurnameAndPincode?name=Prem&surname=Barhate@pinCode=423005";
	 * 
	 * Mockito.when(userService.getByNameAndSurnameAndPincode(Mockito.anyString(),
	 * Mockito.anyString(),Mockito.anyInt())).thenReturn(usersList);
	 * 
	 * RequestBuilder requestBuilder=
	 * MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * MockHttpServletResponse response= result.getResponse();
	 * 
	 * String outputInJson=result.getResponse().getContentAsString();
	 * 
	 * String expectedJson=this.mapToJson(resultModel);
	 * 
	 * assertThat(outputInJson).isEqualTo(expectedJson);
	 * 
	 * assertEquals(HttpStatus.OK.value(), response.getStatus());
	 * 
	 * 
	 * }
	 */
	
	/*
	 * //add
	 * 
	 * @Test public void getByDobAndJoiningDate() throws Exception{ //change Users
	 * mock1=new Users();
	 * 
	 * mock1.setId(5);//change mock1.setName("Piyush"); mock1.setSurname("Koli");
	 * mock1.setUserId("Neosoft_5");//change mock1.setPassword("piyush123");
	 * mock1.setEmail("piyush@gmail.com"); mock1.setDob(null);
	 * mock1.setCity("Pune"); mock1.setContactNumber("987654321");
	 * mock1.setPinCode(425001); mock1.setDesignation("Java Developer");
	 * mock1.setRole("Developer"); mock1.setJoiningDate(null);
	 * 
	 * //change Users mock2=new Users();
	 * 
	 * mock2.setId(4); mock2.setName("Punam"); mock2.setSurname("Patil");
	 * mock2.setUserId("Neosoft_4"); mock2.setPassword("punam123");
	 * mock2.setEmail("punam@gmail.com"); mock2.setDob(null); mock2.setCity("Pune");
	 * mock2.setContactNumber("987645621"); mock2.setPinCode(425001);
	 * mock2.setDesignation("Angular Developer"); mock2.setRole("Developer");
	 * mock2.setJoiningDate(null);
	 * 
	 * List<Users> userList=new ArrayList<Users>(); ResultModel resultModel = new
	 * ResultModel(); resultModel.setData(userList);
	 * resultModel.setMessage("Success"); userList.add(mock1); userList.add(mock2);
	 * 
	 * 
	 * String Url="/users/getByDobAndJoiningDate";
	 * 
	 * Mockito.when(userService.getByDobAndJoiningDate()).thenReturn(userList);
	 * 
	 * RequestBuilder requestBuilder=
	 * MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * MockHttpServletResponse response= result.getResponse();
	 * 
	 * String outputInJson=result.getResponse().getContentAsString();
	 * 
	 * String expectedJson=this.mapToJson(resultModel);
	 * 
	 * assertThat(outputInJson).isEqualTo(expectedJson);
	 * 
	 * assertEquals(HttpStatus.OK.value(), response.getStatus());
	 * 
	 * 
	 * }
	 */
	
	/*
	 * //add
	 * 
	 * @Test public void deleteByUserId() throws Exception {
	 * 
	 * ResultModel resultModel=new ResultModel(); resultModel.setMessage("Success");
	 * 
	 * String Url="/users/deleteUser?userId=Neosoft_7";
	 * 
	 * Mockito.when(userService.deleteUser(Mockito.anyString())).thenReturn(
	 * "Success");
	 * 
	 * RequestBuilder requestBuilder=
	 * MockMvcRequestBuilders.delete(Url).accept(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * MockHttpServletResponse response= result.getResponse();
	 * 
	 * String outputInJson=result.getResponse().getContentAsString();
	 * 
	 * String expectedJson=this.mapToJson(resultModel);
	 * 
	 * assertThat(outputInJson).isEqualTo(expectedJson);
	 * 
	 * assertEquals(HttpStatus.OK.value(), response.getStatus());
	 * 
	 * }
	 */
	/*
	 * //add
	 * 
	 * @Test public void deleteUserById() throws Exception {
	 * 
	 * ResultModel resultModel=new ResultModel(); resultModel.setMessage("Success");
	 * 
	 * String Url="/user/deleteUserById?id=1";
	 * 
	 * Mockito.when(userService.deleteUserById(Mockito.anyInt())).thenReturn(
	 * "Success");
	 * 
	 * RequestBuilder requestBuilder=
	 * MockMvcRequestBuilders.delete(Url).accept(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * MockHttpServletResponse response= result.getResponse();
	 * 
	 * String outputInJson=result.getResponse().getContentAsString();
	 * 
	 * String expectedJson=this.mapToJson(resultModel);
	 * 
	 * assertThat(outputInJson).isEqualTo(expectedJson);
	 * 
	 * assertEquals(HttpStatus.OK.value(), response.getStatus());
	 * 
	 * }
	 */
	

}
