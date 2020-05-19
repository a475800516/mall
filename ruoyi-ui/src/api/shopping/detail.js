import request from '@/utils/request'

// 查询商品详细
export function getGoodDetail(goodId) {
  return request({
    url: '/shopping/good/' + goodId,
    method: 'get'
  })
}

// 新增商品
export function addGood(data) {
  return request({
    url: '/shopping/good',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateGood(data) {
  return request({
    url: '/shopping/good',
    method: 'put',
    data: data
  })
}

// 删除商品
export function delGood(goodId) {
  return request({
    url: '/shopping/good/' + goodId,
    method: 'delete'
  })
}

// 导出商品
export function exportGood(query) {
  return request({
    url: '/shopping/good/export',
    method: 'get',
    params: query
  })
}

// 图片上传
export function uploadImage(data) {
  return request({
    url: '/system/user/profile/uploadImg',
    method: 'post',
    data: data
  })
}


// 下单
export function placeAnOrder(goodId) {
  return request({
    url: '/shopping/orders/placeAnOrder?goodId=' + goodId,
    method: 'get'
  })
}
