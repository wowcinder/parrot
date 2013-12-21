package com.voole.parrot.service.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IBaseDao {
	public Session getCurrSession();

	public void flush();

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	public <E extends Serializable> E refresh(E e, boolean force);
	
	public <E extends Serializable> E refresh(E e);
}
