package com.jonm.service;

import com.jonm.entity.SiteSetting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SiteSettingService {
	Map<String, List<SiteSetting>> getList();

	Map<String, Object> getSiteInfo();

	void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);
}
