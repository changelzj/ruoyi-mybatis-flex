package com.ruoyi.system.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.SysOperLog;
import org.springframework.util.CollectionUtils;

import static com.ruoyi.common.core.domain.entity.table.SysDictTypeTableDef.SYS_DICT_TYPE;
import static com.ruoyi.system.domain.table.SysLogininforTableDef.SYS_LOGININFOR;
import static com.ruoyi.system.domain.table.SysOperLogTableDef.SYS_OPER_LOG;

/**
 * 操作日志 数据层
 * 
 * @author ruoyi
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog>
{
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * <select id="selectOperLogList" parameterType="SysOperLog" resultMap="SysOperLogResult">
     * 		<include refid="selectOperLogVo"/>
     * 		<where>
     * 			<if test="title != null and title != ''">
     * 				AND title like concat('%', #{title}, '%')
     * 			</if>
     * 			<if test="businessType != null">
     * 				AND business_type = #{businessType}
     * 			</if>
     * 			<if test="businessTypes != null and businessTypes.length > 0">
     * 			    AND business_type in
     * 			    <foreach collection="businessTypes" item="businessType" open="(" separator="," close=")">
     * 		 			#{businessType}
     * 		        </foreach>
     * 			</if>
     * 			<if test="status != null">
     * 				AND status = #{status}
     * 			</if>
     * 			<if test="operName != null and operName != ''">
     * 				AND oper_name like concat('%', #{operName}, '%')
     * 			</if>
     * 			<if test="params.beginTime != null and params.beginTime != ''"><!-- 开始时间检索 -->
     * 				AND oper_time &gt;= #{params.beginTime}
     * 			</if>
     * 			<if test="params.endTime != null and params.endTime != ''"><!-- 结束时间检索 -->
     * 				AND oper_time &lt;= #{params.endTime}
     * 			</if>
     * 		</where>
     * 		order by oper_id desc
     * 	</select>

     */
    default Page<SysOperLog> selectOperLogPage(SysOperLog operLog) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        PageDomain pageDomain = TableSupport.buildPageRequest();

        if (StringUtils.isNotEmpty(operLog.getTitle())) {
            queryWrapper.and(SYS_OPER_LOG.TITLE.like(operLog.getTitle()));
        }
        if (!Objects.isNull(operLog.getBusinessType())) {
            queryWrapper.and(SYS_OPER_LOG.BUSINESS_TYPE.eq(operLog.getBusinessType()));
        }
        if (!Objects.isNull(operLog.getBusinessTypes()) && operLog.getBusinessTypes().length > 0) {
            queryWrapper.and(SYS_OPER_LOG.BUSINESS_TYPE.in(Arrays.asList(operLog.getBusinessTypes()) ));
        }
        if (!Objects.isNull(operLog.getStatus())) {
            queryWrapper.and(SYS_OPER_LOG.STATUS.eq(operLog.getStatus()));
        }
        if (StringUtils.isNotEmpty(operLog.getOperName())) {
            queryWrapper.and(SYS_OPER_LOG.OPER_NAME.like(operLog.getOperName()));
        }
        if (StringUtils.isNotEmpty((String) operLog.getParams().get("beginTime")) ) {
            queryWrapper.and(SYS_OPER_LOG.CREATE_TIME.ge(operLog.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) operLog.getParams().get("endTime")) ) {
            queryWrapper.and(SYS_OPER_LOG.CREATE_TIME.le(operLog.getParams().get("endTime")));
        }


        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysOperLog> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    public int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
