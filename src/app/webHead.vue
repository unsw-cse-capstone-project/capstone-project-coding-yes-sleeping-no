<template>
    <el-container class="contour" style="width: 1280px;text-align: center;margin: 0 auto;">
        <el-header style="padding: 0;">
            <el-row :gutter="20" style="margin: 0;padding: 10px;background-color: white; color: black;">
                <el-col :span="5" style="padding: 0 0 0 40px; text-align: left;">
                    <router-link to="/" style="text-decoration: none" class="r_link">
                        <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
                    </router-link>
                </el-col>
                <el-col :span="15" style="padding: 0px;">
                    <el-row :gutter="20" style="margin: 0px;">
                        <el-col :span="16" style="padding: 0px;line-height: 40px;">
                            <div class="flexNowarp" style="font-size: 14px;">
                                <div class="flexItem curPoi">Live Concerts</div>
                                <div class="flexItem curPoi">Movies</div>
                                <div class="flexItem curPoi">Drama</div>
                                <div class="flexItem curPoi">Sport</div>
                            </div>
                        </el-col>
                        <el-col :span="8" style="padding: 0;line-height: 40px;">
                            <el-select
                                    v-model="value"
                                    multiple
                                    filterable
                                    remote
                                    reserve-keyword
                                    placeholder="Search"
                                    :remote-method="remoteMethod"
                                    :loading="loading">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="4" style="padding: 0px 20px 0px 0px;text-align: right;">
                    <div class="curPoi">
                        <el-dropdown @command="handleCommand">
                            <span class="el-dropdown-link" style="color: black">
                              <el-avatar src="../assets/img/Jannabi.png" :size="40" alt="" width="40px;" style="vertical-align: middle; margin: 0 10px 0 0"></el-avatar>
                              <span style="line-height: 40px;">{{user.user_name}}</span>
                              <i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="orderMgm">Order Management</el-dropdown-item>
                                <el-dropdown-item command="accountSet">User Profile</el-dropdown-item>
                                <el-dropdown-item v-if="user.status===1" disabled>Event Management</el-dropdown-item>
                                <el-dropdown-item v-else-if="user.status===2" command="eventMgm">Event Management</el-dropdown-item>
                                <el-dropdown-item divided command="logout">Logout</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </el-col>
            </el-row>
        </el-header>
        <el-container>
            <router-view></router-view>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "webHead",
        data() {
            return {
                user: {},
                options: [],
                value: [],
                list: [],
                loading: false,
                states: ["Alabama", "Alaska", "Arizona",
                    "Arkansas", "California", "Colorado",
                    "Connecticut", "Delaware", "Florida",
                    "Georgia", "Hawaii", "Idaho", "Illinois",
                    "Indiana", "Iowa", "Kansas", "Kentucky",
                    "Louisiana", "Maine", "Maryland",
                    "Massachusetts", "Michigan", "Minnesota",
                    "Mississippi", "Missouri", "Montana",
                    "Nebraska", "Nevada", "New Hampshire",
                    "New Jersey", "New Mexico", "New York",
                    "North Carolina", "North Dakota", "Ohio",
                    "Oklahoma", "Oregon", "Pennsylvania",
                    "Rhode Island", "South Carolina",
                    "South Dakota", "Tennessee", "Texas",
                    "Utah", "Vermont", "Virginia",
                    "Washington", "West Virginia", "Wisconsin",
                    "Wyoming"]
            }
        },
        mounted() {
            this.list = this.states.map(item => {
                return { value: `value:${item}`, label: `label:${item}` };
            });
        },
        methods: {
            handleCommand(command) {
                if(command !== 'logout') {
                    location.href='#/'+command;
                }
                else {
                    this.$http.get("/user/logout").then(
                        res=>{
                            if(res.state){
                                this.$message({
                                    message: res.msg,
                                    type: 'success'
                                });
                                localStorage.removeItem("user");
                                this.user = {};
                                location.href = '/';
                            }
                            else {
                                this.$message({
                                    message: res.msg,
                                    type: 'fail'
                                });
                            }
                        });
                }
            },
            remoteMethod(query) {
                if (query !== '') {
                    this.loading = true;
                    setTimeout(() => {
                        this.loading = false;
                        this.options = this.list.filter(item => {
                            return item.label.toLowerCase()
                                .indexOf(query.toLowerCase()) > -1;
                        });
                    }, 200);
                } else {
                    this.options = [];
                }
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
        }
    }
</script>

<style scoped>
    .r_link {text-decoration: none; color: black}
    .r_link:visited {color: black}
    #rlink {
        color: black
    }
    #rlink :visited {
        color: black;
    }
</style>