import request from '@/utils/request'

// 查询商品类型列表
export function listGoodType(query) {
  return request({
    url: '/shopping/goodType/treeselect',
    method: 'get',
    params: query
  })
}


// 查询商品列表
export function listGood(query) {
  return request({
    url: '/shopping/good/list',
    method: 'get',
    params: query
  })
}
