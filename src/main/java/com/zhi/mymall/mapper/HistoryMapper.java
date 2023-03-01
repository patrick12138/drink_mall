package com.zhi.mymall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.History;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhi.mymall.vo.TimeImgVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-05-07
 */
@Mapper
public interface HistoryMapper extends BaseMapper<History> {
    @Select(" select distinct p.product_name,p.product_id,p.sold_num,p.content,p.category_id, i.url,h.time\n" +
            " from history h INNER JOIN product_img i INNER JOIN product p\n" +
            "            on i.item_id=p.product_id and h.product_id=p.product_id\n" +
            "            where h.user_id=#{userId} and i.is_main=1 order by h.time DESC")
    Page<TimeImgVO> history(@Param("userId") Integer userId, Page<TimeImgVO> page);
}
