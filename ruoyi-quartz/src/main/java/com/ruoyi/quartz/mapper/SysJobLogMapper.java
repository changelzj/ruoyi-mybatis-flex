package com.ruoyi.quartz.mapper;

import java.util.List;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.domain.SysJobLog;

import static com.ruoyi.quartz.domain.table.Tables.SYS_JOB_LOG;

/**
 * 调度任务日志信息 数据层
 * 
 * @author ruoyi
 */
public interface SysJobLogMapper extends BaseMapper<SysJobLog>
{
    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     *
     * <select id="selectJobLogList" parameterType="SysJobLog" resultMap="SysJobLogResult">
     * 		<include refid="selectJobLogVo"/>
     * 		<where>
     * 			<if test="jobName != null and jobName != ''">
     * 				AND job_name like concat('%', #{jobName}, '%')
     * 			</if>
     * 			<if test="jobGroup != null and jobGroup != ''">
     * 				AND job_group = #{jobGroup}
     * 			</if>
     * 			<if test="status != null and status != ''">
     * 				AND status = #{status}
     * 			</if>
     * 			<if test="invokeTarget != null and invokeTarget != ''">
     * 				AND invoke_target like concat('%', #{invokeTarget}, '%')
     * 			</if>
     * 			<if test="params.beginTime != null and params.beginTime != ''"><!-- 开始时间检索 -->
     * 				and date_format(create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')
     * 			</if>
     * 			<if test="params.endTime != null and params.endTime != ''"><!-- 结束时间检索 -->
     * 				and date_format(create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')
     * 			</if>
     * 		</where>
     * 	</select>
     */
    default List<SysJobLog> selectJobLogList(SysJobLog jobLog) {
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(jobLog.getJobName()) ) {
            queryWrapper.and(SYS_JOB_LOG.JOB_NAME.like(jobLog.getJobName()));
        }
        if (StringUtils.isNotEmpty(jobLog.getJobGroup()) ) {
            queryWrapper.and(SYS_JOB_LOG.JOB_GROUP.eq(jobLog.getJobGroup()));
        }
        if (StringUtils.isNotEmpty(jobLog.getStatus()) ) {
            queryWrapper.and(SYS_JOB_LOG.STATUS.eq(jobLog.getStatus()));
        }
        if (StringUtils.isNotEmpty(jobLog.getInvokeTarget()) ) {
            queryWrapper.and(SYS_JOB_LOG.INVOKE_TARGET.eq(jobLog.getInvokeTarget()));
        }
        if (StringUtils.isNotEmpty((String) jobLog.getParams().get("beginTime")) ) {
            queryWrapper.and(SYS_JOB_LOG.CREATE_TIME.ge(jobLog.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) jobLog.getParams().get("endTime")) ) {
            queryWrapper.and(SYS_JOB_LOG.CREATE_TIME.le(jobLog.getParams().get("endTime")));
        }

        return selectListByQuery(queryWrapper);
    }

    default Page<SysJobLog> selectJobLogPage(SysJobLog jobLog) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(jobLog.getJobName()) ) {
            queryWrapper.and(SYS_JOB_LOG.JOB_NAME.like(jobLog.getJobName()));
        }
        if (StringUtils.isNotEmpty(jobLog.getJobGroup()) ) {
            queryWrapper.and(SYS_JOB_LOG.JOB_GROUP.eq(jobLog.getJobGroup()));
        }
        if (StringUtils.isNotEmpty(jobLog.getStatus()) ) {
            queryWrapper.and(SYS_JOB_LOG.STATUS.eq(jobLog.getStatus()));
        }
        if (StringUtils.isNotEmpty(jobLog.getInvokeTarget()) ) {
            queryWrapper.and(SYS_JOB_LOG.INVOKE_TARGET.eq(jobLog.getInvokeTarget()));
        }
        if (StringUtils.isNotEmpty((String) jobLog.getParams().get("beginTime")) ) {
            queryWrapper.and(SYS_JOB_LOG.CREATE_TIME.ge(jobLog.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) jobLog.getParams().get("endTime")) ) {
            queryWrapper.and(SYS_JOB_LOG.CREATE_TIME.le(jobLog.getParams().get("endTime")));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysJobLog> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }

    /**
     * 查询所有调度任务日志
     *
     * @return 调度任务日志列表
     */
    public List<SysJobLog> selectJobLogAll();

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public SysJobLog selectJobLogById(Long jobLogId);

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     * @return 结果
     */
    public int insertJobLog(SysJobLog jobLog);

    /**
     * 批量删除调度日志信息
     * 
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobLogByIds(Long[] logIds);

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     * @return 结果
     */
    public int deleteJobLogById(Long jobId);

    /**
     * 清空任务日志
     */
    public void cleanJobLog();
}
