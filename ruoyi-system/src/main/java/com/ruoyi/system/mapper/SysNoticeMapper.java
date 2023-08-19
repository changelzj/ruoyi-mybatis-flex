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
import com.ruoyi.system.domain.SysNotice;

import static com.ruoyi.system.domain.table.SysConfigTableDef.SYS_CONFIG;
import static com.ruoyi.system.domain.table.SysNoticeTableDef.SYS_NOTICE;

/**
 * 通知公告表 数据层
 * 
 * @author ruoyi
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice>
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);


    /**
     *     <select id="selectNoticeList" parameterType="SysNotice" resultMap="SysNoticeResult">
     *         <include refid="selectNoticeVo"/>
     *         <where>
     * 			<if test="noticeTitle != null and noticeTitle != ''">
     * 				AND notice_title like concat('%', #{noticeTitle}, '%')
     * 			</if>
     * 			<if test="noticeType != null and noticeType != ''">
     * 				AND notice_type = #{noticeType}
     * 			</if>
     * 			<if test="createBy != null and createBy != ''">
     * 				AND create_by like concat('%', #{createBy}, '%')
     * 			</if>
     * 		</where>
     *     </select>

     */
    default Page<SysNotice> selectNoticePage(SysNotice notice) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        PageDomain pageDomain = TableSupport.buildPageRequest();

        if (StringUtils.isNotEmpty(notice.getNoticeTitle())) {
            queryWrapper.and(SYS_NOTICE.NOTICE_TITLE.like(notice.getNoticeTitle()));
        }
        if (StringUtils.isNotEmpty(notice.getNoticeType())) {
            queryWrapper.and(SYS_NOTICE.NOTICE_TYPE.eq(notice.getNoticeType()));
        }
        if (StringUtils.isNotEmpty(notice.getCreateBy())) {
            queryWrapper.and(SYS_NOTICE.CREATE_BY.like(notice.getCreateBy()));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysNotice> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }


    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}
