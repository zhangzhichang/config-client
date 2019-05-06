package cn.sweetz.config.controller;

import cn.sweetz.config.entity.ConfigProperties;
import cn.sweetz.config.service.ConfigPropertiesService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/config/manager" })
public class ConfigPropertiesController {

	@Autowired
	private ConfigPropertiesService configPropertiesService;

	@PostMapping({ "/saveOrModify" })
	public Map<String, Object> insertOrUpdateConfigPeoperties(@RequestBody ConfigProperties configProperties) {
		Map<String, Object> result = initResult();
		try {
			int num = this.configPropertiesService.insertOrUpdateConfigPeoperties(configProperties);
			result.put("result", Boolean.valueOf(num > 0));
		} catch (Exception e) {
			setError(e,
					new StringBuilder().append(configProperties.getId() == null ? "添加" : "修改").append("失败！").toString(),
					result);
		}
		return result;
	}

	@GetMapping({ "/query/{application}/{profile}" })
	public Map<String, Object> queryByApplicationAndProfile(@PathVariable("application") String application,
			@PathVariable("profile") String profile) {
		Map<String, Object> result = initResult();
		try {
			List<ConfigProperties> list = this.configPropertiesService.selectByApplicationAndProfile(application, profile);
			result.put("result", list);
		} catch (Exception e) {
			setError(e, "查询失败！", result);
		}
		return result;
	}

	@GetMapping({ "/queryApplication" })
	public Map<String, Object> queryApplication() {
		Map<String, Object> result = initResult();
		try {
			List<String> list = this.configPropertiesService.selectApplication();
			result.put("result", list);
		} catch (Exception e) {
			setError(e, "查询失败！", result);
		}
		return result;
	}

	@GetMapping({ "/queryProfile/{application}" })
	public Map<String, Object> queryProfileByApplication(@PathVariable("application") String application) {
		Map<String, Object> result = initResult();
		try {
			List<String> list = this.configPropertiesService.selectProfileByApplication(application);
			result.put("result", list);
		} catch (Exception e) {
			setError(e, "查询失败！", result);
		}
		return result;
	}

	@PostMapping({ "/deleted/{id}" })
	public Map<String, Object> deleteConfigPropertiesById(@PathVariable("id") Integer id) {
		Map<String, Object> result = initResult();
		try {
			int num = this.configPropertiesService.deleteConfigPropertiesById(id);
			result.put("result", Boolean.valueOf(num > 0));
		} catch (Exception e) {
			setError(e, "删除失败！", result);
		}

		return result;
	}

	@PostMapping({ "/publish/{application}/{profile}" })
	public Map<String, Object> publishAllByApplicationAndProfile(@PathVariable("application") String application,
			@PathVariable("profile") String profile) {
		Map<String, Object> result = initResult();
		try {
			int num = this.configPropertiesService.publishAllByApplicationAndProfile(application, profile);
			result.put("result", Boolean.valueOf(num > 0));
		} catch (Exception e) {
			setError(e, "发布失败！", result);
		}

		return result;
	}

	private Map<String, Object> initResult() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "200");
		result.put("message", "ok");
		result.put("result", null);
		return result;
	}

	private void setError(Exception exception, String message, Map<String, Object> result) {
		exception.printStackTrace();
		result.put("code", "500");
		result.put("message", message);
		result.put("msg", exception.getMessage());
	}
}