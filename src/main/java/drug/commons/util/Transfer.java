package drug.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import drug.commons.exception.DataViolationException;
import drug.dto.pageModel.PDrugView;
import drug.dto.pageModel.PDrugViewAll;
import drug.dto.pageModel.PFarms;
import drug.dto.pageModel.PSample;
import drug.dto.pageModel.PStrainCharacter;
import drug.dto.pageModel.PStrainCoding;
import drug.dto.pageModel.PStrainMic;
import drug.model.DrugView;
import drug.model.DrugViewAll;
import drug.model.Farms;
import drug.model.Sample;
import drug.model.StrainCharacter;
import drug.model.StrainCoding;
import drug.model.StrainMic;

/**
 * 实现各实体与页面模型的相互转换
 */
public class Transfer {
	
	public  static PFarms changeToPageModel(Farms farm) {
		PFarms pfarm = new PFarms();
		BeanUtils.copyProperties(farm, pfarm);
		return pfarm;
	}
	
	public static Farms changeToEntity(PFarms pfarm) {
		Farms farm = new Farms();
		BeanUtils.copyProperties(pfarm, farm);
		return farm;
	}
	
	public static Sample changeToEntity(PSample psample) {
		Sample sample = new Sample();
		BeanUtils.copyProperties(psample, sample);
		String str = psample.getSampledateStr();
		if (str != null && !"".equals(str)) {
			try {
				sample.setSampledate(new SimpleDateFormat("yyyy-MM-dd").parse(str));
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataViolationException("添加失败,日期格式错误,示例:2016-01-01");
			}
		}
		sample.setAdduser(psample.getOtherMsg());
		sample.setAddtime(new Date());
		return sample;
	}
	
	public static PSample changetoPageModel(Sample sample) {
		PSample psample = new PSample();
		BeanUtils.copyProperties(sample, psample);
		Date date = sample.getSampledate();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		psample.setSampledateStr(dateStr);
		return psample;
	}

	public static StrainCoding changeToEntity(PStrainCoding pstrain) {
		StrainCoding strain = new StrainCoding();
		BeanUtils.copyProperties(pstrain, strain);
		String str = pstrain.getStrainstoragedateStr();
		if (str != null && !"".equals(str)) {
			try {
				strain.setStrainstoragedate(new SimpleDateFormat("yyyy-MM-dd").parse(str));
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataViolationException("操作失败,日期格式错误,示例:2016-01-01");
			}
		}
		strain.setAdduser(pstrain.getOtherMsg());
		strain.setAddtime(new Date());
		return strain;
	}

	public static PStrainCoding changetoPageModel(StrainCoding strain) {
		PStrainCoding pstrain = new PStrainCoding();
		BeanUtils.copyProperties(strain, pstrain);
		Date date = strain.getStrainstoragedate();
		if (date != null) {
			String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			pstrain.setStrainstoragedateStr(dateStr);
		}
		return pstrain;
	}
	
	public static PStrainMic changetoPageModel(StrainMic strainMic) {
		PStrainMic pstrainMic = new PStrainMic();
		BeanUtils.copyProperties(strainMic, pstrainMic);
		return pstrainMic;
	}

	public static StrainMic changeToEntity(PStrainMic pstrainMic) {
		StrainMic strainmic =  new StrainMic();
		BeanUtils.copyProperties(pstrainMic, strainmic);
		strainmic.setAdduser(pstrainMic.getOtherMsg());
		strainmic.setAddtime(new Date());
		return strainmic;
	}
	
	public static PStrainCharacter changetoPageModel(StrainCharacter character) {
		PStrainCharacter pcharacter = new PStrainCharacter();
		BeanUtils.copyProperties(character, pcharacter);
		return pcharacter;
	}

	public static StrainCharacter changeToEntity(PStrainCharacter pcharacter) {
		StrainCharacter character =  new StrainCharacter();
		BeanUtils.copyProperties(pcharacter, character);
		character.setAdduser(pcharacter.getOtherMsg());
		character.setAddtime(new Date());
		return character;
	}
	
	public static PDrugViewAll changeToPageModel(DrugViewAll drugViewAll) {
		PDrugViewAll pdva = new PDrugViewAll();
		BeanUtils.copyProperties(drugViewAll, pdva);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = drugViewAll.getSampledate();
		pdva.setSampledate(date != null ? format.format(date) : "");
		date = drugViewAll.getStrainstoragedate();
		pdva.setStrainstoragedate(date != null ? format.format(date) : "");
		return pdva;
	}
	
	public static PDrugView changeToPageModel(DrugView drugView) {
		PDrugView pdv = new PDrugView();
		BeanUtils.copyProperties(drugView, pdv);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = drugView.getSampledate();
		pdv.setSampledate(date!=null?format.format(date):"");
		date = drugView.getStrainstoragedate();
		pdv.setStrainstoragedate(date!=null?format.format(date):"");
		return pdv;
	}
}
