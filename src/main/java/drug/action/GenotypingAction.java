package drug.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import drug.commons.exception.DataViolationException;
import drug.dto.AjaxResult;
import drug.dto.pageModel.PGenotyping;
import drug.dto.pageModel.ResultPageModel;
import drug.model.Genotyping;
import drug.service.GenotypingService;

@Controller
@RequestMapping("/genotyping")
public class GenotypingAction extends BaseAction {
	@Autowired
	private GenotypingService gtpService;
	public void setGtpdService(GenotypingService gtpService) {
		this.gtpService = gtpService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PGenotyping pgtp) {
		try {
			gtpService.save(pgtp);
			log.info("【亚型-基因添加成功】："+pgtp+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "亚型-基因信息【"+pgtp.getGenname() 
					+ "/"+pgtp.getGenotyping()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				pgtp.setErrorMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				pgtp.setErrorMsg("亚型-基因信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				pgtp.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				pgtp.setErrorMsg("新增亚型-基因时发生未知异常,请联系维护人员");
			}
			log.info("【添加亚型-基因信息异常】："+pgtp+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, pgtp.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PGenotyping pgtp) {
		try {
			gtpService.update(pgtp);
			log.info("【亚型-基因信息修改成功】："+pgtp+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "亚型-基因信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				pgtp.setErrorMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				pgtp.setErrorMsg("亚型-基因信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				pgtp.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				pgtp.setErrorMsg("修改亚型-基因信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改亚型-基因信息异常】："+pgtp+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, pgtp.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PGenotyping pgtp) {
		String ids = pgtp.getIds();
		String errorMsg = "";
		try {
			int deleteNum = gtpService.delete(ids);
			log.info("【亚型-基因信息删除成功】："+ids+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条亚型-基因信息");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该亚型-基因无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除亚型-基因时发生未知异常,请联系维护人员";
			}
			log.info("【删除亚型-基因信息异常】："+ids + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false)  PGenotyping pgtp) {
		String errorMsg = "";
		try {
			ResultPageModel<Genotyping> resultModel = gtpService.list(pgtp);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询亚型-基因信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询亚型-基因信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
