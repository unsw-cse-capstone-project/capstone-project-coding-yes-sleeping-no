/* eslint-disable */
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import routes from './router/router'
import VueRouter from 'vue-router'

import moment from 'moment'
import './assets/iconfont/iconfont.css'
import http from './components/http'

import './assets/style/app.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'

Vue.use(ElementUI, { locale});
//Vue.use(axios);

Vue.config.productionTip = true;
Vue.prototype.$http = http
// Vue.prototype.$axios = axios;

// Global function
Vue.prototype.getPic = function(path) {
  if (path !== null) {
    let len = path.split('/').length - 1;
    const file = path.split('/')[len];
    // let imagePath = require('/Users/edgar/Desktop/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/' + file);
    let imagePath = 'http://localhost:9999/photos/' + file;
    return imagePath;
  }
}

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
