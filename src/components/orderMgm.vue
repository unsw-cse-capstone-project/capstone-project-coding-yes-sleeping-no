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
                    prop="eventName"
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
                    prop="location"
                    label="Location"
                    width="200">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="date"
                    label="Date"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="time"
                    label="Time"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="price"
                    label="Price"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="ticket"
                    label="Tickets"
                    width="100">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="rate"
                    label="Rate"
                    width="100">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="Operate"
                    width="120">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">View</el-button>
                    <el-button type="text" size="small">Cancel</el-button>
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
            },
            clearFilter() {
                this.$refs.filterTable.clearFilter();
            },
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            }
        },
        data () {
            return {
                tableData: [{
                    eventName: 'AFL Melbourne Demons - Brisbane Lions',
                    type: 'Sports',
                    location: 'Metricon Stadium, Gold Coast ',
                    date: '24-7-2020',
                    time: '18:10',
                    price: '99',
                    ticket: 175,
                    rate: 4.3
                }]
            }
        }
    }
</script>

<style scoped>

</style>