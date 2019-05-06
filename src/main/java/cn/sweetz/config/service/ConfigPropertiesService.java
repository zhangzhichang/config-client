package cn.sweetz.config.service;

import cn.sweetz.config.entity.ConfigProperties;
import java.util.List;

public interface ConfigPropertiesService {
	public abstract int insertOrUpdateConfigPeoperties(ConfigProperties paramConfigProperties);

	public abstract List<ConfigProperties> selectByApplicationAndProfile(String paramString1, String paramString2);

	public abstract int deleteConfigPropertiesById(Integer paramInteger);

	public abstract int publishAllByApplicationAndProfile(String paramString1, String paramString2);

	public abstract List<String> selectApplication();

	public abstract List<String> selectProfileByApplication(String paramString);
}