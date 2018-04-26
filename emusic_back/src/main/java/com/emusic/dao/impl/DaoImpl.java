package com.emusic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.query.Query;

import com.emusic.dao.Dao;

public class DaoImpl implements Dao {
    @PersistenceContext
    private EntityManager em;
    
	public EntityManager getEntityManager() {
		return em;
	}

	public Session getSession() {
		return em.unwrap(Session.class);			
	}
	
	public <T, V> T getUniqueField(String field, V value, Class<T> _class, Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(_class);
		Root<T> root = query.from(_class);
		query.select(root).where(builder.equal(root.get(field), value));
		
		List<T> vals = session.createQuery(query).getResultList();
		
		return vals.isEmpty() ? null : vals.get(0);
	}
	
	public <T> List<T> getPagination(int page, int max, Class<T> _class, ScrollableResults  scrollableResults, long[] total) {
		List<T> result = new ArrayList<T>();
		if(scrollableResults.setRowNumber(page * max)) {
			result.add((T)(scrollableResults.get()[0]));
			while(scrollableResults.next() && result.size() < max) {
				result.add((T)(scrollableResults.get()[0]));
			}
		}
		if(scrollableResults.last()) {
			int totalRow = scrollableResults.getRowNumber();
			total[0] = (totalRow / max) + (totalRow % max == 0 ? 0 : 1);
		}
		return result;
	}
	
	public <T> List<T> getPagination(int page, int max, Class<T> _class, Query<T> hquery, long[] total) {
		ScrollableResults  scrollableResults = hquery.setReadOnly(true).scroll(ScrollMode.FORWARD_ONLY);
		return getPagination(page, max, _class, scrollableResults, total);
	}
	
	public <T> List<T> getPagination(int page, int max, Class<T> _class, CriteriaQuery<T> query, long[] total) {
		TypedQuery<T> typedQuery = em.createQuery(query);
		Query<T> hquery = (Query<T>)typedQuery.unwrap(Query.class);
		return getPagination(page, max, _class, hquery, total);
	}
}
