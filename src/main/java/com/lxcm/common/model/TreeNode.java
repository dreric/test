package com.lxcm.common.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树的节点模型
 */
@Data
public class TreeNode {

    private Long id; //节点id

    @JsonProperty("text") // 生成json字符串中的属性名称
    private String name;//节点名称

    private Long parentId;//父几点的id

    // 子节点
    @JsonProperty("nodes")
    private List<TreeNode> children = new ArrayList<TreeNode>();

    private Map<String,Boolean> state = new HashMap<String,Boolean>();

    private String url;
    private String icon;

}
