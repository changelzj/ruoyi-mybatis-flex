package com.ruoyi.system.mapper;

import java.util.List;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;

import static com.ruoyi.common.core.domain.entity.table.SysDictDataTableDef.SYS_DICT_DATA;
import static com.ruoyi.common.core.domain.entity.table.SysDictTypeTableDef.SYS_DICT_TYPE;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType>
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     <where>
     <if test="dictName != null and dictName != ''">
     AND dict_name like concat('%', #{dictName}, '%')
     </if>
     <if test="status != null and status != ''">
     AND status = #{status}
     </if>
     <if test="dictType != null and dictType != ''">
     AND dict_type like concat('%', #{dictType}, '%')
     </if>
     <if test="params.beginTime != null and params.beginTime != ''"><!-- 开始时间检索 -->
     and date_format(create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')
     </if>
     <if test="params.endTime != null and params.endTime != ''"><!-- 结束时间检索 -->
     and date_format(create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')
     </if>
     </where>
     */

    default Page<SysDictType> selectDictTypePage(SysDictType dictType) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(dictType.getDictName())) {
            queryWrapper.and(SYS_DICT_TYPE.DICT_NAME.like(dictType.getDictName()));
        }
        if (StringUtils.isNotEmpty(dictType.getStatus())) {
            queryWrapper.and(SYS_DICT_TYPE.STATUS.eq(dictType.getStatus()));
        }
        if (StringUtils.isNotEmpty(dictType.getDictType())) {
            queryWrapper.and(SYS_DICT_TYPE.DICT_TYPE.eq(dictType.getDictType()));
        }
        if (StringUtils.isNotEmpty((String) dictType.getParams().get("beginTime")) ) {
            queryWrapper.and(SYS_DICT_TYPE.CREATE_TIME.ge(dictType.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) dictType.getParams().get("endTime")) ) {
            queryWrapper.and(SYS_DICT_TYPE.CREATE_TIME.le(dictType.getParams().get("endTime")));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysDictType> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<SysDictType> selectDictTypeAll();



    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public SysDictType selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    public SysDictType selectDictTypeByType(String dictType);

    /**
     * 通过字典ID删除字典信息
     * 
     * @param dictId 字典ID
     * @return 结果
     */
    public int deleteDictTypeById(Long dictId);

    /**
     * 批量删除字典类型信息
     * 
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(SysDictType dictType);

    /**
     * 修改字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updateDictType(SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public SysDictType checkDictTypeUnique(String dictType);


}


