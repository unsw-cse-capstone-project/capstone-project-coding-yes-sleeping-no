/* eslint-disable */
import http from 'http'

/**
 * @name  监听手势
 */
const gesture = () => {

}

/**
 *
 * @param {*} i 请求对象 Obj
 */
const request = (i) => {
  if (!!i.url) {

  } else {
    //err 请输入正确的url路径
  }
}

/**
 * @name 本地存储localStorage
 * @param name  localStorage名字
 * @param content  期望存入的指
 */
const setStorage = (name, content) => {
  if (!!name) {
    if (typeof content !== 'string') {
      content = JSON.stringify(content);
    }
    window.localStorage.setItem(name, content);
  } else {
    return;
  }

}

/**
 * @name 本地存储localStorage(同步)
 * @param name  localStorage名字
 * @param content  期望存入的指
 */
const setStorageSync = (name, content) => {

}

/**
 * @name 本地获取localStorage
 * @param name  localStorage名字
 */
const getStorage = name => {
  // if (!!name) {
  return window.localStorage.getItem(name) || window.parent.localStorage.getItem(name);
  // } else {
  //   // return
  // }
}

/**
 * @name 本地获取localStorage(同步)
 * @param name  localStorage名字
 */
const getStorageSync = name => {
  if (!!name) return;
  return window.localStorage.getItem(name);
}

/**
 * @name 本地删除localStorage
 * @param name  localStorage名字
 */
const removeStorage = name => {
  if (!!name) return;
  window.localStorage.removeItem(name);
}

/**
 * @name 本地删除localStorage(同步)
 * @param name  localStorage名字
 */
const removeStorageSync = name => {
  if (!!name) return;
  window.localStorage.removeItem(name);
}

/**
 * @name 清空localStorage
 */
const clearStorage = () => {
  window.localStorage.clear();
}

/**
 * @name 清空localStorage(同步)
 */
const clearStorageSync = () => {
  window.localStorage.clear();
}

/**
 * @name  获取企业成员基本信息
 */
const getEnterpriseUserInfo = (i) => {

}

/**
 * @name  获取企业成员头像
 */
const getAvatar = (i) => {

}

/**
 * @name  获取企业成员手机号
 */
const getMobile = (i) => {

}

/**
 * @name  获取企业成员邮箱地址
 */
const getEmail = (i) => {

}

const system = (i, data) => {
  var u = navigator.userAgent, app = navigator.appVersion;
  var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //g
  var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
  if (isAndroid) {
    return 'android';
  }
  if (isIOS) {
    return 'ios';
  }
}

const onPullDownRefresh = () => {

}

const stopPullDownRefresh = () => {

}

export default {
  setStorage,
  setStorageSync,
  getStorage,
  getStorageSync,
  removeStorage,
  removeStorageSync,
  system,
  clearStorage,
  clearStorageSync,
  getEnterpriseUserInfo,
  getAvatar,
  getMobile,
  getEmail,
  request,
  gesture
}
