package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.Books;
import entity.Students;
import service.BooksDAO;

public class BooksDAOImpl implements BooksDAO{

	@Override
	public List<Books> queryAllBooks() {
		// TODO Auto-generated method stub
				Transaction tx=null;
				List<Books> list=null;
				String hql="";
				try{
					Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
				    tx=session.beginTransaction();
					hql="from Books";
				    Query query=session.createQuery(hql);
				    list=query.list();  
				    tx.commit();
				    return list;
				}
				catch(Exception ex){
					ex.printStackTrace();
					tx.commit();
					return list;
				}
				finally{
					if(tx!=null){
					   tx=null;	
					}
				}
	}

	@Override
	public Books queryBooksBySid(String sid) {
		Transaction tx=null;
		Books  s=null;
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		    tx=session.beginTransaction();
		    s=(Books)session.get(Books.class,sid);
		    tx.commit();
		    return s;
		}
		catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return s;
		}
		finally{
			if(tx!=null){
			   tx=null;	
			}
		}
	}

	@Override
	public boolean addBooks(Books s) {
		// TODO Auto-generated method stub
		s.setSid(getNewSid());//����ѧ����ѧ��
		Transaction tx=null;
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		    tx=session.beginTransaction();
		    session.save(s);
		    tx.commit();
		    return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}
		finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	@Override
	public boolean updateBook(Books s) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		    tx=session.beginTransaction();
		    session.update(s);
		    tx.commit();
		    return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}
		finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	@Override
	public boolean deleteBook(String sid) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		String hql="";
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		    tx=session.beginTransaction();
		    Books s=(Books)session.get(Books.class, sid);
		    session.delete(s);
            tx.commit();
            return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			tx.commit(); 
			return false;
		}
		finally{
			if(tx!=null){
				tx=null;
			}
		}
	}
	//����ѧ�������
		private String  getNewSid(){
			Transaction tx=null;
			String hql="";
			String sid=null;
			try{
				Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			    tx=session.beginTransaction();
			    //��õ�ǰѧ���������
			    hql="select max(sid) from Books";
			    Query query=session.createQuery(hql);
			    sid=(String)query.uniqueResult();
			    if(sid==null||"".equals(sid)){
			    	//��һ��Ĭ�ϵ������
			    	sid="B0000001";
			    }else{
			    	String temp=sid.substring(1); //ȡ��7λ
			    	int i=Integer.parseInt(temp);//ת������
			    	i++;
			    	//�ڻ�ԭ���ַ���
			    	temp=String.valueOf(i);
			    	int len=temp.length();
			    	//�չ�7λ
			    	for(int j=0;j<7-len;j++){
			    		temp="0"+temp;
			    	}
			    	sid="B"+temp;
			    }
			    tx.commit();
			    return sid;
			}
			catch(Exception ex){
				ex.printStackTrace();
				tx.commit();
				return null;
			}
			finally{
				if(tx!=null){
					tx=null;
				}
			}
		}

}
