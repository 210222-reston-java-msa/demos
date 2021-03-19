package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.UserRole;
import com.revature.util.HibernateUtil;

public class Driver {

	private static Transaction transaction = null;

	public static void main(String[] args) {
		
		//setDatabase();		
	}

	public static void setDatabase() {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			
			UserRole userRole1 = new UserRole(1, "Manager");
			UserRole userRole2 = new UserRole(2, "Employee");
			
			ReimbStatus reimbStatus1 = new ReimbStatus(1, "Pending");
			ReimbStatus reimbStatus2 = new ReimbStatus(2, "Approved");
			ReimbStatus reimbStatus3 = new ReimbStatus(3, "Denied");

			ReimbType reimbType2 = new ReimbType(1, "Business Expenses");
			ReimbType reimbType1 = new ReimbType(2, "Travel Expenses");
			ReimbType reimbType3 = new ReimbType(3, "Medical Expenses");

			transaction = session.beginTransaction();

			session.save(userRole1);
			session.save(userRole2);			
			
			session.save(reimbStatus1);
			session.save(reimbStatus2);
			session.save(reimbStatus3);

			session.save(reimbType1);
			session.save(reimbType2);
			session.save(reimbType3);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}
}
