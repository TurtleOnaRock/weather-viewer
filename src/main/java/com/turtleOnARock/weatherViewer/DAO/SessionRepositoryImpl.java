package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.entities.AppSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepositoryImpl implements SessionRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public SessionRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(AppSession appSession) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(appSession);
    }

    @Override
    public AppSession getById(int sessionId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AppSession.class, sessionId);
    }

    @Override
    public void delete(AppSession appSession) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(appSession);
    }
}
