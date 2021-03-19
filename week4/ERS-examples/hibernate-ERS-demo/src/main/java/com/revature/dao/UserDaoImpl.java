package com.revature.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	private static Transaction transaction = null;
	
	@Override
	public boolean addUser(User user) {
				
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			session.save(user);
			System.out.println("Deneme");
			transaction.commit();
			
		} catch (Exception e) {
			System.out.println(e);
			if(transaction != null) {
				transaction.rollback();
				return false;
			}
		}	
		System.out.println("Hello world");
		return true;
	}
	
	@Override
	public List<User> getAllUsers() {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
					
			TypedQuery<User> query = session.createQuery("from User ORDER BY userId", User.class);
			List<User> users = query.getResultList();
			
			return users;
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
		return null;
	}


	@Override
	public User getUser(String username, String password) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	
		
			String sql = "from User WHERE username= : username AND password= : password";
			
			TypedQuery<User> query = session.createQuery(sql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
					
			try {
				
				User user = query.getSingleResult();

				
				return user;
			} catch (Exception e) {
				return null;
			}		
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}	
		
		return null;
		
	}
	
	
	@Override
	public User getUser(int userId) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	
		
			String sql = "from User WHERE userId= : userId";
			
			TypedQuery<User> query = session.createQuery(sql, User.class);
			query.setParameter("userId", userId);
			
			User user = query.getSingleResult();
			
			return user;
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}	
		
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateUser(User user) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
					
			transaction = session.beginTransaction();
			System.out.println("Database: " + user);
			String sql = "UPDATE User SET username= :username, password= :password, firstName= :firstName"
					+ ", lastName= :lastName, email= :email WHERE userId= :userId";
					
			
			Query<User> query = session.createQuery(sql);
			query.setParameter("userId", user.getUserId());
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			query.setParameter("firstName", user.getFirstName());
			query.setParameter("lastName", user.getLastName());
			query.setParameter("email", user.getEmail());
			
			int count = query.executeUpdate();
			
			transaction.commit();
			
			if(count > 0)
				return true;
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}	
		
		return false;
	}


	@Override
	public String getEmail(int reimbId) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			String sql = "SELECT * FROM users a INNER JOIN reimbursement b ON a.userid = b.reimbauthor WHERE b.reimbId= :reimbId";
					
			User user = session.createNativeQuery(sql, User.class)
						.setParameter("reimbId", reimbId)
						.getSingleResult();
			
						
			return user.getEmail();
			
		} catch (Exception e) {
			System.out.println("Connection failed");
		}
		
		return null;
	}

	@Override
	public boolean isAvailableEmail(String email) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			

			String sql = "from User WHERE email= : email";
			
			TypedQuery<User> query = session.createQuery(sql, User.class);
			query.setParameter("email", email);
			
			try {
				query.getSingleResult();	
				return false;
			} catch (NoResultException e) {
				return true;
			}
			
		} catch (Exception e) {

			if(transaction != null) {
				transaction.rollback();
			}
		}	
		
		return true;
	}

	@Override
	public boolean isAvailableUsername(String username) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			
			String sql = "from User WHERE username= :username";
			
			TypedQuery<User> query = session.createQuery(sql, User.class);
			query.setParameter("username", username);
			
			try {
				query.getSingleResult();	
				return false;
			} catch (NoResultException e) {
				return true;
			}
			
		} catch (Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
		}			
		return true;
	}

	@Override
	public User getUser(String email) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			
			String sql = "from User WHERE email= :email";
			
			TypedQuery<User> query = session.createQuery(sql, User.class);
			query.setParameter("email", email);
			
			try {
				User user = query.getSingleResult();	
				return user;
			} catch (NoResultException e) {
				return null;
			}
			
		} catch (Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
		}			
		return null;
	}


	

}
