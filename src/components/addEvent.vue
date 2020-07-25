<template>
    <div>
        <el-col :span="12">
            <el-form label-position="left" ref="form" :model="form" label-width="120px">
                <el-form-item class="labels" label="Event name:">
                    <el-input v-model="form.title"></el-input>
                </el-form-item>
                <el-form-item class="labels" label="Event type:" style="text-align: left">
                    <el-select v-model="form.type" placeholder="Event type" style="margin: 0">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="labels" label="Tickets:" style="text-align: left">
                    <el-input-number v-model="form.num" controls-position="right" :min="1" :max="10000"></el-input-number>
                </el-form-item>

                <el-form-item class="labels" label="Location:">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item class="labels" label="Date:">
                    <el-col :span="11">
                        <el-date-picker type="date" placeholder="Select Date" v-model="form.start_date" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
                    </el-col>
                </el-form-item>
                <el-form-item class="labels" label="Time:">
                    <el-col :span="11">
                        <el-time-picker
                                v-model="form.start_time"
                                :picker-options="{
                                    selectableRange: '0:0:00 - 23:59:00'
                                }"
                                placeholder="Select Start Time"
                                value-format="yyyy-MM-dd HH:mm:ss">
                        </el-time-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-time-picker
                                v-model="form.end_time"
                                :picker-options="{
                                    selectableRange: '0:0:00 - 23:59:00'
                                }"
                                placeholder="Select End Time"
                                value-format="yyyy-MM-dd HH:mm:ss">
                        </el-time-picker>
                    </el-col>
                </el-form-item>
                <el-form-item class="labels" label="Price:">
                    <el-input v-model="form.ticket_price"></el-input>
                </el-form-item>
                <el-form-item class="labels" label="Description:">
                    <el-input type="textarea" v-model="form.description"></el-input>
                </el-form-item>
                <el-row class="allWidth">
                    <el-col :span="8"  >
                        <button  class="push_button blue" @click="edit">Save</button>
                    </el-col>
                    <el-col :span="16" class="abHeight" style="text-align: right">
                        <button class="push_button red">Cancel</button>
                    </el-col>
                </el-row>
            </el-form>
        </el-col>
        <el-col :span="12">
            <h1>upload img</h1>
        </el-col>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {},
                options: [{
                    value: 'Live Concerts',
                    label: 'Live Concerts'
                }, {
                    value: 'Movies',
                    label: 'Movies'
                }, {
                    value: 'Drama',
                    label: 'Drama'
                }, {
                    value: 'Sport',
                    label: 'Sport'
                }],
            }
        },
        methods: {
            edit() {
                this.form.rate = 0.0;
                this.$http.post("/event/create", this.form).then(
                    res=>{
                        if(res.state){
                            this.form = res.event;
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            location.href='#/eventMgm';
                        }
                        else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            });
                            this.user = {}
                        }
                    });
            }
        }
    }
</script>

<style scoped>
    .labels{
        margin: 20px;
        font-size: 20px;
        font-weight: bold;
        color: black;
    }

    .allWidth {
        margin: 20px;
        text-align: left;
        display: flex;
        align-items: center;
    }
    .push_button {
        position: relative;
        width:100px;
        height:40px;
        text-align:center;
        color:#FFF;
        text-decoration:none;
        line-height:43px;
        font-family:'Oswald', Helvetica;
        display: block;
        margin: 30px;
    }
    .push_button:before {
        background:#f0f0f0;
        background-image:-webkit-gradient(linear, 0% 0%, 0% 100%, from(#D0D0D0), to(#f0f0f0));

        -webkit-border-radius:5px;
        -moz-border-radius:5px;
        border-radius:5px;

        -webkit-box-shadow:0 1px 2px rgba(0, 0, 0, .5) inset, 0 1px 0 #FFF;
        -moz-box-shadow:0 1px 2px rgba(0, 0, 0, .5) inset, 0 1px 0 #FFF;
        box-shadow:0 1px 2px rgba(0, 0, 0, .5) inset, 0 1px 0 #FFF;

        position: absolute;
        content: "";
        left: -6px; right: -6px;
        top: -6px; bottom: -10px;
        z-index: -1;
    }

    .push_button:active {
        -webkit-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset;
        top:5px;
    }
    .push_button:active:before{
        top: -11px;
        bottom: -5px;
        content: "";
    }

    .red {
        text-shadow:-1px -1px 0 #A84155;
        background: #D25068;
        border:1px solid #D25068;

        background-image:-webkit-linear-gradient(top, #F66C7B, #D25068);
        background-image:-moz-linear-gradient(top, #F66C7B, #D25068);
        background-image:-ms-linear-gradient(top, #F66C7B, #D25068);
        background-image:-o-linear-gradient(top, #F66C7B, #D25068);
        background-image:linear-gradient(to bottom, #F66C7B, #D25068);

        -webkit-border-radius:5px;
        -moz-border-radius:5px;
        border-radius:5px;

        -webkit-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #AD4257, 0 4px 2px rgba(0, 0, 0, .5);
        -moz-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #AD4257, 0 4px 2px rgba(0, 0, 0, .5);
        box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #AD4257, 0 4px 2px rgba(0, 0, 0, .5);
    }

    .red:hover {
        background: #F66C7B;
        background-image:-webkit-linear-gradient(top, #D25068, #F66C7B);
        background-image:-moz-linear-gradient(top, #D25068, #F66C7B);
        background-image:-ms-linear-gradient(top, #D25068, #F66C7B);
        background-image:-o-linear-gradient(top, #D25068, #F66C7B);
        background-image:linear-gradient(top, #D25068, #F66C7B);
    }

    .blue {
        text-shadow:-1px -1px 0 #2C7982;
        background: #3EACBA;
        border:1px solid #379AA4;
        background-image:-webkit-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:-moz-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:-ms-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:-o-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:linear-gradient(top, #48C6D4, #3EACBA);

        -webkit-border-radius:5px;
        -moz-border-radius:5px;
        border-radius:5px;

        -webkit-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #338A94, 0 4px 2px rgba(0, 0, 0, .5);
        -moz-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #338A94, 0 4px 2px rgba(0, 0, 0, .5);
        box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #338A94, 0 4px 2px rgba(0, 0, 0, .5);
    }

    .blue:hover {
        background: #48C6D4;
        background-image:-webkit-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:-moz-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:-ms-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:-o-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:linear-gradient(top, #3EACBA, #48C6D4);
    }

</style>