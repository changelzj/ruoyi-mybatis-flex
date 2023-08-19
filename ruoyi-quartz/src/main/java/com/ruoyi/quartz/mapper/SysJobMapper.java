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
import com.ruoyi.quartz.domain.table.SysJobTableDef;

import static com.ruoyi.quartz.domain.table.SysJobTableDef.SYS_JOB;

/**
 * 调度任务信息 数据层
 * 
 * @author ruoyi
 */
public interface SysJobMapper extends BaseMapper<SysJob>
{
    /**
     * 查询调度任务日志集合
     * 
     * @param job 调度信息
     * @return 操作日志集合
     */
    /**
     *         /**
     *          * 	<select id="selectJobList" parameterType="SysJob" resultMap="SysJobResult">
     *          * 		<include refid="selectJobVo"/>
     *          * 		<where>
     *          * 			<if test="jobName != null and jobName != ''">
     *          * 				AND job_name like concat('%', #{jobName}, '%')
     *          * 			</if>
     *          * 			<if test="jobGroup != null and jobGroup != ''">
     *          * 				AND job_group = #{jobGroup}
     *          * 			</if>
     *          * 			<if test="status != null and status != ''">
     *          * 				AND status = #{status}
     *          * 			</if>
     *          * 			<if test="invokeTarget != null and invokeTarget != ''">
     *          * 				AND invoke_target like concat('%', #{invokeTarget}, '%')
     *          * 			</if>
     *          * 		</where>
     *          * 	</select>
     *          */

    default Page<SysJob> selectJobPage(SysJob job) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(job.getJobName()) ) {
            queryWrapper.and(SYS_JOB.JOB_NAME.eq(job.getJobName()));
        }
        if (StringUtils.isNotEmpty(job.getJobGroup()) ) {
            queryWrapper.and(SYS_JOB.JOB_GROUP.eq(job.getJobGroup()));
        }
        if (StringUtils.isNotEmpty(job.getStatus()) ) {
            queryWrapper.and(SYS_JOB.STATUS.eq(job.getStatus()));
        }
        if (StringUtils.isNotEmpty(job.getInvokeTarget()) ) {
            queryWrapper.and(SYS_JOB.INVOKE_TARGET.like(job.getInvokeTarget()));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysJob> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }


    default List<SysJob> selectJobList(SysJob job) {

        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.isNotEmpty(job.getJobName()) ) {
            queryWrapper.and(SYS_JOB.JOB_NAME.eq(job.getJobName()));
        }
        if (StringUtils.isNotEmpty(job.getJobGroup()) ) {
            queryWrapper.and(SYS_JOB.JOB_GROUP.eq(job.getJobGroup()));
        }
        if (StringUtils.isNotEmpty(job.getStatus()) ) {
            queryWrapper.and(SYS_JOB.STATUS.eq(job.getStatus()));
        }
        if (StringUtils.isNotEmpty(job.getInvokeTarget()) ) {
            queryWrapper.and(SYS_JOB.INVOKE_TARGET.like(job.getInvokeTarget()));
        }



        List<SysJob> list = selectListByQuery( queryWrapper);
        return list;
    }

    /**
     * 查询所有调度任务
     * 
     * @return 调度任务列表
     */
    public List<SysJob> selectJobAll();

    /**
     * 通过调度ID查询调度任务信息
     * 
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public SysJob selectJobById(Long jobId);

    /**
     * 通过调度ID删除调度任务信息
     * 
     * @param jobId 调度ID
     * @return 结果
     */
    public int deleteJobById(Long jobId);

    /**
     * 批量删除调度任务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobByIds(Long[] ids);

    /**
     * 修改调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int updateJob(SysJob job);

    /**
     * 新增调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int insertJob(SysJob job);
}
