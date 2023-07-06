package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: OrderProjectVO
 * Package: com.atguigu.crowd.entity.vo
 * Description:
 *
 * @Author peter
 * @Create 2023/7/5 11:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProjectVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String projectName;
    private String launchName;
    private String returnContent;
    private Integer returnCount;
    private Integer supportPrice;
    private Integer freight;
    private Integer orderId;
    private Integer signalPurchase;
    private Integer purchase;


}
