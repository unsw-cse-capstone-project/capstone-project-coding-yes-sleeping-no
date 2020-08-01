webpackJsonp([7],{WweW:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={name:"accountSet",data:()=>({options:[{value:"ACT",label:"ACT"},{value:"NSW",label:"NSW"},{value:"NT",label:"NT"},{value:"QLD",label:"QLD"},{value:"SA",label:"SA"},{value:"TAS",label:"TAS"},{value:"VIC",label:"VIC"},{value:"WA",label:"WA"},{value:"JBT",label:"JBT"}],user:{},imageUrl:""}),methods:{edit(){this.$http.post("/user/updateUserInfo",this.user).then(e=>{e.state?(this.$message({message:e.msg,type:"success"}),localStorage.setItem("user",JSON.stringify(e)),location.reload()):this.$message({message:e.msg,type:"fail"})})},getBase64:e=>(console.log(e),new Promise((t,s)=>{let a=new FileReader,l="";a.readAsDataURL(e),a.onload=function(){l=a.result},a.onerror=function(e){s(e)},a.onloadend=function(){t(l)}})),httpRequest(e){console.log(e);const t="image/jpeg"===e.file.type,s="image/png"===e.file.type,a="image/gif"===e.file.type,l=e.file.size/1024/1024<2;t||s||a?l?(this.getBase64(e.file).then(e=>{this.user.avatar=e,console.log(e)}),this.imageUrl=URL.createObjectURL(e.file),this.$message.success("File upload successfully!")):this.$message.error("The size of profile picture must be less than 2MB!"):this.$message.error("The format of profile picture must be JPG or PNG or GIF!")}},created(){let e=localStorage.getItem("user");e?(this.user=JSON.parse(e).user,this.imageUrl=this.getPic(this.user.avatar)):(alert("You have not logged in yet, click OK to jump to the login page!"),location.href="/")}},l={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-col",{staticClass:"accountInfo",attrs:{span:12}},[s("h1",{staticClass:"press"},[e._v("Account Information")]),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Username:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:e.user.user_name,disabled:""}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Email address:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:e.user.email,disabled:""}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("User balance")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:e.user.user_balance,disabled:""}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Password:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("router-link",{attrs:{to:"/resetPwd"}},[s("el-button",{staticStyle:{width:"313px"},attrs:{type:"primary",icon:"el-icon-key"}},[e._v("Reset password")])],1)],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("First name:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Input your first name"},model:{value:e.user.first_name,callback:function(t){e.$set(e.user,"first_name",t)},expression:"user.first_name"}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Last name:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Input your last name"},model:{value:e.user.last_name,callback:function(t){e.$set(e.user,"last_name",t)},expression:"user.last_name"}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Phone number:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Input your phone number"},model:{value:e.user.phone,callback:function(t){e.$set(e.user,"phone",t)},expression:"user.phone"}})],1)],1),e._v(" "),s("h1",{staticClass:"press"},[e._v("Mail Address")]),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Address line 1:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Street address, P.O. box, company name, c\\o"},model:{value:e.user.address_1,callback:function(t){e.$set(e.user,"address_1",t)},expression:"user.address_1"}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Address line 2:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Apartment, suite, unit, building, floor, etc."},model:{value:e.user.address_2,callback:function(t){e.$set(e.user,"address_2",t)},expression:"user.address_2"}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("City:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Input your city"},model:{value:e.user.city,callback:function(t){e.$set(e.user,"city",t)},expression:"user.city"}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("State/Province:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-select",{staticStyle:{width:"314px"},attrs:{placeholder:"Select"},model:{value:e.user.state,callback:function(t){e.$set(e.user,"state",t)},expression:"user.state"}},e._l(e.options,function(e){return s("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{staticClass:"abHeight",attrs:{span:8}},[e._v("Zip Code:")]),e._v(" "),s("el-col",{attrs:{span:16}},[s("el-input",{attrs:{placeholder:"Input your Zip code"},model:{value:e.user.postcode,callback:function(t){e.$set(e.user,"postcode",t)},expression:"user.postcode"}})],1)],1),e._v(" "),s("el-row",{staticClass:"allWidth"},[s("el-col",{attrs:{span:8}},[s("button",{staticClass:"push_button blue",on:{click:e.edit}},[e._v("Save")])]),e._v(" "),s("el-col",{staticClass:"abHeight",staticStyle:{"text-align":"right"},attrs:{span:16}},[s("router-link",{attrs:{to:"/"}},[s("button",{staticClass:"push_button red"},[e._v("Cancel")])])],1)],1)],1),e._v(" "),s("el-col",{attrs:{id:"profile_pic",span:12}},[s("h1",[e._v("Avatar")]),e._v(" "),s("el-upload",{staticClass:"avatar-uploader",attrs:{action:"#","show-file-list":!1,"http-request":e.httpRequest}},[e.imageUrl?s("img",{staticClass:"avatar",attrs:{src:e.imageUrl}}):s("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)],1)},staticRenderFns:[]};var r=s("VU/8")(a,l,!1,function(e){s("agyp")},"data-v-2f821a2e",null);t.default=r.exports},agyp:function(e,t){}});
//# sourceMappingURL=7.a5e5ae2207fab7c161cc.js.map