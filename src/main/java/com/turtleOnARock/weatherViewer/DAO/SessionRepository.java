package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.entities.AppSession;

public interface SessionRepository {
    void save(AppSession session);

    AppSession getById(int sessionId);

    void delete(AppSession appSession);
}
