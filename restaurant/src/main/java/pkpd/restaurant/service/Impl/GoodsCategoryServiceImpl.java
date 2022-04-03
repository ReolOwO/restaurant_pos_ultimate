package pkpd.restaurant.service.Impl;

import pkpd.restaurant.Enums.ResultEnum;
import pkpd.restaurant.dao.GoodsCategoryDao;

import pkpd.restaurant.entity.GoodsCategory;
import pkpd.restaurant.exception.CustomException;
import pkpd.restaurant.service.GoodsCategoryService;
import pkpd.restaurant.utils.SplitIdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 菜品类型管理
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryDao goodsCategoryDao;
    /**
     * 查询所有菜品类型
     * @return
     */
    @Override
    public List<GoodsCategory> findAll(){
        return goodsCategoryDao.findAll();
    }

    /**
     * 根据菜品类型id查询菜品类型信息
     * @param id
     * @return
     */
    @Override
    public GoodsCategory findById(Long id) {
        return goodsCategoryDao.findById(id);
    }

    /**
     * 添加类型
     * @param goodsCategory
     */
    @Override
    public void insert(GoodsCategory goodsCategory) {
        GoodsCategory result = goodsCategoryDao.findByCategoryName(goodsCategory);
        if(result!=null){
            throw new CustomException(ResultEnum.CATEGORY_NAME_IS_EXIST);
        }
        goodsCategoryDao.insert(goodsCategory);
    }

    /**
     * 修改类型
     * @param goodsCategory
     */
    @Override
    public void update(GoodsCategory goodsCategory) {
        GoodsCategory result = goodsCategoryDao.findByCategoryName(goodsCategory);
        if(result!=null){
            throw new CustomException(ResultEnum.CATEGORY_NAME_IS_EXIST);
        }
        goodsCategoryDao.update(goodsCategory);
    }

    /**
     * 根据id删除类型
     * @param strIds
     */
    @Override
    public void deleteByIds(String strIds) {
        List<Long> idList = SplitIdsUtil.splitStrIds(strIds);
        goodsCategoryDao.deleteByIds(idList);
    }
}
