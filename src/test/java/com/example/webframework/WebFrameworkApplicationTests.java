package com.example.webframework;

import com.example.webframework.model.Account;
import com.example.webframework.model.City;
import com.example.webframework.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebFrameworkApplicationTests {

	@Resource
	AccountRepository repository;

	@Test
	public void findTest(){

		repository.findById(6).ifPresent(System.out::println);
	}

	@Test
	public void addTest(){

		Account account = new Account();
		account.setMoney(999);
		account.setName("bird");

		account = repository.save(account);
		System.out.println("new account id: " + account.getId());

	}

	@Test
	public void deleteTest(){

		repository.deleteById(4);
	}

	@Test
	public void pageTest(){

		repository.findAll(PageRequest.of(1, 2)).forEach(System.out::println);  //直接分页

	}

	@Test
	public void findByIdAndName(){

		System.out.println(repository.findByIdAndName(3, "cat"));
	}


	@Test
	public void getInfoByCityTest(){

//		System.out.println(repository.getInfoByCity("shanghai"));

		List<Map<String, Object>> list = repository.getInfoByCity("shanghai");
		for(Map<String, Object> map:list){
//			System.out.println(map.toString());
			System.out.println(map.get("city"));
		}
	}


	@Test
	public void testWelcome() {
		assertEquals(HttpStatus.OK, new WebFrameworkApplication().welcome().getStatusCode());
	}


	@Test
	public void testModify(){
		repository.updateMoneyByName("dhk", 10000);
	}


	@Test
	public void testOneToOneJoin(){

		Account account = new Account();
		account.setMoney(1998);
		account.setName("monkey");
		City city = new City("monkey", "shanghai");
		account.setCity(city);
		account = repository.save(account);
		System.out.println("主键: " + account.getId() + " , 外键: " + account.getCity().getId());

	}



}
