package drug.service;

import java.util.List;

import drug.dto.pageModel.PAnimal;
import drug.dto.pageModel.PageResultModel;
import drug.model.DetailAnimal;

public interface AnimalService {
	
	/**
	 * 新增动物编号信息
	 * @param panimal
	 */
	public void save(PAnimal panimal);
	
	/**
	 * 修改动物编号信息
	 * @param panimal
	 */
	public void update(PAnimal panimal);
	
	/**
	 * 删除动物编号
	 * @param animalNos
	 */
	public int delete(String animalNos);
	
	/**
	 * 获取动物编号集合
	 * @return 
	 */
	public List<DetailAnimal> getAnimals();
	
	/**
	 * 获取动物编号列表
	 * @param animals
	 * @return 
	 */
	public PageResultModel<PAnimal> list(PAnimal panimal);
}
