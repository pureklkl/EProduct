package com.emusic.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

public class DaoImpl {
    @PersistenceContext
    private EntityManager em;
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
	
	public <T> List<T> getPagination(int first, int max, Class<T> _class, List<Order> order, HashMap<String, Object> restriction, int[] total) {
		StatelessSession ssSession  = em.unwrap(StatelessSession.class);
		Session session = (Session) ssSession;
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(_class);
		Root<T> root = query.from(_class);
		if(!restriction.isEmpty()) {
			Expression<Boolean> sqlRestrict = null;
			for(Map.Entry<String, Object> e : restriction.entrySet()) {
				Expression<Boolean> nextRestrict =  builder.equal(root.get(e.getKey()), e.getValue());
				sqlRestrict = sqlRestrict == null ? nextRestrict : builder.and(sqlRestrict, nextRestrict);
			}
			query.select(root).where(sqlRestrict).orderBy(order);
		} else {
			query.select(root).orderBy(order);
		}
		
		ScrollableResults  scrollableResults = session.createQuery(query).setReadOnly(true).scroll(ScrollMode.FORWARD_ONLY);
		List<T> result = new ArrayList<T>();
		if(scrollableResults.setRowNumber(first)) {
			while(scrollableResults.next() && result.size() < max) {
				result.add((T)(scrollableResults.get()[0]));
			}
		}
		if(scrollableResults.last()) {
			total[0] = scrollableResults.getRowNumber();
		}
		return result;
	}
}
