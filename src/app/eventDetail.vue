<template>
<!--    Scroll down to see the bottom-right button.-->
    <div class="contour" style="background-color: white; width: 1280px">
        <el-row style="padding: 30px;">
            <el-col :span="18">
                <el-row :gutter="30">
                    <el-col :span="12">
                        <div style="width: 390px; height: 600px">
                            <img v-if="imageUrl" :src="imageUrl" alt="" width="390px" style="border-radius: 15px; display: block; margin: 0 auto" />
<!--                        <img src="http://localhost:9999/photos/1168374373e34d9e9fe0b2f573b8a2d9.png" alt="" width="390px" style="border-radius: 15px; display: block; margin: 0 auto" />-->
                            <div v-else style="height: 600px; border-radius: 5px; background-color: #f3f3f3">
                                <img src="../assets/img/CYSNlogo.png" alt="" style="margin: 280px auto">
                            </div>
                        </div>
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
                                <span>{{getDate(event.start_date)}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="text-align: left; padding: 10px">
                            <el-col :span="6" style="padding: 10px 0px">
                                <span>Time: </span>
                            </el-col>
                            <el-col :span="18" style="padding: 10px 0px">
                                <span>{{getTime(event.start_time)}}</span>
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
                        <el-menu-item index="2"  style="font-size: 20px"><a href="#review">Review</a></el-menu-item>
                    </el-menu>
                </el-row>
                <el-row id="description" name="description" style="text-align: left">
                    <h2>Description</h2>
                    <p>{{event.description}}</p>
                </el-row>
                <el-row id="review" name="review" style="text-align: left">
                    <h2>Review</h2>
                    <div v-for="(review, index) in this.reviewsList" style="border: 1px solid #dddddd; border-radius: 5px">
                        <el-row>
                            <el-col :span="3">
                                <img v-if="review.c_avatar" :src="getPic(review.c_avatar)" alt="" style="width: 40px; height: 40px; vertical-align: middle; margin: 10px 10px 0 0; border-radius: 20px; border: 1px solid #DCDFE6">
                                <img v-else src="../assets/img/login.png" alt="" style="width: 40px; height: 40px; vertical-align: middle; margin: 10px 10px 0 0; border-radius: 20px; border: 1px solid #DCDFE6">
                            </el-col>
                            <el-col :span="21">
                                <el-row>{{review.c_user_name}}</el-row>
                                <el-row>{{getDate(review.c_create_time)}} {{getTime(review.c_create_time)}}</el-row>
                            </el-col>
                        </el-row>
                        <el-row style="margin-left: 20px">
                            <p style="word-break:break-word;">{{review.c_content}}</p>
                        </el-row>
                        <el-row v-if="review.h_user_name !== null" style="margin-left: 80px; border: 1px solid #dddddd; border-radius: 5px">
                            <el-row>
                                <el-col :span="3">
                                    <img v-if="review.h_avatar" :src="getPic(review.h_avatar)" alt="" style="width: 40px; height: 40px; margin: 10px 10px 0 0; vertical-align: middle; border-radius: 20px; border: 1px solid #DCDFE6">
                                    <img v-else src="../assets/img/login.png" alt="" style="width: 40px; height: 40px; vertical-align: middle; border-radius: 20px; border: 1px solid #DCDFE6">
                                </el-col>
                                <el-col :span="21">
                                    <el-row>{{review.h_user_name}}</el-row>
                                    <el-row>{{getDate(review.h_create_time)}} {{getTime(review.h_create_time)}}</el-row>
                                </el-col>
                            </el-row>
                            <el-row style="margin-left: 20px">
                                <p style="word-break:break-word;">{{review.h_content}}</p>
                            </el-row>
                        </el-row>
                        <el-row v-else style="margin-left: 30px">
                            <el-col :span="21">
                                <el-input
                                        style="margin-top:20px"
                                        type="textarea"
                                        :rows="2"
                                        placeholder="Enter Reply"
                                        v-model="reply"
                                >
                                </el-input>
                            </el-col>
                            <el-col :span="3">
                                <el-button style="margin-top:40px;" type="primary" @click="sendReply" round>Submit</el-button>
                            </el-col>
                        </el-row>
                    </div>
                    <el-divider></el-divider>
                    <el-row>
                        <el-input
                                style="margin-top:20px"
                                type="textarea"
                                :rows="5"
                                placeholder="Enter review"
                                v-model="content"
                        >
                        </el-input>
                    </el-row>
                    <el-row>
                        <el-button style="margin-top:40px;" type="primary" @click="sendReview" round>Submit</el-button>
                    </el-row>
                </el-row>
            </el-col>

            <el-col :span="6">
                <div style="text-align: center;">
                    <span style="font-size: 24px;">Recommended for you</span>
                </div>
                <el-row :gutter="10" style="margin: 15px 0 0 0;" v-for="(recommended, index) in this.recommendeds" :key="recommended.id">
                    <el-col :span="12">
                        <img v-if="recommended.cover_image" :src="getPic(recommended.cover_image)" alt="" style="width: 150px; border-radius: 5px;"/>
                        <div v-else style="height: 200px; border-radius: 5px; background-color: #f3f3f3">
                            <img src="../assets/img/CYSNlogo.png" alt="" style="margin: 80px auto">
                        </div>
                    </el-col>
                    <el-col :span="12" style="text-align: left">
                        <el-row>
                            <span style="font-size: 24px">{{recommended.title}}</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">At {{recommended.address}}</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Date: {{getDate(recommended.start_date)}}</span>
                        </el-row>
                        <el-row>
                            <span style="color: grey">Time: {{getTime(recommended.start_time)}}</span>
                        </el-row>
                        <el-row>
                            <span style="color: red">Price: {{recommended.ticket_price}}</span>
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
                            <el-input style="margin-top: 20px; width: 580px; " v-model="order.address_1" placeholder="Address line1"></el-input>
                            <el-input style="margin-top: 20px; width: 580px; " v-model="order.address_2" placeholder="Address line2"></el-input>
                            <el-input style="margin-top: 20px; width: 580px; " v-model="order.email" placeholder="Email address"></el-input>
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
                            <el-button style="margin-top: 40px; margin-left:45px; float: left" type="primary" @click="stepTwo">NEXT</el-button>
                            <el-button style="margin-top: 40px; margin-left:45px; float: left" type="primary" @click="cancelOrder">CANCEL</el-button>
                        </el-col>
                        <el-col :span="6">
                            <el-row>
                                <span style="float: left; margin-top: 40px;font-weight: bolder;color: black;font-size: larger">Summary</span>
                                <img style="float: left; margin-top: 20px;" src="../assets/img/joker.png" alt="" width="80%" height="100%" />
                            </el-row>

                        </el-col>
                    </el-tab-pane>
                    <el-tab-pane label="2.Payment Options" name="second">
                        <el-col :span="18" style="height: 100%;margin-top: 50px">
                            <div>
                                <div style="width: 580px; border: 1px solid #DCDFE6; border-radius: 4px; margin: 0 0 30px 60px; text-align: left">
                                    <el-radio v-model="order.status" label="1" style="margin: 10px 10px 0 10px">
                                        <img src="../assets/img/visa1.png" alt=""  height="18px" />
                                        <span style="font-size: 20px;margin-left: 5px">Visa/Master</span>
                                    </el-radio>
                                    <el-row style="margin-top: 20px;" :gutter="22">
                                        <el-col :span="12">
                                            <el-input style="margin-left: 20px; margin-top: 10px"
                                                      placeholder="0000 0000 0000 0000"
                                                      suffix-icon="el-icon-bank-card"
                                                      v-model="order.card_number"
                                                      clearable>
                                            </el-input>
                                        </el-col>
                                        <el-col :span="5" >
                                            <el-input style="margin-top: 10px; margin-left: 20px"
                                                      placeholder="MM/YY" v-model="order.expiry_date" clearable></el-input>
                                        </el-col>
                                        <el-col :span="5" >
                                            <el-input style="margin-top: 10px; margin-left: 20px"
                                                      placeholder="CVV" v-model.number="order.cvv" show-password></el-input>
                                        </el-col>
                                    </el-row>
                                    <el-row>
                                        <el-col :span="22" >
                                            <el-input style="margin: 20px 20px 30px 20px"
                                                      placeholder="Card Holder Name" v-model="order.card_holder" clearable></el-input>
                                        </el-col>
                                    </el-row>
                                </div>
                                <div style="width: 580px; border: 1px solid #DCDFE6; border-radius: 4px; margin: 0 0 30px 60px; text-align: left">
                                    <el-radio v-model="order.status" label="2" style="text-align: left; padding: 12px">
                                        <img src="../assets/img/Paypal222.png" alt=""  height="18px" />
                                        <span style="font-size: 20px;margin-left: 5px">Paypal</span>
                                    </el-radio>
                                </div>

                            </div>
                            <el-row style="text-align: left">
                                <el-button style="margin-top: 40px;margin-left: 50px; width: 200px" type="primary" @click="pay">Pay Now</el-button>
                                <el-button style="margin-top: 40px; margin-left:45px;" type="primary" @click="cancelOrder">Cancel</el-button>
                            </el-row>
                        </el-col>
                        <el-col :span="6">
                            <el-row style="text-align: left; margin: 40px 0 0 0;">
                                <span style="margin-top: 40px;font-weight: bolder;color: black;font-size: larger">Summary</span>
                                <img style="margin-top: 20px;" src="../assets/img/joker.png" alt="" width="80%" height="100%" />
                            </el-row>
                            <el-row>
                                <el-col :span="10">
                                    <el-row style="text-align: left; margin: 10px 0;">
                                        <span style="font-weight: bolder;color: black">{{event.title}}</span>
                                    </el-row>
                                    <el-row style="text-align: left; margin: 10px 0;">
                                        <span style="font-weight: bolder;color: black">No. Tickets</span>
                                    </el-row>
                                    <el-row style="text-align: left; margin: 10px 0;line-height: 28px">
                                        <span style="font-weight: bolder;color: black">Donation</span>
                                    </el-row>
                                    <el-row style="text-align: left; margin: 10px 0;">
                                        <span style="font-weight: bolder;color: black">Amount</span>
                                    </el-row>
                                </el-col>
                                <el-col :span="14">
                                    <el-row style="text-align: left; margin: 10px 0;text-align: center;width: 93px">
                                        <span style="font-weight: bolder;color: black;">${{event.ticket_price}}</span>
                                    </el-row>
                                    <el-row style="text-align: left; margin: 10px 0;text-align: center;width: 93px">
                                        <span style="font-weight: bolder;color: black">{{order.ticket_amount}}</span>
                                    </el-row>
                                    <el-row>
                                        <el-input-number v-model="order.donate" controls-position="right" @change="handleChange_donation" :min="0" :max="1000" size="mini"></el-input-number>
                                    </el-row>
                                    <el-row style="text-align: left; margin: 10px 0;text-align: center;width: 93px">
                                        <span style="font-weight: bolder;color: red">${{amount}}</span>
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
                value1: '',
                content: '',
                colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
                dialogFormVisible: false,
                input: '',
                radio1: '1',
                event: {},
                amount: 0,
                order: {
                    ticket_amount: 1,
                    donate: 0
                },
                imageUrl: '',
                recommendeds: [],
                reviewsList: [],
                reply: '',
                user: {}
            }
        },
        mounted: function () {},
        methods: {
            handleChange(value) {
                this.amount = value * this.event.ticket_price;
            },
            handleChange_donation(value) {
                this.amount = this.event.ticket_price * this.order.ticket_amount + value;
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
            stepTwo(){
                this.activeName = 'second';
                this.order.event_id = this.event.id;
            },
            pay(){
                if(this.order.status === '1') {
                    this.order.status = 1;
                } else if(this.order.status === '2') {
                    this.order.status = 2;
                }
                this.$http.post("/order/create", this.order).then(
                    res=>{
                        console.log(res);
                        if(res.state){
                            this.order = res.order;
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
                            })
                        }
                    })
            },
            cancelOrder(){
                this.dialogFormVisible = false;
            },
            sendReview(){
                let obj = {
                    content: this.content,
                    eventId: this.event.id
                };
                this.$http.post("/eventReview/send", obj).then(
                    res=>{
                        if(res.state) {
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            location.reload();
                        } else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            })
                        }
                    }
                )
            },
            getDate(date) {
                let res;
                if(date !== null && date !== undefined) {
                    res = date.split('T')[0];
                }
                return res;
            },
            getTime(time) {
                let res;
                if(time !== null && time !== undefined) {
                    res = time.split(/[T.]/)[1];
                }
                return res;
            },
            sendReply(){
                let obj = {
                    content: this.reply,
                    eventId: this.event.id,
                    userId: this.user.id
                }
                this.$http.post("/eventReview/reply", obj).then(
                    res=>{
                        if(res.state) {
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            location.reload();
                        } else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            })
                        }
                    }
                )
            },
            showReviews(reviews) {
                console.log(reviews);
                let i;
                let res = {}
                for (i in reviews) {
                    console.log('review is');
                    let review = reviews[i];
                    console.log(review);
                    res.c_avatar = review[1].avatar;
                    res.c_user_name = review[1].user_name;
                    res.c_create_time = review[0].updated_at;
                    res.c_content = review[0].content;
                    if (review.length == 4) {
                        res.h_avatar = review[3].avatar;
                        res.h_user_name = review[3].user_name;
                        res.h_create_time = review[2].updated_at
                        res.h_content = review[2].content;
                    }
                    console.log(res);
                    this.reviewsList.push(res);
                }

            }
            // getPic(path){
            //     if(path !== null) {
            //         let len = path.split('/').length - 1;
            //         const file = path.split('/')[len];
            //         // let imagePath = require('/Users/edgar/Desktop/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/' + file);
            //         let imagePath = 'http://localhost:9999/photos/'+file;
            //         return imagePath;
            //     }
            // }
        },
        created() {
            let userString = localStorage.getItem("user");
            if(userString){
                this.user =  JSON.parse(userString).user;
            }
            let id = this.$route.params.id;
            this.$http.get("/event/find/"+id).then(
                res=>{
                    console.log(res);
                    if(res.state){
                        this.event = res.event;
                        this.recommendeds = res.recommended;
                        this.showReviews(res.eventReviews);
                        console.log(this.reviewsList);
                        // this.event.start_date = this.event.start_date.split('T')[0];
                        // this.event.start_time = this.event.start_time.split(/[T.]/)[1];
                        this.imageUrl = this.getPic(this.event.cover_image);
                        // let len = this.event.cover_image.split('/').length - 1;
                        // const file = this.event.cover_image.split('/')[len];
                        // this.imageUrl = require('/Users/edgar/Desktop/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/' + file);
                        this.amount = this.event.ticket_price;
                    }
                    else {
                        this.$message({
                            message: res.msg,
                            type: 'fail'
                        })
                    }
                }
            );
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
