package com.zzyl.nursing.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.nursing.vo.NursingProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.NursingProjectMapper;
import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.service.INursingProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 护理项目Service业务层处理
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Service
public class NursingProjectServiceImpl extends ServiceImpl<NursingProjectMapper, NursingProject> implements INursingProjectService
{
    @Autowired
    private NursingProjectMapper nursingProjectMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询护理项目
     * 
     * @param id 护理项目主键
     * @return 护理项目
     */
    @Override
    public NursingProject selectNursingProjectById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询护理项目列表
     * 
     * @param nursingProject 护理项目
     * @return 护理项目
     */
    @Override
    public List<NursingProject> selectNursingProjectList(NursingProject nursingProject)
    {
        return nursingProjectMapper.selectNursingProjectList(nursingProject);
    }

    /**
     * 新增护理项目
     * 
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int insertNursingProject(NursingProject nursingProject)
    {
        int result = save(nursingProject) ? 1 : 0;
        // 删除护理项目缓存
        deleteCache();
        return result;
    }

    /**
     * 修改护理项目
     * 
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int updateNursingProject(NursingProject nursingProject)
    {
        int result = updateById(nursingProject) ? 1 : 0;
        // 删除护理项目缓存
        deleteCache();
        return result;
    }

    /**
     * 批量删除护理项目
     * 
     * @param ids 需要删除的护理项目主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectByIds(Long[] ids)
    {
        int result = removeByIds(Arrays.asList(ids)) ? 1 : 0;
        // 删除护理项目缓存
        deleteCache();
        return result;
    }

    /**
     * 删除护理项目信息
     * 
     * @param id 护理项目主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectById(Long id)
    {
        int result = removeById(id) ? 1 : 0;
        // 删除护理项目缓存
        deleteCache();
        return result;
    }

    /**
     * 删除缓存
     */
    private void deleteCache() {
        redisTemplate.delete(CacheConstants.NURSING_PROJECT_ALL_KEY);
    }

    /**
     * 查询所有护理项目
     *
     * @return 护理项目列表
     */
    @Override
    public List<NursingProjectVo> getAll() {
        //从缓存中查询所有护理项目
        List<NursingProjectVo> list = (List<NursingProjectVo>) redisTemplate.opsForValue().get(CacheConstants.NURSING_PROJECT_ALL_KEY);

        //如果缓存中查到了，直接返回
        if(ObjectUtil.isNotEmpty(list)){
            return list;
        }

        //如果缓存中没有，从数据库中查询，并将结果放入缓存
        list = nursingProjectMapper.getAll();

        //将查询到的结果放入缓存
        redisTemplate.opsForValue().set(CacheConstants.NURSING_PROJECT_ALL_KEY, list);
        return list;
    }
}
