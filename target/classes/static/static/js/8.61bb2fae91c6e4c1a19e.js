webpackJsonp([8],{EMpc:function(t,e){},nyvT:function(t,e,l){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={methods:{handleClick(t){console.log(t);let e=t.id;location.href="#/eventDetail/"+e},clearFilter(){this.$refs.filterTable.clearFilter()}},data:()=>({tableData:[],user:{}}),created(){let t=localStorage.getItem("user");t?this.user=JSON.parse(t).user:(alert("You have not logged in yet, click OK to jump to the login page!"),location.href="/"),1===this.user.status&&this.$http.get("/order/get/customer").then(t=>{this.tableData=t.allOrders,console.log(this.tableData)}),2===this.user.status&&this.$http.get("/order/get/host").then(t=>{this.tableData=t.allOrders})}},r={render:function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",[l("el-button",{staticStyle:{float:"left"},on:{click:t.clearFilter}},[t._v("reset all filters")]),t._v(" "),l("el-table",{ref:"filterTable",staticStyle:{width:"100%"},attrs:{data:t.tableData,"default-sort":{prop:"order_number",order:"increasing"},stripe:"",height:"650"}},[l("el-table-column",{attrs:{sortable:"",prop:"order_number",label:"Order Number",width:"150"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"ticket_amount",label:"Ticket Amount",width:"200"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"total_price",label:"Total Price",width:"200"}}),t._v(" "),l("el-table-column",{attrs:{sortable:"",prop:"status",label:"Status",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row.status;return[0===a?l("span",[t._v("No Pay")]):1===a?l("span",[t._v("Paid")]):2===a?l("span",[t._v("Refund")]):t._e()]}}])}),t._v(" "),l("el-table-column",{attrs:{sortable:"",label:"Operate",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-button",{attrs:{type:"text",size:"small"},on:{click:function(l){t.handleClick(e.row)}}},[t._v("View")]),t._v(" "),1===e.row.status?l("el-button",{attrs:{type:"text",size:"small"}},[t._v("Cancel")]):t._e()]}}])})],1)],1)},staticRenderFns:[]};var s=l("VU/8")(a,r,!1,function(t){l("EMpc")},"data-v-1b941196",null);e.default=s.exports}});
//# sourceMappingURL=8.61bb2fae91c6e4c1a19e.js.map