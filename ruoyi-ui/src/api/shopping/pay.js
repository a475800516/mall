import request from '@/utils/request'

// 查询商品类型详细
export function getOrders(ordersId) {
  return request({
    url: '/shopping/orders/' + ordersId,
    method: 'get'
  })
}
