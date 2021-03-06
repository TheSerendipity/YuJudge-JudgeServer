package com.yzl.yujudge.bo;

import com.yzl.yujudge.vo.UserInfoBasicVO;

import java.util.List;
import java.util.Map;

/**
 * 记分板子项的业务对象
 *
 * @author yuzhanglong
 * @date 2020-08-12 23:14:38
 */

public class ScoreBoardItemBO {
    private List<Map<String, Object>> solutionInfo;
    private UserInfoBasicVO teamInfo;
    private Integer rank;
    private Integer totalAcAmount;
    private Long totalTimePenalty;

    public List<Map<String, Object>> getSolutionInfo() {
        return solutionInfo;
    }

    public void setSolutionInfo(List<Map<String, Object>> solutionInfo) {
        this.solutionInfo = solutionInfo;
    }

    public UserInfoBasicVO getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(UserInfoBasicVO teamInfo) {
        this.teamInfo = teamInfo;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTotalAcAmount() {
        return totalAcAmount;
    }

    public void setTotalAcAmount(Integer totalAcAmount) {
        this.totalAcAmount = totalAcAmount;
    }

    public Long getTotalTimePenalty() {
        return totalTimePenalty;
    }

    public void setTotalTimePenalty(Long totalTimePenalty) {
        this.totalTimePenalty = totalTimePenalty;
    }

    @Override
    public String toString() {
        return "ScoreBoardItemBO{" +
                "solutionInfo=" + solutionInfo +
                ", teamInfo=" + teamInfo +
                ", rank=" + rank +
                '}';
    }
}