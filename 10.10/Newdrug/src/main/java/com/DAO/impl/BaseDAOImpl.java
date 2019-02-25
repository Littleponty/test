package com.DAO.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.BaseDAO;
import com.DO.PagerInfo;
import com.util.PagerInfoUtil;

@Repository("baseDAO")
@SuppressWarnings("all")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private SessionFactory sessionFactory;
	
	private Class<T> entityClass; 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
			//return sessionFactory.openSession();
			return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}
	@Transactional
	public void update(T o) {
		this.getCurrentSession().update(o);
	}
	
	public void merge(T o) {
		this.getCurrentSession().merge(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}
	
	public T get(T o, Long id){
		return  (T) this.getCurrentSession().get(entityClass, id);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).setCacheable(true).list();
	}

	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}
	
	public List<T> find(String hql, List<Object> param, com.util.PagerInfo pagerInfo) {
		hql = PagerInfoUtil.addSearchCondition(hql, pagerInfo);
		hql = PagerInfoUtil.afterOrderBy(hql, pagerInfo);
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if(param == null){
			param = new ArrayList<Object>();
		}
		if(pagerInfo.getSearchValue() != null && !pagerInfo.getSearchValue().equals("")
				&& pagerInfo.getSearchColumns() != null && pagerInfo.getSearchColumns().length>0){
			for(String str : pagerInfo.getSearchColumns()){
				param.add("%"+pagerInfo.getSearchValue()+"%");
			}
		}
		for (int i = 0; i < param.size(); i++) {
			q.setParameter(i, param.get(i));
		}
		return q.setFirstResult(pagerInfo.getStart()).setMaxResults(pagerInfo.getLength()).list();
	}

	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	public List<T> find(String hql,Integer page,Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
	 
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql, Object[] param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).setCacheable(true).uniqueResult();
	}

	public Long count(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}
	
	public Long count(String hql, List<Object> param, com.util.PagerInfo pagerInfo) {
		hql = PagerInfoUtil.addSearchCondition(hql, pagerInfo);
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if(param == null){
			param = new ArrayList<Object>();
		}
		if(pagerInfo.getSearchValue() != null && !pagerInfo.getSearchValue().equals("")
				&& pagerInfo.getSearchColumns() != null && pagerInfo.getSearchColumns().length>0){
			for(String str : pagerInfo.getSearchColumns()){
				param.add("%"+pagerInfo.getSearchValue()+"%");
			}
		}
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql).setCacheable(true);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}
	
	
	/**
	 * 自动开启hibernate session,此方法为初始化数据使用
	 * @param hql
	 * @return
	 */
	public List<T> findByOpenSession(String hql){
		Session session = sessionFactory.openSession();
		List<T> list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List<T> find(String hql, List<Object> param, PagerInfo pagerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String hql, List<Object> param, PagerInfo pagerInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}