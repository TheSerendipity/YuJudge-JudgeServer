package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @description 用户提交的数据传输对象
 * @date 2020-7-29 00:59:51
 */

public class SubmissionDTO {
    @NotNull(message = "problemId 不得为空")
    private Long problemId;

    @NotNull(message = "用户代码不得为空")
    private String codeContent;

    @NotNull(message = "目标语言不得为空")
    private String language;

    @NotNull(message = "判题偏好不得为空")
    private String judgePreference;

    private Long problemSetId;


    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public String getJudgePreference() {
        return judgePreference;
    }

    public void setJudgePreference(String judgePreference) {
        this.judgePreference = judgePreference;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getProblemSetId() {
        return problemSetId;
    }

    public void setProblemSetId(Long problemSetId) {
        this.problemSetId = problemSetId;
    }

    @Override
    public String toString() {
        return "SubmissionDTO{" +
                "problemId='" + problemId + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
