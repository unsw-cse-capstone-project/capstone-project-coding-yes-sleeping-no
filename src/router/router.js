/* eslint-disable */
// const trendList = r => require.ensure([], () => r(require('../app/trend/trendList')), 'trendList');
// const trendSetting = r => require.ensure([], () => r(require('../app/trend/children/trendSetting')), 'trendSetting');
const index = r => require.ensure([], () => r(require('../app/index')), 'index');
// const detail = r => _require.ensure([], () => r(require('../app/detail')), 'detail')
const myCenter = r => require.ensure([], () => r(require('../app/myCenter')), 'myCenter');

const routers = [
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/index',
    component: index,
    name: 'index'
  },
  // {
  //   path: '/detail',
  //   component: detail,
  //   name: 'detail'
  // },
  {
    path: '/myCenter',
    component: myCenter,
    name: 'myCenter'
  }
]

export default routers
