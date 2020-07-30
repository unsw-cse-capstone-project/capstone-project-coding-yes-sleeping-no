<template>
    <div>
        <el-button style="float: left" @click="clearFilter" >reset all filters</el-button>
        <el-table
                ref="filterTable"
                :data="tableData"
                :default-sort = "{prop: 'order_number', order: 'increasing'}"
                stripe
                style="width: 100%"
                height="650">
            <el-table-column
                    sortable
                    prop="order_number"
                    label="Order Number"
                    width="150">
            </el-table-column>
<!--            <el-table-column-->
<!--                    sortable-->
<!--                    prop="type"-->
<!--                    label="Type"-->
<!--                    width="120"-->
<!--                    column-key="type"-->
<!--                    :filters="[{text: 'Live Concerts', value: 'Live Concerts'}, {text: 'Movies', value: 'Movies'}, {text: 'Drama', value: 'Drama'}, {text: 'Sports', value: 'Sports'}]"-->
<!--                    :filter-method="filterHandler"-->
<!--            >-->
<!--            </el-table-column>-->
            <el-table-column
                    sortable
                    prop="ticket_amount"
                    label="Ticket Amount"
                    width="200">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="total_price"
                    label="Total Price"
                    width="200">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="status"
                    label="Status"
                    width="200">
                <template slot-scope="{row: {status}}">
                    <span v-if="status === 0">No Pay</span>
                    <span v-else-if="status === 1">Paid</span>
                    <span v-else-if="status === 2">Refund</span>
                </template>
            </el-table-column>
            <el-table-column
                    sortable
                    label="Operate"
                    width="120">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">View</el-button>
                    <el-button type="text" size="small" v-if="scope.row.status === 1">Cancel</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        methods: {
            handleClick (row) {
                console.log(row);
                let id = row.id;
                location.href = "#/eventDetail/"+id;
            },
            clearFilter() {
                this.$refs.filterTable.clearFilter();
            },
            // filterHandler(value, row, column) {
            //     const property = column['property'];
            //     return row[property] === value;
            // }
        },
        data () {
            return {
                tableData: [],
                user: {}
            }
        },
        created() {
            let userString = localStorage.getItem("user");
            if(userString){
                this.user =  JSON.parse(userString).user;
            } else{
                alert("You have not logged in yet, click OK to jump to the login page!");
                location.href ="/";
            }
            if(this.user.status === 1){   // user is a customer
                this.$http.get("/order/get/customer").then(
                    res=>{
                        // console.log(res);
                        this.tableData = res.allOrders;
                        console.log(this.tableData);
                    })
            }
            if(this.user.status === 2){   // user is a host
                this.$http.get("/order/get/host").then(
                    res=>{
                        // console.log(res);
                        this.tableData = res.allOrders;
                    })
            }

        }
    }
</script>

<style scoped>

</style>