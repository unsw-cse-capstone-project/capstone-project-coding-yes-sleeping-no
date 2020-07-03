/* eslint-disable */
import axios from 'axios'
import unit from './utils'

let axio = new axios.create();
let user = '';

if(process.env.NODE_ENV === 'development'){
  axio.defaults.baseURL = '/api';
}else if(process.env.NODE_ENV === 'production'){
  axio.defaults.baseURL = 'http://192.168.9.31:40823';
  // axio.defaults.baseURL = unit.getStorage('baseUrl');
}

axio.defaults.timeout = 30000;
axio.defaults.headers.post['Content-Type'] = 'application/json';

// 添加请求拦截器
axio.interceptors.request.use( config => {
  if(process.env.NODE_ENV === 'development'){
    config.headers.userName = 'sww';
  }else if(process.env.NODE_ENV === 'production'){
    if(!!user){
      config.headers.userName = user;
    } else {
      user = unit.getStorage('userName');
      config.headers.userName = user;
      // config.baseURL = unit.getStorage('baseUrl');
    }
  };
  return config;
}, err => {
  return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use( response => {
    return response;
  }, err => {
    return Promise.reject(err);
  });

function successState(i) {
  if (i.data.code === 600) {
    // AlertModule.show({
    //   title: '提示',
    //   content: '账号在其他地方登录，请重新登录',
    //   onHide () {
    //     logout();
    //     return;
    //   }
    // })
  }
}

function errorState(i) { }

export default {
  get (i, data){
    return new Promise((resolve,reject) => {
      axio({ method: 'get', url: i, params: data})
        .then(res => {
          successState(res);
          resolve(res)
        }, res => {
          errorState(res);
          reject(res);
        })
    })
  },
  post: function(i, data){
    return new Promise((resolve,reject) => {
      axio({ method: 'post', url: i, data: JSON.stringify(data)})
        .then(res => {
          successState(res);
          resolve(res)
        }, res => {
          errorState(res);
          reject(res);
        })
    })
  },
  fetch: function(i, data){
    return new Promise((resolve,reject) => {
      axio({ method: 'post', url: i, params: data})
        .then(res => {
          successState(res);
          resolve(res)
        }, res => {
          errorState(res);
          reject(res);
        })
    })
  }
}
