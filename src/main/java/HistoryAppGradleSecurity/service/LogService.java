package HistoryAppGradleSecurity.service;

import HistoryAppGradleSecurity.model.service.LogServiceModel;

import java.util.List;

public interface LogService {

    void createLog(String action,Long articleId);

    List<LogServiceModel> findAllLogs();

    void deleteOldLogs();
}
