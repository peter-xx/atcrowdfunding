package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.vo.AddressVO;
import com.atguigu.crowd.entity.vo.OrderProjectVO;
import com.atguigu.crowd.entity.vo.OrderVO;

import java.util.List;

/**
 * ClassName: OrderService
 * Package: com.atguigu.crowd.service
 * Description:
 *
 * @Author peter
 * @Create 2023/7/5 11:29
 * @Version 1.0
 */
public interface OrderService {
    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    List<AddressVO> getAddressVO(Integer memberId);

    void saveAddress(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);
}
