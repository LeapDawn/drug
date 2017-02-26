package drug.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import drug.dto.AjaxResult;
import drug.dto.pageModel.PAnimal;
import drug.dto.pageModel.PageResultModel;
import drug.model.DetailAnimal;
import drug.service.AnimalService;


@Controller
@RequestMapping("/animal")
public class AnimalAction extends BaseAction {
	
	@Autowired
	private AnimalService animalService;
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PAnimal animal) {
		try {
			animalService.save(animal);
			log.info("【动物编号添加成功】："+animal+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新动物编号【"+animal.getAnimalname()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				animal.setErrorMsg("该动物编号已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				animal.setErrorMsg("动物编号信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				animal.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				animal.setErrorMsg("新增动物编号时发生未知异常,请联系维护人员");
			}
			log.info("【添加动物编号信息异常】："+animal+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, animal.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PAnimal animal) {
		try {
			animalService.update(animal);
			log.info("【动物编号信息修改成功】："+animal+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "来源【"+animal.getAnimalname()+"】修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	animal.setErrorMsg("动物编号信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				animal.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				animal.setErrorMsg("修改动物编号信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改动物编号信息异常】："+animal+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, animal.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PAnimal animal) {
		String ids = animal.getIds();
		String errorMsg = "";
		try {
			int deleteNum = animalService.delete(ids);
			log.info("【动物编号信息删除成功】："+ids+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条动物编号信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该动物编号已被引用,无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除动物编号时发生未知异常,请联系维护人员";
			}
			log.info("【删除动物编号信息异常】："+ids + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getAnimals() {
		String errorMsg = "";
		try {
			List<DetailAnimal> animals = animalService.getAnimals();
			result = new AjaxResult(true, animals);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取动物编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取动物编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PAnimal animal) {
		String errorMsg = "";
		try {
			PageResultModel<PAnimal> resultModel = animalService.list(animal);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询动物编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询动物编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
