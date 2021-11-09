import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/kangfu";//转换字符串

// 新增订单
export function insertOrder(data) {
  return request({
    url: '/order/order/insert',
    method: 'post',
    data: data
  })
}

// 删除订单
export function deleteOrder(orderIds) {
  return request({
    url: '/order/order/delete/' + orderIds,
    method: 'delete'
  })
}

// 更新单个订单对象信息
export function updateOrder(data) {
  return request({
    url: '/order/order/update',
    method: 'put',
    data: data
  })
}

// 根据orderId查询单个订单对象信息
export function getOrderByOrderId(orderId) {
  return request({
    url: '/order/order/query/' + orderId,
    method: 'get'
  })
}

// 根据userId查询订单列表
export function getOrderList(userId) {
  return request({
    url: '/order/order/list/' + userId,
    method: 'get'
  })
}

// 根据userId查询用户信息
export function getUserByUserId(userId) {
  return request({
    url: '/test/order/queryUser/' + userId,
    method: 'get'
  })
}


// *********************************** 以下方法是调用/test/user/*/ ********************************

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/test/user/list',
    method: 'get',
    params: query
  })
}
// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/test/user/changeStatus',
    method: 'put',
    data: data
  })
}
// *********************************** 以上方法是调用/test/user/*/ ********************************
