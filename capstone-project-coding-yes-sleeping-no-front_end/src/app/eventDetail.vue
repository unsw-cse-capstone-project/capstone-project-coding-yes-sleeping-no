<template>
<!--    Scroll down to see the bottom-right button.-->
    <div class="contour" style="background-color: white">
        <el-row style="padding: 30px; height: 666px">
            <el-col :span="16">
                <el-row :gutter="30">
                    <el-col :span="12">
                        <img src="../assets/img/Joker2.png" alt="" height="560px" style="border-radius: 15px; display: block; margin: 0 auto" />
                    </el-col>
                    <el-col :span="12" style="height: 100%;">
                        <el-row style="text-align: left; padding: 5px">
                            <span style="font-size: 32px;">Joker (2019)</span>
                        </el-row>
                        <el-rate
                                v-model="star_value"
                                disabled
                                show-score
                                text-color="#ff9900"
                                score-template="{value}"
                                style="text-align: left; padding: 10px 0">
                        </el-rate>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Location: </span>
                            </el-col>
                            <el-col :span="18">
                                <el-select v-model="loc_value" placeholder="Select">
                                    <el-option
                                            v-for="item in location_options"
                                            :key="item.loc_value"
                                            :label="item.label"
                                            :value="item.loc_value">
                                    </el-option>
                                </el-select>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Date: </span>
                            </el-col>
                            <el-col :span="18">
                                <el-select v-model="value" placeholder="Select">
                                    <el-option
                                            v-for="item in date_options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Time: </span>
                            </el-col>
                            <el-col :span="18">
                                <el-select v-model="time_value" placeholder="Select">
                                    <el-option
                                            v-for="item in time_options"
                                            :key="item.time_value"
                                            :label="item.label"
                                            :value="item.time_value">
                                    </el-option>
                                </el-select>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Price: </span>
                            </el-col>
                            <el-col :span="18" style="padding:10px 0px">
                                <span style="color: red;">${{this.price}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left;padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Ticket: </span>
                            </el-col>
                            <el-col :span="18" style="padding:10px 0px">
                                <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span style="font-size: 24px">Amount: </span>
                            </el-col>
                            <el-col :span="18" style="padding:10px 0px">
                                <span style="color: red; font-size: 24px">{{this.amount}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 5px">
                            <el-button type="primary">Book and Pay</el-button>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row style="margin: 50px 0">
                    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                        <el-menu-item index="1"  style="font-size: 20px"><a href="#description">Description</a></el-menu-item>
                        <el-menu-item index="2"  style="font-size: 20px"><a href="#comment">Comments</a></el-menu-item>
                    </el-menu>
                </el-row>

                <el-row id="description" name="description" style="text-align: left">
                    <h2>Description</h2>
                    <p>This is the introduction of the event details.This is the introduction of the event details.This is the introduction of the event details.This is the introduction of the event details.This is the introduction of the event details.</p>
                </el-row>
                <el-row id="comment" name="comment" style="text-align: left">
                    <h2>Comments</h2>
                    <div>
                        <el-row>
                            <el-col :span="18">
                                <el-col :span="2">
                                    <el-avatar icon="el-icon-user-solid"></el-avatar>
                                </el-col>
                                <el-col :span="22">
                                    <el-row>username</el-row>
                                    <el-row>30/06/2020 18:00</el-row>
                                </el-col>
                            </el-col>
                            <el-col :span="6">
                                <el-rate
                                        v-model="comment_value"
                                        disabled
                                        show-score
                                        text-color="#ff9900"
                                        score-template="{value}"
                                        style="text-align: left; padding: 10px 0">
                                </el-rate>
                            </el-col>
                        </el-row>
                        <el-row>
                            <p>This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user.</p>
                        </el-row>
                    </div>
                    <div>
                        <el-row>
                            <el-col :span="18">
                                <el-col :span="2">
                                    <el-avatar icon="el-icon-user-solid"></el-avatar>
                                </el-col>
                                <el-col :span="22">
                                    <el-row>username</el-row>
                                    <el-row>30/06/2020 18:00</el-row>
                                </el-col>
                            </el-col>
                            <el-col :span="6">
                                <el-rate
                                        v-model="comment_value"
                                        disabled
                                        show-score
                                        text-color="#ff9900"
                                        score-template="{value}"
                                        style="text-align: left; padding: 10px 0">
                                </el-rate>
                            </el-col>
                        </el-row>
                        <el-row>
                            <p>This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user.</p>
                        </el-row>
                    </div>
                    <div>
                        <el-row>
                            <el-col :span="18">
                                <el-col :span="2">
                                    <el-avatar icon="el-icon-user-solid"></el-avatar>
                                </el-col>
                                <el-col :span="22">
                                    <el-row>username</el-row>
                                    <el-row>30/06/2020 18:00</el-row>
                                </el-col>
                            </el-col>
                            <el-col :span="6">
                                <el-rate
                                        v-model="comment_value"
                                        disabled
                                        show-score
                                        text-color="#ff9900"
                                        score-template="{value}"
                                        style="text-align: left; padding: 10px 0">
                                </el-rate>
                            </el-col>
                        </el-row>
                        <el-row>
                            <p>This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user. This is the comment of a user.</p>
                        </el-row>
                        <el-divider></el-divider>
                        <el-row>
                            <el-input
                                    style="margin-top:20px"
                                    type="textarea"
                                    :rows="5"
                                    placeholder="Enter comment"
                                    v-model="textarea"
                            >
                            </el-input>
                        </el-row>
                        <el-row>
                            <el-col :span="6">
                                <el-row>
                                    <div style="margin-top: 20px" class="block">
                                        <span class="demonstration" >Rate this event</span>
                                        <el-rate
                                                show-text
                                                :texts="['oops', 'disappointed', 'normal', 'good', 'great']"
                                                style="margin-top: 20px"
                                                v-model="rate_value"
                                                :colors="colors">
                                        </el-rate>
                                    </div>
                                </el-row>
                            </el-col>
                            <el-col :span="18">
                                <el-row>
                                    <el-button style="margin-top:40px; margin-left: 400px" type="primary" round>Submit</el-button>
                                </el-row>
                            </el-col>
                        </el-row>
                    </div>
                </el-row>
            </el-col>

            <el-col :span="8">
                <div style="text-align: left;">
                    <span style="font-size: 24px;">Recommended for you</span>
                </div>
                <el-row :gutter="5" style="margin: 15px 0 0 0;">
                    <el-col :span="12">
                        <img src="../assets/img/Joker2.png" alt="" style="width: 150px; border-radius: 5px;"/>
                    </el-col>
                    <el-col :span="12" style="text-align: left">
                        <el-row>
                            <span style="font-size: 24px">Joker (2019)</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Description of event...</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Date of the event...</span>
                        </el-row>
                        <el-row>
                            <span style="color: red">Price from $200</span>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row :gutter="5" style="margin: 15px 0 0 0;">
                    <el-col :span="12">
                        <img src="../assets/img/Joker2.png" alt="" style="width: 150px; border-radius: 5px;"/>
                    </el-col>
                    <el-col :span="12" style="text-align: left">
                        <el-row>
                            <span style="font-size: 24px">Joker (2019)</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Description of event...</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Date of the event...</span>
                        </el-row>
                        <el-row>
                            <span style="color: red">Price from $200</span>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row :gutter="5" style="margin: 15px 0 0 0;">
                    <el-col :span="12">
                        <img src="../assets/img/Joker2.png" alt="" style="width: 150px; border-radius: 5px;"/>
                    </el-col>
                    <el-col :span="12" style="text-align: left">
                        <el-row>
                            <span style="font-size: 24px">Joker (2019)</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Description of event...</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Date of the event...</span>
                        </el-row>
                        <el-row>
                            <span style="color: red">Price from $200</span>
                        </el-row>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>

        <div style="margin-top: 950px;padding: 40px 50px;border-top: 1px solid #121212;">
            <el-row>
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
    </div>
</template>

<script>
    export default {
        data () {
            return {
                num: 1,
                activeIndex: '1',
                location_options: [{
                    loc_value: 'Sydney',
                    label: 'Sydney'
                }, {
                    loc_value: 'Melbourne',
                    label: 'Melbourne'
                }],
                date_options: [{
                    value: '19072020',
                    label: 'July 19'
                }, {
                    value: '20072020',
                    label: 'July 20'
                }],
                time_options: [{
                    time_value: '1300',
                    label: '13:00'
                }, {
                    time_value: '1800',
                    label: '18:00'
                }],
                loc_value: '',
                value: '',
                time_value: '',
                star_value: 3.7,
                comment_value: 4.5,
                amount: 200,
                price: 200,
                textarea: '',
                rate_value: null,
                colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
            }
        },
        mounted: function () {},
        methods: {
            handleChange(value) {
                this.amount = value * this.price;
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scope>
    a {
        text-decoration: none;
    }
    .el-dialog {
        border-radius: 25px;
        width: 900px;
        height: 600px;
    }

    .el-dialog__header {
        display: none;
    }

    .el-dialog__body {
        padding: 0px;
        height: 100%;
    }

</style>
