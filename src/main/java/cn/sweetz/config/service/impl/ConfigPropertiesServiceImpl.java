package cn.sweetz.config.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.sweetz.config.entity.ConfigProperties;
import cn.sweetz.config.service.ConfigPropertiesService;

@Service
@SuppressWarnings({ "unchecked", "rawtypes","unused" })
public class ConfigPropertiesServiceImpl implements ConfigPropertiesService {
	
	private static final String SQL_CLOUMN = " id , pkey , pvalue , before_value as beforeValue , application , profile , label , is_publish as isPublish , create_time as createTime , update_time as updateTime , is_deleted as isDeleted";
	private static RowMapper<ConfigProperties> rowMapper = new BeanPropertyRowMapper(ConfigProperties.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertOrUpdateConfigPeoperties(ConfigProperties configProperties) {
		if ((configProperties == null) || (StringUtils.isEmpty(configProperties.getApplication()))
				|| (StringUtils.isEmpty(configProperties.getProfile()))
				|| (StringUtils.isEmpty(configProperties.getPkey()))
				|| (StringUtils.isEmpty(configProperties.getPvalue()))) {
			return 0;
		}

		String sql = "";
		int num = 0;
		if ((configProperties.getId() != null) && (configProperties.getId().intValue() != 0)) {
			String querySql = "select  id , pkey , pvalue , before_value as beforeValue , application , profile , label , is_publish as isPublish , create_time as createTime , update_time as updateTime , is_deleted as isDeleted from conifg_properties where id = "
					+ configProperties.getId();
			ConfigProperties old = (ConfigProperties) this.jdbcTemplate.queryForObject(querySql, rowMapper);
			if (old != null) {
				sql = "update conifg_properties set pkey = ? , pvalue = ? ,  before_value = ? , application = ? , profile = ? , label = ? , is_publish = ? , update_time = ? where id = ?";

				num = this.jdbcTemplate.update(sql,
						new Object[] { configProperties.getPkey(), configProperties.getPvalue(), old.getPvalue(),
								configProperties.getApplication(), configProperties.getProfile(),
								configProperties.getLabel(), "0", new Date(), old.getId() });
			}
		} else {
			sql = "insert into conifg_properties(pkey , pvalue ,application ,profile,label,is_publish,create_time ) values( ? , ? , ? ,? ,  ? ,? , ? )";

			num = this.jdbcTemplate.update(sql,
					new Object[] { configProperties.getPkey(), configProperties.getPvalue(),
							configProperties.getApplication(), configProperties.getProfile(),
							configProperties.getLabel(), "0", new Date() });
		}

		return num;
	}

	public List<ConfigProperties> selectByApplicationAndProfile(String application, String profile) {
		String sql = "select  id , pkey , pvalue , before_value as beforeValue , application , profile , label , is_publish as isPublish , create_time as createTime , update_time as updateTime , is_deleted as isDeleted from conifg_properties where application = '"
				+ application + "' and profile = '" + profile + "' and is_deleted = '0'";

		return this.jdbcTemplate.query(sql, rowMapper);
	}

	public int deleteConfigPropertiesById(Integer id) {
		String querySql = "select  id , pkey , pvalue , before_value as beforeValue , application , profile , label , is_publish as isPublish , create_time as createTime , update_time as updateTime , is_deleted as isDeleted from conifg_properties where id = "
				+ id;

		ConfigProperties configProperties = (ConfigProperties) this.jdbcTemplate.queryForObject(querySql, rowMapper);
		if (configProperties != null) {
			String updateSql = "update conifg_properties set is_deleted = '1' where id = " + id;

			return this.jdbcTemplate.update(updateSql);
		}

		return 0;
	}

	public int publishAllByApplicationAndProfile(String application, String profile) {
		String updateSql = "update conifg_properties set is_publish = '1' where application = '" + application
				+ "' and profile = '" + profile + "'";
		return this.jdbcTemplate.update(updateSql);
	}

	public List<String> selectApplication() {
		String sql = "select distinct application from conifg_properties where is_deleted = '0'";

		return this.jdbcTemplate.queryForList(sql, String.class);
	}

	public List<String> selectProfileByApplication(String application) {
		String sql = "select distinct profile from conifg_properties where application = '" + application
				+ "' and is_deleted = '0'";

		return this.jdbcTemplate.queryForList(sql, String.class);

	}
}