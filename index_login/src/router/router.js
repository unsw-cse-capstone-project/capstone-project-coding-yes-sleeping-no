/* eslint-disable */
// const trendList = r => require.ensure([], () => r(require('../app/trend/trendList')), 'trendList');
// const trendSetting = r => require.ensure([], () => r(require('../app/trend/children/trendSetting')), 'trendSetting');
const index = r => require.ensure([], () => r(require('../app/index')), 'index');
const details = r => require.ensure([], () => r(require('../app/details')), 'details');

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
  {
    path: '/details',
    component: details,
    name: 'details'
  }
]

export default routers
