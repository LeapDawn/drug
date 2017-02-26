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
import drug.dto.pageModel.PPart;
import drug.dto.pageModel.PageResultModel;
import drug.model.CollectionPart;
import drug.service.PartService;

@Controller
@RequestMapping("/part")
public class PartAction extends BaseAction {
	
	@Autowired
	private PartService partService;
	public void setPartService(PartService partService) {
		this.partService = partService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PPart part) {
		try {
			partService.save(part);
			log.info("【采集部位编号添加成功】："+part+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新采集部位编号【"+part.getPartname()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				part.setErrorMsg("该采集部位编号已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				part.setErrorMsg("采集部位编号信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				part.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				part.setErrorMsg("新增采集部位编号时发生未知异常,请联系维护人员");
			}
			log.info("【添加采集部位编号信息异常】："+part+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, part.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PPart part) {
		try {
			partService.update(part);
			log.info("【采集部位编号信息修改成功】："+part+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "采集部位编号【"+part.getPartname()+"】修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	part.setErrorMsg("采集部位编号信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				part.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				part.setErrorMsg("修改采集部位编号信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改采集部位编号信息异常】："+part+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, part.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PPart part) {
		String ids = part.getPartno();
		String errorMsg = "";
		try {
			int deleteNum = partService.delete(ids);
			log.info("【采集部位编号信息删除成功】："+ids+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条采集部位编号信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该采集部位编号已被引用,无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除采集部位时发生未知异常,请联系维护人员";
			}
			log.info("【删除采集部位编号信息异常】："+ids + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getParts() {
		String errorMsg = "";
		try {
			List<CollectionPart> parts = partService.getParts();
			result = new AjaxResult(true, parts);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取采集部位编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取采集部位编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PPart part) {
		String errorMsg = "";
		try {
			PageResultModel<PPart> resultModel = partService.list(part);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询采集部位编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询采集部位编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
