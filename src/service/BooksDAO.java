package service;

import java.util.List;

import entity.Books;

public interface BooksDAO {
	//��ѯ�����鼮����
		public List<Books> queryAllBooks();
		
		//�����鼮��Ų�ѯ�鼮����
		public Books queryBooksBySid(String sid);
		
		//����鼮����
		public boolean addBooks(Books s);
		
		//�޸��鼮����
		public boolean updateBook(Books s);
		
		//ɾ���鼮����
		public boolean deleteBook(String sid);

}
