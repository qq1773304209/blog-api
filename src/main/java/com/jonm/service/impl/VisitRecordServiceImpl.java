package com.jonm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jonm.entity.VisitRecord;
import com.jonm.dao.VisitRecordMapper;
import com.jonm.service.VisitRecordService;

/**
 * @Description: 访问记录业务层实现
 * @Author: Naccl
 * @Date: 2021-02-23
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {
	@Autowired
	VisitRecordMapper visitRecordMapper;

	@Override
	public void saveVisitRecord(VisitRecord visitRecord) {
		visitRecordMapper.saveVisitRecord(visitRecord);
	}
}
