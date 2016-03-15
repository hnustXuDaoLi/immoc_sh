package service;

import java.util.List;

import entity.Books;

public interface BooksDAO {
	//查询所有书籍资料
		public List<Books> queryAllBooks();
		
		//根据书籍编号查询书籍资料
		public Books queryBooksBySid(String sid);
		
		//添加书籍资料
		public boolean addBooks(Books s);
		
		//修改书籍资料
		public boolean updateBook(Books s);
		
		//删除书籍资料
		public boolean deleteBook(String sid);

}
