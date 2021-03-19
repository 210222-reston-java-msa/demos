package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.DAO.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private static Logger logger = LogManager.getLogger(ReimbursementService.class);
	static ReimbursementDao rd = new ReimbursementDao();

	public Reimbursement getReimbursementById(int id) {
		Reimbursement r = rd.findById(id);
		return r;
	}
	
	public List<Reimbursement> getAllReimbursements(){
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		reimbs = rd.findAll();		
		return reimbs;
	}
	
	public List<Reimbursement> getAllPendingReimbursements(){
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		reimbs = rd.findAllPending();
		return reimbs;
	}
	
	public List<Reimbursement> getAllPastReimbursements(){
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		reimbs = rd.findAllPast();
		return reimbs;
	}
	
	public List<Reimbursement> getReimbursementsByAuthor(int author){
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		reimbs= rd.findByAuthorId(author);
		return reimbs;
	}
	
	public List<Reimbursement> getReimbursementsByResolver(int resolver){
		List<Reimbursement> reimbs = rd.findByResolverId(resolver);
		return reimbs;
	}
	
	public List<Reimbursement> getPendingReimbursementsByAuthor(int author){
		List<Reimbursement> all = rd.findByAuthorId(author);
		List<Reimbursement> pending = new ArrayList<Reimbursement>();
		Reimbursement r;
		Iterator<Reimbursement> itr = all.iterator();
		while(itr.hasNext()) {
			r = itr.next();
			if (r.getrStatus() == 1) {
				pending.add(r);
			}
		}		
		return pending;
	}
	
	public List<Reimbursement> getPastReimbursementsByAuthor(int author){
		List<Reimbursement> all = rd.findByAuthorId(author);
		List<Reimbursement> past = new ArrayList<Reimbursement>();
		Reimbursement r;
		Iterator<Reimbursement> itr = all.iterator();
		while(itr.hasNext()) {
			r = itr.next();
			if (r.getrStatus() == 2 || r.getrStatus() == 3) {	// If approved or denied
				past.add(r);
			}
		}
		logger.debug(past);
		return past;
	}
	
	public List<Reimbursement> getResolvedByManager(int resolver){
		List<Reimbursement> resolved = rd.findByResolverId(resolver);
		return resolved;
	}
	
	public Reimbursement submit(Reimbursement r) {
		rd.create(r);
		return r;
	}
	
	public Reimbursement update(Reimbursement r) {
		rd.update(r);
		return r;
	}

}
