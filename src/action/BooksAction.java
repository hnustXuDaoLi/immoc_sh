package action;

import java.text.SimpleDateFormat;
import java.util.List;

import service.BooksDAO;
import service.impl.BooksDAOImpl;
import entity.Books;

public class BooksAction extends SuperAction{
	private static final long serialVersionUID = 1L;
	//查询所有学生的动作
		public String query(){
			BooksDAO sdao=new BooksDAOImpl();
			List<Books> list=sdao.queryAllBooks();
			//放进session中
			if(list!=null&&list.size()>0){
				session.setAttribute("books_list", list);//!!!!
			}
			return "query_success";
		}
		//删除学生的动作
		public String delete(){
			BooksDAO sdao=new BooksDAOImpl();
			String sid=request.getParameter("sid");
			sdao.deleteBook(sid);//调用删除方法
			return "delete_success";
		}
		//添加学生的动作
		public String add() throws Exception{
			Books s=new Books();
			s.setSname(request.getParameter("sname"));
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			s.setLoandate(sdf.parse(request.getParameter("loandate")));//!!!!
			s.setTotal(request.getParameter("total"));//!!!!
			BooksDAO sdao=new BooksDAOImpl();
			sdao.addBooks(s);
			return "add_success";
		}
		//修改学生资料动作
		public String modify(){
			//获得传递过来的学生编号
			String sid=request.getParameter("sid");
			BooksDAO sdao=new BooksDAOImpl();
			Books s=sdao.queryBooksBySid(sid);
			//保存在会话中
			session.setAttribute("modify_books", s);//!!!!!
			return "modify_success";
		}
		//保存修改后的学生资料动作
		public String save() throws Exception{
			Books s=new Books();
			s.setSid(request.getParameter("sid"));
			s.setSname(request.getParameter("sname"));
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			s.setLoandate(sdf.parse(request.getParameter("loandate")));//!!!
			s.setTotal(request.getParameter("total"));//!!!
			BooksDAO sdao=new BooksDAOImpl();
			sdao.updateBook(s);
			return "save_success";
		}
}
