package com.ruoyi.system.mapper;

import java.util.List;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.table.SysLogininforTableDef;

import static com.ruoyi.system.domain.table.SysConfigTableDef.SYS_CONFIG;
import static com.ruoyi.system.domain.table.SysLogininforTableDef.SYS_LOGININFOR;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author ruoyi
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor>
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     *
     * @param logininfor
     * @return
     *
     * <select id="selectLogininforList" parameterType="SysLogininfor" resultMap="SysLogininforResult">
     * 		select info_id, user_name, ipaddr, login_location, browser, os, status, msg, login_time from sys_logininfor
     * 		<where>
     * 			<if test="ipaddr != null and ipaddr != ''">
     * 				AND ipaddr like concat('%', #{ipaddr}, '%')
     * 			</if>
     * 			<if test="status != null and status != ''">
     * 				AND status = #{status}
     * 			</if>
     * 			<if test="userName != null and userName != ''">
     * 				AND user_name like concat('%', #{userName}, '%')
     * 			</if>
     * 			<if test="params.beginTime != null and params.beginTime != ''"><!-- 开始时间检索 -->
     * 				AND login_time &gt;= #{params.beginTime}
     * 			</if>
     * 			<if test="params.endTime != null and params.endTime != ''"><!-- 结束时间检索 -->
     * 				AND login_time &lt;= #{params.endTime}
     * 			</if>
     * 		</where>
     * 		order by info_id desc
     * 	</select>
     */
    default Page<SysLogininfor> selectLogininforPage(SysLogininfor logininfor) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        PageDomain pageDomain = TableSupport.buildPageRequest();

        if (StringUtils.isNotEmpty(logininfor.getIpaddr())) {
            queryWrapper.and(SYS_LOGININFOR.IPADDR.like(logininfor.getIpaddr()));
        }
        if (StringUtils.isNotEmpty(logininfor.getStatus())) {
            queryWrapper.and(SYS_LOGININFOR.STATUS.eq(logininfor.getStatus()));
        }
        if (StringUtils.isNotEmpty(logininfor.getUserName())) {
            queryWrapper.and(SYS_LOGININFOR.USER_NAME.like(logininfor.getUserName()));
        }
        if (StringUtils.isNotEmpty((String) logininfor.getParams().get("beginTime")) ) {
            queryWrapper.and(SYS_LOGININFOR.CREATE_TIME.ge(logininfor.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) logininfor.getParams().get("endTime")) ) {
            queryWrapper.and(SYS_LOGININFOR.CREATE_TIME.le(logininfor.getParams().get("endTime")));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysLogininfor> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    public int deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     * 
     * @return 结果
     */
    public int cleanLogininfor();
}
