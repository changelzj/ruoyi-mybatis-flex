package com.ruoyi.generator.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.generator.domain.GenTable;

import static com.ruoyi.generator.domain.table.GenTableTableDef.GEN_TABLE;

/**
 * 业务 数据层
 * 
 * @author ruoyi
 */
public interface GenTableMapper extends BaseMapper<GenTable>
{
    /**
     * 查询业务列表
     * 
     * @param genTable 业务信息
     * @return 业务集合
     *
     *     <select id="selectGenTableList" parameterType="GenTable" resultMap="GenTableResult">
     * 		<include refid="selectGenTableVo"/>
     * 		<where>
     * 			<if test="tableName != null and tableName != ''">
     * 				AND lower(table_name) like lower(concat('%', #{tableName}, '%'))
     * 			</if>
     * 			<if test="tableComment != null and tableComment != ''">
     * 				AND lower(table_comment) like lower(concat('%', #{tableComment}, '%'))
     * 			</if>
     * 			<if test="params.beginTime != null and params.beginTime != ''"><!-- 开始时间检索 -->
     * 				AND date_format(create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')
     * 			</if>
     * 			<if test="params.endTime != null and params.endTime != ''"><!-- 结束时间检索 -->
     * 				AND date_format(create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')
     * 			</if>
     * 		</where>
     * 	</select>
     */
    default Page<GenTable> selectGenTableList(GenTable genTable) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        PageDomain pageDomain = TableSupport.buildPageRequest();

        if (StringUtils.isNotEmpty(genTable.getTableName())) {
            queryWrapper.and(GEN_TABLE.TABLE_NAME.like(genTable.getTableName()));
        }
        if (StringUtils.isNotEmpty(genTable.getTableComment())) {
            queryWrapper.and(GEN_TABLE.TABLE_COMMENT.like(genTable.getTableComment()));
        }
        if (StringUtils.isNotEmpty((String) genTable.getParams().get("beginTime")) ) {
            queryWrapper.and(GEN_TABLE.CREATE_TIME.ge(genTable.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) genTable.getParams().get("endTime")) ) {
            queryWrapper.and(GEN_TABLE.CREATE_TIME.le(genTable.getParams().get("endTime")));
        }



        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<GenTable> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }




    /**
     * 查询据库列表
     * 
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     * 
     * @return 表信息集合
     */
    public List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     * 
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     * 
     * @param tableName 表名称
     * @return 业务信息
     */
    public GenTable selectGenTableByName(String tableName);

    /**
     * 新增业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    public int insertGenTable(GenTable genTable);

    /**
     * 修改业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    public int updateGenTable(GenTable genTable);

    /**
     * 批量删除业务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenTableByIds(Long[] ids);
}
