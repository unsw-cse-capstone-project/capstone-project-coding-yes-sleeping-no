webpackJsonp([4],{"25To":function(t,e){},nyvT:function(t,e,l){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={methods:{handleClick(t){console.log(t);let e=t.id;location.href="#/eventDetail/"+e},clearFilter(){this.$refs.filterTable.clearFilter()},filterHandler:(t,e,l)=>e[l.property]===t},data:()=>({tableData:[],user:{}}),created(){let t=localStorage.getItem("user");t?this.user=JSON.parse(t).user:(alert("You have not logged in yet, click OK to jump to the login page!"),location.href="/"),"1"===this.user.status&&this.$http.get("/order/get/customer",this.user).then(t=>{this.tableData=t.order}),"2"===this.user.status&&this.$http.get("/order/get/host",this.user).then(t=>{this.tableData=t.order})}},r={render:function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",[l("el-button",{staticStyle:{float:"left"},on:{click:t.clearFilter}},[t._v("reset all filters")]),t._v(" "),l("el-table",{ref:"filterTable",staticStyle:{width:"100%"},attrs:{data:t.tableData,"default-sort":{prop:"Date",order:"increasing"},stripe:"",height:"650"}},[l("el-table-column",{attrs:{fixed:"",sortable:"",prop:"eventName",label:"Event name",width:"150"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"type",label:"Type",width:"120","column-key":"type",filters:[{text:"Live Concerts",value:"Live Concerts"},{text:"Movies",value:"Movies"},{text:"Drama",value:"Drama"},{text:"Sports",value:"Sports"}],"filter-method":t.filterHandler}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"location",label:"Location",width:"200"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"date",label:"Date",width:"100"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"time",label:"Time",width:"100"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"price",label:"Price",width:"100"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"ticket",label:"Tickets",width:"100"}}),t._v(" "),l("el-table-column",{attrs:{fixed:"right",label:"Operate",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-button",{attrs:{type:"text",size:"small"},on:{click:function(l){t.handleClick(e.row)}}},[t._v("View")]),t._v(" "),"1"===this.user.status?l("el-button",{attrs:{type:"text",size:"small"}},[t._v("Cancel")]):t._e()]}}])})],1)],1)},staticRenderFns:[]};var o=l("VU/8")(a,r,!1,function(t){l("25To")},"data-v-e77419c0",null);e.default=o.exports}});
//# sourceMappingURL=4.544f68a9cddb6f894db3.js.map