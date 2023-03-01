package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.ProductComments;
import com.zhi.mymall.mapper.ProductCommentsMapper;
import com.zhi.mymall.mapper.UsersMapper;
import com.zhi.mymall.service.ProductCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.ProductCommentsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品评价  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class ProductCommentsServiceImpl extends ServiceImpl<ProductCommentsMapper, ProductComments> implements ProductCommentsService {

    @Resource
    private ProductCommentsMapper productCommentsMapper;
    @Resource
    private UsersMapper usersMapper;

    @Override
    public Page<ProductCommentsVO> showComments(String ProductId, Page<ProductCommentsVO> page) {
/*        QueryWrapper<ProductComments> productCommentsQueryWrapper = new QueryWrapper<>();
        productCommentsQueryWrapper.eq("product_id",ProductId);
        List<ProductComments> productCommentsList = productCommentsMapper.selectList(productCommentsQueryWrapper);
        ArrayList<ProductCommentsVO> productCommentsVOS1 = new ArrayList<>();
        for (ProductComments productComments : productCommentsList) {
            ProductCommentsVO productCommentsVOS = new ProductCommentsVO();
            BeanUtils.copyProperties(productComments,productCommentsVOS);
            Users users = usersMapper.selectById(productComments.getUserId());
            BeanUtils.copyProperties(users,productCommentsVOS);
            productCommentsVOS1.add(productCommentsVOS);
        }
        return productCommentsVOS1;*/
        return page.setRecords(productCommentsMapper.getCommentsById(ProductId, page));
    }
}
