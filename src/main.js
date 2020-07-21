/* eslint-disable */
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import routes from './router/router'
import VueRouter from 'vue-router'
import axios from 'axios'

import moment from 'moment'
import './assets/iconfont/iconfont.css'
import http from './components/http'

import './assets/style/app.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import routers from "./router/router";

Vue.use(ElementUI);
//Vue.use(axios);

Vue.config.productionTip = true;
Vue.prototype.$http = http;
// Vue.prototype.$axios = axios;

Vue.filter('dataformat', function (input, str, type, num, t) {
  if (type == 'add') {
    return moment(input).add(num, t).format(str);
  } else if(type === 'sub'){
    return moment(input).subtract(num, t).format(str);
  } else {
    return moment(input).format(str);
  }
})

Vue.use(VueRouter);
const router = new VueRouter({
  routes,
  // mode: 'history',
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
		} else {
      return { x: 0, y: 0 }
		}
	}
});

new Vue({
  router,
  components: { App },
  template: '<App/>'
}).$mount('#app');
