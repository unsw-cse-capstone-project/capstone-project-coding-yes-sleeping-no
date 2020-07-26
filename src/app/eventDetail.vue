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
                            <span style="font-size: 32px;">{{event.title}}</span>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Location: </span>
                            </el-col>
                            <el-col :span="18" style="padding: 10px 0px">
                                <span>{{event.address}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Date: </span>
                            </el-col>
                            <el-col :span="18" style="padding: 10px 0px">
                                <span>{{event.start_date}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Time: </span>
                            </el-col>
                            <el-col :span="18" style="padding: 10px 0px">
                                <span>{{event.start_time}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Price: </span>
                            </el-col>
                            <el-col :span="18" style="padding:10px 0px">
                                <span style="color: red;">${{event.ticket_price}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left;padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Ticket: </span>
                            </el-col>
                            <el-col :span="18" >
                                <el-input-number v-model="order.ticket_amount" @change="handleChange" :min="1" :max="100" label="Ticket Amount"></el-input-number>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span style="font-size: 24px">Amount: </span>
                            </el-col>
                            <el-col :span="18" style="padding:10px 0px">
                                <span style="color: red; font-size: 24px; padding: 0 20px">${{amount}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 5px">
                            <el-button type="primary" @click="dialogFormVisible = true">Book and Pay</el-button>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row style="margin: 50px 0">
                    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                        <el-menu-item index="1"  style="font-size: 20px"><a href="#description">Description</a></el-menu-item>
                        <el-menu-item index="2"  style="font-size: 20px"><a href="#comment">Review</a></el-menu-item>
                    </el-menu>
                </el-row>

                <el-row id="description" name="description" style="text-align: left">
                    <h2>Description</h2>
                    <p>{{event.description}}</p>
                </el-row>
                <el-row id="comment" name="comment" style="text-align: left">
                    <h2>Review</h2>
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
                            <el-button style="margin: 10px 0" type="primary" round>Submit</el-button>
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
        <el-dialog
                class="dialog"
                :visible.sync="dialogFormVisible"
                width="890px">
            <el-row style="height: 80%;">
                <el-tabs :stretch="true" v-model="this.activeName" >
                    <el-tab-pane label="1.Shipping Detail" name="first">
                        <el-col :span="18" style="height: 100%;">
                            <el-input style="margin-top: 80px; width: 280px; " v-model="order.first_name" placeholder="First name"></el-input>
                            <el-input style="margin-top: 80px; margin-left:15px; width: 280px; " v-model="order.last_name" placeholder="Last name"></el-input>
<!--                            <el-input style="margin-top: 20px; width: 580px; " v-model="order.address_1" placeholder="Address line1"></el-input>-->
<!--                            <el-input style="margin-top: 20px; width: 580px; " v-model="order.address_2" placeholder="Address line2"></el-input>-->
                            <el-input style="margin-top: 20px; width: 580px; " v-model="order.address_1" placeholder="Address line"></el-input>
                            <el-input style="margin-top: 20px; width: 280px; " v-model="order.city" placeholder="City"></el-input>
                            <el-select style="margin-left:15px; width:280px" v-model="order.state" placeholder="State/Province">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                            <el-input style="margin-top: 20px; width: 280px; " v-model="order.postcode" placeholder="Zip code"></el-input>
                            <el-input style="margin-top: 20px; margin-left:15px; width: 280px; " v-model="order.phone" placeholder="Phone number"></el-input>
                            <el-button style="margin-top: 70px; margin-left:45px; float: left" type="primary" @click="stepTwo">NEXT</el-button>
                        </el-col>
                        <el-col :span="6">
                            <el-row>
                                <span style="float: left; margin-top: 40px;font-weight: bolder;color: black;font-size: larger">Summary</span>
                                <img style="float: left; margin-top: 20px;" src="../assets/img/joker.png" alt="" width="80%" height="100%" />
                            </el-row>
                            <el-row>
                                <el-col :span="14">
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black">{{event.title}}</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 20px;font-weight: bolder;color: black">Tickets</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black">Total</span>
                                    </el-row>
                                </el-col>
                                <el-col :span="10">
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black;">{{event.ticket_price}}</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 20px;font-weight: bolder;color: black">{{order.ticket_amount}}</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black">{{amount}}</span>
                                    </el-row>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-tab-pane>
                    <el-tab-pane label="2.Payment Options" name="second">
                        <el-col :span="18" style="height: 100%;margin-top: 50px">
                            <div>
                                <el-radio v-model="paymt.status" label="1" border>
                                    <el-row style="width: 95%; float: right;text-align: left">
                                        <img src="../assets/img/VISA1.png" alt=""  height="18px" />
                                        <span style="font-size: 20px;margin-left: 5px">Visa/Master</span>
                                    </el-row>
                                    <el-row style="margin-top: 20px;" :gutter="22">
                                        <el-col :span="12">
                                            <el-input style="margin-left: 20px; margin-top: 10px"
                                                      placeholder="0000 0000 0000 0000"
                                                      suffix-icon="el-icon-bank-card"
                                                      v-model="paymt.card_number"
                                                      clearable>
                                            </el-input>
                                        </el-col>
                                        <el-col :span="5" >
                                            <el-input style="margin-top: 10px; margin-left: 20px"
                                                      placeholder="MM/YY" v-model="paymt.expiry_date" clearable></el-input>
                                        </el-col>
                                        <el-col :span="5" >
                                            <el-input style="margin-top: 10px; margin-left: 20px"
                                                      placeholder="CVV" v-model="paymt.cvv" show-password></el-input>
                                        </el-col>
                                    </el-row>
                                    <el-row >
                                        <el-col :span="22" >
                                            <el-input style="margin-left: 20px; margin-bottom: 30px; margin-right: 20px; margin-top: 20px"
                                                      placeholder="Card Holder Name" v-model="paymt.card_holder" clearable></el-input>
                                        </el-col>
                                    </el-row>
                                </el-radio>
                                <el-radio v-model="paymt.status" label="2" border>
                                    <el-row style="width: 95%; float: right;text-align: left;margin-bottom: 15px">
                                        <img src="../assets/img/Paypal222.png" alt=""  height="18px" />
                                        <span style="font-size: 20px;margin-left: 5px">Paypal</span>
                                    </el-row>
                                </el-radio>
                            </div>
                            <el-row>
                                <el-button style="float: left;margin-top: 40px;margin-left: 50px; width: 200px" type="primary" @click="pay">Pay Now</el-button>
                            </el-row>
                        </el-col>
                        <el-col :span="6">
                            <el-row>
                                <span style="float: left; margin-top: 40px;font-weight: bolder;color: black;font-size: larger">Summary</span>
                                <img style="float: left; margin-top: 20px;" src="../assets/img/joker.png" alt="" width="80%" height="100%" />
                            </el-row>
                            <el-row>
                                <el-col :span="14">
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black">{{event.title}}</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 20px;font-weight: bolder;color: black">Tickets</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black">Total</span>
                                    </el-row>
                                </el-col>
                                <el-col :span="10">
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black;">{{event.ticket_price}}</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 20px;font-weight: bolder;color: black">{{order.ticket_amount}}</span>
                                    </el-row>
                                    <el-row>
                                        <span style="float: left; margin-top: 40px;font-weight: bolder;color: black">{{amount}}</span>
                                    </el-row>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-tab-pane>
                </el-tabs>
            </el-row>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                options: [{
                    value: 'ACT',
                    label: 'ACT'
                }, {
                    value: 'NSW',
                    label: 'NSW'
                }, {
                    value: 'NT',
                    label: 'NT'
                }, {
                    value: 'QLD',
                    label: 'QLD'
                }, {
                    value: 'SA',
                    label: 'SA'
                }, {
                    value: 'TAS',
                    label: 'TAS'
                }, {
                    value: 'VIC',
                    label: 'VIC'
                }, {
                    value: 'WA',
                    label: 'WA'
                }, {
                    value: 'JBT',
                    label: 'JBT'
                }],
                activeIndex: '1',
                activeName: 'first',
                loc_value: '',
                date_value: '',
                time_value: '',
                comment_value: 4.5,
                value1: '',
                textarea: '',
                rate_value: null,
                colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
                dialogFormVisible: false,
                input: '',
                radio1: '1',
                event: {},
                amount: 0,
                order: {
                    ticket_amount: 1
                },
                paymt: {}
            }
        },
        mounted: function () {},
        methods: {
            handleChange(value) {
                this.order.amount = value * this.event.ticket_price;
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
            stepTwo(){
                this.paymt.event_id = this.event.id;
                this.$http.post("/order/create", this.paymt).then(
                    res=>{
                        console.log(res);
                        if(res.state){
                            this.order = res.order;
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            this.activeName = 'second';
                        }
                        else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            })
                        }
                    }
                )
            },
            pay(){
                this.$http.get("/order/confirm/"+this.order.id, this.paymt).then(
                    res=>{
                        if(res.state){
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            location.href='/';
                        }
                        else {
                            this.dialogFormVisible = false;
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            })
                        }
                    }
                )
            }
        },
        created() {
            let id = this.$route.params.id;
            this.$http.get("/event/find/"+id).then(
                res=>{
                    // console.log(res);
                    if(res.state){
                        this.event = res.event;
                        this.$message({
                            message: res.msg,
                            type: 'success'
                        });
                        this.amount = this.event.ticket_price;
                    }
                    else {
                        this.$message({
                            message: res.msg,
                            type: 'fail'
                        })
                    }
                }
            )
        }
    }
</script>

<style lang="scss" scope>
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

    .el-radio.is-bordered{
        height: auto;
        width: 580px;
        margin-left: 40px;
        margin-bottom:30px;
    }

</style>
