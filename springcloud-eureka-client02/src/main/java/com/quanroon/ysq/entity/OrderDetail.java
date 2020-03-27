package com.quanroon.ysq.entity;

import java.io.Serializable;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 15:40
 * @content
 */
public class OrderDetail implements Serializable {
    private String orderId;

    private Item item = new Item();

    public OrderDetail() {

    }

    public OrderDetail(String orderId, Item item) {
        this.orderId = orderId;
        this.item = item;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDetail [orderId=" + orderId + ", item=" + item + "]";
    }

}
