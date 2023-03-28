package com.github.yakami.task.interceptor.core;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by alan on 2023/3/27.
 * 执行目标执行结果
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalTaskResponse implements Serializable {

    private static final long serialVersionUID = 5503890986871164813L;

    /**
     * 出参变量
     */
    @Builder.Default
    public Map<String, String> outPutVariables = Maps.newLinkedHashMap();

    public void addVariable(String key, String val) {
        outPutVariables.put(key, val);
    }

}
