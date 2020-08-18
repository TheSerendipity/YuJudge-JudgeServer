package com.yzl.yujudge.store.redis;

import com.yzl.yujudge.bo.JudgeHostBO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhanglong
 * @description 判题机相关缓存
 */

@Component
public class JudgeHostCache {
    public static final String JUDGE_HOST_INFO_SAVE_PREFIX = "judgeHosts";
    private final RedisOperations redisOperations;

    public JudgeHostCache(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * @param judgeHostBOList 判题机相关业务对象
     * @author yuzhanglong
     * @description 更新判题机状态的信息, 存入redis中
     * @date 2020-08-17 14:18:59
     */
    public void setJudgeConditionCache(List<JudgeHostBO> judgeHostBOList) {
        // 删除旧数据
        redisOperations.remove(JUDGE_HOST_INFO_SAVE_PREFIX);
        // 遍历判断过的judgeHostBO,并将每一项存入hashMap
        for (JudgeHostBO judgeHostBO : judgeHostBOList) {
            boolean isSet = redisOperations.setHashMap(
                    JUDGE_HOST_INFO_SAVE_PREFIX,
                    judgeHostBO.getId().toString(),
                    judgeHostBO
            );
        }
    }

    /**
     * @return List<Object> 判题机信息列表
     * @author yuzhanglong
     * @description 获取缓存中redis状态的信息
     * @date 2020-8-18 14:30:40
     */
    public List<Object> getJudgeHostsConditionListCache() {
        Map<Object, Object> res = redisOperations.getHashMap(JUDGE_HOST_INFO_SAVE_PREFIX);
        return new ArrayList<>(res.values());
    }


    /**
     * @return Object 判题机信息列表 / null 如果判题机信息不存在的话
     * @author yuzhanglong
     * @description 通过id 获取缓存中redis状态的信息（单个判题机）
     * @date 2020-8-18 14:32:39
     */
    public Object getJudgeHostsConditionByJudgeHostId(String key) {
        Map<Object, Object> res = redisOperations.getHashMap(JUDGE_HOST_INFO_SAVE_PREFIX);
        return res.get(key);
    }
}
