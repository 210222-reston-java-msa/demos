package com.revature.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static Transaction transaction = null;

	@Override
	public boolean addReimbursement(Reimbursement reimb) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			session.save(reimb);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				return false;
			}
		}
		return true;
	}

	@Override
	public Reimbursement getReimb(int reimbId) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Reimbursement reimb = session.get(Reimbursement.class, reimbId);

			return reimb;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			TypedQuery<Reimbursement> query = session.createQuery("from Reimbursement ORDER BY reimbId",
					Reimbursement.class);
			List<Reimbursement> reimbs = query.getResultList();

			return reimbs;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements(int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String sql = "SELECT * FROM reimbursement WHERE reimbauthor= :userId ORDER BY reimbid";
			List<Reimbursement> reimbs = session.createNativeQuery(sql, Reimbursement.class)
					.setParameter("userId", userId).list();

			return reimbs;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> getAllReimbursementsForChart(int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			List<Reimbursement> reimbs = new ArrayList<>();

			String sql = "SELECT reimbid, reimbamount, reimbstatusid FROM reimbursement WHERE reimbauthor= :userId ORDER BY reimbid";
			List<Object[]> lists = session.createNativeQuery(sql).setParameter("userId", userId).list();

			for (Object[] list : lists) {
				int reimbId = (Integer) list[0];
				double reimbAmount = (Double) list[1];
				int reimbStatusId = (Integer) list[2];
				
				Reimbursement reimb = new Reimbursement(reimbId, reimbAmount, new ReimbStatus(reimbStatusId));
				reimbs.add(reimb);
			}

			return reimbs;
		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> getAllReimbursementsForChart() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			List<Reimbursement> reimbs = new ArrayList<>();

			String sql = "SELECT reimbid, reimbamount, reimbstatusid FROM reimbursement ORDER BY reimbid";
			List<Object[]> lists = session.createNativeQuery(sql).list();

			for (Object[] list : lists) {
				int reimbId = (Integer) list[0];
				double reimbAmount = (Double) list[1];
				int reimbStatusId = (Integer) list[2];
				
				Reimbursement reimb = new Reimbursement(reimbId, reimbAmount, new ReimbStatus(reimbStatusId));
				reimbs.add(reimb);
			}

			return reimbs;
		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbs(int statusId) { // Manager
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			System.out.println("here it statusid" + statusId);
			String sql = "SELECT * FROM reimbursement WHERE reimbstatusid= :statusId ORDER BY reimbid";
			List<Reimbursement> reimbs = session.createNativeQuery(sql, Reimbursement.class)
					.setParameter("statusId", statusId).list();

			return reimbs;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbs(int userId, int statusId) { // Employee
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String sql = "SELECT * FROM reimbursement WHERE reimbauthor= :userId AND reimbstatusid= :statusId ORDER BY reimbid";
			List<Reimbursement> reimbs = session.createNativeQuery(sql, Reimbursement.class)
					.setParameter("userId", userId).setParameter("statusId", statusId).list();
			return reimbs;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@Override
	public List<Reimbursement> getResolvedReimbs(int statusOneId, int statusTwoId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			System.out.println("here it statusid" + statusOneId);
			System.out.println("here it statusid" + statusTwoId);
			String sql = "SELECT * FROM reimbursement WHERE reimbstatusid= :statusOneId OR reimbstatusid= :statusTwoId ORDER BY reimbid";
			List<Reimbursement> reimbs = session.createNativeQuery(sql, Reimbursement.class)
					.setParameter("statusOneId", statusOneId).setParameter("statusTwoId", statusTwoId).list();

			return reimbs;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@Override
	public List<Reimbursement> getResolvedReimbs(int userId, int statusOneId, int statusTwoId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			System.out.println("here it statusid" + statusOneId);
			System.out.println("here it statusid" + statusTwoId);
			String sql = "SELECT * FROM reimbursement WHERE (reimbstatusid= :statusOneId OR reimbstatusid= :statusTwoId) AND reimbauthor= :userId ORDER BY reimbid";
			List<Reimbursement> reimbs = session.createNativeQuery(sql, Reimbursement.class)
					.setParameter("userId", userId).setParameter("statusOneId", statusOneId)
					.setParameter("statusTwoId", statusTwoId).list();

			return reimbs;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean approveReimb(int reimbId, int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			String sql = "UPDATE reimbursement SET reimbstatusid= :reimbstatusid, reimbresolved= :reimbresolved, reimbresolver= :reimbresolver WHERE reimbid= :reimbid";

			Query<Reimbursement> query = session.createNativeQuery(sql);
			int statusId = 2;
			query.setParameter("reimbstatusid", statusId);
			query.setParameter("reimbresolved", LocalDateTime.now());
			query.setParameter("reimbresolver", userId);
			query.setParameter("reimbid", reimbId);

			int count = query.executeUpdate();

			transaction.commit();

			if (count > 0)
				return true;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean denyReimb(int reimbId, int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			String sql = "UPDATE reimbursement SET reimbstatusid= :reimbstatusid, reimbresolved= :reimbresolved, reimbresolver= :reimbresolver WHERE reimbid= :reimbid";

			Query<Reimbursement> query = session.createNativeQuery(sql);
			int statusId = 3;
			query.setParameter("reimbstatusid", statusId);
			query.setParameter("reimbresolved", LocalDateTime.now());
			query.setParameter("reimbresolver", userId);
			query.setParameter("reimbid", reimbId);

			int count = query.executeUpdate();

			transaction.commit();

			if (count > 0)
				return true;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return false;
	}

	@Override
	public Reimbursement getReceipt(int reimbId) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			TypedQuery<Reimbursement> query = session.createQuery("from Reimbursement WHERE reimbId= :reimbId",
					Reimbursement.class);
			query.setParameter("reimbId", reimbId);
			Reimbursement reimb = query.getSingleResult();

			return reimb;

		} catch (Exception e) {
			System.out.println("Connection failed");
		}

		return null;
	}

}
