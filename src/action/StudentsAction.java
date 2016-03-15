package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.Books;
import entity.Students;
import service.BooksDAO;
import service.StudentsDAO;
import service.impl.BooksDAOImpl;
import service.impl.StudentsDAOImpl;

//学生Action类
public class StudentsAction extends SuperAction {

	private static final long serialVersionUID = 1L;
	//查询所有学生的动作
	public String query(){
		StudentsDAO sdao=new StudentsDAOImpl();
		List<Students> list=sdao.queryAllStudents();
		//放进session中
		if(list!=null&&list.size()>0){
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}
	//删除学生的动作
	public String delete() throws IOException{
		StudentsDAO sdao=new StudentsDAOImpl();
		String sid=request.getParameter("sid");
		Students s=sdao.queryStudentsBySid(sid);
		String bid = s.getAddress();
		Books b=new Books();
	    BooksDAO bdao=new BooksDAOImpl();
		List<Books> list=bdao.queryAllBooks();
		boolean flag = false;
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getSid().equals(bid)){
				flag = true;
				b = bdao.queryBooksBySid(bid);
				int now = Integer.parseInt(b.getTotal())+1;
				b.setTotal(""+now);
				bdao.updateBook(b);
				break;
			}	
		}
		if(flag){
			sdao.deleteStudent(sid);//调用删除方法
		}else{
			popwindow("书籍已被管理员删除，请注意！");
		}
		return "delete_success";
	}
	//添加学生的动作
	public String add() throws Exception{
		Students s=new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		String bid = request.getParameter("address");
		s.setAddress(bid);
		
		Books b=new Books();
	    BooksDAO bdao=new BooksDAOImpl();
		List<Books> list=bdao.queryAllBooks();
		boolean flag = false;
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getSid().equals(bid)){
				flag = true;
				b = bdao.queryBooksBySid(bid);
				b.setLoandate(new Date());
				int now = Integer.parseInt(b.getTotal())-1;
				if(now<0){
					popwindow("书籍已借完，请重新填写！");
					return "add_success";
				}
				b.setTotal(""+now);
				bdao.updateBook(b);
				break;
			}	
		}
		if(flag){
			StudentsDAO sdao=new StudentsDAOImpl();
			sdao.addStudents(s);
			return "add_success";
		}else{
		    popwindow("没有本书籍，请重新填写！");
			return "query_success";
		}
	}
	public void popwindow(String word) throws IOException{
		response.setContentType("text/html; charset=UTF-8"); //转码
	    PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>");
	    out.println("alert('"+word+"');");
	    out.println("history.back();");
	    out.println("</script>");
		
	}
	//修改学生资料动作
	public String modify(){
		//获得传递过来的学生编号
		String sid=request.getParameter("sid");
		StudentsDAO sdao=new StudentsDAOImpl();
		Students s=sdao.queryStudentsBySid(sid);
		//保存在会话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	//保存修改后的学生资料动作
	public String save() throws Exception{
		Students s=new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao=new StudentsDAOImpl();
		sdao.updateStudent(s);
		return "save_success";
	}
   
}
