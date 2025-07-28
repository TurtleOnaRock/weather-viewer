package com.turtleOnARock.weatherViewer.servicies;

import com.turtleOnARock.weatherViewer.DAO.SessionRepository;
import com.turtleOnARock.weatherViewer.entities.AppSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    public AppSession getSession(int sessionId){
        return sessionRepository.getById(sessionId);
    }

    public List<AppSession> getAllSessions() {
        return sessionRepository.getAll();
    }
}
