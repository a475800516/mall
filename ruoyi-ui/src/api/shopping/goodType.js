import request from '@/utils/request'

// 查询商品类型列表
export function listGoodType(query) {
  return request({
    url: '/shopping/goodType/list',
    method: 'get',
    params: query
  })
}

// 查询商品类型详细
export function getGoodType(goodTypeId) {
  return request({
    url: '/shopping/goodType/' + goodTypeId,
    method: 'get'
  })
}

// 新增商品类型
export function addGoodType(data) {
  return request({
    url: '/shopping/goodType',
    method: 'post',
    data: data
  })
}

// 修改商品类型
export function updateGoodType(data) {
  return request({
    url: '/shopping/goodType',
    method: 'put',
    data: data
  })
}

// 删除商品类型
export function delGoodType(goodTypeId) {
  return request({
    url: '/shopping/goodType/' + goodTypeId,
    method: 'delete'
  })
}
