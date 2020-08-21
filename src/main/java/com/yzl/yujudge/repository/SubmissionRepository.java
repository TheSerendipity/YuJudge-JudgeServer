package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.SubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author yuzhanglong
 * @date 2020-7-29 13:02:18
 * @description 提交相关查询类接口
 */
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {

    /**
     * 获取单个提交
     *
     * @param id 提交id
     * @return JudgeSolutionEntity 解决方案实体类
     * @author yuzhanglong
     * @date 2020-7-22 23:06
     * @description 获取单个解决方案
     */
    SubmissionEntity findOneById(Long id);


    /**
     * 根据problemId 获取多个提交
     *
     * @param problemId 问题的id
     * @param pageable  分页参数
     * @return SubmissionEntity 解决方案实体类List
     * @author yuzhanglong
     * @date 2020-7-31 19:18:51
     * @description 通过问题id获取多个解决方案, 按时间先后倒序排列
     */
    Page<SubmissionEntity> findAllByPkProblemOrderByCreateTimeDesc(Long problemId, Pageable pageable);


    /**
     * 获取某天之后的所有submission的数量（无状态）
     *
     * @param userId 用户id
     * @param start  开始时间
     * @param end    结束时间
     * @return Long 数量
     * @author yuzhanglong
     * @date 2020-08-07 13:09:06
     * @description 获取某天之后的所有submission
     */
    @Query("select count(submission) from SubmissionEntity submission " +
            "where submission.creator.id = ?1 " +
            "and submission.createTime between ?2 and ?3 ")
    Long getUserSubmissionTotalAmount(Long userId, Date start, Date end);


    /**
     * 限制判题结果,获取某段时间内的所有submission的数量
     *
     * @param userId    用户id
     * @param start     开始时间
     * @param end       结束时间
     * @param condition 判题状态
     * @return 查询到的提交数目
     * @date 2020-08-07 14:57:44
     * @description 获取某段时间内的所有submission的数量，可以限制判题结果
     */
    @Query("select count(submission) from SubmissionEntity submission " +
            "where submission.creator.id = ?1 and submission.judgeCondition = ?4 " +
            "and submission.createTime between ?2 and ?3 ")
    Long getUserSubmissionTotalAmountByJudgeResult(Long userId, Date start, Date end, String condition);


    /**
     * 根据题目集信息、题目信息、用户/队伍 信息寻找AC的个数
     *
     * @param problemSetId 题目集id
     * @param userId       用户/队伍id
     * @param problemId    题目id
     * @return SubmissionEntity 查询到的提交实体类对象
     * @author yuzhanglong
     * @date 2020-08-13 01:00:23
     * @description 根据题目集信息、题目信息、用户/队伍 信息寻找AC的个数
     * 注意：如果之前已经ac过，
     * 之后的任何提交在这里都不会被计算进去，
     * 详见submission的isAcBefore字段
     */
    @Deprecated
    @Query("select count(submission) from SubmissionEntity submission " +
            "where submission.problemSet.id = ?1 " +
            "and submission.creator.id = ?2 " +
            "and submission.pkProblem = ?3 " +
            "and submission.judgeCondition = 'ACCEPT' " +
            "order by submission.createTime desc ")
    Long getAcAmountByProblemSetIdAndUserIdAndProblemId(Long problemSetId, Long userId, Long problemId);


    /**
     * 根据题目集信息、题目信息、用户/队伍 信息寻找某个用户在某个题目上是否已经ac过
     *
     * @param problemSetId 题目集id
     * @param userId       用户/队伍id
     * @param problemId    题目id
     * @return SubmissionEntity 查询到的提交实体类对象
     * @author yuzhanglong
     * @date 2020-08-13 22:36:12
     * @description 根据题目集信息、题目信息、用户/队伍 信息寻找某个用户在某个题目上是否已经ac过
     */
    @Deprecated
    @Query("select submission from SubmissionEntity submission " +
            "where submission.problemSet.id = ?1 " +
            "and submission.creator.id = ?2 " +
            "and submission.pkProblem = ?3 " +
            "and submission.judgeCondition = 'ACCEPT' " +
            "order by submission.createTime desc ")
    SubmissionEntity getUserFirstAcInProblemSet(Long problemSetId, Long userId, Long problemId);


    /**
     * 统计出在给出的日期之前某个题目是否已经有AC提交
     *
     * @param problemSetId 题目集id
     * @param problemId    题目id
     * @param givenDate    给出的日期
     * @return SubmissionEntity 查询到的提交实体类对象
     * @author yuzhanglong
     * @date 2020-08-13 22:38:38
     * @description 根据题目集信息、题目信息
     * 通过给出的日期，
     * 统计出在给出的日期之前某个题目是否已经有AC提交
     */
    @Query("select count(submission) from SubmissionEntity submission " +
            "where submission.problemSet.id = ?1 " +
            "and submission.pkProblem = ?2 " +
            "and submission.judgeCondition = 'ACCEPT' " +
            "and submission.createTime < ?3 ")
    Long countAcSubmissionEarlyThanGiven(Long problemSetId, Long problemId, Date givenDate);


    /**
     * 根据题目集信息、题目信息、用户/队伍 信息寻找AC的个数
     *
     * @param problemSetId 题目集id
     * @param userId       用户/队伍id
     * @param problemId    题目id
     * @return SubmissionEntity 查询到的提交实体类对象
     * @author yuzhanglong
     * @date 2020-08-13 01:01:19
     * @description 根据题目集信息、题目信息、用户/队伍 信息寻找AC的个数
     * <p>
     * 注意：如果之前已经ac过，
     * 之后的任何提交在这里都不会被计算进去，
     * 详见submission的isAcBefore字段
     */
    @Deprecated
    @Query("select count(submission) from SubmissionEntity submission " +
            "where submission.problemSet.id = ?1 " +
            "and submission.creator.id = ?2 " +
            "and submission.pkProblem = ?3 " +
            "and submission.judgeCondition <> 'ACCEPT' " +
            "order by submission.createTime desc ")
    Long getWaAmountByProblemSetIdAndUserIdAndProblemId(Long problemSetId, Long userId, Long problemId);


    /**
     * 统计某个时间段内某个判题机的提交数据
     *
     * @param start       开始时间
     * @param end         结束时间
     * @param judgeHostId 判题机id
     * @return 提交数据统计的集合
     * @author yuzhanglong
     * @date 2020-8-19 17:05:29
     * @description 统计某个时间段内某个判题机的提交数据
     * 示例:【排序优先级: 提交时间 > 小时数】
     * +--------------------+------------------+--------------------+
     * | 提交时间(精确到天)   | 位于当天的小时数   | 这个小时的提交数量   |
     * +--------------------+------------------+--------------------+
     * | 2020-08-18         |                0 |                  1 |
     * | 2020-08-19         |               14 |                  1 |
     * +--------------------+------------------+--------------------+
     */
    @Query(value = "SELECT DATE(s.create_time), HOUR(s.create_time), COUNT(*)" +
            "FROM submission s " +
            "WHERE s.create_time BETWEEN ?1 and ?2 " +
            "and s.pk_judge_host = ?3 " +
            "GROUP BY DATE(s.create_time), HOUR(s.create_time) " +
            "ORDER BY DATE(s.create_time), HOUR(s.create_time)",
            nativeQuery = true)
    Set<List<Object>> countSubmissionGroupByHoursByJudgeHostId(Date start, Date end, Long judgeHostId);


    /**
     * 统计某个时间段内某个判题机的提交数据
     *
     * @param start        开始时间
     * @param end          结束时间
     * @param problemSetId 判题机id
     * @return 提交数据统计的集合
     * @author yuzhanglong
     * @date 2020-8-19 17:05:29
     * @description 统计某个时间段内某个判题机的提交数据
     * 示例:【排序优先级: 提交时间 > 小时数】
     * +--------------------+------------------+--------------------+
     * | 提交时间(精确到天)   | 位于当天的小时数   | 这个小时的提交数量   |
     * +--------------------+------------------+--------------------+
     * | 2020-08-18         |                0 |                  1 |
     * | 2020-08-19         |               14 |                  1 |
     * +--------------------+------------------+--------------------+
     */
    @Query(value = "SELECT DATE(s.create_time), HOUR(s.create_time), COUNT(*)" +
            "FROM submission s " +
            "WHERE s.create_time BETWEEN ?1 and ?2 " +
            "and s.pk_problem_set = ?3 " +
            "GROUP BY DATE(s.create_time), HOUR(s.create_time) " +
            "ORDER BY DATE(s.create_time), HOUR(s.create_time)",
            nativeQuery = true)
    Set<List<Object>> countSubmissionGroupByHoursByProblemSetId(Date start, Date end, Long problemSetId);


    /**
     * 获取某个用户在本平台内的所有提交统计
     * 【查询结果示例】
     * +--------------------+------+
     * | 结果描述            | 数量  |
     * +--------------------+------+
     * | UNKNOWN_ERROR      |   31 |
     * | ACCEPT             |  338 |
     * | RUNTIME_ERROR      |   16 |
     * | COMPILE_ERROR      |   52 |
     * | SEGMENTATION_FAULT |   12 |
     * | WRONG_ANSWER       |   16 |
     * +--------------------+------+
     *
     * @param userId 目标用户id
     * @return 提交数据统计的集合
     * @author yuzhanglong
     * @date 2020-8-21 00:47:29
     */
    @Query(value = "SELECT judge_condition, COUNT(*) " +
            "FROM submission s " +
            "WHERE s.pk_user = ?1 " +
            "GROUP BY judge_condition ",
            nativeQuery = true)
    Set<List<Object>> countUserTotalSubmissionCountDate(Long userId);


    /**
     * 统计某个题目集的提交信息，排除 UNKNOWN_ERROR
     *
     * @param problemSetId 判题机id
     * @return 提交数据统计的集合
     * @author yuzhanglong
     * @date 2020-8-20 14:40:24
     */
    @Query("select submission from SubmissionEntity submission " +
            "where submission.problemSet.id = ?1 " +
            "and submission.judgeCondition <> 'UNKNOWN_ERROR' " +
            "order by submission.createTime")
    List<SubmissionEntity> countSubmissionConditionScatterByProblemSetId(Long problemSetId);
}