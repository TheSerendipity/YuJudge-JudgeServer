package com.yzl.yujudge.repository;


import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 题目集查询对象
 *
 * @author yuzhanglong
 * @date 2020-08-08 21:37:25
 */
public interface ProblemSetRepository extends BaseRepository<ProblemSetEntity> {
    /**
     * 分页获取题目集合
     *
     * @param pageable 分页配置
     * @param name     关键字
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-08-08 21:40:03
     */
    @Query("select p from ProblemSetEntity p " +
            "where p.name like %?1% " +
            "order by p.createTime desc")
    Page<ProblemSetEntity> findByName(String name, Pageable pageable);

    /**
     * 分页获取题目集合
     * 我们额外传入一个当前时间
     * 用来筛选当前时间介于开始时间和截止时间之间的题目
     *
     * @param currentTime 当前时间
     * @param pageable    分页对象
     * @param name        名称
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-08-09 11:26:49
     */
    @Query("select p from ProblemSetEntity p " +
            "where p.startTime <= ?1 " +
            "and p.deadline >= ?1 " +
            "and p.name like %?2%" +
            "order by p.createTime desc")
    Page<ProblemSetEntity> findByNameBetweenCurrentTime(Date currentTime, String name, Pageable pageable);

    /**
     * 根据ID 获取题目集
     *
     * @param problemSetId 题目集id
     * @return ProblemSetEntity 实体对象
     * @author yuzhanglong
     * @date 2020-08-09 11:51:58
     */
    ProblemSetEntity findOneById(Long problemSetId);

    /**
     * 用户是否参与了/加入了题目集
     *
     * @param problemSetId 题目集id
     * @param userEntity   用户实体对象
     * @return 数量
     * @author yuzhanglong
     * @date 2020-08-13 22:52:44
     */
    Long countByIdAndParticipants(Long problemSetId, UserEntity userEntity);

    /**
     * 获取所有活跃的题目集
     * 我们传入一个当前时间
     * 用来筛选当前时间介于开始时间和截止时间之间的题目
     *
     * @param currentTime 当前时间
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-08-09 11:26:49
     */
    @Query("select p from ProblemSetEntity p " +
            "where p.startTime <= ?1 " +
            "and p.deadline >= ?1 " +
            "order by p.createTime desc")
    List<ProblemSetEntity> findBetweenCurrentTime(Date currentTime);
}