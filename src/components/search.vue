<template>
    <el-container style="background-color: white">
        <div class="searchbox">
            <el-row>
                <el-col :span="18">
                    <el-input
                            size="large"
                            placeholder="Search Event/Type"
                            prefix-icon="el-icon-search"
                            v-model="keyword"
                            @change="search"
                    >
                    </el-input>
                </el-col>
                <el-col :span="6">
                    <el-button style="width: 200px; float: right" type="primary" icon="el-icon-search" @click="search">Search</el-button>
                </el-col>
            </el-row>
            <el-row style="margin-top: 20px;">
                <el-table
                        ref="filterTable"
                        :data="tableData"
                        :default-sort = "{prop: 'Date', order: 'increasing'}"
                        stripe
                        style="width: 100%"
                        height="500"
                        max-height="500">
                    <el-table-column
                            fixed
                            sortable
                            prop="title"
                            label="Event name"
                            width="250"
                    >
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
                            width="250">
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
                </el-table>
            </el-row>
            <el-row style="margin-top: 80px;padding: 40px 50px;border-top: 1px solid #121212;">
                <el-col :span="8" style="text-align: left;font-size: 16px;color: #121212;">
                    <div class="flexNowarp" style="font-size: 14px;">
                        <div class="flexItem curPoi">@CYSN copy @2020</div>
                    </div>
                </el-col>
                <el-col :span="8" style="text-align: left;font-size: 16px;color: #121212;">
                    <div class="flexNowarp" style="font-size: 14px;">
                        <div class="flexItem curPoi">Help</div>
                        <div class="flexItem curPoi">Contract</div>
                        <div class="flexItem curPoi">About us</div>
                    </div>
                </el-col>
                <el-col :span="8" style="text-align: right;">
                    <img src="../assets/img/twitter.png" alt="" width="22px" height="20px" style="padding-right: 30px;" />
                    <img src="../assets/img/ins.png" alt="" width="20px" height="20px" style="padding-right: 30px;" />
                    <img src="../assets/img/facebook.png" alt="" width="20px" height="20px" />
                </el-col>
            </el-row>
        </div>
    </el-container>
</template>

<script>
    export default {
        data () {
            return {
                keyword: '',
                tableData: [],
            }
        },
        methods: {
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            },
            search() {
                if (this.keyword !== null && this.keyword !== '' && this.keyword !== undefined) {
                    location.href='#/search/'+this.keyword;
                    location.reload();
                } else {
                    this.$message({
                        message: 'Wrong keyword!',
                        type: 'fail'
                    })
                }
            }
        },
        created() {
            let keyword = {keyword: this.$route.params.keyword};
            this.keyword = this.$route.params.keyword;
            if (keyword !== null && keyword !== '' && keyword !== undefined) {
                this.$http.post("/event/findByKeyword", keyword).then(
                    res=>{
                        console.log(res);
                        if(res.state){
                            this.tableData = res.event;
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                        }
                        else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            })
                        }
                    });
            }
        }
    }
</script>

<style scoped>
    .searchbox{
        /*margin-top: 30px;*/
        width: 1200px;
        height: 630px;
        background: #f3f3f3;
        box-shadow: 0px 15px 25px 0 rgba(0,0,0,0.2);
        padding: 40px;
        box-sizing: border-box;
        position: absolute;
        top: 47%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -moz-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        -o-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
</style>