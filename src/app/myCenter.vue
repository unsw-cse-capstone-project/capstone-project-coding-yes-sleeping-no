<template>
    <el-container>
        <el-aside :span="6">
            <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    background-color="#f3f3f3"
                    text-color="#000"
                    active-text-color="#ffd04b"
            >
                <h2 class="press">My Center</h2>
                <router-link to="/orderMgm" style="text-decoration: none; text-align: left;">
                    <el-menu-item index="1" style="margin: 0 40px">
                        <i class="el-icon-document"></i>
                        <span slot="title">Order Management</span>
                    </el-menu-item>
                </router-link>
                <router-link to="/eventMgm" style="text-decoration: none; text-align: left;" v-if="this.user.status===2">
                    <el-menu-item index="3" style="margin: 0 40px">
                        <i class="el-icon-document"></i>
                        <span slot="title">Event Management</span>
                    </el-menu-item>
                </router-link>
                <router-link to="/accountSet" style="text-decoration: none; text-align: left;">
                    <el-menu-item index="2" style="margin: 0 40px">
                        <i class="el-icon-setting"></i>
                        <span slot="title">User Profile</span>
                    </el-menu-item>
                </router-link>
                <div style="text-decoration: none; text-align: left;">
                    <el-menu-item index="4" style="margin: 0 40px" @click="logout" >
                        <i class="el-icon-setting"></i>
                        <span slot="title">logout</span>
                    </el-menu-item>
                </div>

            </el-menu>
        </el-aside>
        <el-main :span="18" style="background-color: white">
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "myCenter",
        data() {
            return {
                user: {}
            }
        },
        methods: {
            logout() {
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
        created() {
            let userString = localStorage.getItem("user");
            if(userString){
                this.user =  JSON.parse(userString).user;
            }

        }
    }

</script>

<style scoped>
    .press { color: transparent; background-color : black; text-shadow : rgba(255,255,255,0.5) 0 5px 6px, rgba(255,255,255,0.2) 1px 3px 3px; -webkit-background-clip : text; }
</style>