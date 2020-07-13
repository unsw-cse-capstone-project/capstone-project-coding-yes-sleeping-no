<template>
    <!--<div class="contour" style="width: 1280px;text-align: center;margin: 0 auto;">
        <el-row :gutter="20" style="margin: 0px;padding: 10px;background-color: white;color: black;">
            <el-col :span="3" style="padding: 0px 0px 0px 20px; text-align: left;">
                <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
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
                    <img src="../assets/img/login.png" alt="" width="40px;" style="vertical-align: middle;"/>
                    <span>Sign In/Sign Up</span>
                </div>
            </el-col>
        </el-row>
    </div>-->
    <el-container class="contour" style="width: 1280px;text-align: center;margin: 0 auto;">
        <el-header style="padding: 0;">
            <el-row :gutter="20" style="margin: 0px;padding: 10px;background-color: white;color: black;">
                <el-col :span="3" style="padding: 0px 0px 0px 20px; text-align: left;">
                    <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
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
                        <img src="../assets/img/login.png" alt="" width="40px;" style="vertical-align: middle;"/>
                        <span>Sign In/Sign Up</span>
                    </div>
                </el-col>
            </el-row>
        </el-header>
        <el-container>
            <el-aside :span="6">
                <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#f3f3f3"
                        text-color="#000"
                        active-text-color="#ffd04b">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>导航一</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-menu-item index="2">
                        <i class="el-icon-menu"></i>
                        <span slot="title">导航二</span>
                    </el-menu-item>
                    <el-menu-item index="3" disabled>
                        <i class="el-icon-document"></i>
                        <span slot="title">导航三</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-setting"></i>
                        <span slot="title">导航四</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main :span="18">Main</el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "myCenter"
    }
</script>

<style scoped>

</style>