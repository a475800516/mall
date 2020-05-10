import request from '@/utils/request'

// 查询订单列表
export function listOrders(query) {
  return request({
    url: '/shopping/orders/listMyOrders',
    method: 'get',
    params: query
  })
}
