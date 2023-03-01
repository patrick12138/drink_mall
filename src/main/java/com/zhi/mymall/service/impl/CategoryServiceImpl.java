package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.Category;
import com.zhi.mymall.mapper.CategoryMapper;
import com.zhi.mymall.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.CategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryVO> categoryAll(){
        List<Category> categoriesList = categoryMapper.selectList(null);
        ArrayList<CategoryVO> objects = new ArrayList<>();
        for (Category category : categoriesList) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category,categoryVO);
            objects.add(categoryVO);
        }
        return setcategories(objects);
    }
    //找子分类,构建子菜单
    public List<CategoryVO> setcategories(List<CategoryVO> categoryVOList){
        //找到一级分类，与上面函数差不多
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_level","1");
        List<Category> categoriesList = categoryMapper.selectList(categoryQueryWrapper);
        ArrayList<CategoryVO> levelone = new ArrayList<>();
        for (Category category : categoriesList) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category,categoryVO);
            levelone.add(categoryVO);
        }
        for (CategoryVO categoryVO : levelone) {
            recursion(categoryVOList,categoryVO);
        }
        return levelone;
    }
    //递归分类
    public void recursion(List<CategoryVO> categoryVOList,CategoryVO categoryVO){
        List<CategoryVO> getcategories = getcategories(categoryVOList, categoryVO);
        categoryVO.setCategories(getcategories);
        if (getcategories.size()>0){
            for (CategoryVO getcategory : getcategories) {
                recursion(categoryVOList,getcategory);
            }
        }
    }
    //获取子菜单
    public List<CategoryVO> getcategories(List<CategoryVO> categoryVOList,CategoryVO categoryVO){
        ArrayList<CategoryVO> categoryVOS = new ArrayList<>();
        Iterator<CategoryVO> iterator = categoryVOList.iterator();
        //hasNext():判断是否存在下一个元素
        //		while(iter.hasNext()){
        //			//如果存在，则调用next实现迭代
        while (iterator.hasNext()) {
            CategoryVO next = iterator.next();
            if(next.getParentId().equals(categoryVO.getCategoryId())){
                categoryVOS.add(next);
            }
        }
        return categoryVOS;
    }

}
