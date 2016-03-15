package service.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import entity.Books;
import entity.Students;

import service.BooksDAO;
import service.StudentsDAO;


public class TestStudentsDAOImpl {
	@Test
	public void testQueryAllStudents(){
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testgetNewSid(){
		StudentsDAOImpl sdao = new StudentsDAOImpl();
		//System.out.println(sdao.getNewSid());
	}
	
	@Test
	public void testAddStudents(){
		Students s = new Students();
		s.setSname("doli");
		s.setGender("ÄÐ");
		s.setBirthday(new Date());
		s.setAddress("Õã½­");
		StudentsDAO sdao = new StudentsDAOImpl();
		Assert.assertEquals(true, sdao.addStudents(s));
	}
	@Test
	public void testAddBooks(){
		Books s = new Books();
		s.setSname("¼ÆËã»úÍøÂç");
		s.setLoandate(new Date());
		s.setTotal("10");
		BooksDAO sdao = new BooksDAOImpl();
		Assert.assertEquals(true, sdao.addBooks(s));
	}
}
