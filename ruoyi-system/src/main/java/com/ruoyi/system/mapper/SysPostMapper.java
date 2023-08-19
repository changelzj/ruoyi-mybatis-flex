package com.ruoyi.system.mapper;

import java.util.List;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.SysPost;

import static com.ruoyi.system.domain.table.SysNoticeTableDef.SYS_NOTICE;
import static com.ruoyi.system.domain.table.SysPostTableDef.SYS_POST;

/**
 * 岗位信息 数据层
 * 
 * @author ruoyi
 */
public interface SysPostMapper extends BaseMapper<SysPost>
{
    /**
     * 查询岗位数据集合
     * 
     * @param post 岗位信息
     * @return 岗位数据集合
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * 	<select id="selectPostList" parameterType="SysPost" resultMap="SysPostResult">
     * 	    <include refid="selectPostVo"/>
     * 		<where>
     * 			<if test="postCode != null and postCode != ''">
     * 				AND post_code like concat('%', #{postCode}, '%')
     * 			</if>
     * 			<if test="status != null and status != ''">
     * 				AND status = #{status}
     * 			</if>
     * 			<if test="postName != null and postName != ''">
     * 				AND post_name like concat('%', #{postName}, '%')
     * 			</if>
     * 		</where>
     * 	</select>

     */
    default Page<SysPost> selectPostPage(SysPost post) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        PageDomain pageDomain = TableSupport.buildPageRequest();

        if (StringUtils.isNotEmpty(post.getPostCode())) {
            queryWrapper.and(SYS_POST.POST_CODE.like(post.getPostCode()));
        }
        if (StringUtils.isNotEmpty(post.getStatus())) {
            queryWrapper.and(SYS_POST.STATUS.eq(post.getStatus()));
        }
        if (StringUtils.isNotEmpty(post.getPostName())) {
            queryWrapper.and(SYS_POST.POST_NAME.like(post.getPostName()));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysPost> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    public List<SysPost> selectPostAll();

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    public SysPost selectPostById(Long postId);

    /**
     * 根据用户ID获取岗位选择框列表
     * 
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    public List<Long> selectPostListByUserId(Long userId);

    /**
     * 查询用户所属岗位组
     * 
     * @param userName 用户名
     * @return 结果
     */
    public List<SysPost> selectPostsByUserName(String userName);

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    public int deletePostById(Long postId);

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    public int deletePostByIds(Long[] postIds);

    /**
     * 修改岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(SysPost post);

    /**
     * 新增岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(SysPost post);

    /**
     * 校验岗位名称
     * 
     * @param postName 岗位名称
     * @return 结果
     */
    public SysPost checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     * 
     * @param postCode 岗位编码
     * @return 结果
     */
    public SysPost checkPostCodeUnique(String postCode);
}
