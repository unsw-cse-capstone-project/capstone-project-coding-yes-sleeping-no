webpackJsonp([3],{"dS4+":function(t,e){},pvY8:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={name:"myCenter",data:()=>({user:{}}),methods:{logout(){this.$http.get("/user/logout").then(t=>{t.state?(this.$message({message:t.msg,type:"success"}),localStorage.removeItem("user"),this.user={},location.href="/"):this.$message({message:t.msg,type:"fail"})})}},created(){let t=localStorage.getItem("user");t&&(this.user=JSON.parse(t).user)}},i={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("el-container",[s("el-aside",{attrs:{span:6}},[s("el-menu",{staticClass:"el-menu-vertical-demo",attrs:{"default-active":"2","background-color":"#f3f3f3","text-color":"#000","active-text-color":"#ffd04b"}},[s("h2",{staticClass:"press"},[t._v("My Center")]),t._v(" "),s("router-link",{staticStyle:{"text-decoration":"none","text-align":"left"},attrs:{to:"/orderMgm"}},[s("el-menu-item",{staticStyle:{margin:"0 40px"},attrs:{index:"1"}},[s("i",{staticClass:"el-icon-document"}),t._v(" "),s("span",{attrs:{slot:"title"},slot:"title"},[t._v("Order Management")])])],1),t._v(" "),2===this.user.status?s("router-link",{staticStyle:{"text-decoration":"none","text-align":"left"},attrs:{to:"/eventMgm"}},[s("el-menu-item",{staticStyle:{margin:"0 40px"},attrs:{index:"3"}},[s("i",{staticClass:"el-icon-document"}),t._v(" "),s("span",{attrs:{slot:"title"},slot:"title"},[t._v("Event Management")])])],1):t._e(),t._v(" "),s("router-link",{staticStyle:{"text-decoration":"none","text-align":"left"},attrs:{to:"/accountSet"}},[s("el-menu-item",{staticStyle:{margin:"0 40px"},attrs:{index:"4"}},[s("i",{staticClass:"el-icon-setting"}),t._v(" "),s("span",{attrs:{slot:"title"},slot:"title"},[t._v("User Profile")])])],1),t._v(" "),s("div",{staticStyle:{"text-decoration":"none","text-align":"left"}},[s("el-menu-item",{staticStyle:{margin:"0 40px"},attrs:{index:"5"},on:{click:t.logout}},[s("i",{staticClass:"el-icon-setting"}),t._v(" "),s("span",{attrs:{slot:"title"},slot:"title"},[t._v("logout")])])],1)],1)],1),t._v(" "),s("el-main",{staticStyle:{"background-color":"white"},attrs:{span:18}},[s("router-view")],1)],1)},staticRenderFns:[]};var l=s("VU/8")(a,i,!1,function(t){s("dS4+")},"data-v-f1d639a6",null);e.default=l.exports}});
//# sourceMappingURL=3.686e83a5feec146ad136.js.map