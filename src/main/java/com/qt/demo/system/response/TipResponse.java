package com.qt.demo.system.response;

import com.qt.demo.system.entity.Tip;
import lombok.Data;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/12/7 7:21 下午
 */
@Data
public class TipResponse {

    private List<Tip> tips;

    private Boolean needTest;
}
