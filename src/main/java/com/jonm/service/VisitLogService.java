package com.jonm.service;

import org.springframework.scheduling.annotation.Async;
import com.jonm.entity.VisitLog;
import com.jonm.model.dto.VisitLogUuidTime;

import java.util.List;

public interface VisitLogService {
	List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

	List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

	@Async
	void saveVisitLog(VisitLog log);

	void deleteVisitLogById(Long id);
}
