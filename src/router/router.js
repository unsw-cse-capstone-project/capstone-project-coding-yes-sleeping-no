/* eslint-disable */
const index = r => require.ensure([], () => r(require('../app/index')), 'index');
const myCenter = r => require.ensure([], () => r(require('../app/myCenter')), 'myCenter');
const accountSet = r => require.ensure([], () => r(require('../components/accountSet')), 'accountSet');
const orderMgm = r => require.ensure([], () => r(require('../components/orderMgm')), 'orderMgm');
const eventMgm = r => require.ensure([], () => r(require('../components/eventMgm')), 'eventMgm');
const forgetPwd = r => require.ensure([], () => r(require('../app/forgetPwd')), 'forgetPwd');
const resetPwd = r => require.ensure([], () => r(require('../app/resetPwd')), 'resetPwd');
const webHead = r => require.ensure([], () => r(require('../app/webHead')), 'webHead');
const eventDetail = r => require.ensure([], () => r(require('../app/eventDetail')), 'eventDetail');

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
    path: '/forgetPwd',
    component: forgetPwd,
    name: 'forgetPwd'
  },
  {
    path: '/resetPwd',
    component: resetPwd,
    name: 'resetPwd'
  },
  {
    path: '/head',
    component: webHead,
    name: 'webHead',
    children: [
      {
        path: '/myCenter',
        component: myCenter,
        name: 'myCenter',
        children: [
          {
            path: '/accountSet',
            component: accountSet,
            name: 'accountSet'
          },
          {
            path: '/orderMgm',
            component: orderMgm,
            name: 'orderMgm'
          },
          {
            path: '/eventMgm',
            component: eventMgm,
            name: 'eventMgm'
          }
        ]
      },
      {
        path: '/eventDetail',
        component: eventDetail,
        name: 'eventDetail'
      }
    ]
  }

]

export default routers