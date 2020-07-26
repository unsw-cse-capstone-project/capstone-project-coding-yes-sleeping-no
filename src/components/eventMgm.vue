<template>
    <div>
        <el-button style="float: left" @click="clearFilter" >reset all filters</el-button>
        <el-table
                ref="filterTable"
                :data="tableData"
                :default-sort = "{prop: 'Date', order: 'increasing'}"
                stripe
                style="width: 100%"
                height="650">
            <el-table-column
                    fixed
                    sortable
                    prop="title"
                    label="Event name"
                    width="150">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="type"
                    label="Type"
                    width="120"
                    column-key="type"
                    :filters="[{text: 'Live Concerts', value: 'Live Concerts'}, {text: 'Movies', value: 'Movies'}, {text: 'Drama', value: 'Drama'}, {text: 'Sports', value: 'Sports'}]"
                    :filter-method="filterHandler"
            >
            </el-table-column>
            <el-table-column
                    sortable
                    prop="address"
                    label="Location"
                    width="200">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="start_date"
                    label="Date"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="start_time"
                    label="Time"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="ticket_price"
                    label="Price"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="available_tickets"
                    label="Inventory"
                    width="110">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="Operate"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">View</el-button>
                    <el-button type="text" size="small" @click="del_event(scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>
        <router-link to="/addEvent">
            <el-button style="margin-top: 20px;float: left" type="primary" icon="el-icon-circle-plus-outline">Add new event</el-button>
        </router-link>

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
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            },
            del_event(row) {
                let id = row.id;
                this.$http.get("/event/delete/"+id).then(
                    res=>{
                        if(res.state){
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            location.href ="/eventMgm";
                        }
                        else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            });
                        }
                    })
            }
        },
        data() {
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
            this.$http.get("/event/findByHost", this.user).then(
                res=>{
                    // console.log(res);
                    this.tableData = res.event;
                })
        }
    }
</script>

<style scoped>

</style>