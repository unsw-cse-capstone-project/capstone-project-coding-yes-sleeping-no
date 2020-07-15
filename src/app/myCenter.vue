<template>
    <el-container class="contour" style="width: 1280px;text-align: center;margin: 0 auto;">
        <el-header style="padding: 0;">
            <el-row :gutter="20" style="margin: 0px;padding: 10px;background-color: white;color: black;">
                <el-col :span="3" style="padding: 0px 0px 0px 20px; text-align: left;">
                    <router-link to="/" class="r_link">
                        <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
                    </router-link>
                </el-col>
                <el-col :span="15" style="padding: 0px;">
                    <el-row :gutter="20" style="margin: 0px;">
                        <el-col :span="8" style="padding: 0px;line-height: 40px;">
                            <span class="curPoi">
                                <img src="../assets/img/location_b.png" alt="" width="10px;" style="vertical-align: middle;" />
                                <span style="font-size: 14px;">Los Abgeles</span>
                                <img src="../assets/img/triangle_b.png" alt="" width="10px;" style="vertical-align: middle;" />
                            </span>
                        </el-col>
                        <el-col :span="16" style="padding: 0px;line-height: 40px;">
                            <div class="flexNowarp" style="font-size: 14px;">
                                <div class="flexItem curPoi">Live Concerts</div>
                                <div class="flexItem curPoi">Movies</div>
                                <div class="flexItem curPoi">Drama</div>
                                <div class="flexItem curPoi">Football</div>
                                <div class="flexItem curPoi">Basketball</div>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="6" style="padding: 0px 20px 0px 0px;text-align: right;">
                    <div class="curPoi">
                        <router-link id="rlink" to="/myCenter" style="text-decoration: none;">
                            <el-avatar src="../assets/img/login.png" :size="40" alt="" width="40px;" style="vertical-align: middle; margin: 0 10px 0 0"></el-avatar>
                            <span style="line-height: 40px;">{{user.user_name}}</span>
                        </router-link>
                    </div>
                </el-col>
            </el-row>
        </el-header>
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
                    <router-link to="/orderMgm" style="text-decoration: none">
                        <el-menu-item index="1">
                            <i class="el-icon-document"></i>
                            <span slot="title">Order Management</span>
                        </el-menu-item>
                    </router-link>
                    <router-link to="/eventMgm" style="text-decoration: none">
                        <el-menu-item index="2">
                            <i class="el-icon-document"></i>
                            <span slot="title">Event Management</span>
                        </el-menu-item>
                    </router-link>
                    <router-link to="/accountSet" style="text-decoration: none; ">
                        <el-menu-item index="3" >
                            <i class="el-icon-setting"></i>
                            <span slot="title">User Profile</span>
                        </el-menu-item>
                    </router-link>
                </el-menu>
            </el-aside>
            <el-main :span="18" style="background-color: white">
                <router-view></router-view>
            </el-main>
        </el-container>
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
        methods: {},
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
    .press { color: transparent; background-color : black; text-shadow : rgba(255,255,255,0.5) 0 5px 6px, rgba(255,255,255,0.2) 1px 3px 3px; -webkit-background-clip : text; }

    .r_link {text-decoration: none; color: black}
    .r_link:visited {color: black}
</style>