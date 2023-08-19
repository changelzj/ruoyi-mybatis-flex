package com.ruoyi.system.mapper;

import java.util.List;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryOrderByBuilder;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.system.domain.SysConfig;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.common.core.domain.entity.SysDictData;

import static com.ruoyi.common.core.domain.entity.table.SysDictDataTableDef.SYS_DICT_DATA;
import static com.ruoyi.system.domain.table.SysConfigTableDef.SYS_CONFIG;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData>
{
    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     *
     * 	<select id="selectDictDataList" parameterType="SysDictData" resultMap="SysDictDataResult">
     * 	    <include refid="selectDictDataVo"/>
     * 		<where>
     * 		    <if test="dictType != null and dictType != ''">
     * 				AND dict_type = #{dictType}
     * 			</if>
     * 			<if test="dictLabel != null and dictLabel != ''">
     * 				AND dict_label like concat('%', #{dictLabel}, '%')
     * 			</if>
     * 			<if test="status != null and status != ''">
     * 				AND status = #{status}
     * 			</if>
     * 		</where>
     * 		order by dict_sort asc
     * 	</select>
     */
    default List<SysDictData> selectDictDataList(SysDictData dictData) {
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(dictData.getDictType())) {
            queryWrapper.and(SYS_DICT_DATA.DICT_TYPE.eq(dictData.getDictType()));
        }
        if (StringUtils.isNotEmpty(dictData.getDictLabel())) {
            queryWrapper.and(SYS_DICT_DATA.DICT_LABEL.eq(dictData.getDictLabel()));
        }
        if (StringUtils.isNotEmpty(dictData.getStatus())) {
            queryWrapper.and(SYS_DICT_DATA.STATUS.eq(dictData.getStatus()));
        }
        queryWrapper.orderBy(SYS_DICT_DATA.DICT_SORT.desc());
        return selectListByQuery(queryWrapper);
    }





    default Page<SysDictData> selectDictDataPage(SysDictData dictData) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(dictData.getDictType())) {
            queryWrapper.and(SYS_DICT_DATA.DICT_TYPE.eq(dictData.getDictType()));
        }
        if (StringUtils.isNotEmpty(dictData.getDictLabel())) {
            queryWrapper.and(SYS_DICT_DATA.DICT_LABEL.eq(dictData.getDictLabel()));
        }
        if (StringUtils.isNotEmpty(dictData.getStatus())) {
            queryWrapper.and(SYS_DICT_DATA.STATUS.eq(dictData.getStatus()));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysDictData> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * 查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据
     */
    public int countDictDataByType(String dictType);

    /**
     * 通过字典ID删除字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 结果
     */
    public int deleteDictDataById(Long dictCode);

    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] dictCodes);

    /**
     * 新增字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     * 修改字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);

    /**
     * 同步修改字典类型
     * 
     * @param oldDictType 旧字典类型
     * @param newDictType 新旧字典类型
     * @return 结果
     */
    public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
