webpackJsonp([8],{FhlQ:function(s,t){},IJbM:function(s,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var e={data:()=>({password:"",confirm_password:""}),methods:{upload(){if(null!==this.password&&""!==this.password&&void 0!==this.password&&null!==this.confirm_password&&""!==this.confirm_password&&void 0!==this.confirm_password&&this.password===this.confirm_password){let s={newPassword:this.password};this.$http.post("/user/resetPassword",s).then(s=>{s.state?(this.$message({message:s.msg,type:"success"}),localStorage.setItem("user",JSON.stringify(s.user))):this.$message({message:s.msg,type:"fail"})}),location.href="/"}}}},r={render:function(){var s=this,t=s.$createElement,o=s._self._c||t;return o("div",{staticClass:"pwdbox"},[o("h2",[s._v("Reset password")]),s._v(" "),o("el-input",{staticStyle:{width:"350px","margin-top":"20px"},attrs:{placeholder:"Input your new password","show-password":""},model:{value:s.password,callback:function(t){s.password=t},expression:"password"}}),s._v(" "),o("el-input",{staticStyle:{width:"350px","margin-top":"20px"},attrs:{placeholder:"Confirm your new password","show-password":""},model:{value:s.confirm_password,callback:function(t){s.confirm_password=t},expression:"confirm_password"}}),s._v(" "),o("el-row",[o("el-popconfirm",{attrs:{confirmButtonText:"Confirm",cancelButtonText:"Cancel",icon:"el-icon-info",iconColor:"red",title:"Confirm to change the password？"}},[o("el-button",{staticStyle:{"margin-top":"40px",margin:"50px"},attrs:{slot:"reference",type:"primary",round:""},on:{click:s.upload},slot:"reference"},[s._v("Confirm")])],1),s._v(" "),o("router-link",{attrs:{to:"/"}},[o("el-button",{attrs:{round:""}},[s._v("Cancel")])],1)],1)],1)},staticRenderFns:[]};var a=o("VU/8")(e,r,!1,function(s){o("FhlQ")},"data-v-2b06bdf8",null);t.default=a.exports}});
//# sourceMappingURL=8.d79c8a35ad4fa293ff8f.js.map