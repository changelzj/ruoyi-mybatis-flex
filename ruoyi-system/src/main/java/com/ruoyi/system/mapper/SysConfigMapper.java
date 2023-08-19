package com.ruoyi.system.mapper;

import java.util.List;
import java.util.WeakHashMap;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.system.domain.SysConfig;

import static com.ruoyi.system.domain.table.SysConfigTableDef.SYS_CONFIG;

/**
 * 参数配置 数据层
 * 
 * @author ruoyi
 */
public interface SysConfigMapper extends BaseMapper<SysConfig>
{
    /**
     * 查询参数配置信息
     * 
     * @param config 参数配置信息
     * @return 参数配置信息
     *
     * 	<where>
     * 			<if test="configId !=null">
     * 				and config_id = #{configId}
     * 			</if>
     * 			<if test="configKey !=null and configKey != ''">
     * 				and config_key = #{configKey}
     * 			</if>
     * 		</where>
     */
    default SysConfig selectConfig(SysConfig config) {
        return selectOneByCondition(SYS_CONFIG.CONFIG_ID.eq(config.getConfigId(), v -> v != null)
                .and(SYS_CONFIG.CONFIG_KEY.eq(config.getConfigKey(), v -> StringUtils.isNotEmpty(v) )));
    }

    /**
     * 通过ID查询配置
     * 
     * @param configId 参数ID
     * @return 参数配置信息
     *
     *  where config_key = #{configKey} limit 1
     */
    default SysConfig selectConfigById(Long configId) {
        return selectOneById( configId );
    }

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     *
     *     select config_id, config_name, config_key, config_value, config_type,
     *     create_by, create_time, update_by, update_time, remark
     * 		from sys_config
     *
     * <select id="selectConfigList" parameterType="SysConfig" resultMap="SysConfigResult">
     *         <include refid="selectConfigVo"/>
     *         <where>
     * 			<if test="configName != null and configName != ''">
     * 				AND config_name like concat('%', #{configName}, '%')
     * 			</if>
     * 			<if test="configType != null and configType != ''">
     * 				AND config_type = #{configType}
     * 			</if>
     * 			<if test="configKey != null and configKey != ''">
     * 				AND config_key like concat('%', #{configKey}, '%')
     * 			</if>
     * 			<if test="params.beginTime != null and params.beginTime != ''"><!-- 开始时间检索 -->
     * 				and date_format(create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')
     * 			</if>
     * 			<if test="params.endTime != null and params.endTime != ''"><!-- 结束时间检索 -->
     * 				and date_format(create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')
     * 			</if>
     * 		</where>
     *     </select>
     */
     default List<SysConfig> selectConfigList(SysConfig config) {
         QueryWrapper queryWrapper = QueryWrapper.create();

         if (StringUtils.isNotEmpty(config.getConfigName())) {
             queryWrapper.and(SYS_CONFIG.CONFIG_NAME.like(config.getConfigName()));
         }
         if (StringUtils.isNotEmpty(config.getConfigType())) {
             queryWrapper.and(SYS_CONFIG.CONFIG_TYPE.eq(config.getConfigType()));
         }
         if (StringUtils.isNotEmpty(config.getConfigKey())) {
             queryWrapper.and(SYS_CONFIG.CONFIG_KEY.like(config.getConfigKey()));
         }

         if (StringUtils.isNotEmpty((String) config.getParams().get("beginTime")) ) {
             queryWrapper.and(SYS_CONFIG.CREATE_TIME.ge(config.getParams().get("beginTime")));
         }
         if (StringUtils.isNotEmpty((String) config.getParams().get("endTime")) ) {
             queryWrapper.and(SYS_CONFIG.CREATE_TIME.le(config.getParams().get("endTime")));
         }

         return selectListByQuery(queryWrapper);
     }

    default Page<SysConfig> selectConfigPage(SysConfig config) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        PageDomain pageDomain = TableSupport.buildPageRequest();

        if (StringUtils.isNotEmpty(config.getConfigName())) {
            queryWrapper.and(SYS_CONFIG.CONFIG_NAME.like(config.getConfigName()));
        }
        if (StringUtils.isNotEmpty(config.getConfigType())) {
            queryWrapper.and(SYS_CONFIG.CONFIG_TYPE.eq(config.getConfigType()));
        }
        if (StringUtils.isNotEmpty(config.getConfigKey())) {
            queryWrapper.and(SYS_CONFIG.CONFIG_KEY.like(config.getConfigKey()));
        }

        if (StringUtils.isNotEmpty((String) config.getParams().get("beginTime")) ) {
            queryWrapper.and(SYS_CONFIG.CREATE_TIME.ge(config.getParams().get("beginTime")));
        }
        if (StringUtils.isNotEmpty((String) config.getParams().get("endTime")) ) {
            queryWrapper.and(SYS_CONFIG.CREATE_TIME.le(config.getParams().get("endTime")));
        }

        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }

        Page<SysConfig> page = paginate(pageDomain.getPageNum(), pageDomain.getPageSize(), queryWrapper);
        return page;
    }



    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数配置信息
     */
    default public SysConfig checkConfigKeyUnique(String configKey) {
        return selectOneByQuery( QueryWrapper.create().and(SYS_CONFIG.CONFIG_KEY.eq(configKey)).limit(1) );
    }

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    public int insertConfig(SysConfig config);

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    public int updateConfig(SysConfig config);

    /**
     * 删除参数配置
     * 
     * @param configId 参数ID
     * @return 结果
     */
    public int deleteConfigById(Long configId);

    /**
     * 批量删除参数信息
     * 
     * @param configIds 需要删除的参数ID
     * @return 结果
     */
    public int deleteConfigByIds(Long[] configIds);
}
