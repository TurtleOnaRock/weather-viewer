package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.exceptions.DataBaseException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataSource {

    private static final SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch(Throwable e){
            throw new DataBaseException("Some problem with connection to database");
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
