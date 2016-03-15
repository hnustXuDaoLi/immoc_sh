package action;

import java.text.SimpleDateFormat;
import java.util.List;

import service.BooksDAO;
import service.impl.BooksDAOImpl;
import entity.Books;

public class BooksAction extends SuperAction{
	private static final long serialVersionUID = 1L;
	//��ѯ����ѧ���Ķ���
		public String query(){
			BooksDAO sdao=new BooksDAOImpl();
			List<Books> list=sdao.queryAllBooks();
			//�Ž�session��
			if(list!=null&&list.size()>0){
				session.setAttribute("books_list", list);//!!!!
			}
			return "query_success";
		}
		//ɾ��ѧ���Ķ���
		public String delete(){
			BooksDAO sdao=new BooksDAOImpl();
			String sid=request.getParameter("sid");
			sdao.deleteBook(sid);//����ɾ������
			return "delete_success";
		}
		//���ѧ���Ķ���
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
		//�޸�ѧ�����϶���
		public String modify(){
			//��ô��ݹ�����ѧ�����
			String sid=request.getParameter("sid");
			BooksDAO sdao=new BooksDAOImpl();
			Books s=sdao.queryBooksBySid(sid);
			//�����ڻỰ��
			session.setAttribute("modify_books", s);//!!!!!
			return "modify_success";
		}
		//�����޸ĺ��ѧ�����϶���
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
