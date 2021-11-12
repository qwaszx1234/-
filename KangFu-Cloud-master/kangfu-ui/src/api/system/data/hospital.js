import request from '@/utils/request'

// 医院logo上传
export function uploadAvatar(data) {
  return request({
    url: '/system/data/hospital/avatar',
    method: 'post',
    data: data
  })
}

// 查询医院详细信息
export function getHospitalInfo() {
  return request({
    url: '/system/data/hospital/getHospitalInfo',
    method: 'get'
  })
}

// 显示医院详细信息
export function showHospitalInfo() {
  return request({
    url: '/system/data/hospital/getHospitalInfo',
    method: 'get'
  })
}

// 更新医院
export function updateHospital(data) {
  return request({
    url: '/system/data/hospital/updateHospital',
    method: 'post',
    data: data
  })
}

// 更新医院介绍图片数组
export function updateHospitalImage(data) {
  return request({
    url: '/system/data/hospital/updateHospitalImage',
    method: 'post',
    data: data
  })
}
