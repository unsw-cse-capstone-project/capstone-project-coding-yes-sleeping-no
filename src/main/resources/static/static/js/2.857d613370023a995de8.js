webpackJsonp([2],{"/CKh":function(t,e){},DmHb:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAICAYAAADN5B7xAAAAAXNSR0IArs4c6QAAAKRJREFUGBljFBYWXvD///94BiIAIyPjQkYpKSmunz9/ngZq0sKnB6j4Gjs7uykjSJGQkJAWUACkiQubJqDcN6Cc6bt3764xgRSAGEAqG5tiqFg2VA0DM0zR9+/fL3BxcSkC+QYwMRANcvfbt28bYGJgG2AcoBuzQG6F8UFskBiMD6LBfkAWgPkHJAZzN7I83EkwQaDTXgOd9gzI3wZ0926YOIwGAN0vPSRXJP8nAAAAAElFTkSuQmCC"},QF8b:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={name:"webHead",data:()=>({user:{}}),methods:{},created(){let t=localStorage.getItem("user");t?this.user=JSON.parse(t).user:(alert("You have not logged in yet, click OK to jump to the login page!"),location.href="/")}},s={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("el-container",{staticClass:"contour",staticStyle:{width:"1280px","text-align":"center",margin:"0 auto"}},[i("el-header",{staticStyle:{padding:"0"}},[i("el-row",{staticStyle:{margin:"0px",padding:"10px","background-color":"white",color:"black"},attrs:{gutter:20}},[i("el-col",{staticStyle:{padding:"0px 0px 0px 20px","text-align":"left"},attrs:{span:3}},[i("router-link",{staticClass:"r_link",attrs:{to:"/"}},[i("span",{staticClass:"curPoi",staticStyle:{"font-size":"28px","vertical-align":"middle","font-style":"italic"}},[t._v("CYSN")])])],1),t._v(" "),i("el-col",{staticStyle:{padding:"0px"},attrs:{span:15}},[i("el-row",{staticStyle:{margin:"0px"},attrs:{gutter:20}},[i("el-col",{staticStyle:{padding:"0px","line-height":"40px"},attrs:{span:8}},[i("span",{staticClass:"curPoi"},[i("img",{staticStyle:{"vertical-align":"middle"},attrs:{src:a("eCy7"),alt:"",width:"10px;"}}),t._v(" "),i("span",{staticStyle:{"font-size":"14px"}},[t._v("Los Abgeles")]),t._v(" "),i("img",{staticStyle:{"vertical-align":"middle"},attrs:{src:a("DmHb"),alt:"",width:"10px;"}})])]),t._v(" "),i("el-col",{staticStyle:{padding:"0px","line-height":"40px"},attrs:{span:16}},[i("div",{staticClass:"flexNowarp",staticStyle:{"font-size":"14px"}},[i("div",{staticClass:"flexItem curPoi"},[t._v("Live Concerts")]),t._v(" "),i("div",{staticClass:"flexItem curPoi"},[t._v("Movies")]),t._v(" "),i("div",{staticClass:"flexItem curPoi"},[t._v("Drama")]),t._v(" "),i("div",{staticClass:"flexItem curPoi"},[t._v("Sport")])])])],1)],1),t._v(" "),i("el-col",{staticStyle:{padding:"0px 20px 0px 0px","text-align":"right"},attrs:{span:6}},[i("div",{staticClass:"curPoi"},[i("router-link",{staticStyle:{"text-decoration":"none"},attrs:{id:"rlink",to:"/accountSet"}},[i("el-avatar",{staticStyle:{"vertical-align":"middle",margin:"0 10px 0 0"},attrs:{src:"../assets/img/login.png",size:40,alt:"",width:"40px;"}}),t._v(" "),i("span",{staticStyle:{"line-height":"40px"}},[t._v(t._s(t.user.user_name))])],1)],1)])],1)],1),t._v(" "),i("el-container",[i("router-view")],1)],1)},staticRenderFns:[]};var c=a("VU/8")(i,s,!1,function(t){a("/CKh")},"data-v-0ccb5035",null);e.default=c.exports},eCy7:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAcCAYAAABh2p9gAAAAAXNSR0IArs4c6QAAAsFJREFUSA2dVUuL01AUbpJSaOvQ0hc6Di4Kuqyg42bEMYMiKrizG1Fc+QPcCIMiiOJC0dW4GhcuRBdFcOWACMYXCK5czEI6pGi1lD4owT5UmsbvZHI7NzfJDO2F9Jzzne98uef23hsp5DOKxaKiadqp0Wh0wbKsg6Dsc2g/JEn6KsvyM1VVX5dKJVMsl0Qgk8kch8gqnv1ijo8hXMZzpdVqveNxhQ9SqdRNCD0BlubxAD8N7uVoNGoNBoOx6FgwnU4vo/AOHs+sAwQJJu5SLBb7B9GPDAjlcrmjw+Hwg0MgfNJhhcPhY41G45NMfwDEHkFhkpmJL5RIg7QktHoCa/FGZFCMRe/C3EX+rROrsNcR76JYHOCfDCNZFBNObGB7HGk2m2Uu/zmbzb4wTfMLsASHM/e8DG+BRbzF224IYnaaMMrxXOZjcgsykrsZwFtFUdb4mPeDctDaQzOc4cnMj0QiHeaLdpvcDAlWxQKK+/3+vB++Q64qo+8Nv0JM/zZtAzFnbw3kRJxiaJWVeDweg3POh7C3UqnMI69htr8pj3M+q+v6c7gqxeLArrgn5fP5hGEYVYj6riWKLMz2GxWDcwCGlskzwOkmEok5pdPp/MVZjIOx6GFtAnSCMs4TeJogeL9Wq63Zb0NbD1BgbNZP9Ws4GiF70dHyH1xDA0idnkouFFqu1+sa1Y7Xo1Ao0AWxTuCEYx21K6zGtSY4p4s4pxqSLpyRfayFU6PiOL5nOdc+w/b4jtZnkTzMCDvY1Xa7TZ2Nx7hlhiSTyWv4x36xOMiCUyOumPdtDa2fQeuvRDIfo9WzaNVzgbhaZgVofQOtzyE+xDDBPkarDwXMDj0tMxJmcBW+zmLO6k6Og7bcQEG008WH5yKo/MfcBHaJclsSbs+3ZUbp9Xo/0TqFSw52Cx/2pyw/laXrCh+yl/T4XWei6H9GQPJRLP9IKAAAAABJRU5ErkJggg=="}});
//# sourceMappingURL=2.857d613370023a995de8.js.map