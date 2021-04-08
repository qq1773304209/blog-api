package com.jonm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jonm.entity.CityVisitor;
import com.jonm.dao.CityVisitorMapper;
import com.jonm.service.CityVisitorService;

/**
 * @Description: 城市访客数量统计业务层实现
 * @Author: Naccl
 * @Date: 2021-02-26
 */
@Service
public class CityVisitorServiceImpl implements CityVisitorService {
	@Autowired
	CityVisitorMapper cityVisitorMapper;

	@Override
	public void saveCityVisitor(CityVisitor cityVisitor) {
		cityVisitorMapper.saveCityVisitor(cityVisitor);
	}
}
